package four.com.bbs.service;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

/**
 * 게시판 속성관리를 위한 서비스 인터페이스 클래스
 * @ author (주)거산디에스엔 조은태
 * @ since 2013.08.21
 * @ version 1.0
 * @ see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 * @  수정일              수정자               수정내용
 * @ --------------     ---------     -------------------------------
 * @ 2013.08.21      조은태               최초생성
 *
 */
public interface BbsService {
    /**
     * 신규 게시판 속성정보를 생성한다.
     * 
     * @param ComDefaultVO
     */
    public String insertBbs(BbsVO bbsVO, HttpServletRequest request) throws Exception;
    /**
     * 게시판 속성 정보의 목록을 조회 한다.
     * 
     * @param ComDefaultVO
     * @return 
     */
    public List<BbsVO> selectBbsList(BbsDefaultVO searchVO) throws Exception;

    
    public Integer selectBbsListCnt(BbsDefaultVO searchVO) throws Exception;
    
    /**
     *  검색 계산용 리플갯수 
     */
    public Integer selectBbsListReplyCnt(BbsDefaultVO searchVO) throws Exception;
    
    /**
     *  공지글 select 
     */
    public List<BbsVO> selectBbsListnoticeYn(BbsDefaultVO searchVO) throws Exception;
    
    /**
     * 게시물 한 건에 대해 상세 조회 한다.
     * 
     * @param ntt_id
     * @return 
     */
    public BbsVO selectBbs(String ntt_id) throws Exception;
    
    /**
     * 상세조회한 게시물을 삭제한다.
     * 
     * @param ntt_id
     * @return 
     */
	public void deleteBbs(Integer ntt_id, HttpServletRequest request) throws Exception;

	/**
	 * 게시물 여러개를 삭제한다.
	 * 
	 * @param ntt_id
	 * @return 
	 */
	public void deleteBbs(HashMap<String, String> param) throws Exception;

	/**
     * 상세조회한 게시물을 완전삭제한다.
     * 
     * @param ntt_id
     * @return 
     */
	public void deleteRealBbs(Integer ntt_id) throws Exception;
	
    /**
     * 상세조회한 게시물을 수정한다.
     * 
     * @param BbsCommentVO
     * @return 
     */
	public void updateBbs(BbsVO bbsVO, HttpServletRequest request) throws Exception;
	
	
	public void updateRdCnt(String ntt_id) throws Exception;
	
	public String insertNtt_no(BbsVO bbsVO) throws Exception;
	/**
     * 게시판 속성 정보의 목록을 조회 한다.
     * 
     * @param PdMovieDefaultVO
     * @return List<BbsVO>
     */
    public List<BbsVO> selectBbsListByUser(BbsDefaultVO searchVO) throws Exception;
    /**
     * 게시물 정보의 목록의 갯수을 조회 한다.
     * 
     * @param PdMovieDefaultVO
     * @return int
     */    
    public Integer selectBbsListByUserCnt(BbsDefaultVO searchVO) throws Exception;

    /**
	메인화면 KAFA소식
	*/
	public List<BbsVO> selectBbsForMain(BbsDefaultVO searchVO)throws Exception;
    
	
    /**
     * 게시판 속성 정보의 목록을 조회 한다.
     * 
     * @param ComDefaultVO
     * @return 
     */
    public List<BbsVO> selectBbsListforSearch(BbsDefaultVO searchVO) throws Exception;

    
    public Integer selectBbsListCntforSearch(BbsDefaultVO searchVO) throws Exception;
	
    public BbsVO nextSelectBoardArticle(BbsVO bbsVO) throws Exception;
    /**
     * 게시판 이전글을 조회한다
     * 
     * @param vo
     * @return
     * @throws Exception
     */
    public BbsVO prevSelectBoardArticle(BbsVO bbsVO) throws Exception;
    
    public List<BbsVO> selectEvnetNoticeYList(BbsDefaultVO bbsVO) throws Exception;
    
    public List<BbsVO> selectEvnetNoticeNList(BbsDefaultVO bbsVO) throws Exception;
    
    public int selectEvnetNoticeNListCnt(BbsDefaultVO bbsVO) throws Exception;
    
    public BbsVO prevSelectEventNotice(BbsVO bbsVO) throws Exception;
    
    public BbsVO nextSelectEventNotice(BbsVO bbsVO) throws Exception;
}
