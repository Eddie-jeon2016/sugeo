package four.com.video.web;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.util.UrlPathHelper;

import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import four.com.video.service.VideoMngDefaultVO;
import four.com.video.service.VideoMngService;
import four.com.video.service.VideoMngVO;

@Controller
public class VideoController {

	@Resource(name = "VideoMngService")
	public VideoMngService videomngservice;
	
	//동영상 리스트 불러오기
		@RequestMapping({"/front/video/videoList.do"})
		public String VideoMngList(Model model,
				@ModelAttribute("searchVO") VideoMngDefaultVO searchVO,
				VideoMngVO videoMngVO,
				HttpServletRequest request) throws Exception{
			String returnUrl = "";
			
			VideoMngVO resultVideo = videomngservice.selectVideOneList();

			model.addAttribute("resultVideo", resultVideo);

			return "front/content/videoContent";
		}
	
	
}
