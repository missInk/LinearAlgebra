package lxx.linearAlgebra.Service;

import java.io.File;

import javax.servlet.http.HttpServletRequest;


import lxx.linearAlgebra.entity.UpFile;

public interface AutoAnswerService {
	
	/**
	 * �����ļ��ڰ���������ʽ��ֵ
	 * @param file һ����������ʽ���ļ�
	 * @return ����ʽ��ֵ
	 */
	double doGetValue(File file);
	
	/**
	 * ��request�е��ļ�д����������
	 * @return �ļ��ϴ��󣬱����ڷ������ڵ��ļ������Լ��ļ��ϴ��Ľ�� 
	 */
	UpFile upFile(HttpServletRequest request);

	/**
	 * ���о���ĳ˷�
	 * @param a
	 * @param b
	 * @return
	 */
	double[][] matrixMultiplication(File a, File b);
	
	/**
	 * ���о���ļӷ�
	 * @param a
	 * @param b
	 * @return
	 */
	double[][] matrixAdd(File a, File b);
	
	/**
	 * ��һ������ʽת��Ϊhtml����
	 * @param matrix
	 * @return
	 */
	String matrixToHtml(double[][] matrix);
	
	/**
	 * �����ת��
	 * @param file
	 * @return ת�ú�ľ���
	 */
	double[][] Transpose(File file);
	
	/**
	 * ��������
	 * @param file
	 * @return
	 */
	double[][] mrinv(File file);
	
	/**
	 * ��������
	 * @param fille
	 * @return
	 */
	int rank(File file);
}
