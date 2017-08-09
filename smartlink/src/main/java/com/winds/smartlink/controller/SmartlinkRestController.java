package com.winds.smartlink.controller;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.winds.smartlink.dtos.GenerateLinkRequest;
import com.winds.smartlink.dtos.GenerateLinkResponse;
import com.winds.smartlink.exceptions.BusinessException;
import com.winds.smartlink.models.SmartlinkUser;
import com.winds.smartlink.models.UserLink;
import com.winds.smartlink.services.SmartlinkService;
import com.winds.smartlink.services.UserLinkService;
import com.winds.smartlink.utils.Constants;
import com.winds.smartlink.utils.JacksonUtils;
import com.winds.smartlink.utils.VelocityService;
import com.winds.smartlink.utils.WebUtils;

@RestController
@RequestMapping("/api")
public class SmartlinkRestController {
	
	@Autowired
	private SmartlinkService smartlinkService;
	
	@Autowired
	private UserLinkService userLinkService;
	
	@RequestMapping(value = "/auto", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public @ResponseBody String generateLink(GenerateLinkRequest input, HttpServletRequest request){
		if(input.getId_conten() != null) {
			return savelinkAndResponse(input, request);
		} else {
			return generateLinkAndResponse(input, request);
		}
	}

	private String savelinkAndResponse(GenerateLinkRequest input, HttpServletRequest request) {
		try {
			//SmartlinkUser userSmartlink = smartlinkService.findSmartlinkUserEmail(URLDecoder.decode(input.getGmail(), "UTF-8"));
			UserLink userLink = userLinkService.getUserLinkByUserAndCode(input.getId_mem(), input.getMahoa());
			
			// Make dynamic link
			makeDynamicLink(request, userLink, URLDecoder.decode(input.getId_conten(), "UTF-8"));
			
			userLink.setChannel(URLDecoder.decode(input.getId_kenh(), "UTF-8"));
			userLink.setMetadata(URLDecoder.decode(input.getId_conten(), "UTF-8"));
			userLink.setEmail(URLDecoder.decode(input.getGmail(), "UTF-8"));
			
			userLinkService.update(userLink);
			
			return "Thanh cong";
		} catch (BusinessException | IOException e) {
			e.printStackTrace();
			return "Khong thanh cong";
		}
	}

	private void makeDynamicLink(HttpServletRequest request, UserLink userLink, String metadata)
			throws IOException {
		String realPath = request.getServletContext().getRealPath("/WEB-INF/pages/dp");
		
		String contextPath = request.getContextPath() + "/webs";
		String dpDir = realPath + WebUtils.getPathAfterContextPatḥ̣(userLink.getLink(), contextPath);
		String fileName = "index.jsp";

		VelocityService vs = VelocityService.getInstance();

		Map<String, String> data = new HashMap<String, String>();
		data.put("metadata", metadata);
		data.put("redirectLink", userLink.getSmartlink());
		
		vs.writeFile("index.vm", dpDir + "/" + fileName, data);
	}

	private String generateLinkAndResponse(GenerateLinkRequest input, HttpServletRequest request) {
		try {
			SmartlinkUser userSmartlink = smartlinkService.findSmartlinkUserEmail(input.getMem());
			
			long code = System.currentTimeMillis();
			
			String link = String.format(Constants.LINK_TEMPLATE, 
					WebUtils.getDomainAndPort(request),
					userSmartlink.getSmartlinkUserId(), 
					code);
			
			UserLink userLink = new UserLink();
			userLink.setUserId(userSmartlink.getUserId());
			userLink.setCode(code);
			userLink.setSmartlink(userSmartlink.getSmartlink().getLink());
			userLink.setLink(link);
			
			userLinkService.save(userLink);
			
			// Response
			GenerateLinkResponse response = new GenerateLinkResponse();
			response.setMahoa(code);
			response.setMem(userSmartlink.getUserId());
			response.setUrl(link);
			
			return JacksonUtils.toJson(response);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "Khong tim thay thanh vien";
	}
}
