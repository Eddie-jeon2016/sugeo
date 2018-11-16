package four.mng.press.sevice;


public class PressContentDefaultVO {
	/** 검색조건 */
    private String searchCondition = "";

    /** 검색Keyword */
    private String searchKeyword = "";
    
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

    /** 게시판 타입 */
	private Integer content_type;
	
	/** 게시물 id */
	private String pcId;

	
	public int getRecordCountPerPage() {
		return recordCountPerPage;
	}

	public void setRecordCountPerPage(int recordCountPerPage) {
		this.recordCountPerPage = recordCountPerPage;
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

	public Integer getContent_type() {
		return content_type;
	}

	public void setContent_type(Integer content_type) {
		this.content_type = content_type;
	}

	
	public String getPcId() {
		return pcId;
	}

	public void setPcId(String pcId) {
		this.pcId = pcId;
	}

	@Override
	public String toString() {
		return "PressContentDefaultVO [searchCondition=" + searchCondition
				+ ", searchKeyword=" + searchKeyword + ", pageIndex="
				+ pageIndex + ", pageUnit=" + pageUnit + ", pageSize="
				+ pageSize + ", firstIndex=" + firstIndex + ", lastIndex="
				+ lastIndex + ", content_type=" + content_type + ", pcId="
				+ pcId + ", recordCountPerPage=" + recordCountPerPage + "]";
	}
	
	
	
}
