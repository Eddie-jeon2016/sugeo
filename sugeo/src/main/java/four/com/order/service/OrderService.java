package four.com.order.service;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

public interface OrderService {
	
	public int insertOrder(OrderVO orderVO) throws Exception;
	
	public int insertUploadOrder(OrderVO orderVO) throws Exception;
	
	public OrderVO selectOrderOne(OrderVO orderVO) throws Exception;
}
