package lxx.linearAlgebra.Control;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lxx.linearAlgebra.Service.CommentService;
import lxx.linearAlgebra.Service.TopicService;
import lxx.linearAlgebra.Service.impl.CommentServiceImpl;
import lxx.linearAlgebra.Service.impl.TopicServiceImpl;
import lxx.linearAlgebra.entity.Comment;
import lxx.linearAlgebra.entity.Topic;

@WebServlet("/BackMangerServlet")
public class BackMangerServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	
	public String goTopicPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=UTF-8");
		
		int pageSize = Integer.parseInt((String)request.getParameter("pageSize"));
		int selectPage = Integer.parseInt((String)request.getParameter("selectPage"));
		TopicService service = new TopicServiceImpl();
		List<Topic> list = service.goPage(selectPage, pageSize);
		request.setAttribute("pageSize", pageSize);
		request.setAttribute("list", list);
		return "manger/questionMangerForm.jsp";
	}
	
	public String goComment(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=UTF-8");
		
		int pageSize = Integer.parseInt((String)request.getParameter("pageSize"));
		int selectPage = Integer.parseInt((String)request.getParameter("selectPage"));
		int idtopic = Integer.parseInt((String)request.getParameter("idtopic"));
		CommentService service = new CommentServiceImpl();
		List<Comment> list = service.goPage(selectPage, pageSize, idtopic);
		request.setAttribute("list", list);
		return "manger/commentMangerForm.jsp";
	}
	
	public void goCommentCont(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=UTF-8");
		
		int idtopic = Integer.parseInt((String)request.getParameter("idtopic"));
		CommentService service = new CommentServiceImpl();
		int commentCont = service.getCommentCont(idtopic);
		response.getWriter().print(commentCont);
	}
	
	public String findTopic(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=UTF-8");
		
		String val = request.getParameter("val");
		TopicService service = new TopicServiceImpl();
		List<Topic> list = service.findTopics(val);
		request.setAttribute("list", list);
		return "manger/questionMangerForm.jsp";
	}

	public void delTopic(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=UTF-8");
		
		int idtopic = Integer.parseInt((String)request.getParameter("idtopic"));
		TopicService topicService = new TopicServiceImpl();
		CommentService commentService = new CommentServiceImpl();
		if(topicService.delTopic(idtopic)) {
			if(commentService.getCommentCont(idtopic) <= 0){
				response.getWriter().println(true);
				return;
			}
			if(commentService.delComments(idtopic)) {
				response.getWriter().println(true);
				return;
			}
		}
		response.getWriter().println(false);
	}
	
}
