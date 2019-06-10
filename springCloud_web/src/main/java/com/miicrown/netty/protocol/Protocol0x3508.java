package com.miicrown.netty.protocol;

import com.miicrown.netty.service.SqlService;
import com.miicrown.util.ByteUtil;

import io.netty.buffer.ByteBuf;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper=false)
@ToString
public class Protocol0x3508 extends Protocol {
	
	private int responseNumber;  //应答流水
	private int controlMode;     //控制模式
	private long alarm;           //报警数据
	private String datetime;     //设备时间
	private long totalRunTime;   //累计运行时间
	private long runTime;        //本次开灯运行时间
	private int leakageVolage;   //漏电电压
	private int leakageCurrent;  //漏电电流
	private int waterFlag;       //水浸标志
	private int pkgCount;  //应答数量,代表灯个数
	private Pkg[] pkgs;          //数据包
	
	@Data
	@ToString
	class Pkg{
		private int index;
		private int dim;
		private int voltage;
		private int current;
		private int power;
		private int powerFactor;
	}
	
	public Protocol0x3508(){
		super(ProtocolType.QUERY_LAMP_STATUS_RESPONSE);
	}
	
	@Override
	public boolean decode(ByteBuf bb, int len) {
		
		if(!decodeHead(bb)) return false;
		
		//解析数据
		//应答流水号
		this.setResponseSerialNumber(bb.readUnsignedShortLE()); 
		
		//控制模式
		this.setControlMode(bb.readByte());
		
		//报警标志
		this.setAlarm(bb.readUnsignedIntLE());
		
		//设备时间
		byte[] tmp_dateTime = new byte[6];
		bb.readBytes(tmp_dateTime);
		this.setDatetime(ByteUtil.bcdBytesToString(tmp_dateTime));
		
		//累计运行时间
		this.setTotalRunTime(bb.readUnsignedIntLE());
		
		//本次开机运行时间
		this.setRunTime(bb.readUnsignedShortLE());
		
		//漏电电压
		this.setLeakageVolage(bb.readByte());
		
		//漏电电流
		this.setLeakageCurrent(bb.readUnsignedShortLE());
		
		//水浸
		this.setWaterFlag(bb.readUnsignedShortLE());
		
		//数据包数量
		this.setPkgCount(bb.readByte());
		
		final int MAX = this.getPkgCount();
		int i = 0;
		this.pkgs = new Pkg[MAX];
		
		while(i < MAX){
			
			pkgs[i] = new Pkg();
			pkgs[i].setIndex(bb.readByte());
			pkgs[i].setDim(bb.readByte());
			pkgs[i].setVoltage(bb.readByte());
			pkgs[i].setCurrent(bb.readUnsignedShortLE());
			pkgs[i].setPower(bb.readUnsignedShortLE());
			pkgs[i].setPowerFactor(bb.readUnsignedShortLE());
			
			i++;
		}
		
		return true;
	}

	@Override
	byte[] createHeadAndContent() {
		return null;
	}

