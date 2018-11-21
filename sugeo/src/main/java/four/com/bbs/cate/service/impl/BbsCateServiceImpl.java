package four.com.bbs.cate.service.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import egovframework.com.cmm.service.impl.WorkLogDAO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import four.com.bbs.cate.service.BbsCateService;
import four.com.bbs.cate.service.BbsCateVO;
//import mediacenter.com.bbs.service.BbsUseInf;
//import egovframework.rte.fdl.idgnr.EgovIdGnrService;

/**
 * 게시판 속성관리를 위한 서비스 구현 클래스
 * @author 조은태
 * @since 2013.09.02
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일     	 	수정자           수정내용
 *  -------      	 		--------    ---------------------------
 *   2013.09.02 	 조은태          최초 생성
 *
 * </pre>
 */
@Service("BbsCateService")
public class BbsCateServiceImpl extends EgovAbstractServiceImpl implements BbsCateService {

    @Resource(name = "BbsCateDAO")
    private BbsCateDAO cateDAO;
    
    @Resource(name = "WorkLogDAO")
	private WorkLogDAO workLogDAO;

   // @Resource(name = "BbsUseInfocateDAO")
  //  private BbsUseInfocateDAO bbsUseDAO;

   // @Resource(name = "egovBBSMstrIdGnrService")
  //  private EgovIdGnrService idgenService;
		
    /**
     * 등록된 게시판 속성정보를 삭제한다.
     */
    @Override
	public void deleteBbsCate(String bbscate_id, HttpServletRequest request) throws Exception {
    	request.setAttribute("logType","delete");
    	cateDAO.deleteBbsCate(bbscate_id);
	
	//BbsUseInf bdUseInf = new BbsUseInf();
	
	//bdUseInf.setBbsId(boardCate.getBbsId());
	//bdUseInf.setmodId(boardCate.getmodId());
	
	//bbsUseDAO.deleteBBSUseInfByBoardId(bdUseInf);
    }

    /**
     * 신규 게시판 속성정보를 생성한다.
     */
    @Override
	public String insertBbsCate(BbsCateVO bbsCateVO, HttpServletRequest request) throws Exception {
	//String bbsId = idgenService.getNextStringId();
    String bbscate_id = cateDAO.getNextBbscate_id();
    request.setAttribute("logType","insert");
	bbsCateVO.setBbscateId(bbscate_id);
	
	workLogDAO.insertWorkLog(request);
	cateDAO.insertBbsCate(bbsCateVO);

	return bbscate_id;
	
    }

    /**
     * 게시판 속성정보 한 건을 상세조회한다.
     */
    @Override
	public BbsCateVO selectBbsCate(BbsCateVO bbsCateVO, HttpServletRequest request) throws Exception {
    	request.setAttribute("logType","select");
    	workLogDAO.insertWorkLog(request);
    	return cateDAO.selectBbsCate(bbsCateVO);
    }

    /**
     * 게시판 속성 정보의 목록을 조회 한다.
     * @throws Exception 
     */
/*    public List selectBbsCateList(BbsCateVO bbsCateVO) throws Exception {
    	System.out.println("여기는 'BbsCateServiceImpl' 입니다.");
	List<BbsCateVO> result = cateDAO.selectBbsCateList(bbsCateVO);
	System.out.println("ServiceImpl : result = "+result);
	System.out.println("여기는 'BbsCateServiceImpl'222 입니다.");
	
	Map<String, Object> map = new HashMap<String, Object>();
	
	map.put("resultList", result);
	//map.put("resultCnt", Integer.toString(cnt));
	
	System.out.println("Impl 의 resultList = "+map.get(result));

	return result;
    }*/
    
    
    
    @Override
	public List<BbsCateVO> selectBbsCateList(BbsCateVO bbsCateVO) throws Exception   {
    	
		return cateDAO.selectBbsCateList(bbsCateVO);
	}
    
	/*public List selectBbsCateList(int bbs_id) {
		List<BbsCateVO> bbsCateVO = cateDAO.selectBbsCateList(bbs_id);		
		return bbsCateVO;
	}*/


    /**
     * 게시판 속성정보를 수정한다.
     */
    @Override
	public void updateBbsCate(BbsCateVO bbsCateVO, HttpServletRequest request) throws Exception {
    	System.out.println("여기는 Impl 수정입니다.. = "+bbsCateVO);
    	request.setAttribute("logType","update");
    	workLogDAO.insertWorkLog(request);
    	cateDAO.updateBbsCate(bbsCateVO);
    }
}
