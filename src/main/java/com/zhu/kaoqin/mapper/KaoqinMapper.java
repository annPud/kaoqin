package com.zhu.kaoqin.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zhu.kaoqin.tb.Kaoqin;
import com.zhu.kaoqin.view.NameView;

/**
 * 考勤模块
 * 
 * @author zhuwl120820@gxwsxx.com
 * @since 2.0
 */
public interface KaoqinMapper {

	public List<Kaoqin> selectdetailByName(String ename, Date startTime, Date endTime);

	public List<NameView> selectGroupByName(Date startTime, Date endTime, @Param("sidx") String sidx,
			@Param("sord") String sord);

	public Long selectCountGroupByName(Date startTime, Date endTime);

}
