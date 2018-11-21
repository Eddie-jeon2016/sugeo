/** ===============================================
 * Copyright(c) 4DEPTH 2017
 * @fileName AuthorManageServiceImpl.java
 * @author Chang-il Jeon
 * @since 2017. 3. 7.
 * @version 1.0
 * 
 *=================================================
 */
package egovframework.com.cmm.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.com.cmm.AuthManageVO;
import egovframework.com.cmm.service.AuthorManageService;

@Service("AuthorManageService")
public class AuthorManageServiceImpl implements AuthorManageService{
	
	@Resource(name = "AuthorManageDAO")
	AuthorManageDAO authDao; 
	
	/* (non-Javadoc)
	 * @see egovframework.com.cmm.service.AuthorManageService#selectAuthList()
	 */
	@Override
	public List<AuthManageVO> selectAuthList() throws Exception {
		// TODO Auto-generated method stub
		return authDao.selectAuthList();
	}

	
}

