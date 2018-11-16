package four.mng.auth.service;

public class AuthVO {
	
	/** 고유아이디 */
	private String uniqId;
	
	/** 아이디 */
	private String userId;
	
	/** 패스워드 */
	private String pswd;
	
	/** 이름 */
	private String userName;
	
	/** 권한코드 */
	private String roleCode;
	
	
	/** 등록일 */
	private String regDate;
	
	/** 탈퇴일 */
	private String extDate;
	
	/** 사용여부 */
	private String useYn;
	
	
	/** 비밀번호 비교 */
	private String nowPswd;
	
	/** 아이디 변경 */
	private String tempId;
	
	/*
	 * 접속로그
	 */
	
	/** 접근아이피 */
	private String accessIp;
	
	/** 로그인 일시 */
	private String logDate;

	/*
	 * 작업로그
	 */
	
	/** 작업로그 ID */
	private String logId;
	
	/** 작업URL */
	private String url;
	
	/** 작업내용 */
	private String content;
	
	/** 구분타입 */
	private String logType;
	
	
	public String getUniqId() {
		return uniqId;
	}

	public void setUniqId(String uniqId) {
		this.uniqId = uniqId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPswd() {
		return pswd;
	}

	public void setPswd(String pswd) {
		this.pswd = pswd;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getRoleCode() {
		return roleCode;
	}

	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}


	public String getRegDate() {
		return regDate;
	}

	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}

	public String getExtDate() {
		return extDate;
	}

	public void setExtDate(String extDate) {
		this.extDate = extDate;
	}

	public String getUseYn() {
		return useYn;
	}

	public void setUseYn(String useYn) {
		this.useYn = useYn;
	}


	public String getNowPswd() {
		return nowPswd;
	}

	public void setNowPswd(String nowPswd) {
		this.nowPswd = nowPswd;
	}

	public String getAccessIp() {
		return accessIp;
	}

	public void setAccessIp(String accessIp) {
		this.accessIp = accessIp;
	}

	public String getLogDate() {
		return logDate;
	}

	public void setLogDate(String logDate) {
		this.logDate = logDate;
	}

	public String getTempId() {
		return tempId;
	}

	public void setTempId(String tempId) {
		this.tempId = tempId;
	}

	public String getLogId() {
		return logId;
	}

	public void setLogId(String logId) {
		this.logId = logId;
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

	public String getLogType() {
		return logType;
	}

	public void setLogType(String logType) {
		this.logType = logType;
	}

	
}
