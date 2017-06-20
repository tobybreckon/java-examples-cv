// ********************************************************

// Example : displaying live from attached camera
// or alternatively from an attached video file

// Author : Toby Breckon, toby.breckon@durham.ac.uk

// Copyright (c) 2015 Durham University
// License : LGPL - http://www.gnu.org/licenses/lgpl.html

// ********************************************************

// import required OpenCV components

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.highgui.VideoCapture;

// ********************************************************

public class CaptureVideo {

    public static void main(String[] args) throws InterruptedException {

        // load the Core OpenCV library by name

        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);

        // create video capture device object

        VideoCapture cap = new VideoCapture();

        // try to use the hardware device if present
        
        int CAM_TO_USE = 0;
        
        // create a new image object
        
        Mat matFrame = new Mat();
        
        // try to open first capture device (0)
        
        try {
        	cap.open(CAM_TO_USE);
        } catch (Exception e1) {
            System.out.println("No webcam attached");
        
            // otherwise try opening a video file 
            
            try{
            	cap.open("files/video.mp4");
            } catch (Exception e2) {
            	System.out.println("No video file found");
            }
        }
        
        // if the a video capture source is now open
        
        if (cap.isOpened())
        {

            // create a new window object
            
            Imshow ims = new Imshow("From video source ... ");
        	
        	boolean keepProcessing = true;
        	
        	while (keepProcessing)
        	{	
        		// grab the next frame from video source

        		cap.grab();

            	// decode and return the grabbed video frame

            	cap.retrieve(matFrame);

            	// if the frame is valid (not end of video for example)
            	
            	if (!(matFrame.empty()))
            	{
            		// *** to any processing here*** 
            		
            		// display image with a delay of 40ms (i.e. 1000 ms / 25 = 25 fps)

            		ims.showImage(matFrame);
            		Thread.sleep(40);
            	} else { 
            		keepProcessing = false;
        		}
        	}

        } else {
            System.out.println("error cannot open any capture source - exiting");
        }

        // close down the camera correctly

        cap.release();
        
    }
}

// ********************************************************
