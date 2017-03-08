package com;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
public class Logout extends HttpServlet
{
        public void doGet(HttpServletRequest request, HttpServletResponse response)
                                throws ServletException, IOException {
            response.setContentType("text/html");
            PrintWriter pw=response.getWriter();

            request.getRequestDispatcher("index.jsp").include(request, response);

            HttpSession session=request.getSession();
            session.invalidate();

            pw.println("<script type=\"text/javascript\">");  
                 pw.println("alert('Logout  Successfull..');");
                   pw.println("</script>"); 
    

            pw.close();
    }
}