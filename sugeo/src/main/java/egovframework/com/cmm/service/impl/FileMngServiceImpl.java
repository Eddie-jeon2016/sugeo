package egovframework.com.cmm.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import egovframework.com.cmm.service.FileDefaultVO;
import egovframework.com.cmm.service.FileMngService;
import egovframework.com.cmm.service.FileVO;
import egovframework.com.utl.fcc.service.EgovFileUploadUtil;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;

/**
 * @Class Name : FileMngServiceImpl.java
 * @Description : 파일정보의 관리를 위한 구현 클래스
 * @Modification Information
 *
 *    수정일       수정자         수정내용
 *    -------        -------     -------------------
 *    2009. 3. 25.     이삼섭    최초생성
 *
 * @author 공통 서비스 개발팀 이삼섭
 * @since 2009. 3. 25.
 * @version
 * @see
 *
 */
@Service("FileMngService")
public class FileMngServiceImpl extends EgovAbstractServiceImpl implements FileMngService {

    @Resource(name = "FileManageDAO")
    private FileManageDAO fileMngDAO;
    
 
    @Resource(name = "egovContentIdGnrService")
	private EgovIdGnrService idgenService;

    public static final Logger LOGGER = Logger.getLogger(FileMngServiceImpl.class.getName());

    /**
     * 여러 개의 파일을 삭제한다.
     * 
     * @see egovframework.com.cmm.service.EgovFileMngService#deleteFileInfs(java.util.List)
     */
    @SuppressWarnings("unchecked")
    public void deleteFileInfs(List fvoList) throws Exception {
	fileMngDAO.deleteFileInfs(fvoList);
    }

    /**
     * 하나의 파일에 대한 정보(속성 및 상세)를 등록한다.
     * 
     * @see egovframework.com.cmm.service.EgovFileMngService#insertFileInf(egovframework.com.cmm.service.FileVO)
     */
    public String insertFileInf(FileVO fvo) throws Exception {
    	String atchFileId = fvo.getAtchFileId();
    	
    	fileMngDAO.insertFileInf(fvo);
    	
    	return atchFileId;
    }
    
    /**
     * 하나의 파일에 대한 정보(상세)를 등록한다.
     * 
     * @see egovframework.com.cmm.service.EgovFileMngService#insertFileInf(egovframework.com.cmm.service.FileVO)
     */
    public String insertFileInfDetail(FileVO fvo) throws Exception {
    	String atchFileId = fvo.getAtchFileId();
    	
    	fileMngDAO.insertFileInfDetail(fvo);
    	
    	return atchFileId;
    }
    
    /**
     * 하나의 파일에 대한 파일 테이블 등록
     * @param fvo
     * @return
     * @throws Exception
     */
    public String insertFileOne(FileVO fvo) throws Exception {
/*    	String atchFileId = String.valueOf(getMaxFileID());
    	fvo.setAtchFileId(atchFileId);
*/    	
    	String atchFileId = fvo.getAtchFileId();
    	fileMngDAO.insertFileInf(fvo);
    	
    	return atchFileId;
    }
    
    /**
     * 하나의 파일에 대한 update처리
     * @param fvo
     * @throws Exception
     */
    public String updateFileOne(FileVO fvo) throws Exception {
    	String atchFileId = fvo.getAtchFileId();
    	if ( atchFileId == null || "".equals(atchFileId) || "0".equals(atchFileId) ) {
    		//pk값이 없으면 insert처리
    		atchFileId = String.valueOf(getMaxFileID());
        	fvo.setAtchFileId(atchFileId);
        	fileMngDAO.insertFileInf(fvo);
    	} else {
    		//pk값이 있으면 삭제후 insert처리
    		fileMngDAO.updateFileOne(fvo);
    	}
    	
    	return atchFileId;
    }
    
    

    /**
     * 여러 개의 파일에 대한 정보(속성 및 상세)를 등록한다.
     * 
     * @see egovframework.com.cmm.service.EgovFileMngService#insertFileInfs(java.util.List)
     */
    @SuppressWarnings("unchecked")
    public String insertFileInfs(List fvoList) throws Exception {
	String atchFileId = "";
	
	if (fvoList.size() != 0) {
	    atchFileId = fileMngDAO.insertFileInfs(fvoList);
	}
	if(atchFileId == ""){
		atchFileId = null;
	}
	return atchFileId;
    }

    /**
     * 파일에 대한 목록을 조회한다.
     * 
     * @see egovframework.com.cmm.service.EgovFileMngService#selectFileInfs(egovframework.com.cmm.service.FileVO)
     */
    public List<FileVO> selectFileInfs(FileVO fvo) throws Exception {
	return fileMngDAO.selectFileInfs(fvo);
    }
    
