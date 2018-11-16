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
package four.com.bbs.service;

import java.util.Date;



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
public class BbsVO extends BbsDefaultVO {
	
    private static final long serialVersionUID = 1L;
    
    /** 게시물ID */
    private String nttId ;
 
    /** 게시판ID */
    private Integer bbsId;
    
    /** 게시판제목 */
    private String bbsName = "";
    
	/** 게시물번호 */
    private String nttNo;
    
    /** 게시물제목 */
    private String title = "";
    
    /** 게시물내용 */ 
    private String content = "";
    
    /** 공지여부 */                                
    private String noticeYn        = "N";
    
   
    
    /** 말머리ID */ //int 쓰면 에러남. property type 안맞다고.
    private Integer bbscateId       = 0;
    //새로 추가함 ^_^ 
    //말머리네임
    private String bbscateName;
    
    
    /** 조회수 */
    private Integer rdcnt            = 0;
    
    /** 사용여부 */
    private String useYn           = "Y";
    

    /** 첨부파일ID */
    private String atchFileId ="";
    
    /** 첨부파일ID */
    private String thumbAtchFileId ="";
    
    /** 동영상url */
    private String movieUrl ="";
    
    /** 첨부파일 주소*/
    private String imageUrl ="";
    
    /** 파일네임 */
    private String fileName;
    
    /** 최초등록시점 */
    private Date regDate;
    
    /** 최초등록자ID */
    private String regId ="";
    
    /** 최초수정시점 */
    private Date modDate;
    
    /** 최초수정자ID */
    private String modId   ="";
     
    /** 시간 계산명 */
    private String frstDate;
    
    /** 최초 등록 시간 */
    private String frstDateTime;
    
    
    //[New] 계산된 날짜값 ([New] 사용 유true / 무 false)
    private String newdate;
    
    //오류나서 추가시킴. 이유모름
    private String replyAuth;
    
    //검색 카테고리
    private String searchCateId;

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
	 * @return the nttNo
	 */
	public String getNttNo() {
		return nttNo;
	}

	/**
	 * @param nttNo the nttNo to set
	 */
	public void setNttNo(String nttNo) {
		this.nttNo = nttNo;
	}

	/**
	 * @return the title
	 */
	public String gettitle() {
		return title;
	}

	/**
	 * @param title the title to set
	 */
	public void settitle(String title) {
		this.title = title;
	}

	/**
	 * @return the content
	 */
	public String getcontent() {
		return content;
	}

	/**
	 * @param content the content to set
	 */
	public void setcontent(String content) {
		this.content = content;
	}

	/**
	 * @return the noticeYn
	 */
	public String getnoticeYn() {
		return noticeYn;
	}

	/**
	 * @param noticeYn the noticeYn to set
	 */
	public void setnoticeYn(String noticeYn) {
		this.noticeYn = noticeYn;
	}

	

	

	/**
	 * @return the bbscateId
	 */
	public Integer getBbscateId() {
		return bbscateId;
	}

	/**
	 * @param bbscateId the bbscateId to set
	 */
	public void setBbscateId(Integer bbscateId) {
		this.bbscateId = bbscateId;
	}

	/**
	 * @return the bbscateName
	 */
	public String getbbscateName() {
		return bbscateName;
	}

	/**
	 * @param bbscateName the bbscateName to set
	 */
	public void setbbscateName(String bbscateName) {
		this.bbscateName = bbscateName;
	}

	

	/**
	 * @return the rdcnt
	 */
	public Integer getRdcnt() {
		return rdcnt;
	}

	/**
	 * @param rdcnt the rdcnt to set
	 */
	public void setRdcnt(Integer rdcnt) {
		this.rdcnt = rdcnt;
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
	 * @return the atchFileId
	 */
	public String getAtchFileId() {
		return atchFileId;
	}

	/**
	 * @param atchFileId the atchFileId to set
	 */
	public void setAtchFileId(String atchFileId) {
		this.atchFileId = atchFileId;
	}

	/**
	 * @return the movieUrl
	 */
	public String getMovieUrl() {
		return movieUrl;
	}

	/**
	 * @param movieUrl the movieUrl to set
	 */
	public void setMovieUrl(String movieUrl) {
		this.movieUrl = movieUrl;
	}

	

	/**
	 * @return the fileName
	 */
	public String getfileName() {
		return fileName;
	}

	/**
	 * @param fileName the fileName to set
	 */
	public void setfileName(String fileName) {
		this.fileName = fileName;
	}

	/**
	 * @return the regDate
	 */
	public Date getregDate() {
		return regDate;
	}

	/**
	 * @param regDate the regDate to set
	 */
	public void setregDate(Date regDate) {
		this.regDate = regDate;
	}

	/**
	 * @return the regId
	 */
	public String getregId() {
		return regId;
	}

	/**
	 * @param regId the regId to set
	 */
	public void setregId(String regId) {
		this.regId = regId;
	}

	/**
	 * @return the modDate
	 */
	public Date getmodDate() {
		return modDate;
	}

	/**
	 * @param modDate the modDate to set
	 */
	public void setmodDate(Date modDate) {
		this.modDate = modDate;
	}

	/**
	 * @return the modId
	 */
	public String getmodId() {
		return modId;
	}

	/**
	 * @param modId the modId to set
	 */
	public void setmodId(String modId) {
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

	/**
	 * @return the newdate
	 */
	public String getNewdate() {
		return newdate;
	}

	/**
	 * @param newdate the newdate to set
	 */
	public void setNewdate(String newdate) {
		this.newdate = newdate;
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
	 * @return the thumbAtchFileId
	 */
	public String getThumbAtchFileId() {
		return thumbAtchFileId;
	}

	/**
	 * @param thumbAtchFileId the thumbAtchFileId to set
	 */
	public void setThumbAtchFileId(String thumbAtchFileId) {
		this.thumbAtchFileId = thumbAtchFileId;
	}

	public String getSearchCateId() {
		return searchCateId;
	}

	public void setSearchCateId(String searchCateId) {
		this.searchCateId = searchCateId;
	}

	
	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	@Override
	public String toString() {
		return "BbsVO [nttId=" + nttId + ", bbsId=" + bbsId + ", bbsName="
				+ bbsName + ", nttNo=" + nttNo + ", title=" + title
				+ ", content=" + content + ", noticeYn=" + noticeYn
				+ ", bbscateId=" + bbscateId + ", bbscateName=" + bbscateName
				+ ", rdcnt=" + rdcnt + ", useYn=" + useYn + ", atchFileId="
				+ atchFileId + ", thumbAtchFileId=" + thumbAtchFileId
				+ ", movieUrl=" + movieUrl + ", imageUrl=" + imageUrl
				+ ", fileName=" + fileName + ", regDate=" + regDate
				+ ", regId=" + regId + ", modDate=" + modDate + ", modId="
				+ modId + ", frstDate=" + frstDate + ", frstDateTime="
				+ frstDateTime + ", newdate=" + newdate + ", replyAuth="
				+ replyAuth + ", searchCateId=" + searchCateId + "]";
	}

	

	

	
}
