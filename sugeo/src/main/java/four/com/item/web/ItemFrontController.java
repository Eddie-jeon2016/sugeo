package four.com.item.web;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.util.UrlPathHelper;

import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import four.com.item.service.ItemService;
import four.com.item.service.ItemVO;
import four.com.order.service.OrderService;
import four.com.video.service.VideoMngDefaultVO;
import four.com.video.service.VideoMngService;
import four.com.video.service.VideoMngVO;

@Controller
public class ItemFrontController {

	
	@Resource(name = "ItemService")
	public ItemService itemService;
	
	@RequestMapping(value = {"/front/item/itemView.do", "/index.do"})
	public String insertOrderView(Model model, HttpServletRequest request) throws Exception{
		System.out.println("==================아이템메인화면 시작=================");
		List<ItemVO> itemList = itemService.selectItemFrontList();

		for(int i = 0; i < itemList.size(); i++) {
			if(itemList.get(i).getItemDesc() != null && !itemList.get(i).getItemDesc().equals("")) {
				String desc = itemList.get(i).getItemDesc();
				itemList.get(i).setItemDescArr(desc.split("\n"));
			}
		}
		model.addAttribute("itemList", itemList);
		System.out.println("==================아이템메인화면 종료=================");
		return "front/item/itemView";
	}
	

	
	
}
