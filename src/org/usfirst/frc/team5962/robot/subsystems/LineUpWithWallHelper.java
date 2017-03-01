package org.usfirst.frc.team5962.robot.subsystems;

import org.usfirst.frc.team5962.robot.RobotMap;
import org.usfirst.frc.team5962.robot.sensors.RobotUltrasonicBase;

public class LineUpWithWallHelper {
	
	protected void driveRobot(double outputMagnitude, double curve) {
		RobotMap.myRobot.drive(outputMagnitude,curve);
	}
	
	public void lineUp(RobotUltrasonicBase ultrasonicLeft, RobotUltrasonicBase ultrasonicRight)
	{
		
		if(ultrasonicLeft.getRange() > 8 || ultrasonicRight.getRange() > 8)
		{
			
				driveRobot(-0.6, 0); 
			
		}
		else if((ultrasonicLeft.getRange() - ultrasonicRight.getRange() ) > 1)
		{
			
			
				driveRobot(-0.25, 1); //turn left 
			
			
		}
		
		
		else if((ultrasonicRight.getRange() - ultrasonicLeft.getRange() ) > 1)
		{
			
			
				driveRobot(-0.25, -1); //turn right
			
			
		}
		else if(ultrasonicLeft.getRange() < 5 && ultrasonicRight.getRange() < 5)
			{
			driveRobot(0, 0);	
			}
		else
			{
			driveRobot(-0.6, 0);		
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
