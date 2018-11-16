package four.com.bbs.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.com.cmm.service.impl.EgovComAbstractDAO;
import four.com.bbs.service.BbsManageVO;
   
/**
 * 게시판 속성정보 관리를 위한 데이터 접근 클래스
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
@Repository("BbsManageDAO")
public class BbsManageDAO extends EgovComAbstractDAO {
 
    /**
     * 등록된 게시판 속성정보를 삭제한다.
     * 
     * @param BbsManageVO
     */
    public void deleteBbsManage(int bbs_id) throws Exception {
    	update("BbsManageDAO.deleteBbsManage", bbs_id);
    }

    /**
     * 신규 게시판 속성정보를 등록한다.
     * 
     * @param BbsManageVO
     */
    public int insertBbsManage(BbsManageVO bbsManageVO) throws Exception {
    	return insert("BbsManageDAO.insertBbsManage", bbsManageVO);
    }
 
    /**
     * 게시판 속성정보 한 건을 상세조회 한다.
     * 
     * @param BbsManageVO
     */
    public BbsManageVO selectBbsManage(BbsManageVO bbsManageVO) throws Exception {
    	return (BbsManageVO)select("BbsManageDAO.selectBbsManage", bbsManageVO);
    }

    /**
     * 게시판 속성정보 목록을 조회한다.
     * 
     * @param BbsManageVO
     */
    @SuppressWarnings("unchecked")
    public List<BbsManageVO> selectBbsManageList(BbsManageVO bbsManageVO) throws Exception {
    	return (List<BbsManageVO>)list("BbsManageDAO.selectBbsManageList", bbsManageVO);
    }
    /**
     * 게시판 속성정보 목록을 조회한다.
     * 
     * @param BbsManageVO
     */
    @SuppressWarnings("unchecked")
    public List<BbsManageVO> selectBbsListbyBbsTyCode(String bbs_ty_code) throws Exception {
    	return (List<BbsManageVO>)list("BbsManageDAO.selectBbsListbyBbsTyCode", bbs_ty_code);
    }
    
    /**
     * 게시판 속성정보 목록 숫자를 조회한다
     * 
     * @param vo
     * @return
     * @throws Exception
     */          
    public int selectBbsManageListCnt(BbsManageVO bbsManageVO) throws Exception {
    	return (Integer)select("BbsManageDAO.selectBbsManageListCnt", bbsManageVO);
    }

    /**
     * 게시판 속성정보를 수정한다.
     * 
     * @param BbsManageVO
     */
    public void updateBbsManage(BbsManageVO bbsManageVO) throws Exception {
    	update("BbsManageDAO.updateBbsManage", bbsManageVO);
    }

	public int getNextBbs_id() {
		return (Integer)select("BbsManageDAO.selectNextBbs_id");
	}
	
	//미디어카페에서 사용중이지 않은 게시판인것만 구한다.
    @SuppressWarnings("unchecked")
    public List<BbsManageVO> selectBbsManageListByTyCode(String bbs_ty_code) throws Exception {
    	return (List<BbsManageVO>)list("BbsManageDAO.selectBbsManageListByTyCode", bbs_ty_code);
    }
	
	
}
