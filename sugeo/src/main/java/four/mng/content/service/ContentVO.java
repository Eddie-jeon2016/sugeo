/** ===============================================
 * Copyright(c) 4DEPTH 2017
 * @fileName ContentVO.java
 * @author Chang-il Jeon
 * @since 2017. 2. 2.
 * @version 1.0
 * 
 *=================================================
 */
package four.mng.content.service;

public class ContentVO {

	
	
	private String contentId ="";
	
	private String contentName = "";

	private String content = "";
	
	private String useYn = "";
	
	private String regDate = "";
	
	private int regId = 0;
	
	private String modDate = "";
	
	private int modId = 0;

	/**
	 * @return the contentId
	 */
	public String getContentId() {
		return contentId;
	}

	/**
	 * @param contentId the contentId to set
	 */
	public void setContentId(String contentId) {
		this.contentId = contentId;
	}

	/**
	 * @return the contentName
	 */
	public String getContentName() {
		return contentName;
	}

	/**
	 * @param contentName the contentName to set
	 */
	public void setContentName(String contentName) {
		this.contentName = contentName;
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
	public String getRegDate() {
		return regDate;
	}

	/**
	 * @param regDate the regDate to set
	 */
	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}

	/**
	 * @return the regId
	 */
	public int getRegId() {
		return regId;
	}

	/**
	 * @param regId the regId to set
	 */
	public void setRegId(int regId) {
		this.regId = regId;
	}

	/**
	 * @return the modDate
	 */
	public String getModDate() {
		return modDate;
	}

	/**
	 * @param modDate the modDate to set
	 */
	public void setModDate(String modDate) {
		this.modDate = modDate;
	}

	/**
	 * @return the modId
	 */
	public int getModId() {
		return modId;
	}

	/**
	 * @param modId the modId to set
	 */
	public void setModId(int modId) {
		this.modId = modId;
	}
	
	
}

