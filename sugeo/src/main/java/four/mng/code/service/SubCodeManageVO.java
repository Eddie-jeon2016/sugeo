package four.mng.code.service;

public class SubCodeManageVO {
	/**
	 * 메인코드
	 */
	private String code = "";
	/**
	 * 서브코드
	 */
	private String subCode = "";
	/**
	 * 정렬순서
	 */
	private int sortNum = 0;
	/**
	 * 서브코드 이름
	 */
	private String subCodeName = "";
	/**
	 * 서브코드 설명
	 */
	private String subcodeDesc = "";
	/**
	 * 옵션키
	 */
	private String optionKey = "";
	/**
	 * 사용여부
	 */
	private String useYn = "";
	/**
	 * 등록일
	 */
	private String regDate = "";
	/**
	 * 등록인
	 */
	private int regId = 0;
	/**
	 * 수정일
	 */
	private String modDate = "";
	/**
	 * 수정인
	 */
	private int modId = 0;
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getSubCode() {
		return subCode;
	}
	public void setSubCode(String subCode) {
		this.subCode = subCode;
	}
	public int getSortNum() {
		return sortNum;
	}
	public void setSortNum(int sortNum) {
		this.sortNum = sortNum;
	}
	public String getSubCodeName() {
		return subCodeName;
	}
	public void setSubCodeName(String subCodeName) {
		this.subCodeName = subCodeName;
	}
	public String getSubcodeDesc() {
		return subcodeDesc;
	}
	public void setSubcodeDesc(String subcodeDesc) {
		this.subcodeDesc = subcodeDesc;
	}
	public String getOptionKey() {
		return optionKey;
	}
	public void setOptionKey(String optionKey) {
		this.optionKey = optionKey;
	}
	public String getUseYn() {
		return useYn;
	}
	public void setUseYn(String useYn) {
		this.useYn = useYn;
	}
	public String getRegDate() {
		return regDate;
	}
	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}
	public int getRegId() {
		return regId;
	}
	public void setRegId(int regId) {
		this.regId = regId;
	}
	public String getModDate() {
		return modDate;
	}
	public void setModDate(String modDate) {
		this.modDate = modDate;
	}
	public int getModId() {
		return modId;
	}
	public void setModId(int modId) {
		this.modId = modId;
	}
	
	@Override
	public String toString() {
		return "SubCodeManageVO [code=" + code + ", subCode=" + subCode
				+ ", sortNum=" + sortNum + ", subCodeName=" + subCodeName
				+ ", subcodeDesc=" + subcodeDesc + ", optionKey=" + optionKey
				+ ", useYn=" + useYn + ", regDate=" + regDate + ", regId="
				+ regId + ", modDate=" + modDate + ", modId=" + modId + "]";
	}	
}
