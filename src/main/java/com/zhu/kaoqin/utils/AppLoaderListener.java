/**
 * 
 */
package com.zhu.kaoqin.utils;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.Logger;

/**
 * 
 * @作者： 朱伟亮
 * @创建时间：2013-4-10 上午9:26:36
 */
public class AppLoaderListener implements ServletContextListener  {

	private Logger log = Logger.getLogger(AppLoaderListener.class);


	@Override
	public void contextInitialized(ServletContextEvent sce) {
		ServletContext sc = sce.getServletContext();
		String realPath = sce.getServletContext().getRealPath("/");
		realPath = realPath.replace("\\", "/");
		AppConstant.ABSOLUTE_PATH = realPath;
		log.info("AppConstant.ABSOLUTE_PATH:" + AppConstant.ABSOLUTE_PATH);
		String contextPath = sc.getContextPath();
		AppConstant.CONTEXT_PATH = contextPath;
		log.info("AppConstant.CONTEXT_PATH:" + AppConstant.CONTEXT_PATH);
		sc.setAttribute("uri", contextPath);
	}
	
	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		
	}
}
