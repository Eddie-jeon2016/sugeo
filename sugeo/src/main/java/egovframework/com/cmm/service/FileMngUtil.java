package egovframework.com.cmm.service;

import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import egovframework.com.cmm.WebUtil;
import egovframework.com.cmm.util.ThumbnailCreatable;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;

/**
 * @Class Name  : EgovFileMngUtil.java
 * @Description : 메시지 처리 관련 유틸리티
 * @Modification Information
 *
 *     수정일         수정자                   수정내용
 *     -------          --------        ---------------------------
 *   2009.02.13       이삼섭                  최초 생성
 *   2011.08.09       서준식                  utl.fcc패키지와 Dependency제거를 위해 getTimeStamp()메서드 추가
 * @author 공통 서비스 개발팀 이삼섭
 * @since 2009. 02. 13
 * @version 1.0
 * @see
 *
 */
@Component("FileMngUtil")
public class FileMngUtil {

    public static final int BUFF_SIZE = 2048;


    @Resource(name = "egovFileIdGnrService")
    private EgovIdGnrService idgenService;
    
    /** fileMngService */
    @Resource(name = "FileMngService")
    private FileMngService fileMngService;
    
    @Autowired
	private ThumbnailCreatable thumbnailCreatable;
    
    private static final Logger LOG = Logger.getLogger(FileMngUtil.class.getName());

    /**
     * 첨부파일에 대한 목록 정보를 취득한다.
     *
     * @param files
     * @return
     * @throws Exception
     */
    public List<FileVO> parseFileInf(Map<String, MultipartFile> files, String KeyStr, int fileKeyParam, String atchFileId, String storePath) throws Exception {
	int fileKey = fileKeyParam;

	String storePathString = "";
	String atchFileIdString = "";

	if ("".equals(storePath) || storePath == null) {
	    storePathString = GlobalsProperties.getProperty("Globals.fileStorePath");
	} else {
	    storePathString = GlobalsProperties.getProperty(storePath);
	}
	
	if ("".equals(atchFileId) || atchFileId == null) {
		atchFileIdString = String.valueOf(idgenService.getNextStringId());
	} else {
	    atchFileIdString = atchFileId;
	}

	File saveFolder = new File(WebUtil.filePathBlackList(storePathString));

	if (!saveFolder.exists() || saveFolder.isFile()) {
	    saveFolder.mkdirs();
	}

	Iterator<Entry<String, MultipartFile>> itr = files.entrySet().iterator();
	MultipartFile file;
	String filePath = "";
	List<FileVO> result  = new ArrayList<FileVO>();
	FileVO fvo;
	File readFile = null;
	BufferedImage bi = null;
	Boolean flag = true;
	int count = 0;
	while (itr.hasNext()) {
	    Entry<String, MultipartFile> entry = itr.next();

	    file = entry.getValue();
	    String orginFileName	 = file.getOriginalFilename();
	    String fieldName		 = file.getName();
	    if(flag) {
	    	if(file.isEmpty()) {
	    		fileKey += 1;
	    	}
	    	flag = false;
	    }
	    //--------------------------------------
	    // 원 파일명이 없는 경우 처리
	    // (첨부가 되지 않은 input file type)
	    //--------------------------------------
	    if ("".equals(orginFileName)) {
		continue;
	    }
	    ////------------------------------------

	    int index = orginFileName.lastIndexOf(".");
	    //String fileName = orginFileName.substring(0, index);
	    String fileExt = orginFileName.substring(index + 1);
	    String newName = KeyStr + getTimeStamp() + fileKey;
	    long _size = file.getSize();
	    System.out.println("#####파일 오리지널 이름??? = " + orginFileName);
	    if (!"".equals(orginFileName)) { 
		filePath = storePathString + File.separator + newName;
		 System.out.println("#####storePathString??? = " + storePathString);
		 System.out.println("#####filePath??? = " + filePath);
		file.transferTo(new File(WebUtil.filePathBlackList(filePath)));
	    }
	    
	    /* 섬네일 이미지 만들기  */
	    if(fileExt.toLowerCase().equals("jpg") || fileExt.toLowerCase().equals("gif") || fileExt.toLowerCase().equals("png") || fileExt.toLowerCase().equals("bmp")){
		    String thumbnail50FullPath = new StringBuffer(filePath).append("_thumb50").toString();
			String thumbnail100FullPath = new StringBuffer(filePath).append("_thumb100").toString();
			String thumbnail150FullPath = new StringBuffer(filePath).append("_thumb150").toString();
			String thumbnail200FullPath = new StringBuffer(filePath).append("_thumb200").toString();
			thumbnailCreatable.create(filePath, thumbnail50FullPath, 50, -1);
			thumbnailCreatable.create(filePath, thumbnail100FullPath, 100, -1);
			thumbnailCreatable.create(filePath, thumbnail150FullPath, 150, -1);
			thumbnailCreatable.create(filePath, thumbnail200FullPath, 200, -1);
	    }
	    /* 섬네일 이미지 만들기 끝 */
		fvo = new FileVO();
	    fvo.setFileExtsn(fileExt);
	    fvo.setFileStreCours(storePathString);
	    fvo.setFileMg(Long.toString(_size));
	    fvo.setOrignlfileName(orginFileName);
	    fvo.setStrefileName(newName);
	    fvo.setAtchFileId(atchFileIdString);
	    fvo.setFileSn(String.valueOf(fileKey));
	    fvo.setFieldName(fieldName);
	    if(count >= 1) {
	    	atchFileIdString = String.valueOf(idgenService.getNextStringId());
	    	fvo.setAtchFileId(atchFileIdString);
	    }
	    
	    if(fileExt.equals("jpg") || fileExt.equals("gif") || fileExt.equals("png") || fileExt.equals("bmp")){
		    readFile = new File(fvo.getFileStreCours()+fvo.getStrefileName());
		    bi = ImageIO.read( readFile );
		    int widthSize = bi.getWidth();
		    int heightSize = bi.getHeight();
		    int i = 1;
		    if(widthSize > 690){
		    	while(widthSize > 690){
		    		widthSize = widthSize - (int) (widthSize * i / 100.0);
		    		heightSize = heightSize - (int) (heightSize * i / 100.0);
		    		
					if(widthSize < 691){
						break;
					}
	    		}
		    }
		    fvo.setWidthSize(widthSize);
		    fvo.setHeightSize(heightSize);
	    }
	    //writeFile(file, newName, storePathString);
	    result.add(fvo);

	    fileKey++;
	    count++;
	}

	return result;
    }
    
