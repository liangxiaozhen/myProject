package com.ptpl.test;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.codec.binary.Base64;
import org.mindrot.jbcrypt.BCrypt;

public class BCryptTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// Hash a password for the first time
		String password = "123123";//"testpassword";
		String passwords=null;
		MessageDigest md;
		Base64 base64 = new Base64();
		String hashed = BCrypt.hashpw(password, BCrypt.gensalt());
		System.out.println(hashed);
		try {
			md =  MessageDigest.getInstance("SHA");
			try {
				passwords = new String(base64.encode(md.digest(hashed.getBytes("utf-8"))));
				System.out.println(passwords);
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// gensalt's log_rounds parameter determines the complexity
		// the work factor is 2**log_rounds, and the default is 10
		String hashed2 = BCrypt.hashpw(password, BCrypt.gensalt(12));
 
		
		// Check that an unencrypted password matches one that has
		// previously been hashed
		String candidate = "123123";//"testpassword";
		//String candidate = "wrongtestpassword";
		if (BCrypt.checkpw(candidate, hashed))
			System.out.println("It matches");
		else
			System.out.println("It does not match");
		
		
	}

}
