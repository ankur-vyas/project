/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Mythos-1
 */
public class FileUpload extends HttpServlet
{
    PrintWriter pw;
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException
    {
        pw = response.getWriter();
        HttpSession se = request.getSession();
        String user = (String)se.getAttribute("uname");
        System.out.println(user);
              
        String filename = request.getParameter("filename");
        se.setAttribute("filename", filename);

        RequestDispatcher rd = request.getRequestDispatcher("fileUpload1.jsp");
        rd.include(request, response);
    }
}
