package four.mng.pop.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import four.com.bbs.service.BbsDefaultVO;

public interface PopManageService {
	
	public List<PopManageVO> selectPopManageList(PopManageVO searchVO) throws Exception;
	
	public int selectPopManageListCnt() throws Exception;
	
	public void insertPopManage(PopManageVO pVO, HttpServletRequest request) throws Exception;
	
	public void updatePopManage(PopManageVO pVO, HttpServletRequest request) throws Exception;
	
	public void deletePopManage(String popupId, HttpServletRequest request) throws Exception;
	
	public PopManageVO selectPopManage(String popupId) throws Exception;
	
	public List<PopManageVO> selectPopList() throws Exception;
	
	public int updateFileId(String fileId) throws Exception;
		
}