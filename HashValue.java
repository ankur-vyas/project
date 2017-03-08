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
public class HashValue
{
    static String hexvalue;   
    public String hashvalue(String path,String file)throws Exception
    {
        String input = path+file;
        MessageDigest md = MessageDigest.getInstance("MD5");
        FileInputStream fis = new FileInputStream(input);  //478030946d0b7d918750468715cfd816
//  f53ad53bc6af2f28f31a22e0f2917f6f
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
        return hexvalue;
    }
}