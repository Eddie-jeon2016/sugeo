/**
 * 개요
 * - 메인화면이미지에 대한 ServiceImpl 클래스를 정의한다.
 * 
 * 상세내용
 * - 메인화면이미지에 대한 등록, 수정, 삭제, 조회, 반영확인 기능을 제공한다.
 * - 메인화면이미지의 조회기능은 목록조회, 상세조회로 구분된다.
 * @author 이문준
 * @version 1.0
 * @created 03-8-2009 오후 2:08:58
 */

package four.mng.pop.service.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import egovframework.com.cmm.service.impl.WorkLogDAO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import four.com.bbs.service.BbsDefaultVO;
import four.mng.pop.service.PopManageService;
import four.mng.pop.service.PopManageVO;



@Service("popManageService")
public class PopManageServiceImpl extends EgovAbstractServiceImpl implements PopManageService {

	@Resource(name = "popManageDAO")
    private PopManageDAO popManageDAO;

	@Resource(name = "egovPopupIdGnrService")
	private EgovIdGnrService idGenService;
	
	@Resource(name = "WorkLogDAO")
	private WorkLogDAO workMngDAO;
	
	@Override
	public List<PopManageVO> selectPopManageList(PopManageVO searchVO) throws Exception {
		return popManageDAO.selectPopManageList(searchVO);
	}
	
	@Override
	public int selectPopManageListCnt() throws Exception {
		
		return popManageDAO.selectPopManageListCnt();
	}

	@Override
	public void insertPopManage(PopManageVO pVO, HttpServletRequest request) throws Exception {
		request.setAttribute("logType", "insert");
		workMngDAO.insertWorkLog(request);
		
		String popupId = idGenService.getNextStringId();
		pVO.setPopupId(popupId);
		
		popManageDAO.insertPopManage(pVO);		
	}

	@Override
	public void updatePopManage(PopManageVO pVO, HttpServletRequest request) throws Exception {
		request.setAttribute("logType", "update");
		workMngDAO.insertWorkLog(request);

		popManageDAO.updatePopManage(pVO);
	}

	@Override
	public void deletePopManage(String popupId, HttpServletRequest request) throws Exception {
		request.setAttribute("logType", "delete");
		workMngDAO.insertWorkLog(request);
		
		popManageDAO.deletePopManage(popupId);
	}

	@Override
	public PopManageVO selectPopManage(String popupId) throws Exception {
		return popManageDAO.selectPopManage(popupId);
	}

	@Override
	public List<PopManageVO> selectPopList() throws Exception {
		return popManageDAO.selectPopList();
	}

	@Override
	public int updateFileId(String fileId) throws Exception {
		return popManageDAO.updateFileId(fileId);
	}

}