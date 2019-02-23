package lxx.linearAlgebra.Service;

import lxx.linearAlgebra.entity.User;

public interface UserService {

	/**
	 * ����û��Ƿ����
	 * @param uid �û������ѧ��
	 * @return -1ϵͳ����	0û���ҵ�	1�ҵ���
	 */
	int chekUid(String uid);
	
	/**
	 * ��������Ƿ��Ѿ���ע���Ƿ����
	 * @param uemail �û����������
	 * @return -1ϵͳ����	0û���ҵ�	1�ҵ���
	 */
	int chekUemail(String uemail);
	
	/**
	 * ����û��Ƿ�Ϸ�
	 * @param user �������û����������ѧ�ź�����
	 * @return -1ϵͳ����	0���Ϸ�	1�Ϸ�
	 */
	int signIn(User user);
	
	/**
	 * ע�����û�
	 * @param user ��ע����û�
	 * @return -1ϵͳ����	0ע��ʧ��	1ע��ɹ�
	 */
	int signUp(User user);
	
	/**
	 * �����û�
	 * @param code �û��ļ����룬������ӦΨһ�û�
	 * @return �ɹ�1��ʧ��0,�Ѽ���-1
	 */
	int active(String code);
	
	/**
	 * ͨ��uid����ȡ�û�����Ϣ
	 * @param uid �û���ѧ��
	 * @return �û����û���
	 */
	String getUnameByUid(String uid);
	
	/**
	 * ��ȡ�û�״̬
	 * @param uid �û��˺�
	 * @return �û�״̬��1�Ե�¼��0δ��¼��-1δ����
	 */
	int getUserState(String uid);
	
}
