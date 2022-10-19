package com.sportyshoes.controllers;

import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sportyshoes.entity.Purchases;
import com.sportyshoes.repository.PurchasesRepository;

@Controller
public class ViewPurchaseReportsController {

	@Autowired
	PurchasesRepository purchasesrepository;

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@RequestMapping(value = "/viewpurchasereports", method = RequestMethod.POST)
	public String viewPurchaseReports(Model model) throws IOException {
		try {
			List<Purchases> purchaseList = purchasesrepository.fetchAll();
			model.addAttribute("purchaseList", purchaseList);

		} catch (Exception e) {
			logger.info("Exception occured at ViewPurchaseReportsController" + e.getMessage());
		}
		return "purchasereports";
	}
}
