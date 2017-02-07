package org.usfirst.frc.team5962.robot.commands;

import org.usfirst.frc.team5962.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class RunDistanceVision extends Command {

    public RunDistanceVision() {
        requires(Robot.distanceVision);
    }

    protected void initialize() {
    	Robot.distanceVision.sense = true;
    }

    protected void execute() {
    	Robot.distanceVision.runDistanceVision();
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    	Robot.distanceVision.sense = false;
    }

    protected void interrupted() {
    	end();
    }
}
