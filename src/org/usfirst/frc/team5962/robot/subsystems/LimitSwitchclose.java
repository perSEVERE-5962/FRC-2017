package org.usfirst.frc.team5962.robot.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;

public class LimitSwitchclose extends Subsystem{

	DigitalInput limitSwitchclose = new DigitalInput(5);

	public boolean islimitSwitchclose()
	{
		return limitSwitchclose.get();
	}
	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		
	}
	

}
