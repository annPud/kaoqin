/**
 * 
 */
package com.zhu.kaoqin.service;

import java.util.List;

import com.zhu.kaoqin.tb.Kaoqin;
import com.zhu.kaoqin.vo.JDetailByNameVo;
import com.zhu.kaoqin.vo.JPageBaseVo;

/**
 * @author 朱伟亮
 */
public interface KaoqinService {

	/**
	 * 查找名称分组
	 * 
	 * @author zhuwl120820@gxwsxx.com
	 * @param vo
	 *            参数
	 * @return 分组数据
	 * @since 2.0
	 */
	public JPageBaseVo groupByName(JPageBaseVo vo);

	/**
	 * 按名称查找详细数据
	 * 
	 * @author zhuwl120820@gxwsxx.com
	 * @param vo
	 *            参数
	 * @return 详细数据
	 * @since 2.0
	 */
	public List<Kaoqin> detailByName(JDetailByNameVo vo);
}