    /**
     * 첨부파일에 대한 가로, 세로 정보를 취득한다.
     *
     * @param files
     * @return
     * @throws Exception
     */
    public static List<FileVO> getFileInf(List<FileVO> files) throws Exception {
	
		List<FileVO> result  = new ArrayList<FileVO>();
		
		File readFile = null;
		BufferedImage bi = null;
	    for(int j = 0; j < files.size(); j++){
	    	FileVO fvo = new FileVO();
	    	fvo = files.get(j);
	    	readFile = new File(fvo.getFileStreCours()+fvo.getStrefileName());
	    	if (readFile.isFile()) {
			    bi = ImageIO.read( readFile );
			    int widthSize = bi.getWidth();
			    int heightSize = bi.getHeight();
			    int i = 1;
			    if(widthSize > 690){
			    	while(widthSize > 690){
			    		widthSize = widthSize - (int) (widthSize * i / 100.0);
			    		heightSize = heightSize - (int) (heightSize * i / 100.0);
			    		
						if(widthSize < 691){
							break;
						}
		    		}
			    }
			    fvo.setWidthSize(widthSize);
			    fvo.setHeightSize(heightSize);
	    	} 
	    	result.add(fvo);
	    }
		    
	    return result;
    }
    
    /**
     * 첨부파일을 서버에 저장한다.
     *
     * @param file
     * @param newName
     * @param stordFilePath
     * @throws Exception
     */
    protected void writeUploadedFile(MultipartFile file, String newName, String stordFilePath) throws Exception {
	InputStream stream = null;
	OutputStream bos = null;

	try {
	    stream = file.getInputStream();
	    File cFile = new File(stordFilePath);

	    if (!cFile.isDirectory()) {
		boolean _flag = cFile.mkdir();
		if (!_flag) {
		    throw new IOException("Directory creation Failed ");
		}
	    }

	    bos = new FileOutputStream(stordFilePath + File.separator + newName);

	    int bytesRead = 0;
	    byte[] buffer = new byte[BUFF_SIZE];

	    while ((bytesRead = stream.read(buffer, 0, BUFF_SIZE)) != -1) {
		bos.write(buffer, 0, bytesRead);
	    }
	} catch (Exception e) {
	    //e.printStackTrace();
	    LOG.error("IGNORE:", e);	// 2011.10.10 보안점검 후속조치
	} finally {
	    if (bos != null) {
		try {
		    bos.close();
		} catch (Exception ignore) {
		    LOG.debug("IGNORED: " + ignore.getMessage());
		}
	    }
	    if (stream != null) {
		try {
		    stream.close();
		} catch (Exception ignore) {
		    LOG.debug("IGNORED: " + ignore.getMessage());
		}
	    }
	}
    }

