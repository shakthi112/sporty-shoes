package com.sportyshoes.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sportyshoes.entity.Users;
import com.sportyshoes.repository.UsersRepository;

@Controller
public class BrowseUsersController {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	UsersRepository usersRepository;

	@RequestMapping(value = "/browseusers", method = RequestMethod.POST)
	public String browseusers(Model model) {
		try {
			List<Users> usersList = usersRepository.fetchAll();
			model.addAttribute(usersList);
		} catch (Exception e) {
			logger.info("Exception occured at ViewPurchaseReportsController" + e.getMessage());
		}
		return "browseusers";
	}
}
