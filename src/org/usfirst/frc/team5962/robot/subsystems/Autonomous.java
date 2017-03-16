package org.usfirst.frc.team5962.robot.subsystems;

import org.usfirst.frc.team5962.robot.Robot;
import org.usfirst.frc.team5962.robot.RobotMap;
import org.usfirst.frc.team5962.robot.sensors.RobotUltrasonicBase;
import org.usfirst.frc.team5962.robot.Robot.AutonomousPosition;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Autonomous extends Subsystem  {

	private boolean reachedTargetDistance = false;
	private final double DISTANCETOLINE = 75; //50, 192
	private final double DISTANCETOGEARMIDDLE = 160; //70, 160
	private final int ULTRASONIC_RANGE_VALUE = 7;
	private double lefttargetAngle = 38;
	private double righttargetAngle = -38;
	private int distance = 190;
	private int backdistance = 0;
	public static boolean vision = false;
	//private static RobotUltrasonicBase ultrasonicRight;
	//private static RobotUltrasonicBase ultrasonicLeft;
	
	
	private double targetDistance = DISTANCETOLINE;
	
	
	
//	private boolean put_the_Gear = false;
//	private int turning = 0;
//	private double gyroAngle = 0;
//	private boolean inRange = false;
//	private boolean turned = false;

	private Robot.AutonomousPosition position = Robot.AutonomousPosition.LeftStartingPosition;

	public enum State{
		crossLineNV,
		turnNV,
		driveToHookNV,
		placeTheGearNV,
		turnDriveBackwardsNV,
		driveToMiddleNV,
		
		crossLineV,
		turnV,
		driveToHookV,
		placeTheGearV,
		turnDriveBackwardsV,
		driveToMiddleV,
		
		stop

	};
	public State state;

	public void setPosition(Robot.AutonomousPosition position) {
		this.position = position;
		if(vision == false){
			state = State.crossLineNV;
		}
		else if(vision == true){
			state = State.crossLineV;
		}
		if(this.position == AutonomousPosition.MiddleStartingPosition){
			targetDistance = DISTANCETOGEARMIDDLE;
		}
		else{
			targetDistance = DISTANCETOLINE;
		}
	}

	private boolean isWithinRange() {
		boolean isWithinRange = false;

		if (Robot.ultrasonicLeft.getRange() > ULTRASONIC_RANGE_VALUE || Robot.ultrasonicRight.getRange() > ULTRASONIC_RANGE_VALUE) {
			RobotMap.myRobot.drive(-0.25, 0);
		}
		else if (Robot.ultrasonicLeft.getRange() <= ULTRASONIC_RANGE_VALUE && Robot.ultrasonicRight.getRange() <= ULTRASONIC_RANGE_VALUE) {
			isWithinRange = true;
			RobotMap.myRobot.drive(0, 0);
		}else if ((Robot.ultrasonicLeft.getRange() - Robot.ultrasonicRight.getRange()) > 1.5) {
			RobotMap.myRobot.drive(-0.25, 1); // turn left-
		} else if ((Robot.ultrasonicRight.getRange() - Robot.ultrasonicLeft.getRange()) > 1.5){
			RobotMap.myRobot.drive(-0.25, -1); // turn right
		}  else {
			RobotMap.myRobot.drive(-0.15, 0);
		}
		return isWithinRange;
	}

	public boolean driveForward()
	{		
		double angle = getGyroAngle();
		// drive to obstacle
		if (Math.abs(Robot.encoder.getDistance()) <= targetDistance)
		{
			RobotMap.myRobot.drive(-0.25, RobotMap.inverted*(-angle * 0.03));			
		}
		else {		
			RobotMap.myRobot.drive(0, 0);
			Robot.encoder.reset();
			reachedTargetDistance = true;
		}
		return reachedTargetDistance;	
	}
	
	public void putTheGear()
	{
		double angle = getGyroAngle();
		SmartDashboard.putString("state", state+"");
		switch(state){
		case crossLineNV:
			if (driveForward() == true) {
				if(this.position == AutonomousPosition.MiddleStartingPosition && vision == false){
					state = State.driveToHookNV;
				} 
				else{
					state = State.turnNV;
				}
				
				RobotMap.myRobot.drive(0,0);
			}

			break;

		case turnNV:
			// TODO: change turn based on position
					if (this.position == AutonomousPosition.LeftStartingPosition && angle <= lefttargetAngle) {
						RobotMap.myRobot.drive(0.25,1);//need to try on robot; need to change left value every time, for left right turn chek + (left) or -(right) value 
						System.out.println("STUCK TO THE LEFT");
					} 
					else if (this.position == AutonomousPosition.RightStartingPosition && angle >= righttargetAngle){
						RobotMap.myRobot.drive(0.25,-1);//need to try on robot; need to change left value every time, for left right turn chek + (left) or -(right) value
						System.out.println("STUCK TO THE RIGHT");
					}
					else 
					{
						state = State.driveToHookNV;
						RobotMap.myRobot.drive(0,0);
						System.out.println("STUCK ON ELSE");
					}
					
					System.out.println("OUTSIDE THE IFS");
					System.out.println(angle + righttargetAngle + lefttargetAngle);

			

			break;

		case driveToHookNV:
			
			if(isWithinRange() == false){
				//RobotMap.myRobot.drive(-0.25, 0); // angle
				System.out.println("RANGE IS FALSE, BUT WE MADE IT!!!");
			}
			else
			{
				state = State.placeTheGearNV;
				RobotMap.myRobot.drive(0,0);
				System.out.println("RANGE IS TRUE, WE DID IT!!!");
			}
			
			System.out.println("driveToHook");
			break;

		case placeTheGearNV:
			//			if(Robot.limitSwitchright.islimitSwitchopen() == false){
			//				//stopgearmotr
			//				Robot.gearmechanism.gearStop();
			//				
			//			}
			//			else
			//			{
			//				//rungearmotor
			//				Robot.gearmechanism.openthegear();
			//			}
			Robot.solSub.activateOne();
			
			//state = State.turnDriveBackwardsNV;
			state = State.stop;
			break;

		case turnDriveBackwardsNV:
			if(backdistance <= 150){
				RobotMap.myRobot.drive(0.25, 0);//check the sign
				backdistance++;
			}
//			else if(distance > 40 && distance <= 50){
//				RobotMap.myRobot.drive(-0.25, -1);
//				distance++;
//			}
			else{
				Robot.encoder.reset();
				state = State.driveToMiddleNV;
				RobotMap.myRobot.drive(0,0);
			}
			break;

		case driveToMiddleNV:
			if(this.position == AutonomousPosition.MiddleStartingPosition) 
			{
				state = State.stop;
				RobotMap.myRobot.drive(0,0);
			}else if (Robot.encoder.getDistance() <= distance)
			{
				RobotMap.myRobot.drive(-0.25, /*RobotMap.inverted*/(-angle * 0.03));
				
			}
			else{
				state = State.stop;
				RobotMap.myRobot.drive(0,0);
			}
			break;
			
		case crossLineV:
			if (driveForward() == true) {
				if(this.position == AutonomousPosition.MiddleStartingPosition){
					state = State.driveToHookV;
				} 
				else{
					state = State.turnV;
					
				}
				
				RobotMap.myRobot.drive(0,0);
			}
			break;
		
		case turnV:
			Robot.gearVision.setDirection(position);
			
			Robot.gearVision.runGearVision(0, 0);
			
			if(Robot.gearVision.getSwitchStateGear() == true){
				state = State.driveToHookV;
				
			}
			break;
		
		case driveToHookV:
			if(isWithinRange() == false){
				//	RobotMap.myRobot.drive(-0.25, -angle * 0.03);
					System.out.println("driveToHook FALSE");
					
					Robot.gearVision.runGearVision(-0.25, 0);
				}
				else
				{
					state = State.placeTheGearV;
					RobotMap.myRobot.drive(0,0);
				}
			break;
			
		case placeTheGearV:
			Robot.solSub.activateOne();
			
			state = State.turnDriveBackwardsV;
			break;
			
		case turnDriveBackwardsV:
			if(distance <= 20 && this.position != AutonomousPosition.MiddleStartingPosition){
				RobotMap.myRobot.drive(0.25, 0);//check the sign
				distance++;
			}
			else if(distance > 20 && distance <= 30 && this.position != AutonomousPosition.MiddleStartingPosition){
				RobotMap.myRobot.drive(-0.25, -1);
				distance++;
			}
			else{
				state = State.driveToMiddleV;
			}
			break;
			
		case driveToMiddleV:
			if(distance <= 45 && this.position != AutonomousPosition.MiddleStartingPosition)
			{
				RobotMap.myRobot.drive(-0.50, RobotMap.inverted*(-angle * 0.03));
				distance ++;
			}
			else{
				state = State.stop;
			}
			break;

		case stop:
			Robot.solSub.activateZero();
			
			RobotMap.myRobot.drive(0,0);
			break;
		default:
			break;
		}

	}

	private double getGyroAngle() {
		double angle = Robot.gyro.getGyroAngle();
		return angle;
	}

	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub

	}


	/**
	 * CODE BELOW HERE HAS NOT BEEN REFACTORED YET
	 */

	/*	
	 *  ==============================================================================
	 *  OLD putTheGear Comments
	 *  
 	// #1 drive to line


	//#2 turn the robot
	if (reachToLine == true && put_the_Gear == false && turning <= 50  && inRange == false)
	{
		//need to use the gyro to tuen 
		//maybe need encoders to distance
		//need to use the utrasonic

		RobotMap.myRobot.drive(0.1,1);//need to try on robot; need to change left value every time, for left right turn chek + or - value 
		turning++;


		}

	//#3 drive to hook
	else if(reachToLine == true && put_the_Gear == false && turning > 50 && inRange == false  )
	{
		double angle = getGyroAngle();
		if(isWithinRange() == false){
			RobotMap.myRobot.drive(-0.25, -angle * 0.03);

		}
		else
		{
			RobotMap.myRobot.drive(0,0);
			inRange = true;

		}
	}


	// #4 place the gear
	else
	{ 
		if(Robot.limitSwitchright.islimitSwitchopen() == false){
			//stopgearmotr
			Robot.gearmechanism.gearStop();
			put_the_Gear = true;
		}
		else
		{
			//rungearmotor
			Robot.gearmechanism.openthegear();
		}


	}


	// #5 turn backwards
	if(put_the_Gear == true && distance <= 10)
	{
		RobotMap.myRobot.drive(-0,1);//check the sign
		distance++;
		turned = true;
	}
	// #6 drive to middle
	else if(distance > 10 && turned == true && distance <= 40)
	{
		double angle = getGyroAngle();
		RobotMap.myRobot.drive(-0.25, -angle * 0.03);
		distance ++;

	}
	// #7 finish
	else
	{
		RobotMap.myRobot.drive(0,0);
	}


	return put_the_Gear;

	 *
	 *  ==============================================================================
	 */		

