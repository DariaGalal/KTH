package com.networkprogramming.hw4.controller;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import com.networkprogramming.hw4.model.Convert;

@Controller
public class PageController {
	
	@Autowired
	private JdbcTemplate jdbc;
   
   @GetMapping("/")
   public String convertForm(Model model) {
     model.addAttribute("convert", new Convert());
     return "index";
   }
   
   @Transactional
   @PostMapping("/")
   public String convertSubmit(@Valid @ModelAttribute Convert convert, BindingResult br) {
	 if(br.hasErrors()) {
		 convert = null;
		 return "index";
	 }
	 String query = "SELECT rate from exchange where `fromCurrency` = ? and `toCurrency` = ?";
	 Double rate = jdbc.queryForObject(query, Double.class, convert.getFrom(), convert.getTo());
	 convert.doConvert(rate);
     return "index";
   }

}