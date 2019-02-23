package lxx.linearAlgebra.Service;

import java.io.File;

import javax.servlet.http.HttpServletRequest;


import lxx.linearAlgebra.entity.UpFile;

public interface AutoAnswerService {
	
	/**
	 * 计算文件内包含的行列式的值
	 * @param file 一个包含行列式的文件
	 * @return 行列式的值
	 */
	double doGetValue(File file);
	
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
	double[][] matrixMultiplication(File a, File b);
	
	/**
	 * 进行矩阵的加法
	 * @param a
	 * @param b
	 * @return
	 */
	double[][] matrixAdd(File a, File b);
	
	/**
	 * 将一个行列式转换为html代码
	 * @param matrix
	 * @return
	 */
	String matrixToHtml(double[][] matrix);
	
	/**
	 * 矩阵的转置
	 * @param file
	 * @return 转置后的矩阵
	 */
	double[][] Transpose(File file);
	
	/**
	 * 求矩阵的逆
	 * @param file
	 * @return
	 */
	double[][] mrinv(File file);
	
	/**
	 * 求矩阵的秩
	 * @param fille
	 * @return
	 */
	int rank(File file);
}
