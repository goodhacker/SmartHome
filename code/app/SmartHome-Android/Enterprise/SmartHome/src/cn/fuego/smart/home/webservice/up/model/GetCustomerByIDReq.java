package cn.fuego.smart.home.webservice.up.model;

import cn.fuego.misp.webservice.up.model.MispBaseReqJson;


/**
 * 
* @ClassName: GetUserMarkListReq 
* @Description: TODO
* @author Tang Jun
* @date 2014-10-20 上午10:59:10 
*
 */
public class GetCustomerByIDReq extends MispBaseReqJson
{

	private int userID;
	public int getUserID()
	{
		return userID;
	}

	public void setUserID(int userID)
	{
		this.userID = userID;
	}

	@Override
	public String toString()
	{
		return "GetCustomerByIDReq [userID=" + userID + ", token=" + token
				+ "]";
	}


	
 
}