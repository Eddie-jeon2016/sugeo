/** ===============================================
 * Copyright(c) 4DEPTH 2017
 * @fileName MenuService.java
 * @author Chang-il Jeon
 * @since 2017. 2. 7.
 * @version 1.0
 *
 *=================================================
 */
package four.com.evnet.service;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartHttpServletRequest;

import egovframework.com.cmm.ComDefaultVO;
import four.mng.code.service.SubCodeManageVO;

public interface EventService {

	public int insertEvent(EventVO eventVO, HttpServletRequest request) throws Exception;
	
	public int getNextEvent_no() throws Exception;
	
	public List<EventVO> eventList(EventDefaultVO searchVO) throws Exception;

	public int eventListCnt() throws Exception;
	
	public EventVO selectEvent(String eventNo) throws Exception;
	
	public List<SubCodeManageVO> subCodeList() throws Exception;
	
	public int eventDelete(String eventNo, HttpServletRequest request) throws Exception;
	
	public int updateEvent(EventVO eventVO, HttpServletRequest request) throws Exception;
	
	public int checkYCnt() throws Exception;
	
	public int checkOpenYnCnt(String eventType) throws Exception;
	
	public int updateRecentEvent(EventVO eventVO, HttpServletRequest request)throws Exception;
	
	public int updateEventTypeValueN(String eventType, HttpServletRequest request) throws Exception;
	
	public EventVO selectEventOne(EventVO eventVO) throws Exception;
}

