package four.com.video.web;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.UrlPathHelper;

import egovframework.com.cmm.LoginVO;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import four.com.video.service.VideoMngDefaultVO;
import four.com.video.service.VideoMngService;
import four.com.video.service.VideoMngVO;
import four.common.util.security.tem4UserDetails;

@Controller
public class VideoMngController {
	
	@Resource(name = "VideoMngService")
	public VideoMngService videomngservice;
	
	//동영상 리스트 불러오기
	@RequestMapping({"/mng/bbs/videoMng/videoMngList.do"})
	public String VideoMngList(Model model,
			@ModelAttribute("searchVO") VideoMngDefaultVO searchVO,
			VideoMngVO videoMngVO,
			HttpServletRequest request) throws Exception{
		String returnUrl = "";
		
		PaginationInfo paginationInfo = new PaginationInfo();

		paginationInfo.setCurrentPageNo(searchVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(searchVO.getPageUnit());
		paginationInfo.setPageSize(searchVO.getPageSize());

		searchVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		searchVO.setLastIndex(paginationInfo.getLastRecordIndex());
		searchVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

/*		HashMap<String,String> map = new HashMap<String,String>();
    	map.put("searchCondition", "noticeY");
    	VideoMngVO vmvo = videomngservice.selectVideoMngOneList(map);*/
		
		//리스트 불러올때 노출여부 Y인 게시물이 2개 이상이거나 0개면 최근게시물을 Y로 바꿔줌
		VideoMngDefaultVO searchVO2 = new VideoMngDefaultVO();
		searchVO2.setSearchCondition("noticeY");
		int YCnt = videomngservice.selectVideoMngListCnt(searchVO2); // Y갯수구함
		String updatecondition = "";
		//하나도 없을경우
		if(YCnt == 0){
			updatecondition = "noticeYnLatly";
    		videomngservice.updateVideoMng(videoMngVO, updatecondition, request);
    	//두개이상인 경우	
		}else if(YCnt >= 2){
			for(int i=0; i<YCnt; i++){
				//모두 N으로 바꾸고
				updatecondition = "noticeYnintoN";
				videomngservice.updateVideoMng(videoMngVO, updatecondition, request);		
				//최근것을 Y로
				updatecondition = "noticeYnLatly";
				videomngservice.updateVideoMng(videoMngVO, updatecondition, request);
			}
		}
	
		List<VideoMngVO> resultList = videomngservice
				.selectVideoMngList(searchVO);

		int totCnt = videomngservice.selectVideoMngListCnt(searchVO); // 총 글 갯수 구함

		paginationInfo.setTotalRecordCount(totCnt);

		model.addAttribute("resultList", resultList);
		model.addAttribute("paginationInfo", paginationInfo);

		UrlPathHelper urlPath = new UrlPathHelper();
		String url = urlPath.getOriginatingServletPath(request);

		if ("/mng/bbs/videoMng/videoMngList.do".equals(url)){
			returnUrl = "/mng/bbs/videoMng/BbsVideoMngList";
		}
		return returnUrl;
	}
	
	//동영상 등록 페이지로 이동
	@RequestMapping({"/mng/bbs/videoMng/insertVideoMngView.do"})
	public String insertVideoMngView(Model model)throws Exception{
		LoginVO user = new LoginVO();

		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		if (auth.getDetails() instanceof tem4UserDetails) {
			tem4UserDetails detail = (tem4UserDetails) auth.getDetails();
			user = detail.getLoginInfo();
		}

		if (user == null) {
			return "forward:/usr/uia/LoginUsr.do";
		}else{
			
			String cmd = "insert";
			model.addAttribute("cmd", cmd);
			return "/mng/bbs/videoMng/BbsVideoMngInsertView";
		}
	}
	
	//동영상 등록
	@RequestMapping({"/mng/bbs/videoMng/insertVideoMng.do"})
	public String insertVideoMng(Model model, VideoMngVO videoMngVO,
			@ModelAttribute("searchVO") VideoMngDefaultVO searchVO,
			HttpServletRequest request)throws Exception{
		LoginVO user = new LoginVO();

		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		if (auth.getDetails() instanceof tem4UserDetails) {
			tem4UserDetails detail = (tem4UserDetails) auth.getDetails();
			user = detail.getLoginInfo();
		}
		if(user == null) {
//    		model.addAttribute("resultMsg", "fail.common.login");
        	return "forward:/usr/uia/LoginUsr.do";
	    }else{
	    	request.setAttribute("userId", user.getId());
	    	HashMap<String,String> map = new HashMap<String,String>();
	    	map.put("searchCondition", "noticeY");
	    	videoMngVO.setRegId(user.getId());
	    	//첫등록시 노출여부 무조건 Y
	    	if(videomngservice.selectVideoMngListCnt(searchVO) == 0){
	    		String firstNoticeYn = "Y";
	    		videoMngVO.setNoticeYn(firstNoticeYn);	
	    		videomngservice.insertVideoMng(videoMngVO, request);
	    	}else{
	    		String NoticeYn = videoMngVO.getNoticeYn();
	    		//등록시 입력한 노출여부가 Y인 경우
	    		if(NoticeYn.equals("Y")){
	    			//그전에 Y였던거 찾아서 N으로 바꿔주기
	    			VideoMngVO vmvo = videomngservice.selectVideoMngOneList(map);
	    			vmvo.setNoticeYn("N");
	    			String updatecondition = "noticeYn";
	    			int success = videomngservice.updateVideoMng(vmvo,updatecondition, request);
	    			//지금 등록한 게시물 등록
	    			videomngservice.insertVideoMng(videoMngVO, request);
	    		//등록시 입력한 노출여부가 N인 경우
	    		}else{
	    			if(videomngservice.selectVideoMngOneList(map) != null){
	    				videomngservice.insertVideoMng(videoMngVO, request);	    				
	    			}else{
	    				videoMngVO.setNoticeYn("Y");
	    				videomngservice.insertVideoMng(videoMngVO, request);	   				
	    			}
	    		}	
	    	}
	    	return "redirect:/mng/bbs/videoMng/videoMngList.do";
	    }
	}
	
	//동영상 수정페이지로 이동
	@RequestMapping({ "/mng/bbs/videoMng/VIdeoMngUpdtView.do" })
	public String updateVideoMngView(Model model,
			@RequestParam("select_videoId") String videoId,
			@ModelAttribute("searchVO") VideoMngDefaultVO searchVO,
			HttpServletRequest request) throws Exception {
		String cmd = "update";
		LoginVO user = new LoginVO();

		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		if (auth.getDetails() instanceof tem4UserDetails) {
			tem4UserDetails detail = (tem4UserDetails) auth.getDetails();
			user = detail.getLoginInfo();
		}

		if (user == null) {
			return "forward:/usr/uia/LoginUsr.do";
		}

		HashMap<String,String> map = new HashMap<String,String>();
    	map.put("searchCondition", "videoId");
    	map.put("videoId", videoId);
		VideoMngVO resultList = videomngservice
				.selectVideoMngOneList(map);

		model.addAttribute("resultList", resultList);
		model.addAttribute("cmd", cmd);
		return "/mng/bbs/videoMng/BbsVideoMngInsertView";
	}

	//동영상 수정
	@RequestMapping({"/mng/bbs/videoMng/updateVideoMng.do"})
	public String updateVideoMng(VideoMngVO videoMngVO, Model model, 
			@ModelAttribute("searchVO") VideoMngDefaultVO searchVO, HttpServletRequest request) throws Exception{
		LoginVO user = new LoginVO();

		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		if (auth.getDetails() instanceof tem4UserDetails) {
			tem4UserDetails detail = (tem4UserDetails) auth.getDetails();
			user = detail.getLoginInfo();
		}
		if(user == null) {
//    		model.addAttribute("resultMsg", "fail.common.login");
			return "forward:/usr/uia/LoginUsr.do";
		}else{
			request.setAttribute("userId", user.getId());
			//수정하는 게시물의 원래 상태 먼저 가져옴
			HashMap<String,String> map = new HashMap<String,String>();
			map.put("searchCondition", "videoId");
	    	map.put("videoId", videoMngVO.getVideoId());
			VideoMngVO vmvo = videomngservice.selectVideoMngOneList(map);
			
			String updatecondition = "update";
			videoMngVO.setModId(user.getId());
			//원래상태와 지금의 상태가 같을때
			if(vmvo.getNoticeYn().equals(videoMngVO.getNoticeYn())){
				videomngservice.updateVideoMng(videoMngVO, updatecondition,request);
			//원래상태와 지금의 상태가 다를때
			}else{
				//수정하는 게시물을 N으로 등록시(Y->N으로 수정)
				if(videoMngVO.getNoticeYn().equals("N")){
					//만약 게시물이 하나밖에 없는데 Y->N으로 수정하는 경우
					if(videomngservice.selectVideoMngListCnt(searchVO) == 1){
						//노출여부는 그대로 Y로 유지하고 나머지만 수정
						updatecondition = "update";
						videoMngVO.setNoticeYn("Y");
						videomngservice.updateVideoMng(videoMngVO, updatecondition,request);
					}else{
						//최신것을 Y로 바꾸고
						updatecondition = "noticeYnLatly";
						videomngservice.updateVideoMng(videoMngVO, updatecondition,request);
						//현재 게시물은 N으로
						updatecondition = "update";
						videomngservice.updateVideoMng(videoMngVO, updatecondition,request);						
					}
					
		    	//수정하는 게시물을 Y로 등록시(N->Y로 수정)
				}else{
					//원래 Y였던거 찾아서 N으로 바꿔주고
					updatecondition = "noticeYnintoN";
					videomngservice.updateVideoMng(videoMngVO, updatecondition,request);
					
					//현재 게시물은 Y로
					updatecondition = "update";
					videomngservice.updateVideoMng(videoMngVO, updatecondition,request);
				}
			}
		}
		
		return "redirect:/mng/bbs/videoMng/videoMngList.do?pageIndex="+searchVO.getPageIndex();
	}
	
	//동영상 삭제
	@RequestMapping({"/mng/bbs/videoMng/deleteVideoMng.do"})
	public String deleteVideoMng(@RequestParam String params,
			HttpServletRequest request, VideoMngVO videoMngVO,
			@ModelAttribute("searchVO") VideoMngDefaultVO searchVO) throws Exception{
		HashMap<String, Integer> param = new HashMap<String, Integer>();
		//로그인 확인
		LoginVO user = new LoginVO();

		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		if (auth.getDetails() instanceof tem4UserDetails) {
			tem4UserDetails detail = (tem4UserDetails) auth.getDetails();
			user = detail.getLoginInfo();
		}
		if(user == null) {
//    		model.addAttribute("resultMsg", "fail.common.login");
			return "forward:/usr/uia/LoginUsr.do";
		}else{
			request.setAttribute("userId", user.getId());
			//삭제하는 것 중 노출여부 Y가 있는지 확인하기 위해서 노출여부 Y인 video_id 가져오기
			HashMap<String,String> map = new HashMap<String,String>();
			map.put("searchCondition", "noticeY");
			VideoMngVO vmvo = videomngservice.selectVideoMngOneList(map);

			if (params != null || params != "") {
				String[] params2 = params.toString().split(",");
				for (int i = 0; i < params2.length; i++) {
					String deleteId = params2[i];
			    	//삭제하는 것 중 노출여부 Y가 있으면
			    	if(vmvo.getVideoId().equals(deleteId)){
			    		//원래 노출여부 Y였던 것을 N으로 바꿔준 후
			    		String updatecondition = "noticeYnintoN";
						videomngservice.updateVideoMng(videoMngVO, updatecondition,request);			 
			    		//삭제 후 가장 최근것 Y로 바꿔줌 
			    		videomngservice.deleteVideoMng(deleteId, request);
			    		updatecondition = "noticeYnLatly";
			    		videomngservice.updateVideoMng(videoMngVO, updatecondition, request);
			    	//없으면 그냥 삭제	
			    	}else{
			    		videomngservice.deleteVideoMng(deleteId, request);			    					    		
			    	}
				}
			}
			return "redirect:/mng/bbs/videoMng/videoMngList.do";
		}
	}
	
	
	
	
}