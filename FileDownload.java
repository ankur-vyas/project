/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
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
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Mythos-1
 */
public class FileDownload extends HttpServlet
{         
    public static void CombineFiles(String f1,String f2,String f3) throws IOException
    {
            int i,j;
            FileInputStream fin1,fin2;
            FileOutputStream fout;

            try {
                // open input file         
                try
                {
                fin1 = new FileInputStream(f1);
                fin2 = new FileInputStream(f2);
                } catch(FileNotFoundException e) {
                System.out.println("Input File Not Found");
                return;
            }
            // open output file
                try {
                fout = new FileOutputStream(f3);
                } catch(FileNotFoundException e)
                {
                System.out.println("Error Opening Output File");
                return;
                }
            } catch(ArrayIndexOutOfBoundsException e)
            {
                System.out.println("Usage: CopyFile From To");
                return;
            }
            //Copy File
            try
            {
                do {
                i = fin1.read();               
                if(i != -1) fout.write(i);
                } while(i != -1);

                do {
                j = fin2.read();
                if(j != -1) fout.write(j);
                } while(j != -1);
                
            } catch(IOException e)
            {
                System.out.println("File Error");
            }
            fin1.close();
            fin2.close();

            fout.close();
        }
    
     @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException
    {
         String loc1=null,loc2=null,downfilename=null;
         int flid = Integer.parseInt(request.getParameter("fileid"));
          PrintWriter out = response.getWriter();
          String temploc = "d:/temp/download.txt";
          HttpSession session = request.getSession();
          String user  = (String)session.getAttribute("uname");
          
         try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/dedupli","root", "root");
            Statement st = con.createStatement();

            ResultSet rs = st.executeQuery("select * from filedetails where id ="+flid);

            while(rs.next())
            {
                loc1 = rs.getString("location1");
                loc2 = rs.getString("location2");
                downfilename = rs.getString("upfilename");
            }
            
            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                Date date = new Date();
                String ddate = dateFormat.format(date);
                System.out.println(dateFormat.format(date));
            
            PreparedStatement ps = con.prepareStatement("insert into dlog(fileid, user, ddate, filename) values(?,?,?,?)");
            ps.setInt(1,flid);
            ps.setString(4,downfilename);
            ps.setString(2,user);
            ps.setString(3,ddate);                                    
            ps.execute();
            
            FileDownload.CombineFiles(loc1, loc2,temploc);

            String filepath = "d:/temp/";
            
             response.setContentType("APPLICATION/OCTET-STREAM");                // File download code
             response.setHeader("Content-Disposition","attachment; filename=\"" + downfilename + "\"");
                          
             java.io.FileInputStream fileInputStream = new java.io.FileInputStream(temploc);
                          
             int i;
             while ((i=fileInputStream.read()) != -1) 
             {
                   out.write(i);
              }
             fileInputStream.close();
                  out.close();
            
        }catch(Exception e)
         {
            e.printStackTrace();
        }
   }
}
