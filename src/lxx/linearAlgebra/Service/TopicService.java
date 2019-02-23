package lxx.linearAlgebra.Service;

import java.util.List;

import lxx.linearAlgebra.entity.Comment;
import lxx.linearAlgebra.entity.Topic;

public interface TopicService {

	/**
	 * �ϴ�����
	 * 1���ɹ���2��ʧ��
	 */
	int postQuestion(Topic topic);
	
	/**
	 * ��ȡ���������������
	 * @param tableName �������
	 * @return ��������
	 */
	int getAmount(String tableName);
	
	/**
	 *��ת��ָ��ҳ��
	 * @param selectPage ѡ���ҳ��ҳ��
	 * @param pageSize ҳ��Ĵ�С
	 * @return  ����ƴ�պõ�html����
	 */
	String goPage(int selectPage, int pageSize);
	
	/**
	 * ͨ�������id��������������
	 * @param idtopic
	 * @return
	 */
	Topic getTopic(int idtopic);
	
	/**
	 * ͨ�������id������������������
	 * @param idtopic
	 * @return
	 */
	List<Comment> getComments(int idtopic);
	
	/**
	 * �ϴ��ظ�
	 * @param uid �ϴ����˵�id
	 * @param idtopic �ظ���Ӧ�����id
	 * @param content �ظ�������
	 * @return 1���ϴ��ɹ���0���ϴ�ʧ��
	 */
	int upComment(int uid, int idtopic, String content);
}
