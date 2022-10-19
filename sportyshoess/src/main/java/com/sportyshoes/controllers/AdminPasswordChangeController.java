package com.sportyshoes.controllers;

import java.io.IOException;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.sportyshoes.entity.Admin;
import com.sportyshoes.repository.AdminRepository;

@Controller
public class AdminPasswordChangeController {

	@Autowired
	AdminRepository adminrepository;

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@RequestMapping(value = "/changepassword", method = RequestMethod.POST)
	public String adminLogin(@RequestParam("oldPsw") String oldPwdInput, @RequestParam("newPsw") String newPwdInput,
			HttpSession session, Model model) throws IOException {
		try {
			// Get admin id from session
			String adminid = (String) session.getAttribute("adminid");

			if (oldPwdInput.equals(newPwdInput)) {
				model.addAttribute("pwdChangeError", "Old and new passwords cannot be the same. Please try again.");
			} else {
				// Get admin with old password
				Admin adminObj = adminrepository.findById(adminid);
				String oldPwd = adminObj.getPwd();
				if (oldPwd.equals(oldPwdInput)) {
					// Update password
					adminObj.setPwd(newPwdInput);
					adminrepository.updateAdminPassword(adminObj);
					model.addAttribute("pwdChangeSuccess", "Password change successful.");
				} else {
					model.addAttribute("pwdChangeError",
							"Password change not successful. Please re-check the old password entered or try again.");
				}
			}
		} catch (Exception e) {
			logger.info("Exception occured at AdminLoginController" + e.getMessage());
			model.addAttribute("pwdChangeError",
					"Oops! Something went wrong. We regret the inconvenience caused. Request you to try again.");
			return "pwdchangeresponse";
		}
		return "pwdchangeresponse";
	}
}
