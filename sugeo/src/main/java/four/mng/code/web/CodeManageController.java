/**
 * 개요
 * - 메인화면이미지에 대한 controller 클래스를 정의한다.
 *
 * 상세내용
 * - 메인화면이미지에 대한 등록, 수정, 삭제, 조회, 반영확인 기능을 제공한다.
 * - 메인화면이미지의 조회기능은 목록조회, 상세조회로 구분된다.
 * @author 이문준
 * @version 1.0
 * @created 03-8-2009 오후 2:08:57
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자          수정내용
 *  -------    --------    ---------------------------
 *  2010.8.3	이문준          최초 생성
 *  2011.8.26	정진오			IncludedInfo annotation 추가
 *
 *  </pre>
 */

package four.mng.code.web;

import java.io.PrintWriter;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springmodules.validation.commons.DefaultBeanValidator;

import egovframework.com.cmm.ComDefaultCodeVO;
import egovframework.com.cmm.ComDefaultVO;
import egovframework.com.cmm.LoginVO;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import four.mng.code.service.CodeManageService;
import four.mng.code.service.CodeManageVO;
import four.mng.code.service.SubCodeManageVO;
import four.mng.content.service.ContentVO;
import four.mng.pop.service.PopManageService;
import four.mng.pop.service.PopManageVO;


@Controller
public class CodeManageController {

	@Resource(name = "codeManageService")
	private CodeManageService codeManageService;
	
	@RequestMapping(value = "/mng/code/codeList.do")
	public String codeManageList(Model model, ComDefaultVO searchVO) throws Exception {
		
		List<CodeManageVO> codeList = codeManageService.selectCodeList(searchVO);		
		model.addAttribute("codeList", codeList);
			
		return "mng/code/codeList";
	}
	
	@RequestMapping(value = "/mng/code/subCodeList.do")
	public String subCodeManageList(Model model, String code) throws Exception {

		CodeManageVO codeList = codeManageService.selectAllCodeList(code);

		model.addAttribute("codeList", codeList);
		
		return "mng/code/subCodeList";
		
	}
	
	@RequestMapping("/mng/code/codeRegView.do")
	public String codeRegView(){
		
		return "mng/code/codeRegView";
	}
	
	@RequestMapping("/mng/code/codeInsert.do")
	public String codeInsert(CodeManageVO code)throws Exception{
		
		codeManageService.insertCode(code);
		return "redirect:/mng/code/codeList.do";
		
	}
	
	@RequestMapping("/mng/code/codeUpdateView.do")
	public String codeUpdateView(CodeManageVO code, Model model) throws Exception{
		
		CodeManageVO cVO = codeManageService.selectAllCodeList(code.getCode());
		System.out.println(cVO);
		model.addAttribute("code", cVO);
		
		return "mng/code/codeUpdateView";
	}
	
	
	@RequestMapping("/mng/code/codeUpdate.do")
	public String codeUpdate(CodeManageVO code)throws Exception{
		System.out.println(code);
		codeManageService.updateCode(code);
		
		return "redirect:/mng/code/codeList.do";
	}
	
	//삭제 컨트롤러 매핑 구현 해야댐
	@RequestMapping("/mng/code/codeDelete.do")
	public String codeDelete(String code)throws Exception{
		
		codeManageService.deleteCode(code);
		
		return "redirect:/mng/code/codeList.do";
	}	
	
	@RequestMapping("/mng/code/subCodeRegView.do")
	public String subCodeRegView(String code, Model model){
		
		model.addAttribute("code", code);
		
		return "mng/code/subCodeRegView";
	}
	
	@RequestMapping("/mng/code/subCodeInsert.do")
	public String subCodeInsert(SubCodeManageVO subCode)throws Exception{
		
		codeManageService.insertSubCode(subCode);
		
		return "redirect:/mng/code/subCodeList.do?code="+subCode.getCode();
		
	}
	
	@RequestMapping(value = "/mng/code/subCodeView.do")
	public String subCodeView(String subCode, Model model) throws Exception {

		SubCodeManageVO sVO = codeManageService.selectSubCode(subCode);
		System.out.println("================"+sVO);
		model.addAttribute("subCode", sVO);
		
		return "/mng/code/subCodeView";
	}
	
	@RequestMapping("/mng/code/subCodeUpdateView.do")
	public String subCodeUpdateView(String subCode, Model model) throws Exception{
		
		SubCodeManageVO sVO = codeManageService.selectSubCode(subCode);	
		System.out.println("==============" + sVO);
		model.addAttribute("subCode", sVO);
		
		return "mng/code/subCodeUpdateView";
	}
	
	@RequestMapping("/mng/code/subCodeUpdate.do")
	public String subCodeUpdate(SubCodeManageVO subCode)throws Exception{
		
		codeManageService.updateSubCode(subCode);
		
		return "redirect:/mng/code/subCodeList.do?code="+subCode.getCode();
	}
	
	@RequestMapping("/mng/code/subCodeDelete.do")
	public String subCodeDelete(String subCode)throws Exception{
		
		codeManageService.deleteSubCode(subCode);
		
		return "redirect:/mng/code/codeList.do";
	}
	
	
}
