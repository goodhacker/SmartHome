package cn.fuego.smart.home.webservice.up.model;

import cn.fuego.misp.webservice.up.model.MispBaseReqJson;

public class DeleteOrderByIDReq extends MispBaseReqJson
{
	private int userID;
	private String orderID;
	public int getUserID()
	{
		return userID;
	}
	public void setUserID(int userID)
	{
		this.userID = userID;
	}
	public String getOrderID()
	{
		return orderID;
	}
	public void setOrderID(String orderID)
	{
		this.orderID = orderID;
	}
	@Override
	public String toString()
	{
		return "DeleteOrderByIDReq [userID=" + userID + ", orderID=" + orderID
				+ ", token=" + token + "]";
	}

	
}
