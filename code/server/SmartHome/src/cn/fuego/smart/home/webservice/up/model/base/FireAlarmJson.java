package cn.fuego.smart.home.webservice.up.model.base;

import java.io.Serializable;




/** 
* @ClassName: FireAlarmJson 
* @Description:对应FireAlarmView
* @author Aether
* @date 2014-12-15 下午6:26:29 
*  
*/ 
public class FireAlarmJson implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

	private int id;  			//告警ID，自增长
	private long concentratorID;
	private String alarmTypeName; //告警类型名称
	private long alarmTime;	//告警产生的时间
	private Integer clearStatus;   //0未清除 1 手动清除 2自动清除 AlarmClearEnum

	private long clearTime;	//告警清除的时间
	//通过concenratorID关联concentrator表
	private Integer status;			//集中器状态，0 离线 1在线
	private String concentDesp;		//集中器描述
	
    //通过objID关联FireSensor表	
	private Integer machineID; //机号
	private Integer loopID;    //回路号
	private Integer codeID;    //编号
	private String locationDesp;  //位置描述
	private Float locationX;  //X 偏移，相对当前图片尺寸宽度
	private Float locationY;  //Y 偏移，相当当前图片尺寸高度
	
	private Integer planID;
	private String sensorTypeName;  //传感器类型名称
	private int alarmKind;//0-告警；1-设备状态；


	private String contacts; //联系人
	private String contactPhone; //联系电话
	
	public int getId()
	{
		return id;
	}


	public void setId(int id)
	{
		this.id = id;
	}


	public long getConcentratorID()
	{
		return concentratorID;
	}


	public void setConcentratorID(long concentratorID)
	{
		this.concentratorID = concentratorID;
	}


	public String getAlarmTypeName()
	{
		return alarmTypeName;
	}


	public void setAlarmTypeName(String alarmTypeName)
	{
		this.alarmTypeName = alarmTypeName;
	}


	public long getAlarmTime()
	{
		return alarmTime;
	}


	public void setAlarmTime(long alarmTime)
	{
		this.alarmTime = alarmTime;
	}


	public Integer getClearStatus()
	{
		return clearStatus;
	}


	public void setClearStatus(Integer clearStatus)
	{
		this.clearStatus = clearStatus;
	}


	public long getClearTime()
	{
		return clearTime;
	}


	public void setClearTime(long clearTime)
	{
		this.clearTime = clearTime;
	}


	public Integer getStatus()
	{
		return status;
	}


	public void setStatus(Integer status)
	{
		this.status = status;
	}


	public String getConcentDesp()
	{
		return concentDesp;
	}


	public void setConcentDesp(String concentDesp)
	{
		this.concentDesp = concentDesp;
	}


	public Integer getMachineID()
	{
		return machineID;
	}


	public void setMachineID(Integer machineID)
	{
		this.machineID = machineID;
	}


	public Integer getLoopID()
	{
		return loopID;
	}


	public void setLoopID(Integer loopID)
	{
		this.loopID = loopID;
	}


	public Integer getCodeID()
	{
		return codeID;
	}


	public void setCodeID(Integer codeID)
	{
		this.codeID = codeID;
	}


	public String getLocationDesp()
	{
		return locationDesp;
	}


	public void setLocationDesp(String locationDesp)
	{
		this.locationDesp = locationDesp;
	}


	public Float getLocationX()
	{
		return locationX;
	}


	public void setLocationX(Float locationX)
	{
		this.locationX = locationX;
	}


	public Float getLocationY()
	{
		return locationY;
	}


	public void setLocationY(Float locationY)
	{
		this.locationY = locationY;
	}


	public Integer getPlanID()
	{
		return planID;
	}


	public void setPlanID(Integer planID)
	{
		this.planID = planID;
	}


	public String getSensorTypeName()
	{
		return sensorTypeName;
	}


	public void setSensorTypeName(String sensorTypeName)
	{
		this.sensorTypeName = sensorTypeName;
	}


	public int getAlarmKind()
	{
		return alarmKind;
	}


	public void setAlarmKind(int alarmKind)
	{
		this.alarmKind = alarmKind;
	}


	public String getContacts()
	{
		return contacts;
	}


	public void setContacts(String contacts)
	{
		this.contacts = contacts;
	}


	public String getContactPhone()
	{
		return contactPhone;
	}


	public void setContactPhone(String contactPhone)
	{
		this.contactPhone = contactPhone;
	}


	@Override
	public String toString()
	{
		return "FireAlarmJson [id=" + id + ", concentratorID=" + concentratorID
				+ ", alarmTypeName=" + alarmTypeName + ", alarmTime="
				+ alarmTime + ", clearStatus=" + clearStatus + ", clearTime="
				+ clearTime + ", status=" + status + ", concentDesp="
				+ concentDesp + ", machineID=" + machineID + ", loopID="
				+ loopID + ", codeID=" + codeID + ", locationDesp="
				+ locationDesp + ", locationX=" + locationX + ", locationY="
				+ locationY + ", planID=" + planID + ", sensorTypeName="
				+ sensorTypeName + ", alarmKind=" + alarmKind + ", contacts="
				+ contacts + ", contactPhone=" + contactPhone + "]";
	}




 
}
