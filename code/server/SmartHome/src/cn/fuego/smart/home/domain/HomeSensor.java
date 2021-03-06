package cn.fuego.smart.home.domain;

import java.util.ArrayList;
import java.util.List;

import cn.fuego.common.domain.PersistenceObject;
import cn.fuego.common.util.validate.ValidatorUtil;
import cn.fuego.smart.home.constant.DeviceKindEunm;
import cn.fuego.smart.home.constant.SensorStatusEnum;


/**
 * 
* @ClassName: Sensor 
* @Description: TODO
* @author Tang Jun
* @date 2014-10-20 上午11:01:13 
*
 */
public class HomeSensor implements PersistenceObject
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final String PRI_KEY = "id";
	private long id;              //唯一ID 终端设备的出场ID，具有唯一性
	private long concentratorID;  //集中器ID
	private long sensorID;         //终端ID，在集中中，每个终端设备，每个通道有一个唯一ID
 	private int channelID;       //终端设备的通道ID
	private Integer sensorKind=DeviceKindEunm.DISCRETE_SENSOR.getIntValue();      //传感器种类 0 告警类，1模拟类，2控制类型， SensorKindEunm
	private Integer sensorType=0;      //传感器类型
	private String sensorTypeName="未命名";  //传感器类型名称
	private Integer status=SensorStatusEnum.DISABLE.getIntValue();          //告警使能状态，0-禁止，1-使能，SensorStatusEnum
	private Float warnValue=(float) 0;    //预警值
	private Float errorValue=(float) 0;   //火警值
	private Integer groupID=0;         //区域ID（用于标识联动控制器）
	private String ctrGroupID;      //联动控制器ID ;隔开
	private String description;  //描述
	private String mark="未分组";         //自定义标签
	
	private Long ctrSensorID = 0xfffffffL;//联动控制终端ID
	private Integer ctrChannelID = 0xffff;//联动控制终端通道ID

 
	public Long getCtrSensorID()
	{
		return ctrSensorID;
	}
	public void setCtrSensorID(Long ctrSensorID)
	{
		this.ctrSensorID = ctrSensorID;
	}

	public Integer getCtrChannelID()
	{
		return ctrChannelID;
	}
	public void setCtrChannelID(Integer ctrChannelID)
	{
		this.ctrChannelID = ctrChannelID;
	}
	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + channelID;
		result = prime * result
				+ (int) (concentratorID ^ (concentratorID >>> 32));
		result = prime * result + (int) (sensorID ^ (sensorID >>> 32));
		return result;
	}
	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		HomeSensor other = (HomeSensor) obj;
		if (channelID != other.channelID)
			return false;
		if (concentratorID != other.concentratorID)
			return false;
		if (sensorID != other.sensorID)
			return false;
		return true;
	}

 
	public long getId()
	{
		return id;
	}
	public void setId(long id)
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
	public List<Integer> getCtrGroupIDList()
	{
		List<Integer> groupIDList = new ArrayList<Integer>();
		if(!ValidatorUtil.isEmpty(ctrGroupID))
		{
			String[] strAry = ctrGroupID.split(";");
			for(int i=0;i<strAry.length;i++)
			{
				groupIDList.add(Integer.valueOf(strAry[i]));
			}
			
		}

		return groupIDList;
		
	}
	public void  setCtrGroupIDWithIDList(List<Integer> idList)
	{
  		String str = "";
		for(int i=0;i<idList.size();i++)
		{
			str += idList.get(i) + ";";
		}
		this.ctrGroupID = str;
		
 		
	}
 
	public long getSensorID()
	{
		return sensorID;
	}
	public void setSensorID(long sensorID)
	{
		this.sensorID = sensorID;
	}
	public int getChannelID()
	{
		return channelID;
	}
	public void setChannelID(int channelID)
	{
		this.channelID = channelID;
	}
	public Integer getSensorKind()
	{
		return sensorKind;
	}
	public void setSensorKind(Integer sensorKind)
	{
		this.sensorKind = sensorKind;
	}
	public Integer getSensorType()
	{
		return sensorType;
	}
	public void setSensorType(Integer sensorType)
	{
		this.sensorType = sensorType;
	}
	public String getSensorTypeName()
	{
		return sensorTypeName;
	}
	public void setSensorTypeName(String sensorTypeName)
	{
		this.sensorTypeName = sensorTypeName;
	}
	public Integer getStatus()
	{
		return status;
	}
	public void setStatus(Integer status)
	{
		this.status = status;
	}
	public Float getWarnValue()
	{
		return warnValue;
	}
	public void setWarnValue(Float warnValue)
	{
		this.warnValue = warnValue;
	}
	public Float getErrorValue()
	{
		return errorValue;
	}
	public void setErrorValue(Float errorValue)
	{
		this.errorValue = errorValue;
	}
	public Integer getGroupID()
	{
		return groupID;
	}
	public void setGroupID(Integer groupID)
	{
		this.groupID = groupID;
	}
	public String getCtrGroupID()
	{
		return ctrGroupID;
	}
	public void setCtrGroupID(String ctrGroupID)
	{
		this.ctrGroupID = ctrGroupID;
	}
	public String getDescription()
	{
		return description;
	}
	public void setDescription(String description)
	{
		this.description = description;
	}
	public String getMark()
	{
		return mark;
	}
	public void setMark(String mark)
	{
		this.mark = mark;
	}
 
 
}
