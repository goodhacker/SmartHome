/**   
* @Title: ErrorMessageConst.java 
* @Package cn.fuego.smart.home.constant 
* @Description: TODO
* @author Tang Jun   
* @date 2014-10-24 下午10:42:34 
* @version V1.0   
*/ 
package cn.fuego.misp.constant;

import cn.fuego.misp.dao.file.MispMessageReader;

 /** 
 * @ClassName: ErrorMessageConst 
 * @Description: TODO
 * @author Tang Jun
 * @date 2014-10-24 下午10:42:34 
 *  
 */
public class MISPErrorMessageConst
{
	public static final int SUCCESS = 0;  //操作成功
 
	public static final int ERROR_MSG_WRONG = 1; //消息错误
	
	public static final int OPERATE_FAILED = 2 ; // 

	public static final int ERROR_USER_NOT_EXISTED = 3; //用户不存在
	public static final int ERROR_LOGIN_FAILED = 4;  //登录失败
	public static final int ERROR_OLD_PASSWORD_WORD = 5; //原始密码错误
	
	public static final int CLIENT_VERSION_LOW = 6 ; // 

	public static final int USER_EXISTED = 7;//用户已存在

	public static final int RESULT_NULL = 8;//搜索结果为空
	

	public static String getMessageByErrorCode(int errorCode)
	{
		return MispMessageReader.getInstance().getPropertyByName(String.valueOf(errorCode));
	}
	
}
