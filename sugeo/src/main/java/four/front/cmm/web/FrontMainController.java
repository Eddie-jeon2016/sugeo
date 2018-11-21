/** ===============================================
 * Copyright(c) 4DEPTH 2017
 * @fileName FrontMainController.java
 * @author Chang-il Jeon
 * @since 2017. 2. 3.
 * @version 1.0
 *
 *=================================================
 */
package four.front.cmm.web;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import egovframework.com.cmm.service.FileMngService;
import egovframework.com.cmm.service.FileMngUtil;
import four.com.bbs.service.BbsService;
import four.com.item.service.ItemService;
import four.com.item.service.ItemVO;
import four.front.faq.service.FaqService;
import four.mng.menu.service.MenuService;
import four.mng.menu.service.MenuVO;
import four.mng.pop.service.PopManageService;
import four.mng.pop.service.PopManageVO;



@Controller
public class FrontMainController {

	@Resource(name = "popManageService")
	private PopManageService popManageService;

	@Resource(name = "bbsService")
	private BbsService bbsService;

	@Resource(name = "MenuService")
	private MenuService menuService;
	
	@Resource(name = "FileMngUtil")
	private FileMngUtil fileUtil;

	@Resource(name = "FileMngService")
	private FileMngService fileMngService;
	
	@Resource(name = "faqService")
	FaqService faqService;
	
	@Resource(name = "ItemService")
	public ItemService itemService;
	
	public static List<MenuVO> SysMenuList;

	@PostConstruct
	public List<MenuVO> setMenuList()throws Exception{

		SysMenuList = menuService.selectMenuTopList();

		return SysMenuList;
	}

	@RequestMapping(value={"/front/","/index.do"})
	public String frontIndex(Model model) throws Exception{

		//팝업
		List<PopManageVO> popups = popManageService.selectPopList();
		
		model.addAttribute("title","MAIN");
		
		System.out.println("==================메인화면 시작=================");
		List<ItemVO> itemList = itemService.selectItemFrontList();
		
		System.out.println("디비에서 가져온 아이템 갯수 = " + itemList.size());
		for(int i = 0; i < itemList.size(); i++) {
			if(itemList.get(i).getItemDesc() != null && !itemList.get(i).getItemDesc().equals("")) {
				String desc = itemList.get(i).getItemDesc();
				itemList.get(i).setItemDescArr(desc.split("\n"));
				System.out.println("배열의 사이즈 = " + itemList.get(i).getItemDescArr().length);
			}
			System.out.println(itemList.get(i).getItemDesc());
		}
		model.addAttribute("itemList", itemList);
		System.out.println("==================메인화면 종료=================");
		
		
		return "/front/index";
	}
	
	
	@RequestMapping(value = "/front/menu/top.do") 
	 public String frontTop(Model model) throws Exception { 
	  List<MenuVO> menues = SysMenuList; 
	  model.addAttribute("menues", menues); 
	  return "/front/include/top"; 
	 }

}

