package cn.fuego.smart.home.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import cn.fuego.common.log.FuegoLog;
import cn.fuego.smart.home.R;
import cn.fuego.smart.home.service.MemoryCache;
import cn.fuego.smart.home.ui.bdsend.Utils;
import cn.fuego.smart.home.webservice.up.rest.interceptor.AuthInterceptor;

import com.baidu.android.pushservice.PushConstants;
import com.baidu.android.pushservice.PushManager;

public class MainActivity extends Activity
{
	private FuegoLog log = FuegoLog.getLog(AuthInterceptor.class);
    //private int loginFlag=0;
	public void onCreate(Bundle savedInstanceState) 
	{

		super.onCreate(savedInstanceState);
		//requestWindowFeature(Window.FEATURE_NO_TITLE);
		//getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.main_welcome);
        // Push: ��apikey�ķ�ʽ��¼��һ�������Activity��onCreate�С�
        // �����apikey�����manifest�ļ��У�ֻ��һ�ִ�ŷ�ʽ��
        // ���������Զ��峣����������ʽʵ�֣����滻�����е�Utils.getMetaValue(PushDemoActivity.this,
        // "api_key")
        PushManager.startWork(getApplicationContext(),PushConstants.LOGIN_TYPE_API_KEY,
                Utils.getMetaValue(MainActivity.this, "api_key"));
        // Push: �������ڵ���λ�����ͣ����Դ�֧�ֵ���λ�õ����͵Ŀ���
        // PushManager.enableLbs(getApplicationContext());

	new CountDownTimer(2000, 1000)
		{

			@Override
			public void onTick(long millisUntilFinished)
			{
			}

			@Override
			public void onFinish()
			{
				Intent intent = new Intent();
				//SharedPreferences userInfo = getSharedPreferences(SharedPreferenceConst.UESR_INFO, 0);
				
				if(!MemoryCache.isLogin())
		        {
		        	intent.setClass(MainActivity.this, LoginActivity.class);
		        	//loginFlag =1;
		        	
		        }
		        else
		        {
		        	intent.setClass(MainActivity.this, MainTabbarActivity.class);
		        	intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		        }
				
				startActivity(intent);

				@SuppressWarnings("deprecation")
				int VERSION = Integer.parseInt(android.os.Build.VERSION.SDK);
				if (VERSION >= 5)
				{
					MainActivity.this.overridePendingTransition(
							R.anim.alpha_in, R.anim.alpha_out);
				}
				finish();
			}
		}.start();
	}
		
}
