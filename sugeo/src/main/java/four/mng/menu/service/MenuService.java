/** ===============================================
 * Copyright(c) 4DEPTH 2017
 * @fileName MenuService.java
 * @author Chang-il Jeon
 * @since 2017. 2. 7.
 * @version 1.0
 *
 *=================================================
 */
package four.mng.menu.service;

import java.util.HashMap;
import java.util.List;

import egovframework.com.cmm.ComDefaultVO;

public interface MenuService {


	public List<MenuVO> selectMenuList(ComDefaultVO searchVO)throws Exception;

	public void insertMenu(MenuVO mVO)throws Exception;

	public void updateMenu(MenuVO mVO)throws Exception;

	public MenuVO selectMenuView(MenuVO mVO);

	public List<MenuVO> selectMenuTopList() throws Exception;

	public MenuVO selectParentMenu(String url) throws Exception;

	public HashMap<String,String> FSTselector(String url) throws Exception;

	/* 3뎁스에 따른 추가된 코드 */
	public MenuVO selectSecondTop(String url)throws Exception;
	
	public List<MenuVO> selectSecondTopSecondList(HashMap<String,String> param)throws Exception;
}

