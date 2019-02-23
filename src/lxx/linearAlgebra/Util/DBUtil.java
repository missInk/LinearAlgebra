package lxx.linearAlgebra.Util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBUtil {

	public static PreparedStatement ps = null;
	public static ResultSet rs = null;
	public static Connection connection = null;
	
	/**
	 * ����һ�����ݿ�����
	 */
	public static Connection getConnByProperties() {
		Connection connection = null;
		try {
			Class.forName(PropertiesUtil.getDriverProperties("MySQL"));
			connection = DriverManager.getConnection(PropertiesUtil.getUrlProperties("MySQL"),
					PropertiesUtil.getUsernameProperties("MySQL"), PropertiesUtil.getPasswordProperties("MySQL"));
		} catch (ClassNotFoundException e) {
			System.out.println("����������ʧ��");
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return connection;
	}

	/**
	 * ͨ����ɾ��
	 * @param sql sql���
	 * @param params sql����в�����ֵ
	 * @return �����ɹ�����true��ʧ�ܷ���false
	 */
	public static boolean executeUpdate(String sql, Object[] params) {
		try {
			connection = getConnByProperties();
			ps = creatPreparedStatement(sql, params);
			int count = ps.executeUpdate();
			if (count > 0) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeAll(rs, ps, connection);
		}
		return false;
	}
	
	/**
	 * ��ȡ����õ�PreparedStatement����
	 * @param sql
	 * @param params
	 * @return
	 * @throws SQLException
	 */
	private static PreparedStatement creatPreparedStatement(String sql, Object[] params) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(sql);
		if(params != null) {
			for (int i = 0; i < params.length; i++) {
				ps.setObject(i + 1, params[i]);
			}
		}
		return ps;
	}
	
	/**
	 * ��ѯ����
	 * @param sql
	 * @param params
	 * @return
	 */
	public static int getTotalCount(String sql, Object[] params) {
		int count = -1;
		try {
			connection = getConnByProperties();
			ps = creatPreparedStatement(sql, params);
			rs = ps.executeQuery();
			if(rs.next()) {
				count = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			closeAll(rs, ps, connection);
		}
		return count;
	}
	
	/**
	 * ͨ�ò�
	 * @param sql sql���
	 * @param params sql����в�����ֵ
	 * @return ���ҳ����Ľ����
	 */
	public static ResultSet executeQuery(String sql, Object[] params) {
		try {
			connection = getConnByProperties();
			ps = creatPreparedStatement(sql, params);
			rs = ps.executeQuery();
			return rs;
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rs;
	}
	
	/**
	 * �ر���������
	 * @param rs
	 * @param stmt
	 * @param connection
	 */
	public static void closeAll(ResultSet rs, Statement stmt, Connection connection) {
		try {
			if (rs != null) {
				rs.close();
			}
			if (stmt != null) {
				stmt.close();
			}
			if (connection != null) {
				connection.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}
