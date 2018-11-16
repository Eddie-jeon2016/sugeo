package four.mng.code.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.com.cmm.ComDefaultVO;
import egovframework.com.cmm.service.impl.EgovComAbstractDAO;
import four.mng.code.service.CodeManageVO;
import four.mng.code.service.SubCodeManageVO;

@Repository("codeManageDAO")
public class CodeManageDAO extends EgovComAbstractDAO {
	
	public List<CodeManageVO> selectCodeList(ComDefaultVO searchVO) throws Exception {
		
		return (List<CodeManageVO>)list("CodeManageDAO.selectCodeList", searchVO);
		
	}
	
	public CodeManageVO selectAllCodeList(String code) throws Exception {
		
		return (CodeManageVO) select("CodeManageDAO.selectAllCodeList", code);
	}
	
	public void insertCode(CodeManageVO code) throws Exception {
		
		insert("CodeManageDAO.insertCode", code);
	}
	
	public void updateCode(CodeManageVO code) throws Exception {
		
		update("CodeManageDAO.updateCode", code);
	}
	
	public void deleteCode(String code) throws Exception {
		
		delete("CodeManageDAO.deleteCode", code);
	}
	
	public void insertSubCode(SubCodeManageVO subCode) throws Exception {
		
		insert("CodeManageDAO.insertSubCode", subCode);
	}
	
	public SubCodeManageVO selectSubCode(String subCode) throws Exception {
		
		return (SubCodeManageVO) select("CodeManageDAO.selectSubCode", subCode);
	}
	
	public void updateSubCode(SubCodeManageVO subCode) throws Exception {
		
		update("CodeManageDAO.updateSubCode", subCode);
	}
	
	public void deleteSubCode(String subCode) throws Exception {
		
		delete("CodeManageDAO.deleteSubCode", subCode);
	}
}
