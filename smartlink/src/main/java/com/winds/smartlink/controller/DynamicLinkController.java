package com.winds.smartlink.controller;


import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.winds.smartlink.dtos.FreegeoIP;
import com.winds.smartlink.dtos.SearchSmartlinkTracker;
import com.winds.smartlink.dtos.SmartlinkCondition;
import com.winds.smartlink.exceptions.BusinessException;
import com.winds.smartlink.models.SmartlinkTracker;
import com.winds.smartlink.models.SmartlinkUser;
import com.winds.smartlink.services.SmartlinkService;
import com.winds.smartlink.services.SmartlinkTrackerService;
import com.winds.smartlink.utils.JacksonUtils;
import com.winds.smartlink.utils.WebUtils;

@Controller
/*@RequestMapping("/webs")*/
public class DynamicLinkController {
	@Autowired
	private SmartlinkTrackerService smartlinkTrackerService;
	
	@Autowired
	private SmartlinkService smartlinkService;
	
	@RequestMapping(value = "/{userId}/{channel}", method = RequestMethod.GET)
	public ModelAndView dynamicLinks(@PathVariable("userId") Long userId, 
								@PathVariable("channel") String channel, 
								HttpServletRequest request) {
		ModelMap model = new ModelMap();
		
		try {
			String json = WebUtils.get("http://freegeoip.net/json/" + request.getRemoteAddr());
			
			FreegeoIP freegeo = JacksonUtils.parseJson(json, FreegeoIP.class);
			
			String countryCode = StringUtils.isEmpty(freegeo.getCountry_code()) ? "local" : freegeo.getCountry_code();
			
			SmartlinkCondition condition = new SmartlinkCondition();
			condition.setUserId(userId);
			condition.setCountryCode(countryCode);
			
			SmartlinkUser smartlink = smartlinkService.autoChooseSmartlink(condition);
			
			createTracker(countryCode, smartlink.getSmartlinkUserId());
			
			String link = smartlink.getSmartlink().getLink();
			System.out.println("\nUser: " + userId + " \nCountry code: " + countryCode + " : \nlink: " + link);
			model.put("redirectLink", link);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return new ModelAndView("dp/" + userId + "/" + channel + "/index", model) ;
	}

	private void createTracker(String countryCode, Long smartlinkUserId) throws BusinessException {
		SearchSmartlinkTracker trackerSearch = new SearchSmartlinkTracker();
		trackerSearch.setCountryCode(countryCode);
		trackerSearch.setTrackerDate(new Date());
		//trackerSearch.setSmartlinkUserId(smartlinkUserId); //3 = smartlinkUserId
		
		SmartlinkTracker smartlinkTracker = smartlinkTrackerService.searchOne(trackerSearch);
		
		if(smartlinkTracker == null) {
			smartlinkTracker = new SmartlinkTracker();
			smartlinkTracker.setCountryCode(countryCode);
			smartlinkTracker.setSmartLinkUserId(smartlinkUserId);
			smartlinkTracker.setVisit(1);
			smartlinkTracker.setClicked(0);
			smartlinkTracker.setTrackerDate(new Date());
			smartlinkTrackerService.save(smartlinkTracker);
		} else {
			smartlinkTracker.setVisit(smartlinkTracker.getVisit() + 1);
			smartlinkTrackerService.update(smartlinkTracker);
		}
	}

}
