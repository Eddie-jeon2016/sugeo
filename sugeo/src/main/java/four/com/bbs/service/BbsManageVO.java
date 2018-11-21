package four.com.bbs.service;

/**
 * @Class Name : BbsMasterVO.java
 * @Description : BbsMasterVO Class
 * @Modification Information
 * @
 * @  수정일              수정자               수정내용
 * @ --------------     ---------     -------------------------------
 * @ 2013.08.20      조은태               최초생성
 *
 * @ author (주)거산디에스엔 조은태
 * @ since 2013.08.21
 * @ version 1.0
 * @ see
 *
 *  Copyright (C) by MOPAS All right reserved.
 */

public class BbsManageVO extends BbsDefaultVO {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	/*게시판ID*/
	private Integer bbsId = 0;

	/*게시판명*/
	private String bbsName = "";

	//게시판소개
	private String bbsIntrcn = "";

	//게시판유형코드
	private String bbsTyCode = "";

	//게시판유형코드명
	private String bbsTyCodeName = "";

	//게시판속성코드
	private String bbsAttrbCode= "";

	//답글가능여부
	private String replyPosblYn= "";

	//댓글가능여부
	private String answerYn= "";


	//파일첨부가능여부
	private String fileAtchPosblYn= "";

	//읽기권한
	private String readAuth= "";

	//쓰기권한
	private String writeAuth= "";

	//댓글권한
	private String answerAuth= "";

	//답글권한
	private String replyAuth= "";

	//첨부가능파일숫자
	private int atchPosblFileNumber= 0;

	

	//사용여부
	private String useYn= "";

	//템플릿ID
	private String tmplatId= "";

	//menuNo
	private Integer menuNo =0;

	//menuNm
	private String menuNm= "";

	//최초등록시점
	private String regDate= "";

	//최초등록자ID
	private int regId=0;

	//최초수정시점
	private String modDate= "";

	//최초수정자ID
	private int modId=0;


	/**
	 * @return the bbsId
	 */
	public Integer getBbsId() {
		return bbsId;
	}

	/**
	 * @param bbsId the bbsId to set
	 */
	public void setBbsId(Integer bbsId) {
		this.bbsId = bbsId;
	}

	/**
	 * @return the bbsName
	 */
	public String getbbsName() {
		return bbsName;
	}

	/**
	 * @param bbsName the bbsName to set
	 */
	public void setbbsName(String bbsName) {
		this.bbsName = bbsName;
	}

	/**
	 * @return the bbsIntrcn
	 */
	public String getBbsIntrcn() {
		return bbsIntrcn;
	}

	/**
	 * @param bbsIntrcn the bbsIntrcn to set
	 */
	public void setBbsIntrcn(String bbsIntrcn) {
		this.bbsIntrcn = bbsIntrcn;
	}

	/**
	 * @return the bbsTyCode
	 */
	public String getBbsTyCode() {
		return bbsTyCode;
	}

	/**
	 * @param bbsTyCode the bbsTyCode to set
	 */
	public void setBbsTyCode(String bbsTyCode) {
		this.bbsTyCode = bbsTyCode;
	}

	/**
	 * @return the bbsTyCodeName
	 */
	public String getbbsTyCodeName() {
		return bbsTyCodeName;
	}

	/**
	 * @param bbsTyCodeName the bbsTyCodeName to set
	 */
	public void setbbsTyCodeName(String bbsTyCodeName) {
		this.bbsTyCodeName = bbsTyCodeName;
	}

	/**
	 * @return the bbsAttrbCode
	 */
	public String getBbsAttrbCode() {
		return bbsAttrbCode;
	}

	/**
	 * @param bbsAttrbCode the bbsAttrbCode to set
	 */
	public void setBbsAttrbCode(String bbsAttrbCode) {
		this.bbsAttrbCode = bbsAttrbCode;
	}

	/**
	 * @return the replyPosblYn
	 */
	public String getreplyPosblYn() {
		return replyPosblYn;
	}

	/**
	 * @param replyPosblYn the replyPosblYn to set
	 */
	public void setreplyPosblYn(String replyPosblYn) {
		this.replyPosblYn = replyPosblYn;
	}

	/**
	 * @return the answerYn
	 */
	public String getanswerYn() {
		return answerYn;
	}

	/**
	 * @param answerYn the answerYn to set
	 */
	public void setanswerYn(String answerYn) {
		this.answerYn = answerYn;
	}

	

	

	/**
	 * @return the fileAtchPosblYn
	 */
	public String getfileAtchPosblYn() {
		return fileAtchPosblYn;
	}

