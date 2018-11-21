package four.com.item.service;

public class ItemVO {
	
	/** 품목 번호*/
	private String itemNo;
	
	/** 품목 유형*/
	private String itemTp;
	
	/** 품목명*/
	private String itemNm;
	
	/** 품목 아이콘*/
	private String itemIcon;
	
	/** 품목 간단설명*/
	private String itemSimpDesc;
	
	/** 품목 설명*/
	private String itemDesc;
	
	/** 단가*/
	private String price;
	
	/** 단위*/
	private String unit;
	
	/** 사용여부*/
	private String useYn;
	
	/** 등록일시*/
	private String regDttm;
	
	/** 등록 회원번호*/
	private String regMemNo;
	
	/** 수정일시*/
	private String updateDttm;
	
	/** 수정 회원번호*/
	private String updateMemNo;
	
	/** 삭제여부*/
	private String delYn;
	
	/** 상세설명을 \n으로 나누어 저장할 변수*/
	private String[] itemDescArr;	
	
	private String[] params;
	
	public String getItemNo() {
		return itemNo;
	}

	public void setItemNo(String itemNo) {
		this.itemNo = itemNo;
	}

	public String getItemTp() {
		return itemTp;
	}

	public void setItemTp(String itemTp) {
		this.itemTp = itemTp;
	}

	public String getItemNm() {
		return itemNm;
	}

	public void setItemNm(String itemNm) {
		this.itemNm = itemNm;
	}

	public String getItemIcon() {
		return itemIcon;
	}

	public void setItemIcon(String itemIcon) {
		this.itemIcon = itemIcon;
	}

	public String getItemSimpDesc() {
		return itemSimpDesc;
	}

	public void setItemSimpDesc(String itemSimpDesc) {
		this.itemSimpDesc = itemSimpDesc;
	}

	public String getItemDesc() {
		return itemDesc;
	}

	public void setItemDesc(String itemDesc) {
		this.itemDesc = itemDesc;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getUseYn() {
		return useYn;
	}

	public void setUseYn(String useYn) {
		this.useYn = useYn;
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

	public String[] getItemDescArr() {
		return itemDescArr;
	}

	public void setItemDescArr(String[] itemDescArr) {
		this.itemDescArr = itemDescArr;
	}
	
	public String[] getParams() {
		return params;
	}

	public void setParams(String[] params) {
		this.params = params;
	}

	@Override
	public String toString() {
		return "itemVO [itemNo=" + itemNo + ", itemTp=" + itemTp + ", itemNm=" + itemNm + ", itemIcon=" + itemIcon
				+ ",\n itemSimpDesc=" + itemSimpDesc + ", itemDesc=" + itemDesc + ", price=" + price + ", unit=" + unit
				+ ",\n useYn=" + useYn + ", regDttm=" + regDttm + ", regMemNo=" + regMemNo + ", updateDttm=" + updateDttm
				+ ",\n updateMemNo=" + updateMemNo + ", delYn=" + delYn + "]";
	}
	
	
	
	
	
	
	
}
