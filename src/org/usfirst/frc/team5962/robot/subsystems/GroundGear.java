package org.usfirst.frc.team5962.robot.subsystems;

import org.usfirst.frc.team5962.robot.Robot;
import org.usfirst.frc.team5962.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class GroundGear extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void runGroundGearIntake(){
    	if(Robot.oi.gamePad1.getRawButton(5) == true && Robot.oi.gamePad1.getRawButton(6) == false){
    		RobotMap.groundGearVictor.set(-0.50);
    	}
    	else if(Robot.oi.gamePad1.getRawButton(6) == true && Robot.oi.gamePad1.getRawButton(5) == false){
    		RobotMap.groundGearVictor.set(1);
    	}
    	else{
    		RobotMap.groundGearVictor.set(0);
    	}
    }
}