	/**
	 * @param fileAtchPosblYn the fileAtchPosblYn to set
	 */
	public void setfileAtchPosblYn(String fileAtchPosblYn) {
		this.fileAtchPosblYn = fileAtchPosblYn;
	}

	/**
	 * @return the readAuth
	 */
	public String getReadAuth() {
		return readAuth;
	}

	/**
	 * @param readAuth the readAuth to set
	 */
	public void setReadAuth(String readAuth) {
		this.readAuth = readAuth;
	}

	/**
	 * @return the writeAuth
	 */
	public String getWriteAuth() {
		return writeAuth;
	}

	/**
	 * @param writeAuth the writeAuth to set
	 */
	public void setWriteAuth(String writeAuth) {
		this.writeAuth = writeAuth;
	}

	/**
	 * @return the answerAuth
	 */
	public String getAnswerAuth() {
		return answerAuth;
	}

	/**
	 * @param answerAuth the answerAuth to set
	 */
	public void setAnswerAuth(String answerAuth) {
		this.answerAuth = answerAuth;
	}

	/**
	 * @return the replyAuth
	 */
	public String getReplyAuth() {
		return replyAuth;
	}

	/**
	 * @param replyAuth the replyAuth to set
	 */
	public void setReplyAuth(String replyAuth) {
		this.replyAuth = replyAuth;
	}

	/**
	 * @return the atchPosblFileNumber
	 */
	public int getAtchPosblFileNumber() {
		return atchPosblFileNumber;
	}

	/**
	 * @param atchPosblFileNumber the atchPosblFileNumber to set
	 */
	public void setAtchPosblFileNumber(int atchPosblFileNumber) {
		this.atchPosblFileNumber = atchPosblFileNumber;
	}

	

	/**
	 * @return the useYn
	 */
	public String getuseYn() {
		return useYn;
	}

	/**
	 * @param useYn the useYn to set
	 */
	public void setuseYn(String useYn) {
		this.useYn = useYn;
	}

	/**
	 * @return the tmplatId
	 */
	public String getTmplatId() {
		return tmplatId;
	}

	/**
	 * @param tmplatId the tmplatId to set
	 */
	public void setTmplatId(String tmplatId) {
		this.tmplatId = tmplatId;
	}

	/**
	 * @return the menuNo
	 */
	public Integer getMenuNo() {
		return menuNo;
	}

	/**
	 * @param menuNo the menuNo to set
	 */
	public void setMenuNo(Integer menuNo) {
		this.menuNo = menuNo;
	}

	/**
	 * @return the menuNm
	 */
	public String getMenuNm() {
		return menuNm;
	}

	/**
	 * @param menuNm the menuNm to set
	 */
	public void setMenuNm(String menuNm) {
		this.menuNm = menuNm;
	}

	/**
	 * @return the regDate
	 */
	public String getregDate() {
		return regDate;
	}

	/**
	 * @param regDate the regDate to set
	 */
	public void setregDate(String regDate) {
		this.regDate = regDate;
	}

	/**
	 * @return the regId
	 */
	public int getregId() {
		return regId;
	}

	/**
	 * @param regId the regId to set
	 */
	public void setregId(int regId) {
		this.regId = regId;
	}

	/**
	 * @return the modDate
	 */
	public String getmodDate() {
		return modDate;
	}

	/**
	 * @param modDate the modDate to set
	 */
	public void setmodDate(String modDate) {
		this.modDate = modDate;
	}

	/**
	 * @return the modId
	 */
	public int getmodId() {
		return modId;
	}

	/**
	 * @param modId the modId to set
	 */
	public void setmodId(int modId) {
		this.modId = modId;
	}

	@Override
	public String toString() {
		return "BbsManageVO [bbsId=" + bbsId + ", bbsName=" + bbsName + ", bbsIntrcn=" + bbsIntrcn + ",\n bbsTyCode="
				+ bbsTyCode + ", bbsTyCodeName=" + bbsTyCodeName + ", bbsAttrbCode=" + bbsAttrbCode + ",\n replyPosblYn="
				+ replyPosblYn + ", answerYn=" + answerYn + ", fileAtchPosblYn=" + fileAtchPosblYn + ",\n readAuth="
				+ readAuth + ", writeAuth=" + writeAuth + ", answerAuth=" + answerAuth + ",\n replyAuth=" + replyAuth
				+ ", atchPosblFileNumber=" + atchPosblFileNumber + ", useYn=" + useYn + ",\n tmplatId=" + tmplatId
				+ ", menuNo=" + menuNo + ", menuNm=" + menuNm + ", regDate=" + regDate + ",\n regId=" + regId
				+ ", modDate=" + modDate + ", modId=" + modId + "]";
	}

	
	

	
}
