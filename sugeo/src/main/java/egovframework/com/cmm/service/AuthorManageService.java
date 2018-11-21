/** ===============================================
 * Copyright(c) 4DEPTH 2017
 * @fileName AuthorManageService.java
 * @author Chang-il Jeon
 * @since 2017. 3. 7.
 * @version 1.0
 * 
 *=================================================
 */
package egovframework.com.cmm.service;

import java.util.List;

import egovframework.com.cmm.AuthManageVO;

public interface AuthorManageService {

	
	public List<AuthManageVO> selectAuthList() throws Exception;
		
	
}

