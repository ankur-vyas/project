/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Mythos-1
 */
public class RequestFIle extends HttpServlet {
   

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        String recoverfile=null;

        PrintWriter out = response.getWriter();
        String fpath = "D:/DeD Backup/";

        String fileid = request.getParameter("fileid");

        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/dedupli","root", "root");
            Statement st = con.createStatement();

            ResultSet rs = st.executeQuery("select * from filedetails where id ="+fileid);

            while(rs.next())
            {                
                recoverfile = rs.getString("upfilename");
            }

            SplitFile sf = new SplitFile();
            sf.split(fpath,recoverfile);

            Statement st1 = con.createStatement();
            st1.execute("delete from requests where fileid ="+fileid);


            RequestDispatcher rd = request.getRequestDispatcher("admin_home.jsp");
            rd.include(request, response);
            out.println("<script type=\"text/javascript\">");  
                 out.println("alert('File Recover....');");
                   out.println("</script>"); 

        }catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}