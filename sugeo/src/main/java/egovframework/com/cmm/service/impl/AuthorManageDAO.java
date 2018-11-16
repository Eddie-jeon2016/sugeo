/** ===============================================
 * Copyright(c) 4DEPTH 2017
 * @fileName AuthorManageDAO.java
 * @author Chang-il Jeon
 * @since 2017. 3. 7.
 * @version 1.0
 * 
 *=================================================
 */
package egovframework.com.cmm.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.com.cmm.AuthManageVO;


@Repository("AuthorManageDAO")
public class AuthorManageDAO extends EgovComAbstractDAO {

	public List<AuthManageVO> selectAuthList() throws Exception{
		
		return (List<AuthManageVO>)list("AuthorManageDAO.selectAuthList");
	}
	
}

