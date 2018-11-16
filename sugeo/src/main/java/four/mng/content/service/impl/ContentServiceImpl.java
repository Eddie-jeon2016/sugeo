/** ===============================================
 * Copyright(c) 4DEPTH 2017
 * @fileName ContentServiceImpl.java
 * @author Chang-il Jeon
 * @since 2017. 2. 2.
 * @version 1.0
 * 
 *=================================================
 */
package four.mng.content.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.com.cmm.ComDefaultVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import four.mng.content.service.ContentService;
import four.mng.content.service.ContentVO;

@Service("ContentService")
public class ContentServiceImpl extends EgovAbstractServiceImpl implements ContentService{

	@Resource(name = "ContentDAO")
	private ContentDAO contentDAO;
	
	@Resource(name = "egovContentIdGnrService")
	private EgovIdGnrService idgenService;

	/* (non-Javadoc)
	 * @see four.mng.content.service.ContentService#selectContentList(egovframework.com.cmm.ComDefaultVO)
	 */
	@Override
	public List<ContentVO> selectContentList(ComDefaultVO searchVO)
			throws Exception {
		// TODO Auto-generated method stub
		return contentDAO.selectContentList(searchVO);
	}

	/* (non-Javadoc)
	 * @see four.mng.content.service.ContentService#insertContent(four.mng.content.service.ContentVO)
	 */
	@Override
	public void insertContent(ContentVO cVO)throws Exception {
		String contentId = idgenService.getNextStringId();
		cVO.setContentId(contentId);
		
		contentDAO.insertContent(cVO);
	}

	
	
	/* (non-Javadoc)
	 * @see four.mng.content.service.ContentService#updateContent(four.mng.content.service.ContentVO)
	 */
	@Override
	public void updateContent(ContentVO cVO) throws Exception {
		// TODO Auto-generated method stub
		
		contentDAO.updateContent(cVO);
		
	}

	/* (non-Javadoc)
	 * @see four.mng.content.service.ContentService#selectContentView(four.mng.content.service.ContentVO)
	 */
	@Override
	public ContentVO selectContentView(ContentVO cVO) {
		// TODO Auto-generated method stub
		return contentDAO.selectContentView(cVO);
	}

	@Override
	public int selectContentListCnt() throws Exception {
		
		return contentDAO.selectContentListCnt();
	}
	


	
	
	
}

