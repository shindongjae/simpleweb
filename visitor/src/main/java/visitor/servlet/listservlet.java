package visitor.servlet;

import visitor.dao.*;
import visitor.dto.*;

import java.util.*;
import java.io.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class listservlet
 */

@WebServlet("/visitors")
public class listservlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html; charset=UTF-8");
        visitorDao visitordao=new visitorDao();
        List<Visitor> list=visitordao.getvisitors();
        request.setAttribute("list", list);
        
        RequestDispatcher rd=getServletContext().getRequestDispatcher("/index.jsp");
        rd.forward(request, response);
	}
}
