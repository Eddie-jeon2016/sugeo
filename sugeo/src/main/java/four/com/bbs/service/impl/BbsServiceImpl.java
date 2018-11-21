package four.com.bbs.service.impl;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import egovframework.com.cmm.service.impl.WorkLogDAO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import four.com.bbs.service.BbsDefaultVO;
import four.com.bbs.service.BbsService;
import four.com.bbs.service.BbsVO;

/**
 * 게시판 속성관리를 위한 서비스 구현 클래스
 * @author 공통컴포넌트개발팀 한성곤
 * @since 2009.08.25
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일      수정자           수정내용
 *  -------       --------    ---------------------------
 *   2009.08.25  한성곤          최초 생성
 *
 * </pre>
 */
@Service("bbsService")
public class BbsServiceImpl extends EgovAbstractServiceImpl implements BbsService {

    @Resource(name = "BbsDAO")
    private BbsDAO bbsDAO;
    
    @Resource(name = "WorkLogDAO")
	private WorkLogDAO workMngDAO;
    
    /**
     * 신규 게시판 속성정보를 생성한다.
     */
    @Override
	public String insertBbs(BbsVO bbsVO, HttpServletRequest request) throws Exception {
    	String ntt_id = bbsDAO.getNextNtt_id();
		bbsVO.setNttId(ntt_id);
		
		request.setAttribute("logType", "insert");
		request.setAttribute("userId", bbsVO.getregId());
		workMngDAO.insertWorkLog(request);
		
		bbsDAO.insertBbs(bbsVO);
	
		return ntt_id;
    }
    /**
     * 게시물 그룹번호 +1 
     */
    @Override
	public String insertNtt_no(BbsVO bbsVO) throws Exception {
    	String ntt_no = bbsDAO.getNextNtt_no();
		bbsVO.setNttId(ntt_no);
		return ntt_no;
    }

    /**
     * 게시판 속성 정보의 목록을 조회 한다.
     */
    
	@Override
	public List<BbsVO> selectBbsList(BbsDefaultVO searchVO) throws Exception {
		return bbsDAO.selectBbsList(searchVO);
	}

    /**
     * 일반회원 총 갯수를 조회한다.
     * @param userSearchVO 검색조건
     * @return 일반회원총갯수(int) 
     */
    @Override
	public Integer selectBbsListCnt(BbsDefaultVO searchVO) throws Exception {
    	return bbsDAO.selectBbsListCnt(searchVO);
    }
    
    /**
     *  검색 계산용 리플갯수 
     */
    @Override
    public Integer selectBbsListReplyCnt(BbsDefaultVO searchVO) throws Exception {
    	return bbsDAO.selectBbsListReplyCnt(searchVO);
    }
    /**
     *  검색 계산용 리플갯수 
     */
    @Override
    public List<BbsVO> selectBbsListnoticeYn(BbsDefaultVO searchVO) throws Exception {
    	return bbsDAO.selectBbsListnoticeYn(searchVO);
    }
    
    
    /**
     * 게시물 한 건에 대해 상세 조회한다.
     * @param ntt_it 게시물ID값
     * @return BbsVO
     */  
	@Override
	public BbsVO selectBbs(String ntt_id) {
		BbsVO bbsVO = bbsDAO.selectBbs(ntt_id);		
		return bbsVO;
	}
    /**
     * 상세조회한 게시물을 삭제한다.
     * 
     * @param ntt_id
     * @return 
     */
	@Override
	public void deleteBbs(Integer ntt_id, HttpServletRequest request) throws Exception {
		request.setAttribute("logType", "delete");
		workMngDAO.insertWorkLog(request);
		
		bbsDAO.deleteBbs(ntt_id);
	}
	
	
	
	/**
	 * 게시물 여러개를 삭제한다.
	 * 
	 * @param ntt_id
	 * @return 
	 */
	@Override
	public void deleteBbs(HashMap<String, String> param) throws Exception {
		bbsDAO.deleteBbs(param);
		
	}
	
	
	/**
     * 상세조회한 게시물을 완전삭제한다.
     * 
     * @param ntt_id
     * @return 
     */
	@Override
	public void deleteRealBbs(Integer ntt_id) throws Exception {
		bbsDAO.deleteRealBbs(ntt_id);
	}
	
