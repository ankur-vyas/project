/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 *
 * @author Mythos-1
 */
public class BackupCopy
{   
    public static void copyFile(File oldLocation, File newLocation) throws IOException
    {
        if ( oldLocation.exists( ))
        {
            FileInputStream fi = new FileInputStream(oldLocation);
            FileOutputStream fo = new FileOutputStream(newLocation, false);
            BufferedInputStream  reader = new BufferedInputStream( fi );
            BufferedOutputStream  writer = new BufferedOutputStream(fo);
            try{
                byte[]  buff = new byte[8192];
                int numChars;
                while ( (numChars = reader.read(  buff, 0, buff.length ) ) != -1)
                {
                    writer.write( buff, 0, numChars );
                }
            } catch( IOException ex )
            {
                throw new IOException("IOException when transferring " + oldLocation.getPath() + " to " + newLocation.getPath());
            } finally {
                try {
                    if ( reader != null ){
                        writer.close();
                        reader.close();
                    }
                } catch( IOException ex ){
                    ex.printStackTrace();
                }
            }
        } 
        else
        {
          throw new IOException("Old location does not exist when transferring " + oldLocation.getPath() + " to " + newLocation.getPath() );
        }
    }
}
