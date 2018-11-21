package four.com.order.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.com.cmm.service.impl.EgovComAbstractDAO;
import four.com.item.service.ItemVO;
import four.com.order.service.OrderVO;
import four.com.video.service.VideoMngDefaultVO;
import four.com.video.service.VideoMngVO;

@Repository("OrderDAO")
public class OrderDAO extends EgovComAbstractDAO{

	/** 수거요청 추가 */
	public int insertOrder(OrderVO orderVO) throws Exception {
		return insert("OrderDAO.insertOrder", orderVO);
	}
	/** 수거 견적문의*/
	public int insertUploadOrder(OrderVO orderVO) throws Exception {
		return insert("OrderDAO.insertUploadOrder", orderVO);
	}
	
	/**등록한 수거요청 정보 가져오기(팝업을 위해) */
	public OrderVO selectOrderOne(OrderVO orderVO) throws Exception {
		return selectOne("OrderDAO.selectOrderOne", orderVO);
	}
	

}
