package lxx.linearAlgebra.Dao;

import java.util.List;

import lxx.linearAlgebra.entity.Topic;

public interface TopicDao {

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
	 * @return ���������List����
	 */
	List<Topic> goPage(int selectPage, int pageSize);
	
	/**
	 * ͨ�������id���������ı��������
	 * @param idtopic
	 * @return
	 */
	Topic getTopic(int idtopic);
	
	/**
	 * ���ߴ���Ĳ�����Ϣ��ȡ����
	 * @param val ����Ĳ�����Ϣ
	 * @return ���������list����
	 */
	List<Topic> findTopics(String val);
	
	/**
	 * ɾ���ʴ�
	 * @param idtopic �ʴ��id
	 * @return �ɹ�true��ʧ��false
	 */
	boolean delTopic(int idtopic);
	
}