    /**
     * 게시물 상세정보를 수정한다.
     */
    @Override
	public void updateBbs(BbsVO bbsVO, HttpServletRequest request) throws Exception {
		request.setAttribute("logType", "update");
		request.setAttribute("userId", bbsVO.getmodId());
		workMngDAO.insertWorkLog(request);
    	
    	
    	bbsDAO.updateBbs(bbsVO);
    }
	
    
    //조회수
    public void updateRdCnt(String ntt_id) throws Exception {
    	bbsDAO.updateRdcnt(ntt_id);
    }
    /**
     * 게시판 속성 정보의 목록을 조회 한다.
     * 
     * @param PdMovieDefaultVO
     * @return List<BbsVO>
     */
	@Override
	public List<BbsVO> selectBbsListByUser(BbsDefaultVO searchVO) throws Exception {
		return bbsDAO.selectBbsByUserList(searchVO);
	}
	/**
     * 게시물 정보의 목록의 갯수을 조회 한다.
     * 
     * @param PdMovieDefaultVO
     * @return int
     */   
    @Override
	public Integer selectBbsListByUserCnt(BbsDefaultVO searchVO) throws Exception {
    	return bbsDAO.selectBbsListByUserCnt(searchVO);
    }
	/* (non-Javadoc)
	 * @see four.com.bbs.service.BbsService#selectBbsForMain(four.com.bbs.service.BbsDefaultVO)
	 */
	@Override
	public List<BbsVO> selectBbsForMain(BbsDefaultVO searchVO) throws Exception {
		// TODO Auto-generated method stub
		return bbsDAO.selectBbsForMain(searchVO);
	}
	/* (non-Javadoc)
	 * @see four.com.bbs.service.BbsService#selectBbsListforSearch(four.com.bbs.service.BbsDefaultVO)
	 */
	@Override
	public List<BbsVO> selectBbsListforSearch(BbsDefaultVO searchVO)
			throws Exception {
		// TODO Auto-generated method stub
		return bbsDAO.selectBbsListforSearch(searchVO);
	}
	/* (non-Javadoc)
	 * @see four.com.bbs.service.BbsService#selectBbsListCntforSearch(four.com.bbs.service.BbsDefaultVO)
	 */
	@Override
	public Integer selectBbsListCntforSearch(BbsDefaultVO searchVO)
			throws Exception {
		// TODO Auto-generated method stub
		return bbsDAO.selectBbsListCntforSearch(searchVO);
	}
	@Override
	public BbsVO nextSelectBoardArticle(BbsVO bbsVO) throws Exception {
		
		return bbsDAO.nextSelectBoardArticle(bbsVO);
	}
	@Override
	public BbsVO prevSelectBoardArticle(BbsVO bbsVO) throws Exception {

		return bbsDAO.prevSelectBoardArticle(bbsVO);
	}
	@Override
	public List<BbsVO> selectEvnetNoticeYList(BbsDefaultVO bbsVO) throws Exception {
		
		return bbsDAO.selectEvnetNoticeYList(bbsVO);
	}
	@Override
	public List<BbsVO> selectEvnetNoticeNList(BbsDefaultVO bbsVO) throws Exception {
		
		return bbsDAO.selectEvnetNoticeNList(bbsVO);
	}
	@Override
	public int selectEvnetNoticeNListCnt(BbsDefaultVO bbsVO) throws Exception {
		
		return bbsDAO.selectEvnetNoticeNListCnt(bbsVO);
	}
	@Override
	public BbsVO prevSelectEventNotice(BbsVO bbsVO) throws Exception {
		return bbsDAO.prevSelectEventNotice(bbsVO);
	}
	@Override
	public BbsVO nextSelectEventNotice(BbsVO bbsVO) throws Exception {
		return bbsDAO.nextSelectEventNotice(bbsVO);
	}
	
    
	
}
