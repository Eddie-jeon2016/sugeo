package egovframework.com.cmm;

import java.io.Serializable;

/**
 * @Class Name : ComDefaultVO.java
 * @Description : ComDefaultVO class
 * @Modification Information
 * @
 * @  수정일         수정자                   수정내용
 * @ -------    --------    ---------------------------
 * @ 2009.02.01    조재영         최초 생성
 *
 *  @author 공통서비스 개발팀 조재영
 *  @since 2009.02.01
 *  @version 1.0
 *  @see 
 *  
 */
public class ComDefaultVO implements Serializable {
	
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
    private int firstIndex = 0;

    /** lastIndex */
    private int lastIndex = 1;

    /** recordCountPerPage */
    private int recordCountPerPage = 10;
    
    /** 검색KeywordFrom */
    private String searchKeywordFrom = "";    

    /** menu1 */
    private String menu1 = "";
    
    /** menu1Nm */
    private String menu1Nm = "";
    
    /** menu2 */
    private String menu2 = "";
    
    /** menu2Nm */
    private String menu2Nm = "";
    
    /** menu3 */
    private String menu3 = "";
    
    /** menu3Nm */
    private String menu3Nm = "";
    
    /** cmd */
    private String cmd = "";
    
    /** cmd */
    private String status = "";
    
    private String year_chk ="";
    
    private String month_chk ="";
    
    private String week_chk ="";
    
    
    /** menuNo */
    private String no = "";
    
    /** menuNo */
    private String bid = "";
    
    private Integer bbsId;
    
    private Integer nttId;
    
    /** menuNo */
    private String smenuNo;
    
    /** menuNm */
    private String smenuNm;
    
    /** menuNo */
    private String menuNo;
    
    /** menuNm */
    private String menuNm;
    
    private Integer searchUpperMenu;
    
	/** 검색KeywordTo */
    private String searchKeywordTo = "";
    
    /** 검색날짜사용 */
    private String searchDate = "";
    
    /** 검색날짜사용 */
    private String searchStartDate = "";
    
    /** 검색날짜사용 */
    private String searchEndDate = "";
    
    /** 검색날짜사용 */
    private String searchGubun = "";
    
    /** Content 관련 메뉴네임 */
    private String content;

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
	 * @return the searchKeywordFrom
	 */
	public String getSearchKeywordFrom() {
		return searchKeywordFrom;
	}

	/**
	 * @param searchKeywordFrom the searchKeywordFrom to set
	 */
	public void setSearchKeywordFrom(String searchKeywordFrom) {
		this.searchKeywordFrom = searchKeywordFrom;
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
	 * @return the menu1Nm
	 */
	public String getMenu1Nm() {
		return menu1Nm;
	}

	/**
	 * @param menu1Nm the menu1Nm to set
	 */
	public void setMenu1Nm(String menu1Nm) {
		this.menu1Nm = menu1Nm;
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
	 * @return the menu2Nm
	 */
	public String getMenu2Nm() {
		return menu2Nm;
	}

	/**
	 * @param menu2Nm the menu2Nm to set
	 */
	public void setMenu2Nm(String menu2Nm) {
		this.menu2Nm = menu2Nm;
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
	 * @return the menu3Nm
	 */
	public String getMenu3Nm() {
		return menu3Nm;
	}

	/**
	 * @param menu3Nm the menu3Nm to set
	 */
	public void setMenu3Nm(String menu3Nm) {
		this.menu3Nm = menu3Nm;
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
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @return the no
	 */
	public String getNo() {
		return no;
	}

	/**
	 * @param no the no to set
	 */
	public void setNo(String no) {
		this.no = no;
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
	 * @return the bbs_id
	 */
	public Integer getBbsId() {
		return bbsId;
	}

	/**
	 * @param bbs_id the bbs_id to set
	 */
	public void setBbsId(Integer bbsId) {
		this.bbsId = bbsId;
	}

	/**
	 * @return the ntt_id
	 */
	public Integer getNttId() {
		return nttId;
	}

	/**
	 * @param ntt_id the ntt_id to set
	 */
	public void setNttId(Integer nttId) {
		this.nttId = nttId;
	}

	/**
	 * @return the menuNo
	 */
	public String getSmenuNo() {
		return smenuNo;
	}

	/**
	 * @param menuNo the menuNo to set
	 */
	public void setSmenuNo(String smenuNo) {
		this.smenuNo = smenuNo;
	}

	/**
	 * @return the menuNm
	 */
	public String getSmenuNm() {
		return smenuNm;
	}

	/**
	 * @param menuNm the menuNm to set
	 */
	public void setSmenuNm(String smenuNm) {
		this.smenuNm = smenuNm;
	}

	/**
	 * @return the menuNo
	 */
	public String getMenuNo() {
		return menuNo;
	}

	/**
	 * @param menuNo the menuNo to set
	 */
	public void setMenuNo(String menuNo) {
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
	 * @return the searchUpperMenu
	 */
	public Integer getSearchUpperMenu() {
		return searchUpperMenu;
	}

	/**
	 * @param searchUpperMenu the searchUpperMenu to set
	 */
	public void setSearchUpperMenu(Integer searchUpperMenu) {
		this.searchUpperMenu = searchUpperMenu;
	}

	/**
	 * @return the searchKeywordTo
	 */
	public String getSearchKeywordTo() {
		return searchKeywordTo;
	}

	/**
	 * @param searchKeywordTo the searchKeywordTo to set
	 */
	public void setSearchKeywordTo(String searchKeywordTo) {
		this.searchKeywordTo = searchKeywordTo;
	}

	/**
	 * @return the searchDate
	 */
	public String getSearchDate() {
		return searchDate;
	}

	/**
	 * @param searchDate the searchDate to set
	 */
	public void setSearchDate(String searchDate) {
		this.searchDate = searchDate;
	}

	/**
	 * @return the searchStartDate
	 */
	public String getSearchStartDate() {
		return searchStartDate;
	}

	/**
	 * @param searchStartDate the searchStartDate to set
	 */
	public void setSearchStartDate(String searchStartDate) {
		this.searchStartDate = searchStartDate;
	}

	/**
	 * @return the searchEndDate
	 */
	public String getSearchEndDate() {
		return searchEndDate;
	}

	/**
	 * @param searchEndDate the searchEndDate to set
	 */
	public void setSearchEndDate(String searchEndDate) {
		this.searchEndDate = searchEndDate;
	}

	/**
	 * @return the searchGubun
	 */
	public String getSearchGubun() {
		return searchGubun;
	}

	/**
	 * @param searchGubun the searchGubun to set
	 */
	public void setSearchGubun(String searchGubun) {
		this.searchGubun = searchGubun;
	}

	/**
	 * @return the content
	 */
	public String getContent() {
		return content;
	}

	/**
	 * @param content the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}

	public String getYear_chk() {
		return year_chk;
	}

	public void setYear_chk(String year_chk) {
		this.year_chk = year_chk;
	}

	public String getMonth_chk() {
		return month_chk;
	}

	public void setMonth_chk(String month_chk) {
		this.month_chk = month_chk;
	}

	public String getWeek_chk() {
		return week_chk;
	}

	public void setWeek_chk(String week_chk) {
		this.week_chk = week_chk;
	}


	

    
	
	
}
