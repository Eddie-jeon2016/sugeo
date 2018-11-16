package four.com.bbs.cmt.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springmodules.validation.commons.DefaultBeanValidator;

import egovframework.com.cmm.LoginVO;
import egovframework.rte.fdl.property.EgovPropertyService;
// 코드값 VO 임
//공통코드에 관한 코드
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import four.com.bbs.cmt.service.BbsCommentDefaultVO;
import four.com.bbs.cmt.service.BbsCommentService;
import four.com.bbs.cmt.service.BbsCommentVO;
import four.com.bbs.service.BbsManageService;
import four.com.bbs.service.BbsManageVO;
import four.com.bbs.service.BbsService;
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
	public class BbsCommentController {

		@Resource(name = "bbsService")
	    private BbsService bbsService;
		
		@Resource(name = "bbsCommentService")
	    private BbsCommentService bbsCommentService;
		
		@Resource(name = "BbsManageService")
	    private BbsManageService bbsManageService;
		
	    @Resource(name = "propertiesService")
	    protected EgovPropertyService propertyService;
	    
	    @Autowired
	    private DefaultBeanValidator beanValidator;

	    //Logger log = Logger.getLogger(this.getClass());
	   // 
	    
	 
	    /**
	     * CmtAnswer(상세정보)를 가지고 온다.
	     * @param write_auth 
	     * 
	     * @param asdaas
	     * @param BbsDefaultVO
	     * @param status
	     * @return 
	     * @throws Exception
	     */
	    @RequestMapping(value="/front/bbs/cmt/selectCmtAnswer.do")
	    public void selectCmtAnswer(@RequestParam Map<String, Object> params, HttpServletResponse response
	    							) throws IOException {
	    	
	    	// 결과 상태값 셋팅
			JSONObject result = new JSONObject();
			LoginVO user = new LoginVO();
			
	        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	        if(auth.getDetails() instanceof tem4UserDetails) {
	        	tem4UserDetails detail = (tem4UserDetails) auth.getDetails();
	        	user = detail.getLoginInfo();
	        }		
			try {
				String answer_no = (String) params.get("answer_no");
				
				if ( user == null ) {
		    		throw new Exception("회원전용 서비스입니다.\n로그인후 진행주세요.");
		    	}
					
				// 로그인된 아이디값 bbsVO에 담음.
				BbsCommentDefaultVO searchVO = new BbsCommentDefaultVO();
				
				searchVO.setAnswerNo(Integer.parseInt(answer_no));
				BbsCommentVO result_answer = bbsCommentService.selectCmtAnswer(searchVO);
				
				
				//TODO 값 확인.
				
				result.put("result_answer_no", result_answer.getAnswerNo());
				result.put("result_answer", result_answer.getContent());
	    		result.put("result_cd", "200");
				result.put("result_msg", "Success");

			} catch (Exception e) {
				result.put("result_cd", "500");
				result.put("result_msg", e.getMessage());
			} finally {
	            response.setHeader("Content-Type","text/html; charset=UTF-8");
	        	PrintWriter out = response.getWriter();
	     	    out.write(result.toString());
	     	    out.flush();
	     	    out.close();
	        }
	    }

	    
	    /**
	     * 신규 댓글을 등록한다..
	     * @param write_auth 
	     * 
	     * @param asdaas
	     * @param BbsDefaultVO
	     * @param status
	     * @return /usr/bbs/cmt/selectAnswer.do
	     * @throws Exception
	     */
	    @RequestMapping(value="/front/bbs/cmt/insertBbsComment.do")
	    public void insertBbsComment(@RequestParam Map<String, Object> kkkk, HttpServletResponse response,
	    		 						HttpServletRequest request) throws IOException {
			JSONObject result = new JSONObject();
			LoginVO user = new LoginVO();
			
	        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	        if(auth.getDetails() instanceof tem4UserDetails) {
	        	tem4UserDetails detail = (tem4UserDetails) auth.getDetails();
	        	user = detail.getLoginInfo();
	        }		
			
			try {
				String ntt_id = (String) kkkk.get("ntt_id");
				String bbs_id = (String) kkkk.get("bbs_id");
				String content = (String) kkkk.get("content");
				String bbs_ty_code = (String) kkkk.get("ty_code");
				String cafe_id = (String) kkkk.get("cafe_id");
				
				BbsManageVO bbsManageVO = new BbsManageVO();
				bbsManageVO.setBbsTyCode(bbs_ty_code);
				request.setAttribute("userId", user.getId());
				
				if ( user == null ) {
		    		throw new Exception("회원전용 서비스입니다.\n로그인후 진행주세요.");
		    	}
				
					bbsManageVO.setBbsId(Integer.parseInt(bbs_id));
					bbsManageVO = bbsManageService.selectBbsManage(bbsManageVO);
					
					if(bbsManageVO.getReplyAuth().indexOf(user.getRole()) < 0){
						throw new Exception("권한이 없습니다.\n관리자에게 문의해주세요.");
					}
					
					BbsCommentVO bbsCommentVO = new BbsCommentVO();
					
					
					bbsCommentVO.setNttId(ntt_id);
					bbsCommentVO.setBbsId(Integer.parseInt(bbs_id));
					bbsCommentVO.setContent(content);
					
					int answer_gno = bbsCommentService.insertCommentAnswer(bbsCommentVO, request);
					bbsCommentVO.setAnswerGno(answer_gno);
					bbsCommentService.insertBbsComment(bbsCommentVO, request);
				
				result.put("result_cd", "200");
				result.put("result_msg", "Success");

			} catch (Exception e) {
				result.put("result_cd", "500");
				result.put("result_msg", e.getMessage());
			} finally {
		        response.setHeader("Content-Type","text/html; charset=UTF-8");
		    	PrintWriter out = response.getWriter();
		 	    out.write(result.toString());
		 	    out.flush();
		 	    out.close();
		    }
		}
	    
	    
	    /**
	     * 댓글정보를 수정한다.
	     * @param write_auth 
	     * 
	     * @param asdaas
	     * @param BbsDefaultVO
	     * @param status
	     * @return
	     * @throws Exception
	     */
	    @SuppressWarnings("unused")
		@RequestMapping(value="/front/bbs/cmt/updateBbsComment.do")
	    public void updateBbsComment(@RequestParam Map<String, Object> params, HttpServletResponse response,
	    							HttpServletRequest request) throws IOException {
	    	
	    	// 결과 상태값 셋팅
			JSONObject result = new JSONObject();
			LoginVO user = new LoginVO();
			
	        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	        if(auth.getDetails() instanceof tem4UserDetails) {
	        	tem4UserDetails detail = (tem4UserDetails) auth.getDetails();
	        	user = detail.getLoginInfo();
	        }		
			
			try {
				//공간종류코드를 코드정보로부터 조회valueOf(params.get("userId"));
				String ntt_id = (String) params.get("ntt_id");
				String bbs_id = (String) params.get("bbs_id");
				String content = (String) params.get("content");
				String answer_no = (String) params.get("answer_no");
				
				if ( user == null ) {
		    		throw new Exception("회원전용 서비스입니다.\n로그인후 진행주세요.");
		    	}
				
				// 로그인된 아이디값 bbsVO에 담음.
				BbsCommentVO bbsCommentVO = new BbsCommentVO();
				
	
				bbsCommentVO.setNttId(ntt_id);
				bbsCommentVO.setBbsId(Integer.parseInt(bbs_id));
				bbsCommentVO.setContent(content);
				bbsCommentVO.setAnswerNo(answer_no);
				
				
				bbsCommentService.updateBbsComment(bbsCommentVO, request);
	    		result.put("result_cd", "200");
				result.put("result_msg", "Success");

			} catch (Exception e) {
				result.put("result_cd", "500");
				result.put("result_msg", e.getMessage());
			} finally {
	            response.setHeader("Content-Type","text/html; charset=UTF-8");
	        	PrintWriter out = response.getWriter();
	     	    out.write(result.toString());
	     	    out.flush();
	     	    out.close();
	        }
	    }
	    
	    /**
	     * 댓글정보를 삭제한다.
	     * @param write_auth 
	     * 
	     * @param asdaas
	     * @param BbsDefaultVO
	     * @param status
	     * @return
	     * @throws Exception
	     */
	    @SuppressWarnings("unused")
		@RequestMapping(value="/front/bbs/cmt/deleteBbsComment.do")
	    public void deleteBbsComment(@RequestParam Map<String, Object> params, HttpServletResponse response,
	    								HttpServletRequest request) throws IOException {
	    	
	    	// 결과 상태값 셋팅
			JSONObject result = new JSONObject();
			LoginVO user = new LoginVO();
			
	        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	        if(auth.getDetails() instanceof tem4UserDetails) {
	        	tem4UserDetails detail = (tem4UserDetails) auth.getDetails();
	        	user = detail.getLoginInfo();
	        }		
			try {
				//공간종류코드를 코드정보로부터 조회valueOf(params.get("userId"));
				String ntt_id = (String) params.get("ntt_id");
				String bbs_id = (String) params.get("bbs_id");
				String answer_no = (String) params.get("answer_no");
				
				if ( user == null ) {
		    		throw new Exception("회원전용 서비스입니다.\n로그인후 진행주세요.");
		    	}
					
				// 로그인된 아이디값 bbsVO에 담음.
				BbsCommentVO bbsCommentVO = new BbsCommentVO();
				
				
				//리스트페이지로 다시 돌아올려면 id값이 필요함. 그래서 같이 셋팅
				bbsCommentVO.setNttId(ntt_id);
				bbsCommentVO.setBbsId(Integer.parseInt(bbs_id));
				bbsCommentVO.setAnswerNo(answer_no);
				
				bbsCommentService.deleteBbsComment(bbsCommentVO,request);
	    		result.put("result_cd", "200");
				result.put("result_msg", "Success");

			} catch (Exception e) {
				result.put("result_cd", "500");
				result.put("result_msg", e.getMessage());
			} finally {
	            response.setHeader("Content-Type","text/html; charset=UTF-8");
	        	PrintWriter out = response.getWriter();
	     	    out.write(result.toString());
	     	    out.flush();
	     	    out.close();
	        }
	    }
	    /**
	     * 신규 답글을 등록한다..
	     * @param write_auth 
	     * 
	     * @param asdaas
	     * @param BbsDefaultVO
	     * @param status
	     * @return /usr/bbs/cmt/selectAnswer.do
	     * @throws Exception
	     */
	    @RequestMapping(value="/front/bbs/cmt/insertCommentAnswer.do")
	    public void insertCommentAnswer(@RequestParam Map<String, Object> params, HttpServletResponse response,
	    								HttpServletRequest request) throws IOException {
	    	
	    	// 결과 상태값 셋팅
			JSONObject result = new JSONObject();
			LoginVO user = new LoginVO();
			
	        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	        if(auth.getDetails() instanceof tem4UserDetails) {
	        	tem4UserDetails detail = (tem4UserDetails) auth.getDetails();
	        	user = detail.getLoginInfo();
	        }		

			try {
				//공간종류코드를 코드정보로부터 조회valueOf(params.get("userId"));
				String ntt_id = (String) params.get("ntt_id");
				String bbs_id = (String) params.get("bbs_id");
				String content = (String) params.get("content");
				String answer_no = (String) params.get("answer_no");
				//String answer_gno = (String) params.get("answer_no");
				
				if ( user == null ) {
		    		throw new Exception("회원전용 서비스입니다.\n로그인후 진행주세요.");
		    	}
				// 로그인된 아이디값 bbsVO에 담음.
				BbsCommentVO bbsCommentVO = new BbsCommentVO();
				
				
				bbsCommentVO.setNttId(ntt_id);
				bbsCommentVO.setBbsId(Integer.parseInt(bbs_id));
				bbsCommentVO.setContent(content);
				bbsCommentVO.setpAnswerNo(Integer.parseInt(answer_no));
				
				//부모의 그룹번호 가져오기
				BbsCommentDefaultVO searchVO = new BbsCommentDefaultVO();
				searchVO.setAnswerNo(Integer.parseInt(answer_no));
				
				BbsCommentVO answer_gno = bbsCommentService.selectCmtAnswer(searchVO);
				bbsCommentVO.setAnswerGno(answer_gno.getAnswerGno());
				
				bbsCommentService.insertBbsComment(bbsCommentVO, request);
	    		result.put("result_cd", "200");
				result.put("result_msg", "Success");
					
			} catch (Exception e) {
				result.put("result_cd", "500");
				result.put("result_msg", e.getMessage());
			} finally {
	            response.setHeader("Content-Type","text/html; charset=UTF-8");
	        	PrintWriter out = response.getWriter();
	     	    out.write(result.toString());
	     	    out.flush();
	     	    out.close();
	        }
	    }
		/**
	     * 각 게시물의 댓글 목록을 조회한다.
	     * 
	     * @param boardMasterVO
	     * @param model
	     * @return
	     * @throws Exception
	     */
	    @RequestMapping(value="/front/bbs/cmt/BbsCommentList.do")
	    public String selectBbsCommentList(
	    		//@RequestParam("selectId") int ntt_id,
	    		@ModelAttribute("searchVO") BbsCommentDefaultVO searchVO,
	    		@ModelAttribute("bbsCommentVO") BbsCommentVO bbsCommentVO, 
	    		ModelMap model, HttpServletRequest request) throws Exception {
	    	
	    	PaginationInfo paginationInfo = new PaginationInfo();
			paginationInfo.setCurrentPageNo(searchVO.getPageIndex());
			paginationInfo.setRecordCountPerPage(searchVO.getPageUnit());
			paginationInfo.setPageSize(searchVO.getPageSize());
			
			searchVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
			searchVO.setLastIndex(paginationInfo.getLastRecordIndex());
			searchVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());		
			
			int totCnt = bbsCommentService.selectBbsCommentListCnt(searchVO);
	        paginationInfo.setTotalRecordCount(totCnt);
	        model.addAttribute("paginationInfo", paginationInfo);
	
	        List<BbsCommentVO> resultList = bbsCommentService.selectBbsCommentList(searchVO);
	        
	        model.addAttribute("resultList", resultList);
			return "/front/bbs/cmt/selectComment";
	    }

	   

	}
