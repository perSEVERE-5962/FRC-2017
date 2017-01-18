package org.usfirst.frc.team5962.robot.subsystems;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Pneumatics {
	Compressor c = new Compressor(0);
	
	public Pneumatics() {
	
	
	c.setClosedLoopControl(true);
	
	//c.setClosedLoopControl(false);	
	
	//boolean enabled = c.enabled();
	//boolean pressureSwitch = c.getPressureSwitchValue();
	//double current =  c.getCompressorCurrent();
	}
	



}
