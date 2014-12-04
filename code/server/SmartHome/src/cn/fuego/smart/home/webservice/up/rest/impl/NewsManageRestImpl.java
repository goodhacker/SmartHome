/**   
* @Title: NewsManageServiceImpl.java 
* @Package cn.fuego.smart.home.webservice.from.client.service.impl 
* @Description: TODO
* @author Tang Jun   
* @date 2014-10-20 上午11:22:37 
* @version V1.0   
*/ 
package cn.fuego.smart.home.webservice.up.rest.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import cn.fuego.smart.home.domain.News;
import cn.fuego.smart.home.service.NewsManageService;
import cn.fuego.smart.home.service.ServiceContext;
import cn.fuego.smart.home.service.impl.SensorManageServiceImpl;
import cn.fuego.smart.home.webservice.up.model.GetNewsListReq;
import cn.fuego.smart.home.webservice.up.model.GetNewsListRsp;
import cn.fuego.smart.home.webservice.up.model.base.NewsJson;
import cn.fuego.smart.home.webservice.up.rest.NewsManageRest;

 /** 
 * @ClassName: NewsManageServiceImpl 
 * @Description: TODO
 * @author Tang Jun
 * @date 2014-10-20 上午11:22:37 
 *  
 */
public class NewsManageRestImpl implements NewsManageRest
{
	private Log log = LogFactory.getLog(NewsManageRestImpl.class);

	private NewsManageService newsService = ServiceContext.getInstance().getNewsManageService();
	/* (non-Javadoc)
	 * @see cn.fuego.smart.home.webservice.from.client.service.NewsManageService#getNewsList(cn.fuego.smart.home.webservice.from.client.model.GetNewsListReq)
	 */
	@Override
	public GetNewsListRsp getNewsList(GetNewsListReq req)
	{
		GetNewsListRsp rsp = new GetNewsListRsp();
		List<News> newsList = newsService.getDataSource().getAllPageData();
		for(News e : newsList)
		{
			NewsJson newsJson = new NewsJson();
			newsJson.loadWithNews(e);
			rsp.getNewsList().add(newsJson);
		}
		
 		return rsp;
	}

}