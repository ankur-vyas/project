/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.Part;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Mythos-1
 */
public class FileUpload1 extends HttpServlet {

    String filename, temp, upfname;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String upload = "yes";
        String hxv = null;
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        HttpSession ses = request.getSession();
        String fname = (String) ses.getAttribute("filename");
        String user = (String) ses.getAttribute("uname");

        MultipartRequest m = new MultipartRequest(request, "d:/temp/");

        Enumeration files = m.getFileNames();
        while (files.hasMoreElements()) {
            temp = (String) files.nextElement();
            filename = m.getFilesystemName(temp);
        }
        upfname = filename;

        String s = "d:/temp/";

        try {
            HashValue hs = new HashValue();
            hxv = hs.hashvalue(s, upfname);
            System.out.println("Hex Value : " + hxv);

            Class.forName("com.mysql.jdbc.Driver");
            Connection cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/dedupli", "root", "root");
            Statement st = cn.createStatement();

            ResultSet rs = st.executeQuery("select * from filedetails");
            while (rs.next()) {
                String dbhex = rs.getString("hexvalue");
                String fn = rs.getString("filename");

                if (dbhex.compareTo(hxv) == 0) {
                    upload = "no";
                }
                if (fn.compareTo(fname) == 0) {
                    upload = "no";
                    System.out.println("Upload no");
                }
                
            }

            if (upload.compareTo("yes") == 0) {
                String temp = s + upfname;
                String Backupfile = "d:/DeD Backup/";

                File f1 = new File(temp);                       // create back up copy
                File f2 = new File(Backupfile + upfname);
                BackupCopy bc = new BackupCopy();
                try {
                    bc.copyFile(f1, f2);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                SplitFile sf = new SplitFile();                // spliting file
                sf.split(s, upfname);

                String temp1 = "d:\\temp\\" + upfname;
                File f = new File(temp1);                    // deleting file
                f.deleteOnExit();

                DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                Date date = new Date();
                String upfdate = dateFormat.format(date);
                System.out.println(dateFormat.format(date));

                String loc1 = SplitFile.f1;
                String loc2 = SplitFile.f2;
                PreparedStatement ps = cn.prepareStatement("insert into filedetails(filename,user,hexvalue,upfilename,location1,location2,upfdate) values(?,?,?,?,?,?,?)");
                ps.setString(1, fname);
                ps.setString(2, user);
                ps.setString(3, hxv);
                ps.setString(4, upfname);
                ps.setString(5, loc1);
                ps.setString(6, loc2);
                ps.setString(7, upfdate);
                ps.execute();

                 out.println("<script type=\"text/javascript\">");  
                 out.println("alert('File Upload Successfully.....');");
                   out.println("</script>"); 
                RequestDispatcher rd = request.getRequestDispatcher("fileUpload1.jsp");
                rd.include(request, response);
            } else {
                out.println("<script type=\"text/javascript\">");  
                 out.println("alert('File is already upload...');");
                   out.println("</script>"); 
                File f = new File(s + upfname);
                f.delete();
                f.deleteOnExit();
                response.setContentType("text/html");

//                Cookie[] cookies = request.getCookies();
//                if(cookies!=null)
//                for (int i = 0; i < cookies.length; i++)
//                {
//                 cookies[i].setMaxAge(0);
//                }
                RequestDispatcher rd = request.getRequestDispatcher("fileUpload1.jsp");
                rd.include(request, response);
            }

        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }
}
