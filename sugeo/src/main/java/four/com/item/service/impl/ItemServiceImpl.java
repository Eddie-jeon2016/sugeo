package four.com.item.service.impl;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import egovframework.com.cmm.service.impl.WorkLogDAO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import four.com.item.service.ItemService;
import four.com.item.service.ItemVO;
import four.com.video.service.VideoMngDefaultVO;
import four.com.video.service.VideoMngService;
import four.com.video.service.VideoMngVO;

@Service("ItemService")
public class ItemServiceImpl extends EgovAbstractServiceImpl implements ItemService{

	@Resource(name = "ItemDAO")
	private ItemDAO itemDAO;
	
	@Resource(name = "egovVideoMngIdGnrService")
    private EgovIdGnrService idgenService;
	
	@Resource(name = "WorkLogDAO")
	private WorkLogDAO workMngDAO;
	
	
	/**
     * 게시판 리스트를 불러온다
     */
	@Override
	public List<ItemVO> selectItemFrontList() throws Exception {
		return itemDAO.selectItemFrontList();
	}
}
