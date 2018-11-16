/**
 * 개요
 * - 메인화면이미지에 대한 controller 클래스를 정의한다.
 *
 * 상세내용
 * - 메인화면이미지에 대한 등록, 수정, 삭제, 조회, 반영확인 기능을 제공한다.
 * - 메인화면이미지의 조회기능은 목록조회, 상세조회로 구분된다.
 * @author 이문준
 * @version 1.0
 * @created 03-8-2009 오후 2:08:57
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자          수정내용
 *  -------    --------    ---------------------------
 *  2010.8.3	이문준          최초 생성
 *  2011.8.26	정진오			IncludedInfo annotation 추가
 *
 *  </pre>
 */

package four.mng.pop.web;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;



import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;




import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.service.FileMngService;
import egovframework.com.cmm.service.FileMngUtil;
import egovframework.com.cmm.service.FileVO;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import four.com.bbs.service.BbsDefaultVO;
import four.common.util.security.tem4UserDetails;
import four.mng.pop.service.PopManageService;
import four.mng.pop.service.PopManageVO;

@Controller
public class PopManageController {

	@Resource(name = "popManageService")
	private PopManageService popManageService;
	
	@Resource(name = "FileMngUtil")
	private FileMngUtil fileUtil;

	@Resource(name = "FileMngService")
	private FileMngService fileMngService;
	
	@Resource(name = "egovFileIdGnrService")
	private EgovIdGnrService idgenService;

	@RequestMapping(value = "/mng/pop/popupList.do")
	public String popupManageList(Model model, @ModelAttribute("searchVO")PopManageVO searchVO, HttpServletRequest request) throws Exception {
		
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(searchVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(searchVO.getPageUnit());
		paginationInfo.setPageSize(searchVO.getPageSize());

		searchVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		searchVO.setLastIndex(paginationInfo.getLastRecordIndex());
		searchVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		List<PopManageVO> popupList = popManageService.selectPopManageList(searchVO);
		for (int i = 0; i < popupList.size(); i++) {
			String sD = popupList.get(i).getStartDate();
			popupList.get(i).setStartDate(sD.substring(0, 4) + "년" + sD.substring(5, 7) + "월" + sD.substring(8, 10)
					+ "일 " + sD.substring(11, 13) + "시" + sD.substring(14, 16) + "분");
			String eD = popupList.get(i).getEndDate();
			popupList.get(i).setEndDate(eD.substring(0, 4) + "년" + eD.substring(5, 7) + "월" + eD.substring(8, 10) + "일 "
					+ eD.substring(11, 13) + "시" + eD.substring(14, 16) + "분");
		}
		
		
		int totCnt = popManageService.selectPopManageListCnt(); // 총 글수 개수구함
		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("popupList", popupList);
		model.addAttribute("paginationInfo", paginationInfo);

		return "/mng/pop/popupList";
	}

	@RequestMapping("/mng/pop/popupRegView.do")
	public String popupManageReg() throws Exception {
		
		return "mng/pop/popupRegView";
		
	}

@RequestMapping(value = "/mng/pop/popupInsert.do")
	public String popupManageInsert(Model model, PopManageVO pVO,
									final MultipartHttpServletRequest multiRequest, HttpServletRequest request) throws Exception {
		LoginVO user = new LoginVO();

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth.getDetails() instanceof tem4UserDetails) {
			tem4UserDetails detail = (tem4UserDetails) auth.getDetails();
			user = detail.getLoginInfo();
		}
		
		if (user == null) {
			return "forward:/usr/uia/LoginUsr.do";
		}

		  boolean writeAuth = false; boolean readAuth = false;
		  final Map<String, MultipartFile> files = multiRequest.getFileMap();
		  request.setAttribute("userId", user.getId());

		  
		  if(user != null) { 
			  //로그인 하였을 때
			  List<FileVO> result = null;
			  List<FileVO> attachResult = new ArrayList<>();
			  FileVO Thumbfvo = null;
			  FileVO fvo = null;
		  
			  String uploadFolder = "";
			  String image = "";
			  String imageFile = "";
			  String atchFileId = "";
			  String thumbatchFileId = "";
		  
			 
			  if(!files.isEmpty()){ 
				  result = fileUtil.parseFileInf(files, "POPUP_",0, "", uploadFolder);
		  
			  	//atchFileId = fileMngService.insertFileInfs(result); 
				for (FileVO fvvvo : result) {
					System.out.println(fvvvo);
					if ("file_0".equals(fvvvo.getFieldName())) {
						Thumbfvo = fvvvo;
					}
				}
				System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!");

				if (Thumbfvo != null) {
					System.out.println("@@@@@@@@@@@@@@@@");
					System.out.println(Thumbfvo);
					System.out.println("@@@@@@@@@@@@@@@@");
					thumbatchFileId = fileMngService.insertFileOne(Thumbfvo);
					System.out.println("----------------" + thumbatchFileId);
					pVO.setFileId(thumbatchFileId);
				} else {
					pVO.setFileId("0");
				}
			}
		}
		
		popManageService.insertPopManage(pVO,request);
		
		return "redirect:/mng/pop/popupList.do";
	}
	
