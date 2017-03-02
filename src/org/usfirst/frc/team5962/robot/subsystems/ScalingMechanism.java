package org.usfirst.frc.team5962.robot.subsystems;

import org.usfirst.frc.team5962.robot.RobotMap;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;

public class ScalingMechanism extends Subsystem {
	//Victor scalingvictor = RobotMap.scalingVictor;
	
	CANTalon scalingtalon = RobotMap.scalingtalon;
	public void scaling(){
		scalingtalon.set(-1);
	}

	public void stop(){
		scalingtalon.set(0);
	}
	
	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub

	}

}
