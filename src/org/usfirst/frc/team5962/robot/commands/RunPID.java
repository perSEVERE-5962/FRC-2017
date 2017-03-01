package org.usfirst.frc.team5962.robot.commands;

import org.usfirst.frc.team5962.robot.Robot;
import org.usfirst.frc.team5962.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

public class RunPID extends Command{

	static boolean stop = false;
	public RunPID() {
    	requires(Robot.pidEncoders);
    }

    protected void initialize() {
    	stop = false;
    	Robot.pidEncoders.robotInit();
    	System.out.println("initialize");
    }

    protected void execute() {
    	if(!stop)
    	{
    		System.out.println("execute");
    		
    	Robot.pidEncoders.teleopPeriodic();
    	//Robot.scaling.scaling();
    	}   	
    	//System.out.println("start");
    }

    protected boolean isFinished() { 
        if(stop)
        {
    	System.out.println("isFinished");
        }
        return stop;
        
    }

    protected void end() {
    	Robot.pidEncoders.stopTalon();
    	System.out.println("end");
    	stop = true;
    }

    protected void interrupted() {
    	System.out.println("interrupted");
    	end();
    }

}
