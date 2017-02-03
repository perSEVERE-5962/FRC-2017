package org.usfirst.frc.team5962.robot.commands;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class SolenoidZeroOne
extends Command {
	Solenoid s0 = new Solenoid(0);
	Solenoid s1 = new Solenoid(1);

    public SolenoidZeroOne() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
		s1.set(true);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	s0.set(true);
    	s1.set(false);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	s0.set(false);
    	s1.set(true);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
