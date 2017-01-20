package org.usfirst.frc.team5962.robot.subsystems;

import org.usfirst.frc.team5962.robot.RobotMap;

import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;

public class BallIntake extends Subsystem {
	
	Victor inTakeVictor = RobotMap.inTakeVictor;
	
	public void inTakeBall()
	{
		inTakeVictor.set(-0.5);
	}
	
	public void stop (){
		inTakeVictor.set(0);
	}

	
	
	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		
	}

}
