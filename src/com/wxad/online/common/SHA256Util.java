package com.wxad.online.common;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.codec.binary.Hex;

public class SHA256Util {
	
	MessageDigest digest;
	public String getHash(String str){
		String result="";
	    try {
	        digest = MessageDigest.getInstance("SHA-256");
	        byte[] hash = digest.digest(str.getBytes("UTF-8"));
	        result = Hex.encodeHexString(hash);
	    } catch (NoSuchAlgorithmException e) {
	        // TODO Auto-generated catch block
	        e.printStackTrace();
	    }catch (UnsupportedEncodingException e) {
	        // TODO Auto-generated catch block
	        e.printStackTrace();
	    }
	    return result;
	}
 
}
