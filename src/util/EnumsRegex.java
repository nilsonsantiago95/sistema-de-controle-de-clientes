package util;

public enum EnumsRegex {
	
	CPF("\\d{3}.\\d{3}.\\d{3}-\\d{2}"),
	EMAIL("^[\\w.-]+@[\\w.-]+.[a-zA-Z]{2,}$"),
	PHONENUMBER("\\d{2} 9\\d{4}-\\d{4}");
	
	private String regex;
	
	private EnumsRegex(String regexString) {
		this.regex = regexString;
	}

	public String getRegex() {
		return regex;
	}
}
