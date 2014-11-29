/**   
* @Title: ConcentratorManageService.java 
* @Package cn.fuego.smart.home.service 
* @Description: TODO
* @author Tang Jun   
* @date 2014-10-31 上午12:21:07 
* @version V1.0   
*/ 
package cn.fuego.smart.home.service;

import java.util.List;
import cn.fuego.misp.service.MispCommonService;
import cn.fuego.common.dao.QueryCondition;
import cn.fuego.common.dao.datasource.AbstractDataSource;
import cn.fuego.smart.home.domain.Concentrator;
import cn.fuego.smart.home.domain.UserConcentrator;

 /** 
 * @ClassName: ConcentratorManageService 
 * @Description: TODO
 * @author Tang Jun
 * @date 2014-10-31 上午12:21:07 
 *  
 */
public interface ConcentratorManageService extends MispCommonService<Concentrator>
{
	void online(Concentrator concentrator);
	void offline(Concentrator concentrator);

	void modifyConcentInfo(Concentrator concent);
	void deleteConcentList(List<String> concentIDList);
	Concentrator getDistributionInfo(List<QueryCondition> mapConidtionList);

	void addPermission(UserConcentrator userConcentrator);
	void deletePermissionByID(String userID, String concentratorID);
	UserConcentrator getPermissionByID(String userID, String concentratorID);
	void modifyPermission(UserConcentrator userPermission);
	
	String getOperatePemission(int userID, String concentratorID);
	
	AbstractDataSource<UserConcentrator> getPermissionDataSource(int accountType, List<QueryCondition> conditionList);
	

}
