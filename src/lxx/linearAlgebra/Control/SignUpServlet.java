package lxx.linearAlgebra.Control;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lxx.linearAlgebra.Service.UserService;
import lxx.linearAlgebra.Service.impl.UserServiceImpl;
import lxx.linearAlgebra.entity.User;

@WebServlet("/SignUpServlet")
public class SignUpServlet  extends BaseServlet  {
	private static final long serialVersionUID = 1L;

	/**
	 * ����û���Ҫע���ѧ���Ƿ��Ѿ���ע��
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void checkUidRegistered(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=UTF-8");
		
		String uname = (String)request.getParameter("uid");
		UserService service = new UserServiceImpl();
		if(service.chekUid(uname) == 1) {
			response.getWriter().println("{'message':'ѧ���ѱ�ע��'}");
		}else {
			response.getWriter().println("{'message':'ѧ��û�б�ע��'}");
		}
	}
	
	/**
	 * ����û���Ҫע���ѧ�Ÿ�ʽ�Ƿ���ȷ��ֻ��8Ϊ���ֲ����ʽ��ȷ
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void checkUidStyle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=UTF-8");
		
		String uname = (String)request.getParameter("uid");
		if(!uname.matches("^\\d{8}$")) {
			response.getWriter().println("{'message':'ѧ�����벻�Ϸ�'}");
		}else {
			response.getWriter().println("{'message':'ѧ������Ϸ�'}");
		}
	}
	
	/**
	 * ����û���Ҫע����û�����ʽ�Ƿ���ȷ��ֻ����������,���8λ
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void checkUnameStyle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=UTF-8");
		
		String uname = (String)request.getParameter("uname");
		if(!uname.matches("^[\\u4e00-\\u9fa5]{0,8}$")) {
			response.getWriter().println("{'message':'�û������벻�Ϸ�'}");
		}else {
			response.getWriter().println("{'message':'�û�������Ϸ�'}");
		}
	}
	
	/**
	 * ����û���Ҫע��������ʽ�Ƿ���ȷ��ֻ�е�һ���ֱ���Ϊ��ĸ���ҳ���Ϊ6~16λ��ֻ������ĸ������
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void checkUpwdStyle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=UTF-8");
		
		String upwd = (String)request.getParameter("upwd");
		if(!upwd.matches("^[a-zA-Z]\\w{5,15}$")) {
			response.getWriter().println("{'message':'�������벻�Ϸ�'}");
		}else {
			response.getWriter().println("{'message':'��������Ϸ�'}");
		}
	}
	
	/**
	 * ����û���������������Ƿ�һ��
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void checkTwoUpwdSame(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=UTF-8");
		
		String upwd = (String)request.getParameter("upwd");
		String upwdTwo = (String)request.getParameter("upwdTwo");
		if(!upwdTwo.equals(upwd)) {
			response.getWriter().println("{'message':'�������벻һ��'}");
		}else {
			response.getWriter().println("{'message':'�������벻��'}");
		}
	}
	
	/**
	 * ����û���Ҫע��������Ƿ��Ѿ���ע��
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void checkUemailRegistered(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=UTF-8");
		
		String uemail = (String)request.getParameter("uemail");
		UserService service = new UserServiceImpl();
		if(service.chekUemail(uemail) == 1) {
			response.getWriter().println("{'message':'�����ѱ�ע��'}");
		}else {
			response.getWriter().println("{'message':'����û�б�ע��'}");
		}
	}
	
	/**
	 * ����û���Ҫע��������ʽ�Ƿ�Ϸ�
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void checkUemailStyle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=UTF-8");
		
		String uemail = (String)request.getParameter("uemail");
		if(!uemail.matches("^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$")) {
			response.getWriter().println("{'message':'�����ʽ����ȷ'}");
		}else {
			response.getWriter().println("{'message':'�����ʽ��ȷ'}");
		}
	}
	
	/**
	 * ע���˺�
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void signUp(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=UTF-8");
		
		String uname = (String)request.getParameter("uname");
		String uemail = (String)request.getParameter("uemail");
		String upwd = (String)request.getParameter("upwd");
		String uid = (String)request.getParameter("uid");
		User user = new User(uname, upwd, uemail);
		user.setUid(Integer.valueOf(uid));
		UserService service = new UserServiceImpl();
		if(service.signUp(user) == 1) {
			response.getWriter().println("{'message':'success'}");
		}else {
			response.getWriter().println("{'message':'ϵͳ�쳣��ע��ʧ��'}");
		}
	}
}
