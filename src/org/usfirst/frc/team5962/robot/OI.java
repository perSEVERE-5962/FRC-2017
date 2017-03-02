package org.usfirst.frc.team5962.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc.team5962.robot.commands.*;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	
	SolenoidZeroOne solenoidZeroOne = new SolenoidZeroOne();
	RunBoilerLEDVision runBoilerVision = new RunBoilerLEDVision();
	RunGearLEDVision runGearVision = new RunGearLEDVision();
	RunDistanceVision runDistanceVision = new RunDistanceVision();
	RunGearCollection runGearCollection = new RunGearCollection();
	RunScaling runScaling = new RunScaling();
	public RunPID runPID = new RunPID();
	
	public JoystickButton joystickTankMode;

	ChangeDriveDirection changeDriveDirection = new ChangeDriveDirection();


	private String currentDriveMode = "";
	public Joystick gamePad1;
	public Joystick joystickLeft;
	public Joystick joystickRight;

	private JoystickButton buttonOne;
	private JoystickButton buttonTwo;
	private JoystickButton buttonThree;
	private JoystickButton buttonFour;
	private JoystickButton buttonFive;
	private JoystickButton buttonSix;
	

	private JoystickButton gPButtonOne;
	private JoystickButton gPButtonTwo;
	private JoystickButton gPButtonThree;
	//private JoystickButton gPButtonFour;
	private JoystickButton gPButtonFive;
	private JoystickButton gPButtonSix;
	
	private JoystickButton jRButtonOne;
	
	private JoystickButton jLButtonOne;


	public OI() {
		// add game controllers
		gamePad1 = new Joystick(0);
		joystickLeft = new Joystick(1);
		joystickRight = new Joystick(2);
		
		// add buttons

		buttonOne = new JoystickButton(gamePad1, 1);
		buttonTwo = new JoystickButton(gamePad1, 2);
		buttonThree = new JoystickButton(gamePad1, 3);
		buttonFour = new JoystickButton(gamePad1, 4);
		//buttonFive = new JoystickButton(gamePad1, 5);
		buttonSix = new JoystickButton(gamePad1, 6);
	
		// assign buttons
		buttonOne.toggleWhenPressed(solenoidZeroOne);// A
		//buttonTwo.toggleWhenPressed(runBoilerVision);
		//buttonThree.toggleWhenPressed(runGearVision);
		//buttonFour.toggleWhenPressed(runDistanceVision);
		//buttonFive.toggleWhenPressed(runPID);
		buttonSix.toggleWhenPressed(changeDriveDirection);

		gPButtonOne = new JoystickButton(gamePad1, 1);
		gPButtonTwo = new JoystickButton(gamePad1, 2);
		gPButtonThree = new JoystickButton(gamePad1, 3);
		//gPButtonFour = new JoystickButton(gamePad1, 4);
		gPButtonFive = new JoystickButton(gamePad1, 5);
		gPButtonSix = new JoystickButton(gamePad1, 6);
		
		jRButtonOne = new JoystickButton(joystickRight, 1);
		
		jLButtonOne = new JoystickButton(joystickLeft, 1);
		// assign buttons
		
		/* TESTING DRIVER CONFIGURATION
		gPButtonOne.toggleWhenPressed(solenoidZeroOne);
		gPButtonTwo.toggleWhenPressed(runBoilerVision);
		gPButtonThree.toggleWhenPressed(runGearVision);
		gPButtonFour.toggleWhenPressed(runDistanceVision);
		gPButtonFive.toggleWhenPressed(runPID);
		gPButtonSix.toggleWhenPressed(changeDriveDirection);

		
		joystickTankMode = new JoystickButton(joystickRight, 8);
		joystickTankMode.whenPressed(new RunJoystickTank());
		*/
		
		// COMPETITION BUTTON CONFIGURATION
		
		gPButtonOne.toggleWhenPressed(solenoidZeroOne);
		gPButtonTwo.whenPressed(runGearCollection);
		gPButtonThree.whenPressed(runGearVision);
		gPButtonFive.whenPressed(runBoilerVision);
		gPButtonSix.whenPressed(runDistanceVision);
		
		jRButtonOne.toggleWhenActive(runScaling);
		jLButtonOne.toggleWhenActive(runScaling);
	}
	public double getCoPilotRightTrigger() {
		double value = gamePad1.getRawAxis(3);
		return value;
	}

	public boolean getIntakeButton()
	{
		// TODO: These need to be moved to the co-pilot controller
		return gamePad1.getRawAxis(2) > 0.1;
	}

	public boolean getShootingBall()
	{
		// TODO: These need to be moved to the co-pilot controller
		return gamePad1.getRawAxis(3) > 0.1;
	}

	public boolean getScaling()
	{
		// TODO: These need to be moved to the co-pilot controller
		return joystickRight.getRawButton(1);
	}
	
	
//	public boolean pidEncoders()
//	{
//		return gamePad1.getRawButton(6);
//	}
//	
	public boolean lol()
	{
		return gamePad1.getRawButton(5);
	}
	
	public void setCurrentDriverMode(String mode) {
		currentDriveMode = mode;
		SmartDashboard.putString("Driver Mode Choose", currentDriveMode);
	}
	
	public int getCoPilotPOV(){
		int value = gamePad1.getPOV();
		return value;
	}
	
}

