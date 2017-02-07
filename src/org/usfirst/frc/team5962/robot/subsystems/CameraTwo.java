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
            UsbCamera camera2 = CameraServer2.getInstance().startAutomaticCapture();
            camera2.setResolution(640, 480);
            
            CvSink cvSink2 = CameraServer2.getInstance().getVideo();
            CvSource outputStream2 = CameraServer2.getInstance().putVideo("Blur2", 640, 480);
            
            Mat source2 = new Mat();
            Mat output2 = new Mat();
            
            while(true) {
                cvSink2.grabFrame(source2);
                Imgproc.cvtColor(source2, output2, Imgproc.COLOR_BGR2GRAY);
                outputStream2.putFrame(output2);
            }
        }).start();
}

	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		
	}
}


	

 
