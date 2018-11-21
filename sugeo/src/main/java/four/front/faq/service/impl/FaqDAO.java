package four.front.faq.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.com.cmm.ComDefaultVO;
import egovframework.com.cmm.service.impl.EgovComAbstractDAO;
import four.front.faq.service.FaqVO;

@Repository("faqDAO")
public class FaqDAO extends EgovComAbstractDAO {
	
	public List<FaqVO> selectFaqList(ComDefaultVO comVO) throws Exception {
		
		return (List<FaqVO>) list("FaqDAO.selectFaqList", comVO);
		
	}
	
	public int selectFaqListCnt(ComDefaultVO comVO) throws Exception {
		int totcnt = (Integer)select("FaqDAO.selectFaqListCnt", comVO);
		return totcnt;	
	}
///////////////////////////////////////////////관리자 페이지	

	/**FAQ관리자 리스트를 조회한다 */
	public List<FaqVO> selectFaqMngList(ComDefaultVO comVO) throws Exception{
		return (List<FaqVO>)list("FaqDAO.selectFaqMngList", comVO);
	}
	
	/**FAQ 목록 갯수를 조회한다 */
	public int selectFaqMngListCnt(ComDefaultVO comVO) throws Exception{
		return (Integer)select("FaqDAO.selectFaqMngListCnt", comVO);
	}

	/**FAQ를 등록한다 */
	public int insertFaqMng(FaqVO faqVO) throws Exception{
		return insert("FaqDAO.insertFaqMng", faqVO);
	}
	
	/**FAQ의 수정을 위해 리스트를 조회한다 */
	public FaqVO selectFaqMngOneList(String faqId) throws Exception{
		return selectOne("FaqDAO.selectFaqMngOneList", faqId);
	}	
	
	/**FAQ를 수정한다 */
	public int updateFaqMng(FaqVO faqVO) throws Exception{
		return update("FaqDAO.updateFaqMng", faqVO);
	}
	/**FAQ를 삭제한다 */
	public int deleteFaqMng(String deleteId) throws Exception{
		return update("FaqDAO.deleteFaqMng", deleteId);
	}
	
}
