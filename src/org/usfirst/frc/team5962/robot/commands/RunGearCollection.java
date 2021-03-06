package org.usfirst.frc.team5962.robot.commands;

import org.usfirst.frc.team5962.robot.Robot;
import org.usfirst.frc.team5962.robot.RobotMap;
import org.usfirst.frc.team5962.robot.subsystems.LineUpWithWall;

import edu.wpi.first.wpilibj.command.Command;

public class RunGearCollection extends Command {
	LineUpWithWall lineUpWithWall = new LineUpWithWall(Robot.ultrasonicLeft, Robot.ultrasonicRight);
	
	public RunGearCollection() {
	    	requires(lineUpWithWall);
	    }

	    protected void initialize() {
	    	
	    	
	    }

	    protected void execute() {
	    	lineUpWithWall.lineUp();
	    }

	    protected boolean isFinished() {
	     return false;
	    }

	    protected void end() {
	    	RobotMap.myRobot.drive(0,0);
	    	Robot.solSub.deactivateTwo();
	    }

	    protected void interrupted() {
	    	end();
	    }
	
}
