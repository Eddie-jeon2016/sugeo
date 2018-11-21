package four.com.order.service.impl;

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
import four.com.order.service.OrderImgService;
import four.com.order.service.OrderImgVO;
import four.com.order.service.OrderService;
import four.com.order.service.OrderVO;
import four.com.video.service.VideoMngDefaultVO;
import four.com.video.service.VideoMngService;
import four.com.video.service.VideoMngVO;

@Service("OrderImgService")
public class OrderImgServiceImpl extends EgovAbstractServiceImpl implements OrderImgService{

	@Resource(name = "OrderImgDAO")
	private OrderImgDAO orderImgDAO;
	
	@Override
	public int insertOrderImg(OrderImgVO orderImgVO) throws Exception {
		return orderImgDAO.insertOrderImg(orderImgVO);
	}


}
