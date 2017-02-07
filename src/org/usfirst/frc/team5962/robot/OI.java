package org.usfirst.frc.team5962.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import org.usfirst.frc.team5962.robot.commands.*;

import org.usfirst.frc.team5962.robot.commands.SolenoidZeroOne;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
    //// CREATING BUTTONS
    // One type of button is a joystick button which is any button on a joystick.
    // You create one by telling it which joystick it's on and which button
    // number it is.
    // Joystick stick = new Joystick(port);
    // Button button = new JoystickButton(stick, buttonNumber);
    
    // There are a few additional built in buttons you can use. Additionally,
    // by subclassing Button you can create custom triggers and bind those to
    // commands the same as any other Button.
    
    //// TRIGGERING COMMANDS WITH BUTTONS
    // Once you have a button, it's trivial to bind it to a button in one of
    // three ways:
    
    // Start the command when the button is pressed and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenPressed(new ExampleCommand());
    
    // Run the command while the button is being held down and interrupt it once
    // the button is released.
    // button.whileHeld(new ExampleCommand());
    
    // Start the command when the button is released  and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenReleased(new ExampleCommand());
	
	SolenoidZeroOne solenoidZeroOne = new SolenoidZeroOne();
	RunBoilerLEDVision runBoilerVision = new RunBoilerLEDVision();
	RunGearLEDVision runGearVision = new RunGearLEDVision();
	RunDistanceVision runDistanceVision = new RunDistanceVision();
	
	public Joystick gamePad1;
	public Joystick joystickLeft;
	private String currentDriveMode = "";
	private JoystickButton buttonOne;
	private JoystickButton buttonTwo;
	private JoystickButton buttonThree;
	private JoystickButton buttonFour;

	public OI() {
		// add game controllers
		gamePad1 = new Joystick(0);
		joystickLeft = new Joystick(2);
		
		// add buttons
		buttonOne = new JoystickButton(gamePad1, 1);
		buttonTwo = new JoystickButton(gamePad1, 2);
		buttonThree = new JoystickButton(gamePad1, 3);
		buttonFour = new JoystickButton(gamePad1, 4);
		buttonOne.toggleWhenPressed(solenoidZeroOne);
		buttonTwo.toggleWhenPressed(runBoilerVision);
		buttonThree.toggleWhenPressed(runGearVision);
		buttonFour.toggleWhenPressed(runDistanceVision);
	}
	public double getCoPilotRightTrigger() {
		double value = gamePad1.getRawAxis(3);
		return value;
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

