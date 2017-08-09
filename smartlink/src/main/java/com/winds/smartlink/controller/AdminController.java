package com.winds.smartlink.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.winds.smartlink.authen.exceptions.UserExistsException;
import com.winds.smartlink.dtos.AddUserInput;
import com.winds.smartlink.exceptions.BusinessException;
import com.winds.smartlink.services.SmartlinkService;

@Controller
public class AdminController {
	
	@Autowired
	private SmartlinkService smartlinkService;
	
	
	@RequestMapping(value = "/json/users", method = RequestMethod.GET)
	public String getUsers() {
		return "users";
	}

	@RequestMapping(value = "/users", method = RequestMethod.GET)
	public String manageUser() {
		return "users";
	}
	
	@RequestMapping(value = "/users", method = RequestMethod.POST)
	public ModelAndView addUser(@ModelAttribute AddUserInput input) {
		ModelMap model = new ModelMap();
		try {
			smartlinkService.addUserAndSmartlink(input);
			
			model.put("success", 1); // thanh cong
		} catch (BusinessException e) {
			model.put("success", -1); // Loi khong xac dinh
			e.printStackTrace();
		} catch (UserExistsException e) {
			model.put("success", -2); // Loi ton tai user
			e.printStackTrace();
		}
		
		return new ModelAndView("users", model);
	}
}
