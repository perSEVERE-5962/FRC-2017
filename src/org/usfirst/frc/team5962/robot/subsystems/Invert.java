package org.usfirst.frc.team5962.robot.subsystems;

import org.usfirst.frc.team5962.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Invert extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void invert(){
		RobotMap.leftDrive.setInverted(true);
		RobotMap.rightDrive.setInverted(true);
		RobotMap.inverted = 1;
	}
	
	public void uninvert(){
		RobotMap.leftDrive.setInverted(false);
		RobotMap.rightDrive.setInverted(false);
		RobotMap.inverted = -1;
	}
}

