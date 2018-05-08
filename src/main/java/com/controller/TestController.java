package com.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.service.TestService;

import core.service.JasperReportsService;

@Controller("testController")
@RequestMapping("/test")
public class TestController {

	@Autowired
	private TestService testService;

	@Autowired
	private JasperReportsService jasperReportsService;

	@RequestMapping(value = "index", method = RequestMethod.GET)
	public Object index(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("/test");
		HttpSession session = request.getSession();
		session.setAttribute("key", "value");
		return mv;
	}

	@ResponseBody
	@RequestMapping(value = "json", method = RequestMethod.GET)
	public Object json(HttpServletRequest request) {
		return testService.Test("t");
	}
	
	@RequestMapping(value = "pdf", method = RequestMethod.GET)
	public void index(HttpServletRequest request, HttpServletResponse response) {
		jasperReportsService.exportPdf("test.jasper", true, response);
	}

}