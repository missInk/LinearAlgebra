package lxx.linearAlgebra.Dao;

import java.util.List;

import lxx.linearAlgebra.entity.Comment;

public interface CommentDao {
	
	/**
	 * 通过问题的id来获得问题的所有评论
	 * @param idtopic
	 * @return
	 */
	List<Comment> getComments(int idtopic);
	
	/**
	 * 上传问题回复
	 * @param comment
	 * @return 1：上传成功，0：上传失败
	 */
	int upComment(Comment comment);
	
	/**
	 * 获取某一问题的回复次数
	 * @param idtopic 查询的问题的id
	 * @return 回复次数
	 */
	int getCommentCount(int idtopic);
	
	
	/**
	 * 跳转到指定页面的评论上
	 * @param selectPage 选择的页数
	 * @param pageSize 每一页显示的个数
	 * @param idtopic 问题的id
	 * @return 包含指定评论的list集合
	 */
	List<Comment> goPage(int selectPage, int pageSize, int idtopic);
	
	/**
	 * 删除问答的回复
	 * @param idtopic 问答的id
	 * @return 成功true，失败false
	 */
	boolean delComments(int idtopic);
}
