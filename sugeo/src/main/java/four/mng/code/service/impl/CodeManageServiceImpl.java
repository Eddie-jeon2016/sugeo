package four.mng.code.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.com.cmm.ComDefaultVO;
import four.mng.code.service.CodeManageService;
import four.mng.code.service.CodeManageVO;
import four.mng.code.service.SubCodeManageVO;

@Service("codeManageService")
public class CodeManageServiceImpl implements CodeManageService {

	@Resource(name = "codeManageDAO")
	private CodeManageDAO codeManageDAO;
	
	@Override
	public List<CodeManageVO> selectCodeList(ComDefaultVO searchVO)
			throws Exception {
		
		return codeManageDAO.selectCodeList(searchVO);
	}

	@Override
	public CodeManageVO selectAllCodeList(String code) throws Exception {
		
		return codeManageDAO.selectAllCodeList(code);
	}

	@Override
	public void insertCode(CodeManageVO code) throws Exception {
		
		codeManageDAO.insertCode(code);
	}

	@Override
	public void updateCode(CodeManageVO code) throws Exception {
		
		codeManageDAO.updateCode(code);
	}

	@Override
	public void deleteCode(String code) throws Exception {

		codeManageDAO.deleteCode(code);
	}
	
	@Override
	public void insertSubCode(SubCodeManageVO subCode) throws Exception {
		
		codeManageDAO.insertSubCode(subCode);
	}

	@Override
	public SubCodeManageVO selectSubCode(String subCode) throws Exception {
		
		return codeManageDAO.selectSubCode(subCode);
	}

	@Override
	public void updateSubCode(SubCodeManageVO subCode) throws Exception {
		
		codeManageDAO.updateSubCode(subCode);
	}

	@Override
	public void deleteSubCode(String subCode) throws Exception {
		
		codeManageDAO.deleteSubCode(subCode);
	}
}
