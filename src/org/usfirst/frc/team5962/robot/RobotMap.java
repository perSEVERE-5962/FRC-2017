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
	public static RobotDrive myRobot;
	
	public static Victor ledVictor;
	public static Victor robotLeftVictor1;
	public static Victor robotLeftVictor2;
	public static Victor robotRightVictor1;
	public static Victor robotRightVictor2;
	
	public static void init() {
		ledVictor = new Victor(4);

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
