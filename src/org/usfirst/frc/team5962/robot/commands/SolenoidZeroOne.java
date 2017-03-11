package org.usfirst.frc.team5962.robot.commands;

import org.usfirst.frc.team5962.robot.Robot;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class SolenoidZeroOne extends Command {

    public SolenoidZeroOne() {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.solSub);
		//Robot.solSub.activateZero();
        Robot.solSub.activateOne();
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	//Robot.solSub.activateOne();
    	Robot.solSub.activateZero();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	//Robot.solSub.activateZero();
    	Robot.solSub.activateOne();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
