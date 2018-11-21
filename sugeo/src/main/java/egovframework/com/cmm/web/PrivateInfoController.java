package egovframework.com.cmm.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PrivateInfoController {

	@RequestMapping(value = "/cmm/fms/privateInfo.do")
	public String selectPrivateInfo() throws Exception {
		return "forward:/front/content/contentViewer.do?contentId=CONTENT_0000261";
	}
}
