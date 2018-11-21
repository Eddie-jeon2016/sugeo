package four.com.member.service.impl;

import org.springframework.stereotype.Repository;

import egovframework.com.cmm.service.impl.EgovComAbstractDAO;
import four.com.member.service.MemberVO;

@Repository("MemberDAO")
public class MemberDAO extends EgovComAbstractDAO{
	
	
	/**회원정보 등록*/
	public int insertMember(MemberVO memberVO) throws Exception{
		return insert("", memberVO);
	}

}