    //파일업로드 리스트 조회.
    public List<FileVO> selectFileUpLoadList(FileDefaultVO fvo) throws Exception {
    	return fileMngDAO.selectFileUpLoadList(fvo);
        }
    //파일업로드 리스트 조회 Cnt
    @Override
	public Integer selectFileUpLoadListCnt(FileDefaultVO fvo) throws Exception {
    	return fileMngDAO.selectFileUpLoadListCnt(fvo);
    }

    /**
     * 여러 개의 파일에 대한 정보(속성 및 상세)를 수정한다.
     * 
     * @see egovframework.com.cmm.service.EgovFileMngService#updateFileInfs(java.util.List)
     */
    @SuppressWarnings("unchecked")
    public void updateFileInfs(List fvoList) throws Exception {
	//Delete & Insert
	fileMngDAO.updateFileInfs(fvoList);
    }

    /**
     * 하나의 파일을 삭제한다.
     * 
     * @see egovframework.com.cmm.service.EgovFileMngService#deleteFileInf(egovframework.com.cmm.service.FileVO)
     */
    public void deleteFileInf(FileVO fvo) throws Exception {
	fileMngDAO.deleteFileInf(fvo);
    }
    
    /**
     * attachId로 여러개의 파일을 삭제한다.
     * 
     * @see egovframework.com.cmm.service.EgovFileMngService#deleteFileInf(egovframework.com.cmm.service.FileVO)
     */
    public void deleteFileInfAll(FileVO fvo) throws Exception {
    	fileMngDAO.deleteFileInfAll(fvo);
    	fileMngDAO.deleteFileMaster(fvo);
    }
    
    /**
     * 하나의 파일을 삭제한다.
     * 
     * @see egovframework.com.cmm.service.EgovFileMngService#deleteFileInf(egovframework.com.cmm.service.FileVO)
     */
    public void deleteFileMaster(FileVO fvo) throws Exception {
	fileMngDAO.deleteFileMaster(fvo);
    }

    /**
     * 파일에 대한 상세정보를 조회한다.
     * 
     * @see egovframework.com.cmm.service.EgovFileMngService#selectFileInf(egovframework.com.cmm.service.FileVO)
     */
    public FileVO selectFileInf(FileVO fvo) throws Exception {
    	
	return fileMngDAO.selectFileInf(fvo);
    }

    /**
     * 파일 구분자에 대한 최대값을 구한다.
     * 
     * @see egovframework.com.cmm.service.EgovFileMngService#getMaxFileSN(egovframework.com.cmm.service.FileVO)
     */
    public int getMaxFileSN(FileVO fvo) throws Exception {
	return fileMngDAO.getMaxFileSN(fvo);
    }
    
    /**
     * 파일 구분자에 대한 최대값을 구한다.
     * 
     * @see egovframework.com.cmm.service.EgovFileMngService#getMaxFileSN(egovframework.com.cmm.service.FileVO)
     */
    public int getMaxFileCnt(FileVO fvo) throws Exception {
	return fileMngDAO.getMaxFileCnt(fvo);
    }
    
    /**
     * 파일 구분자에 대한 최대값을 구한다.
     * 
     * @see egovframework.com.cmm.service.EgovFileMngService#getMaxFileSN(egovframework.com.cmm.service.FileVO)
     */
    public int getMaxFileID() throws Exception {
	return fileMngDAO.getMaxFileID();
    }

    /**
     * 전체 파일을 삭제한다.
     * 
     * @see egovframework.com.cmm.service.EgovFileMngService#deleteAllFileInf(egovframework.com.cmm.service.FileVO)
     */
    public void deleteAllFileInf(FileVO fvo) throws Exception {
	fileMngDAO.deleteAllFileInf(fvo);
    }

    /**
     * 파일명 검색에 대한 목록을 조회한다.
     * 
     * @see egovframework.com.cmm.service.EgovFileMngService#selectFileListByfileName(egovframework.com.cmm.service.FileVO)
     */
    public Map<String, Object> selectFileListByfileName(FileVO fvo) throws Exception {
	List<FileVO>  result = fileMngDAO.selectFileListByfileName(fvo);
	int cnt = fileMngDAO.selectFileListCntByfileName(fvo);

	Map<String, Object> map = new HashMap<String, Object>();
	
	map.put("resultList", result);
	map.put("resultCnt", Integer.toString(cnt));

	return map;
    }

    /**
     * 이미지 파일에 대한 목록을 조회한다.
     * 
     * @see egovframework.com.cmm.service.EgovFileMngService#selectImageFileList(egovframework.com.cmm.service.FileVO)
     */
    public List<FileVO> selectImageFileList(FileVO vo) throws Exception {
	return fileMngDAO.selectImageFileList(vo);
    }
    
    /**
     *  (게시판)파일 디테일을 업데이트 한다.
     * 
     * @param fvo
     * @throws Exception
     */
    public void updateFileDetail(FileVO fvo) throws Exception {
    	fileMngDAO.updateFileDetail(fvo);
        }

	@Override
	public FileVO selectMainImg() throws Exception {
		return fileMngDAO.selectMainImg();
		
	}
    
}
