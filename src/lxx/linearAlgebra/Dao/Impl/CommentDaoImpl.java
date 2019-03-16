package lxx.linearAlgebra.Dao.Impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import lxx.linearAlgebra.Dao.CommentDao;
import lxx.linearAlgebra.Util.DBUtil;
import lxx.linearAlgebra.entity.Comment;

public class CommentDaoImpl implements CommentDao {

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

	@Override
	public List<Comment> goPage(int selectPage, int pageSize, int idtopic) {
		List<Comment> list = new ArrayList<Comment>();
		String sql = "SELECT comment.content, user.uname, comment.floor, comment.comment_time FROM linearalgebra.comment, linearalgebra.user where comment_topic_id = ? and uid = comment_user_id limit ?,?";
		Object[] params = { idtopic, (selectPage - 1) * pageSize, pageSize };
		ResultSet rs = DBUtil.executeQuery(sql, params);
		try {
			while (rs.next()) {
				Comment comment = new Comment();
				comment.setContent(rs.getString(1));
				comment.setUname(rs.getString(2));
				comment.setFloor(rs.getInt(3));
				comment.setComment_time(rs.getString(4));
				list.add(comment);
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
	public boolean delComments(int idtopic) {
		String sql = "DELETE FROM `linearalgebra`.`comment` WHERE `comment_topic_id`= ?";
		Object[] params = { idtopic };
		boolean delComment = DBUtil.executeUpdate(sql, params);
		return delComment;
	}
	
}
