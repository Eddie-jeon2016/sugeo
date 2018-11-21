package four.com.bbs.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.com.cmm.service.impl.EgovComAbstractDAO;
import four.com.bbs.service.BbsDefaultVO;
import four.com.bbs.service.BbsVO;
   
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
@Repository("BbsDAO")
public class BbsDAO extends EgovComAbstractDAO {
     /**
     * 신규 게시판 속성정보를 등록한다.
     * 
     * @param BbsManageVO
     */
    public int insertBbs(BbsVO bbsVO) throws Exception {
    	return insert("BbsDAO.insertBbs", bbsVO);
    }
    /**
     * 게시물ID 를 1씩 증가시킨다.
     * 
     * @param vo
     * @return
     * @throws Exception
     */
    public String getNextNtt_id() {
		return (String)select("BbsDAO.selectNextNtt_id");
	}	
    /**
     * 게시물의 그룹번호ID 를 1씩 증가시킨다.
     * 
     * @param vo
     * @return
     * @throws Exception
     */
    public String getNextNtt_no() {
    	
    	return (String)select("BbsDAO.selectNextNtt_no");
	}	
    /**
     * 게시판 속성정보 목록을 조회한다.
     * 
     * @param BbsManageVO
     */
    @SuppressWarnings("unchecked")
    public List<BbsVO> selectBbsList(BbsDefaultVO searchVO) throws Exception {
    		return (List<BbsVO>)list("BbsDAO.selectBbsList", searchVO);
    	
    }
    /**
     * 게시판 속성정보 목록 숫자를 조회한다
     * 
     * @param vo
     * @return
     * @throws Exception
     */
    public int selectBbsListCnt(BbsDefaultVO searchVO) throws Exception {
    	return (Integer)select("BbsDAO.selectBbsListCnt", searchVO);
    }
    
    /**
     * 페이지카운트 계산용
     * @param searchVO
     * @return
     * @throws Exception
     */
    public int selectBbsListReplyCnt(BbsDefaultVO searchVO) throws Exception {
    	return (Integer)select("BbsDAO.selectBbsListReplyCnt", searchVO);
    } 
    
    /**
     * 공지글 select
     * @param searchVO
     * @return
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
	public List<BbsVO> selectBbsListnoticeYn(BbsDefaultVO searchVO) throws Exception {
    	return (List<BbsVO>)list("BbsDAO.selectBbsListnoticeYn", searchVO);
    }
    
    /**
     * 게시물 한 건에 대해 상세 조회 한다.
     * 
     * @param SupportVO
     * @return 
     */
    public BbsVO selectBbs(String ntt_id){
        return (BbsVO) select("BbsDAO.selectBbs", ntt_id);
    }
    
    /**
     * 상세조회한 게시물을 삭제한다.
     * 
     * @param ntt_id
     * @return 
     */
	public void deleteBbs(Integer ntt_id) {
		delete("BbsDAO.deleteBbs", ntt_id);
	}
	
	/**
	 * 게시물 여러개를 삭제한다.
	 * 
	 * @param ntt_id
	 * @return 
	 */
	public void deleteBbs(HashMap<String, String> param) {
		delete("BbsDAO.deleteBbs", param);
	}
	
	/**
     * 상세조회한 게시물을 완전삭제한다.
     * 
     * @param ntt_id
     * @return 
     */
	public void deleteRealBbs(Integer ntt_id) {
		delete("BbsDAO.deleteRealBbs", ntt_id);
	}
	
    /**
     * 게시물 상세정보를 수정한다.
     */
	public void updateBbs(BbsVO bbsVO) throws Exception {
		update("BbsDAO.updateBbs", bbsVO);
    }
	
	
	public void updateRdcnt(String ntt_id) {
		update("BbsDAO.updateRdCnt", ntt_id);
	}
	
