package org.usfirst.frc.team5962.robot.commands;

import org.usfirst.frc.team5962.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class RunPID extends Command{

	static boolean stop = false;
	public RunPID() {
    	requires(Robot.pidEncoders);
    }

    protected void initialize() {
    	Robot.pidEncoders.robotInit();
    	System.out.println("start");
    }

    protected void execute() {
    	if(!stop)
    	{
    	Robot.pidEncoders.teleopPeriodic();
    	}   	
    	//System.out.println("start");
    }

    protected boolean isFinished() { 
        if(stop)
        {
    	System.out.println("stop");
        }
        return stop;
    }

    protected void end() {
    	Robot.pidEncoders.stopTalon();
    	System.out.println("totalstop");
    	stop = true;
    }

    protected void interrupted() {
    	end();
    }

}
