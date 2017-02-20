package org.usfirst.frc.team5962.robot.subsystems;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class SolenoidSubsystem extends Subsystem {

	static Solenoid s0 = new Solenoid(0);
	static Solenoid s1 = new Solenoid(1);
	
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void activateZero(){
    	s1.set(false);
    	s0.set(true);
    }
    
    
    public void activateOne(){
    	s0.set(false);
    	s1.set(true);
    }
    
    
}

