package egovframework.com.cmm.interceptor;
 
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.ModelAndViewDefiningException;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import four.mng.access.service.AccessIpService;
import four.mng.access.service.AccessIpVO;
 
/**
 * 사용자IP 체크 인터셉터
 * @author 유지보수팀 이기하
 * @since 2013.03.28
 * @version 1.0
 * @see
 *  
 * <pre>
 * << 개정이력(Modification Information) >>
 * 
 *   수정일     수정자          수정내용
 *  ----------  --------    ---------------------------
 *  2013.03.28	이기하          최초 생성 
 *  </pre>
 */

public class IpObtainInterceptor extends HandlerInterceptorAdapter {
	
	@Resource(name = "accessIpService")
	private AccessIpService accessIpService;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
 
		String clientIp = request.getRemoteAddr();
		
		AccessIpVO checkVO = new AccessIpVO();
		checkVO.setAccessIp(clientIp);
		int check = accessIpService.selectAccessIpCheck(checkVO);
		if(check == 0) {
			
			ModelAndView modelAndView = new ModelAndView("redirect:/access/accessDeny.do");
			throw new ModelAndViewDefiningException(modelAndView);
//			response.sendRedirect("");
		}
//		LoginVO loginVO = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
// 
//		if (loginVO != null) {
//			loginVO.setIp(clientIp);
//		}
 
		return true;
	}
}
