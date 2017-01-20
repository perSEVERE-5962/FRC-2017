package org.usfirst.frc.team5962.robot.subsystems;

import org.usfirst.frc.team5962.robot.RobotMap;

import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;

public class ShootingMechnasim extends Subsystem {

	Victor ballShootingvictor = RobotMap.ballShootingvictor;
	public void shootingBall()
	{

		ballShootingvictor.set(-0.5);
	}

	public void stop (){
		ballShootingvictor.set(0);
	}
 
	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		
	}

}