    /**
     * 서버의 파일을 다운로드한다.
     *
     * @param request
     * @param response
     * @throws Exception
     */
    public static void downFile(HttpServletRequest request, HttpServletResponse response) throws Exception {

	String downFileName = "";
	String orgFileName = "";

	if ((String)request.getAttribute("downFile") == null) {
	    downFileName = "";
	} else {
	    downFileName = (String)request.getAttribute("downFile");
	}

	if ((String)request.getAttribute("orgFileName") == null) {
	    orgFileName = "";
	} else {
	    orgFileName = (String)request.getAttribute("orginFile");
	}

	orgFileName = orgFileName.replaceAll("\r", "").replaceAll("\n", "");

	File file = new File(WebUtil.filePathBlackList(downFileName));

	if (!file.exists()) {
	    throw new FileNotFoundException(downFileName);
	}

	if (!file.isFile()) {
	    throw new FileNotFoundException(downFileName);
	}

	byte[] b = new byte[BUFF_SIZE]; //buffer size 2K.

	response.setContentType("application/x-msdownload");
	response.setHeader("Content-Disposition:", "attachment; filename=" + new String(orgFileName.getBytes(), "UTF-8"));
	response.setHeader("Content-Transfer-Encoding", "binary");
	response.setHeader("Pragma", "no-cache");
	response.setHeader("Expires", "0");

	BufferedInputStream fin = null;
	BufferedOutputStream outs = null;

	try {
		fin = new BufferedInputStream(new FileInputStream(file));
	    outs = new BufferedOutputStream(response.getOutputStream());
	    int read = 0;

		while ((read = fin.read(b)) != -1) {
		    outs.write(b, 0, read);
		}
	} finally {
	    if (outs != null) {
			try {
			    outs.close();
			} catch (Exception ignore) {
			    //System.out.println("IGNORED: " + ignore.getMessage());
			    LOG.debug("IGNORED: " + ignore.getMessage());
			}
		    }
		    if (fin != null) {
			try {
			    fin.close();
			} catch (Exception ignore) {
			    //System.out.println("IGNORED: " + ignore.getMessage());
			    LOG.debug("IGNORED: " + ignore.getMessage());
			}
		    }
		}
    }

    /**
     * 첨부로 등록된 파일을 서버에 업로드한다.
     *
     * @param file
     * @return
     * @throws Exception
     */
    public static HashMap<String, String> uploadFile(MultipartFile file) throws Exception {

	HashMap<String, String> map = new HashMap<String, String>();
	//Write File 이후 Move File????
	String newName = "";
	String stordFilePath = "D:/download";
	String orginFileName = file.getOriginalFilename();

	int index = orginFileName.lastIndexOf(".");
	//String fileName = orginFileName.substring(0, _index);
	String fileExt = orginFileName.substring(index + 1);
	long size = file.getSize();

	//newName 은 Naming Convention에 의해서 생성
	newName = getTimeStamp();	// 2012.11 KISA 보안조치
	writeFile(file, newName, stordFilePath);
	//storedFilePath는 지정
	map.put(Globals.ORIGIN_FILE_NM, orginFileName);
	map.put(Globals.UPLOAD_FILE_NM, newName);
	map.put(Globals.FILE_EXT, fileExt);
	map.put(Globals.FILE_PATH, stordFilePath);
	map.put(Globals.FILE_SIZE, String.valueOf(size));

	return map;
    }

