package org.usfirst.frc.team5962.robot;

import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SpeedController;
//import com.ctre.CANTalon;
import edu.wpi.first.wpilibj.Victor;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	
	//PWM Channels
//	public final static int PWM_CHANNEL_0 = 0;
//	public final static int PWM_CHANNEL_1 = 1;
//	public final static int PWM_CHANNEL_2 = 2;
	public final static int PWM_CHANNEL_3 = 3;
	public final static int PWM_CHANNEL_4 = 4;
	public final static int PWM_CHANNEL_5 = 5;
//	public final static int PWM_CHANNEL_6 = 6;
//	public final static int PWM_CHANNEL_7 = 7;
//	public final static int PWM_CHANNEL_8 = 8;
//	public final static int PWM_CHANNEL_9 = 9;
	
	//DIO Channels
//	public final static int DIO_CHANNEL_0 = 0;
//	public final static int DIO_CHANNEL_1 = 1;
//	public final static int DIO_CHANNEL_2 = 2;
//	public final static int DIO_CHANNEL_3 = 3;
//	public final static int DIO_CHANNEL_4 = 4;
//	public final static int DIO_CHANNEL_5 = 5;
//	public final static int DIO_CHANNEL_6 = 6;
//	public final static int DIO_CHANNEL_7 = 7;
//	public final static int DIO_CHANNEL_8 = 8;
//	public final static int DIO_CHANNEL_9 = 9;
	

	public static RobotDrive myRobot;
	public static Victor inTakeVictor;
	public static Victor ballShootingvictor;
	public static Victor scalingvictor;
	//public static Victor manipulatorVictor;
	
//	public static Victor ledVictor;
	public static Victor robotLeftVictor1;
	public static Victor robotLeftVictor2;
	public static Victor robotRightVictor1;
	public static Victor robotRightVictor2;
	
	public static void init() {
//		ledVictor = new Victor(5);
		inTakeVictor = new Victor(PWM_CHANNEL_3);
		inTakeVictor.setSafetyEnabled(false);
		ballShootingvictor = new Victor(PWM_CHANNEL_4);
		ballShootingvictor.setSafetyEnabled(false);
		scalingvictor = new Victor(PWM_CHANNEL_5);
		scalingvictor.setSafetyEnabled(false);
		
		SpeedController leftDrive;
		SpeedController rightDrive;
		robotLeftVictor1 = new Victor(0);
		robotLeftVictor2 = new Victor(1);
		robotRightVictor1 = new Victor(6);
		robotRightVictor2 = new Victor(7);
		
	    leftDrive = new MultiSpeedController(robotLeftVictor1, robotLeftVictor2);
	    rightDrive = new MultiSpeedController(robotRightVictor1, robotRightVictor2);
		myRobot = new RobotDrive(leftDrive, rightDrive);
		

	}
}
