package four.com.video.service.impl;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import egovframework.com.cmm.service.impl.WorkLogDAO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import four.com.video.service.VideoMngDefaultVO;
import four.com.video.service.VideoMngService;
import four.com.video.service.VideoMngVO;

@Service("VideoMngService")
public class VideoMngServiceImpl extends EgovAbstractServiceImpl implements VideoMngService{

	@Resource(name = "VideoMngDAO")
	private VideoMngDAO videomngDAO;
	
	@Resource(name = "egovVideoMngIdGnrService")
    private EgovIdGnrService idgenService;
	
	@Resource(name = "WorkLogDAO")
	private WorkLogDAO workMngDAO;
	
	
	/**
     * 게시판 리스트를 불러온다
     */
	@Override
	public List<VideoMngVO> selectVideoMngList(VideoMngDefaultVO serchVO)
			throws Exception {
		return videomngDAO.selectVideoMngList(serchVO);
	}

	/**
     * 게시판 목록 갯수를 조회한다
     */
	@Override
	public Integer selectVideoMngListCnt(VideoMngDefaultVO serchVO)
			throws Exception {
		return videomngDAO.selectVideoMngListCnt(serchVO);
	}

	/**
	 * 비디오를 등록한다
	 */
	@Override
	public int insertVideoMng(VideoMngVO videoMngVO, HttpServletRequest request) throws Exception {
		request.setAttribute("logType", "insert");
		request.setAttribute("userId", videoMngVO.getRegId());
		workMngDAO.insertWorkLog(request);
		
		videoMngVO.setVideoId(idgenService.getNextStringId()); 
		return videomngDAO.insertVideoMng(videoMngVO);
	}

	/**
	 * 비디오 수정을 위해 리스트를 조회한다
	 */
	@Override
	public VideoMngVO selectVideoMngOneList(HashMap<String, String> map) throws Exception {
		return videomngDAO.selectVideoMngOneList(map);
	}

	/**
	 * 비디오를 수정한다
	 */
	@Override
	public int updateVideoMng(VideoMngVO videoMngVO, String updatecondition, HttpServletRequest request) throws Exception {
		request.setAttribute("logType", "update");
		request.setAttribute("userId", videoMngVO.getModId());
		workMngDAO.insertWorkLog(request);
		
		if(updatecondition.equals("noticeYn")){
			return videomngDAO.updateVideoMngNoticeYn(videoMngVO);
		}else if(updatecondition.equals("noticeYnLatly")){
			return videomngDAO.updateVideoMngNoticeYnLatly();
		}else if(updatecondition.equals("noticeYnintoN")){
			return videomngDAO.updateVideoMngNoticeYnintoN();
		}else{
			return videomngDAO.updateVideoMng(videoMngVO);			
		}
	}

	/**
	 * 비디오를 삭제한다
	 */
	@Override
	public int deleteVideoMng(String deleteId, HttpServletRequest request) throws Exception {
		request.setAttribute("logType", "delete");
		workMngDAO.insertWorkLog(request);
		
		return videomngDAO.deleteVideoMng(deleteId);
	}

	
	/**
	 * 사용자 페이지의 비디오를 불러온다
	 */
	@Override
	public VideoMngVO selectVideOneList() throws Exception {
		return videomngDAO.selectVideoOneList();
	}
	
	

}
