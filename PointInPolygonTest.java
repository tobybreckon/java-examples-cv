// ********************************************************

// Example : simple demonstration of the point in polygon
// function from OpenCV

// Author : Toby Breckon, toby.breckon@durham.ac.uk

// Copyright (c) 2015 Durham University
// License : LGPL - http://www.gnu.org/licenses/lgpl.html

// version 0.1

// ********************************************************

// import required OpenCV components

import java.util.ArrayList;
import java.util.List;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.MatOfPoint2f;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.highgui.Highgui;
import org.opencv.imgproc.Imgproc;
import org.opencv.core.CvType;

// ********************************************************

public class PointInPolygonTest {

    public static void main(String[] args) {

        // load the Core OpenCV library by name

        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);

        // create an empty image which is black

        Mat img = Mat.zeros(400, 400, CvType.CV_8SC3);
        
        // Create a contour
        
        Point[] contourPoints = new Point[6];
        
        contourPoints[0] = new Point(50,50);
        contourPoints[1] = new Point(300,50);
        contourPoints[2] = new Point(350,200);
        contourPoints[3] = new Point(300,150);
        contourPoints[4] = new Point(150,350);
        contourPoints[5] = new Point(100,100);
        
        // convert it to a java list of OpenCV MatOfPoint 
        // objects as this is what the draw function requires
        
        MatOfPoint contour1 = new MatOfPoint(contourPoints);
        List<MatOfPoint> contourList = new ArrayList<MatOfPoint>();
        contourList.add(contour1);
        
        // draw the contour
        
        Imgproc.drawContours(img, contourList, -1, new Scalar(0,255,0), 2);
        
        // create two test points and draw them
        
        Point p1 = new Point(150,75);
        Core.rectangle(img, p1, p1, new Scalar(0, 0, 255), 3, 8, 0); // RED point
        
        Point p2 = new Point(50,350);
        Core.rectangle(img, p2, p2, new Scalar(255, 0, 0), 3, 8, 0); // BLuE point
        
        // perform point in polygon test
        
        MatOfPoint2f contourPoint2f = new MatOfPoint2f(contourPoints);
        
        if (Imgproc.pointPolygonTest(contourPoint2f,p1, false) > 0)
        {
        	System.out.println( "RED point " + p1.toString() + " is inside the polygon");
        }
        
        if (Imgproc.pointPolygonTest(contourPoint2f,p2, false) < 0)
        {
        	System.out.println( "BLUE point " + p2.toString() + " is outside the polygon");
        }
        
        // create a display window using an Imshow object

        Imshow ims1 = new Imshow("Ploygon Test");

        // display image

        ims1.showImage(img);

    }
}

// ********************************************************

