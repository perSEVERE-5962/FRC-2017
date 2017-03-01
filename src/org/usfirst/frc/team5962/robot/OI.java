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
		
		joystickTankMode = new JoystickButton(joystickRight, 8);
		joystickTankMode.whenPressed(new RunJoystickTank());
	}
	public double getCoPilotRightTrigger() {
		double value = gamePad1.getRawAxis(3);
		return value;
	}

	public boolean getIntakeButton()
	{
		// TODO: These need to be moved to the co-pilot controller
		return joystickLeft.getRawButton(3);
	}

	public boolean getShootingBall()
	{
		// TODO: These need to be moved to the co-pilot controller
		return joystickLeft.getRawButton(4);
	}

	public boolean getScaling()
	{
		// TODO: These need to be moved to the co-pilot controller
		return joystickLeft.getRawButton(5);
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

