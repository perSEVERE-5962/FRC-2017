package org.usfirst.frc.team5962.robot.subsystems;

import org.usfirst.frc.team5962.robot.RobotMap;
import org.usfirst.frc.team5962.robot.sensors.RobotUltrasonicBase;

public class UsingTwoUltrasonicHelper {
	
	protected void driveRobot(double outputMagnitude, double curve) {
		RobotMap.myRobot.drive(outputMagnitude,curve);
	}
	
	public void lineUp(RobotUltrasonicBase ultrasonicLeft, RobotUltrasonicBase ultrasonicRight)
	{
		
		if(ultrasonicLeft.getRange() >= 6 && ultrasonicLeft.getRange() <= 7)
		{
			if((ultrasonicLeft.getRange() - ultrasonicRight.getRange()) < -0.1)
			{
				driveRobot(-0.6, 1); //turn right
			}
			else/*((ultrasonicLeft.getRange() - ultrasonicRight.getRange()) > 0.1 )*/
			{
				driveRobot(-0.6, -1); //turn left	
			}
			/*
			else if((ultrasonicLeft.getRange() - ultrasonicRight.getRange()) > -0.1 && (ultrasonicLeft.getRange() - ultrasonicRight.getRange() < 0.1)){
				driveRobot(0, 0);
			}
			*/
		}
		else if(ultrasonicRight.getRange() >= 6 && ultrasonicRight.getRange() <= 7)
		{
			if((ultrasonicRight.getRange() - ultrasonicLeft.getRange()) < 0.1)
			{
				driveRobot(-0.25, 1); //turn right
			}
			else
			{
				driveRobot(-0.25, -1); //turn left	
			}
		}
		
		
		else if (ultrasonicLeft.getRange() >= 7 && ultrasonicRight.getRange() >= 7)
			{
				driveRobot(-0.6, 0);
			}
		else if(ultrasonicLeft.getRange() <= 4 && ultrasonicRight.getRange() <= 4)
			{
				driveRobot(0.6, 0);	
			}
		else
			{
				driveRobot(0, 0);		
			}
		
	}
//	
//	{
//		
//		if (ultrasonicLeft.getRange() >= range ||
//		ultrasonicRight.getRange() >= range)
//		{
//			
//			RobotMap.myRobot.drive(-0.6, 0);// need to check the sign and speed TODO
//		}
//		else if (ultrasonicLeft.getRange() >= 7){
//			RobotMap.myRobot.drive(-0.25, 1);// need to check the sign and side to turn
//		}
//		else if (ultrasonicRight.getRange() >= 7){
//			RobotMap.myRobot.drive(-0.25, -1);// need to check the sign and side to turn
//		}
//		
//		else 
//		{
//		
//		RobotMap.myRobot.drive(0,0);	// need to check the sign and side to turn
//		}
//	}

}
