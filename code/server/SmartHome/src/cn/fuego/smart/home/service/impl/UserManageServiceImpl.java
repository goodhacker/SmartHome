/**   
* @Title: UserManageSerivceImpl.java 
* @Package cn.fuego.smart.home.service.impl 
* @Description: TODO
* @author Tang Jun   
* @date 2014-10-29 下午9:07:54 
* @version V1.0   
*/ 
package cn.fuego.smart.home.service.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.service.spi.ServiceException;

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
import cn.fuego.smart.home.dao.DaoContext;
import cn.fuego.smart.home.service.UserManageService;

 /** 
 * @ClassName: UserManageSerivceImpl 
 * @Description: TODO
 * @author Tang Jun
 * @date 2014-10-29 下午9:07:54 
 *  
 */
public class UserManageServiceImpl extends MISPUserServiceImpl implements UserManageService
{

	private Log log = LogFactory.getLog(UserManageServiceImpl.class);
	
	public SystemUser Login(String userName, String password)
	{
		SystemUser user = super.Login(userName, password);
		
		
		return user;
	}

	@Override
	public AbstractDataSource<SystemUser> getUserDataSource(List<QueryCondition> conditionList)
	{
		AbstractDataSource<SystemUser> datasource = new DataBaseSourceImpl<SystemUser>(SystemUser.class,conditionList);
		
		return datasource;
	}

	@Override
	public void saveUserInfo(SystemUser sysUser)
	{
		SystemUser oldUser= (SystemUser) MISPDaoContext.getInstance().getSystemUserDao().getUniRecord(new QueryCondition(ConditionTypeEnum.EQUAL, "userName", sysUser.getUserName()));
		if(null != oldUser)
		{
			log.error("create user failed,the user name "+ sysUser.getUserName() +" is existed." );
			throw new MISPException(MISPErrorMessageConst.USER_EXISTED);
		}
		SystemUser newUser = new SystemUser();
		newUser.setUserName(sysUser.getUserName());
		newUser.setRole(sysUser.getRole());
		newUser.setRegDate(DateUtil.getCurrentDateTime());
		newUser.setStatus(UserStatusEnum.REGISTERED.getIntValue());
		newUser.setPassword(SystemConfigInfo.getDefaultPassword());
		DaoContext.getInstance().getNewsDao().update(newUser);		
		
	}

	@Override
	public void deleteUserList(List<String> userIDList)
	{
		QueryCondition condition = new QueryCondition(ConditionTypeEnum.IN, "userID", userIDList);		
		MISPDaoContext.getInstance().getSystemUserDao().delete(condition);
		
	}

}
