package UTIL;

import java.util.ArrayList;

public class BusinessRuleException extends Exception {
	public BusinessRuleException() {
		super();
	}
	
	public BusinessRuleException(ArrayList<String> message) {
		super(message.toString());
	}
	public BusinessRuleException(String message) {
		super(message);
	}
}