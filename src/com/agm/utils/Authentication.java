package com.agm.utils;

public class Authentication {
	
	private static final String USERNAME = "admin";
	private static final String PWD = "test";
	
	public static boolean hasValidCredentials(String username, String password) {
        if (username.equals(USERNAME) && password.equals(PWD)) {
            return true;
        }
        return false;
    }
}
