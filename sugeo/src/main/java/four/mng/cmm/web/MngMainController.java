/** ===============================================
 * Copyright(c) 4DEPTH 2017
 * @fileName MngMainController.java
 * @author Chang-il Jeon
 * @since 2017. 2. 1.
 * @version 1.0
 * 
 *=================================================
 */
package four.mng.cmm.web;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.util.UrlPathHelper;

import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.LoginVO;
import egovframework.rte.fdl.security.userdetails.util.EgovUserDetailsHelper;
import four.com.bbs.service.BbsManageService;
import four.com.bbs.service.BbsManageVO;
import four.common.util.security.tem4UserDetails;

@Controller
public class MngMainController {
	
	/** EgovMessageSource */
	@Resource(name = "egovMessageSource")
	EgovMessageSource egovMessageSource;


	@RequestMapping("/mng/main.do")
	public String MngMain(Model model){
	    Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
        if(!isAuthenticated) {
        model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
    return "egovframework/com/uat/uia/EgovLoginUsr";
    }
		
		return "mng/main";
	}
	
		@Resource(name = "BbsManageService")
	    private BbsManageService bbsManageService;
		
	   @RequestMapping(value = "mng/", method = RequestMethod.GET)
	    public String root() {
	        return "mng/index";
	    }
	   
	   @RequestMapping("/mng/left.do")
	    public String mngLeft(ModelMap model, HttpServletRequest request)throws Exception{
	        BbsManageVO vo = new BbsManageVO();
	        LoginVO loginVO = new LoginVO();

	        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	        if(auth.getDetails() instanceof tem4UserDetails) {
	            tem4UserDetails detail = (tem4UserDetails) auth.getDetails();
	            loginVO = detail.getLoginInfo();
	        }

	        String url = "";
	        String queryString = "";
	        UrlPathHelper urls = new UrlPathHelper();
	        url = urls.getOriginatingServletPath(request);
	        queryString =  urls.getOriginatingQueryString(request);

	        if(queryString != "" && queryString != null){
	        url+= "?"+queryString;
	        }

	        List<BbsManageVO> resultList = bbsManageService.selectBbsManageList(vo, request);
	        model.addAttribute("resultList", resultList);
	        model.addAttribute("LoginVO",loginVO);
	        model.addAttribute("reqUrl",url);
	        return "mng/include/left";
	    }   
	   
	   
}

