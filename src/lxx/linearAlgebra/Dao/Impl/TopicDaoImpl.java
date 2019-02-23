package lxx.linearAlgebra.Dao.Impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import lxx.linearAlgebra.Dao.TopicDao;
import lxx.linearAlgebra.Util.DBUtil;
import lxx.linearAlgebra.entity.Comment;
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
	public String goPage(int selectPage, int pageSize, String[] htmls) {
		StringBuilder html = new StringBuilder();
		String sql = "SELECT topic.title, user.uname, topic.status, topic.comment_count, topic.topic_time, topic.idtopic FROM linearalgebra.topic, linearalgebra.user where topic.topics_user_id = user.uid limit ?,?";
		Object[] params = { (selectPage - 1) * pageSize, pageSize };
		ResultSet rs = DBUtil.executeQuery(sql, params);
		try {
			while (rs.next()) {
				String title = rs.getString(1);
				String uname = rs.getString(2);
				String status = rs.getString(3);
				String comment_count = rs.getString(4);
				String topic_time = rs.getString(5);
				String idtopic = rs.getString(6);
				html.append(htmls[0]);
				html.append(idtopic);
				html.append(htmls[1]);
				html.append(title);
				html.append(htmls[2]);
				html.append(uname);
				html.append(htmls[3]);
				html.append(status);
				html.append(htmls[4]);
				html.append(comment_count);
				html.append(htmls[5]);
				html.append(topic_time);
				html.append(htmls[6]);
			}
		} catch (SQLException e) {
			System.out.println("遍历结果集出错");
			e.printStackTrace();
		} finally {
			DBUtil.closeAll(rs, null, null);
		}
		return html.toString();
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
	public List<Comment> getComments(int idtopic) {
		List<Comment> comments = new ArrayList<Comment>();
		String sql = "SELECT * FROM linearalgebra.comment where comment_topic_id = ?";
		Object[] params = { idtopic };
		ResultSet rs = DBUtil.executeQuery(sql, params);
		try {
			while (rs.next()) {
				int idcomment = rs.getInt("idcomment");
				int comment_user_id = rs.getInt("comment_user_id");
				String content = rs.getString("content");
				int floor = rs.getInt("floor");
				String comment_time = rs.getString("comment_time");
				int status = rs.getInt("status");
				comments.add(new Comment(idcomment, content, floor, comment_time, comment_user_id, idtopic, status));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeAll(rs, null, null);
		}
		return comments;
	}

	@Override
	public int getCommentCount(int idtopic) {
		String sql = "SELECT COUNT(*) FROM linearalgebra.comment where comment_topic_id = ?";
		Object[] params = { idtopic };
		return DBUtil.getTotalCount(sql, params);
	}

	@Override
	public int upComment(Comment comment) {
		String sql = "INSERT INTO `linearalgebra`.`comment` (`content`, `floor`, `comment_time`, `comment_user_id`, `comment_topic_id`, `status`) VALUES (?, ?, ?, ?, ?, '1')";
		Object[] params = { comment.getContent(), comment.getFloor(), comment.getComment_time(), comment.getComment_user_id(), comment.getComment_topic_id() };
		if(DBUtil.executeUpdate(sql, params)) {
			return 1;
		}else {
			return 0;
		}
	}

}
