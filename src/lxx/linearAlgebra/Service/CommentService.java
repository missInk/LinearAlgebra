package lxx.linearAlgebra.Service;

import java.util.List;

import lxx.linearAlgebra.entity.Comment;

public interface CommentService {

	/**
	 * 通过问题的id来获得问题的所有评论
	 * @param idtopic
	 * @return
	 */
	List<Comment> getComments(int idtopic);
	
	/**
	 * 上传回复
	 * @param uid 上传的人的id
	 * @param idtopic 回复对应问题的id
	 * @param content 回复的内容
	 * @return 1：上传成功，0：上传失败
	 */
	int upComment(int uid, int idtopic, String content);
	
	/**
	 * 获取对应编号的问题的回复次数
	 * @param idtopic 问答的编号
	 * @return 回复的次数
	 */
	int getCommentCont(int idtopic);
	
	/**
	 * 跳转到指定页面的评论上
	 * @param selectPage 选择的页数
	 * @param pageSize 每一页显示的个数
	 * @param idtopic 问题的id
	 * @return 包含指定评论的list集合
	 */
	List<Comment> goPage(int selectPage, int pageSize, int idtopic);
	
	/**
	 * 删除问答的回答
	 * @param idtopic 问答的id
	 * @return 成功true，失败false
	 */
	boolean delComments(int idtopic);
	
}
