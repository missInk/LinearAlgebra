package lxx.linearAlgebra.Dao;

import java.util.List;

import lxx.linearAlgebra.entity.Comment;
import lxx.linearAlgebra.entity.Topic;

public interface TopicDao {

	/**
	 * 上传问题
	 * 1：成功，2：失败
	 */
	int postQuestion(Topic topic);
	
	/**
	 * 获取表包含的数据条数
	 * @param tableName 表的名称
	 * @return 数据条数
	 */
	int getAmount(String tableName);
	
	/**
	 *跳转到指定页数
	 * @param selectPage 选择的页面页数
	 * @param pageSize 页面的大小
	 * @param htmls 未拼凑的html代码
	 * @return  返回拼凑好的html代码
	 */
	String goPage(int selectPage, int pageSize, String[] htmls);
	
	/**
	 * 通过问题的id来获得问题的标题和内容
	 * @param idtopic
	 * @return
	 */
	Topic getTopic(int idtopic);
	
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
}
