package com.healist;

import java.io.File;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        File[] hiddenFiles = new File(".").listFiles(File::isHidden);
    }
}
