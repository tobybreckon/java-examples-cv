// ********************************************************

// Example : computer the difference between a pair of images,
// detect the resulting contours and display them

// Author : Toby Breckon, toby.breckon@durham.ac.uk

// Copyright (c) 2015 Durham University
// License : LGPL - http://www.gnu.org/licenses/lgpl.html

// ********************************************************

// import required Java components

import java.util.List;
import java.util.Vector;

//import required OpenCV components

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.Size;
import org.opencv.core.Scalar;
import org.opencv.highgui.Highgui;
import org.opencv.imgproc.Imgproc;

// ********************************************************

public class detectContours {

    public static void main(String[] args) throws InterruptedException {

        // load the Core OpenCV library by name

        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);

        // load two image files
        
        Mat img1 = Highgui.imread("files/cctv_example1.png");
        Mat img2 = Highgui.imread("files/cctv_example2.png");
        
        // create a new image object to store image difference
        
        Mat diff_img = new Mat();
        
        // compute the difference between the images
        
        Core.absdiff(img1, img2, diff_img);
       
        // now convert it to grey and threshold it
        
        Mat grey = new Mat();
        Imgproc.cvtColor(diff_img, grey, Imgproc.COLOR_BGR2GRAY);
       
        Imgproc.adaptiveThreshold(grey, diff_img, 255, Imgproc.ADAPTIVE_THRESH_MEAN_C, 
        							Imgproc.THRESH_BINARY_INV, 7, 10);
        
        // now clean it up using some morphological operations
        
        Size ksize = new Size(15,15);
        Mat kernel =  Imgproc.getStructuringElement(Imgproc.MORPH_ELLIPSE, ksize);
        
        Imgproc.morphologyEx(diff_img, diff_img, Imgproc.MORPH_CLOSE, kernel);
        
        // find the all the contours from the binary image using the edge to contour
        // stuff we looked at in lectures
        
        List<MatOfPoint> contours = new Vector<MatOfPoint>();
        
        Imgproc.findContours(diff_img, contours, new Mat(), Imgproc.RETR_LIST, Imgproc.CHAIN_APPROX_SIMPLE);
         
        // draw the contours on image 2 in red
        
        Imgproc.drawContours(img2, contours, -1, new Scalar(0,0,255));
        
        // find the largest contour by area 
        
        double maxArea = 0;
        int maxAreaIndex = 0;
        
        for (int i = 0; i < contours.size(); i++) {
        	
        	double area = Imgproc.contourArea(contours.get(i), false);
            
        	if ( area > maxArea )
        	{
        		maxArea = area;
        		maxAreaIndex = i;
        	}
        }
        
        // draw the largest contour in red
        
        Imgproc.drawContours(img2, contours, maxAreaIndex, new Scalar(0,255,0));
        
        // create a new window objects
            
        Imshow ims1 = new Imshow("Image 1");
        Imshow ims2 = new Imshow("Image 2");
        Imshow ims_diff = new Imshow("Difference");
        	
       // display images

        ims1.showImage(img1);
        ims2.showImage(img2);
        ims_diff.showImage(diff_img);
         
    }
}

// ********************************************************
