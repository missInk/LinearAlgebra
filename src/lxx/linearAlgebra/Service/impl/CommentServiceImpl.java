package lxx.linearAlgebra.Service.impl;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;

import lxx.linearAlgebra.Dao.CommentDao;
import lxx.linearAlgebra.Dao.Impl.CommentDaoImpl;
import lxx.linearAlgebra.Service.CommentService;
import lxx.linearAlgebra.entity.Comment;

public class CommentServiceImpl implements CommentService {

	@Override
	public List<Comment> getComments(int idtopic) {
		CommentDao commentDao = new CommentDaoImpl();
		return commentDao.getComments(idtopic);
	}

	
	@Override
	public int upComment(int uid, int idtopic, String content) {
		Comment comment = new Comment(content, uid, idtopic);
		CommentDao commentDao = new CommentDaoImpl();
		int floor = commentDao.getCommentCount(idtopic) + 1;
		Date time = new Date(System.currentTimeMillis());
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String comment_time = dateFormat.format(time);
		comment.setComment_time(comment_time);
		comment.setFloor(floor);
		comment.setStatus(1);
		int result = commentDao.upComment(comment);
		return result;
	}


	@Override
	public List<Comment> goPage(int selectPage, int pageSize, int idtopic) {
		List<Comment> list = null;
		CommentDao commentDao = new CommentDaoImpl();
		list = commentDao.goPage(selectPage, pageSize, idtopic);
		return list;
	}


	@Override
	public int getCommentCont(int idtopic) {
		CommentDao commentDao = new CommentDaoImpl();
		return commentDao.getCommentCount(idtopic);
	}


	@Override
	public boolean delComments(int idtopic) {
		CommentDao commentDao = new CommentDaoImpl();
		boolean delComment = commentDao.delComments(idtopic);
		return delComment;
	}

}
