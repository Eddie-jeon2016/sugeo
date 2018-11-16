package four.mng.code.service;

import java.util.List;

import egovframework.com.cmm.ComDefaultVO;

public interface CodeManageService {
	
	public List<CodeManageVO> selectCodeList(ComDefaultVO searchVO) throws Exception;
	
	public CodeManageVO selectAllCodeList(String code) throws Exception;
	
	public void insertCode(CodeManageVO code) throws Exception;
	
	public void updateCode(CodeManageVO code) throws Exception;
	
	public void deleteCode(String code) throws Exception;
	
	public SubCodeManageVO selectSubCode(String subCode) throws Exception;
	
	public void insertSubCode(SubCodeManageVO subCode) throws Exception;
	
	public void updateSubCode(SubCodeManageVO subCode) throws Exception;
	
	public void deleteSubCode(String subCode) throws Exception;
	
}
