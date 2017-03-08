package com;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AdminLogin extends HttpServlet
{
    @Override
    public void doPost(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException
	{
		PrintWriter pw=res.getWriter();
		res.setContentType("text/html");

		String s1=req.getParameter("uname");
		String s2=req.getParameter("pass");

     try {
    Class.forName("com.mysql.jdbc.Driver");
    Connection cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/dedupli", "root","root");
    PreparedStatement ps = cn.prepareStatement("select * from admin where username=? and password=?");

                ps.setString(1,s1);
                ps.setString(2,s2);
                ResultSet rs =ps.executeQuery();
                int i = 0;
                while(rs.next())
                {
                    i++;
                }
            if(i>0)
            {
             HttpSession session = req.getSession();
             session.setAttribute("uname",s1);
             RequestDispatcher rd =  req.getRequestDispatcher("admin_home.jsp");
             rd.include(req, res);
            }
            else
            {
                     pw.println("<script type=\"text/javascript\">");  
                 pw.println("alert('Please Enter Correct Username And Password');");
                   pw.println("</script>"); 
           
                RequestDispatcher rd =  req.getRequestDispatcher("adminlogin.jsp");
                rd.include(req, res);
            }
        } catch (Exception e)
        {
            pw.println("<html><body>"+ e +"</body></html>");
        }
    }
}