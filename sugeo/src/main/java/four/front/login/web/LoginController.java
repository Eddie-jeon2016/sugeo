package four.front.login.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {
	
	private Logger logger = LoggerFactory.getLogger(LoginController.class);
	
	@RequestMapping("/front/login.do")
	public String login(ModelMap model)throws Exception{
		logger.info("LOGIN 입장");
		return "front/login/login";
	}

	
	
	@RequestMapping("/front/loginAction.do")
	public String loginAction(ModelMap model)throws Exception{
		logger.info("LOGIN 입장");
		return "front/login/loginAction";
	}
}
