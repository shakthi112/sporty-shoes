package com.sportyshoes.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class RedirectController {

	@GetMapping("/redirectWithRedirectView")
    public RedirectView redirectWithUsingRedirectView() {
        return new RedirectView("searchusers.html");
    }
}
