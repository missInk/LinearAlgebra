package lxx.linearAlgebra.Service;

import java.io.File;

import javax.servlet.http.HttpServletRequest;


import lxx.linearAlgebra.entity.UpFile;

public interface AutoAnswerService {
	
	/**
	 * ��һ���ļ��ڵ�ֵת��Ϊ����
	 * @param file
	 * @return
	 */
	String toLine(File file);
	
	/**
	 * ��һ������ʽת��Ϊ�ַ���
	 * @param matrix
	 * @return
	 */
	String toLine(double[][] matrix);
	
	/**
	 * �����ļ��ڰ���������ʽ��ֵ
	 * @return ����ʽ��ֵ
	 */
	double doGetValue(String line);
	
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
	double[][] matrixMultiplication(String LineA, String LineB);
	
	/**
	 * ���о���ļӷ�
	 * @param a
	 * @param b
	 * @return
	 */
	double[][] matrixAdd(String LineA, String LineB);
	
	/**
	 * ��һ������ʽת��Ϊhtml����
	 * @param matrix
	 * @return
	 */
	String matrixToHtml(double[][] matrix);
	
	/**
	 * �����ת��
	 * @return ת�ú�ľ���
	 */
	double[][] Transpose(String line);
	
	/**
	 * ��������
	 * @return
	 */
	double[][] mrinv(String line);
	
	/**
	 * ��������
	 * @param fille
	 * @return
	 */
	int rank(String line);
}
