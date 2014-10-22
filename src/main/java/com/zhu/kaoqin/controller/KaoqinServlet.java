/**
 * 
 */
package com.zhu.kaoqin.controller;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gxws.tools.spring.SpringBeanTool;
import com.zhu.kaoqin.service.KaoqinServiceI;
import com.zhu.kaoqin.vo.EComelateVo;
import com.zhu.kaoqin.vo.EDetailByNameVo;
import com.zhu.kaoqin.vo.EGroupByNameVo;
import com.zhu.kaoqin.vo.EPageRequestVo;

/**
 * 
 * @作者： 朱伟亮
 * @创建时间：2013-4-9 下午4:45:17
 */
@Controller
@RequestMapping("/kaoqin")
public class KaoqinServlet extends AbsWriteJsonDataServlet {

	private String path = "kaoqin";

	private Logger log = Logger.getLogger(KaoqinServlet.class);

	/**
	 * 考勤表格页面
	 * 
	 * @return
	 */
	@RequestMapping({ "", "/" })
	public String kaoqin() {
		return path + "/kaoqin";
	}

	/**
	 * 所有
	 * 
	 * @param response
	 * @param page
	 */
	@RequestMapping("/kaoqinJson")
	public void kaoqinJson(HttpServletResponse response,
			EPageRequestVo pageRequestVo) {
		super.writeJson(kaoqinService().findAllJson(pageRequestVo), response);
	}

	private KaoqinServiceI kaoqinService() {
		return (KaoqinServiceI) SpringBeanTool.getBean("kaoqinService");
	}

	@RequestMapping("/comlateJson")
	public void comelateJson(HttpServletResponse response, EComelateVo comelateVo) {
		super.writeJson(kaoqinService().comelateJson(comelateVo), response);
	}

	/**
	 * 按名字分组
	 * 
	 * @作者：朱伟亮
	 * @创建时间：2013-4-22 上午10:22:56
	 * @param response
	 * @param groupByNameVo
	 */
	@RequestMapping("/groupByNameJson")
	public void groupByName(HttpServletResponse response,
			EGroupByNameVo groupByNameVo) {
		super.writeJson(kaoqinService().groupByName(groupByNameVo), response);
	}

	/**
	 * 到名字分组页面
	 * 
	 * @作者：朱伟亮
	 * @创建时间：2013-4-22 上午10:23:11
	 * @return
	 */
	@RequestMapping("/groupByName")
	public String groupByName() {
		return path + "/groupByName";
	}

	/**
	 * 按名字查询详细
	 * 
	 * @作者：朱伟亮
	 * @创建时间：2013-4-22 上午10:41:38
	 * @param response
	 * @param detailByNameVo
	 */
	@RequestMapping("/detailByNameJson")
	public void detailByNameJson(HttpServletResponse response,
			EDetailByNameVo detailByNameVo) {
		writeJson(kaoqinService().detailByName(detailByNameVo), response);
	}

}
