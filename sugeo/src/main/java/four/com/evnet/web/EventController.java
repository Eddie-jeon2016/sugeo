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
import four.common.util.security.tem4UserDetails;
import four.mng.code.service.SubCodeManageVO;
import four.com.evnet.service.EventDefaultVO;
import four.com.evnet.service.EventService;
import four.com.evnet.service.EventVO;

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
public class EventController {

	@Resource(name = "EventService")
	private EventService eventService;


	@RequestMapping("/front/event/eventViewer.do")
	public String eventViewer(@ModelAttribute EventVO eventVO, Model model) throws Exception {

		EventVO result =  eventService.selectEventOne(eventVO);
		model.addAttribute("result", result);
		return "/front/event/eventViewer";
	}
	

}