package four.com.bbs.cate.service;

/**  
 * @Class Name : BbsCateVO.java
 * @Description : BbsCateVO Class
 * @Modification Information  
 * @
 * @  수정일              수정자               수정내용
 * @ --------------     ---------     -------------------------------
 * @ 2013.08.20      조은태               최초생성
 * 
 * @ author (주)거산디에스엔 조은태
 * @ since 2013.09.02
 * @ version 1.0
 * @ see
 * 
 *  Copyright (C) by MOPAS All right reserved.
 */

public class BbsCateVO {
	
	/*말머리ID*/
	/* int -> String으로 수정*/
	private String bbscateId ;

	/*말머리명*/
	private String bbscateName ;
	
	/* 게시판명 (FK) */
	private int bbsId ;

	/**
	 * @return the bbscateId
	 */
	public String getBbscateId() {
		return bbscateId;
	}

	/**
	 * @param bbscateId the bbscateId to set
	 */
	public void setBbscateId(String bbscateId) {
		this.bbscateId = bbscateId;
	}

	/**
	 * @return the bbscateName
	 */
	public String getBbscateName() {
		return bbscateName;
	}

	/**
	 * @param bbscateName the bbscateName to set
	 */
	public void setBbscateName(String bbscateName) {
		this.bbscateName = bbscateName;
	}

	/**
	 * @return the bbsId
	 */
	public int getBbsId() {
		return bbsId;
	}

	/**
	 * @param bbsId the bbsId to set
	 */
	public void setBbsId(int bbsId) {
		this.bbsId = bbsId;
	}

	@Override
	public String toString() {
		return "BbsCateVO [bbscateId=" + bbscateId + ", bbscateName=" + bbscateName
				+ ", bbsId=" + bbsId + "]";
	}
	
	
	

}