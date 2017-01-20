package org.usfirst.frc.team5962.robot.subsystems;

import org.usfirst.frc.team5962.robot.Robot;

import edu.wpi.first.wpilibj.command.Subsystem;

public class Autonomos extends Subsystem  {
private double DISTANCETOLINE = 162; 
	public boolean driveForward()
	{
		boolean reachToLine = false;
		double angle = getGyroAngle();
		// drive to obstacle
		if (Robot.encoder.getDistance() <= DISTANCETOLINE)
		{
		}
	return reachToLine;	
	}
	
	
	
	private double getGyroAngle() {
		double angle = Robot.gyro.getGyroAngle();
	return angle;
	}
	
	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		
	}

}
