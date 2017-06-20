// ********************************************************

// Example : computer the difference between a pair of images
// and display them

// Author : Toby Breckon, toby.breckon@durham.ac.uk

// Copyright (c) 2015 Durham University
// License : LGPL - http://www.gnu.org/licenses/lgpl.html

// ********************************************************

// import required OpenCV components

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.opencv.highgui.Highgui;
import org.opencv.imgproc.Imgproc;

// ********************************************************

public class ImageDiff {

    public static void main(String[] args) throws InterruptedException {

        // load the Core OpenCV library by name

        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);

        // load two image files
        
        Mat img1 = Highgui.imread("files/toycars1.png");
        Mat img2 = Highgui.imread("files/toycars2.png");
        
        // create a new image object to store image difference
        
        Mat diff_img = new Mat();
        
        
        // compute the difference between the images
        
        Core.absdiff(img1, img2, diff_img);
       
        // now convert it to grey and threshold it
        
        // Q. 2
        
        //Mat grey = new Mat();
        // Imgproc.cvtColor(diff_img, grey, Imgproc.COLOR_BGR2GRAY);
       
        // Imgproc.adaptiveThreshold(grey, diff_img, 255, Imgproc.ADAPTIVE_THRESH_MEAN_C, 
        //							Imgproc.THRESH_BINARY_INV, 15, 7);
        
        // now clean it up using some morphological operations
        
        // Q. 3
        
         // Size ksize = new Size(5,5);
         // Mat kernel =  Imgproc.getStructuringElement(Imgproc.MORPH_ELLIPSE, ksize);
        
         // Imgproc.morphologyEx(diff_img, diff_img, Imgproc.MORPH_CLOSE, kernel);
         
        // create a new window objects
            
        Imshow ims1 = new Imshow("Image 1");
        Imshow ims2 = new Imshow("Image 2");
        Imshow ims_diff = new Imshow("Difference");
        	
       // display image

        ims1.showImage(img1);
        ims2.showImage(img2);
        ims_diff.showImage(diff_img);
         
    }
}

// ********************************************************
