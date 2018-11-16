package four.mng.auth.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.sun.star.deployment.thePackageManagerFactory;

import egovframework.com.cmm.ComDefaultVO;
import egovframework.com.cmm.service.impl.EgovComAbstractDAO;
import four.mng.auth.service.AuthVO;

@Repository("authDAO")
public class AuthDAO extends EgovComAbstractDAO {
	
	public List<AuthVO> selectAuthorList(ComDefaultVO comVO) throws Exception {
		
		return (List<AuthVO>) list("AuthDAO.selectAuthorList", comVO);
	}
	
	public int selectAuthorListCnt(ComDefaultVO comVO) throws Exception {
		
		return (Integer) select("AuthDAO.selectAuthorListCnt", comVO);
	}
	
	public int selectMaxUniqId() throws Exception {
		
		return (Integer) select("AuthDAO.selectMaxUniqId");
	}
	
	
	public void insertAuthorUser(AuthVO aVO) throws Exception {
		
		insert("AuthDAO.insertAuthorUser", aVO);
	}
	
	public AuthVO selectAuthorUser(String uniqId) throws Exception {
		
		return (AuthVO) select("AuthDAO.selectAuthorUser", uniqId);
	}
	
	public int updateAuthorCheck(AuthVO aVO) throws Exception {
		
		return (Integer) select("AuthDAO.updateAuthorCheck", aVO);
	}
	
	
	public void updateAuthorUser(AuthVO aVO) throws Exception {
		
		update("AuthDAO.updateAuthorUser", aVO);
	}
	
	public void deleteAuthorUser(String uniqId) throws Exception {
		
		update("AuthDAO.deleteAuthorUser", uniqId);
	}
	
	public List<AuthVO> selectAccessUserList(ComDefaultVO comVO) throws Exception {
	
		return (List<AuthVO>) list("AuthDAO.selectAccessUserList", comVO);
	}
	
	public int selectAccessUserListCnt(ComDefaultVO comVO) throws Exception {
		
		return (Integer) select("AuthDAO.selectAccessUserListCnt", comVO);
	}
	
	public  List<AuthVO> selectWorkLogList(ComDefaultVO comVO)throws Exception{
		return (List<AuthVO>) list("AuthDAO.selectWorkLogList", comVO);
	}
	
	public int selectWorkLogListCnt(ComDefaultVO comVO) throws Exception{
		return (Integer) select("AuthDAO.selectWorkLogListCnt",comVO);
	}
	
	public AuthVO selectWorkLogOneList(String logId)throws Exception{
		return (AuthVO) select("AuthDAO.selectWorkLogOneList", logId);
	}
	
	public int idCheck(String userId) throws Exception {
		return selectOne("AuthDAO.idCheck",userId);
	}
	
	public void updateAuthorUserPw(AuthVO aVO) throws Exception {
		
		update("AuthDAO.updateAuthorUserPw", aVO);
	}
}
