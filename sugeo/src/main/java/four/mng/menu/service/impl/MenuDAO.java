/** ===============================================
 * Copyright(c) 4DEPTH 2017
 * @fileName MenuDAO.java
 * @author Chang-il Jeon
 * @since 2017. 2. 8.
 * @version 1.0
 *
 *=================================================
 */
package four.mng.menu.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.com.cmm.ComDefaultVO;
import egovframework.com.cmm.service.impl.EgovComAbstractDAO;
import four.mng.menu.service.MenuVO;


@Repository("MenuDAO")
public class MenuDAO extends EgovComAbstractDAO{

	public List<MenuVO> selectMenuList(ComDefaultVO searchVO) throws Exception {
		return (List<MenuVO>) list("MenuDAO.selectMenuList", searchVO);
	}

	public void insertMenu(MenuVO mVO){
		insert("MenuDAO.insertMenu",mVO);
	}

	public void updateMenu(MenuVO mVO){
		update("MenuDAO.updateMenu",mVO);
	}

	public MenuVO selectMenuView(MenuVO mVO){
		return (MenuVO) select("MenuDAO.selectMenuView",mVO);
	}

	public List<MenuVO> selectMenuTopList() throws Exception {
		return (List<MenuVO>) list("MenuDAO.selectMenuTopList");
	}


	public MenuVO selectParentMenu(String url) throws Exception{
		return (MenuVO) select("MenuDAO.selectParentMenu", url);
	}

	public HashMap<String,String> FSTselector(String menuId) throws Exception{
		return (HashMap<String,String>)select("MenuDAO.FSTselector",menuId);
	}
	
	/* 3뎁스에 따른 추가된 코드 */
	public MenuVO selectSecondTop(String url) throws Exception{
		return (MenuVO) selectOne("MenuDAO.selectCurrentMenuMap",url);
	}
	public List<MenuVO> selectSecondTopSecondList(HashMap<String,String> param) throws Exception{
		return (List<MenuVO>) list("MenuDAO.selectSecondTopSecondList",param);
	}

}

