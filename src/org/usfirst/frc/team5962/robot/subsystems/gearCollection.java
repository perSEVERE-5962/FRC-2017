package org.usfirst.frc.team5962.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import org.usfirst.frc.team5962.robot.Robot;
import org.usfirst.frc.team5962.robot.RobotMap;

/**
 *
 */
public class gearCollection extends Subsystem {
    public void initDefaultCommand() {
    }
    
    public void ultrasonicCollection(){
    	if(Robot.ultrasonicRight.getRange() >= 7){
    		
    	}
    }
    
    public void encoderCollection(){
    	
    }
}

