// ********************************************************

// Example : displaying image from attached camera

// Author : Toby Breckon, toby.breckon@durham.ac.uk

// Copyright (c) 2015 Durham University
// License : LGPL - http://www.gnu.org/licenses/lgpl.html

// ********************************************************

// import required OpenCV components

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.highgui.VideoCapture;

// ********************************************************

public class CaptureCamera {

    public static void main(String[] args) {

        // load the Core OpenCV library by name

        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);

        // create video capture device object (camera index = 0)

        VideoCapture cap = new VideoCapture(0);

        if (!cap.isOpened())
            System.out.println("error cannot any open camera");
        else
            System.out.println("found webcam: " + cap.toString());

        Mat matFrame = new Mat();

        // Grabs the next frame from video file or capturing device

        cap.grab(); // First frame maybe empty frame so skip it
        cap.grab();

        // Decodes and returns the grabbed video frame.

        cap.retrieve(matFrame);

        // display image

        Imshow ims = new Imshow("From Camera ....");
        ims.showImage(matFrame);

        // close down the camera correctly

        cap.release();

    }
}

// ********************************************************
