package four.com.bbs.cate.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;


/**
 * 게시판 속성관리를 위한 서비스 인터페이스 클래스
 * @ author (주)거산디에스엔 조은태
 * @ since 2013.09.02
 * @ version 1.0
 * @ see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 * @  수정일              수정자               수정내용
 * @ --------------     ---------     -------------------------------
 * @ 2013.08.21      조은태               최초생성
 *
 */
public interface BbsCateService {

    /**
     * 등록된 게시판 속성정보를 삭제한다.
     * 
     * @param asdaas
     */
	public void deleteBbsCate(String bbsCate_id, HttpServletRequest request) throws Exception;
    
    
    
    
    /**
     * 신규 게시판 속성정보를 생성한다.
     * 
     * @param asdaas
     */
    public String insertBbsCate(BbsCateVO bbsCateVO, HttpServletRequest request) throws Exception;

    
    
    
    
    /**
     * 게시판 속성정보 한 건을 상세조회한다.
     * 
     * @param asdaas
     */
    
    
    public BbsCateVO selectBbsCate(BbsCateVO bbsCateVO, HttpServletRequest request) throws Exception;

    
    
    
    
    /**
     * 게시판 속성 정보의 목록을 조회 한다.
     * 
     * @param asdaas
     * @return 
     */

    //이 밑에 애가 하는 일이 뭔지 모르겟음 (?)
    //VO에있는 값들을 selectBbsManage(bbsManageVO) 에 담아주는거?
    
    //public void selectBbsManageList(BbsManageVO bbsManageVO) throws Exception;
	public List<BbsCateVO> selectBbsCateList(BbsCateVO bbsCateVO) throws Exception;
    
    
    /**
     * 게시판 속성정보를 수정한다.
     * 
     * @param BoardMaster
     */
    public void updateBbsCate(BbsCateVO bbsCateVO, HttpServletRequest request) throws Exception;



    /**
     * 카운트 세는거지만 말머리에서는 필요없다고 ... ^_^
     */
	//public int selectBbsCateListCnt(BbsCateVO bbsCateVO) throws Exception;


}
