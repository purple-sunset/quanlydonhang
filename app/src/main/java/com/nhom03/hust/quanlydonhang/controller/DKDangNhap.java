package com.nhom03.hust.quanlydonhang.controller;


import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by Admin on 30/11/2016.
 */

public class DKDangNhap {

    public static String hashPassword(String s) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA1");
            md.update(s.getBytes());
            BigInteger i = new BigInteger(1, md.digest());
            return String.format("%1$032x", i);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }

}
