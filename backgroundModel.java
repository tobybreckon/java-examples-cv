// ********************************************************

// Example : displaying live from attached camera
// and perform background modelling

// Author : Toby Breckon, toby.breckon@durham.ac.uk

// Copyright (c) 2015 Durham University
// License : LGPL - http://www.gnu.org/licenses/lgpl.html

// ********************************************************

// import required OpenCV components

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.highgui.VideoCapture;
import org.opencv.imgproc.Imgproc;
import org.opencv.video.BackgroundSubtractorMOG;

// ********************************************************

public class backgroundModel {

    public static void main(String[] args) throws InterruptedException {

        // load the Core OpenCV library by name

        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);

        // create video capture device object

        VideoCapture cap = new VideoCapture();

        // try to use the hardware device if present
        
        int CAM_TO_USE = 0;
        
        // create new image objects
        
        Mat frame = new Mat();
        Mat foreground = new Mat();
        Mat fg_mask = new Mat();
        
        // create new Mixture of Gaussian BG model
        
        BackgroundSubtractorMOG MoG = new BackgroundSubtractorMOG();
        
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
            // create new window objects
            
            Imshow imsS = new Imshow("from video Source ... ");
            Imshow imsF = new Imshow("Foreground");
      
          	boolean keepProcessing = true;
        	
        	while (keepProcessing)
        	{	
        		// grab and return the next frame from video source

        		cap.grab();
            	cap.retrieve(frame);

            	// if the frame is valid (not end of video for example)
            	
            	if (!(frame.empty()))
            	{
            	
            		// add it to the background model with a learning rate of 0.1
            		
            		MoG.apply(frame, fg_mask, 0.1);
            		
            		// extract the foreground mask (1 = foreground / 0 - background), 
            		// and convert/expand it to a 3-channel version of the same 
            		
            		Imgproc.cvtColor(fg_mask, fg_mask, Imgproc.COLOR_GRAY2BGR);
            		
            		// logically AND it with the original frame to extract colour 
            		// pixel only in the foreground regions
            		
            		Core.bitwise_and(frame, fg_mask, foreground); 
            		
            		// display image with a delay of 40ms (i.e. 1000 ms / 25 = 25 fps)

            		imsS.showImage(frame);
            		imsF.showImage(foreground);
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
