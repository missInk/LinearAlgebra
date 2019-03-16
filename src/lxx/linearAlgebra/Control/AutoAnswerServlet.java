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

	public void addLine(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=UTF-8");

		AutoAnswerService autoAnswerService = new AutoAnswerServiceImpl();
		UpFile upFile = autoAnswerService.upFile(request);
		if (upFile.getUpResult().equals("文件上传成功")) {
			response.getWriter().print(autoAnswerService.toLine(upFile.getFile().get(0)));
		} else {
			response.getWriter().print(upFile.getUpResult());
		}
	}

	public void getValue(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=UTF-8");

		AutoAnswerService autoAnswerService = new AutoAnswerServiceImpl();
		String line = (String)request.getParameter("line");
		double result = autoAnswerService.doGetValue(line);
		response.getWriter().print(result);
	}

	public void matrixMultiplication(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=UTF-8");

		String lineA = (String)request.getParameter("lineA");
		String lineB = (String)request.getParameter("lineB");
		AutoAnswerService autoAnswerService = new AutoAnswerServiceImpl();
		double[][] result = autoAnswerService.matrixMultiplication(lineA, lineB);
		if (result == null) {
			response.getWriter().print("这两个行列式无法进行乘法运算");
		} else {
			response.getWriter().print(autoAnswerService.toLine(result));
		}
	}

	public void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=UTF-8");

		String lineA = (String)request.getParameter("lineA");
		String lineB = (String)request.getParameter("lineB");
		AutoAnswerService autoAnswerService = new AutoAnswerServiceImpl();
		double[][] result = autoAnswerService.matrixAdd(lineA, lineB);
		if (result == null) {
			response.getWriter().print("这两个行列式无法进行jia法运算");
		} else {
			response.getWriter().print(autoAnswerService.toLine(result));
		}
	}

	public void transpose(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=UTF-8");
		
		String line = (String)request.getParameter("line");
		AutoAnswerService autoAnswerService = new AutoAnswerServiceImpl();
		double[][] matrix = autoAnswerService.Transpose(line);
		response.getWriter().print(autoAnswerService.toLine(matrix));
	}

	public void mrinv(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=UTF-8");

		String line = (String)request.getParameter("line");
		AutoAnswerService autoAnswerService = new AutoAnswerServiceImpl();
		double[][] matrix = autoAnswerService.mrinv(line);
		response.getWriter().print(autoAnswerService.toLine(matrix));
	}

	public void rank(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=UTF-8");

		String line = (String)request.getParameter("line");
		AutoAnswerService autoAnswerService = new AutoAnswerServiceImpl();
		int rank = autoAnswerService.rank(line);
		response.getWriter().print(rank);
	}
	
}
