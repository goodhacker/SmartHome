package cn.fuego.smart.home.webservice.up.model;

import cn.fuego.misp.webservice.up.model.MispBaseReqJson;
import cn.fuego.smart.home.webservice.up.model.base.PageJson;


/**
 * 
* @ClassName: GetUserMarkListReq 
* @Description: TODO
* @author Tang Jun
* @date 2014-10-20 上午10:59:10 
*
 */
public class GetUserMarkListReq extends MispBaseReqJson
{
	private PageJson page;
	private int userID;
	public PageJson getPage()
	{
		return page;
	}
	public void setPage(PageJson page)
	{
		this.page = page;
	}
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
		return "GetUserMarkListReq [page=" + page + ", userID=" + userID + "]";
	}
	
	
 
}