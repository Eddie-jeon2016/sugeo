package four.mng.press.service.impl;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import egovframework.com.cmm.service.impl.WorkLogDAO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import four.mng.press.sevice.PressContentDefaultVO;
import four.mng.press.sevice.PressContentService;
import four.mng.press.sevice.PressContentVO;

@Service("PressContentService")
public class PressContentServiceImpl extends EgovAbstractServiceImpl implements PressContentService {

	@Resource(name = "PressContentDAO")
	private PressContentDAO presscontentDAO;
	
	@Resource(name = "egovPressContentIdGnrService")
    private EgovIdGnrService idgenService;
	
	@Resource(name = "WorkLogDAO")
	private WorkLogDAO workMngDAO;
	
	/**
     * 게시판 리스트를 불러온다
     */
	@Override
	public List<PressContentVO> selectPressContentList(
			PressContentDefaultVO searchVO) throws Exception {
	
		return presscontentDAO.selectPressContentList(searchVO);
	}

	/**
	 * 게시판 목록 갯수를 조회한다
	 */
	@Override
	public Integer selectPressContentListCnt(
			PressContentDefaultVO searchVO) throws Exception {
		return presscontentDAO.selectPressContentListCnt(searchVO);
	}

	/**
	 * 언론보도자료를 등록한다
	 */
	@Override
	public int insertPressContent(PressContentVO pressContentVO, HttpServletRequest request)throws Exception {
		request.setAttribute("logType", "insert");
		request.setAttribute("userId", pressContentVO.getRegId());
		workMngDAO.insertWorkLog(request);
		
		pressContentVO.setPcId(idgenService.getNextStringId());
		return presscontentDAO.insertPressContent(pressContentVO);
	}

	/**
	 * 언론보도자료를  수정을 위해 리스트를 조회한다.
	 */
	@Override
	public PressContentVO selectPressContentOneList(String pcId)
			throws Exception {		
		return presscontentDAO.selectpressContentOneList(pcId);
	}

	/**
	 * 언론보도자료 수정한다
	 */
	@Override
	public int updatePressContent(PressContentVO pressContentVO, HttpServletRequest request)
			throws Exception {
		request.setAttribute("logType", "update");
		request.setAttribute("userId", pressContentVO.getModId());
		workMngDAO.insertWorkLog(request);
		
		return presscontentDAO.updatePressContent(pressContentVO);
	}

	/**
	 * 언론보도자료를 삭제한다
	 */
	@Override
	public int deletePressContent(String deleteId, HttpServletRequest request)
			throws Exception {
		HashMap<String,Object> result = new HashMap<String,Object>();
		request.setAttribute("logType", "delete");
		workMngDAO.insertWorkLog(request);
		
		return presscontentDAO.deletePressContent(deleteId);
	}


	
}
