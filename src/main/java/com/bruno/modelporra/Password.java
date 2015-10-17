package com.bruno.modelporra;


public class Password {
	
	private String previousPassword;
	private String inputPassword;
	
	public Password(){}
	
	public Password(String previousPassword, String inputPassword){
		this.previousPassword = previousPassword;
		this.inputPassword = inputPassword;
	}

	public String getPreviousPassword() {
		return previousPassword;
	}

	public String getInputPassword() {
		return inputPassword;
	}
	
}
