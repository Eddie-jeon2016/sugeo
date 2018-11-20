package four.com.member.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MemberUserController {


	
	@RequestMapping("/front/join/st.do")
	public String step1(ModelMap model)throws Exception{
		return "front/join/st";
	}
	
	@RequestMapping("/front/join/ee.do")
	public String step2(ModelMap model)throws Exception{
		return "front/join/ee";
	}
	
	@RequestMapping("/front/join/pp.do")
	public String stepProc(ModelMap model)throws Exception{
		return "front/join/pp";
	}
	
	@RequestMapping("/front/join/complete.do")
	public String stepComplete(ModelMap model)throws Exception{
		return "front/join/complete";
	}
	
}
