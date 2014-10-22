/**
 * 
 */
package com.zhu.kaoqin.service.Impl;

import java.util.Calendar;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.zhu.kaoqin.entity.KaoqinEntity;
import com.zhu.kaoqin.pojo.GroupByNamePojo;
import com.zhu.kaoqin.repository.BaseDaoI;
import com.zhu.kaoqin.service.KaoqinServiceI;
import com.zhu.kaoqin.vo.EComelateVo;
import com.zhu.kaoqin.vo.EDetailByNameVo;
import com.zhu.kaoqin.vo.EGroupByNameVo;
import com.zhu.kaoqin.vo.EPageRequestVo;
import com.zhu.kaoqin.vo.EPageDataVo;
import com.zhu.kaoqin.vo.JDetailByNameVo;
import com.zhu.kaoqin.vo.JPageBaseVo;

/**
 * 
 * @作者： 朱伟亮
 * @创建时间：2013-4-9 下午4:44:45
 */
@SuppressWarnings({ "unchecked", "rawtypes" })
@Service("kaoqinService")
public class KaoqinServiceImpl implements KaoqinServiceI {

	private Logger log = Logger.getLogger(KaoqinServiceImpl.class);

	@Autowired
	private BaseDaoI<KaoqinEntity> kaoqinDao;

	@Autowired
	private BaseDaoI<GroupByNamePojo> groupDao;

	/**
	 * 全部记录
	 */
	@Override
	public String findAllJson(EPageRequestVo pageRequestVo) {
		String hql = "from KaoqinEntity";
		List<KaoqinEntity> list = kaoqinDao.find(hql, pageRequestVo.getPage(),
				pageRequestVo.getRows(), new Object[0]);
		String hqlCount = "select count(*) " + hql;
		Long total = kaoqinDao.count(hqlCount, new Object[0]);
		EPageDataVo data = new EPageDataVo(list, total);
		return JSON.toJSONString(data);
	}

	/**
	 * 工作时间打卡记录
	 * 
	 * @作者：朱伟亮
	 * @创建时间：2013-4-17 下午2:30:21
	 * @param pageRequestVo
	 * @return
	 */
	@Override
	public String comelateJson(EComelateVo comelateVo) {
		String hqlSelect = "from KaoqinEntity k where EXTRACT(YEAR_MONTH FROM k.clock) = EXTRACT(YEAR_MONTH FROM ?) "
				+ " and time(?)<time(k.clock) and time(k.clock)<time(?) order by k.clock asc";
		Object[] param = new Object[] { comelateVo.getMonthDate(),
				comelateVo.getOnClock(), comelateVo.getOffClock() };
		List<KaoqinEntity> list = kaoqinDao.find(hqlSelect,
				comelateVo.getPage(), comelateVo.getRows(), param);
		String hqlCount = "select count(*)" + hqlSelect;
		Long count = kaoqinDao.count(hqlCount, param);
		EPageDataVo data = new EPageDataVo(list, count);
		return JSON.toJSONString(data);
	}

	@Override
	public String test() {
		String hql = "select count(*) from KaoqinEntity";
		Long count = kaoqinDao.count(hql, new Object[0]);
		return count.toString();
	}

	public String groupByName(EGroupByNameVo groupByNameVo) {
		String hql = "select new com.zhu.kaoqin.pojo.GroupByNamePojo(k.dep,k.ename,k.ckno) from KaoqinEntity k "
				+ "where EXTRACT(YEAR_MONTH FROM ?) = EXTRACT(YEAR_MONTH FROM k.clock)"
				+ "GROUP BY k.ename,k.ckno ORDER BY k.ckno,k.ename";
		List<GroupByNamePojo> list = groupDao.find(hql,
				groupByNameVo.getMonthDate());
		EPageDataVo data = new EPageDataVo(list, Long.valueOf(list.size()));
		return JSON.toJSONString(data);
	}

	public String detailByName(EDetailByNameVo detailByNameVo) {
		String hql = "from KaoqinEntity k where k.ename = ? and "
				+ "EXTRACT(YEAR_MONTH FROM ?) = EXTRACT(YEAR_MONTH FROM k.clock) order by k.clock asc";
		List<KaoqinEntity> list = kaoqinDao.find(hql,
				detailByNameVo.getEname(), detailByNameVo.getMonthDate());
		this.setWarn(list, detailByNameVo.getOffTime());
		EPageDataVo data = new EPageDataVo(list, Long.valueOf(list.size()));
		return JSON.toJSONString(data);
	}
	
	public String detailByName(JDetailByNameVo vo){
		String hql = "from KaoqinEntity k where k.ename = ? and "
				+ "EXTRACT(YEAR_MONTH FROM ?) = EXTRACT(YEAR_MONTH FROM k.clock) order by k.clock asc";
		List<KaoqinEntity> list = kaoqinDao.find(hql,
				vo.getEname(), vo.getMonthDate());
//		this.setWarn(list, vo.getOffTime());
		return JSON.toJSONString(list);
	}

	public String groupByName(JPageBaseVo vo) {
		String baseHql = "from KaoqinEntity k where EXTRACT(YEAR_MONTH FROM ?) = EXTRACT(YEAR_MONTH FROM k.clock) "
				+ "GROUP BY k.ename,k.ckno order by k."
				+ vo.getSidx()
				+ " "
				+ vo.getSord();
		String dataHql = "select new com.zhu.kaoqin.pojo.GroupByNamePojo(k.dep,k.ename,k.ckno) "
				+ baseHql;
		Object[] param = { vo.getMonthDate() };
		List<GroupByNamePojo> list = groupDao.find(dataHql, vo.getPage(),
				vo.getRows(), param);
		vo.setList(list);
		String countHql = baseHql;
		Long count = Long.valueOf(groupDao.find(countHql, param).size());
		vo.setRecords(count);
		vo.setTotal(count / vo.getRows() + 1);
		return JSON.toJSONString(vo);
	}

	private void setWarn(List<KaoqinEntity> list, String offTime) {
		KaoqinEntity en = null;
		for (int i = 0; i < list.size(); i++) {
			en = list.get(i);
			Calendar clock = Calendar.getInstance();
			Calendar ys = Calendar.getInstance();
			clock.setTime(en.getClock());
			if (0 != i) {
				ys.setTime(list.get(i - 1).getClock());
			}
			if (this.offTimeFilter(clock, offTime)) {
				if (0 == i) {
					en.setWarn("am-可能忘记打卡");
				} else {
					if (0 != (clock.get(Calendar.DAY_OF_MONTH) - ys
							.get(Calendar.DAY_OF_MONTH))) {
						en.setWarn("am-可能忘记打卡");
					}
				}
			} else {
				if (0 == i) {
					continue;
				} else if (this.offTimeFilter(ys, offTime)) {

				} else {
					en.setWarn("pm-可能忘记打卡");
				}
			}

			// SimpleDateFormat sdf = new
			// SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			// String offClockStr = sdf.format(offClock.getTime());
			// log.info("offClock:" + offClockStr);
		}
	}

	private boolean offTimeFilter(Calendar c, String offTime) {
		Calendar offClock = Calendar.getInstance();
		offClock.setTime(c.getTime());
		String[] offTimeArr = offTime.split(":");
		offClock.set(Calendar.HOUR_OF_DAY, Integer.parseInt(offTimeArr[0]));
		offClock.set(Calendar.MINUTE, Integer.parseInt(offTimeArr[1]));
		offClock.set(Calendar.SECOND, Integer.parseInt(offTimeArr[2]));
		if (c.getTimeInMillis() - offClock.getTimeInMillis() > 0) {
			return true;
		}
		return false;
	}
}
