package org.usfirst.frc.team5962.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

import org.usfirst.frc.team5962.robot.commands.GearSolenoid;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	public Joystick gamePad1;
	public Joystick joystickLeft;
	
	private JoystickButton buttonOne;
	
	public OI() {
		// add game controllers
		gamePad1 = new Joystick(0);
		joystickLeft = new Joystick(2);
		
		buttonOne = new JoystickButton(gamePad1, 1);		
		buttonOne.toggleWhenPressed(new GearSolenoid());
	}
	
	public boolean getIntakeButton()
	{
		return joystickLeft.getRawButton(3);
	}
	public boolean getShootingBall()
	{
		return joystickLeft.getRawButton(4);
	}
	public boolean getScaling()
	{
		return joystickLeft.getRawButton(5);
	}
	
	
//	public boolean getgearclose()
//	{
//		return joystickLeft.getRawButton(6);
//	}
//	public boolean getgearopen()
//	{
//		return joystickLeft.getRawButton(2);
//	}
}

