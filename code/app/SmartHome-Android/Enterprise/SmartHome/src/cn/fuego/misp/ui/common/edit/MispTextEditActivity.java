package cn.fuego.misp.ui.common.edit;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import cn.fuego.common.util.validate.ValidatorUtil;
import cn.fuego.misp.service.http.MispHttpMessage;
import cn.fuego.misp.ui.base.MispBaseActivtiy;
import cn.fuego.smart.home.R;

public class MispTextEditActivity extends MispBaseActivtiy 
{

	public static String JUMP_DATA = "result";
	
	public static int REQUEST_CODE = 1;
	private TextView takeAddr;
	
	private MispEditParameter result;
 
	

	public static void jump(Activity activity,MispEditParameter parameter,int code)
	{
 		Intent intent = new Intent();
 		intent.setClass(activity, MispTextEditActivity.class);
 		intent.putExtra(MispTextEditActivity.JUMP_DATA, parameter);
 	
 		activity.startActivityForResult(intent,code);

  	}
	@Override
	public void initRes()
	{
		//this.activityRes.setName("修改配送信息");
		this.activityRes.setAvtivityView(R.layout.misp_text_edit);
		this.activityRes.getButtonIDList().add(R.id.misp_title_save);
 
		Intent intent = this.getIntent();
		result = (MispEditParameter) intent.getSerializableExtra(MispTextEditActivity.JUMP_DATA);
 
		if(null != result)
		{
			this.activityRes.setName(result.getTilteName());
		}
	
	}
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		
 		if(null != result)
		{
			takeAddr = (TextView) findViewById(R.id.misp_text_edit_txt);
			String value = result.getDataValue();
			if(!ValidatorUtil.isEmpty(value))
			{
				takeAddr.setText(value);

			}
			if(!ValidatorUtil.isEmpty(result.getPointOut()))
			{
				takeAddr.setHint(result.getPointOut());
			}
			
 
 
		}
		
		
	}
	
	public void activityFinish(MispEditParameter result)
	{
        Intent intent = new Intent();
        intent.putExtra(JUMP_DATA, result);
        /*
         * 调用setResult方法表示我将Intent对象返回给之前的那个Activity，这样就可以在onActivityResult方法中得到Intent对象，
         */
        setResult(1001, intent);

		this.finish();
	}

	@Override
	public void onClick(View v)
	{
		switch(v.getId())
		{
			case R.id.misp_title_save:
			{
 				
  
				String data = takeAddr.getText().toString().trim();
				if(!result.isValid(data))
				{
					showMessage(result.getErrorMsg());
					return;
				}
				result.setDataValue(data);

                activityFinish(result);
			}
			break;
 
		}

  
	}
	
 
 


	 

}
