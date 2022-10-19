package com.sportyshoes.controllers;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import com.sportyshoes.entity.Admin;
import com.sportyshoes.repository.AdminRepository;

@Controller
public class AdminLoginController {
	@Autowired
	AdminRepository adminrepository;

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@RequestMapping(value = "/adminlogin", method = RequestMethod.POST)
	public RedirectView adminLogin(@RequestParam("username") String useridInput,
			@RequestParam("password") String passwordInput, HttpServletResponse response, HttpSession session)
			throws IOException {
		try {

			Admin adminObj = adminrepository.findById(useridInput);
			if (adminObj == null) {
				return new RedirectView("error.html");
			} else {
				String adminid = adminObj.getAdminid();
				session.setAttribute("adminid", adminid);
				String adminpwd = adminObj.getPwd();
				if (adminpwd.equals(passwordInput)) {
					return new RedirectView("adminlanding.html");
				} else {
					return new RedirectView("adminerror.html");
				}
			}

		} catch (Exception e) {
			logger.info("Exception occured at AdminLoginController" + e.getMessage());
			return new RedirectView("error.html");
		}

	}
}
