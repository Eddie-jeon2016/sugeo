/** ===============================================
 * Copyright(c) 4DEPTH 2017
 * @fileName FrontContentController.java
 * @author Chang-il Jeon
 * @since 2017. 2. 3.
 * @version 1.0
 * 
 *=================================================
 */
package four.front.cmm.web;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import four.mng.content.service.ContentService;
import four.mng.content.service.ContentVO;


@Controller
public class FrontContentController {

	@Resource(name = "ContentService")
	public ContentService contentService;
	
	@RequestMapping("/front/content/contentViewer.do")
	public String contentView(Model model, ContentVO contentVO)throws Exception{
		ContentVO cVO = new ContentVO();
		
		cVO = contentService.selectContentView(contentVO);
	
		//잘못된 주소가 들어왔을때 처리
		if(cVO == null){
			return "/front/content/contentViewer";			
		}
		
		
		model.addAttribute("viewContent",cVO);
		model.addAttribute("title",cVO.getContentName());
		return "/front/content/contentViewer";
	}
	
	@RequestMapping("/front/content/privacyViewer.do")
	public String privacyView(Model model, ContentVO contentVO)throws Exception{		
		if(contentVO.getContentId().indexOf(',') != -1){
			String contentId = contentVO.getContentId().substring(0, contentVO.getContentId().indexOf(','));
			contentVO.setContentId(contentId);	
		}
		
		ContentVO cVO = new ContentVO();
		
		cVO = contentService.selectContentView(contentVO);
		
		//잘못된 주소가 들어왔을때 처리
		if(cVO == null){
			return "/front/content/contentViewer";			
		}
				
		model.addAttribute("viewContent",cVO);
		model.addAttribute("title",cVO.getContentName());
		return "/front/content/contentViewer";
	}
}

