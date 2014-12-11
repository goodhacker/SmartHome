/**   
* @Title: AlarmManageServiceImpl.java 
* @Package cn.fuego.smart.home.service.impl 
* @Description: TODO
* @author Aether
* @date 2014-11-7 上午10:13:43 
* @version V1.0   
*/ 
package cn.fuego.smart.home.service.impl;

import java.util.ArrayList;
import java.util.List;

import cn.fuego.common.contanst.ConditionTypeEnum;
import cn.fuego.common.dao.QueryCondition;
import cn.fuego.common.util.format.DataTypeConvert;
import cn.fuego.common.util.format.DateUtil;
import cn.fuego.common.util.validate.ValidatorUtil;
import cn.fuego.misp.service.impl.MispCommonServiceImpl;
import cn.fuego.smart.home.constant.AlarmClearEnum;
import cn.fuego.smart.home.constant.AlarmObjTypeEnmu;
import cn.fuego.smart.home.dao.DaoContext;
import cn.fuego.smart.home.domain.Alarm;
import cn.fuego.smart.home.domain.HomeSensor;
import cn.fuego.smart.home.service.AlarmManageService;
import cn.fuego.smart.home.service.ServiceContext;
import cn.fuego.smart.home.webservice.down.service.WebServiceContext;

/** 
 * @ClassName: AlarmManageServiceImpl 
 * @Description: TODO
 * @author Aether
 * @date 2014-11-7 上午10:13:43 
 *  
 */
public class AlarmManageServiceImpl extends MispCommonServiceImpl<Alarm> implements AlarmManageService
{

	 
 
	public List<Alarm> getAlarmOfUser(int userID)
	{
 		List<Integer> concentorIDList = DataPrivilegeManage.getConcentorOfUser(userID);
 		List<HomeSensor> sensorList = ServiceContext.getInstance().getSensorManageService().get(DataTypeConvert.intToStr(concentorIDList));
 		List<Integer> sensorIDList = new ArrayList<Integer>();
 		for(HomeSensor e : sensorList)
 		{
 			sensorIDList.add(e.getId());
 		}
 		List<Alarm> alarmList = new ArrayList<Alarm>();
 		if(!ValidatorUtil.isEmpty(sensorList))
 		{
 			List<QueryCondition> condtionList = new ArrayList<QueryCondition>();
 	 		condtionList.add(new QueryCondition(ConditionTypeEnum.EQUAL,"objType",AlarmObjTypeEnmu.HOME_SENSOR.getIntValue()));
 	 		condtionList.add(new QueryCondition(ConditionTypeEnum.IN,"objID",sensorIDList));
 	 		condtionList.add(new QueryCondition(ConditionTypeEnum.EQUAL,"clearStatus",AlarmClearEnum.NONE_CLEAR.getIntValue()));
 	 		alarmList = this.getDao().getAll(condtionList);
 		}
 		if(!ValidatorUtil.isEmpty(concentorIDList))
 		{
 			List<QueryCondition> condtionList = new ArrayList<QueryCondition>();
 	 		condtionList.add(new QueryCondition(ConditionTypeEnum.EQUAL,"objType",AlarmObjTypeEnmu.CONCENTRATOR_ALARM.getIntValue()));
 	 		condtionList.add(new QueryCondition(ConditionTypeEnum.IN,"objID",concentorIDList));
 	 		condtionList.add(new QueryCondition(ConditionTypeEnum.EQUAL,"clearStatus",AlarmClearEnum.NONE_CLEAR.getIntValue()));
 			alarmList.addAll(this.getDao().getAll(condtionList));
 		}
 		
 		return alarmList;
 		
	}

	@Override
	public void create(List<Alarm> objList)
	{
		
		super.create(objList); 
		WebServiceContext.getInstance().getPushService().pushAlarm(objList);
	}

	/* (non-Javadoc)
	 * @see cn.fuego.misp.service.impl.MispCommonServiceImpl#GetPrimaryName()
	 */
	@Override
	public String GetPrimaryName()
	{
		// TODO Auto-generated method stub
		return Alarm.PRI_KEY;
	}
	@Override
	public void autoClear(int id)
	{
		Alarm alarm = this.get(String.valueOf(id));
		alarm.setClearStatus(AlarmClearEnum.AUTO_CLEAR.getIntValue());
		alarm.setClearUser(AlarmClearEnum.AUTO_CLEAR.getStrValue());
		alarm.setClearTime(DateUtil.getCurrentDate());
		this.modify(alarm);
	}
	
	@Override
	public void manualClear(int userID, int id)
	{
		Alarm alarm = this.get(String.valueOf(id));
		alarm.setClearStatus(AlarmClearEnum.MANUAL_CLEAR.getIntValue());
		alarm.setClearTime(DateUtil.getCurrentDate());
		alarm.setClearUser(String.valueOf(userID));
		this.modify(alarm);
		
	}

 
}
