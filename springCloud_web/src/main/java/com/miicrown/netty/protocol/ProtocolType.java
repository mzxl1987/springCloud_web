package com.miicrown.netty.protocol;

public enum ProtocolType {
	
	EQUIP_REPONSE(0x0000),                                //终端通用应答
	PC_REPONSE(0x8000),                                   //PC通用应答
	
	LOGIN(0x0001),                                        //登录
	LOGIN_REPONSE(0x8001),                                //登录回应
	
	HEARTBEAT(0x0002),                                    //心跳
	
	SET_PARAM(0x8201),                                    //设置参数
	
	SET_LATLNG_ONOFF_TIME(0x8200),                        //设置经纬度开关时间
	QUERY_LATLNG_ONOFF_TIME(0x8100),                      //查询经纬度开关时间
	QUERY_LATLNG_ONOFF_TIME_RESPONSE(0x0100),             //查询经纬度开关时间应答
	
	SET_TMP_ONOFF_TIME(0xB101),                           //设置临时开关时间
	QUERY_TMP_ONOFF_TIME(0xB501),                         //查询临时开关时间
	QUERY_TMP_ONOFF_TIME_RESPONSE(0x3501),                //查询临时开关时间应答
	
	SET_INNER_PARAMS(0xB103),                           //设置内部参数
	QUERY_INNER_PARAMS(0xB503),                         //查询内部参数
	QUERY_INNER_PARAMS_RESPONSE(0x3503),                //查询内部参数应答
	
	SET_AUTO_DIM_PARAMS(0xB104),                        //设置自动调光参数
	QUERY_AUTO_DIM_PARAMS(0xB504),                      //查询自动调光参数
	QUERY_AUTO_DIM_RESPONSE(0x3504),                    //查询自动调光参数应答
	
	DIM(0xB110),                                        //单灯遥控操作
	
	SET_DATETIME(0x8202),                               //设置单灯日历时钟
	QUERY_DATETIME(0x8102),                             //查询单灯日历时钟
	QUERY_DATETIME_RESPONSE(0x0102),                    //查询单灯日历时钟应答
	
	SET_CONTROL_MODE(0xB106),                            //设置控制模式
	
	QUERY_LAMP_STATUS(0xB508),                           //查询单灯状态
	QUERY_LAMP_STATUS_RESPONSE(0x3508),                  //查询单灯状态应答
	
	ALARM(0x3509),                                       //主动上传报警
	
	QUERY_LAMP_STATUS_RECORD(0xB510),                           //查询单灯状态记录
	QUERY_LAMP_STATUS_RECORD_RESPONSE(0x3510),                  //查询单灯状态记录应答
	
	SET_RSA(0x8203),                                     //设置单灯RSA公钥对
	QUERY_RSA(0x8103),                                   //查询单灯RSA公钥对
	QUERY_RSA_RESPONSE(0x0103),                          //查询单灯RSA公钥对应答
	
	UPGRADE(0x8301),                                     //升级程序
	UPGRADE_RESPONSE(0x0301),                            //升级程序
	
	;
	
	private int type;
	
	ProtocolType(int type){
		this.type = type;
	}
	
	public int getType(){ return type; }
	
	/**
	 * 根据value返回枚举类型,主要在switch中使用
	 * @param type
	 * @return
	 */
    public static ProtocolType get(int type) {
        for (ProtocolType code : values()) {
            if (code.getType() == type) {
                return code;
            }
        }
        return null;
    }

	
}
