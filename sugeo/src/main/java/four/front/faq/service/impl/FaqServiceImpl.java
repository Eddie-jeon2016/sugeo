package four.front.faq.service.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import egovframework.com.cmm.ComDefaultVO;
import egovframework.com.cmm.service.impl.WorkLogDAO;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import four.front.faq.service.FaqService;
import four.front.faq.service.FaqVO;

@Service("faqService")
public class FaqServiceImpl implements FaqService {
	
	@Resource(name = "faqDAO")
	private FaqDAO faqDAO;

	
	@Resource(name = "WorkLogDAO")
	private WorkLogDAO workLogDAO;
	 
	@Resource(name = "egovFaqMngIdGnrService")
    private EgovIdGnrService idgenService;
	
	@Override
	public List<FaqVO> selectFaqList(ComDefaultVO comVO) throws Exception {

		return faqDAO.selectFaqList(comVO);
	}

	@Override
	public int selectFaqListCnt(ComDefaultVO comVO) throws Exception {
		
		return faqDAO.selectFaqListCnt(comVO);
	}

	
	/*************************관리자
	 * 
	 * 
     * faq 리스트를 불러온다
     */
	
	@Override
	public List<FaqVO> selectFaqMngList(ComDefaultVO comVO) throws Exception {
		
		
		return faqDAO.selectFaqMngList(comVO);
	}


	/**
     * faq 목록 갯수를 조회한다
     */
	@Override
	public Integer selectFaqMngListCnt(ComDefaultVO comVO) throws Exception {
		return faqDAO.selectFaqMngListCnt(comVO);
	}

	/**
     * faq를 등록한다.
     */
	@Override
	public int insertFaqMng(FaqVO faqVO, HttpServletRequest request) throws Exception {
		
		request.setAttribute("logType", "insert");
		request.setAttribute("userId", faqVO.getRegId());
    	workLogDAO.insertWorkLog(request);
		faqVO.setfaqId(idgenService.getNextStringId());
		return faqDAO.insertFaqMng(faqVO);
	}

	/**
     * faq수정을 위해 하나의 리스트를 조회한다.
     */
	@Override
	public FaqVO selectFaqMngOneList(String faqId) throws Exception {
		
		return faqDAO.selectFaqMngOneList(faqId);
	}

	
	/**
     * faq를 수정한다.
     */
	@Override
	public int updateFaqmng(FaqVO faqVO, HttpServletRequest request) throws Exception {
		request.setAttribute("logType", "update");
		request.setAttribute("userId", faqVO.getModId());
    	workLogDAO.insertWorkLog(request);
		return faqDAO.updateFaqMng(faqVO);
	}

	/**
     * faq를 삭제한다.
     */
	@Override
	public int deleteFaqMng(String deleteId, HttpServletRequest request) throws Exception {
		request.setAttribute("logType", "delete");
    	workLogDAO.insertWorkLog(request);
		return faqDAO.deleteFaqMng(deleteId);
	}

	
	
	
	
}


