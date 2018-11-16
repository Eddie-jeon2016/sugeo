/** ===============================================
 * Copyright(c) 4DEPTH 2017
 * @fileName MenuVO.java
 * @author Chang-il Jeon
 * @since 2017. 2. 8.
 * @version 1.0
 * 
 *=================================================
 */
package four.mng.menu.service;

import java.util.List;

public class MenuVO {

	
	private String menuId = "";
	
	private String menuScId = "";
	
	private String title = "";
	
	private String menuDesc = "";
	
	private int menuDepth = 0;
	
	private String url = "";
	
	private String useYn = "";
	
	private int seq = 0;
	
	private List<MenuVO> subMenues;
	
	private List<MenuVO> thrdMenues;
	
	/*3뎁스에 따른 추가된 코드*/
	private MenuVO parentMenuMap;
	
	public MenuVO getParentMenuMap() {
		return parentMenuMap;
	}

	public void setParentMenuMap(MenuVO parentMenuMap) {
		this.parentMenuMap = parentMenuMap;
	}
	/*3뎁스에 따른 추가된 코드 끝*/
	
	/**
	 * @return the menuId
	 */
	public String getMenuId() {
		return menuId;
	}

	/**
	 * @param menuId the menuId to set
	 */
	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}

	/**
	 * @return the menuScId
	 */
	public String getMenuScId() {
		return menuScId;
	}

	/**
	 * @param menuScId the menuScId to set
	 */
	public void setMenuScId(String menuScId) {
		this.menuScId = menuScId;
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
	 * @return the menuDesc
	 */
	public String getMenuDesc() {
		return menuDesc;
	}

	/**
	 * @param menuDesc the menuDesc to set
	 */
	public void setMenuDesc(String menuDesc) {
		this.menuDesc = menuDesc;
	}

	/**
	 * @return the menuDepth
	 */
	public int getMenuDepth() {
		return menuDepth;
	}

	/**
	 * @param menuDepth the menuDepth to set
	 */
	public void setMenuDepth(int menuDepth) {
		this.menuDepth = menuDepth;
	}

	/**
	 * @return the url
	 */
	public String geturl() {
		return url;
	}

	/**
	 * @param url the url to set
	 */
	public void seturl(String url) {
		this.url = url;
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
	 * @return the seq
	 */
	public int getSeq() {
		return seq;
	}

	/**
	 * @param seq the seq to set
	 */
	public void setSeq(int seq) {
		this.seq = seq;
	}

	public List<MenuVO> getSubMenues() {
		return subMenues;
	}

	public void setSubMenues(List<MenuVO> subMenues) {
		this.subMenues = subMenues;
	}
	

	/**
	 * @return the thrdMenues
	 */
	public List<MenuVO> getThrdMenues() {
		return thrdMenues;
	}

	/**
	 * @param thrdMenues the thrdMenues to set
	 */
	public void setThrdMenues(List<MenuVO> thrdMenues) {
		this.thrdMenues = thrdMenues;
	}

	@Override
	public String toString() {
		return "MenuVO [menuId=" + menuId + ", menuScId=" + menuScId
				+ ", title=" + title + ", menuDesc=" + menuDesc
				+ ", menuDepth=" + menuDepth + ", url=" + url
				+ ", useYn=" + useYn + ", seq=" + seq + ", subMenues="
				+ subMenues + ", thrdMenues=" + thrdMenues +"]";
	}

		
}

