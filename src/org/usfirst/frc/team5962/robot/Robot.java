
package org.usfirst.frc.team5962.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.networktables.NetworkTable;

import org.usfirst.frc.team5962.robot.commands.RunArcadeGame;
import org.usfirst.frc.team5962.robot.commands.RunAutonomous;
import org.usfirst.frc.team5962.robot.sensors.RobotEncoder;
import org.usfirst.frc.team5962.robot.sensors.RobotGyro;
import org.usfirst.frc.team5962.robot.sensors.RobotUltrasonicAnalog;
import org.usfirst.frc.team5962.robot.subsystems.BoilerLEDVision;
import org.usfirst.frc.team5962.robot.subsystems.Camera;
import org.usfirst.frc.team5962.robot.subsystems.DistanceVision;
import org.usfirst.frc.team5962.robot.subsystems.Drive;
import org.usfirst.frc.team5962.robot.subsystems.GearMechanism;
import org.usfirst.frc.team5962.robot.subsystems.ShootingMechansim;
import org.usfirst.frc.team5962.robot.subsystems.SolenoidSubsystem;
import org.usfirst.frc.team5962.robot.subsystems.Autonomous.State;
import org.usfirst.frc.team5962.robot.subsystems.ScalingMechanism;
import org.usfirst.frc.team5962.robot.subsystems.Autonomous;
import org.usfirst.frc.team5962.robot.subsystems.BallIntake;
import org.usfirst.frc.team5962.robot.subsystems.GearLEDVision;

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
	

	public static GearLEDVision gearVision = new GearLEDVision();
	public static BoilerLEDVision boilerVision = new BoilerLEDVision();
	public static DistanceVision distanceVision = new DistanceVision();

	public static NetworkTable LEDBoiler;
	public static NetworkTable LEDGear;
	public static Autonomous autonomousSubsystem;
	public static SolenoidSubsystem solSub;

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

	public static BallIntake intake;
	public static ShootingMechansim ballshooting;
	public static ScalingMechanism scaling;

	// Gear Manipulator
	public static GearMechanism gearmechanism;

	public static Camera camera;

	//	public static GripPipeline gripPipeline;
	public static Drive drive;

	public static RobotUltrasonicAnalog ultrasonic;
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

		camera = new Camera();
		
		drive = new Drive();
		ultrasonic = new RobotUltrasonicAnalog(0);
		solSub = new SolenoidSubsystem();
		oi = new OI();
		gyro.resetGyro();
		intake = new BallIntake();
		ballshooting = new ShootingMechansim();
		scaling = new ScalingMechanism();
		
		
		gearmechanism = new GearMechanism();

		initAutonomousPositionChooser();
		initAutonomousTargetChooser();
		initAutonomousVision();
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
		
		encoder.reset();		
		SmartDashboard.putString("Starting Gyro Angle", gyro.getGyroAngle()+"");
		drive.invert();
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
		SmartDashboard.putNumber("Encoder - Auto", encoder.getDistance());
	}

	public void teleopInit() {
		mode = false;
		
		drive.uninvert();
		
		Command command = new RunArcadeGame();
		command.start();
	}

	private void intakeBalls() {
		if (oi.getIntakeButton() == true)
		{
			intake.inTakeBall();   		
		}
		else 
		{
			intake.stop();
		}
	}

	private void shootBalls() {
		if (oi.getShootingBall() == true)
		{
			ballshooting.shootingBall();   		
		}
		else 
		{
			ballshooting.stop();
		}
	}

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

	/**
	 * This function is called periodically during operator control
	 */
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
		SmartDashboard.putNumber("Ultrasonic", ultrasonic.getRange());
		SmartDashboard.putNumber("Encoder", encoder.getDistance());

		intakeBalls();
		shootBalls();
		climbTheRope();
	}

	/**
	 * This function is called periodically during test mode
	 */
	public void testPeriodic() {
		LiveWindow.run();			
	}


}
