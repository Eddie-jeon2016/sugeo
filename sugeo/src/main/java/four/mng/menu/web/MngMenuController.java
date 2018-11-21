/** ===============================================
 * Copyright(c) 4DEPTH 2017
 * @fileName MngMenuController.java
 * @author Chang-il Jeon
 * @since 2017. 2. 7.
 * @version 1.0
 * 
 *=================================================
 */
package four.mng.menu.web;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.util.UrlPathHelper;

import egovframework.com.cmm.ComDefaultVO;
import four.mng.menu.service.MenuService;
import four.mng.menu.service.MenuVO;

@Controller
public class MngMenuController {
	
	@Resource(name ="MenuService")
	private MenuService menuService;

	
	@RequestMapping("/mng/menu/menuList.do")
	public String selectMenuList(Model model,ComDefaultVO searchVO)throws Exception{
		List<MenuVO> list = menuService.selectMenuList(searchVO);
		
		model.addAttribute("list",list);
		return "mng/menu/menuList";
	}
	
	@RequestMapping("/mng/menu/menuRegView.do")
	public String MenuReg(Model model,MenuVO mVO){
		MenuVO readMvo = new MenuVO();
		
		readMvo = menuService.selectMenuView(mVO);
		model.addAttribute("ViewMenu",readMvo);
		return "mng/menu/menuRegView";
	}
	
	@RequestMapping("/mng/menu/menuInsert.do")
	public String MenuInsert(MenuVO mVO)throws Exception{
		menuService.insertMenu(mVO);
		
		return "redirect:/mng/menu/menuList.do";
		
	}
	
	@RequestMapping("/mng/menu/menuUpdate.do")
	public String MenuUpdate(MenuVO mVO)throws Exception{
		menuService.updateMenu(mVO);
		
		return "redirect:/mng/menu/menuList.do";
	}
	
	@RequestMapping("/mng/menu/secondTop.do")
	public String secondTop(HttpServletRequest request, Model model) throws Exception {
		UrlPathHelper urlPathHelper = new UrlPathHelper();
		String originalURL = "";
		String parentUrl = "";
		String parentName = "";
		String originalName = "";
		originalURL = urlPathHelper.getOriginatingRequestUri(request);
		if(originalURL.indexOf("pop") > -1) {
			
			parentUrl = "/mng/content/contentList.do";
			parentName = "사이트 관리";
			originalName = "팝업 관리";
		} else if(originalURL.indexOf("content") > -1) {
			
			parentUrl = "/mng/content/contentList.do";
			parentName = "사이트 관리";
			originalName = "콘텐츠 관리";
		} else if(originalURL.indexOf("bbs") > -1) {
			
			parentUrl = "/mng/bbs/BbsManageList.do";
			parentName = "게시판 관리";
			originalName = "게시판 관리";
		} else if (originalURL.indexOf("selectAthorUserList") > -1){
			
			parentUrl = "/mng/auth/selectAthorUserList.do";
			parentName = "권한 관리";
			originalName = "관리자 권한 관리";
		} else if(originalURL.indexOf("selectAccessUserList") > -1) {
			
			parentUrl = "/mng/auth/selectAccessUserList.do";
			parentName = "권한 관리";
			originalName = "관리자 접근 이력 관리";
		} else {
			
			parentUrl = "/mng/main.do";
			parentName = "게시판 관리";
			originalName = "게시판 관리";
		}
		System.out.println(originalURL);
		model.addAttribute("parentUrl", parentUrl);
		model.addAttribute("originalURL", originalURL);
		model.addAttribute("parentName", parentName);
		model.addAttribute("originalName", originalName);
		return "mng/include/secondTop";
	}
}

