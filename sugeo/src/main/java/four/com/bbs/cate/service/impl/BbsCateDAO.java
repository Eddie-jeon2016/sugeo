package four.com.bbs.cate.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.com.cmm.service.impl.EgovComAbstractDAO;
import four.com.bbs.cate.service.BbsCateVO;
   
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
@Repository("BbsCateDAO")
public class BbsCateDAO extends EgovComAbstractDAO {
 
    /**
     * 등록된 게시판 속성정보를 삭제한다.
     * 
     * @param asdaas
     */
    public void deleteBbsCate(String bbscate_id) throws Exception {
    	update("BbsCateDAO.deleteBbsCate", bbscate_id);
    }

    /**
     * 신규 게시판 속성정보를 등록한다.
     * 
     * @param asdaas
     */
/*    public String insertBbsManage(BbsManageVO bbsManageVO) throws Exception {
	return (String)insert("BbsManageDAO.insertBbsManage", bbsManageVO);
    }*/
    
    public int insertBbsCate(BbsCateVO bbsCateVO) throws Exception {
    	return insert("BbsCateDAO.insertBbsCate", bbsCateVO);
    }
 
    /**
     * 게시판 속성정보 한 건을 상세조회 한다.
     * 
     * @param asdaas
     */
    public BbsCateVO selectBbsCate(BbsCateVO bbsCateVO) throws Exception {
    	return (BbsCateVO)select("BbsCateVO.selectBbsCate", bbsCateVO);
    }

    /**
     * 게시판 속성정보 목록을 조회한다.
     * 
     * @param asdaas
     */
    @SuppressWarnings("unchecked")
    public List<BbsCateVO> selectBbsCateList(BbsCateVO bbsCateVO) throws Exception {
    	return (List<BbsCateVO>)list("BbsCateDAO.selectBbsCateList", bbsCateVO);
    }
    /**
     * 게시판 속성정보 목록 숫자를 조회한다
     * 
     * @param vo
     * @return
     * @throws Exception
     */          
    /*public int selectBbsCateListCnt(BbsCateVO bbsCateVO) throws Exception {
	return (Integer)getSqlMapClientTemplate().queryForObject("BbsCateDAO.selectBbsCateListCnt", bbsCateVO);
    }*/

    /**
     * 게시판 속성정보를 수정한다.
     * 
     * @param asdaas
     */
    public void updateBbsCate(BbsCateVO bbsCateVO) throws Exception {
    	update("BbsCateDAO.updateBbsCate", bbsCateVO);
    }

    /**
     * 말머리의 아이디값을 가져온다.
     * 
     * @param asdaas
     */   
    
	public String getNextBbscate_id() {
		return (String)select("BbsCateDAO.selectNextBbscate_id");
	}

}