    /**
     * 파일을 실제 물리적인 경로에 생성한다.
     *
     * @param file
     * @param newName
     * @param stordFilePath
     * @throws Exception
     */
    protected static void writeFile(MultipartFile file, String newName, String stordFilePath) throws Exception {
	InputStream stream = null;
	OutputStream bos = null;

	try {
	    stream = file.getInputStream();
	    File cFile = new File(WebUtil.filePathBlackList(stordFilePath));

	    if (!cFile.isDirectory())
		cFile.mkdir();

	    bos = new FileOutputStream(WebUtil.filePathBlackList(stordFilePath + File.separator + newName));

	    int bytesRead = 0;
	    byte[] buffer = new byte[BUFF_SIZE];

	    while ((bytesRead = stream.read(buffer, 0, BUFF_SIZE)) != -1) {
		bos.write(buffer, 0, bytesRead);
	    }
	} catch (Exception e) {
	    //e.printStackTrace();
	    //throw new RuntimeException(e);	// 보안점검 후속조치
		Logger.getLogger(FileMngUtil.class).debug("IGNORED: " + e.getMessage());
	} finally {
	    if (bos != null) {
		try {
		    bos.close();
		} catch (Exception ignore) {
		    Logger.getLogger(FileMngUtil.class).debug("IGNORED: " + ignore.getMessage());
		}
	    }
	    if (stream != null) {
		try {
		    stream.close();
		} catch (Exception ignore) {
		    Logger.getLogger(FileMngUtil.class).debug("IGNORED: " + ignore.getMessage());
		}
	    }
	}
    }

    /**
     * 서버 파일에 대하여 다운로드를 처리한다.
     *
     * @param response
     * @param strefileName
     *            : 파일저장 경로가 포함된 형태
     * @param orignfileName
     * @throws Exception
     */
    public void downFile(HttpServletResponse response, String strefileName, String orignfileName) throws Exception {
	String downFileName = strefileName;
	String orgFileName = orignfileName;

	File file = new File(downFileName);
	//log.debug(this.getClass().getName()+" downFile downFileName "+downFileName);
	//log.debug(this.getClass().getName()+" downFile orgFileName "+orgFileName);

	if (!file.exists()) {
	    throw new FileNotFoundException(downFileName);
	}

	if (!file.isFile()) {
	    throw new FileNotFoundException(downFileName);
	}

	//byte[] b = new byte[BUFF_SIZE]; //buffer size 2K.
	int fSize = (int)file.length();
	if (fSize > 0) {
	    BufferedInputStream in = null;

	    try {
		in = new BufferedInputStream(new FileInputStream(file));

    	    	String mimetype = "text/html"; //"application/x-msdownload"

    	    	response.setBufferSize(fSize);
		response.setContentType(mimetype);
		response.setHeader("Content-Disposition:", "attachment; filename=" + orgFileName);
		response.setContentLength(fSize);
		//response.setHeader("Content-Transfer-Encoding","binary");
		//response.setHeader("Pragma","no-cache");
		//response.setHeader("Expires","0");
		FileCopyUtils.copy(in, response.getOutputStream());
	    } finally {
		if (in != null) {
		    try {
			in.close();
		    } catch (Exception ignore) {
			
			Logger.getLogger(FileMngUtil.class).debug("IGNORED: " + ignore.getMessage());
		    }
		}
	    }
	    response.getOutputStream().flush();
	    response.getOutputStream().close();
	}

	/*
	String uploadPath = propertiesService.getString("fileDir");

	File uFile = new File(uploadPath, requestedFile);
	int fSize = (int) uFile.length();

	if (fSize > 0) {
	    BufferedInputStream in = new BufferedInputStream(new FileInputStream(uFile));

	    String mimetype = "text/html";

	    response.setBufferSize(fSize);
	    response.setContentType(mimetype);
	    response.setHeader("Content-Disposition", "attachment; filename=\""
					+ requestedFile + "\"");
	    response.setContentLength(fSize);

	    FileCopyUtils.copy(in, response.getOutputStream());
	    in.close();
	    response.getOutputStream().flush();
	    response.getOutputStream().close();
	} else {
	    response.setContentType("text/html");
	    PrintWriter printwriter = response.getWriter();
	    printwriter.println("<html>");
	    printwriter.println("<br><br><br><h2>Could not get file name:<br>" + requestedFile + "</h2>");
	    printwriter.println("<br><br><br><center><h3><a href='javascript: history.go(-1)'>Back</a></h3></center>");
	    printwriter.println("<br><br><br>&copy; webAccess");
	    printwriter.println("</html>");
	    printwriter.flush();
	    printwriter.close();
	}
	//*/


	/*
	response.setContentType("application/x-msdownload");
	response.setHeader("Content-Disposition:", "attachment; filename=" + new String(orgFileName.getBytes(),"UTF-8" ));
	response.setHeader("Content-Transfer-Encoding","binary");
	response.setHeader("Pragma","no-cache");
	response.setHeader("Expires","0");

	BufferedInputStream fin = new BufferedInputStream(new FileInputStream(file));
	BufferedOutputStream outs = new BufferedOutputStream(response.getOutputStream());
	int read = 0;

	while ((read = fin.read(b)) != -1) {
	    outs.write(b,0,read);
	}
	log.debug(this.getClass().getName()+" BufferedOutputStream Write Complete!!! ");

	outs.close();
    	fin.close();
	//*/
    }