	/**
     * 게시판 속성 정보의 목록을 조회 한다.
     * 
     * @param PdMovieDefaultVO
     * @return List<BbsVO>
     */
    @SuppressWarnings("unchecked")
    public List<BbsVO> selectBbsByUserList(BbsDefaultVO searchVO) throws Exception {
    	return (List<BbsVO>)list("BbsDAO.selectBbsListByUser", searchVO);
    }
    /**
     * 게시물 정보의 목록의 갯수을 조회 한다.
     * 
     * @param PdMovieDefaultVO
     * @return int
     */   
    public int selectBbsListByUserCnt(BbsDefaultVO searchVO) throws Exception {
    	return (Integer)select("BbsDAO.selectBbsListByUserCnt", searchVO);
    }
    
    public List<BbsVO> selectBbsForMain(BbsDefaultVO searchVO) throws Exception{
    	return (List<BbsVO>)list("BbsDAO.selectBbsForMain",searchVO);
    }
    
    /**
     * 게시판 속성정보 목록을 조회한다.
     * 
     * @param BbsManageVO
     */
    @SuppressWarnings("unchecked")
    public List<BbsVO> selectBbsListforSearch(BbsDefaultVO searchVO) throws Exception {
    		return (List<BbsVO>)list("BbsDAO.selectBbsListforSearch", searchVO);
    	
    }
    /**
     * 게시판 속성정보 목록 숫자를 조회한다
     * 
     * @param vo
     * @return
     * @throws Exception
     */
    public int selectBbsListCntforSearch(BbsDefaultVO searchVO) throws Exception {
    	return (Integer)select("BbsDAO.selectBbsListCntforSearch", searchVO);
    }
    
    /**
     * 게시판 다음글을 조회한다
     * 
     * @param vo
     * @return
     * @throws Exception
     */
    public BbsVO nextSelectBoardArticle(BbsVO bbsVO) throws Exception {
    	return (BbsVO) select("BbsDAO.nextSelectBoardArticle", bbsVO);
    }
    
    /**
     * 게시판 이전글을 조회한다
     * 
     * @param vo
     * @return
     * @throws Exception
     */
    public BbsVO prevSelectBoardArticle(BbsVO bbsVO) throws Exception {
    	return (BbsVO) select("BbsDAO.prevSelectBoardArticle", bbsVO);
    }
    
    /**
     * 게시판(이벤트공지)의 공지글을 가져온다
     * 
     * @param vo
     * @return
     * @throws Exception
     */
    public List<BbsVO> selectEvnetNoticeYList(BbsDefaultVO bbsVO) throws Exception {
    	return  (List<BbsVO>)select("BbsDAO.selectEvnetNoticeYList");
    }
    
    /**
     * 게시판(이벤트공지)의 일반글을 가져온다
     * 
     * @param vo
     * @return
     * @throws Exception
     */
    public List<BbsVO> selectEvnetNoticeNList(BbsDefaultVO bbsVO) throws Exception {
    	return  (List<BbsVO>)list("BbsDAO.selectEvnetNoticeNList",bbsVO);
    }
    
    /**
     * 게시판(이벤트공지)의 일반글의 갯수
     * 
     * @param vo
     * @return
     * @throws Exception
     */
    public int selectEvnetNoticeNListCnt(BbsDefaultVO bbsVO) throws Exception {
    	return  (Integer)select("BbsDAO.selectEvnetNoticeNListCnt",bbsVO);
    }
    
    /**
     * 이벤트공지 다음글을 조회한다
     * 
     * @param vo
     * @return
     * @throws Exception
     */
    public BbsVO nextSelectEventNotice(BbsVO bbsVO) throws Exception {
    	return (BbsVO) select("BbsDAO.nextSelectEventNotice", bbsVO);
    }
    
    /**
     * 이벤트공지 이전글을 조회한다
     * 
     * @param vo
     * @return
     * @throws Exception
     */
    public BbsVO prevSelectEventNotice(BbsVO bbsVO) throws Exception {
    	return (BbsVO) select("BbsDAO.prevSelectEventNotice", bbsVO);
    }
}
