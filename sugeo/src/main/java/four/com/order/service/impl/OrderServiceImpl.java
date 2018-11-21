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
import four.com.order.service.OrderService;
import four.com.order.service.OrderVO;
import four.com.video.service.VideoMngDefaultVO;
import four.com.video.service.VideoMngService;
import four.com.video.service.VideoMngVO;

@Service("OrderService")
public class OrderServiceImpl extends EgovAbstractServiceImpl implements OrderService{

	@Resource(name = "OrderDAO")
	private OrderDAO orderDAO;
	
	@Resource(name = "egovVideoMngIdGnrService")
    private EgovIdGnrService idgenService;
	

	@Override
	public int insertOrder(OrderVO orderVO) throws Exception {
		return orderDAO.insertOrder(orderVO);
		
	}
	
	@Override
	public int insertUploadOrder(OrderVO orderVO) throws Exception {
		return orderDAO.insertUploadOrder(orderVO);
	}
	
	@Override
	public OrderVO selectOrderOne(OrderVO orderVO) throws Exception {
		return orderDAO.selectOrderOne(orderVO);
	}



}
