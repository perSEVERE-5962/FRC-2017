package org.usfirst.frc.team5962.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team5962.robot.commands.ExampleCommand;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	public Joystick gamePad1;
	public Joystick joystickLeft;
	
	
	public JoystickButton leftJoystickButton3;
	
	public OI() {

		// add game controllers
		gamePad1 = new Joystick(0);
		joystickLeft = new Joystick(2);
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
	public boolean getGreenAButton()
	{
	return gamePad1.getRawButton(1);
	}
	public boolean getRedBButton()
	{
	return gamePad1.getRawButton(2);
	}
	public boolean getBlueXButton()
	{
	return gamePad1.getRawButton(3);
	}
	public boolean getYellowYButton()
	{
	return gamePad1.getRawButton(4);
	}
	
}

