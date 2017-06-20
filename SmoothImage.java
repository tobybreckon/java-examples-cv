// ********************************************************

// Example : displaying an image from file

// Author : Toby Breckon, toby.breckon@durham.ac.uk

// Copyright (c) 2015 Durham University
// License : LGPL - http://www.gnu.org/licenses/lgpl.html

// ********************************************************

// import required OpenCV components

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;
import org.opencv.highgui.Highgui;

// ********************************************************

public class SmoothImage { 
	
	public static void main(String[] args) {

    // load the Core OpenCV library by name

    System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
	
	
    // create a display window using an Imshow object

    Imshow window1 = new Imshow("My Image");

    // load an image from file (read and decode JPEG file)

    Mat inputImg = Highgui.imread("files/lena.jpg");
    
    // create an output object
    
    Mat outputImg = new Mat();
    
    // smooth the image 
    
    Size filter = new Size(5,5);
    Imgproc.GaussianBlur(inputImg, outputImg, filter, 0, 0, Imgproc.BORDER_DEFAULT); 
       
     // display image

     window1.showImage(outputImg);

    }
}

// ********************************************************

