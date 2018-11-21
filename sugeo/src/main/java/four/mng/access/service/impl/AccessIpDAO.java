package four.mng.access.service.impl;

import org.springframework.stereotype.Repository;

import egovframework.com.cmm.service.impl.EgovComAbstractDAO;
import four.mng.access.service.AccessIpVO;

@Repository("accessIpDAO")
public class AccessIpDAO extends EgovComAbstractDAO {
	
	public int selectAccessIpCheck(AccessIpVO ipVO) throws Exception {
		
		return (Integer) select("AccessIpDAO.selectAccessIpCheck", ipVO);
	}
	
}
