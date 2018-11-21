package four.com.bbs.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;


/**
 * 게시판 속성관리를 위한 서비스 인터페이스 클래스
 * @ author (주)거산디에스엔 조은태
 * @ since 2013.08.21
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
public interface BbsManageService {

	
    /**
     * 등록된 게시판 속성정보를 삭제한다.
     * 
     * @param BbsManageVO
     */
	public void deleteBbsManage(int bbs_id, HttpServletRequest request) throws Exception;
    
    
    
    
    /**
     * 신규 게시판 속성정보를 생성한다.
     * 
     * @param BbsManageVO
     */
    public int insertBbsManage(BbsManageVO bbsManageVO, HttpServletRequest request) throws Exception;

    
    
    
    
    /**
     * 게시판 속성정보 한 건을 상세조회한다.
     * 
     * @param BbsManageVO
     */
    
    
    public BbsManageVO selectBbsManage(BbsManageVO bbsManageVO) throws Exception;

    
    
    
    
    /**
     * 게시판 속성 정보의 목록을 조회 한다.
     * 
     * @param BbsManageVO
     * @return 
     */

    //이 밑에 애가 하는 일이 뭔지 모르겟음 (?)
    //VO에있는 값들을 selectBbsManage(bbsManageVO) 에 담아주는거?
    public List<BbsManageVO> selectBbsManageList(BbsManageVO bbsManageVO, HttpServletRequest request) throws Exception;
    
    //public void selectBbsManageList(BbsManageVO bbsManageVO) throws Exception;
    /**
     * 게시판 bbs_ty_code에 따른 목록을 조회한다.
     * 
     * @param bbs_ty_code
     */
    public List<BbsManageVO> selectBbsListbyBbsTyCode(String bbs_ty_code, HttpServletRequest request) throws Exception;
    /**
     * 게시판 속성정보를 수정한다.
     * 
     * @param BoardMaster
     */
    public void updateBbsManage(BbsManageVO bbsManageVO, HttpServletRequest request) throws Exception;



	//미디어카페에서 사용중이지 않은 게시판인것만 구한다.
	public List<BbsManageVO> selectBbsManageListByTyCode(String bbs_ty_code, HttpServletRequest request) throws Exception;




	Integer selectBbsManageListCnt(BbsManageVO bbsManageVO) throws Exception;

}
