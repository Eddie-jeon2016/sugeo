/**
 * 개요
 * - 메인화면이미지에 대한 DAO 클래스를 정의한다.
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

import org.springframework.stereotype.Repository;

import egovframework.com.cmm.service.impl.EgovComAbstractDAO;
import four.com.bbs.service.BbsDefaultVO;
import four.mng.pop.service.PopManageVO;



@Repository("popManageDAO")
public class PopManageDAO extends EgovComAbstractDAO {
	
	public List<PopManageVO> selectPopManageList(PopManageVO searchVO) throws Exception {
		
		return (List<PopManageVO>) list("PopManageDAO.selectPopManageList",searchVO);
		
	}
	
public int selectPopManageListCnt() throws Exception {
		
		return  (Integer)select("PopManageDAO.selectPopManageListCnt");
		
	}
	
	public void insertPopManage(PopManageVO pVO) throws Exception {
		
		insert("PopManageDAO.insertPopManage", pVO);
		
	}
	
	public void updatePopManage(PopManageVO pVO) throws Exception {
		
		update("PopManageDAO.updatePopManage", pVO);
		
	}
	
	public void deletePopManage(String popupId) throws Exception {
		
		delete("PopManageDAO.deletePopManage", popupId);
		
	}
	
	public PopManageVO selectPopManage(String popupId) throws Exception {
		
		return (PopManageVO) select("PopManageDAO.selectPopManage", popupId);
		
	}
	
	public List<PopManageVO> selectPopList() throws Exception {
		
		return (List<PopManageVO>) list("PopManageDAO.selectPopList");
		
	}
	
	public int updateFileId(String fileId) throws Exception {
		return update("PopManageDAO.updateFileId", fileId);
	}
}
