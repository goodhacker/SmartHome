package cn.fuego.smart.home.webservice.from.client.model.base;


/**
 * 
* @ClassName: ConcentratorJson 
* @Description: TODO
* @author Tang Jun
* @date 2014-10-20 上午10:57:50 
*
 */
public class ConcentratorJson
{
	private int concentratorID;
	private String ipAddr;
	private int status;
	private float locationNS;
	private float locationWE;
	private String description;
	private String addr;
	private String mark;
	public int getConcentratorID()
	{
		return concentratorID;
	}
	public void setConcentratorID(int concentratorID)
	{
		this.concentratorID = concentratorID;
	}
	public String getIpAddr()
	{
		return ipAddr;
	}
	public void setIpAddr(String ipAddr)
	{
		this.ipAddr = ipAddr;
	}
	public int getStatus()
	{
		return status;
	}
	public void setStatus(int status)
	{
		this.status = status;
	}
	public float getLocationNS()
	{
		return locationNS;
	}
	public void setLocationNS(float locationNS)
	{
		this.locationNS = locationNS;
	}
	public float getLocationWE()
	{
		return locationWE;
	}
	public void setLocationWE(float locationWE)
	{
		this.locationWE = locationWE;
	}
	public String getDescription()
	{
		return description;
	}
	public void setDescription(String description)
	{
		this.description = description;
	}
	public String getAddr()
	{
		return addr;
	}
	public void setAddr(String addr)
	{
		this.addr = addr;
	}
	public String getMark()
	{
		return mark;
	}
	public void setMark(String mark)
	{
		this.mark = mark;
	}
	@Override
	public String toString()
	{
		return "ConcentratorJson [concentratorID=" + concentratorID
				+ ", ipAddr=" + ipAddr + ", status=" + status + ", locationNS="
				+ locationNS + ", locationWE=" + locationWE + ", description="
				+ description + ", addr=" + addr + ", mark=" + mark + "]";
	}
 

}
