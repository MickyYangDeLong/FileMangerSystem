package o.o.micky.check;

public class CheckUtil {
	/*
	 * check input string is null or empty or black
	 * 
	 * null == str || str.trim().isEmpty()
	 */
	public static boolean checkInputString(String inputString) {
		if (null == inputString || inputString.trim().isEmpty()) {
			return false;
		}
		return true;
	}
}