package com.miicrown.util;

import java.io.ByteArrayOutputStream;

public class ByteUtil {

	final protected static char[] hexArray = "0123456789ABCDEF".toCharArray();
	final private static int ZERO_ASCII = 0x30;
	/**
	* 将String转成BCD码
	* 
	* @param s
	* @return
	*/
	public static byte[] stringToBCDBytes(String s) {

		if (s.length() % 2 != 0) {
			s = "0" + s;
		}
		
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		char[] cs = s.toCharArray();
		for (int i = 0; i < cs.length; i += 2) {
			int high = cs[i] - ZERO_ASCII;
			int low = cs[i + 1] - ZERO_ASCII;
			baos.write(high << 4 | low);
		}
		return baos.toByteArray();
	}
	/**
	* 将BCD码转成String
	* 
	* @param b
	* @return
	*/
	public static String bcdBytesToString(byte [] b){
		StringBuffer sb = new StringBuffer ();
		for (int i = 0;i < b.length;i++ )
		{
			sb.append (hexArray[(b [i] & 0xff) >> 4]);
			sb.append (hexArray[b [i] & 0x0f]);
		}
		return sb.toString () ;
	}
	
	/**
     * int到byte[] 由高位到低位
     * @param i 需要转换为byte数组的整行值。
     * @return byte数组
     */
    public static byte[] intToByteArrayLE(int i) {
        byte[] result = new byte[4];
        result[3] = (byte)((i >> 24) & 0xFF);
        result[2] = (byte)((i >> 16) & 0xFF);
        result[1] = (byte)((i >> 8) & 0xFF);
        result[0] = (byte)(i & 0xFF);
        return result;
    }
    
    /**
     * short到byte[] 由高位到低位
     * @param i 需要转换为byte数组的整行值。
     * @return byte数组
     */
    public static byte[] shortToByteArrayLE(int i) {
        byte[] result = new byte[2];
        result[1] = (byte)((i >> 8) & 0xFF);
        result[0] = (byte)(i & 0xFF);
        return result;
    }
    
    public static String print(byte[] data) {
        StringBuilder r = new StringBuilder(data.length * 2);
        for (byte b : data) {
            r.append(hexArray[(b >> 4) & 0xF]);
            r.append(hexArray[(b & 0xF)]);
            r.append(" ");
        }
        return r.toString();
    }
	
}
