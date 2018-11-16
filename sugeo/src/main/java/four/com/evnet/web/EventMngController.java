package four.com.evnet.web;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.google.gson.Gson;

import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.service.FileMngService;
import egovframework.com.cmm.service.FileMngUtil;
import egovframework.com.cmm.service.FileVO;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import four.com.bbs.service.BbsManageVO;
import four.com.evnet.service.EventDefaultVO;
import four.com.evnet.service.EventService;
import four.com.evnet.service.EventVO;
import four.common.util.security.tem4UserDetails;
import four.mng.code.service.SubCodeManageVO;

/**
 * @Class Name : EventController.java
 * @Description : EventController Class
 * 
 *              이벤트 등록 및 관리를 위한 컨트롤러 클래스 @ author 운영지원팀 이록근 @ since 2018.08.08 @
 * version 1.0 @ see
 *
 * << 개정이력 (Modification Information) >>
 *
 * 수정일 수정자 수정내용 ---------- --------- --------------------------- 18.08.08 이록근 최초
 * 생성
 * 
 * Copyright (C) by MOPAS All right reserved.
 */

@Controller
public class EventMngController {

	@Resource(name = "EventService")
	private EventService eventService;

	@Resource(name = "FileMngUtil")
	private FileMngUtil fileUtil;

	@Resource(name = "FileMngService")
	private FileMngService fileMngService;
	
	@Resource(name = "egovEventIdGnrService")
	private EgovIdGnrService idgenService;

	@RequestMapping("/mng/event/eventInsertView.do")
	public String eventInsertView(Model model, HttpServletRequest request) throws Exception {
		LoginVO user = new LoginVO();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		tem4UserDetails detail = (tem4UserDetails) auth.getDetails();
		user = detail.getLoginInfo();
		request.setAttribute("userId", user.getId());
		
		/*
		 * 권한 체크 아직 잘 모르겠음
		 */

		List<SubCodeManageVO> subCondeList = eventService.subCodeList();
		List<Integer> openYnCntList = new ArrayList<>();

		for (int i = 0; i < subCondeList.size(); i++) {
			String eventType = subCondeList.get(i).getSubCode();
			int openYnCnt = eventService.checkOpenYnCnt(eventType);
		
			openYnCntList.add(openYnCnt);
		}
		
		model.addAttribute("cmd", "insert");
		model.addAttribute("subCodeList", subCondeList);
		model.addAttribute("openYnCntList", openYnCntList);

		return "/mng/event/EventDetailView";
	}

	@RequestMapping("/mng/event/eventInsert.do")
	public String eventInsert(@ModelAttribute EventVO eventVO, Model model,
			final MultipartHttpServletRequest multiRequest,
					HttpServletRequest request) throws Exception {

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
		eventVO.setUserId(user.getId());
		/*
		 * boolean writeAuth = false; boolean readAuth = false;
		 * 
		 * 
		 * final Map<String, MultipartFile> files = multiRequest.getFileMap();
		 * if(user != null) { //로그인 하였을 때 List<FileVO> result = null;
		 * List<FileVO> attachResult = new ArrayList(); FileVO Thumbfvo = null;
		 * FileVO fvo = null;
		 * 
		 * String uploadFolder = ""; String image = ""; String imageFile = "";
		 * String atchFileId = ""; String thumbatchFileId = "";
		 * 
		 * 
		 * if(!files.isEmpty()){ result = fileUtil.parseFileInf(files, "EVENT_",
		 * 0, "", uploadFolder);
		 * 
		 * // atchFileId = fileMngService.insertFileInfs(result); for(FileVO
		 * fvvvo: result){ System.out.println("FOR문에서getFieldName()이름  = " +
		 * fvvvo.getFieldName()); if("file_0".equals(fvvvo.getFieldName())){
		 * Thumbfvo = fvvvo; }else if(fvvvo.getFieldName().startsWith("file_")){
		 * attachResult.add(fvvvo); } } atchFileId =
		 * fileMngService.insertFileInfs(attachResult); if(Thumbfvo != null ){
		 * thumbatchFileId = fileMngService.insertFileOne(Thumbfvo); }else{
		 * eventVO.setThumbAtchFileId("0"); } }
		 * 
		 * if(thumbatchFileId != null && thumbatchFileId != ""){
		 * eventVO.setThumbAtchFileId(thumbatchFileId); }else {
		 * eventVO.setThumbAtchFileId("0"); } }
		 */

		eventVO.setEventNo(idgenService.getNextStringId());
	
		
		//등록 시 공개여부를 Y로 등록 했을 때
		if(eventVO.getOpenYn().equals("Y")) {
			String eventType = eventVO.getEventType();
			//등록하려는 이벤트타입의  openYn의 갯수를 검색
			int openYnCnt = eventService.checkOpenYnCnt(eventType);
			// 기존 데이터에 Y가 있으면 N으로 변경
			if(openYnCnt == 1) {
				eventService.updateEventTypeValueN(eventType, request);		
			}
			eventService.insertEvent(eventVO,request);			
			
		}else {		// 등록 시 N으로 등록한다면 상관없음
			eventService.insertEvent(eventVO,request);			
		}
		

		return "redirect:/mng/event/eventList.do";

	}

