package lxx.linearAlgebra.Service;

import lxx.linearAlgebra.entity.User;

public interface UserService {

	/**
	 * 检查用户是否存在
	 * @param uid 用户输入的学号
	 * @return -1系统错误	0没有找到	1找到了
	 */
	int chekUid(String uid);
	
	/**
	 * 检查邮箱是否已经被注册是否存在
	 * @param uemail 用户输入的邮箱
	 * @return -1系统错误	0没有找到	1找到了
	 */
	int chekUemail(String uemail);
	
	/**
	 * 检查用户是否合法
	 * @param user 被检查的用户，必须包括学号和密码
	 * @return -1系统错误	0不合法	1合法
	 */
	int signIn(User user);
	
	/**
	 * 注册新用户
	 * @param user 新注册的用户
	 * @return -1系统错误	0注册失败	1注册成功
	 */
	int signUp(User user);
	
	/**
	 * 激活用户
	 * @param code 用户的激活码，用来对应唯一用户
	 * @return 成功1，失败0,已激活-1
	 */
	int active(String code);
	
	/**
	 * 通过uid来获取用户名信息
	 * @param uid 用户的学号
	 * @return 用户的用户名
	 */
	String getUnameByUid(String uid);
	
	/**
	 * 获取用户状态
	 * @param uid 用户账号
	 * @return 用户状态，1以登录，0未登录，-1未激活
	 */
	int getUserState(String uid);
	
}
