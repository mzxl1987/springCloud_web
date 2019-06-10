package com.miicrown.util;

public class CrcUtil {
	
	public static boolean check(byte[] data, int destCrc) {
		
		if(null == data || data.length == 0) return false;
		
		/** CRC16生成方法 --------------------------- **/
		int ax,lsb; 
		int i,j,len; 
		ax = 0xFFFF; 
		for(i=0, len = data.length ;  i<len  ;i++) { 
			ax ^=  (0xFF & data[i]); 
			for(j=0  ;j<8  ;j++) { 
				lsb= ax & 0x0001; 
				ax = ax >> 1; 
				if(lsb!=0) 
				ax ^= 0xA001; 
			} 
		} 
		
		return ax == destCrc;	
	}
	
	public static int getCRC16(byte[] data){
		
		
		/** CRC16生成方法 --------------------------- **/
		int ax,lsb,i,j,len; 
		ax = 0xFFFF;
		
		if(null == data || data.length == 0) return ax;
		
		for(i=0, len = data.length ;  i<len  ;i++) { 
			ax ^=  (0xFF & data[i]); 
			for(j=0  ;j<8  ;j++) { 
				lsb= ax & 0x0001; 
				ax = ax >> 1; 
				if(lsb!=0) 
				ax ^= 0xA001; 
			} 
		}
		
		return ax;
	}
	
}
