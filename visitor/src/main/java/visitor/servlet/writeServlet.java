package visitor.servlet;

import visitor.dao.*;
import visitor.dto.*;


import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/visitors/write")
public class writeServlet extends HttpServlet {
		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String name=request.getParameter("name");
		String content=request.getParameter("content");
		
		Visitor visitor=new Visitor(name, content);
		visitorDao visitorDao= new visitorDao();
		visitorDao.addvisitor(visitor);
		
		response.sendRedirect("http://localhost:8080/visitor/visitors");
	}

}
