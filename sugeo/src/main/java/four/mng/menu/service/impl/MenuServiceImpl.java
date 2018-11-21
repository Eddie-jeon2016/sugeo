/** ===============================================
 * Copyright(c) 4DEPTH 2017
 * @fileName MenuServiceImpl.java
 * @author Chang-il Jeon
 * @since 2017. 2. 8.
 * @version 1.0
 *
 *=================================================
 */
package four.mng.menu.service.impl;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.com.cmm.ComDefaultVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import four.mng.menu.service.MenuService;
import four.mng.menu.service.MenuVO;

@Service("MenuService")
public class MenuServiceImpl extends EgovAbstractServiceImpl implements MenuService{

	@Resource(name = "MenuDAO")
	private MenuDAO menuDAO;

	@Resource(name = "egovMenuIdGnrService")
	private EgovIdGnrService idgenService;

	/* (non-Javadoc)
	 * @see four.mng.menu.service.MenuService#selectMenuList(egovframework.com.cmm.ComDefaultVO)
	 */
	@Override
	public List<MenuVO> selectMenuList(ComDefaultVO searchVO) throws Exception {
		// TODO Auto-generated method stub
		return menuDAO.selectMenuList(searchVO);
	}

	/* (non-Javadoc)
	 * @see four.mng.menu.service.MenuService#insertMenu(four.mng.menu.service.MenuVO)
	 */
	@Override
	public void insertMenu(MenuVO mVO) throws Exception {
		String menuId = idgenService.getNextStringId();
		mVO.setMenuId(menuId);
		// TODO Auto-generated method stub
		menuDAO.insertMenu(mVO);
	}

	/* (non-Javadoc)
	 * @see four.mng.menu.service.MenuService#updateMenu(four.mng.menu.service.MenuVO)
	 */
	@Override
	public void updateMenu(MenuVO mVO) throws Exception {
		// TODO Auto-generated method stub
		menuDAO.updateMenu(mVO);
	}

	/* (non-Javadoc)
	 * @see four.mng.menu.service.MenuService#selectMenuView(four.mng.menu.service.MenuVO)
	 */
	@Override
	public MenuVO selectMenuView(MenuVO mVO) {
		// TODO Auto-generated method stub
		return menuDAO.selectMenuView(mVO);
	}

	@Override
	public List<MenuVO> selectMenuTopList() throws Exception {

		return menuDAO.selectMenuTopList();
	}

	@Override
	public MenuVO selectParentMenu(String url) throws Exception {

		return menuDAO.selectParentMenu(url);
	}

	/* (non-Javadoc)
	 * @see four.mng.menu.service.MenuService#FSTselector(java.lang.String)
	 */
	@Override
	public HashMap FSTselector(String menuId) throws Exception {
		// TODO Auto-generated method stub
		return menuDAO.FSTselector(menuId);
	}
	
	
	/* 3뎁스에 따른 추가된 코드 */
	@Override
	public MenuVO selectSecondTop(String url) throws Exception {
		return menuDAO.selectSecondTop(url);
	}
	
	@Override
	public List<MenuVO> selectSecondTopSecondList(HashMap<String,String> param) throws Exception {
		return menuDAO.selectSecondTopSecondList(param);
	}

}