/**
 * 
 */
package com.zhu.kaoqin.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gxws.tools.spring.SpringBeanTool;
import com.zhu.kaoqin.service.FileReadServiceI;
import com.zhu.kaoqin.utils.AppConstant;

/**
 * 桌面初始化
 * 
 * @作者： 朱伟亮
 * @创建时间：2013-4-12 上午10:41:50
 */
@Controller
@RequestMapping("/desktop")
public class InitJsonDataServlet extends AbsWriteJsonDataServlet {

	private Logger log = Logger.getLogger(InitJsonDataServlet.class);

	@RequestMapping("/initDesktopJson")
	public void initDesktopJson(HttpServletRequest request,
			HttpServletResponse response) {
		String filePath = AppConstant.ABSOLUTE_PATH
				+ "static/comp/easyui-app/desktop.json";
		String jsonData = getFileRead().fileReadByLines(filePath, true);
		StringBuffer rootPath = new StringBuffer("http://");
		rootPath.append(request.getHeader("Host"));
		rootPath.append(request.getContextPath());
		String druidPath = rootPath.toString() + "/druid";
		String kaoqinPath = rootPath.toString() + "/kaoqin/groupByName";
		jsonData = jsonData.replace("&{druid}", druidPath);
		jsonData = jsonData.replace("&{kaoqin}", kaoqinPath);
		super.writeJson(jsonData, response);
	}

	private FileReadServiceI getFileRead() {
		return (FileReadServiceI) SpringBeanTool.getBean("fileReadService");
	}
}
