package accbook.dto;

public class AccountBook {

	private String userId;
	private String userPw;
	
	private String userCode;
	private int balance;
	
	private int inNo;
	private String inTitle;
	private int inAmount;
	private String inDate;
	
	private int outNo;
	private String outTitle;
	private int outAmount;
	private String outDate;
	
	public AccountBook() {}



	public AccountBook(int balance, String inTitle, int inAmount, String inDate) {
		
		this.balance = balance;
		this.inTitle = inTitle;
		this.inAmount = inAmount;
		this.inDate = inDate;
	}



	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserPw() {
		return userPw;
	}

	public void setUserPw(String userPw) {
		this.userPw = userPw;
	}

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}

	public int getInNo() {
		return inNo;
	}

	public void setInNo(int inNo) {
		this.inNo = inNo;
	}

	public String getInTitle() {
		return inTitle;
	}

	public void setInTitle(String inTitle) {
		this.inTitle = inTitle;
	}

	public int getInAmount() {
		return inAmount;
	}

	public void setInAmount(int inAmount) {
		this.inAmount = inAmount;
	}

	public String getInDate() {
		return inDate;
	}

	public void setInDate(String inDate) {
		this.inDate = inDate;
	}

	public int getOutNo() {
		return outNo;
	}

	public void setOutNo(int outNo) {
		this.outNo = outNo;
	}

	public String getOutTitle() {
		return outTitle;
	}

	public void setOutTitle(String outTitle) {
		this.outTitle = outTitle;
	}

	public int getOutAmount() {
		return outAmount;
	}

	public void setOutAmount(int outAmount) {
		this.outAmount = outAmount;
	}

	public String getOutDate() {
		return outDate;
	}

	public void setOutDate(String outDate) {
		this.outDate = outDate;
	}
	
	
}
