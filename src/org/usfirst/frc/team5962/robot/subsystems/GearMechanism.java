package org.usfirst.frc.team5962.robot.subsystems;


import edu.wpi.first.wpilibj.command.Subsystem;

public class GearMechanism  extends Subsystem {
//	Victor gearvictor = RobotMap.gearvictor;

	public void closethegear ()
	{
//		gearvictor.set(-0.5);
	}


	public void openthegear()
	{
//		gearvictor.set(1);
	}
	public void gearStop()
	{
//		gearvictor.set(0);
	}



	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub

	}

}
