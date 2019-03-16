package lxx.linearAlgebra.Dao.Impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import lxx.linearAlgebra.Dao.UserDao;
import lxx.linearAlgebra.Util.DBUtil;
import lxx.linearAlgebra.entity.User;

public class UserDaoImpl implements UserDao {

	@Override
	public int chekUid(String uid) {
		String sql = "SELECT * FROM linearalgebra.user where uid = ?";
		Object[] params = { uid };
		ResultSet rs = DBUtil.executeQuery(sql, params);
		try {
			if (rs.next()) {
				return 1;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
		} finally {
			DBUtil.closeAll(rs, null, null);
		}
		return 0;
	}

	@Override
	public int signIn(User user) {
		String sql = "SELECT * FROM linearalgebra.user where uid=? and upwd=?";
		Object[] params = { user.getUid(), user.getUpwd() };
		ResultSet rs = DBUtil.executeQuery(sql, params);
		try {
			if (rs.next()) {
				return 1;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
		} finally {
			DBUtil.closeAll(rs, null, null);
		}
		return 0;
	}

	@Override
	public int chekUemail(String uemail) {
		String sql = "SELECT * FROM linearalgebra.user where uemail = ?";
		Object[] params = { uemail };
		ResultSet rs = DBUtil.executeQuery(sql, params);
		try {
			if (rs.next()) {
				return 1;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
		}finally {
			DBUtil.closeAll(rs, DBUtil.ps, DBUtil.connection);
		}
		return 0;
	}

	@Override
	public int signUp(User user) {
		String sql = "INSERT INTO `linearalgebra`.`user` (`uid`, `uname`, `upwd`, `uemail`, `code`, `upermissions`, `state`, `registerTime`) VALUES (?, ?, ?, ?, ?, '”√ªß', '-1', ?);";
		Object[] params = { user.getUid(), user.getUname(), user.getUpwd(), user.getUemail(), user.getCode(), user.getRegisterTime() };
		if (DBUtil.executeUpdate(sql, params)) {
			return 1;
		}
		return 0;
	}

	@Override
	public boolean activeUser(String code) {
		String sql = "UPDATE `linearalgebra`.`user` SET `state`='0' WHERE `code`=?";
		Object[] params = { code };
		if (DBUtil.executeUpdate(sql, params)) {
			return true;
		}
		return false;
	}

	@Override
	public int checkState(String uname) {
		int result = 0;
		String sql = "SELECT state FROM linearalgebra.user WHERE `uname`=?";
		Object[] params = { uname };
		ResultSet rs = DBUtil.executeQuery(sql, params);
		try {
			if(rs.next()) {
				result = rs.getInt("state");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBUtil.closeAll(rs, DBUtil.ps, DBUtil.connection);
		}
		return result;
	}

	@Override
	public String getUnameByCode(String code) {
		String uname = null;
		String sql = "SELECT uname FROM linearalgebra.user WHERE `code`=?";
		Object[] params = { code };
		ResultSet rs = DBUtil.executeQuery(sql, params);
		try {
			if(rs.next()) {
				uname = rs.getString("uname");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBUtil.closeAll(rs, DBUtil.ps, DBUtil.connection);
		}
		return uname;
	}

	@Override
	public String getUnameByUid(String uid) {
		String uname = null;
		String sql = "SELECT uname FROM linearalgebra.user WHERE `uid`=?";
		Object[] params = { uid };
		ResultSet rs = DBUtil.executeQuery(sql, params);
		try {
			if(rs.next()) {
				uname = rs.getString("uname");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBUtil.closeAll(rs, DBUtil.ps, DBUtil.connection);
		}
		return uname;
	}

	@Override
	public int getUserState(String uid) {
		int ustate = 1;
		String sql = "SELECT state FROM linearalgebra.user WHERE `uid`=?";
		Object[] params = { uid };
		ResultSet rs = DBUtil.executeQuery(sql, params);
		try {
			if(rs.next()) {
				ustate = rs.getInt("state");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBUtil.closeAll(rs, DBUtil.ps, DBUtil.connection);
		}
		return ustate;
	}

}
