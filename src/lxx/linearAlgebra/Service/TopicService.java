package lxx.linearAlgebra.Service;

import java.util.List;

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
	 * @return  包含问题的list集合
	 */
	List<Topic> goPage(int selectPage, int pageSize);
	
	/**
	 * 通过问题的id来获得问题的内容
	 * @param idtopic
	 * @return
	 */
	Topic getTopic(int idtopic);
	
	/**
	 * 更具传入的部分信息获取问题
	 * @param val 传入的部分信息
	 * @return 包含问题的list集合
	 */
	List<Topic> findTopics(String val);
	
	/**
	 * 删除问答
	 * @param idtopic 问答的id
	 * @return 成功true，失败false
	 */
	boolean delTopic(int idtopic);
}
