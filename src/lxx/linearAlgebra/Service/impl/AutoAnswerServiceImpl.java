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
			// ����ļ��ϴ�ʱentype�Ƿ���ȷ
			boolean isMultipart = ServletFileUpload.isMultipartContent(request);
			if (isMultipart) {
//				FileItemFactory fileItemFactory = new DiskFileItemFactory();
				DiskFileItemFactory fileItemFactory = new DiskFileItemFactory();
				ServletFileUpload upload = new ServletFileUpload(fileItemFactory);
				//�����ϴ��ļ�ʱ�õ��Ļ������Ĵ�С10kb
				fileItemFactory.setSizeThreshold(1024*10);
				//�����ļ����Ϊ20kb
				upload.setFileSizeMax(1024*20);
				List<FileItem> items = upload.parseRequest(request);
				Iterator<FileItem> iterator = items.iterator();
				//�������е������ֶ�
				while(iterator.hasNext()) {
					FileItem item = iterator.next();
					if(item.getFieldName().substring(0, 13).equals("linearAlgebra")) {//����name���Ի�ȡ�ֶ�
						String path = request.getSession().getServletContext().getRealPath("upload");
						Date date = new Date(); 
						SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddhhmmss");
						String fileName = item.getName();//��ȡ�ļ���
						String ext = fileName.substring(fileName.lastIndexOf("."));
						if(!ext.equals(".txt")) {//�ļ�����txt��ֱ�ӷ����ϴ�ʧ��
							upFile.setUpResult("�ļ��ϴ���ʽ����ȷ");
							return upFile;
						}
						fileName = fileName.substring(0, fileName.lastIndexOf("."));
						fileName += dateFormat.format(date) + ext;
						File f = new File(path, fileName);
						item.write(f);
						upFile.getFile().add(f);
						upFile.setUpResult("�ļ��ϴ��ɹ�");
					}
				}
			}
		} catch(FileUploadBase.SizeLimitExceededException e) {
			upFile.setUpResult("�ϴ��ļ���С��������");
			e.printStackTrace();
		} catch (FileUploadException e) {
			upFile.setUpResult("�ļ��ϴ���������");
			e.printStackTrace();
		}catch (Exception e) {
			upFile.setUpResult("�����쳣");
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
