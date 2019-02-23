package lxx.linearAlgebra.Service;

import java.util.List;

import lxx.linearAlgebra.entity.Comment;
import lxx.linearAlgebra.entity.Topic;

public interface TopicService {

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
	 * @return  返回拼凑好的html代码
	 */
	String goPage(int selectPage, int pageSize);
	
	/**
	 * 通过问题的id来获得问题的内容
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
	 * 上传回复
	 * @param uid 上传的人的id
	 * @param idtopic 回复对应问题的id
	 * @param content 回复的内容
	 * @return 1：上传成功，0：上传失败
	 */
	int upComment(int uid, int idtopic, String content);
}
