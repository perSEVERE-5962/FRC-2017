package org.usfirst.frc.team5962.robot.commands;

import org.usfirst.frc.team5962.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class RunGearCollection extends Command {
	static boolean stop = false;
	public RunGearCollection() {
	    	requires(Robot.lineUpWithWall);
	    }

	    protected void initialize() {
	    	stop = false;
	    	
	    }

	    protected void execute() {
	    	Robot.lineUpWithWall.lineUp();
	    }

	    protected boolean isFinished() {
	     return false;   
	    }

	    protected void end() {
	    	stop = true;
	    }

	    protected void interrupted() {
	    	end();
	    }
	
}
