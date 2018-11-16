/** ===============================================
 * Copyright(c) 4DEPTH 2017
 * @fileName FrontSubController.java
 * @author Chang-il Jeon
 * @since 2017. 3. 7.
 * @version 1.0
 * 
 *=================================================
 */
package four.front.cmm.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class FrontSubController {

	
	
	@RequestMapping("/front/sub/location.do")
	public String locations(Model model){
		return "front/hard/location";
	}
	
}

