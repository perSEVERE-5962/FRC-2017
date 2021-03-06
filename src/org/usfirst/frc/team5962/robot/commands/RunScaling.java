package org.usfirst.frc.team5962.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team5962.robot.subsystems.ScalingMechanism;
/**
 *
 */
public class RunScaling extends Command {
	
	ScalingMechanism scaling = new ScalingMechanism();

    public RunScaling() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(scaling);
    	scaling.stop();
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	scaling.stop();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	scaling.scaling();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	scaling.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
