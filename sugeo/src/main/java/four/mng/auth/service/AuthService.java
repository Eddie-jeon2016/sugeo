package four.mng.auth.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import egovframework.com.cmm.ComDefaultVO;

public interface AuthService {
	
	public List<AuthVO> selectAuthorList(ComDefaultVO comVO) throws Exception;
	
	public int selectAuthorListCnt(ComDefaultVO comVO) throws Exception;
	
	public int selectMaxUniqId() throws Exception;
	
	public void insertAuthorUser(AuthVO aVO, HttpServletRequest request) throws Exception;
	
	public AuthVO selectAuthorUser(String uniqId) throws Exception;
	
	public int updateAuthorCheck(AuthVO aVO, HttpServletRequest request) throws Exception;
	
	public void updateAuthorUser(AuthVO aVO, HttpServletRequest request) throws Exception;
	
	public void deleteAuthorUser(String uniqId, HttpServletRequest request) throws Exception;
	
	public List<AuthVO> selectAccessUserList(ComDefaultVO comVO) throws Exception;
	
	public int selectAccessUserListCnt(ComDefaultVO comVO) throws Exception;
	
	public List<AuthVO> selectWorkLogList(ComDefaultVO comVO)throws Exception;
	
	public int selectWorkLogListCnt(ComDefaultVO comVO)throws Exception;
	
	public AuthVO selectWorkLogOneList(String logId)throws Exception;
	
	public int idCheck(String userId) throws Exception;
}
