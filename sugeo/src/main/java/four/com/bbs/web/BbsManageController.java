package four.com.bbs.web;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springmodules.validation.commons.DefaultBeanValidator;

import com.google.gson.Gson;

import egovframework.com.cmm.AuthManageVO;
import egovframework.com.cmm.ComDefaultCodeVO;
import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.service.AuthorManageService;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import four.com.bbs.service.BbsDefaultVO;
import four.com.bbs.service.BbsManageSearchVO;
import four.com.bbs.service.BbsManageService;
import four.com.bbs.service.BbsManageVO;
// 코드값 VO 임
//공통코드에 관한 코드
import four.common.util.security.tem4UserDetails;

/**
 * 게시판 속성관리를 위한 컨트롤러  클래스
 * @author 조은태
 * @since 2013.08.21
 * @version 1.0
 * @see
 *
 * <pre>
 *    수정일    	  수정자               수정내용
 *  ----------      	 ---------      ---------------------------
 *  13.08.21   	  조은태 		      최초 생성
 * </pre> 
 */

@Controller
public class BbsManageController {

    @Resource(name = "BbsManageService")
    private BbsManageService bbsManageService;

    @Resource(name = "propertiesService")
    protected EgovPropertyService propertyService;
    
    @Resource(name = "AuthorManageService")
    private AuthorManageService authorManageService;
    
    @Autowired
    private DefaultBeanValidator beanValidator;

    //Logger log = Logger.getLogger(this.getClass());
    
    /**
     * 신규 게시판 마스터 등록을 위한 등록페이지로 이동한다.
     * 
     * @param BbsManageVO 게시판관리
     * @param BbsCommentDefaultVO 검색조건
     * @param model
     * @return
     * @throws Exception
     */
   @RequestMapping("/mng/bbs/BbsMngInsertViewPopup.do") /** 등록버튼 클릭 시? */
    public String insertManageView(
//    		@ModelAttribute("searchVO") BbsDefaultVO searchVO,
    		Model model) throws Exception {
    	
	   	List<AuthManageVO> authList = authorManageService.selectAuthList();
	   	model.addAttribute("authList", authList);
    	 
    	 
    	/** 게시물 등록 페이지를 보여주면 되는것 ? */
    	return "mng/bbs/insertBbsManage";
    }
    

   @RequestMapping("/mng/bbs/insertBbsManage.do")
    public String insertBbsManage(
            @ModelAttribute("bbsManageVO") BbsManageVO bbsManageVO, 
            BindingResult bindingResult,
            Model model, HttpServletRequest request
            )throws Exception {
	   
	   	LoginVO user = new LoginVO();
		
	    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    if(auth.getDetails() instanceof tem4UserDetails) {
	    	tem4UserDetails detail = (tem4UserDetails) auth.getDetails();
	    	user = detail.getLoginInfo();
	    }	
		if(user == null) {
			model.addAttribute("resultMsg", "fail.common.login");
		return "/usr/uia/LoginUsr";
		}
	
		if(user.getRole().equals("ROLE_ADMIN")){
			beanValidator.validate(bbsManageVO, bindingResult);
			if (bindingResult.hasErrors()){
				Map map = bindingResult.getModel();
				Set keys = map.keySet();
				Iterator it = keys.iterator();
				while(it.hasNext()) {
					Object key = it.next();
					Object val = map.get(key);
				}
				return "mng/bbs/BBSMngInsertView.do";
			}else{//Exception 없이 진행시 등록 성공메시지
				
				bbsManageService.insertBbsManage(bbsManageVO,request);
				model.addAttribute("resultMsg", "success.common.insert");
				return "forward:/mng/bbs/BbsProcess.do";
			}
		}else{
			model.addAttribute("resultMsg", "not.auth.use");
			return "forward:/mng/bbs/BbsProcess.do";
		}
	}

