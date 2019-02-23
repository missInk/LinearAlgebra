package lxx.linearAlgebra.Service.impl;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;

import lxx.linearAlgebra.Dao.TopicDao;
import lxx.linearAlgebra.Dao.Impl.TopicDaoImpl;
import lxx.linearAlgebra.Service.TopicService;
import lxx.linearAlgebra.entity.Comment;
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
	public String goPage(int selectPage, int pageSize) {
		String str0 = "<tr style='height: 30px;line-height: 30px;'><td style='width: 480px;' align='center'><a href='QuestionServlet?method=goTopic&idtopic=";
		String str1 = "'><font style='vertical-align: inherit;'><font style='vertical-align: inherit;'>";
		String str2 = "</font></font></a></td><td style='width: 160px;' align='center'><font style='vertical-align: inherit;'><font style='vertical-align: inherit;'>";
		String str3 = "</font></font></td><td style='width: 60px;' align='center'><font style='vertical-align: inherit;'><font style='vertical-align: inherit;'>";
		String str4 = "</font></font></td><td style='width: 60px;' align='center'><font style='vertical-align: inherit;'><font style='vertical-align: inherit;'>";
		String str5 = "</font></font></td><td style='width: 160px;' align='center'><font style='vertical-align: inherit;'><font style='vertical-align: inherit;'>";
		String str6 = "</font></font></td></tr>";
		String[] htmls = { str0, str1, str2, str3, str4, str5, str6 };
		TopicDao topicDao = new TopicDaoImpl();
		return topicDao.goPage(selectPage, pageSize, htmls);
	}

	@Override
	public Topic getTopic(int idtopic) {
		TopicDao topicDao = new TopicDaoImpl();
		return topicDao.getTopic(idtopic);
	}

	@Override
	public List<Comment> getComments(int idtopic) {
		TopicDao topicDao = new TopicDaoImpl();
		return topicDao.getComments(idtopic);
	}

	
	@Override
	public int upComment(int uid, int idtopic, String content) {
		Comment comment = new Comment(content, uid, idtopic);
		TopicDao topicDao = new TopicDaoImpl();
		int floor = topicDao.getCommentCount(idtopic) + 1;
		Date time = new Date(System.currentTimeMillis());
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String comment_time = dateFormat.format(time);
		comment.setComment_time(comment_time);
		comment.setFloor(floor);
		comment.setStatus(1);
		int result = topicDao.upComment(comment);
		return result;
	}

}
