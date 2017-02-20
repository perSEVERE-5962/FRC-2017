package org.usfirst.frc.team5962.robot.subsystems;

import org.usfirst.frc.team5962.robot.RobotMap;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;

public class BallIntake extends Subsystem {
	
	CANTalon ballfeeder = RobotMap.ballfeedertalon;
	CANTalon agitator = RobotMap.agitatortalon;
	//Victor inTakeVictor = RobotMap.inTakeVictor;
	
	public void inTakeBall()
	{
		ballfeeder.set(-1);
		agitator.set(-1);
	}
	
	public void stop (){
		ballfeeder.set(0);
		agitator.set(0);
	}

	
	
	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		
	}

}
