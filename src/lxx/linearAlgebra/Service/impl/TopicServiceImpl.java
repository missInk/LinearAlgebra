package lxx.linearAlgebra.Service.impl;

import java.sql.Date;
import java.util.List;

import lxx.linearAlgebra.Dao.TopicDao;
import lxx.linearAlgebra.Dao.Impl.TopicDaoImpl;
import lxx.linearAlgebra.Service.TopicService;
import lxx.linearAlgebra.entity.Topic;

public class TopicServiceImpl implements TopicService {

	@Override
	public int postQuestion(Topic topic) {
		Date topic_time = new Date(System.currentTimeMillis());
		topic.setTopic_time(topic_time);
		topic.setComment_count(0);
		topic.setNice_topic(1);
		topic.setStatus(0);
		TopicDao topicDao = new TopicDaoImpl();
		return topicDao.postQuestion(topic);
	}

	@Override
	public int getAmount(String tableName) {
		TopicDao topicDao = new TopicDaoImpl();
		return topicDao.getAmount(tableName);
	}

	@Override
	public List<Topic> goPage(int selectPage, int pageSize) {
		List<Topic> list = null;
		TopicDao topicDao = new TopicDaoImpl();
		list = topicDao.goPage(selectPage, pageSize);
		return list;
	}

	@Override
	public Topic getTopic(int idtopic) {
		TopicDao topicDao = new TopicDaoImpl();
		return topicDao.getTopic(idtopic);
	}

	@Override
	public List<Topic> findTopics(String val) {
		TopicDao topicDao = new TopicDaoImpl();
		return topicDao.findTopics(val);
	}

	@Override
	public boolean delTopic(int idtopic) {
		TopicDao topicDao = new TopicDaoImpl();
		boolean delTopic = topicDao.delTopic(idtopic);
		return delTopic;
	}



}
