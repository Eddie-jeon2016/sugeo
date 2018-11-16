/** ===============================================
 * Copyright(c) 4DEPTH 2017
 * @fileName ContentService.java
 * @author Chang-il Jeon
 * @since 2017. 2. 2.
 * @version 1.0
 * 
 *=================================================
 */
package four.mng.content.service;

import java.util.List;

import egovframework.com.cmm.ComDefaultVO;

public interface ContentService {


	public List<ContentVO> selectContentList(ComDefaultVO searchVO)throws Exception;
	
	public void insertContent(ContentVO cVO)throws Exception;
	
	public void updateContent(ContentVO cVO)throws Exception;
	
	public ContentVO selectContentView(ContentVO cVO);
	
	public int selectContentListCnt() throws Exception;
	
}

