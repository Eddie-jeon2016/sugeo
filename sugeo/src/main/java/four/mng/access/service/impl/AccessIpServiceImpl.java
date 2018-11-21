package four.mng.access.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import four.mng.access.service.AccessIpService;
import four.mng.access.service.AccessIpVO;

@Service("accessIpService")
public class AccessIpServiceImpl implements AccessIpService {
	
	@Resource(name = "accessIpDAO")
	private AccessIpDAO accessIpDAO;

	@Override
	public int selectAccessIpCheck(AccessIpVO ipVO) throws Exception {
		
		return accessIpDAO.selectAccessIpCheck(ipVO);
	}

	
	
	
}
