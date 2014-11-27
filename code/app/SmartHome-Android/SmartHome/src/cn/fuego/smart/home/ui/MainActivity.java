package cn.fuego.smart.home.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.Window;
import android.view.WindowManager;
import cn.fuego.common.log.FuegoLog;
import cn.fuego.smart.home.webservice.up.rest.interceptor.AuthInterceptor;

import com.fuego.smarthome.R;

public class MainActivity extends Activity
{
	private FuegoLog log = FuegoLog.getLog(AuthInterceptor.class);

	public void onCreate(Bundle savedInstanceState) 
	{
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
		WindowManager.LayoutParams.FLAG_FULLSCREEN);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.welcome);

		new CountDownTimer(2000,1000) {

		@Override
		public void onTick(long millisUntilFinished) {
		}
		@Override
		public void onFinish() 
		{
		Intent intent = new Intent();
		intent.setClass(MainActivity.this, LoginActivity.class);
		startActivity(intent);

		int VERSION=Integer.parseInt(android.os.Build.VERSION.SDK);
		if(VERSION >= 5)
		{
			MainActivity.this.overridePendingTransition(R.anim.alpha_in, R.anim.alpha_out);
		}
		finish();
		}
		}.start();
		}
		
}
