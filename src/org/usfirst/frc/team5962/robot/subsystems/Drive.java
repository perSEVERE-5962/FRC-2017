
package org.usfirst.frc.team5962.robot.subsystems;

import org.usfirst.frc.team5962.robot.Robot;
import org.usfirst.frc.team5962.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Drive extends Subsystem {

	boolean stop = false;
	boolean gyroTurn = false;
	boolean intake = false;
    boolean start = false;
	
//    public void gameTank() {
//		RobotMap.myRobot.tankDrive(Robot.oi.gamePad1.getRawAxis(1), Robot.oi.gamePad1.getRawAxis(5));
//	}
//
//	public void gameXTank() {
//		RobotMap.myRobot.tankDrive(Robot.oi.gamePad2.getRawAxis(1), Robot.oi.gamePad2.getRawAxis(5));	
//	}
//
	public void joystickTank() {
		RobotMap.myRobot.tankDrive(Robot.oi.joystickLeft, Robot.oi.joystickRight);
	}
//
//	public void arcadeJoystickRight() {
//		RobotMap.myRobot.arcadeDrive(Robot.oi.joystickRight);
//	}
//
//	public void arcadeJoystickLeft() {
//		RobotMap.myRobot.arcadeDrive(Robot.oi.joystickLeft);
//	}
	public void arcadeGame() {
		//RobotMap.myRobot.arcadeDrive(Robot.oi.gamePad1);
		RobotMap.myRobot.arcadeDrive(Robot.oi.driverPad);
	}
//
//	public void arcadeXGame() {
//		RobotMap.myRobot.arcadeDrive(Robot.oi.gamePad2);
//	}
	/*
	public void invert(){
		RobotMap.leftDrive.setInverted(true);
		RobotMap.rightDrive.setInverted(true);
		RobotMap.inverted = -1;
	}
	
	public void uninvert(){
		RobotMap.leftDrive.setInverted(false);
		RobotMap.rightDrive.setInverted(false);
		RobotMap.inverted = 1;
	}
	*/



	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub

	}
}
