package com.zhu.kaoqin.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.RespectBinding;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//import com.alibaba.fastjson.JSON;
import com.zhu.kaoqin.service.KaoqinService;
import com.zhu.kaoqin.vo.JDetailByNameVo;
import com.zhu.kaoqin.vo.JPageBaseVo;

/**
 * 
 * @author annpud
 *
 */
@RestController
public class KaoqinController {

	private static final Logger log = Logger.getLogger(KaoqinController.class);

	@Autowired
	private KaoqinService ks;

	@RequestMapping({ "", "/" })
	public String kaoqin(HttpServletRequest req) {
		req.getServletContext().setAttribute("uri", req.getContextPath());
		return "/kaoqin";
	}

//	@RequestMapping("/groupByNameJson")
//	public void groupByNameJson(JPageBaseVo vo, HttpServletResponse res) {
//		writeJson(JSON.toJSONString(ks.groupByName(vo)), res);
//	}

//	@RequestMapping("/detailByNameJson")
//	public void detailByName(JDetailByNameVo vo, HttpServletResponse res) {
//		log.info(vo.getEname() + "" + vo.getMonthDate());
//		writeJson(JSON.toJSONString(ks.detailByName(vo)), res);
//	}

//	@RequestMapping("/detailByName")
//	public String fullCalender(HttpServletRequest req,String ename, Model model) {
//		log.info(ename);
//		model.addAttribute("ename", ename);
//		return "/calender";
//	}
//
//	private void writeJson(String jsonData, HttpServletResponse res) {
//		res.setContentType("text/html; charset=utf-8");
//		PrintWriter pw = null;
//		try {
//			pw = res.getWriter();
//			pw.print(jsonData);
//			pw.flush();
//			pw.close();
//		} catch (IOException e) {
//			log.error(e.getMessage());
//		} finally {
//			if (null != pw) {
//				pw.close();
//			}
//		}
//	}

}
