package four.com.bbs.cate.web;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springmodules.validation.commons.DefaultBeanValidator;

import com.google.gson.Gson;

import egovframework.com.cmm.LoginVO;
import egovframework.rte.fdl.property.EgovPropertyService;
//공통코드에 관한 코드
import four.com.bbs.cate.service.BbsCateService;
import four.com.bbs.cate.service.BbsCateVO;
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
	public class BbsCateController {
		
		 
		 

	    @Resource(name = "BbsCateService")
	    private BbsCateService bbsCateService;

	    @Resource(name = "propertiesService")
	    protected EgovPropertyService propertyService;
	    


	    @Autowired
	    private DefaultBeanValidator beanValidator;

	    //Logger log = Logger.getLogger(this.getClass());
	    
	    /**
	     * 신규 카테고리 정보를 등록한다.
	     * @param write_auth 
	     * 
	     * @param BbsCateVO
	     * @return
	     * @throws Exception
	     */
	    @RequestMapping("/mng/bbs/cate/insertBbsCate.do")
	    @ResponseBody
	    public String insertBbsCate(
	    		@ModelAttribute("searchVO") BbsCateVO bbsCateVO, HttpServletRequest request,
	    		HttpServletResponse response) throws Exception {
			// 결과 상태값 셋팅
			HashMap result = new HashMap();
			
			LoginVO user = new LoginVO();
			
	        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	        if(auth.getDetails() instanceof tem4UserDetails) {
	        	tem4UserDetails detail = (tem4UserDetails) auth.getDetails();
	        	user = detail.getLoginInfo();
	        }			
			try {
				request.setAttribute("userId", user.getId());
				bbsCateService.insertBbsCate(bbsCateVO,request);
				
				result.put("result_msg", "Success");
				result.put("result_cd", "200");

			} catch (Exception e) {
				result.put("result_cd", "500");
				result.put("result_msg", e.getMessage());
			} finally {
				return new Gson().toJson(result);
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
	  
	    
	    // 게시판 리스트 보여주는 곳 ^_^
	    @RequestMapping(value="/mng/bbs/cate/BbsCateListPopup.do")
	    public String selectBBSCateList(
	    		@ModelAttribute("bbsCateVO") BbsCateVO bbsCateVO, HttpServletRequest request,
	    		ModelMap model) throws Exception {
		
	    	int bbs_id = bbsCateVO.getBbsId();
	    	
	    	
	    	List<BbsCateVO> ResultList = bbsCateService.selectBbsCateList(bbsCateVO);
	        	
	    	model.addAttribute("bbsId", bbs_id);
	    	model.addAttribute("cateResultList", ResultList);
			
			return "mng/bbs/cate/BbsCateDetail";
	    }

	    /**
	     * 카테고리 정보를 수정한다.
	     * @param write_auth 
	     * 
	     * @param BbsCateVO
	     * @return
	     * @throws Exception
	     */
	    @RequestMapping("/mng/bbs/cate/updateBbsCate.do")
	    @ResponseBody
	    public String updateBbsCate(
	    		@ModelAttribute("bbsCateVO") BbsCateVO bbsCateVO,HttpServletRequest request,
	    		HttpServletResponse response) throws Exception {
			// 결과 상태값 셋팅
			HashMap result = new HashMap();
			LoginVO user = new LoginVO();
			
	        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	        if(auth.getDetails() instanceof tem4UserDetails) {
	        	tem4UserDetails detail = (tem4UserDetails) auth.getDetails();
	        	user = detail.getLoginInfo();
	        }		
			try {
				
				request.setAttribute("userId", user.getId());
				bbsCateService.updateBbsCate(bbsCateVO, request);
				
				result.put("result_msg", "Success");
				result.put("result_cd", "200");

			} catch (Exception e) {
				result.put("result_cd", "500");
				result.put("result_msg", e.getMessage());
			} finally {
		        return new Gson().toJson(result);
		    }
		}
	    
	    /**
	     * 카테고리 정보를 삭제한다.
	     * 
	     * @param asdaas
	     * @param status
	     * @return
	     * @throws Exception
	     */
	    @RequestMapping("/mng/bbs/cate/deleteBbsCate.do")
	    @ResponseBody
	    public String deleteBbsCate(
	    		@ModelAttribute("bbsCateVO") BbsCateVO bbsCateVO,HttpServletRequest request,
	    		HttpServletResponse response) throws Exception {
			// 결과 상태값 셋팅
			HashMap result = new HashMap();
			LoginVO user = new LoginVO();
			
	        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	        if(auth.getDetails() instanceof tem4UserDetails) {
	        	tem4UserDetails detail = (tem4UserDetails) auth.getDetails();
	        	user = detail.getLoginInfo();
	        }		
			try {
				/*Boolean isAuthenticated = UserDetailsHelper.isAuthenticated();
		    	if(!isAuthenticated) {
		    		model.addAttribute("resultMsg", "fail.common.login");
		        	return "mediacenter/usr/uia/LoginUsr";
		    	}
		    	LoginVO user = (LoginVO)UserDetailsHelper.getAuthenticatedUser();
		    	if(user.getRole().equals("ROLE_ADMIN")){
		    		bbsCateService.deleteBbsCate(bbsCateVO.getBbscate_id());
		    	}else{
		    		model.addAttribute("resultMsg", "not.auth.use");
		    	}*/
				request.setAttribute("userId", user.getId());
				bbsCateService.deleteBbsCate(bbsCateVO.getBbscateId(), request);
			
				
				result.put("result_msg", "Success");
				result.put("result_cd", "200");

			} catch (Exception e) {
				result.put("result_cd", "500");
				result.put("result_msg", e.getMessage());
			} finally {
				return new Gson().toJson(result);
				
		    }
		}
	}
