package four.com.order.service;

/**
 * @author 포뎁스2
 *
 */
public class OrderImgVO {
	
	private String imgNo;
	
	private String orderNo;
	
	private String imgNm;
	
	private String imgPath;
	
	private String regDttm;
	
	private String regMemNo;
	
	private String updateDttm;
	
	private String updateMemNo;
	
	private String delYn;

	public String getImgNo() {
		return imgNo;
	}

	public void setImgNo(String imgNo) {
		this.imgNo = imgNo;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getImgNm() {
		return imgNm;
	}

	public void setImgNm(String imgNm) {
		this.imgNm = imgNm;
	}

	public String getRegDttm() {
		return regDttm;
	}

	public void setRegDttm(String regDttm) {
		this.regDttm = regDttm;
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

	public String getImgPath() {
		return imgPath;
	}

	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}

	@Override
	public String toString() {
		return "OrderImgVO [imgNo=" + imgNo + ", orderNo=" + orderNo + ", imgNm=" + imgNm + ", imgPath=" + imgPath
				+ ",\n regDttm=" + regDttm + ", regMemNo=" + regMemNo + ", updateDttm=" + updateDttm + ", updateMemNo="
				+ updateMemNo + ", delYn=" + delYn + "]";
	}

	
	
}
