package org.usfirst.frc.team5962.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team5962.robot.Robot;

/**
 *
 */
public class RunGearLEDVision extends Command {
	
	
    public RunGearLEDVision() {
    	requires(Robot.gearVision);
    }

    protected void initialize() {
    	Robot.gearVision.sense = true;
    }

    protected void execute() {
    	Robot.gearVision.runGearVision();
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    	Robot.gearVision.sense = false;
    }

    protected void interrupted() {
    	end();
    }
}
