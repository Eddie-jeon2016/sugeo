package egovframework.com.cmm;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 *  클래스
 * @author 공통서비스개발팀 이삼섭
 * @since 2009.06.01
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일      수정자           수정내용
 *  -------       --------    ---------------------------
 *   2009.3.11   이삼섭          최초 생성
 *
 * </pre>
 */
@SuppressWarnings("serial")
public class ComDefaultCodeVO implements Serializable {
    /** 코드 ID */
    private String code = "";
    
    /** 상세코드 */
    private String subCode = "";
    
    /** 코드명 */
    private String codeName = "";
    
    /** 코드설명 */
    private String codeDesc = "";
    
    /** 상세 조건 여부 */
    private String haveDetailCondition = "N";
    
    /** 상세 조건 */
    private String detailCondition = "";

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
	 * @return the codeDesc
	 */
	public String getcodeDesc() {
		return codeDesc;
	}

	/**
	 * @param codeDesc the codeDesc to set
	 */
	public void setcodeDesc(String codeDesc) {
		this.codeDesc = codeDesc;
	}

	/**
	 * @return the haveDetailCondition
	 */
	public String getHaveDetailCondition() {
		return haveDetailCondition;
	}

	/**
	 * @param haveDetailCondition the haveDetailCondition to set
	 */
	public void setHaveDetailCondition(String haveDetailCondition) {
		this.haveDetailCondition = haveDetailCondition;
	}

	/**
	 * @return the detailCondition
	 */
	public String getDetailCondition() {
		return detailCondition;
	}

	/**
	 * @param detailCondition the detailCondition to set
	 */
	public void setDetailCondition(String detailCondition) {
		this.detailCondition = detailCondition;
	}
 
    
    
}
