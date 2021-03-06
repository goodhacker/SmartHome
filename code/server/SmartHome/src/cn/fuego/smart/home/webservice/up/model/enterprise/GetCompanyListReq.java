package cn.fuego.smart.home.webservice.up.model.enterprise;

import cn.fuego.misp.webservice.up.model.MispBaseReqJson;
import cn.fuego.smart.home.webservice.up.model.base.PageJson;


/** 
* @ClassName: GetCompanyListReq 
* @Description: TODO
* @author Aether
* @date 2015-3-13 上午10:11:48 
*  
*/
public class GetCompanyListReq extends MispBaseReqJson
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
	public String toString() {
		return "GetCompanyListReq [page=" + page + ", userID=" + userID
				+ ", token=" + token + "]";
	}
	
	
 
}