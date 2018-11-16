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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.util.UrlPathHelper;

import egovframework.com.cmm.ComDefaultVO;
import egovframework.com.cmm.service.FileMngService;
import egovframework.com.cmm.service.FileMngUtil;
import egovframework.com.cmm.service.FileVO;
import four.com.bbs.service.BbsDefaultVO;
import four.com.bbs.service.BbsService;
import four.com.bbs.service.BbsVO;
import four.front.faq.service.FaqService;
import four.front.faq.service.FaqVO;
import four.mng.menu.service.MenuService;
import four.mng.menu.service.MenuVO;
import four.mng.pop.service.PopManageService;
import four.mng.pop.service.PopManageVO;
import four.mng.press.sevice.PressContentDefaultVO;
import four.mng.press.sevice.PressContentService;
import four.mng.press.sevice.PressContentVO;



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
	
	@Resource(name = "PressContentService")
	PressContentService pressContentService;

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
		
		
		// 관련글
		BbsDefaultVO searchVO = new BbsDefaultVO();
		searchVO.setBbsId(1);
		searchVO.setRecordCountPerPage(2);
		List<BbsVO> relatedContents = bbsService.selectBbsList(searchVO);
	
		for(int i =0; i < relatedContents.size(); i++) {

			String text = relatedContents.get(i).getcontent().replaceAll("<br>/ig", "\n");
			text = "<p>"+text.replaceAll("<[^>]*>", "")+"</p>";
			relatedContents.get(i).setcontent(text);
		}
		
		   
		// faq
		ComDefaultVO comVO = new ComDefaultVO();
		comVO.setRecordCountPerPage(9999);
		List<FaqVO> faqList = faqService.selectFaqList(comVO);
		
		
		// 언론보도자료 
		PressContentDefaultVO pressContentVO = new PressContentDefaultVO();
		pressContentVO.setRecordCountPerPage(5);
		List<PressContentVO> pressContentList = pressContentService.selectPressContentList(pressContentVO);
		
		
		
		System.out.println("관련글 사이즈2나오지?? = "+relatedContents.size());
		System.out.println("faq 사이즈나오지?? = "+faqList.size());
		System.out.println("언론보도자료 사이즈나오지?? = "+pressContentList.size());
		System.out.println("팝업 사이즈 나오지?? = " + popups.size());
		
		// 메인 이미지
		FileVO vo = new FileVO();
		vo = fileMngService.selectMainImg();
		
		/*
		FileVO fvo = new FileVO();
		Map imgMap = new HashMap<>();
		
		//팝업에 등록되어 있는 파일 아이디로 파일 상세 정보 찾기
		for(int i =0; i < popups.size(); i++) {
			if(popups.get(i).getFileId() != null) {
				fvo.setAtchFileId(popups.get(i).getFileId());
				fvo.setFileSn("0");
				FileVO file = fileMngService.selectFileInf(fvo);
				imgMap.put(popups.get(i).getFileId(), file);
			}
		}
		
		model.addAttribute("imgFile", imgMap);
		
		*/
		
		/*
		//제작영화
		List<ProductMovieVO> movies = productMovieService.selectProductMovieListMain();
		model.addAttribute("movies", movies);

		//학사일정
		List<SchoolScheVO> schedulesJU =  schoolScheService.selectSchoolListMainJU();
		List<SchoolScheVO> schedulesJA =  schoolScheService.selectSchoolListMainJA();

		model.addAttribute("schedulesJU", schedulesJU);
		model.addAttribute("schedulesJA", schedulesJA);*/

		//KAFA소식
		BbsDefaultVO bbsSearchVO = new BbsDefaultVO();
		bbsSearchVO.setBbsId(1);
		bbsSearchVO.setRecordCountPerPage(6);
		List<BbsVO> MainBbsList = bbsService.selectBbsForMain(bbsSearchVO);
		model.addAttribute("mainBbs",MainBbsList);
		
		model.addAttribute("title","MAIN");
		
		model.addAttribute("mainImg",vo.getAtchFileId());
		model.addAttribute("popups", popups);
		model.addAttribute("relatedContents", relatedContents);
		model.addAttribute("faqList", faqList);
		model.addAttribute("pressContentList", pressContentList);
		
		return "/front/index";
	}
	
	
	@RequestMapping(value = "/front/menu/top.do") 
	 public String frontTop(Model model) throws Exception { 
	  List<MenuVO> menues = SysMenuList; 
	  model.addAttribute("menues", menues); 
	  return "/front/include/top"; 
	 }
	
	
	

	@RequestMapping(value = "/front/menu/secondTop.do")
	public String frontSecondTop(Model model, HttpServletRequest request) throws Exception {
		/*아인세 secondTop 가져오는 부분  */
        UrlPathHelper urlPathHelper = new UrlPathHelper();
        String originalURL = "";
        String[] params = null;
        Map<String, String> maps = new HashMap<String, String>();


        if (urlPathHelper.getOriginatingQueryString(request) != null) {
            originalURL = urlPathHelper.getOriginatingRequestUri(request) + "?" + urlPathHelper.getOriginatingQueryString(request);
        } else {
            originalURL = urlPathHelper.getOriginatingRequestUri(request);
        }

        MenuVO menu = new MenuVO();
        
        if(originalURL.equals("/front/content/contentViewer.do?contentId=CONTENT_0000081") 
        		|| originalURL.equals("/front/content/contentViewer.do?contentId=CONTENT_0000091")
        		|| originalURL.equals("/front/content/contentViewer.do?contentId=CONTENT_0000101")
        		|| originalURL.equals("/front/content/contentViewer.do?contentId=CONTENT_0000111")){
        	originalURL = "/front/content/contentViewer.do?contentId=CONTENT_0000071";     	
        }
        
        if(originalURL.equals("/front/content/contentViewer.do?contentId=CONTENT_0000131") 
        		|| originalURL.equals("/front/content/contentViewer.do?contentId=CONTENT_0000141")
        		|| originalURL.equals("/front/content/contentViewer.do?contentId=CONTENT_0000151")
        		|| originalURL.equals("/front/content/contentViewer.do?contentId=CONTENT_0000161")){
        	originalURL = "/front/content/contentViewer.do?contentId=CONTENT_0000121";     	
        }

        menu = menuService.selectSecondTop(originalURL);

        int depth = 0;

        if (menu != null) {
            if (menu.getParentMenuMap() != null) {
                depth++;
                if (menu.getParentMenuMap().getParentMenuMap() != null) {
                    depth++;
                    if (menu.getParentMenuMap().getParentMenuMap().getParentMenuMap() != null) {
                        depth++;
                    }
                }
            }
        }
        HashMap<String, Object> menuMap = new HashMap<>();
        MenuVO fMenu = new MenuVO();
        MenuVO sMenu = new MenuVO();
        MenuVO tMenu = new MenuVO();

        if (depth == 3) {
            fMenu = menu.getParentMenuMap().getParentMenuMap().getParentMenuMap();
            sMenu = menu.getParentMenuMap().getParentMenuMap();
            tMenu = menu.getParentMenuMap();
        }
        if (depth == 2) {
            fMenu = menu.getParentMenuMap().getParentMenuMap();
            sMenu = menu.getParentMenuMap();
            tMenu = menu;
        }
        if (depth == 1) {
            fMenu = menu.getParentMenuMap();
            sMenu = menu;
        }
        List<MenuVO> secondMenu = new ArrayList<>();
        HashMap<String,String> param = new HashMap<>();
        if(sMenu != null){
            param.put("menuScId",sMenu.getMenuScId());
            param.put("existId","");

            secondMenu =  menuService.selectSecondTopSecondList(param);
        }
        for (int i = 0; i < secondMenu.size(); i++) {
			if(secondMenu.get(i).gettitle().equals("faq"))
				secondMenu.get(i).settitle("자주하는 질문과 답변");
		}
        menuMap.put("fMenu", fMenu);
        menuMap.put("sMenu", sMenu);
        menuMap.put("tMenu", tMenu);
        menuMap.put("currentMenuId",sMenu.getMenuId());
        menuMap.put("secondMenu", secondMenu);
        
        model.addAttribute("menuMap", menuMap);
        
		return "/front/include/secondTop";
	}

}

