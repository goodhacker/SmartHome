/**   
* @Title: AlarmPushServiceImpl.java 
* @Package cn.fuego.smart.home.webservice.down.service 
* @Description: TODO
* @author Tang Jun   
* @date 2014-12-3 下午11:28:04 
* @version V1.0   
*/ 
package cn.fuego.smart.home.webservice.down.service.impl;

import java.util.List;

import cn.fuego.common.contanst.ConditionTypeEnum;
import cn.fuego.common.dao.QueryCondition;
import cn.fuego.common.log.FuegoLog;
import cn.fuego.common.util.validate.ValidatorUtil;
import cn.fuego.smart.home.constant.PushMessagTypeEnum;
import cn.fuego.smart.home.domain.Alarm;
import cn.fuego.smart.home.domain.UserConcentrator;
import cn.fuego.smart.home.service.ServiceContext;
import cn.fuego.smart.home.service.cache.AppLoginCache;
import cn.fuego.smart.home.service.cache.BaiduPushInfo;
import cn.fuego.smart.home.webservice.down.model.MessageInfoJson;
import cn.fuego.smart.home.webservice.down.model.PushMessageJson;
import cn.fuego.smart.home.webservice.down.service.BaiduPushTool;
import cn.fuego.smart.home.webservice.down.service.PushService;

 /** 
 * @ClassName: AlarmPushServiceImpl 
 * @Description: TODO
 * @author Tang Jun
 * @date 2014-12-3 下午11:28:04 
 *  
 */
public class PushServiceImpl implements PushService
{
	private FuegoLog log = FuegoLog.getLog(getClass());

	/* (non-Javadoc)
	 * @see cn.fuego.smart.home.webservice.down.service.AlarmPushService#pushAlarm(java.util.List)
	 */
	@Override
	public void pushAlarm(List<Alarm> alarmList)
	{
		 for(Alarm alarm : alarmList)
		 {
			 
			 QueryCondition conditon = new QueryCondition(ConditionTypeEnum.EQUAL, UserConcentrator.attr_concentratorID,String.valueOf(alarm.getConcenratorID()));
			 
			 List<UserConcentrator> userConList = ServiceContext.getInstance().getConcentratorManageService().get(UserConcentrator.class, conditon);
			 if(!ValidatorUtil.isEmpty(userConList))
			 {
				 for(UserConcentrator userCon : userConList)
				 {
					 BaiduPushInfo pushInfo = AppLoginCache.getPushInfo(userCon.getUserID());
					 
					 if(null != pushInfo)
					 {
						 PushMessageJson json = new PushMessageJson();
						 
						 json.setTitle("告警");
						 json.setDescription("火警");
						 MessageInfoJson message = new MessageInfoJson();
						 message.setObjType(PushMessagTypeEnum.ALRAM_MSG.getIntValue());
						 message.setObj(alarm.getId());
						 json.setCustomContentString(message);

						 BaiduPushTool.pushNotification(pushInfo, json);
					 }
					 else
					 {
						 log.info("no need to push,the user have not been logined, user id is " + userCon.getUserID());
					 }
					
	
				 }
			 }
			 else
			 {
				 log.warn("can not get manage user,we will not push anythig for the alarm"  + alarm);
 			 }
			 
		 }
		
	}

}