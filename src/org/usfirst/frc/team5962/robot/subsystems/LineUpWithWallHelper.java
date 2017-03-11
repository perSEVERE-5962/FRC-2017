package org.usfirst.frc.team5962.robot.subsystems;

import org.usfirst.frc.team5962.robot.RobotMap;
import org.usfirst.frc.team5962.robot.Robot;
import org.usfirst.frc.team5962.robot.sensors.RobotUltrasonicBase;

public class LineUpWithWallHelper {

	protected void driveRobot(double outputMagnitude, double curve) {
		RobotMap.myRobot.drive(outputMagnitude, curve);
	}

	public void lineUp(RobotUltrasonicBase ultrasonicLeft, RobotUltrasonicBase ultrasonicRight) {

		if (ultrasonicLeft.getRange() > 9 || ultrasonicRight.getRange() > 9) {
			Robot.solSub.deactivateTwo();
			driveRobot(-0.15, 0);
		} else if(ultrasonicLeft.getRange() < 3 || ultrasonicRight.getRange() < 3){
			Robot.solSub.deactivateTwo();
			driveRobot(0.15, 0);
		} else if ((ultrasonicLeft.getRange() - ultrasonicRight.getRange()) > 1.5) {
			Robot.solSub.deactivateTwo();
			driveRobot(-0.15, 1); // turn left-
		} else if ((ultrasonicRight.getRange() - ultrasonicLeft.getRange()) > 1.5) {
			Robot.solSub.deactivateTwo();
			driveRobot(-0.15, -1); // turn right
		} else if (ultrasonicLeft.getRange() < 5 && ultrasonicRight.getRange() < 5) {
			Robot.solSub.activateTwo();
			driveRobot(0, 0);
		} else {
			Robot.solSub.deactivateTwo();
			driveRobot(-0.15, 0);
		}
		
	}

	
}
