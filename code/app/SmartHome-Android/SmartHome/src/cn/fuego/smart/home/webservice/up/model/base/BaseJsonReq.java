package cn.fuego.smart.home.webservice.up.model.base;

import cn.fuego.smart.home.service.MemoryCache;

/**
 * 
* @ClassName: BaseJsonReq 
* @Description: TODO
* @author Tang Jun
* @date 2014-10-20 上午10:57:41 
*
 */
public class BaseJsonReq
{
	protected String token = MemoryCache.getToken();

	public String getToken()
	{
		return token;
	}

	public void setToken(String token)
	{
		this.token = token;
	}

	@Override
	public String toString()
	{
		return "BaseJsonReq [token=" + token + "]";
	}
 

}
