package com.yum.license.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yum.license.util.License;
import com.yum.license.util.LicenseManager;
import com.yum.license.util.LicenseTool;

/**
 * 策略模板表
 * 
 * @author Yum
 * @email wtuada@126.com
 * @date 2018-07-07 14:05:30
 */

@Controller
@RequestMapping("/license")
public class LicenseController {

	@GetMapping("")
	String index() {
		return "license/index";
	}

	@ResponseBody
	@PostMapping("/generate")
	Object generate(@RequestParam Map<String, Object> params,Model model,final HttpServletRequest request, final HttpServletResponse response) {

		System.out.println(params.toString());
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		response.setCharacterEncoding("UTF-8");
		System.out.println("Date:" + dateFormat.format(date));
		
		try {
            new LicenseTool().createLicense(params);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
		

		return true;
	}
	
	@ResponseBody
	@GetMapping("/verifier")
	Object verifier(@RequestParam Map<String, Object> params,Model model,final HttpServletRequest request, final HttpServletResponse response) {
		boolean valid = false;
		 LicenseManager licenseManager = LicenseManager.getInstance();
	        try {
	            License license = licenseManager.getLicense();
	            System.out.println("license = " + license);
	            valid = licenseManager.isValidLicense(license);
	            System.out.println("valid = " + valid);
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
		
		
		return valid;
	}

}
