package four.com.order.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import com.google.gson.Gson;

import egovframework.com.cmm.service.FileMngService;
import egovframework.com.cmm.service.FileMngUtil;
import egovframework.com.cmm.service.FileVO;
import four.com.item.service.ItemService;
import four.com.item.service.ItemVO;
import four.com.order.service.OrderImgService;
import four.com.order.service.OrderImgVO;
import four.com.order.service.OrderService;
import four.com.order.service.OrderVO;


@Controller
public class OrderFrontController {

	@Resource(name = "OrderService")
	public OrderService orderService;
	
	@Resource(name = "ItemService")
	public ItemService itemService;
	
	@Resource(name = "OrderImgService")
	public OrderImgService orderImgService;
	
    @Resource(name = "FileMngUtil")
    private FileMngUtil fileUtil;
    
    @Resource(name = "FileMngService")
    private FileMngService fileMngService;
	
	@RequestMapping({"/front/order/insertOrderView.do"})
	public String insertOrderView(Model model, @ModelAttribute ItemVO itemVO,
						HttpServletRequest request) throws Exception{
		System.out.println("===================예약하는 곳 시작==================");

		List<ItemVO> itemList = itemService.selectItemFrontList();
		
		int totalPoint = 0;
		int itemCnt = 0;
		int itemKg = 0;
		String itemNo = null;
		
		for (int i = 0; i < itemList.size(); i++) {
			for (int j = 0; j < itemVO.getParams().length; j++) {
				String param = itemVO.getParams()[i];
				if(!param.equals("")) {
					System.out.println("param = " + param);
					String[] gubun = param.split(",");
					itemNo = gubun[0];	//	수거품목 종류
					String unit = gubun[2];		//	단위(개,kg)
					if(itemNo.equals(itemList.get(i).getItemNo())) {
						totalPoint +=  Integer.parseInt(itemList.get(i).getPrice())* Integer.parseInt(gubun[1]);
						if(unit.equals("개"))
							itemCnt += Integer.parseInt(gubun[1]);
						else 
							itemKg += Integer.parseInt(gubun[1]);
						break;
					}
				}
			}
		}
		
		model.addAttribute("totalPoint", totalPoint);
		model.addAttribute("itemCnt", itemCnt);
		model.addAttribute("itemKg", itemKg);
		model.addAttribute("itemNo", itemNo);
		System.out.println("===================예약하는 곳 종료==================");
		return "front/order/order";
	}
	
	@RequestMapping(value={"/front/order/insertOrder.do"},produces = "application/json; charset=utf8")
    @ResponseBody
	public String insertOrder(Model model, @ModelAttribute OrderVO orderVO,
			HttpServletRequest request) throws Exception{
		System.out.println("===================수거요청 추가하는 곳 시작==================");
		HashMap<String, Object> result = new HashMap<>();
		System.out.println(orderVO);
		orderVO.setMemNo("0");
		orderVO.setCmpyNo("00001");
		orderVO.setCmpyNm("수거업체");
		int count = orderService.insertOrder(orderVO);
		if(count == 1) {
			result.put("result_code","200");
			OrderVO resultOrderVO = orderService.selectOrderOne(orderVO);
			result.put("resultOrderVO", resultOrderVO);
		}else {
			result.put("result_code","500");
			result.put("msg","수거요청에 실패하였습니다.");
		}
		System.out.println("===================수거요청 추가하는 곳 종료==================");
		return new Gson().toJson(result);
	}
	
	
	@RequestMapping({"/front/order/insertUploadOderView.do"})
	public String insertUploadOderView(Model model, @ModelAttribute ItemVO itemVO,
			HttpServletRequest request) throws Exception{
		System.out.println("===================견적하는 곳 시작==================");
		
		model.addAttribute("itemNo", itemVO.getItemNo());
		System.out.println("===================견적하는 곳 종료==================");
		return "front/order/orderUpload";
	}
	
	@RequestMapping(value = {"/front/order/insertUploadOder.do"},produces = "application/json; charset=utf8")
	@ResponseBody
	public String insertUploadOder(Model model, MultipartHttpServletRequest multiRequest,
			HttpServletRequest request, @ModelAttribute OrderVO orderVO) throws Exception{
		System.out.println("===================견적추가하는 곳 시작==================");
		System.out.println("orderVO = " + orderVO);
		String uploadFolder = "";
		HashMap<String, Object> result = new HashMap<>();

		try{
			Map<String, MultipartFile> files = multiRequest.getFileMap();
			List<FileVO> fileList = new ArrayList<>();
			List<OrderImgVO> addFileList = new ArrayList<>();
			
			if(!files.isEmpty()) {
				fileList = fileUtil.parseFileInf(files, "UPD_", 0,"",uploadFolder);
				for(FileVO fvo : fileList) {
					if(fvo.getFieldName().indexOf("uploadFile")> -1) {
						OrderImgVO  imgVO = new OrderImgVO();
						imgVO.setOrderNo(orderVO.getOrderNo());
						imgVO.setImgNm(fvo.getOrignlfileName());
						imgVO.setRegMemNo("0");
						//orderImgService.insertOrderImg(imgVO);
						addFileList.add(imgVO);
					}
				}
/*				
				if(addFileList.size()>0){
	                String addFileId = fileMngService.insertFileInfs(addFileList);
	            }*/
			}

			orderVO.setMemNo("0");
			orderVO.setMemNm("문의하는 사람이름");
			orderVO.setAddr("견적문의하는 사람의 주소");
			orderVO.setMobile("01056144367");
			orderVO.setRegMemNo("0");

			int cnt = orderService.insertUploadOrder(orderVO);
			
			OrderVO resultOrderVO = orderService.selectOrderOne(orderVO);
			for(int i = 0; i < addFileList.size(); i++) {
				addFileList.get(i).setOrderNo(resultOrderVO.getOrderNo());
				orderImgService.insertOrderImg(addFileList.get(i));
			}
			
			if(cnt == 1) {
				result.put("result_code", "200");
				result.put("msg", "견적문의가 완료되었습니다.");
			}else {
				result.put("result_code", "500");
				result.put("msg", "견적문의에 실패했습니다..");
			}
		} catch(Exception e) {
			result.put("result_code", "500");
			result.put("msg", "견적문의에 실패했습니다.");
		}

		System.out.println("===================견적추가하는 곳 종료==================");
		return new Gson().toJson(result);
	}
	
	
}
