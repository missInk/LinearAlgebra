package lxx.linearAlgebra.Control;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lxx.linearAlgebra.Service.UserService;
import lxx.linearAlgebra.Service.impl.UserServiceImpl;

@WebServlet("/ActiveServlet")
public class ActiveServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	
	public String active(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=UTF-8");
		
		String code = (String)request.getParameter("code");
		UserService service = new UserServiceImpl();
		if(service.active(code) == 1) {
			return "activeSuccess.jsp";
		}else if(service.active(code) == 0) {
			return "activeFalse.jsp";
		}
		return "activeRepeat.jsp";
	}
	
}
