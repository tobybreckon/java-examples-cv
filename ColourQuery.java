// ********************************************************

// Example : displaying an image from file

// Author : Toby Breckon, toby.breckon@durham.ac.uk

// Copyright (c) 2015 Durham University
// License : LGPL - http://www.gnu.org/licenses/lgpl.html

// ********************************************************

// import required OpenCV components

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.highgui.Highgui;

// ********************************************************

public class ColourQuery {

    public static void main(String[] args) {

        // load the Core OpenCV library by name

        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);

        // load an image from file (read and decode JPEG file)

        Mat inputImage = Highgui.imread("files/lena.jpg");

        // create a display window using an Imshow object

        Imshow ims1 = new Imshow("My Image");
        
        // display some colour values (note the BGR colour channel order)
        
        double[] bgr = inputImage.get(0, 0);
        System.out.println( "colour @ (0,0) = B: " + bgr[0] + " G: " + bgr[1] + " R: " + bgr[2]);

        bgr = inputImage.get(50, 50);
        System.out.println( "colour @ (50,50) = B: " + bgr[0] + " G: " + bgr[1] + " R: " + bgr[2]);
       
        bgr = inputImage.get(100, 25);
        System.out.println( "colour @ (100,25) = B: " + bgr[0] + " G: " + bgr[1] + " R: " + bgr[2]);

        bgr = inputImage.get(17, 234);
        System.out.println( "colour @ (17,234) = B: " + bgr[0] + " G: " + bgr[1] + " R: " + bgr[2]);
        
        // set some pixel values to blue (i.e. BGR = (255,0,0)
        
        double[] colour = new double [3];
        colour[0] = 255; colour[1] = 0; colour[2] = 0;
        inputImage.put(25, 25, colour);
        inputImage.put(25, 24, colour);
        inputImage.put(25, 23, colour);
        inputImage.put(25, 22, colour);
        // ...
        
        // display image

        ims1.showImage(inputImage);

    }
}

// ********************************************************