	@RequestMapping(value = "/mng/pop/popupUpdateView.do")
	public String popupManageUpdateView(Model model, String popupId, String pageIndex, HttpServletRequest request) throws Exception {
		
		PopManageVO pVO =  popManageService.selectPopManage(popupId);
    	
    	pVO.setStartHour(pVO.getStartDate().substring(11, 16));
    	pVO.setStartDate(pVO.getStartDate().substring(0, 10));

    	pVO.setEndHour(pVO.getEndDate().substring(11, 16));
    	pVO.setEndDate(pVO.getEndDate().substring(0, 10));

    	
		FileVO vo = new FileVO();
    /*	vo.setAtchFileId(bbsVO.getAtchFileId());*/
		vo.setAtchFileId(pVO.getFileId());
		vo.setFileSn("0");

    	//List<FileVO> fileList = fileMngService.selectFileInfs(vo);
    	vo = fileMngService.selectFileInf(vo);

		model.addAttribute("thumbFile",vo);
		model.addAttribute("popup", pVO);
		model.addAttribute("cmd", "update");
		model.addAttribute("pageIndex", pageIndex);
		
		return "mng/pop/popupUpdateView";
	}
	
	@RequestMapping(value = "/mng/pop/popupUpdate.do")
	public String popupManageUpdate(PopManageVO pVO,
									final MultipartHttpServletRequest multiRequest,
									@RequestParam Map<?, ?> commandMap, HttpServletRequest request) throws Exception {
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
			request.setAttribute("userId", user.getId());
			
			List<FileVO> result = null;
			List<FileVO> attachResult = new ArrayList();
			FileVO Thumbfvo 	= null;
			FileVO fvo 			= null;

	    	String uploadFolder = "";
	    	String image = "";
	    	String imageFile = "";
	    	String atchFileId = "";
	    	String thumbatchFileId = "";

			if (commandMap.get("changeImg").equals("Y")) {
				final Map<String, MultipartFile> files = multiRequest.getFileMap();

				if (!files.isEmpty()) {
					result = fileUtil.parseFileInf(files, "POPUP_", 0, "", uploadFolder);

					// atchFileId = fileMngService.insertFileInfs(result);
					for (FileVO fvvvo : result) {
						
						if ("file_0".equals(fvvvo.getFieldName())) {
							Thumbfvo = fvvvo;
							pVO.setFileId(Thumbfvo.getAtchFileId());
						} else if (fvvvo.getFieldName().startsWith("file_")) {
							attachResult.add(fvvvo);
						}
					}
					atchFileId = fileMngService.insertFileInfs(attachResult);
					if (Thumbfvo != null) {
						thumbatchFileId = fileMngService.insertFileOne(Thumbfvo);
					} else {
						
						pVO.setFileId("0");
					}

				}
			}

	    	String ntt_no = idgenService.getNextStringId();

			

/*
			// 로그인된 아이디값 bbsVO에 담음.
			bbsVO.setNtcrId(user.getUniqId());
			bbsVO.setregId(user.getUniqId());

			if(atchFileId != null && atchFileId != ""){
				bbsVO.setAtchFileId(atchFileId);
			}else {
				bbsVO.setAtchFileId("0");
			}
			if(thumbatchFileId != null && thumbatchFileId != ""){
				bbsVO.setThumbAtchFileId(thumbatchFileId);
			}else {
				bbsVO.setThumbAtchFileId("0");
			}*/

			// submit 시 들고온값 여기서 데이터 입력함.
	    	
			popManageService.updatePopManage(pVO, request);
		}
		
		
		
		
		
		return "redirect:/mng/pop/popupList.do?pageIndex="+commandMap.get("pageIndex");
	}
	
	@RequestMapping(value = "/mng/pop/popupDelete.do")
	public String popupManageDelete(String popupId, String pageIndex, HttpServletRequest request) throws Exception {
		LoginVO user = new LoginVO();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		tem4UserDetails detail = (tem4UserDetails) auth.getDetails();
		user = detail.getLoginInfo();
		request.setAttribute("userId", user.getId());
		
		popManageService.deletePopManage(popupId,request);
		
		return "redirect:/mng/pop/popupList.do";
	}
}

