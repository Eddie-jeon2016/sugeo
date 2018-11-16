package four.com.press.web;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.UrlPathHelper;

import egovframework.com.cmm.LoginVO;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import four.com.bbs.service.BbsManageVO;
import four.common.util.security.tem4UserDetails;
import four.mng.press.sevice.PressContentDefaultVO;
import four.mng.press.sevice.PressContentService;
import four.mng.press.sevice.PressContentVO;

@Controller
public class PressContentController {

	@Resource(name = "PressContentService")
	public PressContentService presscontentservice;

	// 언론보도자료 리스트 불러오기
	@RequestMapping({ "/mng/bbs/pressContent/pressContentList.do", "/front/pressContent/pressContentList.do" })
	public String PressContentList(Model model,
			@ModelAttribute("searchVO") PressContentDefaultVO searchVO,
			HttpServletRequest request) throws Exception {		
		String returnUrl = "";
		
		PaginationInfo paginationInfo = new PaginationInfo();

		paginationInfo.setCurrentPageNo(searchVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(searchVO.getPageUnit());
		paginationInfo.setPageSize(searchVO.getPageSize());

		searchVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		searchVO.setLastIndex(paginationInfo.getLastRecordIndex());
		searchVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		List<PressContentVO> resultList = presscontentservice
				.selectPressContentList(searchVO);

		int totCnt = presscontentservice.selectPressContentListCnt(searchVO); // 총 글 갯수 구함

		paginationInfo.setTotalRecordCount(totCnt);

		model.addAttribute("resultList", resultList);
		model.addAttribute("searchVO", searchVO);
		model.addAttribute("paginationInfo", paginationInfo);

		UrlPathHelper urlPath = new UrlPathHelper();
		String url = urlPath.getOriginatingServletPath(request);

		if ("/mng/bbs/pressContent/pressContentList.do".equals(url)) {
			returnUrl = "/mng/bbs/pressContent/BbsPressContentList";
		}else if("/front/pressContent/pressContentList.do".equals(url)){
			returnUrl = "/front/content/pressContentList";
			
		}
		return returnUrl;
	}

	// 글등록 페이지로 이동
	@RequestMapping({ "/mng/bbs/pressContent/pressContentInsertView.do" })
	public String insertPressContentView(Model model) throws Exception {

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
			
			String cmd = "insert";
			model.addAttribute("cmd", cmd);
			return "/mng/bbs/pressContent/BbsPressContentInsertView";
		}
	}

	// 글수정 페이지로 이동
	@RequestMapping({ "/mng/bbs/pressContent/pressContentUpdateView.do" })
	public String updatePressContentView(Model model,@ModelAttribute("searchVO") PressContentDefaultVO searchVO,
			@RequestParam("select_pcId") String pcId, HttpServletRequest request) throws Exception {
		String cmd = "update";
		LoginVO user = new LoginVO();

		System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@searchVO"+searchVO);
		
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

		PressContentVO resultList = presscontentservice
				.selectPressContentOneList(pcId);

		model.addAttribute("resultList", resultList);
		model.addAttribute("cmd", cmd);
		return "/mng/bbs/pressContent/BbsPressContentInsertView";
	}

	// 글 등록
	@RequestMapping({ "/mng/bbs/pressContent/insertPressContent.do" })
	public String insertPressContent(PressContentVO pressContentVO, Model model, HttpServletRequest request)
			throws Exception {

		LoginVO user = new LoginVO();

		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		if (auth.getDetails() instanceof tem4UserDetails) {
			tem4UserDetails detail = (tem4UserDetails) auth.getDetails();
			user = detail.getLoginInfo();
		}
		if(user == null) {
//    		model.addAttribute("resultMsg", "fail.common.login");
        	return "forward:/usr/uia/LoginUsr.do";
	    }else{
	    	pressContentVO.setRegId(user.getId());
	    	
	    	presscontentservice.insertPressContent(pressContentVO, request);
	    	
	    	return "redirect:/mng/bbs/pressContent/pressContentList.do";
	    	
	    }
	}
	
	// 글 수정
	@RequestMapping({ "/mng/bbs/pressContent/updatePressContent.do" })
	public String updatePressContent(PressContentVO pressContentVO, @ModelAttribute("searchVO") PressContentDefaultVO searchVO,
			Model model, HttpServletRequest request)
			throws Exception {
		
		LoginVO user = new LoginVO();
		
		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		if (auth.getDetails() instanceof tem4UserDetails) {
			tem4UserDetails detail = (tem4UserDetails) auth.getDetails();
			user = detail.getLoginInfo();
		}
		if(user == null) {
//    		model.addAttribute("resultMsg", "fail.common.login");
			return "forward:/usr/uia/LoginUsr.do";
		}else{
			
			pressContentVO.setModId(user.getId());
			
			System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@"+pressContentVO);
			presscontentservice.updatePressContent(pressContentVO, request);;
			
			
			return "redirect:/mng/bbs/pressContent/pressContentList.do?pageIndex="+searchVO.getPageIndex();
			
		}
	}

	// 글 삭제
	@RequestMapping({ "/mng/bbs/pressContent/deletePressContent.do" })
	public String deletePressContent(@RequestParam String params,
			@ModelAttribute("searchVO") PressContentDefaultVO searchVO,
			HttpServletRequest request) throws Exception {
		HashMap<String, Integer> param = new HashMap<String, Integer>();

		//로그인 확인
		LoginVO user = new LoginVO();
		
		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		if (auth.getDetails() instanceof tem4UserDetails) {
			tem4UserDetails detail = (tem4UserDetails) auth.getDetails();
			user = detail.getLoginInfo();
		}
		request.setAttribute("userId", user.getId());
		if(user == null) {
//    		model.addAttribute("resultMsg", "fail.common.login");
			return "forward:/usr/uia/LoginUsr.do";
		}else{
			if (params != null || params != "") {
				String[] params2 = params.toString().split(",");
				for (int i = 0; i < params2.length; i++) {
					String deleteId = params2[i];	
					presscontentservice.deletePressContent(deleteId,request);
				}
			}
			return "redirect:/mng/bbs/pressContent/pressContentList.do";
		}
	}

}
