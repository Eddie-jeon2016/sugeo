package four.com.bbs.service;

import java.io.Serializable;


/**  
 	* @Class Name : BbsDefaultVO.java
 	* @Description : BbsDefaultVO Class
 	* @Modification Information  
 	* @
	 *    수정일    	  수정자               수정내용
	 *  ----------      	 ---------      ---------------------------
	 *  13.08.21   	  조은태 		      최초 생성
	 * 
	 * @ author (주)거산디에스엔 조은태
	 * @ since 2013.08.21
	 * @ version 1.0
	 * @ see
	 * 
	 *  Copyright (C) by MOPAS All right reserved.
	 */
@SuppressWarnings("serial")
public class BbsDefaultVO implements Serializable {
	/** 검색조건 */
    private String searchCondition = "";
    
    /** 검색 공유 여부 */
    private String searchShareAt = "";
    
    /** 미디어카페를 제외한 리스트 */
    private String searchCafeException = "";
    
    /** 검색카테고리id */
    private String searchCateId ;
    
    /** 검색Keyword */
    private String searchKeyword = "";
    
    /** 검색사용여부 */
    private String searchUseYn = "";
    
    /** 현재페이지 */
    private int pageIndex = 1;
    
    /** 페이지갯수 */
    private int pageUnit = 9;
    
    /** 페이지사이즈 */
    private int pageSize = 10;

    /** firstIndex */
    private int firstIndex = 0;

    /** lastIndex */
    private int lastIndex = 1;
    
    /** 게시판 id */
	private Integer bbsId;
	
	/** 게시물 id */
	private String nttId;
	
	/** r_id */
	private String bid;
	
	/** ntcr_id */
	private String ntcrId;

    /** recordCountPerPage */
    private int recordCountPerPage = 10;
    
    /** menu1 */
    private String menu1 = "";    
    
    /** menu2 */
    private String menu2 = "";
    
    /** menu3 */
    private String menu3 = "";
    
    /** menuNo */
    private Integer menuNo;
    
    /** smenuNo */
    private Integer smenuNo;
    
    /** cmd */
    private String cmd = "";
    
    private String no;
    
    private String rssLink;
    
    //말머리 클릭 시 페이지 이동을 위해 넣었음.
    private String bidNo;
    
    //위젯 수정을 위한 검색조건
    private String searchWigetList;

    //위젯 수정을 위한 검색조건
    private String searchBbs_ty_code;
    
    //공유단 일회성 구분자값
    private String shareGubun = "";
    
    private String ceCode;
    
    private String ceCodeNm;
    
    private String domain;
    
    public String getShareGubun() {
		return shareGubun;
	}

	public void setShareGubun(String shareGubun) {
		this.shareGubun = shareGubun;
	}

	public String getSearchWigetList() {
		return searchWigetList;
	}

	public void setSearchWigetList(String searchWigetList) {
		this.searchWigetList = searchWigetList;
	}

	/**
	 * @return the searchBbs_ty_code
	 */
	public String getSearchBbs_ty_code() {
		return searchBbs_ty_code;
	}

	/**
	 * @param searchBbs_ty_code the searchBbs_ty_code to set
	 */
	public void setSearchBbs_ty_code(String searchBbs_ty_code) {
		this.searchBbs_ty_code = searchBbs_ty_code;
	}

	public String getBid() {
		return bid;
	}

	public void setBid(String bid) {
		this.bid = bid;
	}

	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	public String getMenu1() {
		return menu1;
	}

	public void setMenu1(String menu1) {
		this.menu1 = menu1;
	}

	public String getMenu2() {
		return menu2;
	}

	public void setMenu2(String menu2) {
		this.menu2 = menu2;
	}

	public String getMenu3() {
		return menu3;
	}

