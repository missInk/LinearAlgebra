package lxx.linearAlgebra.Control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lxx.linearAlgebra.Service.AutoAnswerService;
import lxx.linearAlgebra.Service.impl.AutoAnswerServiceImpl;
import lxx.linearAlgebra.entity.UpFile;

@WebServlet("/AutoAnswerServlet")
public class AutoAnswerServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;

	public String goPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=UTF-8");

		String pageName = (String) request.getParameter("pageName");
		return "automaticAnswer/" + pageName;
	}

	public void getValue(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=UTF-8");

		AutoAnswerService autoAnswerService = new AutoAnswerServiceImpl();
		UpFile upFile = autoAnswerService.upFile(request);
		if (upFile.getUpResult().equals("�ļ��ϴ��ɹ�")) {
			double result = autoAnswerService.doGetValue(upFile.getFile().get(0));
			response.getWriter().print("������ʽ��ֵΪ" + result);
		} else {
			response.getWriter().print(upFile.getUpResult());
		}
	}

	public void matrixMultiplication(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=UTF-8");

		AutoAnswerService autoAnswerService = new AutoAnswerServiceImpl();
		UpFile upFile = autoAnswerService.upFile(request);
		if (upFile.getUpResult().equals("�ļ��ϴ��ɹ�")) {
			double[][] result = autoAnswerService.matrixMultiplication(upFile.getFile().get(0),
					upFile.getFile().get(1));
			if (result == null) {
				response.getWriter().print("����������ʽ�޷����г˷�����");
			} else {
				response.getWriter().print(autoAnswerService.matrixToHtml(result));
			}
		} else {
			response.getWriter().print(upFile.getUpResult());
		}
	}

	public void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=UTF-8");

		AutoAnswerService autoAnswerService = new AutoAnswerServiceImpl();
		UpFile upFile = autoAnswerService.upFile(request);
		if (upFile.getUpResult().equals("�ļ��ϴ��ɹ�")) {
			double[][] result = autoAnswerService.matrixAdd(upFile.getFile().get(0), upFile.getFile().get(1));
			if (result == null) {
				response.getWriter().print("����������ʽ�޷����мӷ�����");
			} else {
				response.getWriter().print(autoAnswerService.matrixToHtml(result));
			}
		} else {
			response.getWriter().print(upFile.getUpResult());
		}
	}

	public String transpose_jsp(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=UTF-8");
		return "automaticAnswer/transpose.jsp";
	}

	public void transpose(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=UTF-8");

		AutoAnswerService autoAnswerService = new AutoAnswerServiceImpl();
		UpFile upFile = autoAnswerService.upFile(request);
		if (upFile.getUpResult().equals("�ļ��ϴ��ɹ�")) {
			double[][] result = autoAnswerService.Transpose(upFile.getFile().get(0));
			response.getWriter().print(autoAnswerService.matrixToHtml(result));
		} else {
			response.getWriter().print(upFile.getUpResult());
		}
	}

	public void mrinv(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=UTF-8");

		AutoAnswerService autoAnswerService = new AutoAnswerServiceImpl();
		UpFile upFile = autoAnswerService.upFile(request);
		if (upFile.getUpResult().equals("�ļ��ϴ��ɹ�")) {
			double[][] result = autoAnswerService.mrinv(upFile.getFile().get(0));
			response.getWriter().print(autoAnswerService.matrixToHtml(result));
		} else {
			response.getWriter().print(upFile.getUpResult());
		}
	}

	public void rank(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=UTF-8");

		AutoAnswerService autoAnswerService = new AutoAnswerServiceImpl();
		UpFile upFile = autoAnswerService.upFile(request);
		if (upFile.getUpResult().equals("�ļ��ϴ��ɹ�")) {
			int result = autoAnswerService.rank(upFile.getFile().get(0));
			response.getWriter().print("������ʽ����Ϊ" + result);
		} else {
			response.getWriter().print(upFile.getUpResult());
		}
	}
	
}
