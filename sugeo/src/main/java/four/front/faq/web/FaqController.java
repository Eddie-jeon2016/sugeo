package four.front.faq.web;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.UrlPathHelper;

import egovframework.com.cmm.ComDefaultVO;
import egovframework.com.cmm.LoginVO;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import four.common.util.security.tem4UserDetails;
import four.front.faq.service.FaqService;
import four.front.faq.service.FaqVO;

@Controller
public class FaqController {
	
	@Resource(name = "faqService")
	private FaqService faqService;
	
	@RequestMapping(value="/front/faq/faqList.do")
	public String selectFaqList(@ModelAttribute ComDefaultVO comVO, Model model) throws Exception {
		
		// faq 서브코드를 불러온다.
		String faqSubcode = "A0004";
		
		PaginationInfo paginationInfo = new PaginationInfo();
	    System.out.println(comVO);
		paginationInfo.setCurrentPageNo(comVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(comVO.getPageUnit());
		paginationInfo.setPageSize(comVO.getPageSize());
		
		comVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		comVO.setLastIndex(paginationInfo.getLastRecordIndex());
		comVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
		
		List<FaqVO> faqList = faqService.selectFaqList(comVO);
		
		//줄바꿈
		for(int i = 0; i < faqList.size(); i++) {
			faqList.get(i).setAnswer(faqList.get(i).getAnswer().replaceAll("\r\n","<br>"));
		}
			
		int totCnt = faqService.selectFaqListCnt(comVO);
		paginationInfo.setTotalRecordCount(totCnt);
		
		model.addAttribute("comVO", comVO);
		model.addAttribute("totcnt", totCnt);
        model.addAttribute("paginationInfo", paginationInfo);
		model.addAttribute("faqList", faqList);
        
		return "front/faq/faqList";
	}
	
	
	//////////////////////////////관리자 페이지
	//faq 리스트 불러오기
	@RequestMapping({"/mng/bbs/faqMng/faqMngList.do"})
	public String selectFaqMngList(Model model,
			@ModelAttribute("searchVO")FaqVO searchVO,
			HttpServletRequest request) throws Exception{
		String returnUrl = "";
		
		PaginationInfo paginationInfo = new PaginationInfo();

		paginationInfo.setCurrentPageNo(searchVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(searchVO.getPageUnit());
		paginationInfo.setPageSize(searchVO.getPageSize());

		searchVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		searchVO.setLastIndex(paginationInfo.getLastRecordIndex());
		searchVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		List<FaqVO> resultList = faqService.selectFaqMngList(searchVO);

		int totCnt = faqService.selectFaqMngListCnt(searchVO); // 총 글 갯수 구함

		paginationInfo.setTotalRecordCount(totCnt);

		model.addAttribute("resultList", resultList);
		model.addAttribute("paginationInfo", paginationInfo);

		UrlPathHelper urlPath = new UrlPathHelper();
		String url = urlPath.getOriginatingServletPath(request);

		if ("/mng/bbs/faqMng/faqMngList.do".equals(url)) {
			returnUrl = "/mng/bbs/faqMng/FaqMngList";
		}
		return returnUrl;	
	}
	
	//faq 등록페이지로 이동
	@RequestMapping({"/mng/bbs/faqMng/faqMngInsertView.do"})
	public String insertFaqMngView(Model model) throws Exception{
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
			return "/mng/bbs/faqMng/FaqMngInsertView";
		}
	}
	
	//faq 등록
	@RequestMapping({"/mng/bbs/faqMng/insertFaqMng.do"})
	public String insertFaqMng(FaqVO faqVO, HttpServletRequest request)throws Exception{
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
	    	
	    	faqVO.setRegId(user.getId());
	    	
	    	System.out.println(faqVO);
	    	faqService.insertFaqMng(faqVO, request);
	    	
	    	return "redirect:/mng/bbs/faqMng/faqMngList.do";
	    	
	    }
	}
	
	//faq 수정 페이지로 이동
	@RequestMapping({"/mng/bbs/faqMng/FaqMngUpdateView.do"})
	public String updateFaqMngView(Model model,
			@ModelAttribute("searchVO")FaqVO searchVO,
			@RequestParam("select_faqId") String faqId)throws Exception{
		String cmd = "update";
		LoginVO user = new LoginVO();

		System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@faqId"+faqId);
		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		if (auth.getDetails() instanceof tem4UserDetails) {
			tem4UserDetails detail = (tem4UserDetails) auth.getDetails();
			user = detail.getLoginInfo();
		}

		if (user == null) {
			return "forward:/usr/uia/LoginUsr.do";
		}else{
			System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!");
			System.out.println(user.getId());
			System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!");
			
			FaqVO resultList = faqService.selectFaqMngOneList(faqId);
			
			System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@faqId"+resultList);
			model.addAttribute("resultList", resultList);
			model.addAttribute("cmd", cmd);
			return "/mng/bbs/faqMng/FaqMngInsertView";
			
		}
	}
	
	//faq 수정
	@RequestMapping({"/mng/bbs/faqMng/updateFaqMng.do"})
	public String updateFaqMng(FaqVO faqVO, Model model,
			HttpServletRequest request) throws Exception{
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
			faqVO.setModId(user.getId());
			
			faqService.updateFaqmng(faqVO, request);
			
			return "redirect:/mng/bbs/faqMng/faqMngList.do?pageIndex="+faqVO.getPageIndex();
			
		}
	}
	
	//faq 글 삭제
	@RequestMapping({"/mng/bbs/faqMng/deleteFaqMng.do"})
	public String deleteFaqMng(FaqVO faqVO, HttpServletRequest request) throws Exception{
		System.out.println(faqVO.getfaqId());
		System.out.println(faqVO);
		
		//로그인 확인
		LoginVO user = new LoginVO();
		
		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		if (auth.getDetails() instanceof tem4UserDetails) {
			tem4UserDetails detail = (tem4UserDetails) auth.getDetails();
			user = detail.getLoginInfo();
		}
		if(user == null) {
//		    		model.addAttribute("resultMsg", "fail.common.login");
			return "forward:/usr/uia/LoginUsr.do";
		}else{
			request.setAttribute("userId", user.getId());
			faqService.deleteFaqMng(faqVO.getfaqId(), request);
			return "redirect:/mng/bbs/faqMng/faqMngList.do";
		
		}
	}
}
