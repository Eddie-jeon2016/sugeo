package four.front.faq.service;

import egovframework.com.cmm.ComDefaultVO;

public class FaqVO extends ComDefaultVO {
	
	private String faqId;
	
	private String faqType;
	
	private String quest;
	
	private String answer;
	
	private String useYn;
	
	private int sortNum;
	
	private String typeName;
	
	private String regDate = "";
	
	private String regId = "";
	
	private String modDate = "";
	
	private String modId = "";
	
	
	public String getfaqId() {
		return faqId;
	}

	public void setfaqId(String faqId) {
		this.faqId = faqId;
	}

	public String getFaqType() {
		return faqType;
	}

	public void setFaqType(String faqType) {
		this.faqType = faqType;
	}

	public String getQuest() {
		return quest;
	}

	public void setQuest(String quest) {
		this.quest = quest;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public String getUseYn() {
		return useYn;
	}

	public void setUseYn(String useYn) {
		this.useYn = useYn;
	}

	public int getSortNum() {
		return sortNum;
	}

	public void setSortNum(int sortNum) {
		this.sortNum = sortNum;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
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
	
	@Override
	public String toString() {
		return "FaqVO [faqId=" + faqId + ", faqType=" + faqType + ", quest="
				+ quest + ", answer=" + answer + ", useYn=" + useYn
				+ ", sortNum=" + sortNum + ", typeName=" + typeName
				+ ", regDate=" + regDate + ", regId=" + regId + ", modDate="
				+ modDate + ", modId=" + modId + "]";
	}
	
}
