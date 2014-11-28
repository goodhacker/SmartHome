/**   
* @Title: UserManageSerivceImpl.java 
* @Package cn.fuego.smart.home.service.impl 
* @Description: TODO
* @author Tang Jun   
* @date 2014-10-29 下午9:07:54 
* @version V1.0   
*/ 
package cn.fuego.smart.home.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import cn.fuego.common.contanst.ConditionTypeEnum;
import cn.fuego.common.dao.QueryCondition;
import cn.fuego.common.dao.datasource.AbstractDataSource;
import cn.fuego.common.dao.datasource.DataBaseSourceImpl;
import cn.fuego.common.util.SystemConfigInfo;
import cn.fuego.common.util.format.DateUtil;
import cn.fuego.misp.constant.MISPErrorMessageConst;
import cn.fuego.misp.dao.MISPDaoContext;
import cn.fuego.misp.domain.SystemUser;
import cn.fuego.misp.service.MISPException;
import cn.fuego.misp.service.impl.MISPUserServiceImpl;
import cn.fuego.smart.home.constant.UserStatusEnum;
import cn.fuego.smart.home.constant.UserTypeEnum;
import cn.fuego.smart.home.dao.DaoContext;
import cn.fuego.smart.home.domain.UserMark;
import cn.fuego.smart.home.service.UserManageService;

 /** 
 * @ClassName: UserManageSerivceImpl 
 * @Description: TODO
 * @author Tang Jun
 * @date 2014-10-29 下午9:07:54 
 *  
 */
public class UserManageServiceImpl extends MISPUserServiceImpl<SystemUser> implements UserManageService
{

	private Log log = LogFactory.getLog(UserManageServiceImpl.class);
	
	public SystemUser Login(String userName, String password)
	{
		SystemUser user = super.Login(userName, password);
		
		
		return user;
	}
    @Override
    public void create(SystemUser user)
    {
    	user.setPassword(SystemConfigInfo.getDefaultPassword());
    	user.setStatus(UserStatusEnum.REGISTERED.getIntValue());//默认已注册
    	super.create(user);
    }
 

	@Override
	public void delete(List<String> userIDList)
	{
		for(int i=0;i<userIDList.size();i++)
		{
			String userID = userIDList.get(i);
			SystemUser oldUser= MISPDaoContext.getInstance().getSystemUserDao().getUniRecord(new QueryCondition(ConditionTypeEnum.EQUAL, "userID", userID));
			if(oldUser.getRole()==UserTypeEnum.ADMIN.getTypeValue())
			{
				throw new MISPException(MISPErrorMessageConst.ADMIN_NOT_DELETED);
			}
		}
		//QueryCondition condition = new QueryCondition(ConditionTypeEnum.IN, "userID", userIDList);		
		//MISPDaoContext.getInstance().getSystemUserDao().delete(condition);
		super.delete(userIDList);
		
	}

	/* (non-Javadoc)
	 * @see cn.fuego.smart.home.service.UserManageService#getUseMark()
	 */
	@Override
	public List<UserMark> getUseMark(int userID)
	{
		 List<UserMark> list  = (List<UserMark>) DaoContext.getInstance().getUserMarkDao().getAll();
		 return list;
	}

	/* (non-Javadoc)
	 * @see cn.fuego.smart.home.service.UserManageService#deleteUserMark(cn.fuego.smart.home.domain.UserMark)
	 */
	@Override
	public void deleteUserMark(UserMark userMark)
	{
		
		List<QueryCondition> conditionList = new ArrayList<QueryCondition>();
		conditionList.add(new QueryCondition(ConditionTypeEnum.EQUAL,UserMark.getUserIDAttr(),String.valueOf(userMark.getUserID())));
		conditionList.add(new QueryCondition(ConditionTypeEnum.EQUAL,UserMark.getMarkAttr(),userMark.getMark()));

		DaoContext.getInstance().getUserMarkDao().delete(conditionList);
		
	}

	/* (non-Javadoc)
	 * @see cn.fuego.smart.home.service.UserManageService#createUserMark(cn.fuego.smart.home.domain.UserMark)
	 */
	@Override
	public void createUserMark(UserMark userMark)
	{
		DaoContext.getInstance().getUserMarkDao().create(userMark);
		
	}

}
