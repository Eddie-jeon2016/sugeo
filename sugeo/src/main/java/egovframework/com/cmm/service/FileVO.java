package egovframework.com.cmm.service;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * @Class Name : FileVO.java
 * @Description : 파일정보 처리를 위한 VO 클래스
 * @Modification Information
 *
 *    수정일       수정자         수정내용
 *    -------        -------     -------------------
 *    2009. 3. 25.     이삼섭
 *
 * @author 공통 서비스 개발팀 이삼섭
 * @since 2009. 3. 25.
 * @version
 * @see
 *
 */
@SuppressWarnings("serial")
public class FileVO implements Serializable {

    /**
     * 첨부파일 아이디
     */
    public String atchFileId = "";
    /**
     * 생성일자
     */
    public String creatDt = "";
    /**
     * 파일내용
     */
    public String fileCn = "";
    /**
     * 파일확장자
     */
    public String fileExtsn = "";
    /**
     * 파일크기
     */
    public String fileMg = "";
    /**
     * 파일연번
     */
    public String fileSn = "";
    /**
     * 파일저장경로
     */
    public String fileStreCours = "";
    /**
     * 원파일명
     */
    public String orignlfileName = "";
    /**
     * 저장파일명
     */
    public String strefileName = "";
    /**
     * input 필드 name
     */
    public String fieldName = "";
    
    Integer widthSize = 0;
    
    Integer heightSize = 0;
    /**
     * 유저아이디
     */    
    public String user_id;
    /**
     * 파일 코드
     */
    public String filecode;
    
    public String frst_date;
    
    /** menu1 */
    private String menu1 = "";    
    
    /** menu2 */
    private String menu2 = "";
    
    /** menu3 */
    private String menu3 = "";
    
    /** cmd */
    private String cmd = "";
    
    /** cmd */
    private String status = "";
    
    private String fileId ="";
    
    public String getFileId() {
		return fileId;
	}

	public void setFileId(String fileId) {
		this.fileId = fileId;
	}

	/**
     * atchFileId attribute를 리턴한다.
     * 
     * @return the atchFileId
     */
    public String getAtchFileId() {
	return atchFileId;
    }

    /**
     * atchFileId attribute 값을 설정한다.
     * 
     * @param atchFileId
     *            the atchFileId to set
     */
    public void setAtchFileId(String atchFileId) {
	this.atchFileId = atchFileId;
    }

    /**
     * creatDt attribute를 리턴한다.
     * 
     * @return the creatDt
     */
    public String getCreatDt() {
	return creatDt;
    }

    /**
     * creatDt attribute 값을 설정한다.
     * 
     * @param creatDt
     *            the creatDt to set
     */
    public void setCreatDt(String creatDt) {
	this.creatDt = creatDt;
    }

    /**
     * fileCn attribute를 리턴한다.
     * 
     * @return the fileCn
     */
    public String getFileCn() {
	return fileCn;
    }

    /**
     * fileCn attribute 값을 설정한다.
     * 
     * @param fileCn
     *            the fileCn to set
     */
    public void setFileCn(String fileCn) {
	this.fileCn = fileCn;
    }

    /**
     * fileExtsn attribute를 리턴한다.
     * 
     * @return the fileExtsn
     */
    public String getFileExtsn() {
	return fileExtsn;
    }

    /**
     * fileExtsn attribute 값을 설정한다.
     * 
     * @param fileExtsn
     *            the fileExtsn to set
     */
    public void setFileExtsn(String fileExtsn) {
	this.fileExtsn = fileExtsn;
    }

    /**
     * fileMg attribute를 리턴한다.
     * 
     * @return the fileMg
     */
    public String getFileMg() {
	return fileMg;
    }

    /**
     * fileMg attribute 값을 설정한다.
     * 
     * @param fileMg
     *            the fileMg to set
     */
    public void setFileMg(String fileMg) {
	this.fileMg = fileMg;
    }

    /**
     * fileSn attribute를 리턴한다.
     * 
     * @return the fileSn
     */
    public String getFileSn() {
	return fileSn;
    }

    /**
     * fileSn attribute 값을 설정한다.
     * 
     * @param fileSn
     *            the fileSn to set
     */
    public void setFileSn(String fileSn) {
	this.fileSn = fileSn;
    }

    /**
     * fileStreCours attribute를 리턴한다.
     * 
     * @return the fileStreCours
     */
    public String getFileStreCours() {
	return fileStreCours;
    }

    /**
     * fileStreCours attribute 값을 설정한다.
     * 
     * @param fileStreCours
     *            the fileStreCours to set
     */
    public void setFileStreCours(String fileStreCours) {
	this.fileStreCours = fileStreCours;
    }

    /**
     * orignlfileName attribute를 리턴한다.
     * 
     * @return the orignlfileName
     */
    public String getOrignlfileName() {
	return orignlfileName;
    }

    /**
     * orignlfileName attribute 값을 설정한다.
     * 
     * @param orignlfileName
     *            the orignlfileName to set
     */
    public void setOrignlfileName(String orignlfileName) {
	this.orignlfileName = orignlfileName;
    }

    /**
     * strefileName attribute를 리턴한다.
     * 
     * @return the strefileName
     */
    public String getStrefileName() {
	return strefileName;
    }

    /**
     * strefileName attribute 값을 설정한다.
     * 
     * @param strefileName
     *            the strefileName to set
     */
    public void setStrefileName(String strefileName) {
	this.strefileName = strefileName;
    }

    /**
     * toString 메소드를 대치한다.
     */
    public String toString() {
	return ToStringBuilder.reflectionToString(this);
    }

	/**
	 * @return the menu1
	 */
	public String getMenu1() {
		return menu1;
	}

	/**
	 * @param menu1 the menu1 to set
	 */
	public void setMenu1(String menu1) {
		this.menu1 = menu1;
	}

	/**
	 * @return the menu2
	 */
	public String getMenu2() {
		return menu2;
	}

	/**
	 * @param menu2 the menu2 to set
	 */
	public void setMenu2(String menu2) {
		this.menu2 = menu2;
	}

	/**
	 * @return the menu3
	 */
	public String getMenu3() {
		return menu3;
	}

	/**
	 * @param menu3 the menu3 to set
	 */
	public void setMenu3(String menu3) {
		this.menu3 = menu3;
	}

	/**
	 * @return the cmd
	 */
	public String getCmd() {
		return cmd;
	}

	/**
	 * @param cmd the cmd to set
	 */
	public void setCmd(String cmd) {
		this.cmd = cmd;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	/**
	 * @return the widthSize
	 */
	public Integer getWidthSize() {
		return widthSize;
	}

	/**
	 * @param widthSize the widthSize to set
	 */
	public void setWidthSize(Integer widthSize) {
		this.widthSize = widthSize;
	}

	/**
	 * @return the heightSize
	 */
	public Integer getHeightSize() {
		return heightSize;
	}

	/**
	 * @param heightSize the heightSize to set
	 */
	public void setHeightSize(Integer heightSize) {
		this.heightSize = heightSize;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getFilecode() {
		return filecode;
	}

	public void setFilecode(String filecode) {
		this.filecode = filecode;
	}

	public String getFrst_date() {
		return frst_date;
	}

	public void setFrst_date(String frst_date) {
		this.frst_date = frst_date;
	}
	
	
	
}
