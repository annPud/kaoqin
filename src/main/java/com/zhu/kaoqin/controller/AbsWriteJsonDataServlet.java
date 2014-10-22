/**
 * 
 */
package com.zhu.kaoqin.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

/**
 * 返回json数据
 * 
 * @作者： 朱伟亮
 * @创建时间：2013-4-12 上午10:47:52
 */
public class AbsWriteJsonDataServlet {

	private Logger log = Logger.getLogger(AbsWriteJsonDataServlet.class);

	protected void writeJson(String jsonData,HttpServletResponse res){
//		log.info(jsonData);
		res.setContentType("text/html; charset=utf-8");
		PrintWriter pw = null;
		try {
			pw = res.getWriter();
			pw.print(jsonData);
			pw.flush();
			pw.close();
		} catch (IOException e) {
			log.error(e.getMessage());
		}finally{
			if(null != pw){
				pw.close();
			}
		}
	}
}
