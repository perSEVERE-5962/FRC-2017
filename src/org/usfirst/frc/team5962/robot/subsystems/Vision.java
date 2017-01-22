package org.usfirst.frc.team5962.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import org.opencv.core.Rect;
import org.opencv.imgproc.Imgproc;
import com.ctre.CANTalon;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.vision.VisionRunner;
import edu.wpi.first.wpilibj.vision.VisionThread;

public class Vision extends Subsystem {

public void tryvision()
{

	final int IMG_WIDTH = 320;
	final int IMG_HEIGHT = 240;
	
	VisionThread visionThread;
	double centerX = 0.0;
	RobotDrive drive;
	
	final Object imgLock = new Object();

}
	
	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		
	}

}
