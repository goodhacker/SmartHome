package cn.fuego.misp.service.http;

import android.os.Message;
import cn.fuego.misp.constant.MISPErrorMessageConst;
import cn.fuego.misp.webservice.up.model.MispBaseRspJson;

public class MispHttpMessage 
{
	private Message message = new Message();

	public Message getMessage()
	{
		return message;
	}

	public void setMessage(Message message)
	{
		this.message = message;
	}
	
	public boolean isSuccess()
	{
		if(MISPErrorMessageConst.SUCCESS != message.what)
		{
			return false;
		}
		if(MISPErrorMessageConst.SUCCESS != getErrorCode())
		{
			return false;
		}
		return true;
		 
	}
	
	public boolean isNetSuccess()
	{
		if(MISPErrorMessageConst.SUCCESS != message.what)
		{
			return false;
		}
		return true;
	}

	public int getErrorCode()
	{
		MispBaseRspJson rsp = (MispBaseRspJson) message.obj;
		if(null != rsp)
		{
			return rsp.getErrorCode();
			
		}
		return MISPErrorMessageConst.NET_FAIL;
	}
	

}
