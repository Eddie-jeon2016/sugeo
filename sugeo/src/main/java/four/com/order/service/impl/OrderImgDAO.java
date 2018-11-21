package four.com.order.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.com.cmm.service.impl.EgovComAbstractDAO;
import four.com.item.service.ItemVO;
import four.com.order.service.OrderImgVO;
import four.com.order.service.OrderVO;
import four.com.video.service.VideoMngDefaultVO;
import four.com.video.service.VideoMngVO;

@Repository("OrderImgDAO")
public class OrderImgDAO extends EgovComAbstractDAO{

	/** 견적문의 이미지 추가 */
	public int insertOrderImg(OrderImgVO orderImgVO) throws Exception {
		return insert("OrderImgDAO.insertOrderImg", orderImgVO);
	}

}
