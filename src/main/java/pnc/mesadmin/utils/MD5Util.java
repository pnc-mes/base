package pnc.mesadmin.utils;

import org.apache.shiro.crypto.hash.Md5Hash;

public class MD5Util {

	public static String md5(String password){
		return new Md5Hash(password, "pnc", 1).toString();
	}

	public static String md5(String password, String salt){
		return new Md5Hash(password, salt, 1).toString();
	}

	public static void main(String[] args){
		String password = md5("1", "pnc");

		System.out.println(password);
	}
}
