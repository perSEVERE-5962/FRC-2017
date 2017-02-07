package org.usfirst.frc.team5962.robot.subsystems;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.vision.VisionThread;
import edu.wpi.first.wpilibj.vision.VisionRunner;

import org.opencv.core.Mat;
import org.opencv.core.Rect;
import org.opencv.imgproc.Imgproc;
import org.usfirst.frc.team5962.robot.Robot;

import edu.wpi.cscore.CvSink;
import edu.wpi.cscore.CvSource;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.cscore.CameraServerJNI;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.IterativeRobot;

public class Camera extends Subsystem {
	private VisionThread visionThread;
	public static double centerX = 0.0;
	public static double centerY = 0.0;
	public static double area = 0.0;
	
	
	public static int IMG_WIDTH = 640;
	public static int IMG_HEIGHT = 360;
	
	private UsbCamera camera = CameraServer.getInstance().startAutomaticCapture();
	
	public final static Object imgLock = new Object();
	
	public Camera() {
        camera.setResolution(IMG_WIDTH, IMG_HEIGHT);

        new Thread(() -> {
            
            CvSink cvSink = CameraServer.getInstance().getVideo();
            CvSource outputStream = CameraServer.getInstance().putVideo("Blur", 640, 480);
            
            Mat source = new Mat();
            Mat output1 = new Mat();
            
            while(true) {
                cvSink.grabFrame(source);
                Imgproc.cvtColor(source, output1, Imgproc.COLOR_BGR2GRAY);
                outputStream.putFrame(output1);
                
                Robot.gripPipeline.process(output1);
            }
        }).start();
        
        visionThread = new VisionThread(camera, new GripPipeline(), pipeline -> {
        	if (!pipeline.filterContoursOutput().isEmpty()){
        		Rect r = Imgproc.boundingRect(pipeline.filterContoursOutput.get(0));
        		synchronized (imgLock) {
        			centerX = r.x + (r.width / 2);
        			centerY = r.y + (r.height / 2);
        			//area = r.area();
        		}
        	}
        });visionThread.start();
        
}

	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		
	}
}


	

 
