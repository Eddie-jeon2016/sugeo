package four.com.video.service;

public class VideoMngVO {
	/** 고유아이디*/
	private String videoId = "";
	/** 제목*/
	private String title = "";
	/** url*/
	private String url = "";
	/** 작성일*/
	private String regDate = "";
	/** 작성자*/
	private String regId = "";
	/** 수정일*/
	private String modDate = "";
	/** 수정인*/
	private String modId = "";
	/** 노출여부*/
	private String noticeYn = "";
	/** 사용여부*/
	private String useYn = "";
	public String getVideoId() {
		return videoId;
	}
	public void setVideoId(String videoId) {
		this.videoId = videoId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getRegDate() {
		return regDate;
	}
	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}
	public String getRegId() {
		return regId;
	}
	public void setRegId(String regId) {
		this.regId = regId;
	}
	public String getModDate() {
		return modDate;
	}
	public void setModDate(String modDate) {
		this.modDate = modDate;
	}
	public String getModId() {
		return modId;
	}
	public void setModId(String modId) {
		this.modId = modId;
	}
	public String getNoticeYn() {
		return noticeYn;
	}
	public void setNoticeYn(String noticeYn) {
		this.noticeYn = noticeYn;
	}
	public String getUseYn() {
		return useYn;
	}
	public void setUseYn(String useYn) {
		this.useYn = useYn;
	}
	@Override
	public String toString() {
		return "VideoMngVO [videoId=" + videoId + ", title=" + title + ", url="
				+ url + ", regDate=" + regDate + ", regId=" + regId
				+ ", modDate=" + modDate + ", modId=" + modId + ", noticeYn="
				+ noticeYn + ", useYn=" + useYn + "]";
	}
	
	
	
}
