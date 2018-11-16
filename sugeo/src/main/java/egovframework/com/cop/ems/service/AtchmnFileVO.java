package egovframework.com.cop.ems.service;

/**
 * 발송메일에 첨부되는 파일 VO 클래스
 * @author 공통서비스 개발팀 박지욱
 * @since 2009.03.12
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자          수정내용
 *  -------    --------    ---------------------------
 *  2009.03.12  박지욱          최초 생성
 *
 *  </pre>
 */
public class AtchmnFileVO {

	/** 첨부파일ID */
	private String atchFileId;
	/** 파일연번 */
	private String fileSn;
	/** 원파일명 */
	private String orignlfileName;
	/** 저장파일명 */
	private String strefileName;
	/** 파일저장경로 */
	private String fileStreCours;
	/** 파일확장자 */
	private String fileExtsn;
	/** 파일크기 */
	private int fileMg;

	/**
	 * atchFileId attribute 를 리턴한다.
	 * @return String
	 */
	public String getAtchFileId() {
		return atchFileId;
	}

	/**
	 * atchFileId attribute 값을 설정한다.
	 * @param atchFileId String
	 */
	public void setAtchFileId(String atchFileId) {
		this.atchFileId = atchFileId;
	}

	/**
	 * fileSn attribute 를 리턴한다.
	 * @return String
	 */
	public String getFileSn() {
		return fileSn;
	}

	/**
	 * fileSn attribute 값을 설정한다.
	 * @param fileSn String
	 */
	public void setFileSn(String fileSn) {
		this.fileSn = fileSn;
	}

	/**
	 * orignlfileName attribute 를 리턴한다.
	 * @return String
	 */
	public String getOrignlfileName() {
		return orignlfileName;
	}

	/**
	 * orignlfileName attribute 값을 설정한다.
	 * @param orignlfileName String
	 */
	public void setOrignlfileName(String orignlfileName) {
		this.orignlfileName = orignlfileName;
	}

	/**
	 * strefileName attribute 를 리턴한다.
	 * @return String
	 */
	public String getStrefileName() {
		return strefileName;
	}

	/**
	 * strefileName attribute 값을 설정한다.
	 * @param strefileName String
	 */
	public void setStrefileName(String strefileName) {
		this.strefileName = strefileName;
	}

	/**
	 * fileStreCours attribute 를 리턴한다.
	 * @return String
	 */
	public String getFileStreCours() {
		return fileStreCours;
	}

	/**
	 * fileStreCours attribute 값을 설정한다.
	 * @param fileStreCours String
	 */
	public void setFileStreCours(String fileStreCours) {
		this.fileStreCours = fileStreCours;
	}

	/**
	 * fileExtsn attribute 를 리턴한다.
	 * @return String
	 */
	public String getFileExtsn() {
		return fileExtsn;
	}

	/**
	 * fileExtsn attribute 값을 설정한다.
	 * @param fileExtsn String
	 */
	public void setFileExtsn(String fileExtsn) {
		this.fileExtsn = fileExtsn;
	}

	/**
	 * fileMg attribute 를 리턴한다.
	 * @return int
	 */
	public int getFileMg() {
		return fileMg;
	}

	/**
	 * fileMg attribute 값을 설정한다.
	 * @param fileMg int
	 */
	public void setFileMg(int fileMg) {
		this.fileMg = fileMg;
	}
}
