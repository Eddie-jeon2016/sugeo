package four.mng.press.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.com.cmm.service.impl.EgovComAbstractDAO;
import four.mng.press.sevice.PressContentDefaultVO;
import four.mng.press.sevice.PressContentVO;

@Repository("PressContentDAO")
public class PressContentDAO extends EgovComAbstractDAO{
	
	/**게시판 리스트를 조회한다 */
	public List<PressContentVO> selectPressContentList(PressContentDefaultVO searchVO) throws Exception{
		return (List<PressContentVO>) list("PressContentDAO.selectPressContentList", searchVO);
	}
	
	/**게시판 목록 갯수를 조회한다 */
	public int selectPressContentListCnt(PressContentDefaultVO searchVO) throws Exception{
		return (Integer)select("PressContentDAO.selectPressContentListCnt", searchVO);
	}
	
	/**언론보도자료를 등록한다. */
	public int insertPressContent(PressContentVO pressContentVO) throws Exception{
		return insert("PressContentDAO.insertPressContent", pressContentVO);
	}

	/**언론보도자료를 수정을 위해 리스트를 조회한다. */
	public PressContentVO selectpressContentOneList(String pcId) throws Exception{
		return selectOne("PressContentDAO.selectPressContentOneList", pcId);
	}

	/**언론보도자료를 수정한다. */
	public int updatePressContent(PressContentVO pressContentVO) throws Exception{
		return update("PressContentDAO.updatePressContent", pressContentVO);
	}
	
	/**언론보도자료를 삭제한다. */
	public int deletePressContent(String deleteId) throws Exception{
		return update("PressContentDAO.deletePressContent", deleteId);
	}
	
	
	
	
}
