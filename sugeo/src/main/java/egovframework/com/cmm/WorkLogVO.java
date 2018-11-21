package egovframework.com.cmm;

/**  
 	* @Class Name : WorkLogVO.java
 	* @Description : WorkLogVO Class

	* @ author 운영지원팀 이록근
	* @ since 2018.08.20
	* @ version 1.0
	* @ see
	*
	* << 개정이력 (Modification Information) >>
	*
	*    수정일    	  수정자                수정내용
	 *  ----------    ---------            ---------------------------
	 *  18.08.120  	  이록근 		최초 생성
	 *  
	 *  Copyright (C) by MOPAS All right reserved.
	 */


public class WorkLogVO {

	
	private String logId;
	private String userId;
	private String accessIp;
	private String url;
	private String content;
	private String logDate;
	private String logType;
	
	
	public String getLogId() {
		return logId;
	}
	public void setLogId(String logId) {
		this.logId = logId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getAccessIp() {
		return accessIp;
	}
	public void setAccessIp(String accessIp) {
		this.accessIp = accessIp;
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
	public String getLogDate() {
		return logDate;
	}
	public void setLogDate(String logDate) {
		this.logDate = logDate;
	}
	public String getLogType() {
		return logType;
	}
	public void setLogType(String logType) {
		this.logType = logType;
	}
	@Override
	public String toString() {
		return "WorkLogVO [logId=" + logId + ", userId=" + userId + ", accessIp=" + accessIp + ", url=" + url
				+ ", content=" + content + ", logDate=" + logDate + ", logType=" + logType + "]";
	}
	
	
}
