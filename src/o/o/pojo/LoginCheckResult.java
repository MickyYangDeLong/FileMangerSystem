package o.o.pojo;

public class LoginCheckResult {
	private int checkResultCode;
	private String checkMessage;

	public void setCodeAndMessage(String message, int checkResultCode) {
		setCheckMessage(message);
		setCheckResultCode(checkResultCode);
	}

	public int getCheckResultCode() {
		return checkResultCode;
	}

	public void setCheckResultCode(int checkResultCode) {
		this.checkResultCode = checkResultCode;
	}

	public String getCheckMessage() {
		return checkMessage;
	}

	public void setCheckMessage(String checkMessage) {
		this.checkMessage = checkMessage;
	}

}
