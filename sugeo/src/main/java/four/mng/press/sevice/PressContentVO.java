package four.mng.press.sevice;

public class PressContentVO {
	
	/** 고유아이디*/
	private String pcId = "";

	/** 보도자료 제목*/
	private String title = "";
	
	/** 언론사*/
	private String pressName = "";
	
	/** 보도자료 url*/
	private String url = "";
	
	/** 작성일*/
	private String regDate = "";
	
	/** 작성자*/
	private String regId = "";

	/** 수정일*/
	private String modDate = "";
	
	/** 수정인*/
	private String modId = "";
	
	/** 사용여부(삭제)*/
	private String useYn = "";

	public String getPcId() {
		return pcId;
	}

	public void setPcId(String pcId) {
		this.pcId = pcId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPressName() {
		return pressName;
	}

	public void setPressName(String pressName) {
		this.pressName = pressName;
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

	public String getUseYn() {
		return useYn;
	}

	public void setUseYn(String useYn) {
		this.useYn = useYn;
	}

	@Override
	public String toString() {
		return "PressContentVO [pcId=" + pcId + ", title=" + title
				+ ", pressName=" + pressName + ", url=" + url + ", regDate="
				+ regDate + ", regId=" + regId + ", modDate=" + modDate
				+ ", modId=" + modId + ", useYn=" + useYn + "]";
	}

	
	
	
}
