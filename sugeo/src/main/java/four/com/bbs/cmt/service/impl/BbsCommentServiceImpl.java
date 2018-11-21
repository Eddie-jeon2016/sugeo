package four.com.bbs.cmt.service.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import egovframework.com.cmm.service.impl.WorkLogDAO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import four.com.bbs.cmt.service.BbsCommentDefaultVO;
import four.com.bbs.cmt.service.BbsCommentService;
import four.com.bbs.cmt.service.BbsCommentVO;
import four.com.bbs.cmt.service.impl.BbsCommentDAO;

/**
 * 게시판 속성관리를 위한 서비스 구현 클래스
 * @author 조은태
 * @since 2013.09.02
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일     	 	수정자           수정내용
 *  -------      	 		--------    ---------------------------
 *   2013.09.02 	 조은태          최초 생성
 *
 * </pre>
 */
@Service("bbsCommentService")
public class BbsCommentServiceImpl extends EgovAbstractServiceImpl implements BbsCommentService {

    @Resource(name = "BbsCommentDAO")
    private BbsCommentDAO bbsCommentDAO;
    
    @Resource(name = "WorkLogDAO")
   	private WorkLogDAO workLogDAO;


    /**
     * 댓글 리스트 뽑아온다.
     */  
    @Override
	public List<BbsCommentVO> selectBbsCommentList(BbsCommentDefaultVO searchVO) throws Exception {
		return bbsCommentDAO.selectBbsCommentList(searchVO);
	}
    
    /**
     * 댓글 정보를 가져온다.
     */  
    @Override
	public BbsCommentVO selectCmtAnswer(BbsCommentDefaultVO searchVO) throws Exception {
		return bbsCommentDAO.selectCmtAnswer(searchVO);
	}
    
    /**
     * 댓글 리스트 총 갯수를 구한다.
     */
    @Override
	public int selectBbsCommentListCnt(BbsCommentDefaultVO searchVO) throws Exception {
    	return bbsCommentDAO.selectBbsCommentListCnt(searchVO);
    }

    /**
     * 신규 게시판 속성정보를 생성한다.
     */
    @Override 
	public void insertBbsComment(BbsCommentVO bbsCommentVO, HttpServletRequest request) throws Exception {
    	
    	request.setAttribute("logType", "insert");
    	request.setAttribute("userId", bbsCommentVO.getRegId());
    	
    	String answer_no = bbsCommentDAO.getNextBbsAnswer_no();
	    bbsCommentVO.setAnswerNo(answer_no);
	    bbsCommentDAO.insertBbsComment(bbsCommentVO);
    }
    // 그룹번호 맥스값 +1 쿼리
    @Override
    public int insertCommentAnswer(BbsCommentVO bbsCommentVO, HttpServletRequest request) throws Exception {
    	
    	request.setAttribute("logType", "insert");
    	request.setAttribute("userId", bbsCommentVO.getRegId());
    	
    	int answer_gno = bbsCommentDAO.getNextBbsAnswer_gno();
        bbsCommentVO.setAnswerGno(answer_gno);
        return answer_gno;
     }
    
    /**
     * 게시판 속성정보를 수정한다.
     */
    @Override
	public void updateBbsComment(BbsCommentVO bbsCommentVO, HttpServletRequest request) throws Exception {
    	
    	request.setAttribute("logType", "update");
    	request.setAttribute("userId", bbsCommentVO.getRegId());
    	bbsCommentDAO.updateBbsComment(bbsCommentVO);
    }
    
    /**
     * 게시판 속성정보를 삭제한다.
     */
    @Override
	public void deleteBbsComment(BbsCommentVO bbsCommentVO, HttpServletRequest request) throws Exception {
    	
    	request.setAttribute("logType", "delete");
    	request.setAttribute("userId", bbsCommentVO.getRegId());
    	bbsCommentDAO.deleteBbsComment(bbsCommentVO);
    }
}