	@Override
	public String toInsertSQL() {
		String sql = "INSERT INTO l_%s(id,"
				+ "voltage1,current1,dim1,power1,power_Factor1,"
				+ "voltage2,current2,dim2,power2,power_Factor2,"
				+ "voltage3,current3,dim3,power3,power_Factor3,"
				+ "voltage4,current4,dim4,power4,power_Factor4,"
				+ "control_Mode,alarm,leakage_Voltage,leakage_Current,water_Log,"
				+ "total_Running_Time,current_Running_Time,zhaoce_Date"
				+ ") "
				+ "VALUES ('%s',"
				+ "'%s','%s','%s','%s','%s',"
				+ "'%s','%s','%s','%s','%s',"
				+ "'%s','%s','%s','%s','%s',"
				+ "'%s','%s','%s','%s','%s',"
				+ "'%s','%s','%s','%s','%s',"
				+ "'%s','%s',now()"
				+ ")";
		int pkgCount = this.getPkgCount();
		
		return String.format(sql,
				SqlService.map_lamps.get(getEquipId()),
				System.nanoTime(),
				pkgCount >= 1 ? this.pkgs[0].voltage : 0,
				pkgCount >= 1 ? this.pkgs[0].current : 0,
				pkgCount >= 1 ? this.pkgs[0].dim : 0,
				pkgCount >= 1 ? this.pkgs[0].power: 0,
				pkgCount >= 1 ? this.pkgs[0].powerFactor: 0,
				
				pkgCount >= 2 ? this.pkgs[1].voltage : 0,
				pkgCount >= 2 ? this.pkgs[1].current : 0,
				pkgCount >= 2 ? this.pkgs[1].dim : 0,
				pkgCount >= 2 ? this.pkgs[1].power: 0,
				pkgCount >= 2 ? this.pkgs[1].powerFactor: 0,
						
				pkgCount >= 3 ? this.pkgs[2].voltage : 0,
				pkgCount >= 3 ? this.pkgs[2].current : 0,
				pkgCount >= 3 ? this.pkgs[2].dim : 0,
				pkgCount >= 3 ? this.pkgs[2].power: 0,
				pkgCount >= 3 ? this.pkgs[2].powerFactor: 0,
						
				pkgCount >= 4 ? this.pkgs[3].voltage : 0,
				pkgCount >= 4 ? this.pkgs[3].current : 0,
				pkgCount >= 4 ? this.pkgs[3].dim : 0,
				pkgCount >= 4 ? this.pkgs[3].power: 0,
				pkgCount >= 4 ? this.pkgs[3].powerFactor: 0,
						
				getControlMode(),
				getAlarm(),
				getLeakageVolage(),
				getLeakageCurrent(),
				getWaterFlag(),
				
				getTotalRunTime(),getRunTime()		
				
						
				);
	}

	@Override
	public String toUpdateSQL() {
		String sql = "update t_lamp set "
				+ "voltage1='%s',current1='%s',dim1='%s',power1='%s',power_Factor1='%s',"
				+ "voltage2='%s',current2='%s',dim2='%s',power2='%s',power_Factor2='%s',"
				+ "voltage3='%s',current3='%s',dim3='%s',power3='%s',power_Factor3='%s',"
				+ "voltage4='%s',current4='%s',dim4='%s',power4='%s',power_Factor4='%s',"
				+ "control_Mode='%s',alarm='%s',leakage_Voltage='%s',leakage_Current='%s',water_Log='%s',"
				+ "total_Running_Time='%s',current_Running_Time='%s',zhaoce_Date = now()"
				+ " where id = '%s' ";
		int pkgCount = this.getPkgCount();
		
		return String.format(sql,
				pkgCount >= 1 ? this.pkgs[0].voltage : 0,
				pkgCount >= 1 ? this.pkgs[0].current : 0,
				pkgCount >= 1 ? this.pkgs[0].dim : 0,
				pkgCount >= 1 ? this.pkgs[0].power: 0,
				pkgCount >= 1 ? this.pkgs[0].powerFactor: 0,
				
				pkgCount >= 2 ? this.pkgs[1].voltage : 0,
				pkgCount >= 2 ? this.pkgs[1].current : 0,
				pkgCount >= 2 ? this.pkgs[1].dim : 0,
				pkgCount >= 2 ? this.pkgs[1].power: 0,
				pkgCount >= 2 ? this.pkgs[1].powerFactor: 0,
						
				pkgCount >= 3 ? this.pkgs[2].voltage : 0,
				pkgCount >= 3 ? this.pkgs[2].current : 0,
				pkgCount >= 3 ? this.pkgs[2].dim : 0,
				pkgCount >= 3 ? this.pkgs[2].power: 0,
				pkgCount >= 3 ? this.pkgs[2].powerFactor: 0,
						
				pkgCount >= 4 ? this.pkgs[3].voltage : 0,
				pkgCount >= 4 ? this.pkgs[3].current : 0,
				pkgCount >= 4 ? this.pkgs[3].dim : 0,
				pkgCount >= 4 ? this.pkgs[3].power: 0,
				pkgCount >= 4 ? this.pkgs[3].powerFactor: 0,
						
				getControlMode(),
				getAlarm(),
				getLeakageVolage(),
				getLeakageCurrent(),
				getWaterFlag(),
				
				getTotalRunTime(),
				getRunTime(),		
				
				SqlService.map_lamps.get(getEquipId())
						
				);
	}

}
