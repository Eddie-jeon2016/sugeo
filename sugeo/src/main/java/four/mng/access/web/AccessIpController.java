package four.mng.access.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AccessIpController {
	
	@RequestMapping(value = "/access/accessDeny.do")
	public String accessDeny() throws Exception {
		
		return "mng/access/accessDeny";
	}
}
