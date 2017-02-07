package org.usfirst.frc.team5962.robot.subsystems;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.command.Subsystem;

import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;

import edu.wpi.cscore.CvSink;
import edu.wpi.cscore.CvSource;
import edu.wpi.cscore.UsbCamera;

public class Camera extends Subsystem {
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
           UsbCamera camera = CameraServer.getInstance().startAutomaticCapture();
            camera.setResolution(640, 480);

            
            CvSink cvSink = CameraServer.getInstance().getVideo();
            CvSource outputStream = CameraServer.getInstance().putVideo("Blur", 640, 480);
            
            Mat source = new Mat();
            Mat output1 = new Mat();
            
            while(true) {
                cvSink.grabFrame(source);
                Imgproc.cvtColor(source, output1, Imgproc.COLOR_BGR2GRAY);
                outputStream.putFrame(output1);
             }
        }).start();
        
}

	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		
	}
}


	

 
