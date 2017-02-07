package org.usfirst.frc.team5962.robot.subsystems;

import org.usfirst.frc.team5962.robot.RobotMap;

import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;

public class ScalingMechanism extends Subsystem {
	Victor scalingvictor = RobotMap.scalingVictor;

	public void scaling()
	{
		scalingvictor.set(-0.5);
	}

	public void stop (){
		scalingvictor.set(0);
	}
	
	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub

	}

}
