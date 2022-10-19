package com.sportyshoes.controllers;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.sportyshoes.entity.Products;
import com.sportyshoes.repository.ProductsRepository;

@Controller
public class ManageProductsController {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	ProductsRepository productsRepository;

	@RequestMapping(value = "/addproduct", method = RequestMethod.POST)
	public String addProduct(@RequestParam("productName") String productNameInput,
			@RequestParam("brand") String brandInput, @RequestParam("price") int priceInput, Model model) {
		try {
			productsRepository.insertProduct(productNameInput, brandInput, priceInput);
			model.addAttribute("successMsg", "Product added successfully!");
		} catch (Exception e) {
			logger.info("Exception occured at addProduct() ManageProductsController" + e.getMessage());
			model.addAttribute("errorMsg", "Oops! Something went wrong. Please try again later");
		}
		return "manageproductsresult";
	}

	@RequestMapping(value = "/updateproduct", method = RequestMethod.POST)
	public String updateProduct(@RequestParam("productIdUpdated") int productIdUpdated,
			@RequestParam(name = "productNameUpdated", required = false) String productNameUpdated,
			@RequestParam(name = "brandUpdated", required = false) String brandUpdated,
			@RequestParam(name = "priceUpdated", required = false, defaultValue = "0") int priceUpdated, Model model) {
		try {
			Products product = productsRepository.findById(productIdUpdated);
			if (product == null) {
				model.addAttribute("errorMsg", "Product with this Id not found! Try again!");
			} else {
				if (!StringUtils.isEmpty(productNameUpdated)) {
					product.setProductname(productNameUpdated);
				}
				if (!StringUtils.isEmpty(brandUpdated)) {
					product.setBrand(brandUpdated);
				}
				if (priceUpdated != 0) {
					product.setPrice(priceUpdated);
				}
				productsRepository.updateProduct(product);
				model.addAttribute("successMsg", "Product updated successfully!");
			}
		} catch (Exception e) {
			logger.info("Exception occured at updateProduct() ManageProductsController" + e.getMessage());
			model.addAttribute("errorMsg", "Oops! Something went wrong. Please try again later");
		}
		return "manageproductsresult";
	}

	@RequestMapping(value = "/deleteproduct", method = RequestMethod.POST)
	public String deleteProduct(@RequestParam("productIdDeleted") int productIdDeleted, Model model) {
		try {
			int rowsDeleted = productsRepository.deleteProduct(productIdDeleted);
			if (rowsDeleted == 1) {
				model.addAttribute("successMsg", "Product deleted successfully!");
			} else {
				model.addAttribute("errorMsg", "Product with this Id not found! Try again!");
			}
		} catch (Exception e) {
			logger.info("Exception occured at deleteProduct() ManageProductsController" + e.getMessage());
			model.addAttribute("errorMsg",
					"Oops! Something went wrong. Please try again later. Also please ensure no purchase has already been made on the product.");
		}
		return "manageproductsresult";
	}
}
