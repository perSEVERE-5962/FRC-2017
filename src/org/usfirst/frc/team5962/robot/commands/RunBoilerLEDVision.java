package org.usfirst.frc.team5962.robot.commands;

import org.usfirst.frc.team5962.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class RunBoilerLEDVision extends Command {

    public RunBoilerLEDVision() {
        requires(Robot.boilerVision);
    }

    protected void initialize() {
    	Robot.boilerVision.sense = true;
    }

    protected void execute() {
    	Robot.boilerVision.runBoilerVision();
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    	Robot.boilerVision.sense = false;
    }

    protected void interrupted() {
    	end();
    }
}
