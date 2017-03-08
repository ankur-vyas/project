/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com;

import java.io.FileInputStream;
import java.security.MessageDigest;

/**
 *
 * @author Mythos-1
 */
public class TestValue
{
    static String hexvalue;   
    public static void main (String args[])
    {
        String input = "D:\\sql.txt";
        MessageDigest md;
        try {
            md = MessageDigest.getInstance("MD5");
       
        FileInputStream fis = new FileInputStream(input);  //c90996ab559ea5bc61d48bdea776422e
                                                            //2bd2b754e59332c533783e82a4af46c0
        byte[] dataBytes = new byte[1024];
     
        int nread = 0;
        while ((nread = fis.read(dataBytes)) != -1) {
          md.update(dataBytes, 0, nread);
        };
        byte[] mdbytes = md.digest();

        //convert the byte to hex format method 1
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < mdbytes.length; i++) {
          sb.append(Integer.toString((mdbytes[i] & 0xff) + 0x100, 16).substring(1));
        }

        hexvalue =  sb.toString();
        System.out.println("Digest(in hex format):: " + sb.toString());
         } catch (Exception ex) {
            ex.printStackTrace();
        }       
    }
}