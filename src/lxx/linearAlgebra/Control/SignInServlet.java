package lxx.linearAlgebra.Control;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lxx.linearAlgebra.Service.UserService;
import lxx.linearAlgebra.Service.impl.UserServiceImpl;
import lxx.linearAlgebra.Util.MD5Util;
import lxx.linearAlgebra.entity.User;

@WebServlet("/SignInServlet")
public class SignInServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
       
	/**
	 * ������ݿ����Ƿ���ڴ�ǰ�˴�������ѧ��
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void checkUid(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=UTF-8");
		
		String uid = (String)request.getParameter("uid");
		UserService service = new UserServiceImpl();
		int result = service.chekUid(uid);
		if(result != 1) {
			response.getWriter().println("�Ҳ������û�");
		}
	}
	
	/**
	 * ����û��Ƿ�Ϸ�
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void signIn(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=UTF-8");
		
		String uid = (String)request.getParameter("uid");
		String upwd = MD5Util.makeStringToMD5((String)request.getParameter("upwd"));
		User user = new User();
		user.setUid(Integer.parseInt(uid));
		user.setUpwd(upwd);
		UserService service = new UserServiceImpl();
		int result0 = service.getUserState(uid);
		if(result0 == -1) {
			response.getWriter().println("{'message':'�û�δ�����ǰ�����伤��'}");
			return;
		}else if(result0 == 1) {
			response.getWriter().println("{'message':'�û��Ե�¼'}");
			return;
		}
		int result1 = service.signIn(user);
		if(result1 == 1) {
			response.getWriter().println("{'message':'success'}");
			request.getSession().setAttribute("uname", service.getUnameByUid(uid));
			request.getSession().setAttribute("uid", uid);
		}else if(result1 == 0) {
			response.getWriter().println("{'message':'�û������������'}");
		}else if(result1 == -1) {
			response.getWriter().println("{'message':'ϵͳ����'}");
		}
	}
	
	/**
	 * �û��˳���¼
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public String signOut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getSession().removeAttribute("uanme");
		request.getSession().removeAttribute("uid");
		return "title.jsp";
	}

}
