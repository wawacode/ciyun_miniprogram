package com.centrin.ciyun.common.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

/**
 * <pre>
 * 系统所有des统一使用这个，不使用那个DesPlus
 * 
 * 
 * 采用des和base64的方式对一个字符串进行加密和解密
 * 1)  密钥是一个长度 16,由16进制字符组成的字符串,  如：1234567890ABCDEF  使用时,
 *  相临的两位理解为一个16进制数的明文,然后转换为实际使用的8位密钥 
 *  2)  待加密数据按照PKCS5规则进行补位（缺7位补7个0x07，缺6位则补6个0x06，
 *  以次类推，如果正好8位，也需要补8个0x08） 
 *  3)  实际加密模式选择DES-ECB
 *  4)  经过DES加密后的数据必须通过Base64编码转换为明文的字符串
 * </pre>
 * 
 * @author
 * 
 */
public class Des {
	private static final Log LOG = LogFactory.getLog(Des.class);
	private static final String DEFAULT_KEY = "6A32663072317432";
	private Key key;

	public Des(String str) {
		setKey(str);// 生成密匙
	}

	public Des() {
		setKey(DEFAULT_KEY);
	}

	/**
	 * 将一个16进制的字符串转成byte[]数组
	 * 
	 * @Title: hexStringToByte
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param hex
	 * @return
	 * @author
	 * @date Jan 20, 2012
	 */
	private static byte[] hexStr2ByteArr(String hex) {
		int len = (hex.length() / 2);
		byte[] result = new byte[len];
		char[] achar = hex.toCharArray();
		for (int i = 0; i < len; i++) {
			int pos = i * 2;
			result[i] = (byte) (toByte(achar[pos]) << 4 | toByte(achar[pos + 1]));
		}
		return result;
	}

	/**
	 * 将byte数组转换为表示16进制值的字符串， 如：byte[]{8,18}转换为：0813， 和public static byte[]
	 * hexStr2ByteArr(String strIn) 互为可逆的转换过程
	 * 
	 * @param arrB
	 *            需要转换的byte数组
	 * @return 转换后的字符串
	 * @throws Exception
	 *             本方法不处理任何异常，所有异常全部抛出
	 */
	public static String byteArr2HexStr(byte[] arrB) {
		int iLen = arrB.length;
		// 每个byte用两个字符才能表示，所以字符串的长度是数组长度的两倍
		StringBuffer sb = new StringBuffer(iLen * 2);
		for (int i = 0; i < iLen; i++) {
			int intTmp = arrB[i];
			// 把负数转换为正数
			while (intTmp < 0) {
				intTmp = intTmp + 256;
			}
			// 小于0F的数需要在前面补0
			if (intTmp < 16) {
				sb.append("0");
			}
			sb.append(Integer.toString(intTmp, 16));
		}
		return sb.toString();
	}

	private static byte toByte(char c) {
		byte b = (byte) "0123456789ABCDEF".indexOf(c);
		return b;
	}

	/**
	 * 根据参数生成des的KEY
	 * 
	 * @Title: setKey
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param strKey
	 * @author
	 * @date Jan 20, 2012
	 */
	private void setKey(String strKey) {
		DESKeySpec desKeySpec;
		try {
			byte[] s = hexStr2ByteArr(strKey.toUpperCase());
			// System.out.println(s.length);
			// for (int i = 0; i < s.length; i++) {
			// System.out.print("s["+i+"]="+s[i]+"=>");
			// }
			desKeySpec = new DESKeySpec(s);
			SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
			key = keyFactory.generateSecret(desKeySpec);
		} catch (InvalidKeyException e1) {
			// TODO Auto-generated catch block
			// e1.printStackTrace();
			LOG.error("InvalidKeyException", e1);
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			LOG.error("NoSuchAlgorithmException", e);
		} catch (InvalidKeySpecException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			LOG.error("InvalidKeySpecException", e);
		}
	}

