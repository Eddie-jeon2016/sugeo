package four.mng.banner.service.impl;

import javax.annotation.Resource;

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


import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import egovframework.com.cmm.service.impl.WorkLogDAO;
import four.mng.banner.service.bannerService;
import four.mng.banner.service.bannerVO;
import four.mng.code.service.impl.CodeManageDAO;



@Service("bannerService")
public class bannerServiceImpl implements bannerService{

	@Resource(name = "bannerDAO")
	private bannerDAO bannerDAO;
	
	@Resource(name = "WorkLogDAO")
	private WorkLogDAO workMngDAO;

	
	@Override
	public int bannerInsert(bannerVO bVO, HttpServletRequest request) throws Exception {
		request.setAttribute("logType", "insert");
		workMngDAO.insertWorkLog(request);
		
		return bannerDAO.bannerInsert(bVO);
	}

}
