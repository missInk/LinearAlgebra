package lxx.linearAlgebra.Service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;

import lxx.linearAlgebra.Dao.UserDao;
import lxx.linearAlgebra.Dao.Impl.UserDaoImpl;
import lxx.linearAlgebra.Service.UserService;
import lxx.linearAlgebra.Util.CodeUtil;
import lxx.linearAlgebra.Util.MD5Util;
import lxx.linearAlgebra.Util.MailUtil;
import lxx.linearAlgebra.entity.User;

public class UserServiceImpl implements UserService {

	@Override
	public int chekUid(String uid) {
		UserDao userDao = new UserDaoImpl();
		return userDao.chekUid(uid);
	}

	@Override
	public int signIn(User user) {
		UserDao userDao = new UserDaoImpl();
		return userDao.signIn(user);
	}

	@Override
	public int chekUemail(String uemail) {
		UserDao userDao = new UserDaoImpl();
		return userDao.chekUemail(uemail);
	}

	@Override
	public int signUp(User user) {
		String code = CodeUtil.generateUniqueCode();
		UserDao userDao = new UserDaoImpl();
		Date date = new Date(System.currentTimeMillis());
		SimpleDateFormat dateFormat = new SimpleDateFormat("YYYY-MM-DD hh:mm:ss");
		String registerTime = dateFormat.format(date);
		user.setRegisterTime(registerTime);
		user.setUpwd(MD5Util.makeStringToMD5(user.getUpwd()));
		user.setCode(code);
		int result = userDao.signUp(user);
		if (result == 1) {
			new Thread(new MailUtil(user.getUemail(), code)).start();
		}
		return result;
	}

	@Override
	public int active(String code) {
		UserDao userDao = new UserDaoImpl();
		String uname = userDao.getUnameByCode(code);
		if(uname == null) {
			return 0;
		}else if (userDao.checkState(uname) == -1 && userDao.activeUser(code)) {
			return 1;
		}
		return -1;
	}

	@Override
	public String getUnameByUid(String uid) {
		UserDao userDao = new UserDaoImpl();
		return userDao.getUnameByUid(uid);
	}

	@Override
	public int getUserState(String uid) {
		UserDao userDao = new UserDaoImpl();
		return userDao.getUserState(uid);
	}

}
