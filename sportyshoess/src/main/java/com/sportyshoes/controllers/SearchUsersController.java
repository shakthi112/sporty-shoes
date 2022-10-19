package com.sportyshoes.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.sportyshoes.entity.Users;
import com.sportyshoes.repository.UsersRepository;

@Controller
public class SearchUsersController {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	UsersRepository usersRepository;

	@RequestMapping(value = "/searchusers", method = RequestMethod.POST)
	public String searchusers(@RequestParam("fname") String fnameInput, @RequestParam("lname") String lnameInput,
			Model model) {
		try {
			List<Users> usersList = usersRepository.searchUsers(fnameInput, lnameInput);
			if(usersList.size() >= 1) {
				model.addAttribute(usersList);
			}
			else {
				model.addAttribute("noUserFound", "No users with matching name found.");
			}
		} catch (Exception e) {
			logger.info("Exception occured at SearchUsersController" + e.getMessage());
		}
		return "searchusersresults";
	}
}
