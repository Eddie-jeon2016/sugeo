package four.mng.code.service;

import java.util.List;

public class CodeManageVO {
	/**
	 * 메인코드
	 */
	private String code = "";
	/**
	 * 메인코드 명
	 */
	private String codeName = "";
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
	/**
	 * 코드설명
	 */
	private String codeDesc = "";
	/**
	 * 서브코드
	 */
	private List<SubCodeManageVO> subCodes;
	/**
	 * 부모코드에 대한 서브코드 갯수
	 */
	private int countSubCode = 0;
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getCodeName() {
		return codeName;
	}

	public void setCodeName(String codeName) {
		this.codeName = codeName;
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

	public String getcodeDesc() {
		return codeDesc;
	}

	public void setcodeDesc(String codeDesc) {
		this.codeDesc = codeDesc;
	}

	public List<SubCodeManageVO> getSubCodes() {
		return subCodes;
	}

	public void setSubCodes(List<SubCodeManageVO> subCodes) {
		this.subCodes = subCodes;
	}
	

	public int getCountSubCode() {
		return countSubCode;
	}

	public void setCountSubCode(int countSubCode) {
		this.countSubCode = countSubCode;
	}

	@Override
	public String toString() {
		return "CodeManageVO [code=" + code + ", codeName=" + codeName
				+ ", useYn=" + useYn + ", regDate=" + regDate + ", regId="
				+ regId + ", modDate=" + modDate + ", modId=" + modId
				+ ", codeDesc=" + codeDesc + ", subCodes=" + subCodes
				+ ", countSubCode=" + countSubCode + "]";
	}

	
	
}
