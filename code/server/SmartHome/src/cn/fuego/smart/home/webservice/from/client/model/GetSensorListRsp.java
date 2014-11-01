package cn.fuego.smart.home.webservice.from.client.model;

import java.util.List;

import cn.fuego.smart.home.webservice.from.client.model.base.BaseJsonRsp;
import cn.fuego.smart.home.webservice.from.client.model.base.HomeSensorJson;
import cn.fuego.smart.home.webservice.from.client.model.base.SetResultJson;


/**
 * 
* @ClassName: GetSensorListRsp 
* @Description: TODO
* @author Tang Jun
* @date 2014-10-20 上午10:58:58 
*
 */
public class GetSensorListRsp extends BaseJsonRsp
{
	private List<HomeSensorJson> sensorList;

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
		return "GetSensorListRsp [sensorList=" + sensorList + "]";
	}



}
