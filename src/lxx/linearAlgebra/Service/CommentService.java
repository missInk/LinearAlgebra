package lxx.linearAlgebra.Service;

import java.util.List;

import lxx.linearAlgebra.entity.Comment;

public interface CommentService {

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
	
	/**
	 * ��ȡ��Ӧ��ŵ�����Ļظ�����
	 * @param idtopic �ʴ�ı��
	 * @return �ظ��Ĵ���
	 */
	int getCommentCont(int idtopic);
	
	/**
	 * ��ת��ָ��ҳ���������
	 * @param selectPage ѡ���ҳ��
	 * @param pageSize ÿһҳ��ʾ�ĸ���
	 * @param idtopic �����id
	 * @return ����ָ�����۵�list����
	 */
	List<Comment> goPage(int selectPage, int pageSize, int idtopic);
	
	/**
	 * ɾ���ʴ�Ļش�
	 * @param idtopic �ʴ��id
	 * @return �ɹ�true��ʧ��false
	 */
	boolean delComments(int idtopic);
	
}
