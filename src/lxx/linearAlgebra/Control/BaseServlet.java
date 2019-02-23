package lxx.linearAlgebra.Control;

import java.io.IOException;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class BaseServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;

	@Override
	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String methodName = request.getParameter("method");
		if(methodName == null || methodName.trim().isEmpty()) {
			throw new RuntimeException("没有传递method参数，无法确定想要调用的方法");
		}
		Class<? extends BaseServlet> c = this.getClass();
		Method method = null;
		try {
			method = c.getMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);
		} catch (Exception e) {
			throw new RuntimeException("你要调用的方法"+methodName+"不存在");
		} 
		try {
			String result = (String)method.invoke(this, request, response);
			/*
			 * 根据方法的返回值来进行跳转或重定向
			 * 没有返回值或返回值为空，则程序不做任何处理
			 * 查看返回值是否包含冒号，如果没有表示转发
			 * 有的话，若冒号前面是‘r’或‘R’则表示重定向
			 * 若冒号前面是‘f’或‘F’则表示转发
			 * 冒号后面为重定向或者转发的地址
			 * 出现其他情况则抛出异常
			 */
			if(result == null || result.trim().isEmpty()) {
				return;
			}
			if(result.contains(":")) {
				int index = result.indexOf(":");
				String e = result.substring(0, index);
				String path = result.substring(index+1);
				if(e.equalsIgnoreCase("r")) {
					response.sendRedirect(request.getContextPath() + path);
				}else if(e.equalsIgnoreCase("f")) {
					request.getRequestDispatcher(path).forward(request, response);
				}else {
					throw new RuntimeException("你指定的操作无法完成");
				}
			}else {
				request.getRequestDispatcher(result).forward(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} 
	}
}
