package four.common.util.security;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;
import org.springframework.web.util.UrlPathHelper;

import egovframework.com.cmm.LoginVO;
import egovframework.com.uat.uia.service.EgovLoginService;
import egovframework.com.uat.uia.web.EgovLoginController;
import egovframework.rte.fdl.security.userdetails.util.EgovUserDetailsHelper;

/**
 * Created by Eddie Jeon on 2016-11-08.
 */
public class tem4AuthenticationHandler extends SimpleUrlLogoutSuccessHandler implements AuthenticationSuccessHandler, AuthenticationFailureHandler {
    private static final Logger logger = LoggerFactory.getLogger(EgovLoginController.class);

    /** EgovLoginService */
	@Resource(name = "loginService")
	private EgovLoginService loginService;
    
    //인증이 성공한 경우
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
		System.out.println("onAuthenticationSuccess");
		
        String user_id = authentication.getPrincipal().toString();
        String ip = request.getHeader("X-FORWARDED-FOR");
        if (ip == null || ip.length() == 0) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0) {
            ip = request.getHeader("WL-Proxy-Client-IP"); // 웹로직
        }
        if (ip == null || ip.length() == 0) {
            ip = request.getRemoteAddr() ;
        }

//        System.out.println("로그인한 사용자 정보 user_id["+user_id+"]ip["+ip+"]");
        LoginVO logVO = new LoginVO();
        try{
        	tem4UserDetails detail = (tem4UserDetails) authentication.getDetails();
        	logVO = detail.getLoginInfo();
        	logVO.setIp(ip);
        	loginService.insertLoginLog(logVO);
        	
        }catch(Exception e){
        	e.getStackTrace();
        }
        
        
        long time = System.currentTimeMillis();
        SimpleDateFormat dayTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String str = dayTime.format(new Date(time));

        System.out.println("#############"+str);
        HttpSession session = request.getSession(true);

        session.setAttribute("logTime",str);

        response.sendRedirect("/mng/main.do");
	}
	
    //인증이 실패한 경우
	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
		System.out.println("onAuthenticationFailure");
				
		String message = exception.getMessage();
		String messageArr[] = message.split("\\|\\|");
        String ip = request.getHeader("X-FORWARDED-FOR");
        if (ip == null || ip.length() == 0) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0) {
            ip = request.getHeader("WL-Proxy-Client-IP"); // 웹로직
        }
        if (ip == null || ip.length() == 0) {
            ip = request.getRemoteAddr() ;
        }

        System.out.println("MESSAGE["+message+"]"+messageArr[0]);
        System.out.println("로그인 실패한 사용자 정보 ip["+ip+"]");        
        
        LoginVO logvo = new LoginVO();

        logvo.setId(messageArr[1]);
        
//        AccessLogVO accessLogVO = new AccessLogVO();       
//        if(messageArr.length > 1 && "ERROR0001".equals(messageArr[0])) {
//        	accessLogVO.setUserId(messageArr[1]);
//        }
//        accessLogVO.setIp(ip);
//        accessLogVO.setSuccessYn("N");
//        sm.accessLogInsert(accessLogVO);

        UrlPathHelper urlPathHelper = new UrlPathHelper();
		String contextPath = urlPathHelper.getContextPath(request);
        
		/*response.sendRedirect("/uat/uia/egovLoginUsr.do");*/
        /*response.sendRedirect(contextPath+"/uat/uia/egovLoginUsr.do");*/
        response.sendRedirect("/uat/uia/egovLoginUsr.do?message="+messageArr[0]);
	}
	
	@Override
	public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
		System.out.println("onLogoutSuccess");
		
		response.sendRedirect("/uat/uia/egovLoginUsr.do");
	}
}
