package org.usfirst.frc.team5962.robot;

import com.ctre.CANTalon;
import com.ctre.CANTalon.FeedbackDevice;
import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Talon;
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
	public final static int PWM_CHANNEL_0 = 0;	// in use
	public final static int PWM_CHANNEL_1 = 1;	// in use
	public final static int PWM_CHANNEL_2 = 2;
	public final static int PWM_CHANNEL_3 = 3;	// in use
	public final static int PWM_CHANNEL_4 = 4;	// in use
	public final static int PWM_CHANNEL_5 = 5;	// in use
	public final static int PWM_CHANNEL_6 = 6;	// in use
	public final static int PWM_CHANNEL_7 = 7;	// in use
	public final static int PWM_CHANNEL_8 = 8;	// in use
	public final static int PWM_CHANNEL_9 = 9;	// in use
	
	//DIO Channels
	public final static int DIO_CHANNEL_0 = 0;
	public final static int DIO_CHANNEL_1 = 1;
	public final static int DIO_CHANNEL_2 = 2;
	public final static int DIO_CHANNEL_3 = 3;
	public final static int DIO_CHANNEL_4 = 4;
	public final static int DIO_CHANNEL_5 = 5;
	public final static int DIO_CHANNEL_6 = 6;
	public final static int DIO_CHANNEL_7 = 7;
	public final static int DIO_CHANNEL_8 = 8;
	public final static int DIO_CHANNEL_9 = 9;	

	public static RobotDrive myRobot;
	
	
	public static Victor robotLeftVictor1;
	public static Victor robotLeftVictor2;
	public static Victor robotRightVictor1;
	public static Victor robotRightVictor2;
	public static CANTalon talon;
	public static CANTalon scalingtalon;
	public static CANTalon ballfeedertalon; 
	public static CANTalon agitatortalon; 

	public static Servo axisCameraServoViewHorizontal;
	public static Servo axisCameraServoViewVertical;
	
	public static SpeedController leftDrive;
	public static SpeedController rightDrive;
	
	public static void init() {
		
		//SpeedController leftDrive;
		//SpeedController rightDrive;

		robotLeftVictor1 = new Victor(PWM_CHANNEL_0);
		robotLeftVictor2 = new Victor(PWM_CHANNEL_1);
		robotRightVictor1 = new Victor(PWM_CHANNEL_6);
		robotRightVictor2 = new Victor(PWM_CHANNEL_7);
		
		talon = new CANTalon(11);
		scalingtalon = new CANTalon(13);
		ballfeedertalon = new CANTalon(10);
		agitatortalon = new CANTalon(12);
		//talon.setInverted(true);
		
		axisCameraServoViewHorizontal = new Servo(PWM_CHANNEL_8);
		axisCameraServoViewVertical = new Servo(PWM_CHANNEL_9);
				
	    leftDrive = new MultiSpeedController(robotLeftVictor1, robotLeftVictor2);
	    rightDrive = new MultiSpeedController(robotRightVictor1, robotRightVictor2);
	    leftDrive.setInverted(true);
	    rightDrive.setInverted(true);
		myRobot = new RobotDrive(leftDrive, rightDrive);
	}
}
