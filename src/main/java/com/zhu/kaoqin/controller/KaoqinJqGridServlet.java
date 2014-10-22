/**
 * 
 */
package com.zhu.kaoqin.controller;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gxws.tools.spring.SpringBeanTool;
import com.zhu.kaoqin.service.KaoqinServiceI;
import com.zhu.kaoqin.vo.JDetailByNameVo;
import com.zhu.kaoqin.vo.JPageBaseVo;

/**
 * 
 * @作者： 朱伟亮
 * @创建时间：2013-4-28 下午2:56:23
 */
@Controller
@RequestMapping("/kaoqinjq")
public class KaoqinJqGridServlet extends AbsWriteJsonDataServlet {

	private Logger log = Logger.getLogger(KaoqinJqGridServlet.class);

	private String pathJq = "kaoqinjq";

	@RequestMapping({ "", "/" })
	public String kaoqin() {
		// return path + "/kaoqin";
		return pathJq + "/kaoqinjq";
	}

	@RequestMapping("/groupByNameJson")
	public void groupByNameJson(JPageBaseVo vo, HttpServletResponse res) {
		writeJson(kaoqinService().groupByName(vo), res);
	}

	@RequestMapping("/detailByNameJson")
	public void detailByName(JDetailByNameVo vo, HttpServletResponse res) {
		log.info(vo.getEname());
		log.info(vo.getMonthDate());
		writeJson(kaoqinService().detailByName(vo), res);
	}

	@RequestMapping("/detailByName")
	public String fullCalender(String ename, Model model) {
		log.info("detail:" + ename);
		model.addAttribute("ename", ename);
		return pathJq + "/fullcalender";
	}

	private KaoqinServiceI kaoqinService() {
//		return (KaoqinServiceI) SpringBeanFactory.getBean("kaoqinService");
		return (KaoqinServiceI) SpringBeanTool.getBean("kaoqinService");
	}

}
