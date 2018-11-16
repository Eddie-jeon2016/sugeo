package four.com.evnet.service.imple;

import java.util.Enumeration;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.util.UrlPathHelper;

import egovframework.com.cmm.WorkLogVO;
import egovframework.com.cmm.service.impl.WorkLogDAO;
import egovframework.com.cmm.util.EgovHttpRequestHelper;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import four.com.evnet.service.EventDefaultVO;
import four.com.evnet.service.EventService;
import four.com.evnet.service.EventVO;
import four.mng.code.service.SubCodeManageVO;

@Service("EventService")
public class EventServiceImpl extends EgovAbstractServiceImpl implements EventService{

	@Resource(name = "EventDAO")
	private EventDAO eventDAO;
	
	@Resource(name = "WorkLogDAO")
	private WorkLogDAO workLogDAO;
	
	@Resource(name = "WorkLogDAO")
	private WorkLogDAO workMngDAO;

	@Override
	public int insertEvent(EventVO eventVO, HttpServletRequest request) throws Exception {
		
		WorkLogVO workLogVO = new WorkLogVO();
		workLogVO.setUserId(eventVO.getUserId());
		request.setAttribute("logType", "insert");
		workLogDAO.insertWorkLog(request);
		
		return eventDAO.insertEvent(eventVO);
	}
	
	@Override
	public List<EventVO> eventList(EventDefaultVO searchVO) throws Exception {
		return eventDAO.eventList(searchVO);
	}


	@Override
	public int eventListCnt() throws Exception {
		return eventDAO.eventListCnt();
	}


	@Override
	public EventVO selectEvent(String eventNo) throws Exception {

		return eventDAO.selectEvent(eventNo);
	}


	@Override
	public List<SubCodeManageVO> subCodeList() throws Exception {

		return eventDAO.subCodeList();
	}

	@Override
	public int eventDelete(String eventNo, HttpServletRequest request) throws Exception {
		request.setAttribute("logType", "delete");
		workMngDAO.insertWorkLog(request);
		
		return eventDAO.deleteEvent(eventNo);
	}

	@Override
	public int getNextEvent_no() throws Exception {
		
		return eventDAO.getNextEvent_no();
	}

	@Override
	public int updateEvent(EventVO eventVO, HttpServletRequest request) throws Exception {
		request.setAttribute("logType", "update");
		//workMngDAO.insertWorkLog(request);
		return eventDAO.updateEvent(eventVO);
	}

	@Override
	public int checkYCnt() throws Exception {
		
		return eventDAO.checkYCnt();
	}

	@Override
	public int checkOpenYnCnt(String eventType) throws Exception {

		return eventDAO.checkOpenYnCnt(eventType);
	}

	@Override
	public int updateRecentEvent(EventVO eventVO, HttpServletRequest request) throws Exception {
		request.setAttribute("logType", "update");
		workMngDAO.insertWorkLog(request);
		
		return eventDAO.updateRecentEvent(eventVO);
	}

	@Override
	public int updateEventTypeValueN(String eventType, HttpServletRequest request) throws Exception {
		request.setAttribute("logType", "update");
		
		workMngDAO.insertWorkLog(request);
			
		return eventDAO.updateEventTypeValueN(eventType);
	}

	@Override
	public EventVO selectEventOne(EventVO eventVO) throws Exception {
		
		return eventDAO.selectEventOne(eventVO);
	}


	

	
}
