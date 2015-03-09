package cn.fuego.smart.home.service;

import cn.fuego.smart.home.service.impl.AlarmManageServiceImpl;
import cn.fuego.smart.home.service.impl.BuildingManageServiceImpl;
import cn.fuego.smart.home.service.impl.CompanyManageServiceImpl;
import cn.fuego.smart.home.service.impl.ConcentratorManageServiceImpl;
import cn.fuego.smart.home.service.impl.FireSensorManageServiceImpl;
import cn.fuego.smart.home.service.impl.KnowledgeManageServiceImpl;
import cn.fuego.smart.home.service.impl.NewsManageServiceImpl;
import cn.fuego.smart.home.service.impl.PlanManageServiceImpl;
import cn.fuego.smart.home.service.impl.ProductManageServiceImpl;
import cn.fuego.smart.home.service.impl.SensorManageServiceImpl;
import cn.fuego.smart.home.service.impl.SensorTypeManageServiceImpl;
import cn.fuego.smart.home.service.impl.ServiceOrderManageServiceImpl;
import cn.fuego.smart.home.service.impl.UserManageServiceImpl;

public class ServiceContext
{
	private static ServiceContext instance;

	private NewsManageService newsManageService  = null;
	private SensorManageService sensorManageService  = null;
	private ServiceOrderManageService serviceOrderManageService  = null;
    private SensorTypeManageService sensorTypeManageService=null;
	private ConcentratorManageService concentratorManageService = null;
	private UserManageService userManageService = null;
	
	private AlarmManageService alarmManageService =null;
	
	private CompanyManageService companyManageService = null;
	
	private BuildingManageService  buildingManageService = null;

	
	private PlanManageService planManageService = null;
	private FireSensorManageService fireSensorManageService = null;

	
	private KnowledgeManageService knowledgeManageService = null;
	
	private ProductManageService productManageService = null;

	
	private ServiceContext()
	{

	}

	public static synchronized ServiceContext getInstance()
	{
		if (null == instance)
		{
			instance = new ServiceContext();
		}
		return instance;
	}
	public synchronized NewsManageService getNewsManageService()
	{
		if (null == newsManageService)
		{
			newsManageService = new NewsManageServiceImpl();
		}
		return newsManageService;
	}
 
	public synchronized SensorManageService getSensorManageService()
	{
		if (null == sensorManageService)
		{
			sensorManageService =  new SensorManageServiceImpl();
		}
		return sensorManageService;
	}
	public synchronized ServiceOrderManageService getServiceOrderManageService()
	{
		if (null == serviceOrderManageService)
		{
			serviceOrderManageService =  new ServiceOrderManageServiceImpl();
		}
		return serviceOrderManageService;
	}
	
	public synchronized ConcentratorManageService getConcentratorManageService()
	{
		if (null == concentratorManageService)
		{
			concentratorManageService =  new ConcentratorManageServiceImpl();
		}
		return concentratorManageService;
	}
	public synchronized UserManageService  getUserManageService()
	{
		if (null == userManageService)
		{
			userManageService =  new UserManageServiceImpl();
		}
		return userManageService;
	}
	public synchronized AlarmManageService  getAlarmManageService()
	{
		if (null == alarmManageService)
		{
			alarmManageService =  new AlarmManageServiceImpl();
		}
		return alarmManageService;
	}
	public synchronized SensorTypeManageService  getSensorTypeManageService()
	{
		if (null == sensorTypeManageService)
		{
			sensorTypeManageService =  new SensorTypeManageServiceImpl();
		}
		return sensorTypeManageService;
	}	
	public synchronized CompanyManageService  getCompanyManageService()
	{
		if (null == companyManageService)
		{
			companyManageService =  new CompanyManageServiceImpl();
		}
		return companyManageService;
	}
	public synchronized BuildingManageService  getBuildingManageService()
	{
		if (null == buildingManageService)
		{
			buildingManageService =  new BuildingManageServiceImpl();
		}
		return buildingManageService;
	}
	public synchronized PlanManageService  getPlanManageService()
	{
		if (null == planManageService)
		{
			planManageService =  new PlanManageServiceImpl();
		}
		return planManageService;
	}
	
	public synchronized FireSensorManageService  getFireSensorManageService()
	{
		if (null == fireSensorManageService)
		{
			fireSensorManageService =  new FireSensorManageServiceImpl();
		}
		return fireSensorManageService;
	}

	/**
	 * @return
	 */
	public synchronized KnowledgeManageService getKnowledgeManageService()
	{
		if (null == knowledgeManageService)
		{
			knowledgeManageService =  new  KnowledgeManageServiceImpl();
		}
		return knowledgeManageService;
		
	}

	/**
	 * @return
	 */
	public ProductManageService getProductManageService()
	{
		if (null == productManageService)
		{
			productManageService =  new  ProductManageServiceImpl();
		}
		return productManageService;
	}
}
