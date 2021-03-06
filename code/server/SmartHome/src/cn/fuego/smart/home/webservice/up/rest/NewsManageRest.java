package cn.fuego.smart.home.webservice.up.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import cn.fuego.smart.home.webservice.up.model.GetNewsByIDReq;
import cn.fuego.smart.home.webservice.up.model.GetNewsByIDRsp;
import cn.fuego.smart.home.webservice.up.model.GetNewsListReq;
import cn.fuego.smart.home.webservice.up.model.GetNewsListRsp;

/**
 * 
* @ClassName: NewsManageService 
* @Description: TODO
* @author Tang Jun
* @date 2014-10-20 上午11:03:11 
*
 */
@Path("/news")
@Produces("application/json")
@Consumes("application/json")  
public interface NewsManageRest
{
	@POST	
	@Path("/list")
	public GetNewsListRsp getNewsList(GetNewsListReq req);

	@POST	
	@Path("/id")
	public GetNewsByIDRsp getNews(GetNewsByIDReq req);
	
 
}
