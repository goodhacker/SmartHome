package cn.fuego.smart.home.ui.base;

import java.security.MessageDigest;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;
import cn.fuego.misp.constant.MISPErrorMessageConst;
import cn.fuego.misp.service.http.HttpListener;
import cn.fuego.misp.service.http.MispHttpMessage;
import cn.fuego.misp.ui.base.MispHttpActivtiy;
import cn.fuego.smart.home.service.MemoryCache;

public abstract class BaseActivtiy extends MispHttpActivtiy implements HttpListener
{

	private Context contextDialog ;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		//重载，禁止所有activity竖屏
		super.onCreate(savedInstanceState);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
	}
	public void showMessage(MispHttpMessage message)
	{
		super.showMessage(message.getErrorCode());	
	}
	public void exitDialog(Context context) { 
		contextDialog = context;
		SharedPreUtil.initSharedPreference(context);
        AlertDialog.Builder builder = new Builder(contextDialog);   
        builder.setMessage("确定要退出吗?");   
        builder.setTitle("提示");   
        builder.setPositiveButton("确认",  new android.content.DialogInterface.OnClickListener() {   
            @Override   
            public void onClick(DialogInterface dialog, int which) {   
                dialog.dismiss();   
                //android.os.Process.killProcess(android.os.Process.myPid()); 
                SharedPreUtil.getInstance().DeleteUser();
                ExitApplication.getInstance().exit(contextDialog);
                
            }   
        });   
        builder.setNegativeButton("取消", new android.content.DialogInterface.OnClickListener() {   
            @Override   
            public void onClick(DialogInterface dialog, int which) {   
                dialog.dismiss();   
            }   
        });   
        builder.create().show(); 
        ExitApplication.getInstance().addActivity(this);
    }
	
	// MD5加密，32位
    public  String MD5(String str) {
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
 
        char[] charArray = str.toCharArray();
        byte[] byteArray = new byte[charArray.length];
 
        for (int i = 0; i < charArray.length; i++) {
            byteArray[i] = (byte) charArray[i];
        }
        byte[] md5Bytes = md5.digest(byteArray);
 
        StringBuffer hexValue = new StringBuffer();
        for (int i = 0; i < md5Bytes.length; i++) {
            int val = ((int) md5Bytes[i]) & 0xff;
            if (val < 16) {
                hexValue.append("0");
            }
            hexValue.append(Integer.toHexString(val));
        }
        return hexValue.toString();
    }
    
    public String getTrimText(EditText text)
    {
		String txt =text.getText().toString().trim();
		
    	return txt;
    	
    }
    //用于scrollview 中自适应listview
    public static void setListViewHeightBasedOnChildren(ListView listView)
    {   
        // 获取ListView对应的Adapter   
    	ListAdapter listAdapter = listView.getAdapter();   
        if (listAdapter == null) {   
            return;   
        }   
   
        int totalHeight = 0;   
        for (int i = 0, len = listAdapter.getCount(); i < len; i++) {   
            // listAdapter.getCount()返回数据项的数目   
            View listItem = listAdapter.getView(i, null, listView);  
            if(listItem.getVisibility()!=View.GONE)
            {
                // 计算子项View 的宽高   
                listItem.measure(0, 0);    
                // 统计所有子项的总高度   
                totalHeight += listItem.getMeasuredHeight(); 
            }
   
        }   
   
        ViewGroup.LayoutParams params = listView.getLayoutParams();   
        params.height = totalHeight+ (listView.getDividerHeight() * (listAdapter.getCount() - 1));   
        // listView.getDividerHeight()获取子项间分隔符占用的高度   
        // params.height最后得到整个ListView完整显示需要的高度   
        listView.setLayoutParams(params);   
    }
	@Override
	public void handle(MispHttpMessage message)
	{
		// TODO Auto-generated method stub
		
	}

    public void showToast(Context mContext,String Msg)
    {
    	Toast.makeText(mContext, Msg, Toast.LENGTH_SHORT).show();
    }
    public void showToast(Context mContext,MispHttpMessage message)
    {

	    Toast.makeText(mContext, MISPErrorMessageConst.getMessageByErrorCode(message.getErrorCode()), Toast.LENGTH_SHORT).show();
    }
    /**
     * 如何进程被杀死，则取消登录状态
     */
	@Override
	protected void onDestroy()
	{
		MemoryCache.setLogin(false);
		super.onDestroy();
	}


}
