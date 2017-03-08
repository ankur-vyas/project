package com;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class UserRequest extends HttpServlet
{
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException
    {
        String fname=null;
        HttpSession ses = request.getSession();
        String user = (String) ses.getAttribute("uname");
        int fid =Integer.parseInt(request.getParameter("fid"));
        PrintWriter pw = response.getWriter();

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	Date date = new Date();
        String reqtime = dateFormat.format(date);
	System.out.println(reqtime);

        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/dedupli","root", "root");
            Statement st = con.createStatement();

            ResultSet rs = st.executeQuery("select * from filedetails where id ="+fid);

            while(rs.next())
            {
                fname = rs.getString("upfilename");
            }
        }catch(Exception e)
        {
            e.printStackTrace();
        }

        try{
    Class.forName("com.mysql.jdbc.Driver");
    Connection cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/dedupli", "root","root");
    PreparedStatement ps = cn.prepareStatement("insert into requests(user, fileid, filename, reqtime) values(?,?,?,?)");

                    ps.setString(1,user);
                    ps.setInt(2,fid);
                    ps.setString(3,fname);
                    ps.setString(4,reqtime);

                    ps.execute();
                
                 RequestDispatcher rd =  request.getRequestDispatcher("user_home.jsp");
                 rd.include(request, response);

        }catch(Exception e)
        {
            pw.println("<html><body>"+ e +"</body></html>");
        }
    }
}