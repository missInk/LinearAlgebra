package lxx.linearAlgebra.Dao;

import java.util.List;

import lxx.linearAlgebra.entity.Comment;

public interface CommentDao {
	
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
	
	
	/**
	 * ��ת��ָ��ҳ���������
	 * @param selectPage ѡ���ҳ��
	 * @param pageSize ÿһҳ��ʾ�ĸ���
	 * @param idtopic �����id
	 * @return ����ָ�����۵�list����
	 */
	List<Comment> goPage(int selectPage, int pageSize, int idtopic);
	
	/**
	 * ɾ���ʴ�Ļظ�
	 * @param idtopic �ʴ��id
	 * @return �ɹ�true��ʧ��false
	 */
	boolean delComments(int idtopic);
}
