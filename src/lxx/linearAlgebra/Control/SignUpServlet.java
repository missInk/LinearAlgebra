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
	 * 检查用户想要注册的学号是否已经被注册
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
			response.getWriter().println("{'message':'学号已被注册'}");
		}else {
			response.getWriter().println("{'message':'学号没有被注册'}");
		}
	}
	
	/**
	 * 检查用户想要注册的学号格式是否正确，只有8为数字才算格式正确
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
			response.getWriter().println("{'message':'学号输入不合法'}");
		}else {
			response.getWriter().println("{'message':'学号输入合法'}");
		}
	}
	
	/**
	 * 检查用户想要注册的用户名格式是否正确，只能输入中文,最多8位
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
			response.getWriter().println("{'message':'用户名输入不合法'}");
		}else {
			response.getWriter().println("{'message':'用户名输入合法'}");
		}
	}
	
	/**
	 * 检查用户想要注册的密码格式是否正确，只有第一个字必须为字母，且长度为6~16位，只包含字母和数字
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
			response.getWriter().println("{'message':'密码输入不合法'}");
		}else {
			response.getWriter().println("{'message':'密码输入合法'}");
		}
	}
	
	/**
	 * 检查用户输入的两次密码是否一样
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
			response.getWriter().println("{'message':'两次密码不一致'}");
		}else {
			response.getWriter().println("{'message':'两次密码不致'}");
		}
	}
	
	/**
	 * 检查用户想要注册的邮箱是否已经被注册
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
			response.getWriter().println("{'message':'邮箱已被注册'}");
		}else {
			response.getWriter().println("{'message':'邮箱没有被注册'}");
		}
	}
	
	/**
	 * 检查用户想要注册的邮箱格式是否合法
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
			response.getWriter().println("{'message':'邮箱格式不正确'}");
		}else {
			response.getWriter().println("{'message':'邮箱格式正确'}");
		}
	}
	
	/**
	 * 注册账号
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
			response.getWriter().println("{'message':'系统异常，注册失败'}");
		}
	}
}
