/**   
* @Title: UserConcentrator.java 
* @Package cn.fuego.smart.home.domain 
* @Description: TODO
* @author Tang Jun   
* @date 2014-11-5 下午11:33:40 
* @version V1.0   
*/ 
package cn.fuego.smart.home.domain;

import cn.fuego.common.domain.PersistenceObject;

 /** 
 * @ClassName: UserConcentrator 
 * @Description: TODO
 * @author Tang Jun
 * @date 2014-11-5 下午11:33:40 
 *  
 */
public class UserConcentrator implements PersistenceObject
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer userID;
	private Integer concentratorID;
	private Integer operate;  //0,read 1 modify 2 delete 3 all
	public Integer getUserID()
	{
		return userID;
	}
	public void setUserID(Integer userID)
	{
		this.userID = userID;
	}
	public Integer getConcentratorID()
	{
		return concentratorID;
	}
	public void setConcentratorID(Integer concentratorID)
	{
		this.concentratorID = concentratorID;
	}
	public Integer getOperate()
	{
		return operate;
	}
	public void setOperate(Integer operate)
	{
		this.operate = operate;
	}

	

}
