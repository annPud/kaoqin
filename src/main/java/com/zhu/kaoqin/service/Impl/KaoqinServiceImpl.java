package com.zhu.kaoqin.service.Impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhu.kaoqin.mapper.KaoqinMapper;
import com.zhu.kaoqin.service.KaoqinService;
import com.zhu.kaoqin.tb.Kaoqin;
import com.zhu.kaoqin.view.NameView;
import com.zhu.kaoqin.vo.JDetailByNameVo;
import com.zhu.kaoqin.vo.JPageBaseVo;

/**
 * 
 * @author 朱伟亮
 */
@Service
public class KaoqinServiceImpl implements KaoqinService {

	private Logger log = Logger.getLogger(KaoqinServiceImpl.class);

	private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

	@Autowired
	private KaoqinMapper km;

	public List<Kaoqin> detailByName(JDetailByNameVo vo) {
		// String hql = "from KaoqinEntity k where k.ename = ? and "
		// + "EXTRACT(YEAR_MONTH FROM ?) = EXTRACT(YEAR_MONTH FROM k.clock)
		// order by k.clock asc";
		// List<Kaoqin> list = kaoqinDao.find(hql, vo.getEname(),
		// vo.getMonthDate());
		Date[] startEndTime = startEndTime(vo.getMonthDate());
		return km.selectdetailByName(vo.getEname(), startEndTime[0], startEndTime[1]);
	}

	public JPageBaseVo groupByName(JPageBaseVo vo) {
		// String baseHql = "from KaoqinEntity k where EXTRACT(YEAR_MONTH FROM
		// ?) = EXTRACT(YEAR_MONTH FROM k.clock) "
		// + "GROUP BY k.ename,k.ckno order by k." + vo.getSidx() + " " +
		// vo.getSord();
		// String dataHql = "select new
		// com.zhu.kaoqin.pojo.GroupByNamePojo(k.dep,k.ename,k.ckno) " +
		// baseHql;
		// Object[] param = { vo.getMonthDate() };
		// List<GroupByNamePojo> list = groupDao.find(dataHql, vo.getPage(),
		// vo.getRows(), param);
		Date[] startEndTime = startEndTime(vo.getMonthDate());
		List<NameView> list = km.selectGroupByName(startEndTime[0], startEndTime[1], vo.getSidx(), vo.getSord());
		vo.setList(list);
		// String countHql = baseHql;
		// Long count = Long.valueOf(groupDao.find(countHql, param).size());
		Long count = km.selectCountGroupByName(startEndTime[0], startEndTime[1]);
		vo.setRecords(count);
		vo.setTotal(count / vo.getRows() + 1);
		// return JSON.toJSONString(vo);
		return vo;
	}

	private Date[] startEndTime(String source) {
		Date[] startEnd = new Date[2];
		Date now = null;
		try {
			now = sdf.parse(source);
		} catch (ParseException e) {
			log.error(e.getMessage(), e);
			return null;
		}
		Calendar start = Calendar.getInstance();
		start.setTime(now);
		start.set(Calendar.DAY_OF_MONTH, 1);
		startEnd[0] = start.getTime();
		Calendar end = Calendar.getInstance();
		end.setTime(start.getTime());
		end.add(Calendar.MONTH, 1);
		startEnd[1] = end.getTime();
		return startEnd;
	}

}
