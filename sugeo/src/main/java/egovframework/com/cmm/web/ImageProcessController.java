package egovframework.com.cmm.web;

import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.Map;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import egovframework.com.cmm.SessionVO;
import egovframework.com.cmm.service.FileMngService;
import egovframework.com.cmm.service.FileVO;
import egovframework.com.cmm.service.Globals;


/**
 * @Class Name : ImageProcessController.java
 * @Description :
 * @Modification Information
 *
 *    수정일       수정자         수정내용
 *    -------        -------     -------------------
 *    2009. 4. 2.     이삼섭
 *
 * @author 공통 서비스 개발팀 이삼섭
 * @since 2009. 4. 2.
 * @version
 * @see
 *
 */
@SuppressWarnings("serial")
@Controller
public class ImageProcessController extends HttpServlet {

    @Resource(name = "FileMngService")
    private FileMngService fileService;

    private static final Logger LOG = Logger.getLogger(ImageProcessController.class.getName());

    /**
     * 첨부된 이미지에 대한 미리보기 기능을 제공한다.
     *
     * @param atchFileId
     * @param fileSn
     * @param sessionVO
     * @param model
     * @param response
     * @throws Exception
     */
    @RequestMapping("/cmm/fms/getImage.do")
    public void getImageInf(SessionVO sessionVO, ModelMap model, Map<String, Object> commandMap, HttpServletResponse response,@RequestParam Map<String, Object> params) throws Exception {

		//@RequestParam("atchFileId") String atchFileId,
		//@RequestParam("fileSn") String fileSn,
		String atchFileId = (String)params.get("atchFileId");
		String fileSn = params.get("fileSn") == null ? "0" : (String)params.get("fileSn");
		String thumSize = (String)params.get("thumSize");
		
		FileVO vo = new FileVO();
	
		vo.setAtchFileId(atchFileId);
		vo.setFileSn(fileSn);
	
		FileVO fvo = fileService.selectFileInf(vo);
	
		//String fileLoaction = fvo.getFileStreCours() + fvo.getStrefileName();
	
		// 2011.10.10 보안점검 후속조치
		File file = null;
		FileInputStream fis = null;
	
		BufferedInputStream in = null;
		ByteArrayOutputStream bStream = null;
		
		String fileName = fvo.getStrefileName();
		
		/* 섬네일 이미지 사이즈가 있을경우 사이즈에 맞는 이미지파일을 선택한다.  */
		if( thumSize != null && !"".equals(thumSize) ) {
			if(fileName.lastIndexOf(".") > 0 ){
				fileName = fileName.substring(0, fileName.length()-4) + "_thumb" + thumSize + fileName.substring(fileName.length()-4);
			}else{
				fileName = fileName + "_thumb" + thumSize ;
			}
		}
		try {
		    file = new File(fvo.getFileStreCours(), fileName);
		    /* 파일이 존재하지 않을경우 파일404이미지로 대체  */
		    if(file.exists()){
		    	fis = new FileInputStream(file);
		    }else{
		    	file = new File("/", "file_not_found.jpg");
		    	fis = new FileInputStream(file);
		    }
		    in = new BufferedInputStream(fis);
		    bStream = new ByteArrayOutputStream();
		    File file1 = new File(fvo.getFileStreCours()+fvo.getStrefileName());
		    /* 파일이 존재하지 않을경우 파일404이미지로 대체  */
		    if(!file1.exists()){
		    	file1 = new File("/", "file_not_found.jpg");
		    }
		    BufferedImage bi = ImageIO.read( file1 );
            
		    int imgByte;
		    while ((imgByte = in.read()) != -1) {
			bStream.write(imgByte);
		    }
	
			String type = "";
		
			if (fvo.getFileExtsn() != null && !"".equals(fvo.getFileExtsn())) {
			    if ("jpg".equals(fvo.getFileExtsn().toLowerCase())) {
				type = "image/jpeg";
			    } else {
				type = "image/" + fvo.getFileExtsn().toLowerCase();
			    }
//			    type = "image/" + fvo.getFileExtsn().toLowerCase();
		
			} else {
			    LOG.debug("Image fileType is null.");
			}
		
			response.setHeader("Content-Type", type);
			response.setContentLength(bStream.size());
			
			bStream.writeTo(response.getOutputStream());
		
			response.getOutputStream().flush();
			response.getOutputStream().close();
	
			// 2011.10.10 보안점검 후속조치 끝
		} finally {
			if (bStream != null) {
				try {
					bStream.close();
				} catch (Exception ignore) {
					LOG.debug("IGNORE: " + ignore.getMessage());
				}
			}
			if (in != null) {
				try {
					in.close();
				} catch (Exception ignore) {
					LOG.debug("IGNORE: " + ignore.getMessage());
				}
			}
			if (fis != null) {
				try {
					fis.close();
				} catch (Exception ignore) {
					LOG.debug("IGNORE: " + ignore.getMessage());
				}
			}
		}
    }
    /**
     * 첨부된 이미지에 대한 미리보기 기능을 제공한다.
     *
     * @param atchFileId
     * @param fileSn
     * @param sessionVO
     * @param model
     * @param response
     * @throws Exception
     */
    @RequestMapping("/cmm/fms/getImageSize.do")
    public String getImageSize(SessionVO sessionVO, ModelMap model, Map<String, Object> commandMap, HttpServletResponse response) throws Exception {

		//@RequestParam("atchFileId") String atchFileId,
		//@RequestParam("fileSn") String fileSn,
		String atchFileId = (String)commandMap.get("atchFileId");
		String fileSn = (String)commandMap.get("fileSn");
		String fileSize = "";
		BufferedImage bi = null;
		FileVO vo = new FileVO();
	
		vo.setAtchFileId(atchFileId);
		vo.setFileSn(fileSn);
	
		FileVO fvo = fileService.selectFileInf(vo);
	
		// 2011.10.10 보안점검 후속조치
		File file = null;
	
		String fileName = fvo.getStrefileName();
		/* 섬네일 이미지 사이즈가 있을경우 사이즈에 맞는 이미지파일을 선택한다.  */
		
	    file = new File(fvo.getFileStreCours()+fileName);
	    bi = ImageIO.read( file );
        fileSize = "가로 : " + bi.getWidth() + ", 높이 : " +  bi.getHeight();

		return fileSize;
    }
    
}
