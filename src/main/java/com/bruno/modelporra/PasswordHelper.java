package com.bruno.modelporra;


import java.math.BigInteger;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class PasswordHelper {
	
	public static void verifyPasswordInsert(String oldPassword, PasswordInterf clazz) {
		
		String newPassword = clazz.getPassword();
		String newPasswdEncrypt = encrypt(newPassword);
		
        if(newPassword.equals(oldPassword) || newPasswdEncrypt.equals(oldPassword))
            clazz.setPassword(oldPassword);
        else
            clazz.setPasswordWitEncrypt(newPassword);
    }
	
	public static boolean verifyIfSamePassword(Password password){
		
		String previousPassword = password.getPreviousPassword();
		String inputPassword = password.getInputPassword().trim();
		String encryptPassword = encrypt(inputPassword);
		
		if(encryptPassword.equals(previousPassword) || inputPassword.equals(previousPassword))
			return true;
		else
			return false;
		
	}
	
	public static String encrypt(String password){
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(password.getBytes(), 0, password.length());
            return new BigInteger(1, md.digest()).toString(16);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }
	
}
