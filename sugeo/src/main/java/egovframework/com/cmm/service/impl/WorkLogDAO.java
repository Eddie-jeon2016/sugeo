/**  
 	* @Class Name : WorkLogVO.java
 	* @Description : WorkLogVO Class

	* @ author 운영지원팀 이록근
	* @ since 2018.08.20
	* @ version 1.0
	* @ see
	*
	* << 개정이력 (Modification Information) >>
	*
	*    수정일    	  수정자                수정내용
	 *  ----------    ---------            ---------------------------
	 *  18.08.120  	  이록근 		최초 생성
	 *  
	 *  Copyright (C) by MOPAS All right reserved.
	 */
package egovframework.com.cmm.service.impl;

import java.util.Enumeration;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Repository;
import org.springframework.web.util.UrlPathHelper;

import egovframework.com.cmm.WorkLogVO;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;


@Repository("WorkLogDAO")
public class WorkLogDAO extends EgovComAbstractDAO {
	
	@Resource(name = "egovworkLogIdGnrService")
	private EgovIdGnrService idgenService;
	
	public int insertWorkLog(HttpServletRequest requset) throws Exception{
		
		WorkLogVO workLogVO = new WorkLogVO();
		
		requset.setCharacterEncoding("UTF-8");
		Enumeration enums = requset.getParameterNames();

		UrlPathHelper urls = new UrlPathHelper(); 
        String url = urls.getOriginatingServletPath(requset); 
		StringBuffer sb = new StringBuffer();
		while(enums.hasMoreElements()) {
			String key = (String)enums.nextElement();
			System.out.println(key);
			String value = (String) requset.getParameter(key);
			sb.append(key +" : "+value+", ");
		}

		String content = sb.toString();
		String userId = (String)requset.getAttribute("userId");

		if(userId == null) {
			workLogVO.setUserId("");			
		}else {			
			workLogVO.setUserId((String)requset.getAttribute("userId"));			
		}
		workLogVO.setContent(content);
		workLogVO.setUrl(url);
		workLogVO.setAccessIp(requset.getRemoteAddr());
		workLogVO.setLogId(idgenService.getNextStringId());
		workLogVO.setLogType((String)requset.getAttribute("logType"));
		return insert("WorkLogDAO.insertWorkLog",workLogVO);
	}

	
	
	
}