	/**
     * 게시판 마스터 목록을 조회한다.
     * 
     * @param boardMasterVO
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/mng/bbs/BbsManageList.do")
    public String selectBBSManageList(
    		@ModelAttribute("bbsManageVO") BbsManageVO bbsManageVO,
    		@ModelAttribute("searchVO") BbsDefaultVO searchVO,
    		ModelMap model, HttpServletRequest request) throws Exception {

    	LoginVO user = new LoginVO();
    	
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if(auth.getDetails() instanceof tem4UserDetails) {
        	tem4UserDetails detail = (tem4UserDetails) auth.getDetails();
        	user = detail.getLoginInfo();
        }	
		if(user == null) {
			model.addAttribute("resultMsg", "fail.common.login");
		return "/usr/uia/LoginUsr";
		}
		PaginationInfo paginationInfo = new PaginationInfo();
		BbsManageVO vo = new BbsManageVO();
		paginationInfo.setCurrentPageNo(searchVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(searchVO.getPageUnit());
		paginationInfo.setPageSize(searchVO.getPageSize());

		searchVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		searchVO.setLastIndex(paginationInfo.getLastRecordIndex());
		searchVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());		
		
		List<BbsManageVO> resultList = bbsManageService.selectBbsManageList(bbsManageVO,request);
		
		
		model.addAttribute("resultList",resultList);
		
		int totCnt = bbsManageService.selectBbsManageListCnt(bbsManageVO); //총 글수 개수구함
		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("paginationInfo", paginationInfo);
	        
		return "mng/bbs/BbsManageList";
    }
    
    /**
     * 게시판 마스터 상세내용을 조회한다.
     * 수정페이지로 이동한다.
     * 
     * @param boardMasterVO
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping("/mng/bbs/BbsManageDetailPopup.do")
    public String updateBoardMaster(@ModelAttribute("bbsManageVO") BbsManageVO bbsManageVO,
    	    BindingResult bindingResult, ModelMap model, HttpServletRequest request) throws Exception {
			
    	BbsManageVO bbsVO = bbsManageService.selectBbsManage(bbsManageVO);
    	String provideUrl;
    	ComDefaultCodeVO vo = new ComDefaultCodeVO();
    	
    	provideUrl = "/mng/bbs/BbsManageList.do?bbsId=" + bbsVO.getBbsId();

    	//공간종류부모코드를 코드정보로부터 조회
//    	vo.setPcode("COM1000");
//    	List<CmmnDetailCode> pCode_result = cmmUseService.selectCmmCodeDetail(vo);
//    	
//    	AuthDefaultVO aVO = new AuthDefaultVO();
//    	aVO.setSearchUseYn("Y");
    	List<AuthManageVO> authList = authorManageService.selectAuthList();
    	model.addAttribute("authList", authList);
    	
    	model.addAttribute("updateResult", bbsVO);
    	model.addAttribute("provdUrl", provideUrl);
//    	model.addAttribute("pCode_result", 	pCode_result);
    	
    	return "mng/bbs/BbsManageDetail";
    }
    
    /**
     * 게시판 마스터 정보를 수정 후 리스트로 이동
     * 
     * 
     * @param BbsManageVO
     * @param boardMaster
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping("/mng/bbs/updateBbsManage.do")
    public String updateBbsManage(
    		@ModelAttribute("bbsManageVO") BbsManageVO bbsManageVO,
    		@ModelAttribute("searchVO") BbsDefaultVO searchVO,
	    BindingResult bindingResult, ModelMap model,HttpServletRequest request) throws Exception {
    	
    	LoginVO user = new LoginVO();
    	
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if(auth.getDetails() instanceof tem4UserDetails) {
        	tem4UserDetails detail = (tem4UserDetails) auth.getDetails();
        	user = detail.getLoginInfo();
        }	
		if(user == null) {
			model.addAttribute("resultMsg", "fail.common.login");
			return "/usr/uia/LoginUsr";
		}
		if(user.getRole().equals("ROLE_ADMIN")){
    		beanValidator.validate(bbsManageVO, bindingResult);
			if (bindingResult.hasErrors()) {
				if(bbsManageVO.getMenuNo().equals("")){
					bbsManageVO.setMenuNo(null);
				}
				bbsManageVO.setregId(Integer.parseInt(user.getId()));
				BbsManageSearchVO vo = (BbsManageSearchVO) bbsManageService.selectBbsManage(bbsManageVO);
				model.addAttribute("result", vo);
				return "forward:/mng/bbs/BbsProcess.do";
			}else{
			    bbsManageService.updateBbsManage(bbsManageVO, request);
			    return "forward:/mng/bbs/BbsProcess.do";
		    }
    	}else{
    		model.addAttribute("resultMsg", "not.auth.use");
    		return "forward:/mng/bbs/BbsProcess.do";
    	}
    }

    /**
     * 게시판 마스터 정보를 삭제한다.
     * 
     * @param BbsManageVO
     * @param status
     * @return
     * @throws Exception
     */
    @RequestMapping("/mng/bbs/DelManage.do")
    @ResponseBody    
    public String deleteBbsManage(
    		@RequestParam("bbsId") int bbs_id,
		    ModelMap model, HttpServletRequest request)
            throws Exception {
    	HashMap result = new HashMap();
    	LoginVO user = new LoginVO();
    	
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if(auth.getDetails() instanceof tem4UserDetails) {
        	tem4UserDetails detail = (tem4UserDetails) auth.getDetails();
        	user = detail.getLoginInfo();
        }	
		if(user == null) {
			model.addAttribute("resultMsg", "fail.common.login");
		return "/usr/uia/LoginUsr";
		}
		
		try{
			request.setAttribute("userId", user.getId());
    		bbsManageService.deleteBbsManage(bbs_id, request);
			
			result.put("result_msg", "Success");
			result.put("result_cd", "200");

		} catch (Exception e) {
			result.put("result_cd", "500");
			result.put("result_msg", e.getMessage());
		}
//    	if(user.getRole().equals("ROLE_ADMIN")){

//    	}else{
//    		model.addAttribute("resultMsg", "not.auth.use");
//    	}
       return new Gson().toJson(result);
    }
    
    @RequestMapping("/mng/bbs/BbsProcess.do")
    public String processDone(ModelMap model){
    	return "/mng/bbs/BbsManage";
    }
}
