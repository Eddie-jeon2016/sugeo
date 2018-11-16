package four.com.bbs.service.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import egovframework.com.cmm.service.impl.WorkLogDAO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import four.com.bbs.service.BbsDefaultVO;
import four.com.bbs.service.BbsManageService;
import four.com.bbs.service.BbsManageVO;
import four.com.bbs.service.BbsVO;

/**
 * 게시판 속성관리를 위한 서비스 구현 클래스
 * @author 공통컴포넌트개발팀 한성곤
 * @since 2009.08.25
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일      수정자           수정내용
 *  -------       --------    ---------------------------
 *   2009.08.25  한성곤          최초 생성
 *
 * </pre>
 */
@Service("BbsManageService")
public class BbsManageServiceImpl extends EgovAbstractServiceImpl implements BbsManageService {

    @Resource(name = "BbsManageDAO")
    private BbsManageDAO manageDAO;
    
    @Resource(name = "WorkLogDAO")
   	private WorkLogDAO workLogDAO;

    /**
     * 등록된 게시판 속성정보를 삭제한다.
     */
    @Override
	public void deleteBbsManage(int bbs_id, HttpServletRequest request) throws Exception {
    	
    	request.setAttribute("logType", "delete");
    	workLogDAO.insertWorkLog(request);
		manageDAO.deleteBbsManage(bbs_id);
    }

    /**
     * 신규 게시판 속성정보를 생성한다.
     */
    @Override
	public int insertBbsManage(BbsManageVO bbsManageVO, HttpServletRequest request) throws Exception {
		//String bbsId = idgenService.getNextStringId();
	    int bbs_id = manageDAO.getNextBbs_id();
		bbsManageVO.setBbsId(bbs_id);
		request.setAttribute("logType", "insert");
		request.setAttribute("userId", bbsManageVO.getregId());
		workLogDAO.insertWorkLog(request);
		manageDAO.insertBbsManage(bbsManageVO);

		return bbs_id;
    }

    /**
     * 게시판 속성정보 한 건을 상세조회한다.
     */
    @Override
	public BbsManageVO selectBbsManage(BbsManageVO bbsManageVO) throws Exception {
    	return manageDAO.selectBbsManage(bbsManageVO);
    }

    /**
     * 게시판 속성 정보의 목록을 조회 한다.
     */
	@Override
	public List<BbsManageVO> selectBbsManageList(BbsManageVO bbsManageVO, HttpServletRequest request) throws Exception {
		/*request.setAttribute("logType", "select");
    	request.setAttribute("userId", bbsManageVO.getregId()+"");
		workLogDAO.insertWorkLog(request);*/
		return manageDAO.selectBbsManageList(bbsManageVO);
	}
    /**
     * 일반회원 총 갯수를 조회한다.
     * @param userSearchVO 검색조건
     * @return 일반회원총갯수(int) 
     */
	@Override
	public Integer selectBbsManageListCnt(BbsManageVO bbsManageVO) throws Exception {
		return manageDAO.selectBbsManageListCnt(bbsManageVO);
	}	
    /**
     * 게시판 속성 정보의 목록을 조회 한다.
     */
    @Override
	public List<BbsManageVO> selectBbsListbyBbsTyCode(String bbs_ty_code, HttpServletRequest request) throws Exception {
    	request.setAttribute("logType", "select");
		workLogDAO.insertWorkLog(request);
		List<BbsManageVO> result = manageDAO.selectBbsListbyBbsTyCode(bbs_ty_code);
		return result;
    }
    
    
    /**
     * 게시판 속성정보를 수정한다.
     */
    @Override
	public void updateBbsManage(BbsManageVO bbsManageVO, HttpServletRequest request) throws Exception {
    	request.setAttribute("logType", "update");
    	request.setAttribute("userId", bbsManageVO.getregId());
		workLogDAO.insertWorkLog(request);
    	manageDAO.updateBbsManage(bbsManageVO);
    }
    
	//미디어카페에서 사용중이지 않은 게시판인것만 구한다.
    @Override
	public List<BbsManageVO> selectBbsManageListByTyCode(String bbs_ty_code, HttpServletRequest request) throws Exception {
		List<BbsManageVO> result = manageDAO.selectBbsManageListByTyCode(bbs_ty_code);
		return result;
    }

}
