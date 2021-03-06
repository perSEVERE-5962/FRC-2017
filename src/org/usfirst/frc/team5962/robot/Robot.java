
package org.usfirst.frc.team5962.robot;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.networktables.NetworkTable;

import org.usfirst.frc.team5962.robot.commands.CameraControlPOV;
import org.usfirst.frc.team5962.robot.commands.CameraControlStick;
import org.usfirst.frc.team5962.robot.commands.RunArcadeGame;
import org.usfirst.frc.team5962.robot.commands.RunAutonomous;
import org.usfirst.frc.team5962.robot.commands.RunJoystickTank;
import org.usfirst.frc.team5962.robot.sensors.RobotEncoder;
import org.usfirst.frc.team5962.robot.sensors.RobotGyro;
import org.usfirst.frc.team5962.robot.sensors.RobotUltrasonicAnalog;
import org.usfirst.frc.team5962.robot.subsystems.BoilerLEDVision;
import org.usfirst.frc.team5962.robot.subsystems.Camera;
import org.usfirst.frc.team5962.robot.subsystems.DistanceVision;
import org.usfirst.frc.team5962.robot.subsystems.Drive;
import org.usfirst.frc.team5962.robot.subsystems.PIDEncoders;
import org.usfirst.frc.team5962.robot.subsystems.ShootingMechansim;
import org.usfirst.frc.team5962.robot.subsystems.SolenoidSubsystem;
import org.usfirst.frc.team5962.robot.subsystems.LineUpWithWall;
import org.usfirst.frc.team5962.robot.subsystems.Autonomous.State;
import org.usfirst.frc.team5962.robot.subsystems.ScalingMechanism;
import org.usfirst.frc.team5962.robot.subsystems.Autonomous;
import org.usfirst.frc.team5962.robot.subsystems.BallIntake;
import org.usfirst.frc.team5962.robot.subsystems.GearLEDVision;
import org.usfirst.frc.team5962.robot.subsystems.GroundGear;
import org.usfirst.frc.team5962.robot.subsystems.Invert;

