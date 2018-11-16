package four.com.evnet.service;

import java.io.Serializable;

/**  
* @Class Name : EventDefaultVO.java
* @Description : EventDefaultVO Class

* @ author 운영지원팀 이록근
* @ since 2018.08.08
* @ version 1.0
* @ see
*
* << 개정이력 (Modification Information) >>
*
*    수정일    	  수정자                수정내용
*  ----------    ---------            ---------------------------
*  18.08.08   	  이록근 		최초 생성
*  
*  Copyright (C) by MOPAS All right reserved.
*/

@SuppressWarnings("serial")
public class EventDefaultVO implements Serializable {

	 /** 현재페이지 */
    private int pageIndex = 1;
    
    /** 페이지갯수 */
    private int pageUnit = 10;
    
    /** 페이지사이즈 */
    private int pageSize = 10;

    /** firstIndex */
    private int firstIndex = 0;

    /** lastIndex */
    private int lastIndex = 1;
    
    /** recordCountPerPage */
    private int recordCountPerPage = 10;
    
    private String userId;

	public int getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}

	public int getPageUnit() {
		return pageUnit;
	}

	public void setPageUnit(int pageUnit) {
		this.pageUnit = pageUnit;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getFirstIndex() {
		return firstIndex;
	}

	public void setFirstIndex(int firstIndex) {
		this.firstIndex = firstIndex;
	}

	public int getLastIndex() {
		return lastIndex;
	}

	public void setLastIndex(int lastIndex) {
		this.lastIndex = lastIndex;
	}

	public int getRecordCountPerPage() {
		return recordCountPerPage;
	}

	public void setRecordCountPerPage(int recordCountPerPage) {
		this.recordCountPerPage = recordCountPerPage;
	}
	

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "EventDefaultVO [pageIndex=" + pageIndex + ", pageUnit=" + pageUnit + ", pageSize=" + pageSize
				+ ", firstIndex=" + firstIndex + ", lastIndex=" + lastIndex + ", recordCountPerPage="
				+ recordCountPerPage + ", userId=" + userId + "]";
	}

	
    
	
    
	
}
