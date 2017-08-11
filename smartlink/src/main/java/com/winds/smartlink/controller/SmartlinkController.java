package com.winds.smartlink.controller;


import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.winds.smartlink.authen.model.User;
import com.winds.smartlink.authen.service.UserService;
import com.winds.smartlink.dtos.SearchSmartlinkTracker;
import com.winds.smartlink.exceptions.BusinessException;
import com.winds.smartlink.models.SmartlinkTracker;
import com.winds.smartlink.models.UserLink;
import com.winds.smartlink.services.SmartlinkTrackerService;
import com.winds.smartlink.services.UserLinkService;
import com.winds.smartlink.utils.Constants;
import com.winds.smartlink.utils.JacksonUtils;
import com.winds.smartlink.utils.VelocityService;
import com.winds.smartlink.utils.WebUtils;

@Controller
//@RequestMapping("/mvc")
public class SmartlinkController {
	@Autowired
	private SmartlinkTrackerService smartlinkTrackerService;
	
	@Autowired
	private UserLinkService userLinkService;
	
	@Autowired
	private UserService userService;

	@RequestMapping(value = "/error", method = RequestMethod.GET)
	public String error(HttpServletRequest request) {
		return "404";
	}
	
	@RequestMapping(value = "/links", method = RequestMethod.GET)
	public ModelAndView loginPage(HttpServletRequest request) {
		ModelMap model = new ModelMap();
		
		User user = getCurrentUser();
		
		String link = String.format(Constants.LINK_TEMPLATE, 
				WebUtils.getDomainAndPort(request),
				user.getUserId(), 
				System.currentTimeMillis());
		
		model.put("link", link);
		
		return new ModelAndView("smartlink", model);
	}
	
	@RequestMapping(value = "/links", method = RequestMethod.POST)
	public ModelAndView createLink(@ModelAttribute("userLink") UserLink userLink, HttpServletRequest request) {
		ModelMap model = new ModelMap();
		try {
			User user = getCurrentUser();
			
			userLink.setUserId(user.getUserId());
			userLinkService.save(userLink);
			
			makeDynamicLink(request, userLink.getLink(), userLink.getMetadata());
			
			model.put("link", userLink.getLink());
			model.put("success", 1);

		} catch (BusinessException | IOException e) {
			model.put("success", -1);
			e.printStackTrace();
		}
		
		return new ModelAndView("smartlink", model);
	}
	
	@RequestMapping(value = "/tracker", method = RequestMethod.GET)
	public ModelAndView tracker() {
		ModelMap model = new ModelMap();

		return new ModelAndView("tracker", model);
	}
	
	@RequestMapping(value = "/trackers", method = RequestMethod.GET)
	@ResponseBody
	public String getListTracker() {
		SearchSmartlinkTracker input = new SearchSmartlinkTracker();
		input.setUserId(getCurrentUser().getUserId());
		
		try {
			List<SmartlinkTracker> trackers = smartlinkTrackerService.search(input);
			
			return JacksonUtils.toJson(trackers);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "{error: true}";
	}

	private User getCurrentUser() {
		try {
			return userService.findByUsername(getPrincipal());
		} catch (BusinessException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	private void makeDynamicLink(HttpServletRequest request, String generateLink, String metadata)
			throws IOException {
		String realPath = request.getServletContext().getRealPath("/WEB-INF/pages/dp");
		
		String contextPath = request.getContextPath() + "/webs";
		String dpDir = realPath + WebUtils.getPathAfterContextPatḥ̣(generateLink, contextPath);
		String fileName = "index.jsp";

		VelocityService vs = VelocityService.getInstance();

		Map<String, String> data = new HashMap<String, String>();
		data.put("metadata", metadata);
		data.put("redirectLink", "${redirectLink}");
		
		vs.writeFile("index.vm", dpDir + "/" + fileName, data);
	}

	private String getPrincipal() {
		String userName = null;
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		if (principal instanceof UserDetails) {
			userName = ((UserDetails) principal).getUsername();
		} else {
			userName = principal.toString();
		}
		return userName;
	}

}
