package may.lyc.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.codec.binary.Hex;

/**
 * 工具类
 * 
 * @author MayRock
 */
public class MyUtils {
	public static void main(String[] args) throws NoSuchAlgorithmException {
		System.out.println(getRomPwd("aa", "123456"));
		System.out.println(getPwd(getRomPwd("aa", "123456"), "aa"));
		System.out.println(jdkMD5("asdfjghaswd7321yiho1244efr32w")+" + "+jdkMD5("1231231").length());
	}

	
	/**
	 * md5加密
	 * @param src
	 * @throws NoSuchAlgorithmException
	 */
	public static String jdkMD5(String src) throws NoSuchAlgorithmException {
		MessageDigest md = MessageDigest.getInstance("MD5");
		byte[] md5Bytes = md.digest(src.getBytes());
		return Hex.encodeHexString(md5Bytes);// 利用第三方包将byte数组转化为16进制字符串
	}

	/**
	 * 加密：加密方式:随机长度&内容字符串+原密码+随机长度&内容字符串
	 * 随机字符串长度：帐号首位字符变小写后的ASCII码值与122的差值的个位数与账号长度相乘 若不在[25-32]范围内则变为25位
	 * 
	 * @param account
	 * @param password
	 */
	public static String getRomPwd(String account, String password) {
		// 根据帐号第一位产生随机字符串
		int nums = 122 - (int) account.toLowerCase().charAt(0);
		int acclength = account.length();
		int length = nums % 10 * acclength < 25 || nums % 10 * acclength > 32 ? 25 : nums % 10 * acclength;
		char[] chars1 = getChars(length);
		String chars1s = String.valueOf(chars1);
		char[] chars2 = getChars(length);
		String chars2s = String.valueOf(chars2);
		return chars1s + password + chars2s;

	}

	/**
	 * 产生随机的字符
	 */
	public static char getRandomChar(int i,int j) {
		int randNumber = (int) Math.floor(Math.random() * i + j);
		return (char) randNumber;
	}

	/**
	 * 产生指定长度的随机字符数组
	 * 
	 * @param 数组长度
	 */
	public static char[] getChars(int length) {
		char[] chars = new char[length];
		for (int i = 0; i < length; i++) {
			chars[i] = getRandomChar(66,57);
		}
		return chars;
	}

	/**
	 * 随机密码转换成原密码
	 * 
	 * @param ramPwd
	 * @param account
	 * @return
	 */
	public static String getPwd(String ramPwd, String account) {
		int nums = 122 - (int) account.toLowerCase().charAt(0);
		int acclength = account.length();
		int l = nums % 10 * acclength < 25 ? 25 : nums % 10 * acclength;
		int length = ramPwd.length();
		// 本体=(总长-随机*2)
		int LL = length - l * 2;
		// substring(随机,随机+本体)
		return ramPwd.substring((l), (l + LL));
	}
}