    /**
     * 2011.08.09
     * 공통 컴포넌트 utl.fcc 패키지와 Dependency제거를 위해 내부 메서드로 추가 정의함
     * 응용어플리케이션에서 고유값을 사용하기 위해 시스템에서17자리의TIMESTAMP값을 구하는 기능
     *
     * @param
     * @return Timestamp 값
     * @exception MyException
     * @see
     */
    private static String getTimeStamp() {

	String rtnStr = null;

	// 문자열로 변환하기 위한 패턴 설정(년도-월-일 시:분:초:초(자정이후 초))
	String pattern = "yyyyMMddhhmmssSSS";

	try {
	    SimpleDateFormat sdfCurrent = new SimpleDateFormat(pattern, Locale.KOREA);
	    Timestamp ts = new Timestamp(System.currentTimeMillis());

	    rtnStr = sdfCurrent.format(ts.getTime());
	} catch (Exception e) {
	    //e.printStackTrace();
		
	    //throw new RuntimeException(e);	// 보안점검 후속조치
	    LOG.debug("IGNORED: " + e.getMessage());
	}

	return rtnStr;
    }
    
    /**
     * 파일삭제
     * @param file_list
     */
    public static void delete_files( List<FileVO> file_list ) {
    	FileVO fileVO = null;
		if ( file_list != null && !file_list.isEmpty() && file_list.size()>0 )  {
			for ( int i=0;i<file_list.size();i++ ) {
				fileVO = file_list.get(i);
				
				String fileStreCours = fileVO.getFileStreCours();
				
				String strefileName    = fileVO.getStrefileName();

				//파일삭제
				File deleteFile  = new File ( fileStreCours+strefileName );
				deleteFile.delete();
				
				//이미지 형태일경우 미리보기 파일도 삭제
				if ( "jpg".equals(fileVO.getFileExtsn()) || "png".equals(fileVO.getFileExtsn()) || "gif".equals(fileVO.getFileExtsn()) ) {
					File deleteFile_thumb50  = new File ( fileStreCours+strefileName+"thumb50" );
    				deleteFile_thumb50.delete();
    				File deleteFile_thumb100  = new File ( fileStreCours+strefileName+"thumb100" );
    				deleteFile_thumb100.delete();
    				File deleteFile_thumb150  = new File ( fileStreCours+strefileName+"thumb150" );
    				deleteFile_thumb150.delete();
    				File deleteFile_thumb200  = new File ( fileStreCours+strefileName+"thumb200" );
    				deleteFile_thumb200.delete();
				}
			}
		}
    }
}
