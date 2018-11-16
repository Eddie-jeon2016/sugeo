package egovframework.com.cmm.web;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.google.gson.Gson;

import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.service.FileMngService;
import egovframework.com.cmm.service.FileMngUtil;
import egovframework.com.cmm.service.FileVO;
import egovframework.com.cmm.service.GlobalsProperties;
import four.common.util.security.tem4UserDetails;

/**
 * 파일 조회, 삭제, 다운로드 처리를 위한 컨트롤러 클래스
 * @author 공통서비스개발팀 이삼섭
 * @since 2009.06.01
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2009.3.25  이삼섭          최초 생성
 *
 * </pre>
 */
@Controller
public class FileMngController {
	
	 
	 

    @Resource(name = "FileMngService")
    private FileMngService fileService;
    
    @Resource(name = "FileMngUtil")
    private FileMngUtil fileUtil;

    private static final Logger LOG = Logger.getLogger(FileMngController.class.getName());

    /**
     * 첨부파일에 대한 목록을 조회한다.
     * 
     * @param fileVO
     * @param atchFileId
     * @param sessionVO
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping("/cmm/fms/selectFileInfs.do")
    public String selectFileInfs(@ModelAttribute("searchVO") FileVO fileVO, Map<String, Object> commandMap, ModelMap model) throws Exception {
	String atchFileId = (String)commandMap.get("param_atchFileId");

	fileVO.setAtchFileId(atchFileId);
	List<FileVO> result = fileService.selectFileInfs(fileVO);

	model.addAttribute("fileList", result);
	model.addAttribute("updateFlag", "N");
	model.addAttribute("fileListCnt", result.size());
	model.addAttribute("atchFileId", atchFileId);
	
	return "egovframework/com/cmm/fms/FileList";
    }

    /**
     * 첨부파일 변경을 위한 수정페이지로 이동한다.
     * 
     * @param fileVO
     * @param atchFileId
     * @param sessionVO
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping("/cmm/fms/selectFileInfsForUpdate.do")
    public String selectFileInfsForUpdate(@ModelAttribute("searchVO") FileVO fileVO, Map<String, Object> commandMap,
	    //SessionVO sessionVO,
	    ModelMap model) throws Exception {

	String atchFileId = (String)commandMap.get("param_atchFileId");
	String atchFileCnt = (String)commandMap.get("atchFileCnt");
	
	fileVO.setAtchFileId(atchFileId);

	List<FileVO> result = fileService.selectFileInfs(fileVO);
	
	model.addAttribute("fileList", result);
	model.addAttribute("updateFlag", "Y");
	model.addAttribute("atchFileCnt", atchFileCnt);
	model.addAttribute("fileListCnt", result.size());
	model.addAttribute("atchFileId", atchFileId);
	
	return "egovframework/com/cmm/fms/FileList";
    }
    
    @RequestMapping("/cmm/fms/selectFileListView.do")
    public String selectListView(
    		@RequestParam Map<String, Object> params,
    		@ModelAttribute("searchVO") FileVO fileVO, 
    		Map<String, Object> commandMap,
//    		@CommandMap Map commandMap,
    		//SessionVO sessionVO,
    		ModelMap model) throws Exception {

    	
    	String updateFlag = params.get("updateFlag") == null ? "N" : String.valueOf(params.get("updateFlag"));
		String atchFileId = (String)params.get("param_atchFileId");
		String atchFileCnt = (String)params.get("atchFileCnt");
		String domain = (String)params.get("param_domain") == null ? "" : (String)params.get("param_domain");
		
		fileVO.setAtchFileId(atchFileId);
	
		List<FileVO> result = fileService.selectFileInfs(fileVO);
		
		model.addAttribute("domain", 		domain);
		model.addAttribute("fileList", 		result);
		model.addAttribute("updateFlag", 	updateFlag);
		model.addAttribute("atchFileCnt", 	atchFileCnt);
		model.addAttribute("fileListCnt", 	result.size());
		model.addAttribute("atchFileId", 	atchFileId);
		
		return "egovframework/com/cmm/fms/FileListViewOnly";
    }

    /**
     * 첨부파일에 대한 삭제를 처리한다.
     * 
     * @param fileVO
     * @param returnUrl
     * @param sessionVO
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping("/cmm/fms/deleteFileInfs.do")
    public String deleteFileInf(@ModelAttribute("searchVO") FileVO fileVO, @RequestParam("returnUrl") String returnUrl,
	    //SessionVO sessionVO,
	    HttpServletRequest request,
	    ModelMap model) throws Exception {

	//Boolean isAuthenticated = UserDetailsHelper.isAuthenticated();

	//if (isAuthenticated) {
	    fileService.deleteFileInf(fileVO);
	//}

	//--------------------------------------------
	// contextRoot가 있는 경우 제외 시켜야 함
	//--------------------------------------------
	////return "forward:/cmm/fms/selectFileInfs.do";
	//return "forward:" + returnUrl;
	
	if ("".equals(request.getContextPath()) || "/".equals(request.getContextPath())) {
	    return "forward:" + returnUrl;
	}
	
	if (returnUrl.startsWith(request.getContextPath())) {
	    return "forward:" + returnUrl.substring(returnUrl.indexOf("/", 1));
	} else {
	    return "forward:" + returnUrl;
	}
	////------------------------------------------
    }
    
    /**
     * 첨부파일에 대한 모두삭제를 처리한다.
     * 
     * @param fileVO
     * @param returnUrl
     * @param sessionVO
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping("/cmm/fms/deleteFileAll.do")
    public void deleteFileAll(
    	@ModelAttribute("searchVO") FileVO fileVO,
    	@RequestParam Map<String, Object> params,
	    HttpServletRequest request, HttpServletResponse response,
	    ModelMap model) throws Exception {
    	
    	String atchfile_id = request.getParameter("atchFileId");
    	String file_sn = request.getParameter("fileSn");
    	fileVO.setAtchFileId(atchfile_id);
    	fileVO.setFileSn(file_sn);
    	
    	JSONObject result = new JSONObject();
		
			try {
				LoginVO user = new LoginVO();
				
		        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		        if(auth.getDetails() instanceof tem4UserDetails) {
		        	tem4UserDetails detail = (tem4UserDetails) auth.getDetails();
		        	user = detail.getLoginInfo();
		        }
				if ( user == null ) {
					throw new Exception("로그인후 진행해주세요.");
				}else {
					/*  파일 삭제 */
				    fileService.deleteFileInf(fileVO);
				    /*  파일 상세 삭제 */
				    fileService.deleteFileMaster(fileVO);
				    result.put("result_cd", "200");
					result.put("result_msg", "Success");
				}
			} catch (Exception e) {
				result.put("result_cd", "500");
				result.put("result_msg", e.getMessage());
			} finally {
	            response.setHeader("Content-Type","text/html; charset=UTF-8");
	        	PrintWriter out = response.getWriter();
	     	    out.write(result.toString());
	     	    out.flush();
	     	    out.close();
	        }
    }

    /**
     * 이미지 첨부파일에 대한 목록을 조회한다.
     * 
     * @param fileVO
     * @param atchFileId
     * @param sessionVO
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping("/cmm/fms/selectImageFileInfs.do")
    public String selectImageFileInfs(@ModelAttribute("searchVO") FileVO fileVO, Map<String, Object> commandMap,
	    //SessionVO sessionVO,
	    ModelMap model) throws Exception {
    	
	
	String thumSize = (String)commandMap.get("thumSize");
	/*String atchFileId = (String)commandMap.get("atchFileId");
	fileVO.setAtchFileId(atchFileId);*/
	
	List<FileVO> result = fileService.selectImageFileList(fileVO);
	
	model.addAttribute("fileList", result);
	model.addAttribute("thumSize", thumSize);

	return "egovframework/com/cmm/fms/ImgFileList";
    }
    
    @RequestMapping("/cmm/fms/summerUploader.do")
    @ResponseBody
    public String summerNoteUploader(
    		ModelMap model,
    		HttpServletRequest request,
    		HttpServletResponse response,
    		final MultipartHttpServletRequest multiRequest
    		)throws Exception{
    	
    		HashMap map = new HashMap();
    	    // 이미지 업로드할 경로
    	    String uploadPath = GlobalsProperties.getProperty("Globals.summerFileStorePath");
    	    int size = 10 * 1024 * 1024;  // 업로드 사이즈 제한 10M 이하
    	    String fileName = ""; // 파일명

    	    try{
				List<FileVO> result = null;

    	    	String uploadFolder = "";
    	    	String image = "";
    	    	String imageFile = "";
    	    	String atchFileId = "";
    	    	
    	    	final Map<String, MultipartFile> files = multiRequest.getFileMap();
    	    	if(!files.isEmpty()){
    	    	    result = fileUtil.parseFileInf(files, "BBS_", 0, "", uploadFolder);
    	    	    atchFileId = fileService.insertFileInfs(result);
    	    	}
    	    	
    	    	map.put("atchFileId",result.get(0).atchFileId);
    	    	map.put("fileSn",result.get(0).fileSn);
    	    	
    	    }catch(Exception e){
    	        e.printStackTrace();
    	    }

    	return new Gson().toJson(map);
    }
    
    @RequestMapping("/cmm/fms/popupUploader.do")
    @ResponseBody
    public String summerNotePopupUploader(
    		ModelMap model,
    		HttpServletRequest request,
    		HttpServletResponse response,
    		final MultipartHttpServletRequest multiRequest
    		)throws Exception{
    	
    	HashMap map = new HashMap();
    	// 이미지 업로드할 경로
    	String uploadPath = GlobalsProperties.getProperty("Globals.summerFileStorePath");
    	int size = 10 * 1024 * 1024;  // 업로드 사이즈 제한 10M 이하
    	String fileName = ""; // 파일명
    	
    	try{
    		List<FileVO> result = null;
    		
    		String uploadFolder = "";
    		String image = "";
    		String imageFile = "";
    		String atchFileId = "";
    		
    		final Map<String, MultipartFile> files = multiRequest.getFileMap();
    		System.out.println("☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆");
    		System.out.println(files.size());
    		System.out.println("☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆");
    		if(!files.isEmpty()){
    			result = fileUtil.parseFileInf(files, "POPUP_", 0, "", uploadFolder);
    			atchFileId = fileService.insertFileInfs(result);
    		}
    		
    		map.put("atchFileId",result.get(0).atchFileId);
    		map.put("fileSn",result.get(0).fileSn);
    		
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    	
    	return new Gson().toJson(map);
    }
}
