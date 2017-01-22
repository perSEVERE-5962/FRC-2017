package org.usfirst.frc.team5962.robot.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;

public class LimitSwitchopen extends Subsystem{
	DigitalInput limitSwitchopen = new DigitalInput(4);

	public boolean islimitSwitchopen()
	{
		return limitSwitchopen.get();
	}
	
	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		
		
	}

}
