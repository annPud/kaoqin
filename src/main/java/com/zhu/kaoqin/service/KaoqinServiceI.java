/**
 * 
 */
package com.zhu.kaoqin.service;

import com.zhu.kaoqin.vo.EComelateVo;
import com.zhu.kaoqin.vo.EDetailByNameVo;
import com.zhu.kaoqin.vo.EGroupByNameVo;
import com.zhu.kaoqin.vo.EPageRequestVo;
import com.zhu.kaoqin.vo.JDetailByNameVo;
import com.zhu.kaoqin.vo.JPageBaseVo;

/**
 * 
 * @作者： 朱伟亮
 * @创建时间：2013-4-16 上午9:36:13
 */
public interface KaoqinServiceI {
	public abstract String findAllJson(EPageRequestVo pageRequestVo);

	public String comelateJson(EComelateVo comelateVo);

	public abstract String test();

	public String groupByName(EGroupByNameVo groupByNameVo);

	public String detailByName(EDetailByNameVo detailByNameVo);

	public String groupByName(JPageBaseVo vo);
	
	public String detailByName(JDetailByNameVo vo);
}