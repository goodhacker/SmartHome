package cn.fuego.smart.home.webservice.up.model;

import java.util.ArrayList;
import java.util.List;

import cn.fuego.misp.webservice.up.model.MispBaseRspJson;
import cn.fuego.smart.home.webservice.up.model.base.UserMarkJson;


/**
 * 
* @ClassName: GetUserMarkListRsp 
* @Description: TODO
* @author Tang Jun
* @date 2014-10-20 上午10:59:14 
*
 */
public class GetUserMarkListRsp extends MispBaseRspJson
{
	private List<UserMarkJson> markList = new ArrayList<UserMarkJson>();

	public List<UserMarkJson> getMarkList()
	{
		return markList;
	}

	public void setMarkList(List<UserMarkJson> markList)
	{
		this.markList = markList;
	}

	@Override
	public String toString()
	{
		return "GetUserMarkListRsp [markList=" + markList + "]";
	}
	
	

}