	public void setMenu3(String menu3) {
		this.menu3 = menu3;
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
	 * @return the smenuNo
	 */
	public Integer getSmenuNo() {
		return smenuNo;
	}

	/**
	 * @param smenuNo the smenuNo to set
	 */
	public void setSmenuNo(Integer smenuNo) {
		this.smenuNo = smenuNo;
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

	public String getSearchShareAt() {
		return searchShareAt;
	}

	public void setSearchShareAt(String searchShareAt) {
		this.searchShareAt = searchShareAt;
	}

	public String getSearchCafeException() {
		return searchCafeException;
	}

	public void setSearchCafeException(String searchCafeException) {
		this.searchCafeException = searchCafeException;
	}

	public String getSearchCateId() {
		return searchCateId;
	}

	public void setSearchCateId(String searchCateId) {
		this.searchCateId = searchCateId;
	}

	public String getSearchCondition() {
        return searchCondition;
    }

    public void setSearchCondition(String searchCondition) {
        this.searchCondition = searchCondition;
    }

    public String getSearchKeyword() {
        return searchKeyword;
    }

    public void setSearchKeyword(String searchKeyword) {
        this.searchKeyword = searchKeyword;
    }

    public String getSearchUseYn() {
        return searchUseYn;
    }

    public void setSearchUseYn(String searchUseYn) {
        this.searchUseYn = searchUseYn;
    }

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

    
	public String getCmd() {
		return cmd;
	}

	public void setCmd(String cmd) {
		this.cmd = cmd;
	}


	/**
	 * @return the rssLink
	 */
	public String getRssLink() {
		return rssLink;
	}

	/**
	 * @param rssLink the rssLink to set
	 */
	public void setRssLink(String rssLink) {
		this.rssLink = rssLink;
	}

	/**
	 * @return the ceCode
	 */
	public String getCeCode() {
		return ceCode;
	}

	/**
	 * @param ceCode the ceCode to set
	 */
	public void setCeCode(String ceCode) {
		this.ceCode = ceCode;
	}

	/**
	 * @return the ceCodeNm
	 */
	public String getCeCodeNm() {
		return ceCodeNm;
	}

	/**
	 * @param ceCodeNm the ceCodeNm to set
	 */
	public void setCeCodeNm(String ceCodeNm) {
		this.ceCodeNm = ceCodeNm;
	}

	/**
	 * @return the domain
	 */
	public String getDomain() {
		return domain;
	}

	/**
	 * @param domain the domain to set
	 */
	public void setDomain(String domain) {
		this.domain = domain;
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
	 * @return the ntcrId
	 */
	public String getNtcrId() {
		return ntcrId;
	}

	/**
	 * @param ntcrId the ntcrId to set
	 */
	public void setNtcrId(String ntcrId) {
		this.ntcrId = ntcrId;
	}

	/**
	 * @return the bidNo
	 */
	public String getBidNo() {
		return bidNo;
	}

	/**
	 * @param bidNo the bidNo to set
	 */
	public void setBidNo(String bidNo) {
		this.bidNo = bidNo;
	}

	@Override
	public String toString() {
		return "BbsDefaultVO [searchCondition=" + searchCondition
				+ ", searchShareAt=" + searchShareAt + ", searchCafeException="
				+ searchCafeException + ", searchCateId=" + searchCateId
				+ ", searchKeyword=" + searchKeyword + ", searchUseYn="
				+ searchUseYn + ", pageIndex=" + pageIndex + ", pageUnit="
				+ pageUnit + ", pageSize=" + pageSize + ", firstIndex="
				+ firstIndex + ", lastIndex=" + lastIndex + ", bbsId=" + bbsId
				+ ", nttId=" + nttId + ", bid=" + bid + ", ntcrId=" + ntcrId
				+ ", recordCountPerPage=" + recordCountPerPage + ", menu1="
				+ menu1 + ", menu2=" + menu2 + ", menu3=" + menu3 + ", menuNo="
				+ menuNo + ", smenuNo=" + smenuNo + ", cmd=" + cmd + ", no="
				+ no + ", rssLink=" + rssLink + ", bidNo=" + bidNo
				+ ", searchWigetList=" + searchWigetList
				+ ", searchBbs_ty_code=" + searchBbs_ty_code + ", shareGubun="
				+ shareGubun + ", ceCode=" + ceCode + ", ceCodeNm=" + ceCodeNm
				+ ", domain=" + domain + "]";
	}    
	
	
}
