package four.com.bbs.web;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.aspectj.apache.bcel.generic.ReturnaddressType;
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
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.util.UrlPathHelper;
import org.springmodules.validation.commons.DefaultBeanValidator;

import com.google.gson.Gson;

import egovframework.com.cmm.ComDefaultVO;
import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.service.FileMngService;
import egovframework.com.cmm.service.FileMngUtil;
import egovframework.com.cmm.service.FileVO;
import egovframework.com.cmm.service.Globals;
import egovframework.com.cmm.util.StringUtil;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
// 코드값 VO 임
//공통코드에 관한 코드
import four.com.bbs.cate.service.BbsCateService;
import four.com.bbs.cate.service.BbsCateVO;
import four.com.bbs.cmt.service.BbsCommentDefaultVO;
import four.com.bbs.cmt.service.BbsCommentService;
import four.com.bbs.service.BbsDefaultVO;
import four.com.bbs.service.BbsManageService;
import four.com.bbs.service.BbsManageVO;
import four.com.bbs.service.BbsService;
import four.com.bbs.service.BbsVO;
import four.com.evnet.service.EventService;
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
	public class BbsUserController {

		@Resource(name = "bbsService")
	    private BbsService bbsService;

	    @Resource(name = "BbsCateService")
	    private BbsCateService bbsCateService;

		@Resource(name = "bbsCommentService")
	    private BbsCommentService bbsCommentService;

		@Resource(name = "BbsManageService")
	    private BbsManageService bbsManageService;

	    /** fileMngService */
	    @Resource(name = "FileMngService")
	    private FileMngService fileMngService;

	    @Resource(name = "FileMngUtil")
	    private FileMngUtil fileUtil;

	    @Resource(name = "propertiesService")
	    protected EgovPropertyService propertyService;
	    
	    @Resource(name = "egovBbsNttIdGnrService")
		private EgovIdGnrService idgenService;

	    @Resource(name = "EventService")
		private EventService eventService;
	    
	    @Autowired
	    private DefaultBeanValidator beanValidator;

//	    Logger log = Logger.getLogger(this.getClass());
	   //

	    /**
		 * CONTENT 목록을 조회한다. (pageing)
		 * @param searchVO - 조회할 정보가 담긴 ContentDefaultVO
		 * @return "/content/ContentList"
		 * @exception Exception
		 */
	    @RequestMapping(value={"/front/bbs/BbsMain.do"})
	    public String ContentMain(
	    		@ModelAttribute("searchVO") ComDefaultVO searchVO,
	    		HttpServletRequest request,
	    		ModelMap model)
	            throws Exception {
	    	
	    	LoginVO user = new LoginVO();

	        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	        if(auth.getDetails() instanceof tem4UserDetails) {
	        	tem4UserDetails detail = (tem4UserDetails) auth.getDetails();
	        	user = detail.getLoginInfo();
	        }
	    	String returnUrl = "";

		    	BbsManageVO bmVO = new BbsManageVO();
		    	System.out.println("searchVO.getMenuNo()========= " + searchVO.getMenuNo());
		    	System.out.println(searchVO);
		    	bmVO.setMenuNo(Integer.parseInt(searchVO.getMenuNo()));
		    	request.setAttribute("userId", user.getId());
		    	BbsManageVO bbsVO = bbsManageService.selectBbsManage(bmVO);
		    	if(bbsVO != null){
			    	searchVO.setBbsId(bbsVO.getBbsId());
		    	}
				
		    	model.addAttribute("title",bbsVO.getbbsName());
		    	model.addAttribute("bbsVO",bbsVO);
		    	model.addAttribute("loginVO",user);

		    	UrlPathHelper urlPath = new UrlPathHelper();
		    	String url = urlPath.getOriginatingServletPath(request);

		    	if("/front/bbs/BbsMain.do".equals(url)) {
		    		returnUrl = "/front/bbs/BbsMain";
		    	} 

			return returnUrl;
	    }

		@RequestMapping({"/front/bbs/BbsInfoValidator.do"})
		@ResponseBody
	    public String BbsInfoValidator(
	    		@RequestParam Map<String, Object> params
	    		, HttpServletResponse response
	    		, HttpServletRequest request) throws Exception {
			// 결과 상태값 셋팅
			HashMap result = new HashMap();
			LoginVO user = new LoginVO();

	        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	        if(auth.getDetails() instanceof tem4UserDetails) {
	        	tem4UserDetails detail = (tem4UserDetails) auth.getDetails();
	        	user = detail.getLoginInfo();
	        }
			String bbs_id = String.valueOf(params.get("bbsId"));
			String role = "";
			BbsManageVO bbsManageVO = new BbsManageVO();
			bbsManageVO.setBbsId(Integer.parseInt(bbs_id));
			bbsManageVO = bbsManageService.selectBbsManage(bbsManageVO);

			try {
				if(user == null){
					role = "ROLE_ANONYMOUS";
				}else{
					role = user.getRole();
				}
				if(user == null){
					throw new Exception("회원전용 서비스입니다.\n로그인후 진행주세요.");
				}
				if(bbsManageVO.getWriteAuth().indexOf(role) < 0){
					throw new Exception("사용권한이 없습니다.");
				}
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
	    @RequestMapping(value={"/front/bbs/BbsSelectList.do", "/mng/bbs/BbsDetailList.do", "/mng/notice/BbsDetailList.do", "/mng/bbs/relatedContent/BbsDetailList.do"})
	    public String selectBBSList(
	    		@ModelAttribute("searchVO") BbsDefaultVO searchVO,
	    		HttpServletRequest request ,
	    		ModelMap model) throws Exception {
	    	

	    	UrlPathHelper urlPath = new UrlPathHelper();
	    	String url = urlPath.getOriginatingServletPath(request);
	    	
	    	//최초 로그인 후 이벤트 공지로 가기위한 설정
	    	if("/mng/main.do".equals(url)) {

	    		searchVO.setBbsId((Integer.parseInt(request.getParameter("BbsId"))));
	    		searchVO.setMenuNo((Integer.parseInt(request.getParameter("menuNo"))));
	    		searchVO.setSearchCondition("2");
	    	}
	    	if("/mng/notice/BbsDetailList.do".equals(url)) {
	    		searchVO.setSearchCondition("2");
	    	}
	    	
		    String returnUrl = "";
		    if(searchVO.getBbsId() != null){
			    PaginationInfo paginationInfo = new PaginationInfo();

				paginationInfo.setCurrentPageNo(searchVO.getPageIndex());
				paginationInfo.setRecordCountPerPage(searchVO.getPageUnit());
				paginationInfo.setPageSize(searchVO.getPageSize());

				searchVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
				searchVO.setLastIndex(paginationInfo.getLastRecordIndex());
				searchVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
			
				//말머리를 가져오기위해 bbs_id 를 셋팅해줌
				BbsCateVO bbsCateVO = new BbsCateVO();
				bbsCateVO.setBbsId(searchVO.getBbsId());
				List<BbsCateVO> cateList = bbsCateService.selectBbsCateList(bbsCateVO);


				//마스터관리에서 상세정보(코드)를 가져오기위해 bbs_id 를 셋팅해줌
				BbsManageVO bbsManageVO = new BbsManageVO();
				bbsManageVO.setBbsId(searchVO.getBbsId());
				bbsManageVO = bbsManageService.selectBbsManage(bbsManageVO);
				
				
				List<BbsVO> resultList = new ArrayList<>();
				int totCnt;
				
				if(searchVO.getMenuNo() == 1000008 && "/front/bbs/BbsMain.do".equals(url)) {
					resultList = bbsService.selectEvnetNoticeNList(searchVO);
					totCnt = bbsService.selectEvnetNoticeNListCnt(searchVO); //총 글수 개수구함
				}else {
					resultList = bbsService.selectBbsList(searchVO);	
					 totCnt = bbsService.selectBbsListCnt(searchVO); //총 글수 개수구함
				}
				BbsVO vo = null;
				
			    if(resultList != null){
			    	for(int i=0; i < resultList.size(); i++){
			    		StringBuilder content = new StringBuilder();
			    		vo = resultList.get(i);
			    		vo.setcontent(vo.getcontent()); //Textarea

			        }
			    }

			    System.out.println(resultList.size());
			    for(int i=0; i<resultList.size(); i++) {
			    
					BbsVO result = resultList.get(i);
					result.getcontent().replaceAll("/<br>/ig", "\n");
					result.getcontent().replaceAll("/&nbsp;/ig", " ");
					result.setcontent(result.getcontent().replaceAll("\\<.*?>",""));
				}

				model.addAttribute("resultList", resultList);
		     
				
				
		        //Textarea 특수문자 복원
		        //댓글 개수 구함

		        /*BbsCommentDefaultVO searchCmtVO = new BbsCommentDefaultVO();
		        searchCmtVO.setBbsId(Integer.parseInt(searchVO.getNttId()));
		        int answer_Cnt = bbsCommentService.selectBbsCommentListCnt(searchCmtVO);
		        model.addAttribute("answer_Cnt", answer_Cnt);
		         */
				 
		        List<BbsVO> noticeList = null;
		      
		        if(searchVO.getMenuNo() == 1000008 && "/front/bbs/BbsMain.do".equals(url)) {
		        	 noticeList = bbsService.selectBbsListnoticeYn(searchVO);
		        	
		        }
		        
		       /* int ReplyCnt = bbsService.selectBbsListReplyCnt(searchVO); //리플개수 구함 */

		        paginationInfo.setTotalRecordCount(totCnt);
		        model.addAttribute("paginationInfo", paginationInfo);
		        model.addAttribute("noticeList",noticeList);

		        //댓글쓰기기능 사용여부 체크
				Boolean answerYn = false;

				//권한이 없는 회원들은 글쓰기버튼 없앤다.
				Boolean writeAuth = false;
				Boolean readAuth = false;
				Boolean outMember = true;
				LoginVO user = new LoginVO();

		        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		        if(auth.getDetails() instanceof tem4UserDetails) {
		        	tem4UserDetails detail = (tem4UserDetails) auth.getDetails();
		        	user = detail.getLoginInfo();
		        }
				if(user.getRole() != null){ //로그인 하였을 때
					if(bbsManageVO.getWriteAuth().indexOf(user.getRole()) > -1){ //로그인한 유저권한에 쓰기권한이 있을경우
						writeAuth = true;
					}
					if (bbsManageVO.getReadAuth().indexOf(user.getRole()) > -1){ //로그인한 유저권한에 읽기권한이 있을경우
						readAuth = true;
					}

				}else{
					if(bbsManageVO.getWriteAuth().indexOf("ROLE_ANONY") > -1){ //로그인한 유저권한에 쓰기권한이 있을경우
						writeAuth = true;
					}
					if (bbsManageVO.getReadAuth().indexOf("ROLE_ANONY") > -1){ //로그인한 유저권한에 읽기권한이 있을경우
						readAuth = true;
					}

				}

				if(bbsManageVO.getanswerYn() == "Y"){ //댓글쓰기기능이 'Y' 일경우 true로 바꿔줌.
					answerYn = true;
				}

				model.addAttribute("outMember",outMember);
				model.addAttribute("answerYn", answerYn);
				/*model.addAttribute("replycnt", ReplyCnt);*/
				model.addAttribute("readAuth", readAuth);
				model.addAttribute("writeAuth", writeAuth); //글쓰기 권한
				model.addAttribute("cateList", cateList); // 말머리
				model.addAttribute("bbsManageVO", bbsManageVO); //게시판마스터관리
				model.addAttribute("paginationInfo", paginationInfo);
				model.addAttribute("searchVO", searchVO);

				
		    	if("/front/bbs/BbsMain.do".equals(url)) {
		    		returnUrl = "/front/bbs/board/selectBoardList";
		    		if(searchVO.getMenuNo() == 1000001){
		    			model.addAttribute("type","relatedContent");
		    		}
		    	}else if("/mng/main.do".equals(url)) {		    		
		    		returnUrl = "/mng/bbs/detail/BbsDetailList";
		    		model.addAttribute("type", "eventNotice");
				}else if("/mng/bbs/BbsDetailList.do".equals(url)) {		    		
		    		returnUrl = "/mng/bbs/detail/BbsDetailList";
				}else if("/mng/notice/BbsDetailList.do".equals(url)) {
					returnUrl =  "/mng/bbs/detail/BbsDetailList";
					model.addAttribute("type", "eventNotice");
				}else if("/mng/bbs/relatedContent/BbsDetailList.do".equals(url)){
					returnUrl =  "/mng/bbs/detail/BbsDetailList";					
					model.addAttribute("type", "relatedContent");
				}else if("/front/bbs/BbsSelectList.do".equals(url)) {
					returnUrl = "/front/bbs/board/selectBoardList";
				}

		    }else{
		    	returnUrl = "/mediacenter/com/cmm/error/code404";
		    }
		    System.out.println("######최종 url = " + returnUrl);
			return returnUrl;
	    }

	    /**
	     * 신규 게시물 등록을 위한 등록페이지로 이동한다.
	     *
	     * @param BbsManageVO 게시판관리
	     * @param BbsCommentDefaultVO 검색조건
	     * @param model
	     * @return
	     * @throws Exception
	     */
	   @RequestMapping({"/front/bbs/BbsInsertView.do"})
	    public String insertBbsView(
	    		@ModelAttribute("searchVO") BbsDefaultVO searchVO,
	    		@ModelAttribute("bbsVO") BbsVO bbsVO,
	    		Model model,
	    		HttpServletRequest request) throws Exception {
			String returnUrl = "";
			//말머리 들고오기 위한 코드
			Integer bbs_id =searchVO.getBbsId();
			BbsCateVO bbsCateVO = new BbsCateVO();
			bbsCateVO.setBbsId(bbs_id);
			List<BbsCateVO> cateList = bbsCateService.selectBbsCateList(bbsCateVO);
			model.addAttribute("cateList", cateList); // 말머리

			BbsManageVO bbsManageVO = new BbsManageVO();
			bbsManageVO.setBbsId(bbs_id);
			bbsManageVO = bbsManageService.selectBbsManage(bbsManageVO);

			//권한 체크 ( View 페이지에서 버튼 보여주지않기 위함 )
	        Boolean writeAuth = false;
	        Boolean readAuth = false;
	        Boolean answerAuth = false;
	        Boolean replyAuth = false;

			LoginVO user = new LoginVO();

	        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	        if(auth.getDetails() instanceof tem4UserDetails) {
	        	tem4UserDetails detail = (tem4UserDetails) auth.getDetails();
	        	user = detail.getLoginInfo();
	        }
			if(user != null){
				if(bbsManageVO.getWriteAuth().indexOf(user.getRole()) > -1){
					writeAuth = true;
				}
				if(bbsManageVO.getReadAuth().indexOf(user.getRole()) > -1){
					readAuth = true;
				}
				if(bbsManageVO.getAnswerAuth().indexOf(user.getRole()) > -1){
					answerAuth = true;
				}
				if(bbsManageVO.getReplyAuth().indexOf(user.getRole()) > -1){
					replyAuth = true;
				}
					bbsVO.setNtcrId(user.getUniqId());
					
			}
					model.addAttribute("writeAuth", writeAuth); //글쓰기 권한
					model.addAttribute("readAuth", readAuth); //글쓰기 권한
					model.addAttribute("answerAuth", answerAuth); //글쓰기 권한
					model.addAttribute("replyAuth", replyAuth); //글쓰기 권한
				//권한체크 끝
					model.addAttribute("bbsManageVO", bbsManageVO);

					UrlPathHelper urlPath = new UrlPathHelper();
			    	String url = urlPath.getOriginatingServletPath(request);

			    	if("/front/bbs/BbsMain.do".equals(url)) {
			    		returnUrl = "/front/bbs/board/InsertBoardView";
			    	} 

					return returnUrl;//자료실 , 자유게시판
				}
	   /**
	     * 신규 게시물을 등록 한다.
	     *
	     * @param BbsManageVO 게시판관리
	     * @param BbsCommentDefaultVO 검색조건
	     * @param model
	     * @return
	     * @throws Exception
	     */
	   @RequestMapping({"/front/bbs/BbsInsert.do"}) /** 등록버튼 클릭 시? */
	    public String insertBbs(
	    		final MultipartHttpServletRequest multiRequest,
	    		@ModelAttribute("searchVO") BbsDefaultVO searchVO,
	    		@ModelAttribute("bbsVO") BbsVO bbsVO,
	    		BindingResult bindingResult,
	    		Model model,
	    		HttpServletRequest request) throws Exception {

				LoginVO user = new LoginVO();

		        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		        if(auth.getDetails() instanceof tem4UserDetails) {
		        	tem4UserDetails detail = (tem4UserDetails) auth.getDetails();
		        	user = detail.getLoginInfo();
		        }
		       
		    	if(user == null) {
	        	return "forward:/usr/uia/LoginUsr.do";
		    	}
		    	
	    		BbsManageVO bbsManageVO = new BbsManageVO();
	    		bbsManageVO.setBbsId(searchVO.getBbsId());
				bbsManageVO = bbsManageService.selectBbsManage(bbsManageVO);
				model.addAttribute("bbsManageVO", bbsManageVO);
	    		boolean writeAuth = false;
				boolean readAuth = false;


				if(user != null) { //로그인 하였을 때
					
					List<FileVO> result = null;
					List<FileVO> attachResult = new ArrayList();
					FileVO Thumbfvo 	= null;
					FileVO fvo 			= null;

	    	    	String uploadFolder = "";
	    	    	String image = "";
	    	    	String imageFile = "";
	    	    	String atchFileId = "";
	    	    	String thumbatchFileId = "";

	    	    	final Map<String, MultipartFile> files = multiRequest.getFileMap();
	    	    	if(!files.isEmpty()){
	    	    	    result = fileUtil.parseFileInf(files, "BBS1_", 0, "", uploadFolder);

//	    	    	    atchFileId = fileMngService.insertFileInfs(result);
	    	    	    for(FileVO fvvvo: result){
	    	    	    	if("file_0".equals(fvvvo.getFieldName())){
	    	    	    		Thumbfvo = fvvvo;
	    	    	    	}else if(fvvvo.getFieldName().startsWith("file_")){
	    	    	    		attachResult.add(fvvvo);
	    	    	    	}
	    	    	    }
	    	    	    atchFileId = fileMngService.insertFileInfs(attachResult);
	    	    	    if(Thumbfvo != null ){
	    	    	    	thumbatchFileId = fileMngService.insertFileOne(Thumbfvo);
	    	    	    }else{
    	    	    		bbsVO.setThumbAtchFileId("0");
	    	    	    }

	    	    	}

	    	    	String ntt_no = idgenService.getNextStringId();

					if(bbsManageVO.getWriteAuth().indexOf(user.getRole()) > -1){ //로그인한 유저권한에 쓰기권한이 있을경우
						writeAuth = true;
					}
					if (bbsManageVO.getReadAuth().indexOf(user.getRole()) > -1){ //로그인한 유저권한에 읽기권한이 있을경우
						readAuth = true;
					}


					// 로그인된 아이디값 bbsVO에 담음.
					bbsVO.setNtcrId(user.getUniqId());
					bbsVO.setregId(user.getId());

					if(atchFileId != null && atchFileId != ""){
						bbsVO.setAtchFileId(atchFileId);
					}else {
						bbsVO.setAtchFileId("0");
					}
					if(thumbatchFileId != null && thumbatchFileId != ""){
						bbsVO.setThumbAtchFileId(thumbatchFileId);
					}else {
						bbsVO.setThumbAtchFileId("0");
					}

					bbsVO.setNttNo(ntt_no);
				

					// submit 시 들고온값 여기서 데이터 입력함.
					bbsService.insertBbs(bbsVO,request);
				}

				UrlPathHelper urlPath = new UrlPathHelper();
		    	String url = urlPath.getOriginatingServletPath(request);
		    	String returnUrl = "";
		    	if("/front/bbs/BbsInsert.do".equals(url)) {
		    		if(bbsManageVO.getMenuNo() == 1000008) {
		    			returnUrl = "redirect:/mng/notice/BbsDetailList.do?BbsId=8&menuNo=1000008";
		    		}else if(bbsManageVO.getMenuNo() == 1000001){
		    			returnUrl = "redirect:/mng/bbs/relatedContent/BbsDetailList.do?BbsId=1&menuNo=1000001";		    			
		    		}else {
		    			returnUrl = "redirect:/front/bbs/BbsMain.do?menuNo="+bbsManageVO.getMenuNo();
		    		}
		    	}

					return returnUrl;
	    }


		    	 /**
			     * 한 게시물에 대한 상세조회한다.
			     * @param ntt_id 상세조회대상 게시물아이디
			     * @param BbsCommentDefaultVO 검색조건
			     * @param model 화면모델
			     * @return returnUrl
			     * @throws Exception
			     */
			    @RequestMapping({"/front/bbs/BbsSelectView.do"})
			    public String selectBbs(
			    		@RequestParam("selectId") String ntt_id ,
			    		@RequestParam("selectbbsId") int bbs_id ,
			            @ModelAttribute("searchVO") BbsDefaultVO searchVO,
			            @ModelAttribute("bbsVO") BbsVO bbsVO,
			            HttpServletRequest request, SessionStatus session,
			            Model model
			            )throws Exception {
			        String returnUrl = "";

			        /*현재 URL 작성하기*/
			    	String backUrl = "/front/bbs/BbsMain.do";

			        //상세보기 클릭했을때 +1 해주는 쿼리
			        bbsService.updateRdCnt(ntt_id);

			        //게시물 관련 상세조회한 내용 불러오기
			    	bbsVO = bbsService.selectBbs(ntt_id);
			    	//Textarea 특수문자 복원
			    	bbsVO.setcontent(bbsVO.getcontent());
			    	bbsVO.setSearchCateId(searchVO.getSearchCateId());
			    	// 이전글, 다음글 게시글을 가져온다.
			    	BbsVO nextBbs = bbsService.nextSelectBoardArticle(bbsVO);
			    	BbsVO prevBbs = bbsService.prevSelectBoardArticle(bbsVO);
			    	
			
			    	if(searchVO.getMenuNo() == 1000008) {
			    		nextBbs = bbsService.nextSelectEventNotice(bbsVO);
			    		prevBbs = bbsService.prevSelectEventNotice(bbsVO);
			    	}

			    	model.addAttribute("nextBbs", nextBbs);
			    	model.addAttribute("prevBbs", prevBbs);
			        model.addAttribute("result", bbsVO);

			    	BbsManageVO bbsManageVO = new BbsManageVO();
					bbsManageVO.setBbsId(bbs_id);
					bbsManageVO = bbsManageService.selectBbsManage(bbsManageVO);

					

					//로그인한 대상자
					LoginVO user = new LoginVO();

			        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			        if(auth.getDetails() instanceof tem4UserDetails) {
			        	tem4UserDetails detail = (tem4UserDetails) auth.getDetails();
			        	user = detail.getLoginInfo();
			        }

			    	Boolean writeAuth = false;
			        Boolean readAuth = false;
			        Boolean answerAuth = false;
			        Boolean replyAuth = false;
			    	if ( user.getRole() != null ) {
						if(bbsManageVO.getWriteAuth().indexOf(user.getRole()) > -1){
							writeAuth = true;
						}
						if(bbsManageVO.getReadAuth().indexOf(user.getRole()) > -1){
							readAuth = true;
						}
						if(bbsManageVO.getAnswerAuth().indexOf(user.getRole()) > -1){
							answerAuth = true;
						}
						if(bbsManageVO.getReplyAuth().indexOf(user.getRole()) > -1){
							replyAuth = true;
						}

					}else{
						if(bbsManageVO.getWriteAuth().indexOf("ROLE_ANONY") > -1){ //로그인한 유저권한에 쓰기권한이 있을경우
							writeAuth = true;
						}
						if (bbsManageVO.getReadAuth().indexOf("ROLE_ANONY") > -1){ //로그인한 유저권한에 읽기권한이 있을경우
							readAuth = true;
						}
						if(bbsManageVO.getAnswerAuth().indexOf("ROLE_ANONY") > -1){ //로그인한 유저권한에 읽기권한이 있을경우
							answerAuth = true;
						}
						if(bbsManageVO.getReplyAuth().indexOf("ROLE_ANONY") > -1){ //로그인한 유저권한에 읽기권한이 있을경우
							replyAuth = true;
						}

					}
			    	Boolean answerYn = false;
			    	if(bbsManageVO.getanswerYn() == "Y"){
						answerYn = true;
					}
					model.addAttribute("answerYn", answerYn);
			        String domain = Globals.DOMAIN_URL;
			        //코드값으로 페이지이동을 위한 내용 불러오기
			        //등급권한을 불러온다.
					model.addAttribute("domain", domain);
					System.out.println("domain = " + domain);
					model.addAttribute("bbsManageVO", bbsManageVO);
					model.addAttribute("writeAuth", writeAuth); //글쓰기 권한
					model.addAttribute("readAuth", readAuth); //글쓰기 권한
					model.addAttribute("answerAuth", answerAuth); //글쓰기 권한
					model.addAttribute("replyAuth", replyAuth); //글쓰기 권한

					UrlPathHelper urlPath = new UrlPathHelper();
			    	String url = urlPath.getOriginatingServletPath(request);
			    	if("/front/bbs/BbsMain.do".equals(url)) {
			    		returnUrl = "/front/bbs/board/selectBoardView";
			    	} 
					return returnUrl; //자료실 , 자유게시판
			    }
			    /**
			     * 상세조회한 게시물을 수정하기 위해 수정페이지로 이동한다.
			     * @param ntt_id 상세조회대상 게시물아이디
			     * @param BbsCommentDefaultVO 검색조건
			     * @param model 화면모델
			     * @return returnUrl
			     * @throws Exception
			     */
			    @RequestMapping({"/front/bbs/BbsSelectUpdtView.do", "/mng/bbs/BbsSelectDetailUpdtView.do", "/mng/notice/BbsSelectDetailUpdtView.do", "/mng/bbs/relatedContent/BbsSelectDetailUpdtView.do"})
			    public String updateBbsView(
			    		@RequestParam("select_nttId") String ntt_id ,
			            @ModelAttribute("searchVO") BbsDefaultVO searchVO,
			            @ModelAttribute("bbsVO") BbsVO bbsVO,
			            Model model,
			            HttpServletRequest request
			            )throws Exception {
			    	
			        String returnUrl = "";
			        //게시물 관련 상세조회한 내용 불러오기
//			    	bbsVO = bbsService.selectBbs(ntt_id);
			      

			        bbsVO = bbsService.selectBbs(ntt_id);
			     
			       
			    	model.addAttribute("result",bbsVO);

			    	FileVO vo = new FileVO();
/*			    	vo.setAtchFileId(bbsVO.getAtchFileId());
			    	List<FileVO> fileList = fileMngService.selectFileInfs(vo);
			    	vo.setAtchFileId(bbsVO.getThumbAtchFileId());
			    	vo = fileMngService.selectFileInf(vo);
		    		model.addAttribute("thumbFile",vo);
			    	model.addAttribute("fileList",fileList);
			    	System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@"+vo);*/
			    	
			    	vo.setAtchFileId(bbsVO.getThumbAtchFileId());
					vo.setFileSn("0");
					List<FileVO> fileList = fileMngService.selectFileInfs(vo);
			    	
			    	vo = fileMngService.selectFileInf(vo);
			    	System.out.println(vo);
			    	/*bbsVO.setAtchFileId(vo.getAtchFileId());*/
			    	model.addAttribute("fileList",fileList);
					model.addAttribute("thumbFile",vo);

			    	//코드값으로 페이지이동을 위한 내용 불러오기
			    	BbsManageVO bbsManageVO = new BbsManageVO();
					bbsManageVO.setBbsId(bbsVO.getBbsId());
					bbsManageVO = bbsManageService.selectBbsManage(bbsManageVO);
					bbsManageVO.setNttId(ntt_id);
					
					//말머리를 가져오기위해 bbs_id 를 셋팅해줌
					BbsCateVO bbsCateVO = new BbsCateVO();
					bbsCateVO.setBbsId(bbsVO.getBbsId());
					List<BbsCateVO> cateList = bbsCateService.selectBbsCateList(bbsCateVO);
					
					//로그인한 대상자
					LoginVO user = new LoginVO();

			        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			        if(auth.getDetails() instanceof tem4UserDetails) {
			        	tem4UserDetails detail = (tem4UserDetails) auth.getDetails();
			        	user = detail.getLoginInfo();
			        }
			        /*  getNtcrId 이거 이제 없는데.... 록근
				    Boolean isAuthenticated2 = Boolean.valueOf(bbsVO.getNtcrId().equals(user.getUniqId()));
				    if (!isAuthenticated2.booleanValue()) {
				    	if(!user.getRole().equals("ROLE_ADMIN")){
				    		throw new Exception("수정권한이 없습니다.");
				    	}
				    }*/

				    Boolean writeAuth = false;
			        Boolean readAuth = false;
			        Boolean answerAuth = false;
			        Boolean replyAuth = false;
			    	if ( user.getRole() != null ) {
			    		//user = new LoginVO();
						if(bbsManageVO.getWriteAuth().indexOf(user.getRole()) > -1){
							writeAuth = true;
						}
						if(bbsManageVO.getReadAuth().indexOf(user.getRole()) > -1){
							readAuth = true;
						}
						if(bbsManageVO.getAnswerAuth().indexOf(user.getRole()) > -1){
							answerAuth = true;
						}
						if(bbsManageVO.getReplyAuth().indexOf(user.getRole()) > -1){
							replyAuth = true;
						}

			    	}
			    	

				    	model.addAttribute("writeAuth", writeAuth); //글쓰기 권한
						model.addAttribute("readAuth", readAuth); //글쓰기 권한
						model.addAttribute("answerAuth", answerAuth); //글쓰기 권한
						model.addAttribute("replyAuth", replyAuth); //글쓰기 권한
						model.addAttribute("cateList", cateList); // 말머리
						model.addAttribute("bbsManageVO", bbsManageVO);
						model.addAttribute("cmd", "update");
						
						UrlPathHelper urlPath = new UrlPathHelper();
				    	String url = urlPath.getOriginatingServletPath(request);
				    	if("/front/bbs/BbsMain.do".equals(url)) {
				    		returnUrl = "/front/bbs/board/InsertBoardView";
				    	}else if("/mng/bbs/BbsSelectDetailUpdtView.do".equals(url)){
				    			returnUrl = "/mng/bbs/detail/BbsDetailView";	
				    	}else if("/mng/notice/BbsSelectDetailUpdtView.do".equals(url)) {
				    		returnUrl = "/mng/bbs/detail/eventNoticeInsertView";	
				    	}else if("/mng/bbs/relatedContent/BbsSelectDetailUpdtView.do".equals(url)){
				    		returnUrl = "/mng/bbs/detail/relatedContentInsertView";
				    	}
				    	
				    	
						return returnUrl;
				    }

			    /**
		         * 게시물 수정 후 목록페이지로 이동
		         * @param searchVO
		         * @param model
		         * @return
		         * @throws Exception
		         */
		        @RequestMapping({"/front/bbs/BbsUpdate.do"})
		        public String updateBbs(
		        		final MultipartHttpServletRequest multiRequest,
		        		@ModelAttribute("searchVO") BbsDefaultVO searchVO,
		        		@ModelAttribute("bbsVO") BbsVO bbsVO,
		        		HttpServletRequest request,
			            Model model
			            ) throws Exception {
		    
					LoginVO user = new LoginVO();

			        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			        if(auth.getDetails() instanceof tem4UserDetails) {
			        	tem4UserDetails detail = (tem4UserDetails) auth.getDetails();
			        	user = detail.getLoginInfo();
			        }

			    	if(user == null) {
//			    		model.addAttribute("resultMsg", "fail.common.login");
			        	return "forward:/usr/uia/LoginUsr.do";
				    	}


			    	//첨부파일삭제
					String apply_id =  StringUtil.isNullToString(multiRequest.getParameter("attach_id"));
					List<FileVO> fileResult = null;
					FileVO fvo 			= null;

					//첨부파일삭제
					if(apply_id != null && !apply_id.equals("")){
						String apply_sn[] = multiRequest.getParameterValues("attach_sn");

						List<FileVO> fvoList = new ArrayList<FileVO>();

						for(int i = 0 ; i < apply_sn.length; i++){

							fvo = new FileVO();
							fvo.setAtchFileId(apply_id);
							fvo.setFileSn(apply_sn[i]);
							fvoList.add(fvo);
						}


						fileMngService.deleteFileInfs(fvoList);
						fvo = new FileVO();
						fvo.setAtchFileId(apply_id);
						int fileCnt = fileMngService.getMaxFileSN(fvo);

						//첨부파일 모두 삭제할지여부
						if(fileCnt == 1 ){

							fileMngService.deleteAllFileInf(fvo);
							bbsVO.setAtchFileId("0");
						}
					}

					//썸네일 삭제
					String thumb_id = StringUtil.isNullToString(multiRequest.getParameter("thumb_id"));
					FileVO thumbfvo 			= null;
					if(thumb_id != null && !thumb_id.equals("")){
						thumbfvo = new FileVO();
						thumbfvo.setAtchFileId(thumb_id);
						fileMngService.deleteAllFileInf(thumbfvo);
						bbsVO.setThumbAtchFileId("0");
					}

		    		String uploadFolder = "";
				    String atchFileId = bbsVO.getAtchFileId();
				    String thumbatchFileId = bbsVO.getThumbAtchFileId();
				    String file_sn ="";
				    List<FileVO> attachResult = new ArrayList();
				    FileVO Thumbfvo = new FileVO();

				    final Map<String, MultipartFile> files = multiRequest.getFileMap();

//    	    	    result = fileUtil.parseFileInf(files, "BBS_", 0, "", uploadFolder);
//
//    	    	    for(FileVO fvvvo: result){
//    	    	    	if("file_0".equals(fvvvo.getFieldName())){
//    	    	    		Thumbfvo = fvvvo;
//    	    	    	}else{
//    	    	    		attachResult.add(fvvvo);
//    	    	    	}
//    	    	    }
//    	    	    atchFileId = fileMngService.insertFileInfs(attachResult);
//    	    	    if(Thumbfvo != null ){
//    	    	    	thumbatchFileId = fileMngService.insertFileOne(Thumbfvo);
//    	    	    }else{
//	    	    		bbsVO.setThumbAtchFileId("0");
//    	    	    }
				    
				    if (!files.isEmpty()) {
				    	if(atchFileId == null || atchFileId.equals("0")){
				    		List<FileVO> result = fileUtil.parseFileInf(files, "BBS_", 0, "", uploadFolder);

		    	    	    for(FileVO fvvvo: result){
		    	    	    	if(!"file_0".equals(fvvvo.getFieldName()) && fvvvo.getFieldName().startsWith("file_")){
		    	    	    		attachResult.add(fvvvo);
		    	    	    
		    	    	    	}
		    	    	    }
		    	    	    
		    	    	    atchFileId = fileMngService.insertFileInfs(attachResult);
				    	    bbsVO.setAtchFileId(atchFileId);

				    	}else{

				    		fvo = new FileVO();
				    		fvo.setAtchFileId(atchFileId);
				    		int cnt = fileMngService.getMaxFileSN(fvo);
				    		List<FileVO> _result = fileUtil.parseFileInf(files, "BBS_", cnt, atchFileId, "");

		    	    	    for(FileVO fvvvo: _result){
		    	    	    	if ("file_1".equals(fvvvo.getFieldName()) ) {
									file_sn = "1";
								}else if("file_2".equals(fvvvo.getFieldName())){
									file_sn = "2";
								}else if("file_3".equals(fvvvo.getFieldName())){
									file_sn = "3";
								}else if("file_4".equals(fvvvo.getFieldName())){
									file_sn = "4";
								}

		    	    	    	fvvvo.setFileSn(file_sn);
		    	    	    	if(!"file_0".equals(fvvvo.getFieldName()) && fvvvo.getFieldName().startsWith("file_")){
		    	    	    		attachResult.add(fvvvo);
		    	    	    		if(fileMngService.selectFileInf(fvvvo) == null){
		    	    	    			fileMngService.insertFileInfDetail(fvvvo);
		    	    	    		}else{
		    	    	    			fileMngService.updateFileDetail(fvvvo);
		    	    	    		}
		    	    	    	}

		    	    	    }

				    	}

				    	if(thumbatchFileId == null || "0".equals(thumbatchFileId)){
				    		List<FileVO> result = fileUtil.parseFileInf(files, "BBS_", 0, "", uploadFolder);
		    	    	    for(FileVO fvvvo: result){
		    	    	    	if("file_0".equals(fvvvo.getFieldName())){
		    	    	    		Thumbfvo = fvvvo;
		    	    	    	}
		    	    	    }
		    	    	    if(Thumbfvo != null ){
		    	    	    	if(!"".equals(Thumbfvo.getFileSn())) {
		    	    	    		thumbatchFileId = fileMngService.insertFileOne(Thumbfvo);
		    	    	    	}
		    	    	    }
				    	    bbsVO.setThumbAtchFileId(thumbatchFileId);

				    	}else{

				    		fvo = new FileVO();
				    		fvo.setAtchFileId(thumbatchFileId);
				    		int cnt = fileMngService.getMaxFileSN(fvo);
				    		List<FileVO> _result = fileUtil.parseFileInf(files, "BBS_", cnt, thumbatchFileId, "");

		    	    	    for(FileVO fvvvo: _result){

		    	    	    	if ("file_0".equals(fvvvo.getFieldName()) ) {
		    	    	    		fvvvo.setFileSn("0");

		    	    	    		if(fileMngService.selectFileInf(fvvvo) == null){
		    	    	    			fileMngService.insertFileInfDetail(fvvvo);
		    	    	    		}else{
		    	    	    			fileMngService.updateFileDetail(fvvvo);
		    	    	    		}
								}
		    	    	    }


//				    		for (int i=0;i<_result.size();i++) {
//								fvo = _result.get(i);
//
//								if ("file_1".equals(fvo.getFieldName()) ) {
//									file_sn = "0";
//								}else if("file_2".equals(fvo.getFieldName())){
//									file_sn = "1";
//								}else if("file_3".equals(fvo.getFieldName())){
//									file_sn = "2";
//								}else if("file_4".equals(fvo.getFieldName())){
//									file_sn = "3";
//								}
//								fvo.setFileSn(file_sn);
//								if(fileMngService.selectFileInf(fvo) == null){
//									fileMngService.insertFileInfDetail(fvo);
//								}else{
//									fileMngService.updateFileDetail(fvo);
//								}
//							}

				    	}

				    }

				    if(atchFileId == null || atchFileId == ""){
						bbsVO.setAtchFileId("0");
					}

			    	bbsVO.setmodId(user.getUniqId());
					//게시물 관련 상세조회한 내용 불러오기
			    	bbsService.updateBbs(bbsVO,request);

			    	//코드값으로 페이지이동을 위한 내용 불러오기
			    	BbsManageVO bbsManageVO = new BbsManageVO();
					bbsManageVO.setBbsId(bbsVO.getBbsId());
					bbsManageVO = bbsManageService.selectBbsManage(bbsManageVO);

					model.addAttribute("bbsManageVO", bbsManageVO);

					UrlPathHelper urlPath = new UrlPathHelper();
			    	String url = urlPath.getOriginatingServletPath(request);
			    	String returnUrl = "";
			    	if("/front/bbs/BbsUpdate.do".equals(url)) {
			    		if(bbsManageVO.getMenuNo() == 1000008) {
			    			returnUrl = "redirect:/mng/notice/BbsDetailList.do?BbsId=8&menuNo=1000008&pageIndex="+searchVO.getPageIndex();

			    		}else if(bbsManageVO.getMenuNo() == 1000001){
			    			returnUrl = "redirect:/mng/bbs/relatedContent/BbsDetailList.do?BbsId=1&menuNo=1000001";
			    			
			    		}else {
			    			returnUrl = "redirect:/front/bbs/BbsMain.do?menuNo="+bbsManageVO.getMenuNo();
			    		}
			    	} 
					return returnUrl;
		    	}

			    /**
			     * 상세조회한 게시물에 답글을 쓰기위해 답글페이지로 이동한다.
			     * @param ntt_id 상세조회대상 게시물아이디
			     * @param BbsCommentDefaultVO 검색조건
			     * @param model 화면모델
			     * @return returnUrl
			     * @throws Exception
			     */
			    @RequestMapping({"/front/bbs/BbsInsertAnswerView.do"})
			    public String insertBbsAnswerView(
			    		//@RequestParam("selectId") int ntt_id ,
			            @ModelAttribute("searchVO") BbsDefaultVO searchVO,
			            @ModelAttribute("bbsVO") BbsVO bbsVO, HttpServletRequest request,
			            Model model
			            )throws Exception {
			        String returnUrl = "";
			        //게시물 관련 상세조회한 내용 불러오기
			        bbsVO = bbsService.selectBbs(searchVO.getNttId());

			        /*현재 URL 작성하기*/
			    	String backUrl = "/usr/bbs/BbsMain.do";
//			    	String cUrl = WebUtil.urlArgumentMake(backUrl);
//			    	cUrl = cUrl + "cmd="+searchVO.getCmd()+"&pageIndex="+searchVO.getPageIndex()+"&smenuNo="+searchVO.getSmenuNo()+"&ntt_id="+searchVO.getNtt_id();
//			    	model.addAttribute("cUrl", WebUtil.urlEncoding(cUrl));

			        BbsManageVO bbsManageVO = new BbsManageVO();
					bbsManageVO.setBbsId(searchVO.getBbsId());
					bbsManageVO = bbsManageService.selectBbsManage(bbsManageVO);

					//로그인한 대상자
					LoginVO user = new LoginVO();

			        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			        if(auth.getDetails() instanceof tem4UserDetails) {
			        	tem4UserDetails detail = (tem4UserDetails) auth.getDetails();
			        	user = detail.getLoginInfo();
			        }
					Boolean writeAuth = false;
			        Boolean readAuth = false;
			        Boolean answerAuth = false;
			        Boolean replyAuth = false;
					if(user != null){

						//권한 체크 ( View 페이지에서 버튼 보여주지않기 위함 )
						if(bbsManageVO.getWriteAuth().indexOf(user.getRole()) > -1){
							writeAuth = true;
						}
						if(bbsManageVO.getReadAuth().indexOf(user.getRole()) > -1){
							readAuth = true;
						}
						if(bbsManageVO.getAnswerAuth().indexOf(user.getRole()) > -1){
							answerAuth = true;
						}
						if(bbsManageVO.getReplyAuth().indexOf(user.getRole()) > -1){
							replyAuth = true;
						}
							bbsVO.setNtcrId(user.getUniqId());
							

					}
							model.addAttribute("writeAuth", writeAuth); //글쓰기 권한
							model.addAttribute("readAuth", readAuth); //글쓰기 권한
							model.addAttribute("answerAuth", answerAuth); //글쓰기 권한
							model.addAttribute("replyAuth", replyAuth); //글쓰기 권한
						//권한체크 끝
							model.addAttribute("bbsManageVO", bbsManageVO);
							model.addAttribute("bbsVO", bbsVO);

					if(bbsManageVO.getBbsTyCode().equals("NORMAL")){
						returnUrl = "/front/bbs/board/AnswerBoardView"; //자료실 , 자유게시판
					}else if(bbsManageVO.getBbsTyCode().equals("COM1002")){
						returnUrl = "/front/bbs/news/AnswerNewsView"; //센터뉴스
					}

					return returnUrl;
					}

			    /**
			     * 신규 답글을 등록한다..
			     *
			     * @param BbsManageVO 게시판관리
			     * @param BbsCommentDefaultVO 검색조건
			     * @param model
			     * @return
			     * @throws Exception
			     */
			   @RequestMapping("/front/bbs/insertBbsAnswer.do") /** 등록버튼 클릭 시? */
			    public String insertBbsAnswer(
			    		@ModelAttribute("searchVO") BbsDefaultVO searchVO,
			    		@ModelAttribute("bbsVO") BbsVO bbsVO,HttpServletRequest request,
			    		Model model) throws Exception {
			    	// 0. Spring Security 사용자권한 처리
					LoginVO user = new LoginVO();

			        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			        if(auth.getDetails() instanceof tem4UserDetails) {
			        	tem4UserDetails detail = (tem4UserDetails) auth.getDetails();
			        	user = detail.getLoginInfo();
			        }
			    	if(user == null) {
//			    		model.addAttribute("resultMsg", "fail.common.login");
			        	return "forward:/usr/uia/LoginUsr.do";
			    	}
					//코드값 & 권한 가져오기 위함
					BbsManageVO bbsManageVO = new BbsManageVO();
					bbsManageVO.setBbsId(searchVO.getBbsId());
					bbsManageVO = bbsManageService.selectBbsManage(bbsManageVO);
					model.addAttribute("bbsManageVO", bbsManageVO);

					BbsVO bVO = bbsService.selectBbs(bbsVO.getNttId());
					//TODO 로그인 된 값 가져와서 user 에 담음.

					// 로그인된 아이디값 bbsVO에 담음.
					bbsVO.setNtcrId(user.getUniqId());
				
					bbsVO.setregId(user.getUniqId());
					//int parntsctt_no=0;

		
					//부모글 그룹번호 들고와서 Setting
					bbsVO.setNttNo(bVO.getNttNo());
					bbsVO.setAtchFileId("0");

					// submit 시 들고온값 여기서 데이터 입력함.
					bbsService.insertBbs(bbsVO,request);

						return "redirect:/front/bbs/BbsMain.do";
			    }
		    /**
		     * 게시물정보삭제후 목록조회 화면으로 이동한다.
		     * @param lect_sch_id 삭제대상 아이디 정보
		     * @param searchVO 검색조건정보
		     * @param model 화면모델
		     * @return forward:/uss/umt/EgovMberManage.do
		     * @throws Exception
		     */
		    @RequestMapping({"/front/bbs/BbsDelete.do", "/mng/bbs/deleteBbsList.do"})
		    public String deleteBbs(
		            @RequestParam String menuNo,@RequestParam String bbsId,
		            @ModelAttribute("searchVO") BbsDefaultVO searchVO,
		            @RequestParam String params, SessionStatus status,
		            Model model, HttpServletRequest request )throws Exception {		    	
		    	
		    	System.out.println("@@@@@@@@@@@@@@@@@@@@"+menuNo);
		    	System.out.println("@@@@@@@@@@@@@@@@@@@@"+bbsId);
		    	System.out.println("@@@@@@@@@@@@@@@@@@@@"+params);
		    	//로그인 확인
				LoginVO user = new LoginVO();
				
				Authentication auth = SecurityContextHolder.getContext()
						.getAuthentication();
				if (auth.getDetails() instanceof tem4UserDetails) {
					tem4UserDetails detail = (tem4UserDetails) auth.getDetails();
					user = detail.getLoginInfo();
				}
		    	
		    	HashMap<String, Integer> param = new HashMap<String, Integer>();
		    	
				request.setAttribute("userId", user.getId());
		    	
		    	if(params != null || params != ""){
		    		String[] params2 = params.toString().split(",");
		    		for(int i=0; i<params2.length; i++) {
		    			int deleteNo = Integer.parseInt(params2[i]);
		    			bbsService.deleteBbs(deleteNo,request);
		    		}
		    	}

		    	
		    	UrlPathHelper urlPath = new UrlPathHelper();
		    	String url = urlPath.getOriginatingServletPath(request);
		    	String returnUrl = "";
		    	returnUrl = "redirect:/mng/bbs/BbsDetailList.do?BbsId="+bbsId+"&menuNo="+menuNo;		    		
		    	if(menuNo.equals("1000008"))
		    		returnUrl = "redirect:/mng/notice/BbsDetailList.do?BbsId="+bbsId+"&menuNo="+menuNo;

		    	if(menuNo.equals("1000001"))
		    		returnUrl = "redirect:/mng/bbs/relatedContent/BbsDetailList.do?BbsId="+bbsId+"&menuNo="+menuNo;
		    
		    	return returnUrl;
		    }

		    @RequestMapping("/front/SearchMain.do")
		    public String bbsSearchMain(ModelMap model,
		    		@ModelAttribute("searchVO") ComDefaultVO searchVO,
		    		@RequestParam(value="selectbbsId", required=false) String bbsId
		    		)throws Exception{

		    	BbsManageVO bbsVO = new BbsManageVO();
		    	bbsVO.setBbsId(0);
		    	if(bbsId == null || bbsId == ""){
	    			bbsVO.setBbsId(0);
		    	}else{
		    		bbsVO.setBbsId(Integer.parseInt(bbsId));
		    	}


//		    	bbsVO.setBbsId(bbsId);

		    	model.addAttribute("bbsVO",bbsVO);
	    		return "/front/bbs/BbsSearchMain";
		    }

		    @RequestMapping("/front/SearchBbsList.do")
		    public String bbsSearchList(ModelMap model
		    		,@ModelAttribute("searchVO") BbsDefaultVO searchVO
		    		)throws Exception{

			    PaginationInfo paginationInfo = new PaginationInfo();

				paginationInfo.setCurrentPageNo(searchVO.getPageIndex());
				paginationInfo.setRecordCountPerPage(searchVO.getPageUnit());
				paginationInfo.setPageSize(searchVO.getPageSize());

				searchVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
				searchVO.setLastIndex(paginationInfo.getLastRecordIndex());
				searchVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

			    if(searchVO.getBbsId() == 0){
					model.addAttribute("searchTy","TT");
					model.addAttribute("searchTyNm","전체");
			    }else{
			    	model.addAttribute("searchTy","TN");
			    	model.addAttribute("searchTyNm",searchVO.getBbsId());
			    }
				//게시물 리스트 구함
				List<BbsVO> resultList = bbsService.selectBbsListforSearch(searchVO);
				for(int i=0; i<resultList.size(); i++) {

					BbsVO result = resultList.get(i);
					result.getcontent().replaceAll("/<br>/ig", "\n");
					result.getcontent().replaceAll("/&nbsp;/ig", " ");
					result.setcontent(result.getcontent().replaceAll("\\<.*?>",""));
				}
				int totCnt = bbsService.selectBbsListCntforSearch(searchVO); //총 글수 개수구함
				paginationInfo.setTotalRecordCount(totCnt);

				model.addAttribute("resultList", resultList);
				model.addAttribute("paginationInfo", paginationInfo);
				model.addAttribute("totCnt",totCnt);

			    	model.addAttribute("title","통합검색");
		    	return "/front/bbs/board/selectBoardListforSearch";
		    }
		    
		    @RequestMapping("/mng/notice/eventNoticeInsertView.do")
		    public String eventNoticeInsertView(@ModelAttribute("searchVO") BbsDefaultVO searchVO,
		    									@ModelAttribute("bbsVO") BbsVO bbsVO,
		    									Model model,
		    									HttpServletRequest request) throws Exception {
		    	int bbs_id =searchVO.getBbsId();
		    	BbsManageVO bbsManageVO = new BbsManageVO();
				bbsManageVO.setBbsId(bbs_id);
				bbsManageVO = bbsManageService.selectBbsManage(bbsManageVO);
		    	//권한 체크 ( View 페이지에서 버튼 보여주지않기 위함 )
		        Boolean writeAuth = false;
		        Boolean readAuth = false;
		        Boolean answerAuth = false;
		        Boolean replyAuth = false;

				LoginVO user = new LoginVO();

		        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		        if(auth.getDetails() instanceof tem4UserDetails) {
		        	tem4UserDetails detail = (tem4UserDetails) auth.getDetails();
		        	user = detail.getLoginInfo();
		        }
				if(user != null){
					if(bbsManageVO.getWriteAuth().indexOf(user.getRole()) > -1){
						writeAuth = true;
					}
					if(bbsManageVO.getReadAuth().indexOf(user.getRole()) > -1){
						readAuth = true;
					}
					if(bbsManageVO.getAnswerAuth().indexOf(user.getRole()) > -1){
						answerAuth = true;
					}
					if(bbsManageVO.getReplyAuth().indexOf(user.getRole()) > -1){
						replyAuth = true;
					}
						bbsVO.setNtcrId(user.getUniqId());
						
				}
						model.addAttribute("writeAuth", writeAuth); //글쓰기 권한
						model.addAttribute("readAuth", readAuth); //글쓰기 권한
						model.addAttribute("answerAuth", answerAuth); //글쓰기 권한
						model.addAttribute("replyAuth", replyAuth); //글쓰기 권한
						model.addAttribute("cmd", "insert");
						model.addAttribute("bbsManageVO", bbsManageVO);
						
					//권한체크 끝
						
		    	return "/mng/bbs/detail/eventNoticeInsertView";
		    }

		    
		    @RequestMapping("/mng/event/checkYCnt.do")
			@ResponseBody
		    public String checkYCnt() throws Exception {
		    	
		    	HashMap result = new HashMap();
		    	int yCnt = eventService.checkYCnt();
		    	
		    	if(yCnt >= 5) {
		    		result.put("result_cd", 500);
		    		result.put("result_mng", "Fail");
		    	}else {
		    		result.put("result_cd", 200);
		    		result.put("result_mng", "Success");
		    	}
		    	return new Gson().toJson(result);
		    }
		    
		 //관련글 등록 페이지로 이동   
		 @RequestMapping({"/mng/bbs/relatedContent/relatedContentInsertView.do"})
		 public String relatedContentInsertView(@ModelAttribute("searchVO") BbsDefaultVO searchVO,
					@ModelAttribute("bbsVO") BbsVO bbsVO,
					Model model,
					HttpServletRequest request)throws Exception{
		    	int bbs_id =searchVO.getBbsId();
		    	BbsManageVO bbsManageVO = new BbsManageVO();
				bbsManageVO.setBbsId(bbs_id);
				bbsManageVO = bbsManageService.selectBbsManage(bbsManageVO);
		    	//권한 체크 ( View 페이지에서 버튼 보여주지않기 위함 )
		        Boolean writeAuth = false;
		        Boolean readAuth = false;
		        Boolean answerAuth = false;
		        Boolean replyAuth = false;

				LoginVO user = new LoginVO();

		        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		        if(auth.getDetails() instanceof tem4UserDetails) {
		        	tem4UserDetails detail = (tem4UserDetails) auth.getDetails();
		        	user = detail.getLoginInfo();
		        }
				if(user != null){
					if(bbsManageVO.getWriteAuth().indexOf(user.getRole()) > -1){
						writeAuth = true;
					}
					if(bbsManageVO.getReadAuth().indexOf(user.getRole()) > -1){
						readAuth = true;
					}
					if(bbsManageVO.getAnswerAuth().indexOf(user.getRole()) > -1){
						answerAuth = true;
					}
					if(bbsManageVO.getReplyAuth().indexOf(user.getRole()) > -1){
						replyAuth = true;
					}
						bbsVO.setNtcrId(user.getUniqId());
						
				}
						model.addAttribute("writeAuth", writeAuth); //글쓰기 권한
						model.addAttribute("readAuth", readAuth); //글쓰기 권한
						model.addAttribute("answerAuth", answerAuth); //글쓰기 권한
						model.addAttribute("replyAuth", replyAuth); //글쓰기 권한
						model.addAttribute("cmd", "insert");
						model.addAttribute("bbsManageVO", bbsManageVO);			
					//권한체크 끝
				return "/mng/bbs/detail/relatedContentInsertView";
		 }
		 
		 //관련글 수정
		 @RequestMapping({"/mng/bbs/relatedContent/updateRelatedContent.do"})
		 public String updateRelatedContent(final MultipartHttpServletRequest multiRequest,
	        		@ModelAttribute("searchVO") BbsDefaultVO searchVO,
	        		@ModelAttribute("bbsVO") BbsVO bbsVO,
	        		HttpServletRequest request, @RequestParam("oldFileId") String oldFileId,
		            Model model) throws Exception{
			 LoginVO user = new LoginVO();
			 	
		        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		        if(auth.getDetails() instanceof tem4UserDetails) {
		        	tem4UserDetails detail = (tem4UserDetails) auth.getDetails();
		        	user = detail.getLoginInfo();
		        }
		       
		    	if(user == null) {
		    		return "forward:/usr/uia/LoginUsr.do";
		    	}
		    	
				if(user != null) { //로그인 하였을 때
					List<FileVO> result = null;
					List<FileVO> attachResult = new ArrayList();
					FileVO Thumbfvo 	= null;

			    	String uploadFolder = "";
			    	String image = "";
			    	String imageFile = "";
			    	String atchFileId = "";
			    	String thumbatchFileId = "";
				    String file_sn ="";

				    final Map<String, MultipartFile> files = multiRequest.getFileMap();
				    
				    
				    if (!files.isEmpty()) {
				    		result = fileUtil.parseFileInf(files, "BBS_", 0, "", uploadFolder);

		    	    	    for(FileVO fvvvo: result){
		    	    	    	if("file_0".equals(fvvvo.getFieldName())){
		    	    	    		Thumbfvo = fvvvo;
		    	    	    		bbsVO.setThumbAtchFileId(Thumbfvo.getAtchFileId());
		    	    	    	}else if(fvvvo.getFieldName().startsWith("file_")){		    	    	
		    	    	    		attachResult.add(fvvvo);
		    	    	    	}
		    	    	    }
		    	    	    
		    	    	    atchFileId = fileMngService.insertFileInfs(attachResult);
				    	    bbsVO.setAtchFileId(atchFileId);
				    	    
				    	    if(Thumbfvo != null ){
				    	    	thumbatchFileId = fileMngService.insertFileOne(Thumbfvo);
				    	    }else{
				    	    	if(oldFileId != null && oldFileId != "" && oldFileId != "0"){
				    	    		bbsVO.setThumbAtchFileId(oldFileId);				    	    		
				    	    	}else{
				    	    		bbsVO.setThumbAtchFileId("0");				    	    		
				    	    		
				    	    	}
				    	    }
				    	}
				    
				    bbsVO.setmodId(user.getId());
				    //게시물 관련 상세조회한 내용 불러오기
				    bbsService.updateBbs(bbsVO,request);
			}
			 return "redirect:/mng/bbs/relatedContent/BbsDetailList.do?BbsId=1&menuNo=1000001&pageIndex="+searchVO.getPageIndex();
		 }
		    
		    
	}
