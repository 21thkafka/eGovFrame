package egov.login.web;

import java.util.ArrayList;
import java.util.HashMap;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import egov.main.service.MainService;

@Controller
public class LoginController {

	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
	
	//@Resource(name="MainService") MainService mainService;
	
	@RequestMapping(value = "/login.do")
	public String main(HttpServletRequest request,ModelMap model)
	{
		return "login";
	}
	
	@RequestMapping(value = "/usercondition.do")
	public String usercondtion(HttpServletRequest request,ModelMap model)
	{
		return "usercondition";
	}
	
	@RequestMapping(value = "/userenter.do")
	public String userenter(HttpServletRequest request,ModelMap model)
	{
		return "userenter";
	}
	

}
