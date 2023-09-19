package biz;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.MemberDAO;
import vo.MemberVO;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		
		String id, pwd;
		boolean result = false;
		PrintWriter out = response.getWriter();
		
		id = request.getParameter("id");
		pwd = request.getParameter("pwd");
		
		MemberDAO dao = new MemberDAO();
		result = dao.getMemberPwd(id, pwd);
		
		if(result) {
			MemberVO vo = new MemberVO();
			vo.setUserId(id);
			vo.setUserPwd(pwd);
			
			HttpSession session = request.getSession();
			session.setAttribute("loginOK", vo);
			response.sendRedirect("/Jong/Login/loginOK.jsp");
		} else {
			response.sendRedirect("/Jong/Login/loginFail.jsp");
		}
		
		
		
	}

}
