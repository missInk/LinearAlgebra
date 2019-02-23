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

	@Override
	public double doGetValue(File file) {
		return LineAlgebraUtil.getValue(LineAlgebraUtil.toLineAlgebra(file), LineAlgebraUtil.getRow(file));
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
	public double[][] matrixMultiplication(File a, File b) {
		double[][] matrixA = LineAlgebraUtil.toLineAlgebra(a);
		double[][] matrixB = LineAlgebraUtil.toLineAlgebra(b);
		return LineAlgebraUtil.matrixMultiplication(matrixA, matrixB);
	}

	@Override
	public double[][] matrixAdd(File a, File b) {
		double[][] matrixA = LineAlgebraUtil.toLineAlgebra(a);
		double[][] matrixB = LineAlgebraUtil.toLineAlgebra(b);
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
	public double[][] Transpose(File file) {
		double[][] matrix = LineAlgebraUtil.toLineAlgebra(file);
		return LineAlgebraUtil.Transpose(matrix, matrix.length, matrix[0].length);
	}

	@Override
	public double[][] mrinv(File file) {
		double[][] matrix = LineAlgebraUtil.toLineAlgebra(file);
		LineAlgebraUtil.Mrinv(matrix, matrix.length);
		return matrix;
	}

	@Override
	public int rank(File file) {
		double[][] matrix = LineAlgebraUtil.toLineAlgebra(file);
		return LineAlgebraUtil.Rank(matrix, -1, matrix.length);
	}

}
