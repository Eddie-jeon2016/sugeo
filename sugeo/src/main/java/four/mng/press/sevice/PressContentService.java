package four.mng.press.sevice;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import four.com.bbs.service.BbsDefaultVO;
import four.com.bbs.service.BbsVO;


public interface PressContentService {
	
	/** 리스트를 조회한다*/
	 public List<PressContentVO> selectPressContentList(PressContentDefaultVO searchVO) throws Exception;

	 /** 리스트 목록 갯수를 조회한다*/
	 public Integer selectPressContentListCnt(PressContentDefaultVO searchVO)throws Exception;
	 
	 /** 언론보도자료를 등록한다*/
	 public int insertPressContent(PressContentVO pressContentVO, HttpServletRequest request)throws Exception;
	 
	 /** 수정을 위해 리스트를 조회한다*/
	 public PressContentVO selectPressContentOneList(String pcId)throws Exception;	 
	 
	 
	 /** 언론보도자료를 수정한다*/
	 public int updatePressContent(PressContentVO pressContentVO, HttpServletRequest request)throws Exception;
	 
	 /** 언론보도자료를 삭제한다*/
	 public int deletePressContent(String deleteId, HttpServletRequest request)throws Exception;
	 
	 
}
