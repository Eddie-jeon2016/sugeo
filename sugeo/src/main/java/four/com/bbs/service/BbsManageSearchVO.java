package four.com.bbs.service;

import java.io.Serializable;

/**
 * 게시판 속성 정보를 관리하기 위한 VO  클래스
 * @ author (주)거산디에스엔 조은태
 * @ since 2013.08.21
 * @ version 1.0
 * @ see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 * @  수정일              수정자               수정내용
 * @ --------------     ---------     -------------------------------
 * @ 2013.08.21      조은태               최초생성
 *
 * </pre>
 */
@SuppressWarnings("serial")
public class BbsManageSearchVO extends BbsManageVO implements Serializable {
    
    /** 검색시작일 */
    private String searchBgnDe = "";
    
    /** 검색조건 */
    private String searchCnd = "";
    
    /** 검색종료일 */
    private String searchEndDe = "";
    
    /** 검색단어 */
    private String searchWrd = "";
    
    /** 정렬순서(DESC,ASC) */
    private String sortOrdr = "";
    
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

    /** rowNo */
    private int rowNo = 0;

    /** 최초 등록자명 */
    private String regName = "";

    /** 게시판유형 코드명 */
    private String bbsTyCodeName = "";

    /** 게시판속성 코드명 */
    private String bbsAttrbCodeName = "";

    /** 템플릿 명 */
    private String tmplatName = "";

   

    /** 권한지정 여부 */
    private String authFlag = "";

    /** 템플릿경로 */
    private String tmplatCours = "";

    /**
     * searchBgnDe attribute를 리턴한다.
     * 
     * @return the searchBgnDe
     */
    public String getSearchBgnDe() {
	return searchBgnDe;
    }

    /**
     * searchBgnDe attribute 값을 설정한다.
     * 
     * @param searchBgnDe
     *            the searchBgnDe to set
     */
    public void setSearchBgnDe(String searchBgnDe) {
	this.searchBgnDe = searchBgnDe;
    }

    /**
     * searchCnd attribute를 리턴한다.
     * 
     * @return the searchCnd
     */
    public String getSearchCnd() {
	return searchCnd;
    }

    /**
     * searchCnd attribute 값을 설정한다.
     * 
     * @param searchCnd
     *            the searchCnd to set
     */
    public void setSearchCnd(String searchCnd) {
	this.searchCnd = searchCnd;
    }

    /**
     * searchEndDe attribute를 리턴한다.
     * 
     * @return the searchEndDe
     */
    public String getSearchEndDe() {
	return searchEndDe;
    }

    /**
     * searchEndDe attribute 값을 설정한다.
     * 
     * @param searchEndDe
     *            the searchEndDe to set
     */
    public void setSearchEndDe(String searchEndDe) {
	this.searchEndDe = searchEndDe;
    }

    /**
     * searchWrd attribute를 리턴한다.
     * 
     * @return the searchWrd
     */
    public String getSearchWrd() {
	return searchWrd;
    }

    /**
     * searchWrd attribute 값을 설정한다.
     * 
     * @param searchWrd
     *            the searchWrd to set
     */
    public void setSearchWrd(String searchWrd) {
	this.searchWrd = searchWrd;
    }

    /**
     * sortOrdr attribute를 리턴한다.
     * 
     * @return the sortOrdr
     */
    public String getSortOrdr() {
	return sortOrdr;
    }

    /**
     * sortOrdr attribute 값을 설정한다.
     * 
     * @param sortOrdr
     *            the sortOrdr to set
     */
    public void setSortOrdr(String sortOrdr) {
	this.sortOrdr = sortOrdr;
    }

    /**
     * searchUseYn attribute를 리턴한다.
     * 
     * @return the searchUseYn
     */
   
    //@Override
	@Override
	public String getSearchUseYn() {
	return searchUseYn;
    }

    /**
     * searchUseYn attribute 값을 설정한다.
     * 
     * @param searchUseYn
     *            the searchUseYn to set
     */
   // @Override
	@Override
	public void setSearchUseYn(String searchUseYn) {
	this.searchUseYn = searchUseYn;
    }

    /**
     * pageIndex attribute를 리턴한다.
     * 
     * @return the pageIndex
     */
    //@Override
	@Override
	public int getPageIndex() {
	return pageIndex;
    }

    /**
     * pageIndex attribute 값을 설정한다.
     * 
     * @param pageIndex
     *            the pageIndex to set
     */
   // @Override
	@Override
	public void setPageIndex(int pageIndex) {
	this.pageIndex = pageIndex;
    }

    /**
     * pageUnit attribute를 리턴한다.
     * 
     * @return the pageUnit
     */
    //@Override
	@Override
	public int getPageUnit() {
	return pageUnit;
    }

    /**
     * pageUnit attribute 값을 설정한다.
     * 
     * @param pageUnit
     *            the pageUnit to set
     */
    //@Override
	@Override
	public void setPageUnit(int pageUnit) {
	this.pageUnit = pageUnit;
    }

