package com;

import com.mysql.jdbc.Connection;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UpdateUserPass extends HttpServlet
{
    @Override
    public void doPost(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException
	{
		PrintWriter pw=res.getWriter();
		res.setContentType("text/html");

		String username = req.getParameter("uname");
		String pass = req.getParameter("pass");

     try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection cn = (Connection)DriverManager.getConnection("jdbc:mysql://localhost:3306/dedupli", "root","root");
            PreparedStatement ps = cn.prepareStatement("update user set password=? where email=?");

                ps.setString(1,pass);
                ps.setString(2,username);

              if(ps.execute())
            {
             RequestDispatcher rd =  req.getRequestDispatcher("changePassUser.jsp");
             rd.include(req, res);
             pw.println("<html><body>Please try again..!</body></html>");
            }
            else
            {
                 pw.println("<script type=\"text/javascript\">");  
                 pw.println("alert('Username And Password Is Updated');");
                   pw.println("</script>"); 
          
      
           
              RequestDispatcher rd =  req.getRequestDispatcher("user_home.jsp");
             rd.include(req, res);
       ;
            }
        } catch (Exception e)
        {
            pw.println("<html><body>"+ e +"</body></html>");
        }
    }
}