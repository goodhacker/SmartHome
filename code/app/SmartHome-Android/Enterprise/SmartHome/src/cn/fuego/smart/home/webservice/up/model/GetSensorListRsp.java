package cn.fuego.smart.home.webservice.up.model;

import java.util.ArrayList;
import java.util.List;

import cn.fuego.misp.webservice.up.model.MispBaseRspJson;
import cn.fuego.smart.home.webservice.up.model.base.HomeSensorJson;


/**
 * 
* @ClassName: GetSensorListRsp 
* @Description: TODO
* @author Tang Jun
* @date 2014-10-20 上午10:58:58 
*
 */
public class GetSensorListRsp extends MispBaseRspJson
{
	private List<HomeSensorJson> sensorList = new ArrayList<HomeSensorJson>();

	public List<HomeSensorJson> getSensorList()
	{
		return sensorList;
	}

	public void setSensorList(List<HomeSensorJson> sensorList)
	{
		this.sensorList = sensorList;
	}

	@Override
	public String toString()
	{
		return "GetSensorListRsp [sensorList=" + sensorList + ", errorCode="
				+ errorCode + ", errorMsg=" + errorMsg + ", obj=" + obj + "]";
	}

 



}
