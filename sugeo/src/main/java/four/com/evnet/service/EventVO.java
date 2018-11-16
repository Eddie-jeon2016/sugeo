package four.com.evnet.service;

/**  
	* @Class Name : EventVO.java
	* @Description : EventVO Class

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


public class EventVO  extends EventDefaultVO{

	private String eventNo;
	private String eventType;
	private String title;
	private String startDate;
	private String endDate;
	private String openYn;
	private String regDate;
	private String regId;
	private String modDate;
	private String modId;
	private String useYn;
	private String url;
	private String content;
	private String eventStmt;
	private String startHour;
	private String endHour;	
	private String thumbAtchFileId;
		
	public String getEventNo() {
		return eventNo;
	}
	public void setEventNo(String eventNo) {
		this.eventNo = eventNo;
	}
	public String getEventType() {
		return eventType;
	}
	public void setEventType(String eventType) {
		this.eventType = eventType;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getOpenYn() {
		return openYn;
	}
	public void setOpenYn(String openYn) {
		this.openYn = openYn;
	}
	public String getRegDate() {
		return regDate;
	}
	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}
	public String getRegId() {
		return regId;
	}
	public void setRegId(String regId) {
		this.regId = regId;
	}
	public String getModDate() {
		return modDate;
	}
	public void setModDate(String modDate) {
		this.modDate = modDate;
	}
	public String getModId() {
		return modId;
	}
	public void setModId(String modId) {
		this.modId = modId;
	}
	public String getUseYn() {
		return useYn;
	}
	public void setUseYn(String useYn) {
		this.useYn = useYn;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getEventStmt() {
		return eventStmt;
	}
	public void setEventStmt(String eventStmt) {
		this.eventStmt = eventStmt;
	}
	public String getThumbAtchFileId() {
		return thumbAtchFileId;
	}
	public void setThumbAtchFileId(String thumbAtchFileId) {
		this.thumbAtchFileId = thumbAtchFileId;
	}
	
	public String getStartHour() {
		return startHour;
	}
	public void setStartHour(String startHour) {
		this.startHour = startHour;
	}
	public String getEndHour() {
		return endHour;
	}
	public void setEndHour(String endHour) {
		this.endHour = endHour;
	}
	@Override
	public String toString() {
		return "EventVO [eventNo=" + eventNo + ", eventType=" + eventType + ", title=" + title + ", startDate="
				+ startDate + ", endDate=" + endDate + ", openYn=" + openYn + ", regDate=" + regDate + ", regId="
				+ regId + ", modDate=" + modDate + ", modId=" + modId + ", useYn=" + useYn + ", url=" + url
				+ ", content=" + content + ", eventStmt=" + eventStmt + ", thumbAtchFileId=" + thumbAtchFileId + "]";
	}
	
	
	

	
	
}
