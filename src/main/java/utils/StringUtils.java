package utils;

public class StringUtils {
	public boolean isValidString(String str) {
		return str.matches("[a-zA-Z0-9]+");
	}
}
