package cn.fuego.smart.home.ui.setting.user;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import cn.fuego.common.log.FuegoLog;
import cn.fuego.misp.service.http.MispHttpMessage;
import cn.fuego.smart.home.R;
import cn.fuego.smart.home.service.MemoryCache;
import cn.fuego.smart.home.ui.LoginActivity;
import cn.fuego.smart.home.ui.base.BaseActivtiy;
import cn.fuego.smart.home.ui.base.ExitApplication;
import cn.fuego.smart.home.ui.base.SharedPreUtil;
import cn.fuego.smart.home.ui.base.UserEntity;
import cn.fuego.smart.home.webservice.up.model.ModifyPwdReq;
import cn.fuego.smart.home.webservice.up.rest.WebServiceContext;

public class ModifyPwdActivity extends BaseActivtiy implements View.OnClickListener
{
	private FuegoLog log = FuegoLog.getLog(getClass());
	
	private EditText txt_oldPwd,txt_newPwd1,txt_newPwd2;
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.modify_pwd);
		ExitApplication.getInstance().addActivity(this);
		
		txt_oldPwd = (EditText) findViewById(R.id.txt_oldPwd);
		txt_newPwd1 = (EditText) findViewById(R.id.txt_newPwd1);
		txt_newPwd2 = (EditText) findViewById(R.id.txt_newPwd2);
		txt_oldPwd.requestFocus();            
		txt_oldPwd.requestFocusFromTouch();
		Button back_btn=(Button)findViewById(R.id.modify_pwd_back);
		back_btn.setOnClickListener(this);
		back_btn.setTag(1);
		
		Button user_info_btn = (Button) findViewById(R.id.modify_pwd_sure);
		user_info_btn.setOnClickListener(this);
		user_info_btn.setTag(2);
	}

	@Override
	public void onClick(View v)
	{
		int tag = (Integer) v.getTag();

		switch(tag)
		{
		case 1: this.finish();  
				break;//密码修改返回
		case 2: modifyPwd();
		 		break;//跳转到登录界面或者退出

		default:break;
		}
		
	}

	private void modifyPwd()
	{
		if(getTrimText(txt_oldPwd)!=null&&getTrimText(txt_newPwd1)!=null&&getTrimText(txt_newPwd2)!=null)
		{
			if(getTrimText(txt_newPwd1).equals(getTrimText(txt_newPwd2)))
			{
				ModifyPwdReq req = new ModifyPwdReq();
				req.setOldPwd(this.MD5(this.getTrimText(txt_oldPwd)));
				req.setPwdNew(this.MD5(this.getTrimText(txt_newPwd1)));
				req.setUserName(MemoryCache.getLoginInfo().getUser().getUserName());
				WebServiceContext.getInstance().getUserManageRest(this).modifyPassword(req);
				
			}
			else
			{
				this.showMessage("新密码输入不一致！");
			}
		}
		else
		{
			this.showMessage("输入不能为空！");
		}
		
	}

	@Override
	public void handle(MispHttpMessage message)
	{

		
		if (message.isSuccess())
		{

			Intent intent = new Intent(ModifyPwdActivity.this,LoginActivity.class);
			intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP );
			MemoryCache.setFlag(1);
			startActivity(intent);
			this.finish();
			//ExitApplication.getInstance().exit(this);
		}

			showToast(ModifyPwdActivity.this, message);


	}
}
