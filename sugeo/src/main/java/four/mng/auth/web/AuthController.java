package four.mng.auth.web;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.UrlPathHelper;

import com.google.gson.Gson;

import egovframework.com.cmm.ComDefaultVO;
import egovframework.com.cmm.LoginVO;
import egovframework.com.utl.sim.service.EgovFileScrty;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import four.common.util.security.tem4UserDetails;
import four.mng.auth.service.AuthService;
import four.mng.auth.service.AuthVO;

@Controller
public class AuthController {
	
	@Resource(name = "authService")
	private AuthService authService;
	
	 @Resource(name = "egovAuthIdGnrService")
		private EgovIdGnrService idgenService;
	
	 /** EgovPropertyService */
	 @Resource(name = "propertiesService")
	 protected EgovPropertyService propertiesService;
	 
	 
	@RequestMapping(value = "/mng/auth/selectAuthorUserList.do")
	public String selectAthorUserList(ComDefaultVO comVO, Model model, HttpServletRequest request) throws Exception {
		
		PaginationInfo paginationInfo = new PaginationInfo();
	    
		paginationInfo.setCurrentPageNo(comVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(comVO.getPageUnit());
		paginationInfo.setPageSize(comVO.getPageSize());
		
		comVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		comVO.setLastIndex(paginationInfo.getLastRecordIndex());
		comVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
		
		List<AuthVO> resultList = authService.selectAuthorList(comVO);
		
		paginationInfo.setTotalRecordCount(authService.selectAuthorListCnt(comVO));
		
		model.addAttribute("resultList", resultList);
		model.addAttribute("paginationInfo", paginationInfo);
		model.addAttribute("comVO", comVO);
	
		return "mng/auth/authList";
	}
	
	@RequestMapping(value = {"/mng/auth/authorUserRegView.do", "/mng/auth/authorUserUpdateView.do"})
	public String authorUserRegView(HttpServletRequest request, Model model, String uniqId,ComDefaultVO comVO) throws Exception {
		
		//로그인 확인
		LoginVO user = new LoginVO();

		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		if (auth.getDetails() instanceof tem4UserDetails) {
			tem4UserDetails detail = (tem4UserDetails) auth.getDetails();
			user = detail.getLoginInfo();
		}
		if (user == null) {
			return "forward:/usr/uia/LoginUsr.do";
		}

		request.setAttribute("userId", user.getId());
		String uri = request.getRequestURI();
		if(uri.indexOf("authorUserUpdateView") > -1) {
			
			AuthVO result = authService.selectAuthorUser(uniqId);
			model.addAttribute("result", result);
		}
		model.addAttribute("comVO",comVO);
		
		return "mng/auth/authRegView";
	}
	
	@RequestMapping(value = "/mng/auth/insertAuthorUser.do")
	public String insertAuthorUser(AuthVO aVO, HttpServletRequest request) throws Exception {
		
		//로그인 확인
		LoginVO user = new LoginVO();
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();	
		if (auth.getDetails() instanceof tem4UserDetails) {
			tem4UserDetails detail = (tem4UserDetails) auth.getDetails();
			user = detail.getLoginInfo();
		}
		if (user == null) {
			return "forward:/usr/uia/LoginUsr.do";
		}
		request.setAttribute("userId", user.getId());
		//uniqId 생성
		String uniqId = idgenService.getNextStringId();
		aVO.setUniqId(uniqId);
		authService.insertAuthorUser(aVO, request);
		
		return "redirect:/mng/auth/selectAuthorUserList.do";
	}
	
	@RequestMapping(value = "/mng/auth/updateAuthorUserCheck.do")
	@ResponseBody
	public String updateAuthorUserCheck(AuthVO aVO, HttpServletRequest request) throws Exception {
		HashMap<String,Object> result = new HashMap();
		
		try {
			int pswdCheck = authService.updateAuthorCheck(aVO, request);
			System.out.println("pswdCheck : "+pswdCheck);
			if(pswdCheck > 0) {
				
				result.put("status", "success");
			} else {
				
				result.put("status", "fail");
			}
			
		} catch (Exception e) {
			result.put("error", e.getMessage());
		}
		
		return new Gson().toJson(result);
	}
	
	@RequestMapping(value = "/mng/auth/updateAuthorUser.do")
	public String updateAuthorUser(AuthVO aVO,ComDefaultVO comVO, HttpServletRequest request) throws Exception {
		//로그인 확인
		LoginVO user = new LoginVO();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();	
		if (auth.getDetails() instanceof tem4UserDetails) {
			tem4UserDetails detail = (tem4UserDetails) auth.getDetails();
			user = detail.getLoginInfo();
			}
		if (user == null) {
			return "forward:/usr/uia/LoginUsr.do";
		}

		request.setAttribute("userId", user.getId());
		authService.updateAuthorUser(aVO, request);
		
		return "redirect:/mng/auth/selectAuthorUserList.do?pageIndex="+comVO.getPageIndex();
	}
	
	@RequestMapping(value = "/mng/auth/deleteAuthorUser.do")
	public String delete(String uniqId,ComDefaultVO comVO, HttpServletRequest request) throws Exception {
		
		//로그인 확인
				LoginVO user = new LoginVO();

				Authentication auth = SecurityContextHolder.getContext()
						.getAuthentication();
				if (auth.getDetails() instanceof tem4UserDetails) {
					tem4UserDetails detail = (tem4UserDetails) auth.getDetails();
					user = detail.getLoginInfo();
				}
				if (user == null) {
					return "forward:/usr/uia/LoginUsr.do";
				}
				request.setAttribute("userId", user.getId());

		authService.deleteAuthorUser(uniqId, request);
		
		return "redirect:/mng/auth/selectAuthorUserList.do";
	}
	
	@RequestMapping(value = "/mng/auth/selectAccessUserList.do")
	public String selectAccessUserList(ComDefaultVO comVO, Model model,
								HttpServletRequest request, HttpServletResponse response) throws Exception {
		



		PaginationInfo paginationInfo = new PaginationInfo();
	    
		paginationInfo.setCurrentPageNo(comVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(comVO.getPageUnit());
		paginationInfo.setPageSize(comVO.getPageSize());
		
		comVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		comVO.setLastIndex(paginationInfo.getLastRecordIndex());
		comVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		List<AuthVO> resultList = authService.selectAccessUserList(comVO);
		
		paginationInfo.setTotalRecordCount(authService.selectAccessUserListCnt(comVO));
		
		model.addAttribute("resultList", resultList);
		model.addAttribute("paginationInfo", paginationInfo);
		model.addAttribute("comVO", comVO);
		
		return "mng/auth/authAccessList";
	}
	
	@RequestMapping({"/mng/auth/workList/selectWorkLogList.do", "/mng/auth/workList/selectWorkLogListExcel.do"})
	public String selectWorkLogList(@ModelAttribute("comVO")ComDefaultVO comVO, Model model,HttpServletRequest request) throws Exception{
		
		 UrlPathHelper urlPathHelper = new UrlPathHelper();
	     String url = urlPathHelper.getOriginatingServletPath(request);
	     String resultUrl;
	     
	     if(url.equals("/mng/auth/workList/selectWorkLogListExcel.do")){
	    	
	    	 comVO.setPageUnit(9999);
	    	 comVO.setPageSize(propertiesService.getInt("pageSize"));
	    	 resultUrl = "mng/auth/authWorkLogListExcel";
	     }else {
	    	
	    	 resultUrl = "mng/auth/authWorkLogList";
	     }
	     
		PaginationInfo paginationInfo = new PaginationInfo();
	    
		paginationInfo.setCurrentPageNo(comVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(comVO.getPageUnit());
		paginationInfo.setPageSize(comVO.getPageSize());
		
		comVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		comVO.setLastIndex(paginationInfo.getLastRecordIndex());
		comVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		List<AuthVO> resultList = authService.selectWorkLogList(comVO);
		for(int i = 0; i < resultList.size(); i++) {
			String content = resultList.get(i).getContent();
			content = content.replaceAll("<img", "&lt;img");
			resultList.get(i).setContent(content);
		}
		
		paginationInfo.setTotalRecordCount(authService.selectWorkLogListCnt(comVO));
		
		model.addAttribute("resultList", resultList);
		model.addAttribute("paginationInfo", paginationInfo);
		model.addAttribute("comVO", comVO);
		
		
		return resultUrl;
	}
	
	@RequestMapping({"/mng/auth/workList/selectWorkLogOneList.do"})
	public String selectWorkLogOneList(Model model,@ModelAttribute("comVO")ComDefaultVO comVO,HttpServletRequest request,
			@RequestParam("logId") String logId)throws Exception{
		//로그인 확인
		LoginVO user = new LoginVO();

		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		if (auth.getDetails() instanceof tem4UserDetails) {
			tem4UserDetails detail = (tem4UserDetails) auth.getDetails();
			user = detail.getLoginInfo();
		}

		if (user == null) {
			return "forward:/usr/uia/LoginUsr.do";
		}else{
		
			AuthVO authVO = authService.selectWorkLogOneList(logId);
			model.addAttribute("authVO",authVO);
		}

		return "mng/auth/authWorkLogDetailView";
	}
	
	@RequestMapping(value = "/mng/auth/idCheck.do")
	@ResponseBody
	public String idCheck(AuthVO aVO) throws Exception {
	
		System.out.println(aVO.getUserId());
		HashMap<String,Object> result = new HashMap();
		int cnt = authService.idCheck(aVO.getUserId());
		if(cnt == 1) {
			result.put("code", "500");
		}else {
			result.put("code", "200");
		}
		return new Gson().toJson(result);
	}
}
