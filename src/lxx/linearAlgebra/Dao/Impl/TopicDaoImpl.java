package lxx.linearAlgebra.Dao.Impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import lxx.linearAlgebra.Dao.TopicDao;
import lxx.linearAlgebra.Util.DBUtil;
import lxx.linearAlgebra.entity.Topic;

public class TopicDaoImpl implements TopicDao {

	@Override
	public int postQuestion(Topic topic) {
		String sql = "INSERT INTO `linearalgebra`.`topic` (`title`, `content`, `comment_count`, `status`, `topic_time`, `topics_user_id`, `nice_topic`) VALUES (?, ?, ?, ?, ?, ?, ?)";
		Object[] params = { topic.getTitle(), topic.getContent(), topic.getComment_count(), topic.getStatus(),
				topic.getTopic_time(), topic.getTopics_user_id(), topic.getNice_topic() };
		if (DBUtil.executeUpdate(sql, params)) {
			return 1;
		}
		return 0;
	}

	@Override
	public int getAmount(String tableName) {
		String sql = "SELECT COUNT(*) FROM " + tableName;
		return DBUtil.getTotalCount(sql, null);
	}

	@Override
	public List<Topic> goPage(int selectPage, int pageSize) {
		List<Topic> list = null;
		String sql = "SELECT topic.title, user.uname, topic.status, topic.comment_count, topic.topic_time, topic.idtopic FROM linearalgebra.topic, linearalgebra.user where topic.topics_user_id = user.uid limit ?,?";
		Object[] params = { (selectPage - 1) * pageSize, pageSize };
		list = getTopic(DBUtil.executeQuery(sql, params));
		return list;
	}

	@Override
	public Topic getTopic(int idtopic) {
		Topic topic = new Topic(idtopic);
		String sql = "SELECT * FROM linearalgebra.topic where idtopic = ?";
		Object[] params = { idtopic };
		ResultSet rs = DBUtil.executeQuery(sql, params);
		try {
			if (rs.next()) {
				topic.setTitle(rs.getString("title"));
				topic.setContent(rs.getString("content"));
				topic.setTopic_time(rs.getDate("topic_time"));
			} else {
				throw new RuntimeException("没有找到该问题");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeAll(rs, null, null);
		}
		return topic;
	}

	@Override
	public List<Topic> findTopics(String val) {
		List<Topic> list = null;
		String sql = "SELECT topic.title, user.uname, topic.status, topic.comment_count, topic.topic_time, topic.idtopic FROM linearalgebra.topic, linearalgebra.user where title LIKE '%"
				+ val +"%' and topic.topics_user_id = user.uid";
		Object[] params = {  };
		list = getTopic(DBUtil.executeQuery(sql, params));
		return list;
	}

	/**
	 * 通过返回的结果集来构造一个包含Topic的集合
	 * @param rs 返回的结果
	 * @return 一个包含Topic的集合
	 */
	private List<Topic> getTopic(ResultSet rs){
		List<Topic> list = new ArrayList<Topic>();
		try {
			while (rs.next()) {
				Topic topic = new Topic();
				topic.setTitle(rs.getString(1));
				topic.setUname(rs.getString(2));
				topic.setStatus(rs.getInt(3));
				topic.setComment_count(rs.getInt(4));
				topic.setTopic_time(rs.getDate(5));
				topic.setIdtopic(rs.getInt(6));
				list.add(topic);
			}
		} catch (SQLException e) {
			System.out.println("遍历结果集出错");
			e.printStackTrace();
		} finally {
			DBUtil.closeAll(rs, null, null);
		}
		return list;
	}

	@Override
	public boolean delTopic(int idtopic) {
		String sql = "DELETE FROM `linearalgebra`.`topic` WHERE `idtopic`= ?";
		Object[] params = { idtopic };
		boolean delTopic = DBUtil.executeUpdate(sql, params);
		return delTopic;
	}
	
}
