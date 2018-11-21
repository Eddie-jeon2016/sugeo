package four.com.order.service;

public class OrderVO {
	
	/** 신청번호*/
	private String orderNo;

	/** 품목번호*/
	private String itemNo;
	
	/** 회원번호*/
	private String memNo;
	
	/** 회원명*/
	private String memNm;
	
	/** 핸드폰*/
	private String mobile;
	
	/** 주소*/
	private String addr;
	
	/** 방문일시*/
	private String visitDttm;
	
	/** 요청설명*/
	private String reqDesc;
	
	/** 예상수거량*/
	private String expRecycleQuty;
			   	   
	/** 예상포인트*/
	private String expPoint;
	
	/** 실제수거량*/
	private String realRecyleQuty;
	
	/** 실제포인트*/
	private String realRecylePoint;
	
	/** 업체번호*/
	private String cmpyNo;
	
	/** 업체명*/
	private String cmpyNm;
	
	/** 수거인*/
	private String pickupNm;
	
	/** 수거인 휴대폰*/
	private String pickupMobile;
	
	/** 영수증 이미지*/
	private String receiptImg;
	
	/** 등록일/
	private String regDttm;
	
	/** 등록회원번호*/
	private String regMemNo;
	
	/** 수정일시*/
	private String updateDttm;
	
	/** 수정회원번호*/
	private String updateMemNo;
	
	/** 삭제여부*/
	private String delYn;
	
	/** 진행상타*/
	private String prossStatus;

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getItemNo() {
		return itemNo;
	}

	public void setItemNo(String itemNo) {
		this.itemNo = itemNo;
	}

	public String getMemNo() {
		return memNo;
	}

	public void setMemNo(String memNo) {
		this.memNo = memNo;
	}

	public String getMemNm() {
		return memNm;
	}

	public void setMemNm(String memNm) {
		this.memNm = memNm;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public String getVisitDttm() {
		return visitDttm;
	}

	public void setVisitDttm(String visitDttm) {
		this.visitDttm = visitDttm;
	}

	public String getReqDesc() {
		return reqDesc;
	}

	public void setReqDesc(String reqDesc) {
		this.reqDesc = reqDesc;
	}

	public String getExpRecycleQuty() {
		return expRecycleQuty;
	}

	public void setExpRecycleQuty(String expRecycleQuty) {
		this.expRecycleQuty = expRecycleQuty;
	}


	public String getRealRecyleQuty() {
		return realRecyleQuty;
	}

	public void setRealRecyleQuty(String realRecyleQuty) {
		this.realRecyleQuty = realRecyleQuty;
	}


	public String getPickupNm() {
		return pickupNm;
	}

	public void setPickupNm(String pickupNm) {
		this.pickupNm = pickupNm;
	}

	public String getPickupMobile() {
		return pickupMobile;
	}

	public void setPickupMobile(String pickupMobile) {
		this.pickupMobile = pickupMobile;
	}

	public String getReceiptImg() {
		return receiptImg;
	}

	public void setReceiptImg(String receiptImg) {
		this.receiptImg = receiptImg;
	}

	public String getRegMemNo() {
		return regMemNo;
	}

	public void setRegMemNo(String regMemNo) {
		this.regMemNo = regMemNo;
	}

	public String getUpdateDttm() {
		return updateDttm;
	}

	public void setUpdateDttm(String updateDttm) {
		this.updateDttm = updateDttm;
	}

	public String getUpdateMemNo() {
		return updateMemNo;
	}

	public void setUpdateMemNo(String updateMemNo) {
		this.updateMemNo = updateMemNo;
	}

	public String getDelYn() {
		return delYn;
	}

	public void setDelYn(String delYn) {
		this.delYn = delYn;
	}

	public String getProssStatus() {
		return prossStatus;
	}

	public void setProssStatus(String prossStatus) {
		this.prossStatus = prossStatus;
	}

	public String getExpPoint() {
		return expPoint;
	}

	public void setExpPoint(String expPoint) {
		this.expPoint = expPoint;
	}

	public String getRealRecylePoint() {
		return realRecylePoint;
	}

	public void setRealRecylePoint(String realRecylePoint) {
		this.realRecylePoint = realRecylePoint;
	}
	

	public String getCmpyNo() {
		return cmpyNo;
	}

	public void setCmpyNo(String cmpyNo) {
		this.cmpyNo = cmpyNo;
	}

	public String getCmpyNm() {
		return cmpyNm;
	}

	public void setCmpyNm(String cmpyNm) {
		this.cmpyNm = cmpyNm;
	}

	@Override
	public String toString() {
		return "OrderVO [orderNo=" + orderNo + ", itemNo=" + itemNo + ", memNo=" + memNo + ", memNm=" + memNm
				+ ",\n mobile=" + mobile + ", addr=" + addr + ", visitDttm=" + visitDttm + ", reqDesc=" + reqDesc
				+ ",\n expRecycleQuty=" + expRecycleQuty + ", expPoint=" + expPoint + ", realRecyleQuty=" + realRecyleQuty
				+ ",\n realRecylePoint=" + realRecylePoint + ", cmpyNo=" + cmpyNo + ", cmpyNm=" + cmpyNm + ", pickupNm="
				+ pickupNm + ",\n pickupMobile=" + pickupMobile + ", receiptImg=" + receiptImg + ", regMemNo=" + regMemNo
				+ ",\n updateDttm=" + updateDttm + ", updateMemNo=" + updateMemNo + ", delYn=" + delYn + ", prossStatus="
				+ prossStatus + "]";
	}


	
}
