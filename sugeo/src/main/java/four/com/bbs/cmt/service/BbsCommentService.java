package four.com.bbs.cmt.service;

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
public interface BbsCommentService {
	
    /**
     * 댓글 리스트 뽑아온다.
     */
    public List<BbsCommentVO> selectBbsCommentList(BbsCommentDefaultVO searchVO) throws Exception;

    /**
     * 댓글 정보를 가져온다.
     */
    public BbsCommentVO selectCmtAnswer(BbsCommentDefaultVO searchVO) throws Exception;
    
    /**
     * 댓글 리스트 총 갯수를 구한다.
     */
    public int selectBbsCommentListCnt(BbsCommentDefaultVO searchVO) throws Exception;
    
    /**
     * 신규 댓글 정보를 입력한다.
     * 
     * @param ComDefaultVO
     */
	public void insertBbsComment(BbsCommentVO bbsCommentVO, HttpServletRequest request) throws Exception;

    /**
     * 댓글 정보를 수정한다.
     * 
     * @param ComDefaultVO
     */
	public void updateBbsComment(BbsCommentVO bbsCommentVO, HttpServletRequest request) throws Exception;

	
    /**
     * 댓글 정보를 삭제한다.
     * 
     * @param ComDefaultVO
     */
	public void deleteBbsComment(BbsCommentVO bbsCommentVO, HttpServletRequest request) throws Exception;

	
	int insertCommentAnswer(BbsCommentVO bbsCommentVO, HttpServletRequest request) throws Exception;



	

    
    
    
    /**
     * 상세조회한 게시물을 삭제한다.
     * 
     * @param ntt_id
     * @return 
     */
	//public void deleteBbsComment(int ntt_id) throws Exception;
	
    /**
     * 상세조회한 게시물을 수정한다.
     * 
     * @param BbsVO
     * @return 
     */
	//public void updateBbsComment(BbsCommentVO bbsCommentVO) throws Exception;
    
    
}
