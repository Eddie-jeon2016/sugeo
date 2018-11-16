package four.mng.pop.service;

import java.util.Date;

import egovframework.com.cmm.ComDefaultVO;

/**
 * 일반회원VO클래스로서 일반회원관리 비지니스로직 처리용 항목을 구성한다.
 * @author 공통서비스 개발팀 조재영
 * @since 2013.08.20
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *     수정일                      수정자                                     수정내용
 *  ----------    --------    ---------------------------
 *  2013.08.20      김강산                최초 생성
 *
 * </pre>
 */
/**
 * @author san
 *
 */
@SuppressWarnings("serial")
public class PopManageVO extends ComDefaultVO{

	/**
	 * 팝업ID
	 */
	private String popupId = "";
	/**
	 * 팝업명
	 */
	private String popupName = "";
	/**
	 * 팝업시작날짜
	 */
	private String startDate = "";
	/**
	 * 팝업종료일자
	 */
	private String endDate = "";
	/**
	 * 팝업내용
	 */
	private String content = "";
	/**
	 * 사용여부
	 */
	private String useYn = "";
	/**
	 * 웹주소
	 */
	private String url = "";
	/**
	 * 파일 아이디
	 */
	private String fileId = "";
	/**
	 * 닫기유형
	 */
	private String closeType = "";
	
	private String fileName;
	
	private String startHour;
	
	private String endHour;
	
	public String getPopupId() {
		return popupId;
	}
	public void setPopupId(String popupId) {
		this.popupId = popupId;
	}
	public String getPopupName() {
		return popupName;
	}
	public void setPopupName(String popupName) {
		this.popupName = popupName;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getUseYn() {
		return useYn;
	}
	public void setUseYn(String useYn) {
		this.useYn = useYn;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getFileId() {
		return fileId;
	}
	public void setFileId(String fileId) {
		this.fileId = fileId;
	}
	public String getCloseType() {
		return closeType;
	}
	public void setCloseType(String closeType) {
		this.closeType = closeType;
	}
	
	
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	
	public String getStartHour() {
		return startHour;
	}
	public void setStartHour(String startHour) {
		this.startHour = startHour;
	}
	public String getEndHour() {
		return endHour;
	}
	public void setEndHour(String endHour) {
		this.endHour = endHour;
	}
	@Override
	public String toString() {
		return "PopManageVO [popupId=" + popupId + ", popupName=" + popupName + ",\n startDate=" + startDate
				+ ", endDate=" + endDate + ", content=" + content + ", useYn=" + useYn + ", url=" + url + ",\n fileId="
				+ fileId + ", closeType=" + closeType + "]";
	}
	
	
	
	
	
	
}