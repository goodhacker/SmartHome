/**   
* @Title: SensorPlan.java 
* @Package cn.fuego.smart.home.domain 
* @Description: TODO
* @author Aether
* @date 2015-1-29 下午3:57:58 
* @version V1.0   
*/ 
package cn.fuego.smart.home.domain;

import cn.fuego.common.domain.PersistenceObject;

/** 
 * @ClassName: SensorPlan 
 * @Description: TODO
 * @author Aether
 * @date 2015-1-29 下午3:57:58 
 *  
 */
public class SensorPlan implements PersistenceObject
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final String PRI_KEY = "planID";
	
	private int planID; 	//自增长，主键
	private int buildingID; //楼编号
	private String floor;	//
	private String name;
	private String desp;
	private String picPath; //文件路径
	public int getPlanID()
	{
		return planID;
	}
	public void setPlanID(int planID)
	{
		this.planID = planID;
	}
	public int getBuildingID()
	{
		return buildingID;
	}
	public void setBuildingID(int buildingID)
	{
		this.buildingID = buildingID;
	}
	public String getFloor()
	{
		return floor;
	}
	public void setFloor(String floor)
	{
		this.floor = floor;
	}
	public String getName()
	{
		return name;
	}
	public void setName(String name)
	{
		this.name = name;
	}
	public String getDesp()
	{
		return desp;
	}
	public void setDesp(String desp)
	{
		this.desp = desp;
	}
	public String getPicPath()
	{
		return picPath;
	}
	public void setPicPath(String picPath)
	{
		this.picPath = picPath;
	}


	
}
