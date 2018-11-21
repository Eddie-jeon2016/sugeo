package four.com.item.service;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

public interface ItemService {
	/** 리스트를 조회한다*/
	public List<ItemVO> selectItemFrontList() throws Exception;

/*	*//** 리스트 목록 갯수를 조회한다*//*
	public Integer selectVideoMngListCnt(itemDefaultVO serchVO) throws Exception;

	*//** 비디오를 등록한다*//*
	public int insertVideoMng(itemVO videoMngVO, HttpServletRequest request) throws Exception;
	
	*//** 수정을 위해 글 하나를 조회한다*//*
	public itemVO selectVideoMngOneList(HashMap<String, String> map) throws Exception;
	
	*//** 비디오를 수정한다*//*
	public int updateVideoMng(itemVO videoMngVO, String updatecondition, HttpServletRequest request) throws Exception;
	
	*//** 비디오를 삭제한다*//*
	public int deleteVideoMng(String deleteId, HttpServletRequest request) throws Exception;
	
	*//** 사용자 페이지에 띄워줄 비디오를 하나 가져온다*//*
	public itemVO selectVideOneList() throws Exception;*/
	
}
