package four.com.bbs.cmt.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.com.cmm.service.impl.EgovComAbstractDAO;
import four.com.bbs.cmt.service.BbsCommentDefaultVO;
import four.com.bbs.cmt.service.BbsCommentVO;
   
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
@Repository("BbsCommentDAO")
public class BbsCommentDAO extends EgovComAbstractDAO {
 
    /**
     * 등록된 게시판 속성정보를 삭제한다.
     * 
     * @param asdaas
     */
/*    public void deleteBbsCate(int bbscate_id) throws Exception {
	update("BbsCateDAO.deleteBbsCate", bbscate_id);
    }*/


    

    /**
     * 댓글 리스트 뽑아온다.
     */  
    @SuppressWarnings("unchecked")
	public List<BbsCommentVO> selectBbsCommentList(BbsCommentDefaultVO searchVO) {
		return (List<BbsCommentVO>)list("BbsCommentDAO.selectBbsCommentList", searchVO);
	}
    
    /**
     * 댓글 정보를 가져온다..
     */  
    @SuppressWarnings("unchecked")
	public BbsCommentVO selectCmtAnswer(BbsCommentDefaultVO searchVO) {
    	return (BbsCommentVO)select("BbsCommentDAO.selectCmtAnswer", searchVO);
	}    
    
    /**
     * 댓글 리스트 총 갯수를 구한다.
     */
	public int selectBbsCommentListCnt(BbsCommentDefaultVO searchVO) {
		return (Integer)select("BbsCommentDAO.selectBbsCommentListCnt", searchVO);
	}

    /**
     * 신규 댓글 정보를 등록한다.
     * 
     * @param asdaas
     */
    public int insertBbsComment(BbsCommentVO bbsCommentVO) throws Exception {
	return insert("BbsCommentDAO.insertBbsComment", bbsCommentVO);
    }


    //Answer_no 맥스값 +1
	public String getNextBbsAnswer_no() {
		return (String)select("BbsCommentDAO.selectNextAnswer_no");
	}
	
	//그룹번호 맥스값 +1
	public int getNextBbsAnswer_gno() {
		return (Integer)select("BbsCommentDAO.selectNextAnswer_gno");
	}
	
    /**
     * 댓글 정보를 수정한다.
     * 
     * @param asdaas
     */
	public void updateBbsComment(BbsCommentVO bbsCommentVO) throws Exception {
		update("BbsCommentDAO.updateBbsComment", bbsCommentVO);
	}

    /**
     * 댓글 정보를 삭제한다.
     * 
     * @param asdaas
     */
	public void deleteBbsComment(BbsCommentVO bbsCommentVO) throws Exception{
		update("BbsCommentDAO.deleteBbsComment", bbsCommentVO);
		
	}

	
    
    
    


    

    
    


}
