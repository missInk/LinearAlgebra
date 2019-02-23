package lxx.linearAlgebra.Dao;

import java.util.List;

import lxx.linearAlgebra.entity.Comment;
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
	 * @param htmls δƴ�յ�html����
	 * @return  ����ƴ�պõ�html����
	 */
	String goPage(int selectPage, int pageSize, String[] htmls);
	
	/**
	 * ͨ�������id���������ı��������
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
	 * �ϴ�����ظ�
	 * @param comment
	 * @return 1���ϴ��ɹ���0���ϴ�ʧ��
	 */
	int upComment(Comment comment);
	
	/**
	 * ��ȡĳһ����Ļظ�����
	 * @param idtopic ��ѯ�������id
	 * @return �ظ�����
	 */
	int getCommentCount(int idtopic);
}
