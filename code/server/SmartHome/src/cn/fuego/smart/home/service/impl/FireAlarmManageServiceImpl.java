/**   
* @Title: FireAlarmManageServiceImpl.java 
* @Package cn.fuego.smart.home.service.impl 
* @Description: TODO
* @author Aether
* @date 2015-3-11 下午5:27:45 
* @version V1.0   
*/ 
package cn.fuego.smart.home.service.impl;

import java.util.ArrayList;
import java.util.List;

import cn.fuego.common.contanst.ConditionTypeEnum;
import cn.fuego.common.dao.QueryCondition;
import cn.fuego.common.util.validate.ValidatorUtil;
import cn.fuego.misp.service.impl.MispCommonServiceImpl;
import cn.fuego.smart.home.constant.AlarmClearEnum;
import cn.fuego.smart.home.constant.AttributeConst;
import cn.fuego.smart.home.domain.Company;
import cn.fuego.smart.home.domain.FireAlarmView;
import cn.fuego.smart.home.service.FireAlarmManageService;
import cn.fuego.smart.home.service.ServiceContext;
import cn.fuego.smart.home.webservice.up.model.base.AttributeJson;

/** 
 * @ClassName: FireAlarmManageServiceImpl 
 * @Description: TODO
 * @author Aether
 * @date 2015-3-11 下午5:27:45 
 *  
 */
public class FireAlarmManageServiceImpl extends MispCommonServiceImpl<FireAlarmView>  implements  FireAlarmManageService
{

	@Override
	public List<FireAlarmView> getFireAlarmByCompany(String companyID,int startNum, int pageSize, List<AttributeJson> filterList)
	{
		Company company=ServiceContext.getInstance().getCompanyManageService().get(Integer.valueOf(companyID));
		List<QueryCondition> condtionList = new ArrayList<QueryCondition>();
		List<FireAlarmView>  fireAlarmList= new ArrayList<FireAlarmView>();
		//是否需要做判空处理
		condtionList.add(new QueryCondition(ConditionTypeEnum.EQUAL, "concentratorID", company.getConcentratorID()));
		
		if(!ValidatorUtil.isEmpty(filterList))
		{
			for(AttributeJson attr:filterList)
			{
				if(attr.getAttrName().equals(AttributeConst.ALARM_KIND))
				{
					if(!ValidatorUtil.isEmpty(attr.getAttrValue()))
					{
						condtionList.add(new QueryCondition(ConditionTypeEnum.EQUAL, "kind", attr.getAttrValue()));
					}
					
				}
			}
			
		}
		condtionList.add(new QueryCondition(ConditionTypeEnum.EQUAL, "clearStatus", AlarmClearEnum.NONE_CLEAR.getIntValue()));
	 	condtionList.add(new QueryCondition(ConditionTypeEnum.DESC_ORDER,"alarmTime"));
	 	fireAlarmList.addAll(this.getDao(FireAlarmView.class).getAll(condtionList, startNum, pageSize));
	 	
	 	return fireAlarmList;
	}
}
