package four.mng.banner.service;
/**  
	* @Class Name : BbsDefaultVO.java
	* @Description : BbsDefaultVO Class

* @ author 운영지원팀 이록근
* @ since 2018.08.16
* @ version 1.0
* @ see
*
* << 개정이력 (Modification Information) >>
*
*    수정일    	  수정자                수정내용
 *  ----------    ---------            ---------------------------
 *  18.08.16 	  이록근 		최초 생성
 *  
 *  Copyright (C) by MOPAS All right reserved.
 */

public class bannerVO {

	
	private String fileId1;
	private String fileId2;
	private String fileId3;
	
	
	public bannerVO() {
		// TODO Auto-generated constructor stub
	}
	
	public bannerVO(String fileId1, String fileId2, String fileId3) {
		super();
		this.fileId1 = fileId1;
		this.fileId2 = fileId2;
		this.fileId3 = fileId3;
	}

	public String getFileId1() {
		return fileId1;
	}
	public void setFileId1(String fileId1) {
		this.fileId1 = fileId1;
	}
	public String getFileId2() {
		return fileId2;
	}
	public void setFileId2(String fileId2) {
		this.fileId2 = fileId2;
	}
	public String getFileId3() {
		return fileId3;
	}
	public void setFileId3(String fileId3) {
		this.fileId3 = fileId3;
	}
	
	
	@Override
	public String toString() {
		return "bannerVO [fileId1=" + fileId1 + ", fileId2=" + fileId2 + ", fileId3=" + fileId3 + "]";
	}
	

}
