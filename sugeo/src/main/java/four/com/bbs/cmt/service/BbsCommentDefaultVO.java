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

import java.io.Serializable;


/**  
 	* @Class Name : BbsDefaultVO.java
 	* @Description : BbsDefaultVO Class
 	* @Modification Information  
 	* @
	 *    수정일    	  수정자               수정내용
	 *  ----------      	 ---------      ---------------------------
	 *  13.08.21   	  조은태 		      최초 생성z
	 * 
	 * @ author (주)거산디에스엔 조은태
	 * @ since 2013.08.21
	 * @ version 1.0
	 * @ see
	 * 
	 *  Copyright (C) by MOPAS All right reserved.
	 */
public class BbsCommentDefaultVO implements Serializable {
	/** 검색조건 */
    private String searchCondition = "";
    
    /** 검색Keyword */
    private String searchKeyword = "";
    
    /** 검색사용여부 */
    private String searchUseYn = "";
    
    /** 현재페이지 */
    private int pageIndex = 1;
    
    /** 페이지갯수 */
    private int pageUnit = 10;
    
    /** 페이지사이즈 */
    private int pageSize = 10;

    /** firstIndex */
    private int firstIndex = 1;

    /** lastIndex */
    private int lastIndex = 1;
    
    /** recordCountPerPage */
    private int recordCountPerPage = 10;
    
    /** menu1 */
    private String menu1 = "";

	/** menu2 */
    private String menu2 = "";

    /** menu2 */
    private String menu3 = "";
    
    /** cmd */
    private String cmd = "";
    
    private Integer bbsId;
    private Integer nttId;
    //private int no;
    private String bid;
    private int answerNo;
    private int answerGno;
	/**
	 * @return the searchCondition
	 */
	public String getSearchCondition() {
		return searchCondition;
	}
	/**
	 * @param searchCondition the searchCondition to set
	 */
	public void setSearchCondition(String searchCondition) {
		this.searchCondition = searchCondition;
	}
	/**
	 * @return the searchKeyword
	 */
	public String getSearchKeyword() {
		return searchKeyword;
	}
	/**
	 * @param searchKeyword the searchKeyword to set
	 */
	public void setSearchKeyword(String searchKeyword) {
		this.searchKeyword = searchKeyword;
	}
	/**
	 * @return the searchUseYn
	 */
	public String getSearchUseYn() {
		return searchUseYn;
	}
	/**
	 * @param searchUseYn the searchUseYn to set
	 */
	public void setSearchUseYn(String searchUseYn) {
		this.searchUseYn = searchUseYn;
	}
	/**
	 * @return the pageIndex
	 */
	public int getPageIndex() {
		return pageIndex;
	}
	/**
	 * @param pageIndex the pageIndex to set
	 */
	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}
	/**
	 * @return the pageUnit
	 */
	public int getPageUnit() {
		return pageUnit;
	}
	/**
	 * @param pageUnit the pageUnit to set
	 */
	public void setPageUnit(int pageUnit) {
		this.pageUnit = pageUnit;
	}
	/**
	 * @return the pageSize
	 */
	public int getPageSize() {
		return pageSize;
	}
	/**
	 * @param pageSize the pageSize to set
	 */
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	/**
	 * @return the firstIndex
	 */
	public int getFirstIndex() {
		return firstIndex;
	}
	/**
	 * @param firstIndex the firstIndex to set
	 */
	public void setFirstIndex(int firstIndex) {
		this.firstIndex = firstIndex;
	}
	/**
	 * @return the lastIndex
	 */
	public int getLastIndex() {
		return lastIndex;
	}
	/**
	 * @param lastIndex the lastIndex to set
	 */
	public void setLastIndex(int lastIndex) {
		this.lastIndex = lastIndex;
	}
	/**
	 * @return the recordCountPerPage
	 */
	public int getRecordCountPerPage() {
		return recordCountPerPage;
	}
	/**
	 * @param recordCountPerPage the recordCountPerPage to set
	 */
	public void setRecordCountPerPage(int recordCountPerPage) {
		this.recordCountPerPage = recordCountPerPage;
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
	 * @return the nttId
	 */
	public Integer getNttId() {
		return nttId;
	}
	/**
	 * @param nttId the nttId to set
	 */
	public void setNttId(Integer nttId) {
		this.nttId = nttId;
	}
	/**
	 * @return the bid
	 */
	public String getBid() {
		return bid;
	}
	/**
	 * @param bid the bid to set
	 */
	public void setBid(String bid) {
		this.bid = bid;
	}
	/**
	 * @return the answerNo
	 */
	public int getAnswerNo() {
		return answerNo;
	}
	/**
	 * @param answerNo the answerNo to set
	 */
	public void setAnswerNo(int answerNo) {
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
    


    
	
    
    
    
/*    @Override
	public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
*/
}
