package lxx.linearAlgebra.Control;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lxx.linearAlgebra.Service.TopicService;
import lxx.linearAlgebra.Service.impl.TopicServiceImpl;
import lxx.linearAlgebra.entity.Comment;
import lxx.linearAlgebra.entity.Topic;

@WebServlet("/QuestionServlet")
public class QuestionServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * �ύ�������ݿ��У�����ύ�ɹ�����success��ʧ�ܷ���false
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void postQuestions(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=UTF-8");

		String uid = (String) request.getSession().getAttribute("uid");
		String title = (String) request.getParameter("title");
		String content = (String) request.getParameter("content");
		Topic topic = new Topic(title, content, Integer.parseInt(uid));
		TopicService service = new TopicServiceImpl();
		int result = service.postQuestion(topic);
		if (result == 1) {
			response.getWriter().println("{'message':'success'}");
			return;
		} else {
			response.getWriter().println("{'message':'false'}");
			return;
		}

	}

	/**
	 * ��ȡ���ݿ�����������������
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void getQuestionsAmount(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=UTF-8");

		String tableName = (String) request.getParameter("tableName");
		TopicService service = new TopicServiceImpl();
		int amount = service.getAmount(tableName);
		response.getWriter().print(amount);
		request.setAttribute("amount", amount);
	}

	/**
	 * ��ת��ָ������
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void goPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=UTF-8");

		int selectPage = Integer.parseInt((String) request.getParameter("selectPage"));
		int pageSize = Integer.parseInt((String) request.getParameter("pageSize"));
		TopicService service = new TopicServiceImpl();
		String html = service.goPage(selectPage, pageSize);
		response.getWriter().println(html);
	}

	/**
	 * ���ݴ������������ת��ָ�������������
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public String goTopic(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=UTF-8");

		int idtopic = Integer.parseInt((String) request.getParameter("idtopic"));
		TopicService service = new TopicServiceImpl();
		Topic topic = service.getTopic(idtopic);
		List<Comment> comments = service.getComments(idtopic);
		request.setAttribute("topic", topic);
		request.setAttribute("comments", comments);
		return "question.jsp";
	}

	/**
	 * �ϴ��ظ�
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String upComment(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");

		int uid = Integer.parseInt((String) request.getSession().getAttribute("uid"));
		/**
		 * ǰ���趨Ϊpost��Ϊɶ��̨������ʾget
		 */
		String content = request.getParameter("content");
	    byte source [] = content.getBytes("iso8859-1");
	    content = new String (source,"UTF-8");
		
		int idtopic = Integer.parseInt((String) request.getParameter("idtopic"));
		TopicService service = new TopicServiceImpl();
		service.upComment(uid, idtopic, content);
		return "QuestionServlet?method=goTopic&idtopic=" + idtopic;
	}

}
