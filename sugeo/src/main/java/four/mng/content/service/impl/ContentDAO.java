/** ===============================================
 * Copyright(c) 4DEPTH 2017
 * @fileName ContentDAO.java
 * @author Chang-il Jeon
 * @since 2017. 2. 2.
 * @version 1.0
 * 
 *=================================================
 */
package four.mng.content.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import egovframework.com.cmm.ComDefaultVO;
import egovframework.com.cmm.service.impl.EgovComAbstractDAO;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import four.mng.content.service.ContentVO;

@Repository("ContentDAO")
public class ContentDAO extends EgovComAbstractDAO{
	
	public List<ContentVO> selectContentList(ComDefaultVO searchVO) throws Exception {
		return (List<ContentVO>) list("ContentDAO.selectContentList", searchVO);
	}

	public void insertContent(ContentVO cVO){
		insert("ContentDAO.insertContent",cVO);
	}
	
	public void updateContent(ContentVO cVO){
		update("ContentDAO.updateContent",cVO);
	}
	
	public ContentVO selectContentView(ContentVO cVO){
		return (ContentVO) select("ContentDAO.selectContentView",cVO);
	}
	
	public int selectContentListCnt() throws Exception {
		return (Integer) select("ContentDAO.selectContentListCnt");
	}
	
	
}

