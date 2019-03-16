package lxx.linearAlgebra.Service.impl;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadBase;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import lxx.linearAlgebra.Service.AutoAnswerService;
import lxx.linearAlgebra.Util.LineAlgebraUtil;
import lxx.linearAlgebra.entity.UpFile;

public class AutoAnswerServiceImpl implements AutoAnswerService {

	/**
	 * 将String类型的字符串转换为矩阵
	 * @param line
	 * @return 一个矩阵
	 */
	private double[][] getLine(String line){
		String[] lines = line.split("\n");
		int row = lines.length - 1;
		int col = LineAlgebraUtil.getCol(lines[1]);
		StringBuilder builder = new StringBuilder();
		for(int i = 0; i < row; i++) {
			builder.append(lines[i+1]);
		}
		String value = builder.toString();
		return LineAlgebraUtil.toLineAlgebra(value, row, col);
	}
	
	@Override
	public double doGetValue(String line) {
		String[] lines = line.split("\n");
		int row = lines.length - 1;
		double[][] p = getLine(line);
		return LineAlgebraUtil.getValue(p, row);
	}

	@Override
	public UpFile upFile(HttpServletRequest request) {
		UpFile upFile = new UpFile();
		try {
			// 检查文件上传时entype是否正确
			boolean isMultipart = ServletFileUpload.isMultipartContent(request);
			if (isMultipart) {
//				FileItemFactory fileItemFactory = new DiskFileItemFactory();
				DiskFileItemFactory fileItemFactory = new DiskFileItemFactory();
				ServletFileUpload upload = new ServletFileUpload(fileItemFactory);
				//设置上传文件时用到的缓冲区的大小10kb
				fileItemFactory.setSizeThreshold(1024*10);
				//设置文件最大为20kb
				upload.setFileSizeMax(1024*20);
				List<FileItem> items = upload.parseRequest(request);
				Iterator<FileItem> iterator = items.iterator();
				//遍历表单中的所有字段
				upFile.setUpResult("文件上传为空");
				while(iterator.hasNext()) {
					FileItem item = iterator.next();
					if(item.getFieldName().substring(0, 13).equals("linearAlgebra")) {//更具name属性获取字段
						String path = request.getSession().getServletContext().getRealPath("upload");
						Date date = new Date(); 
						SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddhhmmss");
						String fileName = item.getName();//获取文件名
						String ext = fileName.substring(fileName.lastIndexOf("."));
						if(!ext.equals(".txt")) {//文件不是txt，直接返回上传失败
							upFile.setUpResult("文件上传格式不正确");
							return upFile;
						}
						fileName = fileName.substring(0, fileName.lastIndexOf("."));
						fileName += dateFormat.format(date) + ext;
						File f = new File(path, fileName);
						item.write(f);
						upFile.getFile().add(f);
						upFile.setUpResult("文件上传成功");
					}
				}
			}
		} catch(FileUploadBase.SizeLimitExceededException e) {
			upFile.setUpResult("上传文件大小超出限制");
			e.printStackTrace();
		} catch (FileUploadException e) {
			upFile.setUpResult("文件上传出现问题");
			e.printStackTrace();
		}catch (Exception e) {
			upFile.setUpResult("其他异常");
			e.printStackTrace();
		}
		return upFile;
	}

	
	@Override
	public double[][] matrixMultiplication(String LineA, String LineB) {
		double[][] matrixA = getLine(LineA);
		double[][] matrixB = getLine(LineB);
		return LineAlgebraUtil.matrixMultiplication(matrixA, matrixB);
	}

	@Override
	public double[][] matrixAdd(String LineA, String LineB) {
		double[][] matrixA = getLine(LineA);
		double[][] matrixB = getLine(LineB);
		return LineAlgebraUtil.matrixAdd(matrixA, matrixB);
	}

	@Override
	public String matrixToHtml(double[][] matrix) {
		StringBuilder html = new StringBuilder();
		html.append("<table border='1' cellpadding='3'>");
		for(int i = 0; i < matrix.length; i++) {
			html.append("<tr>");
			for(int j = 0; j < matrix[0].length; j++) {
				html.append("<td>" + matrix[i][j] + "</td>");
			}
			html.append("</tr>");
		}
		html.append("</table>");
		return html.toString();
	}

	@Override
	public double[][] Transpose(String line) {
		double[][] matrix = getLine(line);
		return LineAlgebraUtil.Transpose(matrix, matrix.length, matrix[0].length);
	}

	@Override
	public double[][] mrinv(String line) {
		double[][] matrix = getLine(line);
		LineAlgebraUtil.Mrinv(matrix, matrix.length);
		return matrix;
	}

	@Override
	public int rank(String line) {
		double[][] matrix = getLine(line);
		return LineAlgebraUtil.Rank(matrix, -1, matrix.length);
	}

	@Override
	public String toLine(File file) {
		StringBuilder line = new StringBuilder();
		int row = LineAlgebraUtil.getRow(file);
		int col = LineAlgebraUtil.getCol(file, row);
		double[][] value = LineAlgebraUtil.toLineAlgebra(file);
		for(int i = 0; i < row; i++) {
			for(int j = 0; j < col; j++) {
				line.append(Double.toString(value[i][j])+" ");
			}
			line.append("\n");
		}
		return line.toString();
	}

	@Override
	public String toLine(double[][] matrix) {
		StringBuilder line = new StringBuilder();
		int row = matrix.length;
		int col = matrix[0].length;
		for(int i = 0; i < row; i++) {
			for(int j = 0; j < col; j++) {
				line.append(Double.toString(matrix[i][j])+" ");
			}
			line.append("\n");
		}
		return line.toString();
	}

}
