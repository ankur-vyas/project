/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com;

import java.io.*;
import java.util.Scanner;

public class SplitFile
{
    static String f1=null,f2=null;
    public void split(String Path,String fname)
    {       
        try{
        // Reading file and getting no. of files to be generated

            System.out.println("FileName in SplitFile : "+fname);
            String name = fname;
            int dot = name.lastIndexOf('.');
            String filebase = (dot == -1) ? name : name.substring(0, dot);
            System.out.println("FileBase in SplitFile : "+filebase);

        String inputfile = Path+fname; //  Source File Name.
  
            File file = new File(inputfile);
              Scanner scanner = new Scanner(file);
              int count = 0;
              while (scanner.hasNextLine())
              {
               scanner.nextLine();
               count++;
              }
              System.out.println("Lines in the file: " + count);     // Displays no. of lines in the input file.
              double nol = count/2;                //  No. of lines to be split and saved in each output file.
              
              int nof=2;
              
              System.out.println("No. of files to be generated :"+nof); // Displays no. of files to be generated.

  //----------------------------------------------------------------------------------------
  // Actual splitting of file into smaller files

          FileInputStream fstream = new FileInputStream(inputfile);
          DataInputStream in = new DataInputStream(fstream);

          BufferedReader br = new BufferedReader(new InputStreamReader(in)); 
          String strLine;

          for (int j=1;j<=nof;j++)
          {
              FileWriter fstream1;
           if(j==1)
           {
             f1 = "D:/DeD/part1/"+filebase+j+".txt";
            fstream1 = new FileWriter(f1);     // Destination File Location
           }                                                    //+j+".txt"
           else
           {
              f2 = "D:/DeD/part2/"+filebase+j+".txt";
            fstream1 = new FileWriter(f2);     // Destination File Location
           }
           

           BufferedWriter out = new BufferedWriter(fstream1);
           for (int i=1;i<=nol;i++)
           {
            strLine = br.readLine();
            if (strLine!= null)
            {
             out.write(strLine);
             if(i!=nol)
             {
              out.newLine();
             }
            }
           }
           out.close();
//           fstream.close();
//           fstream1.close();
          }

          in.close();
         }catch (Exception e)
         {
          System.err.println("Error: " + e.getMessage());
         }
     }

}