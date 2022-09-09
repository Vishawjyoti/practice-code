package com.institute.eazy.util;

import javaxt.encryption.BCrypt;

public class Util {

    public static String hashPassword(String password_plaintext) {
        String salt = BCrypt.gensalt(12);
        String hashed_password = BCrypt.hashpw(password_plaintext, salt);
        return (hashed_password);
    }

    public static boolean checkPassword(String password_plaintext, String stored_hash) {
        boolean password_verified = false;
        if (null == stored_hash || !stored_hash.startsWith("$2a$"))
            throw new java.lang.IllegalArgumentException("Invalid hash provided for comparison");
        password_verified = BCrypt.checkpw(password_plaintext, stored_hash);
        return (password_verified);
    }
    public static String passwordEncrypt( String str) {
        int key = 3;
        StringBuilder encryptPassword = new StringBuilder();

        for (int i = 0; i < str.length(); i++) {
            char character = str.charAt(i);
            int ascii = (int) character;
            ascii = ascii + key;
            char ch1 = (char) ascii;
            encryptPassword.append(ch1);
        }
        return encryptPassword.toString();

    }

    public static String passwordDecrypt(String str){
        int key = 3;
        StringBuilder decryptPassword = new StringBuilder();

        for (int i = 0; i < str.length(); i++) {
            char character = str.charAt(i);
            int ascii = (int) character;
            ascii = ascii - key;
            char ch1 = (char) ascii;
            decryptPassword.append(ch1);
        }
        return decryptPassword.toString();

    }
}
