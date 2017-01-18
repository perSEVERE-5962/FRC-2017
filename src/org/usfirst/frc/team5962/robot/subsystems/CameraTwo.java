package org.usfirst.frc.team5962.robot.subsystems;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Subsystem;

import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;

import edu.wpi.cscore.CvSink;
import edu.wpi.cscore.CvSource;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.cscore.CameraServerJNI;
import org.usfirst.frc.team5962.robot.CameraServer2;
import edu.wpi.first.wpilibj.IterativeRobot;


public class CameraTwo extends Subsystem {
	public CameraTwo() {
        new Thread(() -> {
            UsbCamera camera = CameraServer2.getInstance().startAutomaticCapture();
            camera.setResolution(640, 480);
            
            CvSink cvSink = CameraServer2.getInstance().getVideo();
            CvSource outputStream = CameraServer2.getInstance().putVideo("Blur", 640, 480);
            
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


	

 
