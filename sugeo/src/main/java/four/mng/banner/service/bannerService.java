package four.mng.banner.service;

import javax.servlet.http.HttpServletRequest;

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

public interface bannerService {

	public int bannerInsert(bannerVO bVO, HttpServletRequest request) throws Exception;
}