import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/* The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {
	
	public static boolean mode = true; //true = auto, false = teleop
	//Khushil Bhagat

	public static GearLEDVision gearVision = new GearLEDVision();
	public static BoilerLEDVision boilerVision = new BoilerLEDVision();
	public static DistanceVision distanceVision = new DistanceVision();
	public static PIDEncoders pidEncoders = new PIDEncoders();
	public static CameraControlPOV cameraPOV;
public static CameraControlStick cameraControlStick;
	public static NetworkTable LEDBoiler;
	public static NetworkTable LEDGear;
	public static Autonomous autonomousSubsystem;
	public static SolenoidSubsystem solSub;
	public static GroundGear groundGear;
	
	public static Invert invert;

	public Robot(){
		LEDBoiler = NetworkTable.getTable("GRIP/LEDBoiler");
		LEDGear = NetworkTable.getTable("GRIP/LEDGear");
	}

	public static OI oi;
	public static enum AutonomousVision
	{
		UsingVision,
		NotUsingVision,
	}

	public static enum AutonomousPosition {
		LeftStartingPosition,
		MiddleStartingPosition,
		RightStartingPosition,
	}

	public static enum AutonomousTarget {		
		CrossTheLine,
		PutTheGear,
		ShootTheBall,
		None,	
	}
	public static PIDEncoders pidencoder;
	public static BallIntake intake;
	public static ShootingMechansim ballshooting;
	public static ScalingMechanism scaling;
	//public static LineUpWithWall lineUpWithWall;
	// Gear Manipulator

	//public static Camera camera; //= new Camera();
	
	//	public static GripPipeline gripPipeline;
	public static Drive drive;

	public static RobotUltrasonicAnalog ultrasonicRight;
	public static  RobotUltrasonicAnalog ultrasonicLeft;
	public static RobotGyro gyro= new RobotGyro();
	public static RobotEncoder encoder = new RobotEncoder();

	//private final Object imgLock = new Object();
	Command autonomousCommand;

	SendableChooser<AutonomousVision> autonomousVision;
	SendableChooser<AutonomousPosition> autoPositionChooser;
	SendableChooser<AutonomousTarget> autoFirstTargetChooser;


	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	boolean ran = false;

	public void robotInit() {
		RobotMap.init();
// test
		//camera = new Camera();
		CameraServer.getInstance().startAutomaticCapture();

		invert = new Invert();
		ultrasonicRight = new RobotUltrasonicAnalog(1);
		ultrasonicLeft = new RobotUltrasonicAnalog(0);
		solSub = new SolenoidSubsystem();
		gyro.resetGyro();
		intake = new BallIntake();
		ballshooting = new ShootingMechansim();
		scaling = new ScalingMechanism();
		//lineUpWithWall = new LineUpWithWall(ultrasonicLeft, ultrasonicRight);
		
		groundGear = new GroundGear();
		

		
		pidencoder = new PIDEncoders();
		
		oi = new OI();
		
		drive = new Drive();
		
		initAutonomousPositionChooser();
		initAutonomousTargetChooser();
		initAutonomousVision();

		//cameraPOV = new CameraControlPOV();
		cameraControlStick = new CameraControlStick();
		solSub.deactivateTwo();
	}

	private void initAutonomousVision()
	{
		autonomousVision = new SendableChooser<AutonomousVision>();
		autonomousVision.addDefault("Using Vision ",   AutonomousVision.UsingVision);
		autonomousVision.addObject("Not Using Vision", AutonomousVision.NotUsingVision);
		SmartDashboard.putData("Select if vision is to be used in Autonomous:", autonomousVision);
	}

	private void initAutonomousPositionChooser() {
		autoPositionChooser = new SendableChooser<AutonomousPosition>();
		autoPositionChooser.addDefault("Left Starting Position", AutonomousPosition.LeftStartingPosition);
		autoPositionChooser.addObject("Middle Starting Position", AutonomousPosition.MiddleStartingPosition);
		autoPositionChooser.addObject("Right Starting Position", AutonomousPosition.RightStartingPosition);
		SmartDashboard.putData("Select Autonomous Start Position:", autoPositionChooser);
	}

	private void initAutonomousTargetChooser(){
		autoFirstTargetChooser = new SendableChooser<AutonomousTarget>();
		autoFirstTargetChooser.addDefault("CrossTheLine", AutonomousTarget.CrossTheLine);
		autoFirstTargetChooser.addObject("PutTheGear", AutonomousTarget.PutTheGear);
		autoFirstTargetChooser.addObject("ShootTheBall", AutonomousTarget.ShootTheBall);
		autoFirstTargetChooser.addObject("None", AutonomousTarget.None);
		SmartDashboard.putData("Select Autonomous First Target:", autoFirstTargetChooser);

	}



	/**
	 * This function is called once each time the robot enters Disabled mode.
	 * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
	 */
	public void disabledInit(){
	}

	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select between different autonomous modes
	 * using the dashboard. The sendable chooser code works with the Java SmartDashboard. If you prefer the LabVIEW
	 * Dashboard, remove all of the chooser code and uncomment the getString code to get the auto name from the text box
	 * below the Gyro
	 *
	 * You can add additional auto modes by adding additional commands to the chooser code above (like the commented example)
	 * or additional comparisons to the switch structure below with additional strings & commands.
	 */
	public void autonomousInit() {
		mode = true;

		invert.invert();
		
		encoder.reset();		
		SmartDashboard.putString("Starting Gyro Angle", gyro.getGyroAngle()+"");
		//drive.invert();
		autonomousSubsystem = new Autonomous();

		AutonomousVision autoVision = (AutonomousVision) autonomousVision.getSelected();
		AutonomousPosition position = (AutonomousPosition) autoPositionChooser.getSelected();
		AutonomousTarget obstacle = (AutonomousTarget) autoFirstTargetChooser.getSelected();

		autonomousCommand = new RunAutonomous(autoVision , position, obstacle);

		// schedule the autonomous command (example)
		if (autonomousCommand != null)
		{
			autonomousCommand.start();
		}
	}

	/**
	 * This function is called periodically during autonomous
	 */
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
		//SmartDashboard.putNumber("Encoder - Auto", encoder.getDistance());
		SmartDashboard.putNumber("Auto Ultrasonicright", ultrasonicRight.getRange());
		SmartDashboard.putNumber("Auto Ultrasonicleft", ultrasonicLeft.getRange());
	}

	public void teleopInit() {
		//Command command = new RunJoystickTank(); 
		Command command = new RunArcadeGame();
		
		solSub.deactivateTwo();

		mode = false;
		invert.invert();
		//drive.uninvert();
		
		command.start();
		RobotMap.myRobot.setMaxOutput(0.5);
	}
	
	//	private void pidcontrol()
		//{	
			
//			if(oi.lol() == true )
//			{
//				lineUpWithWall.lineUp();
//			}
			
	//		
	//		
	//		
		//}
	//What is pid?
	//	private void pidcontrolstop()
	//	{
	//
	//		if(oi.pidEncodersstop() == true )
	//		{
	//			pidencoder.stopTalon();
	//		}
	//		
	//	}

	private void intakeBalls() {
		if (oi.gamePad1.getRawAxis(5) >= 0.15 || oi.gamePad1.getRawAxis(5) <= -0.15)
		{
			//intake.inTakeBall();   	
			RobotMap.agitatortalon.set(oi.gamePad1.getRawAxis(5));
		}
		else 
		{  
			RobotMap.agitatortalon.set(0);
		}
	}

	private void shootBalls() {
		if (oi.getShootingBall() == true)
		{
			//ballshooting.shootingBall(); 
			oi.runPID.start();
		}
		else 
		{
			//ballshooting.stop();
			oi.runPID.cancel();
		}
	}
/*
	private void climbTheRope() {
		if (oi.getScaling() == true)
		{
			scaling.scaling();    		
		}
		else 
		{
			scaling.stop();
		}
	}
*/
	/**
	 * This function is called periodically during operator control
	 */
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
		SmartDashboard.putNumber("Ultrasonicright", ultrasonicRight.getRange());
		SmartDashboard.putNumber("Ultrasonicleft", ultrasonicLeft.getRange());
		//SmartDashboard.putNumber("Encoder", encoder.getDistance());

		encoder.getDistance();
		SmartDashboard.putString("Gyro Angle", "" + gyro.gyro.getAngle());
		
		
		//cameraPOV.execute();
		cameraControlStick.execute();
		intakeBalls();
		groundGear.runGroundGearIntake();
		
		//shootBalls();

		//climbTheRope();
		//pidcontrol();

		//pidcontrolstop();

	}

	/**
	 * This function is called periodically during test mode
	 */
	public void testPeriodic() {
		LiveWindow.run();			
	}


}
