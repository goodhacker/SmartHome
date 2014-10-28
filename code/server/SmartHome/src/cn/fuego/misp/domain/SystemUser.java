/**   
* @Title: User.java 
* @Package cn.fuego.remote.medical.expert.domain 
* @Description: TODO
* @author Tang Jun   
* @date 2014-9-24 上午11:49:00 
* @version V1.0   
*/ 
package cn.fuego.misp.domain;

import java.util.Date;

import cn.fuego.common.domain.PersistenceObject;

/** 
 * @ClassName: User 
 * @Description: TODO
 * @author Tang Jun
 * @date 2014-9-24 上午11:49:00 
 *  
 */

public class SystemUser implements PersistenceObject
{
	private int userID;
	private String userName;
	private String password;
	private int accountType;
	private Date regDate;
	
	
	public static String getUserIDAttr()
	{
		return "userID";
	}
	
	public static String getUserNameAttr()
	{
		return "userName";
	}
 
	public int getUserID()
	{
		return userID;
	}
	public void setUserID(int userID)
	{
		this.userID = userID;
	}
	public String getUserName()
	{
		return userName;
	}
	public void setUserName(String userName)
	{
		this.userName = userName;
	}
	public String getPassword()
	{
		return password;
	}
	public void setPassword(String password)
	{
		this.password = password;
	}
	public int getAccountType()
	{
		return accountType;
	}
	public void setAccountType(int accountType)
	{
		this.accountType = accountType;
	}
	public Date getRegDate()
	{
		return regDate;
	}
	public void setRegDate(Date regDate)
	{
		this.regDate = regDate;
	}
	

}
