/*
 * Copyright 2008-2009 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package four.com.bbs.cmt.service;

import java.util.Date;

import four.com.bbs.service.BbsDefaultVO;


/**  
 * @Class Name : BbsVO.java
 * @Description : BbsVO Class
 * @Modification Information  
 * @
 * @  수정일              수정자               수정내용
 * @ --------------     ---------     -------------------------------
 * @ 2013.08.20      조은태               최초생성
 * 
 * @author (주)거산디에스엔
 * @since 2013. 08.20
 * @version 1.0
 * @see
 * 
 *  Copyright (C) by MOPAS All right reserved.
 */
@SuppressWarnings("serial")
public class BbsCommentVO extends BbsDefaultVO {
	
    // 게시물 ID
	private String nttId;
	
	//게시판 ID
	private Integer bbsId;
	
	//댓글번호
	private String answerNo;
	
	//댓글그룹번호
	private int answerGno ;
	
	//부모댓글번호
	private int pAnswerNo;
	
	
	//댓글내용
	private String content;
	
	//사용여부
	private String useYn;
	
	//등록시점
	private Date regDate;
	
	//등록자ID
	private String regId;
	
	//최종수정시점
	private Date modDate;
	
	//최종수정자ID
	private String modId;
	
	private String frstDate;
	
	private String frstDateTime;

	/**
	 * @return the nttId
	 */
	public String getNttId() {
		return nttId;
	}
	/**
	 * @param nttId the nttId to set
	 */
	public void setNttId(String nttId) {
		this.nttId = nttId;
	}
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
	 * @return the answerNo
	 */
	public String getAnswerNo() {
		return answerNo;
	}
	/**
	 * @param answerNo the answerNo to set
	 */
	public void setAnswerNo(String answerNo) {
		this.answerNo = answerNo;
	}
	/**
	 * @return the answerGno
	 */
	public int getAnswerGno() {
		return answerGno;
	}
	/**
	 * @param answerGno the answerGno to set
	 */
	public void setAnswerGno(int answerGno) {
		this.answerGno = answerGno;
	}
	/**
	 * @return the pAnswerNo
	 */
	public int getpAnswerNo() {
		return pAnswerNo;
	}
	/**
	 * @param pAnswerNo the pAnswerNo to set
	 */
	public void setpAnswerNo(int pAnswerNo) {
		this.pAnswerNo = pAnswerNo;
	}
	
	
	/**
	 * @return the content
	 */
	public String getContent() {
		return content;
	}
	/**
	 * @param content the answer to set
	 */
	public void setContent(String content) {
		this.content = content;
	}
	/**
	 * @return the useYn
	 */
	public String getUseYn() {
		return useYn;
	}
	/**
	 * @param useYn the useYn to set
	 */
	public void setUseYn(String useYn) {
		this.useYn = useYn;
	}
	/**
	 * @return the regDate
	 */
	public Date getRegDate() {
		return regDate;
	}
	/**
	 * @param regDate the regDate to set
	 */
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}
	/**
	 * @return the regId
	 */
	public String getRegId() {
		return regId;
	}
	/**
	 * @param regId the regId to set
	 */
	public void setRegId(String regId) {
		this.regId = regId;
	}
	/**
	 * @return the modDate
	 */
	public Date getModDate() {
		return modDate;
	}
	/**
	 * @param modDate the modDate to set
	 */
	public void setModDate(Date modDate) {
		this.modDate = modDate;
	}
	/**
	 * @return the modId
	 */
	public String getModId() {
		return modId;
	}
	/**
	 * @param modId the modId to set
	 */
	public void setModId(String modId) {
		this.modId = modId;
	}
	
	
	/**
	 * @return the frstDate
	 */
	public String getFrstDate() {
		return frstDate;
	}
	/**
	 * @param frstDate the frstDate to set
	 */
	public void setFrstDate(String frstDate) {
		this.frstDate = frstDate;
	}
	/**
	 * @return the frstDateTime
	 */
	public String getFrstDateTime() {
		return frstDateTime;
	}
	/**
	 * @param frstDateTime the frstDateTime to set
	 */
	public void setFrstDateTime(String frstDateTime) {
		this.frstDateTime = frstDateTime;
	}
	
	
	

}
