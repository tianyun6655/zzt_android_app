package com.example.tianyunchen.zaozaotuandroid.untils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by tianyunchen on 2/28/17.
 */

public class StringToMD5 {
    public static String md5(String string) {

        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
            byte[] bytes = md5.digest(string.getBytes());
            String result = "";
            for (byte b : bytes) {
                String temp = Integer.toHexString(b & 0xff);
                if (temp.length() == 1) {
                    temp = "0" + temp;
                }
                result += temp;
            }
            return result;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }
}