	@RequestMapping("/mng/event/eventList.do")
	public String eventList(@ModelAttribute("searchVO") EventDefaultVO searchVO, Model model, HttpServletRequest request) throws Exception {

		LoginVO user = new LoginVO();
		

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth.getDetails() instanceof tem4UserDetails) {
			tem4UserDetails detail = (tem4UserDetails) auth.getDetails();
			user = detail.getLoginInfo();
		}
		if (user == null) {
			model.addAttribute("resultMsg", "fail.common.login");
			return "/usr/uia/LoginUsr";
		}
		request.setAttribute("userId", user.getId());
		PaginationInfo paginationInfo = new PaginationInfo();
		BbsManageVO vo = new BbsManageVO();
		paginationInfo.setCurrentPageNo(searchVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(searchVO.getPageUnit());
		paginationInfo.setPageSize(searchVO.getPageSize());

		searchVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		searchVO.setLastIndex(paginationInfo.getLastRecordIndex());
		searchVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
		
		/*	모두 N일 때 리스트를 보여주기 전에 이벤트종류마다 하나씩 Y로 
		List<SubCodeManageVO> subCondeList = eventService.subCodeList();
		for (int i = 0; i < subCondeList.size(); i++) {
			if(subCondeList.get(i).getCode().equals("A0005")) {
				String eventType = subCondeList.get(i).getSubCode();
				int openYnCnt = eventService.checkOpenYnCnt(eventType);
				if(openYnCnt == 0) {
					System.out.println("eventType = " + eventType);
					eventService.updateRecentEvent(eventType);
				}
			}
			
		}*/
		
		List<EventVO> resultList = eventService.eventList(searchVO);

		for (int i = 0; i < resultList.size(); i++) {
			String sD = resultList.get(i).getStartDate();
			resultList.get(i).setStartDate(sD.substring(0, 4) + "년" + sD.substring(5, 7) + "월" + sD.substring(8, 10)
					+ "일 " + sD.substring(11, 13) + "시" + sD.substring(14, 16) + "분");
			resultList.get(i).setStartHour( sD.substring(11, 13)+":"+sD.substring(14, 16));
			
			String eD = resultList.get(i).getEndDate();
			resultList.get(i).setEndDate(eD.substring(0, 4) + "년" + eD.substring(5, 7) + "월" + eD.substring(8, 10)
					+ "일 " + eD.substring(11, 13) + "시" + eD.substring(14, 16) + "분");
			resultList.get(i).setEndHour( eD.substring(11, 13)+":"+eD.substring(14, 16));

			Date curDate = new Date();
			String chgSd = sD.replaceAll("-", "").replaceAll(":", "").replace(" ", "").substring(0, 14);
			String chgEd = eD.replaceAll("-", "").replaceAll(":", "").replace(" ", "").substring(0, 14);
			System.out.println("chgSd = " + chgSd);
			System.out.println("chgEd = " + chgEd);
			long curTime = System.currentTimeMillis();
			SimpleDateFormat df = new SimpleDateFormat("YYYYMMddHHmmss");
			String tempCurTime = df.format(new Date(curTime));
			
			long curTimeL = Long.parseLong(tempCurTime);
			long startTimeL =  Long.parseLong(chgSd);
			long endTimeL = Long.parseLong(chgEd);
			
			resultList.get(i).setRegDate(resultList.get(i).getRegDate().substring(2, 10));
			
			if(curTimeL - startTimeL < 0) {
				resultList.get(i).setEventStmt("대기");
			}
			if(curTimeL - startTimeL >= 0 
					&& curTimeL - endTimeL <= 0) {
				
				resultList.get(i).setEventStmt("접수중");

			}
			if(curTimeL - endTimeL >= 0) {
				resultList.get(i).setEventStmt("마감");
			}
			
			if(resultList.get(i).getEventType().equals("빈칸 채우기 이벤트")) 
				resultList.get(i).setEventType("빈칸");
			else if(resultList.get(i).getEventType().equals("삼지선다 이벤트"))
				resultList.get(i).setEventType("삼지선다");
			else if(resultList.get(i).getEventType().equals("동영상퀴즈 이벤트"))
				resultList.get(i).setEventType("동영상");
		}
		
		

		int totCnt = eventService.eventListCnt();
		paginationInfo.setTotalRecordCount(totCnt);

		model.addAttribute("resultList", resultList);
		model.addAttribute("paginationInfo", paginationInfo);
		return "/mng/event/EventDetailList";
	}

