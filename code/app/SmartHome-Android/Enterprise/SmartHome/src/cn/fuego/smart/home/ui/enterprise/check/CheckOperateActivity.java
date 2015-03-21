package cn.fuego.smart.home.ui.enterprise.check;



import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;
import cn.fuego.common.util.validate.ValidatorUtil;
import cn.fuego.misp.service.MemoryCache;
import cn.fuego.misp.ui.base.MispBaseActivtiy;
import cn.fuego.misp.ui.common.upload.MispUploadImgActivity;
import cn.fuego.misp.ui.model.ListViewResInfo;
import cn.fuego.misp.ui.util.LoadImageUtil;
import cn.fuego.misp.ui.util.StrUtil;
import cn.fuego.misp.webservice.up.model.MispBaseRspJson;
import cn.fuego.smart.home.R;
import cn.fuego.smart.home.constant.CheckResultEnum;
import cn.fuego.smart.home.constant.IntentCodeConst;
import cn.fuego.smart.home.service.CheckLogCache;
import cn.fuego.smart.home.webservice.up.model.base.CheckLogJson;

public class CheckOperateActivity extends MispBaseActivtiy implements OnCheckedChangeListener
{

	private View abnormalView;
	private CheckLogJson checkLog;
	private TextView abnormal_desp;
	private ImageView abnormal_img;
	//private String abnormalPic="";
	@Override
	public void initRes()
	{
		this.activityRes.setAvtivityView(R.layout.activity_check_operate);
		this.activityRes.setName("日常巡检");
		this.activityRes.getButtonIDList().add(R.id.check_operate_upload_btn);
		checkLog = (CheckLogJson) this.getIntent().getSerializableExtra(ListViewResInfo.SELECT_ITEM);
		
	}

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		abnormalView=findViewById(R.id.check_operate_desp);

		initData();

 
	}

	//初始化数据
	private void initData()
	{
		RadioButton unset_btn= (RadioButton) findViewById(R.id.radio_btn_unset);
		RadioButton normal_btn= (RadioButton) findViewById(R.id.radio_btn_normal);
		RadioButton abnormal_btn= (RadioButton) findViewById(R.id.radio_btn_abnormal);
		
		switch(CheckResultEnum.getEnumByInt(checkLog.getCheckResult()))
		{
		case NONE_SET:
			unset_btn.setChecked(true);
			abnormalView.setVisibility(View.INVISIBLE);
			break;
		case NORMAL:
			normal_btn.setChecked(true);
			abnormalView.setVisibility(View.INVISIBLE);
			break;
		case ABNORMAL:
			abnormal_btn.setChecked(true);
			abnormalView.setVisibility(View.VISIBLE);
			break;
			
		}
		abnormal_desp = (TextView) findViewById(R.id.check_operate_abnormal_desp);
		abnormal_desp.setText(StrUtil.noNullStr(checkLog.getAbnormalDesp()));
		
		RadioGroup group = (RadioGroup) findViewById(R.id.check_operate_radiogroup);
		group.setOnCheckedChangeListener(this);
		
		abnormal_img= (ImageView) findViewById(R.id.check_operate_img);
		if(!ValidatorUtil.isEmpty(checkLog.getAbnormalPic()))
		{
			loadImag(checkLog.getAbnormalPic());
		}
		
		
	}

	@Override
	public void saveOnClick(View v)
	{
		Intent intent = new Intent();
        //以下设置flag 有作用
		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		CheckOperateActivity.this.setResult(IntentCodeConst.RESULT_CODE,intent);
		checkLog.setAbnormalDesp(abnormal_desp.getText().toString().trim());
	 
		CheckLogCache.getInstance().update(checkLog);
		this.finish();
		
	}


	@Override
	public void onClick(View v)
	{
		
		uploadImg();
	}

	private void uploadImg()
	{
		
		MispUploadImgActivity.jump(this, 1);
	}

	/**
	 * 
	 */
	private void clearCache()
	{
		checkLog.setAbnormalDesp("");
		checkLog.setAbnormalPic("");
		abnormal_desp.setText("");
		loadImag(checkLog.getAbnormalPic());
	}
	@Override
	public void onCheckedChanged(RadioGroup group, int checkedId)
	{
		clearCache();
		switch(checkedId)
		{
			case R.id.radio_btn_normal:
				abnormalView.setVisibility(View.INVISIBLE);
				checkLog.setCheckResult(CheckResultEnum.NORMAL.getIntValue());
				break;
			case R.id.radio_btn_abnormal:
				abnormalView.setVisibility(View.VISIBLE);
				checkLog.setCheckResult(CheckResultEnum.ABNORMAL.getIntValue());

				break;
			case R.id.radio_btn_unset:
				abnormalView.setVisibility(View.INVISIBLE);
				checkLog.setCheckResult(CheckResultEnum.NONE_SET.getIntValue());

				break;
			default:break;
		}
		
		
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data)
	{
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		if(null!=data)
		{
			MispBaseRspJson rsp = (MispBaseRspJson) data.getSerializableExtra(RETURN_DATA);
			if(null!=rsp)
			{
				checkLog.setAbnormalPic((String)rsp.getObj());
				
				loadImag(checkLog.getAbnormalPic());
			}
		}
	
	}
	
	private void loadImag(String picName)
	{
		LoadImageUtil.getInstance().loadImage(abnormal_img, MemoryCache.getImageUrl()+picName);
		
	}


}