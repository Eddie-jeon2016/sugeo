/** ===============================================
 * Copyright(c) 4DEPTH 2017
 * @fileName AuthManageVO.java
 * @author Chang-il Jeon
 * @since 2017. 3. 7.
 * @version 1.0
 * 
 *=================================================
 */
package egovframework.com.cmm;

public class AuthManageVO {
	
	
	
	
	private String roleCode = "";
	
	private String roleName = "";
	
	private String useYn = "";

	private String regDate = "";
	
	private int regId = 0;

	/**
	 * @return the roleCode
	 */
	public String getRoleCode() {
		return roleCode;
	}

	/**
	 * @param roleCode the roleCode to set
	 */
	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}

	/**
	 * @return the roleName
	 */
	public String getRoleName() {
		return roleName;
	}

	/**
	 * @param roleName the roleName to set
	 */
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	/**
	 * @return the useyn
	 */
	public String getUseYn() {
		return useYn;
	}

	/**
	 * @param useyn the useyn to set
	 */
	public void setUseYn(String useYn) {
		this.useYn = useYn;
	}

	/**
	 * @return the regDate
	 */
	public String getRegDate() {
		return regDate;
	}

	/**
	 * @param regDate the regDate to set
	 */
	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}

	/**
	 * @return the regId
	 */
	public int getRegId() {
		return regId;
	}

	/**
	 * @param regId the regId to set
	 */
	public void setRegId(int regId) {
		this.regId = regId;
	}
	
	
	
	

}

