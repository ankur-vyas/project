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
import javax.servlet.http.HttpSession;

public class UpdateUser extends HttpServlet
{
    @Override
    public void doPost(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException
	{
		PrintWriter pw=res.getWriter();
		res.setContentType("text/html");

		String s1=req.getParameter("name");
		String s2=req.getParameter("mobno");
                String s3=req.getParameter("dob");
                String s4=req.getParameter("email");

     try {
            HttpSession session = req.getSession(true);
            String s5 = (String)session.getAttribute("uname");

            Class.forName("com.mysql.jdbc.Driver");
            Connection cn = (Connection)DriverManager.getConnection("jdbc:mysql://localhost:3306/dedupli", "root","root");
            PreparedStatement ps = cn.prepareStatement("update user set name=?, mobno=?, dob=?, email=? where email=?");

                ps.setString(1,s1);
                ps.setString(2,s2);
                ps.setString(3,s3);
                ps.setString(4,s4);
                ps.setString(5,s5);
                ps.execute();
                 pw.println("<script type=\"text/javascript\">");  
                 pw.println("alert('Profile Is Updated..');");
                   pw.println("</script>"); 
          
            
           
             RequestDispatcher rd =  req.getRequestDispatcher("user_home.jsp");
             rd.include(req, res);
         

        } catch (Exception e)
        {
            pw.println("<html><body>"+ e +"</body></html>");
        }
    }
}