	/**
	 * 加密String明文输入,String密文输出，都是base64后的数据
	 * 
	 * @Title: getEncString
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param strMing
	 * @return
	 * @author
	 * @date Jan 20, 2012
	 */
	public String getEncString(String strMing) {
		byte[] byteMi = null;
		byte[] byteMing = null;
		String strMi = "";
		BASE64Encoder base64en = new BASE64Encoder();
		try {
			byteMing = strMing.getBytes("UTF8");
			byteMi = this.getEncCode(byteMing);
			strMi = base64en.encode(byteMi);
			strMi = strMi.replace("\r", "");
			strMi = strMi.replace("\n", "");
		} catch (Exception e) {
			LOG.error("加密异常", e);
//			throw new RuntimeException("Error initializing SqlMap class. Cause: " + e);
			return strMing;
		} finally {
			base64en = null;
			byteMing = null;
			byteMi = null;
		}
		return strMi;
	}

	/**
	 * 加密String密文输入,String明文输出，都是base64后的数据
	 * 
	 * @Title: getDesString
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param strMi
	 * @return
	 * @author
	 * @date Jan 20, 2012
	 */
	public String getDesString(String strMi) {

		BASE64Decoder base64De = new BASE64Decoder();
		byte[] byteMing = null;
		byte[] byteMi = null;
		String strMing = "";
		try {
			// System.out.println("--------------------------");
			// System.out.println(strMi);
			// System.out.println("--------------------------");
			byteMi = base64De.decodeBuffer(strMi.replace(" ", ""));
			// String tmpStr = new String(byteMi).replace(" ", "");
			// System.out.println(tmpStr);
			// byteMi = Tool.hexStringToByte(tmpStr);
			// System.out.println("--------------------------");
			byteMing = this.getDesCode(byteMi);
			// System.out.println(Tool.bytesToHexString(byteMing));
			// System.out.println("----------strMing start----------------");
			// strMing = new String(byteMing);
			strMing = new String(byteMing, "UTF8");
			// System.out.println(strMing);
			// System.out.println("----------strMing end----------------");
		} catch (Exception e) {
			LOG.error("解密异常", e);
//			throw new RuntimeException("Error initializing SqlMap class. Cause: " + e);
			return strMi;
		} finally {
			base64De = null;
			byteMing = null;
			byteMi = null;
		}
		return strMing;
	}

	/**
	 * 加密以byte[]明文输入,byte[]密文输出
	 * 
	 * @param byteS
	 * @return
	 */
	private byte[] getEncCode(byte[] byteS) {
		byte[] byteFina = null;
		Cipher cipher;
		try {
			cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
			cipher.init(Cipher.ENCRYPT_MODE, key);
			byteFina = cipher.doFinal(byteS);
		} catch (Exception e) {
			LOG.error("getEncCode异常",e);
//			throw new RuntimeException("Error initializing SqlMap class. Cause: " + e);
		} finally {
			cipher = null;
		}
		return byteFina;
	}

	/**
	 * 解密以byte[]密文输入,以byte[]明文输出
	 * 
	 * @param byteD
	 * @return
	 */
	private byte[] getDesCode(byte[] byteD) {
		Cipher cipher;
		byte[] byteFina = null;
		try {

			// DESKeySpec desKeySpec = new
			// DESKeySpec(Tool.hexStringToByte("0102030405060708"));
			// SecretKeyFactory keyFactory =
			// SecretKeyFactory.getInstance("DES");
			// key = keyFactory.generateSecret(desKeySpec);
			// IvParameterSpec iv = new
			// IvParameterSpec("12345678".getBytes("UTF-8"));

			cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
			// cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
			// cipher = Cipher.getInstance("DES/CBC/NoPadding");
			cipher.init(Cipher.DECRYPT_MODE, key);
			byteFina = cipher.doFinal(byteD);
		} catch (Exception e) {
			e.printStackTrace();
			// throw new RuntimeException(
			// "Error initializing SqlMap class. Cause: " + e);
		} finally {
			cipher = null;
		}
		return byteFina;
	}

	public static void main(String args[]) {
		Des des = new Des();  //使用默认秘钥

		String mingwen = "zhangsan"; // 明文
		// String miwen = "hiehsc7NExaFB+ybr2vSRw==";

		// DES加密
		String str3 = des.getEncString(mingwen);
		System.out.println("密文:" + str3);

		// 解密
		String deStr = des.getDesString(str3);
		System.out.println("明文:" + deStr);
	}
}
