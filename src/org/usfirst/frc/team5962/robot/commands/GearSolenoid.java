package org.usfirst.frc.team5962.robot.commands;
import org.usfirst.frc.team5962.robot.Robot;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class GearSolenoid extends Command {
	private Solenoid solenoid0 = new Solenoid(0);
	private Solenoid solenoid1 = new Solenoid(1);

    public GearSolenoid() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	
    	solenoid1.set(true);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	solenoid0.set(true);
    	solenoid1.set(false);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	solenoid0.set(false);
    	solenoid1.set(true);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
