package lxx.linearAlgebra.Service;

import java.io.File;

import javax.servlet.http.HttpServletRequest;


import lxx.linearAlgebra.entity.UpFile;

public interface AutoAnswerService {
	
	/**
	 * 将一个文件内的值转变为矩阵
	 * @param file
	 * @return
	 */
	String toLine(File file);
	
	/**
	 * 将一个行列式转换为字符串
	 * @param matrix
	 * @return
	 */
	String toLine(double[][] matrix);
	
	/**
	 * 计算文件内包含的行列式的值
	 * @return 行列式的值
	 */
	double doGetValue(String line);
	
	/**
	 * 将request中的文件写道服务器中
	 * @return 文件上传后，保存在服务器内的文件对象以及文件上传的结果 
	 */
	UpFile upFile(HttpServletRequest request);

	/**
	 * 进行矩阵的乘法
	 * @param a
	 * @param b
	 * @return
	 */
	double[][] matrixMultiplication(String LineA, String LineB);
	
	/**
	 * 进行矩阵的加法
	 * @param a
	 * @param b
	 * @return
	 */
	double[][] matrixAdd(String LineA, String LineB);
	
	/**
	 * 将一个行列式转换为html代码
	 * @param matrix
	 * @return
	 */
	String matrixToHtml(double[][] matrix);
	
	/**
	 * 矩阵的转置
	 * @return 转置后的矩阵
	 */
	double[][] Transpose(String line);
	
	/**
	 * 求矩阵的逆
	 * @return
	 */
	double[][] mrinv(String line);
	
	/**
	 * 求矩阵的秩
	 * @param fille
	 * @return
	 */
	int rank(String line);
}
