package four.com.evnet.service.imple;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.com.cmm.service.impl.EgovComAbstractDAO;
import four.com.evnet.service.EventDefaultVO;
import four.com.evnet.service.EventVO;
import four.mng.code.service.SubCodeManageVO;

/**  
* @Class Name : EventDAO.java
* @Description : EventDAO Class

* @ author 운영지원팀 이록근
* @ since 2018.08.08
* @ version 1.0
* @ see
*
* << 개정이력 (Modification Information) >>
*
*    수정일    	  수정자                수정내용
*  ----------    ---------            ---------------------------
*  18.08.08   	  이록근 		최초 생성
*  
*  Copyright (C) by MOPAS All right reserved.
*/

@Repository("EventDAO")
public class EventDAO extends EgovComAbstractDAO{
	
	public int insertEvent(EventVO eventVO) throws Exception{
		return insert("EventDAO.eventInsert",eventVO);
	}
	
	public int getNextEvent_no() {
		return (Integer)select("EventDAO.selectNextEvent_no");
	}	
	
	@SuppressWarnings("unchecked")
	public  List<EventVO> eventList(EventDefaultVO searchVO) throws Exception {
		return (List<EventVO>) list("EventDAO.eventList", searchVO);
	}
	
	public  int eventListCnt() throws Exception {
		return  (Integer)selectOne("EventDAO.eventListCnt");
	}
	
	
	public EventVO selectEvent(String eventNo) throws Exception {
		return selectOne("EventDAO.selectEvent", eventNo);
	}
	
	@SuppressWarnings("unchecked")
	public List<SubCodeManageVO> subCodeList() throws Exception {
		return (List<SubCodeManageVO>)list("EventDAO.subCodeList");
	}
	
	public int deleteEvent(String eventNo) throws Exception {
		return update("EventDAO.eventDelete", eventNo);
	}
	
	public int updateEvent(EventVO eventVO) throws Exception {
		return update("EventDAO.eventUpdate", eventVO);
	}
	
	public int checkYCnt() throws Exception {
		int c = selectOne("EventDAO.checkYCnt");
		return c;
	}
	public int checkOpenYnCnt(String eventType)throws Exception {
		return selectOne("EventDAO.checkOpenYnCnt",eventType);
	}
	
	public int updateRecentEvent(EventVO eventVO)throws Exception {
		
		String updateresult = selectOne("EventDAO.updateRecentEvent",eventVO);
		int returnCnt;
		if(updateresult ==null) {
			returnCnt = 0;
		}else {
			returnCnt = 1;
		}
		return returnCnt;
	}
	
	public int updateEventTypeValueN(String eventType) throws Exception {
		return update("EventDAO.eventOpenYnValueSetN",eventType);
	}

	public EventVO selectEventOne(EventVO eventVO) throws Exception {
		return selectOne("EventDAO.selectEventOne", eventVO);
	}
	
	
	

}