	@RequestMapping("/mng/event/eventDetailUpdateView.do")
	public String eventDetailUpdtView(@ModelAttribute("searchVO") EventVO searchVO,
										String eventNo, Model model) throws Exception {
		
		LoginVO user = new LoginVO();

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth.getDetails() instanceof tem4UserDetails) {
			tem4UserDetails detail = (tem4UserDetails) auth.getDetails();
			user = detail.getLoginInfo();
		}

		if (user == null) {
			return "forward:/usr/uia/LoginUsr.do";
		}
		
		
		EventVO result = eventService.selectEvent(eventNo);
		
		result.setPageIndex(searchVO.getPageIndex());
		String sD = result.getStartDate();
		result.setStartDate(sD.substring(0, 10));
		result.setStartHour(sD.substring(11, 13)+":"+sD.substring(14, 16));
		
		String eD = result.getEndDate();
		result.setEndDate(eD.substring(0, 10));
		result.setEndHour(eD.substring(11, 13)+":"+eD.substring(14, 16));
		
		List<SubCodeManageVO> subCondeList = eventService.subCodeList();

		model.addAttribute("result", result);
		model.addAttribute("cmd", "update");
		model.addAttribute("subCodeList", subCondeList);
		return "/mng/event/EventDetailView";
	}

	

	@RequestMapping("/mng/event/eventUpdate.do")
	public String eventUpdate(@ModelAttribute EventVO eventVO, Model model, HttpServletRequest request) throws Exception {

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
		eventVO.setUserId(user.getId());
		
		eventVO.setStartDate(eventVO.getStartDate()+" "+eventVO.getStartHour());
		
		eventVO.setEndDate(eventVO.getEndDate()+" "+eventVO.getEndHour());
		// 수정한 이벤트의 공개여부가 Y일 때 
		// 같은 이벤트들의 공개여부를 모두 N으로 수정
		if(eventVO.getOpenYn().equals("Y")) {
			eventService.updateEventTypeValueN(eventVO.getEventType(),request);	
			eventService.updateEvent(eventVO,request);
		}else {
			// 수정한 이벤트의 공개여부가 N일 때 먼저 수정을 하고
			// 같은 이벤트들의 공개여부의 Y의 갯수를 확인하고 가징 최근 이벤트의 공개여부를 Y로 수정
			eventService.updateEvent(eventVO,request);		
			int openYn = eventService.checkOpenYnCnt(eventVO.getEventType());
			if(openYn == 0) {
				eventService.updateRecentEvent(eventVO,request);
				
			}
		}

		return "redirect:/mng/event/eventList.do?pageIndex="+eventVO.getPageIndex();
	}
	
	@RequestMapping("/mng/event/eventDelete.do")
	public String eventDelete(@ModelAttribute EventVO eventVO, HttpServletRequest request) throws Exception {
		
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
		System.out.println("이벤트 삭제에서 아이디 값" + user.getId());
		eventVO.setUserId(user.getId());
		
		String eventNo = eventVO.getEventNo()+"";
		
		// 지우려는 이벤트의 공개여부가 Y일 경우 
		// 해당 이벤트의 use_yn을 n으로 변경 후 
		// use_yn이 y이고 같은 종류의 이벤트 중에서 가장 최신이벤트의 open_yn을 y로 변경
		if(eventVO.getOpenYn().equals("Y")) {
			eventService.eventDelete(eventNo,request);
			request.setAttribute("userId", user.getId());
			eventService.updateRecentEvent(eventVO,request);
		} else {	// 지우려는 이벤트의 공개여부가 N이면 상관없음
			eventService.eventDelete(eventNo,request);		
			int openYnCnt = eventService.checkOpenYnCnt(eventVO.getEventType());
			// Y가 1개도 없다면 지우려는 이벤트를 지우고
			// 가장 최근의 이벤트 공개여부를 Y로 수정
			if(openYnCnt == 0) {
				eventService.eventDelete(eventNo,request);				
				eventService.updateRecentEvent(eventVO,request);
			}
		}
		return "redirect:/mng/event/eventList.do?pageIndex="+eventVO.getPageIndex();
	}
	
	@RequestMapping("/mng/event/eventTypeCheckYCnt.do")
	@ResponseBody
    public String eventTypeCheckYCnt(@ModelAttribute EventVO eventVO, HttpServletRequest request) throws Exception {
    	
		
    	HashMap result = new HashMap();
    	int openYn = eventService.checkOpenYnCnt(eventVO.getEventType());
    	
    	if(openYn == 1) {
    		result.put("result_cd", 500);
    		result.put("result_mng", "Fail");
    	}else {
//    		result.put("result_cd", 200);
    		result.put("result_mng", "Success");
    	}
    	return new Gson().toJson(result);
    }
	
	
	@RequestMapping("/front/event/eventPreview.do")
	public String eventPreview(Model model, @ModelAttribute EventVO eventVO) throws Exception {
	
		System.out.println("^^이벤트");
		System.out.println(eventVO);
		
		model.addAttribute("result", eventVO);
		return "/mng/event/EventPreviewPop";
	}
	

}