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
			throw new RuntimeException("û�д���method�������޷�ȷ����Ҫ���õķ���");
		}
		Class<? extends BaseServlet> c = this.getClass();
		Method method = null;
		try {
			method = c.getMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);
		} catch (Exception e) {
			throw new RuntimeException("��Ҫ���õķ���"+methodName+"������");
		} 
		try {
			String result = (String)method.invoke(this, request, response);
			/*
			 * ���ݷ����ķ���ֵ��������ת���ض���
			 * û�з���ֵ�򷵻�ֵΪ�գ���������κδ���
			 * �鿴����ֵ�Ƿ����ð�ţ����û�б�ʾת��
			 * �еĻ�����ð��ǰ���ǡ�r����R�����ʾ�ض���
			 * ��ð��ǰ���ǡ�f����F�����ʾת��
			 * ð�ź���Ϊ�ض������ת���ĵ�ַ
			 * ��������������׳��쳣
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
					throw new RuntimeException("��ָ���Ĳ����޷����");
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
