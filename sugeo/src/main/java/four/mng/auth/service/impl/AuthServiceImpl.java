package four.mng.auth.service.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import egovframework.com.cmm.ComDefaultVO;
import egovframework.com.cmm.service.impl.WorkLogDAO;
import egovframework.com.cmm.util.EgovHttpRequestHelper;
import egovframework.com.utl.sim.service.EgovFileScrty;
import four.mng.auth.service.AuthService;
import four.mng.auth.service.AuthVO;

@Service("authService")
public class AuthServiceImpl implements AuthService {

	@Resource(name = "authDAO")
	private AuthDAO authDAO;
	
	@Resource(name = "WorkLogDAO")
	private WorkLogDAO workLogDAO;
	
	@Override
	public List<AuthVO> selectAuthorList(ComDefaultVO comVO)
			throws Exception {
		return authDAO.selectAuthorList(comVO);
	}

	@Override
	public int selectAuthorListCnt(ComDefaultVO comVO) throws Exception {

		return authDAO.selectAuthorListCnt(comVO);
	}

	@Override
	public int selectMaxUniqId() throws Exception {
		
		return authDAO.selectMaxUniqId();
	}

	@Override
	public void insertAuthorUser(AuthVO aVO, HttpServletRequest request) throws Exception {
		
		// 1. 입력한 비밀번호를 암호화한다.
		String enpassword = EgovFileScrty.encryptPassword(aVO.getPswd(), aVO.getUserId());
		aVO.setPswd(enpassword);
		request.setAttribute("logType", "insert");
		workLogDAO.insertWorkLog(request);
		authDAO.insertAuthorUser(aVO);
	}

	@Override
	public AuthVO selectAuthorUser(String uniqId) throws Exception {
		return authDAO.selectAuthorUser(uniqId);
	}
	
	@Override
	public int updateAuthorCheck(AuthVO aVO, HttpServletRequest request) throws Exception {
		// 아이디 변경을 위한 로직
		if(!aVO.getUserId().equals(aVO.getTempId())){
			aVO.setUserId(aVO.getTempId());
		}
		// 1. 입력한 비밀번호를 암호화한다.
		String enpassword = EgovFileScrty.encryptPassword(aVO.getNowPswd(), aVO.getUserId());
		aVO.setNowPswd(enpassword);
		
		return authDAO.updateAuthorCheck(aVO);
	}

	@Override
	public void updateAuthorUser(AuthVO aVO, HttpServletRequest request) throws Exception {
		/*
		// 비밀번호가 입력되지 않은경우 기존의 비밀번호를 유지한다.
		if(!"".equals(aVO.getPswd()) || "00000000".equals(aVO.getPswd())) {
			
			// 1. 입력한 비밀번호를 암호화한다. 비밀번호 변경시 
			System.out.println("입력한 비밀번호를 암호화한다. 비밀번호 변경시 ");
			String enpassword = EgovFileScrty.encryptPassword(aVO.getPswd(), aVO.getUserId());
			aVO.setPswd(enpassword);
			System.out.println("비밀번호가 빈값이 아니거나 0000000일때 ");
			request.setAttribute("logType", "update");
			workLogDAO.insertWorkLog(request);
			authDAO.updateAuthorUserPw(aVO);
		} else {
			
			// 1. 기존 비밀번호와 아이디를 암호화한다.
			System.out.println("기존 비밀번호와 아이디를 암호화한다. ");
			request.setAttribute("logType", "update");
			request.setAttribute("userId", aVO.getUserId());
			workLogDAO.insertWorkLog(request);
			authDAO.updateAuthorUser(aVO);
		}
				*/
		// 비밀번호가 변경된 경우 
		if(!"".equals(aVO.getPswd())) {
			
			String enpassword = EgovFileScrty.encryptPassword(aVO.getPswd(), aVO.getUserId());
			aVO.setPswd(enpassword);
			System.out.println("비밀번호가 빈값이 아니거나 0000000일때 ");
			request.setAttribute("logType", "update");
			workLogDAO.insertWorkLog(request);
			authDAO.updateAuthorUserPw(aVO);
			
		}else {	//비밀번호가 변경되지 않은 경우
			
			System.out.println("기존 비밀번호와 아이디를 암호화한다. ");
			request.setAttribute("logType", "update");
			workLogDAO.insertWorkLog(request);
			authDAO.updateAuthorUser(aVO);
		}
	}
	
	@Override
	public void deleteAuthorUser(String uniqId, HttpServletRequest request) throws Exception {
		request.setAttribute("logType", "delete");
		workLogDAO.insertWorkLog(request);
		authDAO.deleteAuthorUser(uniqId);
	}

	@Override
	public List<AuthVO> selectAccessUserList(ComDefaultVO comVO)
			throws Exception {
		return authDAO.selectAccessUserList(comVO);
	}

	@Override
	public int selectAccessUserListCnt(ComDefaultVO comVO) throws Exception {
		
		return authDAO.selectAccessUserListCnt(comVO);
	}
	
	@Override
	public List<AuthVO> selectWorkLogList(ComDefaultVO comVO) throws Exception {
		return authDAO.selectWorkLogList(comVO);
	}
	
	@Override
	public int selectWorkLogListCnt(ComDefaultVO comVO) throws Exception {
		return authDAO.selectWorkLogListCnt(comVO);
	}
	@Override
	public AuthVO selectWorkLogOneList(String logId) throws Exception {
		return authDAO.selectWorkLogOneList(logId);
	}
	
	@Override
	public int idCheck(String userId) throws Exception {
		
		return authDAO.idCheck(userId);
	}
	
}
