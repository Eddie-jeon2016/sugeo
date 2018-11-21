package four.front.faq.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import egovframework.com.cmm.ComDefaultVO;

public interface FaqService {
	
	public List<FaqVO> selectFaqList(ComDefaultVO comVO) throws Exception;
	
	public int selectFaqListCnt(ComDefaultVO comVO) throws Exception;
	
////////////////////////////////////////////관리자 페이지
	/** 리스트를 조회한다*/
	public List<FaqVO> selectFaqMngList(ComDefaultVO comVO) throws Exception;

	/** 리스트를 갯수를 조회한다*/
	public Integer selectFaqMngListCnt(ComDefaultVO comVO) throws Exception;
	
	/** faq를 등록한다*/
	public int insertFaqMng(FaqVO fagVO, HttpServletRequest request)throws Exception;
	
	/** faq 수정을 위해 리스트를 조회한다*/
	public FaqVO selectFaqMngOneList(String faqId)throws Exception;
	
	/** faq를 수정한다*/
	public int updateFaqmng(FaqVO faqVO, HttpServletRequest request)throws Exception;
	
	/** faq를 삭제한다*/
	public int deleteFaqMng(String deleteId, HttpServletRequest request) throws Exception;
	
	
}
