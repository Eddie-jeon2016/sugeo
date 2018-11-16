package four.mng.banner.web;
/**  
* @Class Name : BbsDefaultVO.java
* @Description : BbsDefaultVO Class

* @ author 운영지원팀 이록근
* @ since 2018.08.16
* @ version 1.0
* @ see
*
* << 개정이력 (Modification Information) >>
*
*    수정일    	  수정자                수정내용
*  ----------    ---------            ---------------------------
*  18.08.16 	  이록근 		최초 생성
*  
*  Copyright (C) by MOPAS All right reserved.
*/


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import egovframework.com.cmm.service.FileMngService;
import egovframework.com.cmm.service.FileMngUtil;
import egovframework.com.cmm.service.FileVO;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;

import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.google.gson.Gson;

import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.service.FileVO;
import four.common.util.security.tem4UserDetails;
import four.mng.banner.service.bannerService;
import four.mng.banner.service.bannerVO;

@Controller
public class bannerController {

	@Resource(name = "bannerService")
	private bannerService bannerService;
	
	@Resource(name = "FileMngUtil")
	private FileMngUtil fileUtil;
	
	@Resource(name = "FileMngService")
	private FileMngService fileMngService;
	
	@Resource(name = "egovMainImgId1GnrService")
    private EgovIdGnrService mainImg1IdgenService;
	
	@Resource(name = "egovMainImgId2GnrService")
    private EgovIdGnrService mainImg2IdgenService;
	
	@Resource(name = "egovMainImgId3GnrService")
    private EgovIdGnrService mainImg3IdgenService;
	
	
	@RequestMapping("/mng/banner/bannerInsertView.do")
	public String bannerInsertView() {
		
		return "/mng/banner/bannerInsertView";
	}
	
	
	@RequestMapping("/mng/banner/bannerInsert.do") 
	public String bannerInsert(MultipartHttpServletRequest multiRequest,
								@ModelAttribute bannerVO bbVO, HttpServletRequest request
								) throws Exception {
		
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
		 List<FileVO> result = null;
		  List<FileVO> attachResult = new ArrayList<>();
		  List<String> fileIdList = new ArrayList<>();
		  FileVO Thumbfvo = null;
		  FileVO fvo = null;
	  
		  String uploadFolder = "";
		  String image = "";
		  String imageFile = "";
		  String atchFileId = "";
		  String thumbatchFileId = "";
		  
		  final Map<String, MultipartFile> files = multiRequest.getFileMap();
		  System.out.println(")))))))))))))) = " +  files.size());
		  System.out.println(bbVO);
		if(!files.isEmpty()){ 
			  result = fileUtil.parseFileInf(files, "MAIN_",0, "", uploadFolder);
			  System.out.println(")))))))))))))) = " +  result.size());
			  for(int i = 0; i < 3; i++)
				  fileIdList.add("");
		  	//atchFileId = fileMngService.insertFileInfs(result); 

			for (FileVO fvvvo : result) {
				System.out.println("*******************");
				System.out.println(fvvvo);
				System.out.println("*******************");
				if(fvvvo.getFieldName().equals("file_1")) {
					fvvvo.setAtchFileId(mainImg1IdgenService.getNextStringId());
					thumbatchFileId = fileMngService.insertFileOne(fvvvo);
					fileIdList.add(0,thumbatchFileId);
					
				}else if(fvvvo.getFieldName().equals("file_2")) {
					fvvvo.setAtchFileId(mainImg2IdgenService.getNextStringId());
					thumbatchFileId = fileMngService.insertFileOne(fvvvo);
					fileIdList.add(1,thumbatchFileId);
					
				}else if(fvvvo.getFieldName().equals("file_3")) {
					fvvvo.setAtchFileId(mainImg3IdgenService.getNextStringId());
					thumbatchFileId = fileMngService.insertFileOne(fvvvo);
					fileIdList.add(2,thumbatchFileId);
				}

				fileIdList.add(thumbatchFileId);
				//pVO.setFileId(thumbatchFileId);
			} 
		}

		System.out.println(fileIdList);
		bannerVO bVO = new bannerVO(fileIdList.get(0),fileIdList.get(1),fileIdList.get(2));
	
		bannerService.bannerInsert(bVO,request);
		

		
		
		return "/mng/banner/bannerInsertView";
	}
}