//
//	private void turnTheRobot()
//	{
//		//if (reachToLine == true && put_the_Gear == false && turning <= 50  && inRange == false)
//		{
//			//need to use the gyro to tuen 
//			//maybe need encoders to distance
//			//need to use the utrasonic
//
//			RobotMap.myRobot.drive(0.1,1);//need to try on robot; need to change left value every time, for left right turn chek + or - value 
//			turning++;
//
//
//		}
//
//
//	}
//	private void driveToHook()
//	{
//		//if(reachToLine == true && put_the_Gear == false && turning > 50 && inRange == false  )
//		{
//			double angle = getGyroAngle();
//			if(isWithinRange() == false){
//				RobotMap.myRobot.drive(-0.25, -angle * 0.03);
//
//			}
//			else
//			{
//				RobotMap.myRobot.drive(0,0);
//				inRange = true;
//
//			}
//		}
//
//	}
//	private void placeTheGear()
//	{
//		//		if(Robot.limitSwitchright.islimitSwitchopen() == false){
//		//			//stopgearmotr
//		//			Robot.gearmechanism.gearStop();
//		//			put_the_Gear = true;
//		//		}
//		//		else
//		//		{
//		//			//rungearmotor
//		//			Robot.gearmechanism.openthegear();
//		//		}
//
//	}
//	private void turnToDriveBackward()
//	{
//		//if(put_the_Gear == true && distance <= 10)
//		{
//			RobotMap.myRobot.drive(-0,1);//check the sign
//			distance++;
//			turned = true;
//		}
//	}
//	private void driveToMiddle()
//	{
//		// if(distance > 10 && turned == true && distance <= 40)
//		{
//			double angle = getGyroAngle();
//			RobotMap.myRobot.drive(-0.25, -angle * 0.03);
//			distance ++;
//
//		}
//	}
//	private void finish()
//	{
//		RobotMap.myRobot.drive(0,0);
//	}
//
//
//	private boolean putTheGearMiddle()
//	{
//		boolean isinrange = false;
//		double angle = getGyroAngle();
//		// drive to obstacle
//		if (isWithinRange() || Robot.encoder.getDistance() <= DISTANCETOGEARMIDDLE)
//		{
//			RobotMap.myRobot.drive(-0.25, -angle * 0.03);
//			isinrange = true;	
//		}
//		else if(isinrange == true)
//		{
//			//			if(Robot.limitSwitchright.islimitSwitchopen() == false){
//			//				//stopgearmotr
//			//				Robot.gearmechanism.gearStop();
//			//				
//			//			}
//			//			else
//			//			{
//			//				//rungearmotor
//			//				Robot.gearmechanism.openthegear();
//			//			}
//		}
//		else {
//
//			RobotMap.myRobot.drive(0, 0);
//			Robot.encoder.reset();
//			reachToLine = true;
//		}
//		return reachToLine;		
//	}
//

}