    /**
     * pageSize attribute를 리턴한다.
     * 
     * @return the pageSize
     */
   // @Override
	@Override
	public int getPageSize() {
	return pageSize;
    }

    /**
     * pageSize attribute 값을 설정한다.
     * 
     * @param pageSize
     *            the pageSize to set
     */
    //@Override
	@Override
	public void setPageSize(int pageSize) {
	this.pageSize = pageSize;
    }

    /**
     * firstIndex attribute를 리턴한다.
     * 
     * @return the firstIndex
     */
   // @Override
	@Override
	public int getFirstIndex() {
	return firstIndex;
    }

    /**
     * firstIndex attribute 값을 설정한다.
     * 
     * @param firstIndex
     *            the firstIndex to set
     */
    //@Override
	@Override
	public void setFirstIndex(int firstIndex) {
	this.firstIndex = firstIndex;
    }

    /**
     * lastIndex attribute를 리턴한다.
     * 
     * @return the lastIndex
     */
    //@Override
	@Override
	public int getLastIndex() {
	return lastIndex;
    }

    /**
     * lastIndex attribute 값을 설정한다.
     * 
     * @param lastIndex
     *            the lastIndex to set
     */
    //@Override
	@Override
	public void setLastIndex(int lastIndex) {
	this.lastIndex = lastIndex;
    }

    /**
     * recordCountPerPage attribute를 리턴한다.
     * 
     * @return the recordCountPerPage
     */
   // @Override
	@Override
	public int getRecordCountPerPage() {
	return recordCountPerPage;
    }

    /**
     * recordCountPerPage attribute 값을 설정한다.
     * 
     * @param recordCountPerPage
     *            the recordCountPerPage to set
     */
    //@Override
	@Override
	public void setRecordCountPerPage(int recordCountPerPage) {
	this.recordCountPerPage = recordCountPerPage;
    }

    /**
     * rowNo attribute를 리턴한다.
     * 
     * @return the rowNo
     */
    public int getRowNo() {
	return rowNo;
    }

    /**
     * rowNo attribute 값을 설정한다.
     * 
     * @param rowNo
     *            the rowNo to set
     */
    public void setRowNo(int rowNo) {
	this.rowNo = rowNo;
    }

    /**
     * regName attribute를 리턴한다.
     * 
     * @return the regName
     */
    public String getregName() {
	return regName;
    }

    /**
     * regName attribute 값을 설정한다.
     * 
     * @param regName
     *            the regName to set
     */
    public void setregName(String regName) {
	this.regName = regName;
    }

    /**
     * bbsTyCodeName attribute를 리턴한다.
     * 
     * @return the bbsTyCodeName
     */
    public String getbbsTyCodeName() {
	return bbsTyCodeName;
    }

    /**
     * bbsTyCodeName attribute 값을 설정한다.
     * 
     * @param bbsTyCodeName
     *            the bbsTyCodeName to set
     */
    public void setbbsTyCodeName(String bbsTyCodeName) {
	this.bbsTyCodeName = bbsTyCodeName;
    }

    /**
     * bbsAttrbCodeName attribute를 리턴한다.
     * 
     * @return the bbsAttrbCodeName
     */
    public String getbbsAttrbCodeName() {
	return bbsAttrbCodeName;
    }

    /**
     * bbsAttrbCodeName attribute 값을 설정한다.
     * 
     * @param bbsAttrbCodeName
     *            the bbsAttrbCodeName to set
     */
    public void setbbsAttrbCodeName(String bbsAttrbCodeName) {
	this.bbsAttrbCodeName = bbsAttrbCodeName;
    }

    /**
     * tmplatName attribute를 리턴한다.
     * 
     * @return the tmplatName
     */
    public String gettmplatName() {
	return tmplatName;
    }

    /**
     * tmplatName attribute 값을 설정한다.
     * 
     * @param tmplatName
     *            the tmplatName to set
     */
    public void settmplatName(String tmplatName) {
	this.tmplatName = tmplatName;
    }

   

    /**
     * authFlag attribute를 리턴한다.
     * 
     * @return the authFlag
     */
    public String getAuthFlag() {
	return authFlag;
    }

    /**
     * authFlag attribute 값을 설정한다.
     * 
     * @param authFlag
     *            the authFlag to set
     */
    public void setAuthFlag(String authFlag) {
	this.authFlag = authFlag;
    }

    /**
     * tmplatCours attribute를 리턴한다.
     * 
     * @return the tmplatCours
     */
    public String getTmplatCours() {
	return tmplatCours;
    }

    /**
     * tmplatCours attribute 값을 설정한다.
     * 
     * @param tmplatCours
     *            the tmplatCours to set
     */
    public void setTmplatCours(String tmplatCours) {
	this.tmplatCours = tmplatCours;
    }

    /**
     * toString 메소드를 대치한다.
     */
  /*  @Override
	public String toString() {
	return ToStringBuilder.reflectionToString(this);
    }*/
}
