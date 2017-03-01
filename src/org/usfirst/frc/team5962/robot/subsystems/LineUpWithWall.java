package org.usfirst.frc.team5962.robot.subsystems;

import org.usfirst.frc.team5962.robot.Robot;
import org.usfirst.frc.team5962.robot.RobotMap;
import org.usfirst.frc.team5962.robot.sensors.RobotUltrasonicBase;

import edu.wpi.first.wpilibj.command.Subsystem;

public class LineUpWithWall extends Subsystem {
	
	private RobotUltrasonicBase ultrasonicLeft;
	private RobotUltrasonicBase ultrasonicRight;
	
	private LineUpWithWallHelper helper = new LineUpWithWallHelper();
	
	public LineUpWithWall(RobotUltrasonicBase ultrasonicLeft, RobotUltrasonicBase ultrasonicRight) {
		this.ultrasonicLeft = ultrasonicLeft;
		this.ultrasonicRight = ultrasonicRight;
	}
	
	
//	double ultra = Robot.ultrasonicleft.getRange() - Robot.ultrasonicrigth.getRange();
	

	public void lineUp()
	{
		
		helper.lineUp(ultrasonicLeft, ultrasonicRight);
		
		
//		if(Robot.ultrasonicleft.getRange() >= 6 && Robot.ultrasonicleft.getRange() <= 7)
//		{
//			if((Robot.ultrasonicleft.getRange() - Robot.ultrasonicrigth.getRange()) < -0.1)
//			{
//				RobotMap.myRobot.drive(-0.6, 1); //turn right
//			}
//			else/*((Robot.ultrasonicleft.getRange() - Robot.ultrasonicrigth.getRange()) > 0.1 )*/
//			{
//				RobotMap.myRobot.drive(-0.6, -1); //turn left	
//			}
//			/*
//			else if((Robot.ultrasonicleft.getRange() - Robot.ultrasonicrigth.getRange()) > -0.1 && (Robot.ultrasonicleft.getRange() - Robot.ultrasonicrigth.getRange() < 0.1)){
//				RobotMap.myRobot.drive(0, 0);
//			}
//			*/
//		}
//		else if(Robot.ultrasonicrigth.getRange() >= 6 && Robot.ultrasonicrigth.getRange() <= 7)
//		{
//			if((Robot.ultrasonicrigth.getRange() - Robot.ultrasonicleft.getRange()) < 0.1)
//			{
//				RobotMap.myRobot.drive(-0.25, 1); //turn right
//			}
//			else
//			{
//				RobotMap.myRobot.drive(-0.25, -1); //turn left	
//			}
//		}
//		
//		
//		else if (Robot.ultrasonicleft.getRange() >= 7 && Robot.ultrasonicrigth.getRange() >= 7)
//			{
//				RobotMap.myRobot.drive(-0.6, 0);
//			}
//		else if(Robot.ultrasonicleft.getRange() <= 4 && Robot.ultrasonicrigth.getRange() <= 4)
//			{
//				RobotMap.myRobot.drive(0.6, 0);	
//			}
//		else
//			{
//				RobotMap.myRobot.drive(0, 0);		
//			}
		
	}
//	
//	{
//		
//		if (Robot.ultrasonicleft.getRange() >= range ||
//		Robot.ultrasonicrigth.getRange() >= range)
//		{
//			
//			RobotMap.myRobot.drive(-0.6, 0);// need to check the sign and speed TODO
//		}
//		else if (Robot.ultrasonicleft.getRange() >= 7){
//			RobotMap.myRobot.drive(-0.25, 1);// need to check the sign and side to turn
//		}
//		else if (Robot.ultrasonicrigth.getRange() >= 7){
//			RobotMap.myRobot.drive(-0.25, -1);// need to check the sign and side to turn
//		}
//		
//		else 
//		{
//		
//		RobotMap.myRobot.drive(0,0);	// need to check the sign and side to turn
//		}
//	}
	
	
	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		
	}

}
