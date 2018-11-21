package four.com.video.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.com.cmm.service.impl.EgovComAbstractDAO;
import four.com.video.service.VideoMngDefaultVO;
import four.com.video.service.VideoMngVO;

@Repository("VideoMngDAO")
public class VideoMngDAO extends EgovComAbstractDAO{

	/**게시판 리스트를 조회한다 */
	public List<VideoMngVO> selectVideoMngList(VideoMngDefaultVO serchVO) throws Exception{
		return (List<VideoMngVO>) list("VideoMngDAO.selectVideoMngList", serchVO);
	}
	
	/**게시판 목록 갯수를 조회한다*/
	public int selectVideoMngListCnt(VideoMngDefaultVO serchVO) throws Exception{
		return (Integer)select("VideoMngDAO.selectVideoMngListCnt", serchVO);
	}
	
	/**비디오를 등록한다*/
	public int insertVideoMng(VideoMngVO videoMngVO) throws Exception{
		return insert("VideoMngDAO.insertVideoMng", videoMngVO);
	}
	
	/**동영상 수정을 위해 리스트를 조회한다*/
	public VideoMngVO selectVideoMngOneList(HashMap<String, String> map) throws Exception{
		return selectOne("VideoMngDAO.selectVideoMngOneList", map);
	}
	
	/**동영상을 수정한다*/
	public int updateVideoMng(VideoMngVO videoMngVO) throws Exception{
		return update("VideoMngDAO.updateVideoMng", videoMngVO);
	}
	
	/**동영상을 노출여부를 수정한다*/
	public int updateVideoMngNoticeYn(VideoMngVO videoMngVO) throws Exception{
		return update("VideoMngDAO.updateVideoMngNoticeYn", videoMngVO);
	}

	/**동영상을 노출여부가 Y인 게시물을 N으로 수정한다*/
	public int updateVideoMngNoticeYnintoN() throws Exception{
		return update("VideoMngDAO.updateVideoMngNoticeYnintoN");
	}
	
	/**제일 최근 게시물의 노출여부를 수정한다*/
	public int updateVideoMngNoticeYnLatly()throws Exception{
		return update("VideoMngDAO.updateVideoMngNoticeYnLately");
	}

	/**동영상을 삭제한다*/
	public int deleteVideoMng(String deleteId) throws Exception{
		return update("VideoMngDAO.deleteVideoMng", deleteId);
	}
	
	/**사용자 페이지 동영상을 가져온다*/
	public VideoMngVO selectVideoOneList()throws Exception{
		return selectOne("VideoMngDAO.selectVideoOneList");
	}
	
	

}
