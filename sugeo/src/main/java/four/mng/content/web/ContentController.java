/** ===============================================
 * Copyright(c) 4DEPTH 2017
 * @fileName ContentController.java
 * @author Chang-il Jeon
 * @since 2017. 2. 2.
 * @version 1.0
 * 
 *=================================================
 */
package four.mng.content.web;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import egovframework.com.cmm.ComDefaultVO;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import four.mng.content.service.ContentService;
import four.mng.content.service.ContentVO;


@Controller
public class ContentController {

	@Resource(name = "ContentService")
	public ContentService contentService;
	
	@RequestMapping("/mng/content/contentList.do")
	public String ContentList(Model model,ComDefaultVO searchVO)throws Exception{
		
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(searchVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(searchVO.getPageUnit());
		paginationInfo.setPageSize(searchVO.getPageSize());

		searchVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		searchVO.setLastIndex(paginationInfo.getLastRecordIndex());
		searchVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
		
		List<ContentVO> list = contentService.selectContentList(searchVO);
		int totalCnt = contentService.selectContentListCnt();
		paginationInfo.setTotalRecordCount(totalCnt);
		model.addAttribute("paginationInfo", paginationInfo);
		model.addAttribute("list",list);
		return "mng/content/contentList";
	}
	
	@RequestMapping("/mng/content/contentRegView.do")
	public String ContentReg(Model model,ContentVO cVO){
		ContentVO readCvo = new ContentVO();
		
		readCvo = contentService.selectContentView(cVO);
		model.addAttribute("ViewContent",readCvo);
		return "mng/content/contentRegView";
	}
	
	@RequestMapping("/mng/content/contentInsert.do")
	public String ContentInsert(ContentVO cVO)throws Exception{
		contentService.insertContent(cVO);
		
		return "redirect:/mng/content/contentList.do";
		
	}
	
	@RequestMapping("/mng/content/contentUpdate.do")
	public String ContentUpdate(ContentVO cVO)throws Exception{
		contentService.updateContent(cVO);
		
		return "redirect:/mng/content/contentList.do";
	}
	
	
	
}

