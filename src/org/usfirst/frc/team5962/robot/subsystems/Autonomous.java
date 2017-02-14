package org.usfirst.frc.team5962.robot.subsystems;

import org.usfirst.frc.team5962.robot.Robot;
import org.usfirst.frc.team5962.robot.RobotMap;
import org.usfirst.frc.team5962.robot.Robot.AutonomousPosition;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Autonomous extends Subsystem  {

	private boolean reachedTargetDistance = false;
	private final double DISTANCETOLINE = 192; 
	private final double DISTANCETOGEARMIDDLE = 160;
	private final int ULTRASONIC_RANGE_VALUE = 7;
	private double lefttargetAngle = 40;
	private double righttargetAngle = -40;
	private int distance = 0;
	
	
	
	private double targetDistance = DISTANCETOLINE;
	
	
	
//	private boolean put_the_Gear = false;
//	private int turning = 0;
//	private double gyroAngle = 0;
//	private boolean inRange = false;
//	private boolean turned = false;

	private Robot.AutonomousPosition position = Robot.AutonomousPosition.LeftStartingPosition;

	public enum State
	{
		crossLine,
		turn,
		driveToHook,
		placeTheGear,
		turnDriveBackwards,
		driveToMiddle,
		stop

	};
	public State state;

	public void setPosition(Robot.AutonomousPosition position) {
		this.position = position;
		state = State.crossLine;
		if (this.position == AutonomousPosition.MiddleStartingPosition){
			targetDistance = DISTANCETOGEARMIDDLE;
		} else {
			targetDistance = DISTANCETOLINE;
		}
	}

	private boolean isWithinRange() {
		boolean isWithinRange = false;

		boolean ultrasonicEnabled = Robot.ultrasonic.isEnabled();
		if (ultrasonicEnabled) {
			isWithinRange = Robot.ultrasonic.getRange() <= ULTRASONIC_RANGE_VALUE;
		} // otherwise just return the default (true)
		return isWithinRange;
	}

	public boolean driveForward()
	{		
		double angle = getGyroAngle();
		// drive to obstacle
		if (Robot.encoder.getDistance() <= targetDistance)
		{
			RobotMap.myRobot.drive(-0.25, -angle * 0.03);			
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
		case crossLine:
			if (driveForward() == true) {
				if (this.position == AutonomousPosition.MiddleStartingPosition){
					state = State.driveToHook;
				} else {
					state = State.turn;
				}
				
				RobotMap.myRobot.drive(0,0);
			}

			break;

		case turn:
			// TODO: change turn based on position
					/*	if (this.position == AutonomousPosition.LeftStartingPosition) {
							
							if(angle <= lefttargetAngle)
							{
								RobotMap.myRobot.drive(0.25,-1);//need to try on robot; need to change left value every time, for left right turn chek + (left) or -(right) value 
							}
						} 
						else if (this.position == AutonomousPosition.RightStartingPosition)
						{
							if(angle >= righttargetAngle)
							{
								RobotMap.myRobot.drive(0.25,1);//need to try on robot; need to change left value every time, for left right turn chek + (left) or -(right) value 
							}
						}
						else 
						{
							state = State.driveToHook;
							RobotMap.myRobot.drive(0,0);
						}*/
						System.out.println("turn");
						Robot.gearVision.runGearVision();
						if(Robot.gearVision.switchStateGear == true){
							state = State.driveToHook;
						}
						System.out.println(Robot.gearVision.switchStateGear + "AUTO AUTO");

			

			break;

		case driveToHook:
			/*
			if(isWithinRange() == false){
				RobotMap.myRobot.drive(-0.25, -angle * 0.03);
			}
			else
			{
				state = State.placeTheGear;
				RobotMap.myRobot.drive(0,0);
			}
			*/
			System.out.println("driveToHook");
			Robot.distanceVision.runDistanceVision();
			if(Robot.distanceVision.switchStateDistance == true){
				state = State.placeTheGear;
			}
			
			break;

		case placeTheGear:
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
			state = State.turnDriveBackwards;
			RobotMap.myRobot.drive(0,0);
			break;

		case turnDriveBackwards:
			if(distance <= 10)
			{
				RobotMap.myRobot.drive(-0.2,1);//check the sign
				distance++;
			}
			state = State.driveToMiddle;
			break;

		case driveToMiddle:
			if(distance <= 40)
			{
				RobotMap.myRobot.drive(-0.25, -angle * 0.03);
				distance ++;
			}
			state = State.stop;
			break;

		case stop:
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
