package egovframework.com.cmm.service;

import java.io.Serializable;

/**
 * 공통상세코드 모델 클래스
 * @author 공통서비스 개발팀 이중호
 * @since 2009.04.01
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2009.04.01  이중호          최초 생성
 *
 * </pre>
 */
public class CmmnDetailCode implements Serializable {

	private static final long serialVersionUID = 1L;

	/*
	 * 코드ID
	 */
    private String code = "";

    /*
     * 코드ID명
     */
    private String codeName = "";

    /*
     * 코드
     */
	private String subCode = "";

	/*
	 * 코드명
	 */
    private String subCodeName = "";

    /*
     * 코드설명
     */
    private String subcodeDesc = "";

    /*
     * 사용여부
     */
    private String useYn = "";

    /*
     * 최초등록자ID
     */
    private String regId = "";

    /*
     * 최종수정자ID
     */
    private String modId   = "";

	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * @return the codeName
	 */
	public String getCodeName() {
		return codeName;
	}

	/**
	 * @param codeName the codeName to set
	 */
	public void setCodeName(String codeName) {
		this.codeName = codeName;
	}

	/**
	 * @return the subCode
	 */
	public String getSubCode() {
		return subCode;
	}

	/**
	 * @param subCode the subCode to set
	 */
	public void setSubCode(String subCode) {
		this.subCode = subCode;
	}

	/**
	 * @return the subCodeName
	 */
	public String getSubCodeName() {
		return subCodeName;
	}

	/**
	 * @param subCodeName the subCodeName to set
	 */
	public void setSubCodeName(String subCodeName) {
		this.subCodeName = subCodeName;
	}

	/**
	 * @return the subcodeDesc
	 */
	public String getSubcodeDesc() {
		return subcodeDesc;
	}

	/**
	 * @param subcodeDesc the subcodeDesc to set
	 */
	public void setSubcodeDesc(String subcodeDesc) {
		this.subcodeDesc = subcodeDesc;
	}

	/**
	 * @return the useYn
	 */
	public String getUseYn() {
		return useYn;
	}

	/**
	 * @param useYn the useYn to set
	 */
	public void setUseYn(String useYn) {
		this.useYn = useYn;
	}

	/**
	 * @return the regId
	 */
	public String getRegId() {
		return regId;
	}

	/**
	 * @param regId the regId to set
	 */
	public void setRegId(String regId) {
		this.regId = regId;
	}

	/**
	 * @return the modId
	 */
	public String getModId() {
		return modId;
	}

	/**
	 * @param modId the modId to set
	 */
	public void setModId(String modId) {
		this.modId = modId;
	}


}
