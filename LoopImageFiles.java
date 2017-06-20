// ********************************************************

// Example : loop through a set of image files

// there are several ways to do this, but this one is simple 
// to setup

// Author : Toby Breckon, toby.breckon@durham.ac.uk

// Copyright (c) 2015 Durham University
// License : LGPL - http://www.gnu.org/licenses/lgpl.html

// version 0.2

// ********************************************************

// import required OpenCV components

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.highgui.Highgui;

import java.io.File;

// ********************************************************

public class LoopImageFiles {
	
    public static void main(String[] args) throws InterruptedException {

        // define the path to my files - clearly and obviously 
    	
        // **** CHANGE THIS TO YOUR OWN DIRECTORY ****/
        
        String IMAGE_FILES_DIRECTORY_PATH = "files/";
    	
        // load the Core OpenCV library by name

        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
    	
    	 // create a display window using an Imshow object

        Imshow ims = new Imshow("Next Image ...");

        // get a listing of files in that directory
        
        File dir = new File(IMAGE_FILES_DIRECTORY_PATH);
        File[] directoryListing = dir.listFiles();
        if (directoryListing != null) {
            for (File imgFile : directoryListing) {
            	
            	// if the file name ends with .jpg (JPEG) or .png (Portable Network Graphic)

            	if ((imgFile.getName().endsWith(".png")) || (imgFile.getName().endsWith(".jpg")))
            	{
            		// load an image from each file (read and decode image file)

            		Mat inputImage = Highgui.imread(IMAGE_FILES_DIRECTORY_PATH + "/" + imgFile.getName());

            		// *** to any processing on it here*** 
        		
            		// display image with a delay of 500ms (i.e. 1/2 second)

            		ims.showImage(inputImage);
            		Thread.sleep(500);
            	}
            }
          } else {
            
        	  System.out.println( "Could not get listing for directory: " + IMAGE_FILES_DIRECTORY_PATH);
          }
    }
}

// ********************************************************
