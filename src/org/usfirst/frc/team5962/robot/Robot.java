
package org.usfirst.frc.team5962.robot;

import edu.wpi.cscore.CvSink;
import edu.wpi.cscore.CvSource;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Timer;
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
import org.usfirst.frc.team5962.robot.subsystems.CameraTwo;
import org.usfirst.frc.team5962.robot.subsystems.DistanceVision;
import org.usfirst.frc.team5962.robot.subsystems.Drive;
import org.usfirst.frc.team5962.robot.subsystems.GearMechanism;
import org.usfirst.frc.team5962.robot.subsystems.LimitSwitchclose;
import org.usfirst.frc.team5962.robot.subsystems.LimitSwitchopen;
import org.usfirst.frc.team5962.robot.subsystems.ShootingMechansim;
import org.usfirst.frc.team5962.robot.subsystems.Autonomous.state;
import org.usfirst.frc.team5962.robot.subsystems.ScalingMechanism;
import org.usfirst.frc.team5962.robot.subsystems.Autonomous;
import org.usfirst.frc.team5962.robot.subsystems.BallIntake;
import org.usfirst.frc.team5962.robot.subsystems.GearLEDVision;
import org.usfirst.frc.team5962.robot.subsystems.GripPipeline;

import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.vision.VisionThread;

 /* The VM is configured to automatically run this class, and to call the
=======
import java.util.ArrayList;
import java.util.Enumeration;

import org.opencv.core.MatOfPoint;
import org.opencv.core.Rect;
import org.opencv.imgproc.Imgproc;
import com.ctre.CANTalon;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.vision.VisionRunner;
import edu.wpi.first.wpilibj.vision.VisionThread;

import org.usfirst.frc.team5962.robot.sensors.*;
/**
 * The VM is configured to automatically run this class, and to call the
>>>>>>> KoopaFreak600
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {

	
	public static GearLEDVision gearVision = new GearLEDVision();
	public static BoilerLEDVision boilerVision = new BoilerLEDVision();
	public static DistanceVision distanceVision = new DistanceVision();
	
	public static NetworkTable LEDBoiler;
	public NetworkTable LEDPeg;
	NetworkTable BlueBoiler;
	NetworkTable RedBoiler;
	NetworkTable table;
	public static Autonomous autonomousSubsystem;
	
	
	/*
	boolean bGot = false;
	boolean gGot = false;
	boolean gotFin = false;
	
	//boolean moved = false;
	
	
	
	double[] LBAreas = null;
	double[] LBCenterX = null;
	double[] LPAreas = null;
	double[] LPCenterX = null;
	
	int bLength = 0;
	int gLength = 0;
	int bPlace = 0;
	int gPlace = 0;
	double bBiggestValue = 0.0;
	double gBiggestValueOne = 0.0;
	double gBiggestValueTwo = 0.0;
	int bBiggestPlace = 0;
	int gBiggestPlaceOne = 0;
	int gBiggestPlaceTwo = 0;
	int timesRan = 0;
	
	double avgCenter = 0.0;
	
	int ImgHeight = 480;
	int ImgWidth = 640;
	
	boolean less = false;
	
	boolean senseBoiler = false;
	boolean senseGear = true;
	*/
	
	public static Solenoid s0 = new Solenoid(0);
	public static Solenoid s1 = new Solenoid(1);
	public Robot(){
		LEDBoiler = NetworkTable.getTable("GRIP/LEDBoiler");
		LEDPeg = NetworkTable.getTable("GRIP/LEDPeg");
		BlueBoiler = NetworkTable.getTable("GRIP/BlueBoiler");
		RedBoiler = NetworkTable.getTable("GRIP/RedBoiler");
		table = NetworkTable.getTable("GRIP/myContoursReport");
		
		
	}
	
	public static OI oi;
	public static enum AutonomousChoosing
	{
		withGreenLight,
		withOutGreenLight,
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
	
	
	
	
	
	private static final int IMG_WIDTH = 320;
	private static final int IMG_HEIGHT = 240;
	

	public static BallIntake intake;
	public static ShootingMechansim ballshooting;
	public static ScalingMechanism scaling;
	
	// Gear Manipulator
	public static GearMechanism gearmechanism;
	public static LimitSwitchopen limitSwitchright;
	public static LimitSwitchclose limitSwitchleft;
	
	public static Camera camera;
	public static CameraTwo cameraTwo;
	
	private VisionThread visionThread;
	public static GripPipeline gripPipeline;
	//private double centerX = 0.0;
	public static Drive drive;
	
	//private RobotDrive visionDrive;
	
    public static RobotUltrasonicAnalog ultrasonicShoot;
    public static RobotUltrasonicAnalog ultrasonictest;
    public static RobotGyro gyro= new RobotGyro();
    public static RobotEncoder encoder = new RobotEncoder();
	
	//private final Object imgLock = new Object();
    Command autonomousCommand;

    SendableChooser autonomouschoosing;
    SendableChooser autoPositionChooser;
    SendableChooser autoFirstTargetChooser;


    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
	boolean ran = false;
	
    public void robotInit() {
		RobotMap.init();
		
		camera = new Camera();
		
		drive = new Drive();
	    ultrasonicShoot = new RobotUltrasonicAnalog(0);
	    ultrasonictest = new RobotUltrasonicAnalog(1);
		oi = new OI();
		gyro.resetGyro();
		intake = new BallIntake();
		ballshooting = new ShootingMechansim();
		scaling = new ScalingMechanism();
		
		gearmechanism = new GearMechanism();
		limitSwitchright = new LimitSwitchopen();
		limitSwitchleft = new LimitSwitchclose(); 
       
		initAutonomousPositionChooser();
		initAutonomousTargetChooser();
		initAutonomousChoosing();
		s1.set(true);
	}
    private void initAutonomousChoosing()
    {
    	autonomouschoosing = new SendableChooser();
    	autonomouschoosing.addDefault(" withgreenlight ",   AutonomousChoosing.withGreenLight);
    	autonomouschoosing.addObject(" withoutgreenlight ", AutonomousChoosing.withOutGreenLight);
    }
    private void initAutonomousPositionChooser() {
    	autoPositionChooser = new SendableChooser();
    	autoPositionChooser.addDefault("LeftStartingPosition", AutonomousPosition.LeftStartingPosition);
    	autoPositionChooser.addObject("MiddleStartingPosition", AutonomousPosition.MiddleStartingPosition);
    	autoPositionChooser.addObject("RightStartingPosition", AutonomousPosition.RightStartingPosition);
    	SmartDashboard.putData("Select Autonomous Start Position:", autoPositionChooser);
    }
    
    private void initAutonomousTargetChooser(){
    	autoFirstTargetChooser = new SendableChooser();
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
    	encoder.reset();		
		SmartDashboard.putString("Starting Gyro Angle", gyro.getGyroAngle()+"");
		Autonomous.State = state.croosline;
		
		autonomousSubsystem = new Autonomous();
		autonomousSubsystem.State = state.croosline; 
		
		
		AutonomousChoosing autochoosing = (AutonomousChoosing) autonomouschoosing.getSelected();
		AutonomousPosition position = (AutonomousPosition) autoPositionChooser.getSelected();
		AutonomousTarget obstacle = (AutonomousTarget) autoFirstTargetChooser.getSelected();
		
		autonomousCommand = new RunAutonomous(autochoosing , position, obstacle);


		// schedule the autonomous command (example)
		if (autonomousCommand != null)
		{
			SmartDashboard.putString("Starting autonomousCommand", "TRUE");
			autonomousCommand.start();
		}
    }
    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
    }

    public void teleopInit() {
		Command command = new RunArcadeGame();
		command.start();
    }

//    boolean downbuttonpress = false;
//    private void openGear() {
//    	if (oi.getgearopen() == true)
//    	{
//    		downbuttonpress = true;
//    		gearmechanism.openthegear();	
//    	}
//    	if(downbuttonpress == true && limitSwitchright.islimitSwitchopen() == false )
//    	{	
//    		gearmechanism.gearStop();
//    		downbuttonpress = false;
//    	}
//    	else if(downbuttonpress == true)
//    	{
//    		gearmechanism.openthegear();
//    	}
//    }
    
//    boolean upbuttonpress = false;
//    private void closeGear() {
//    	if (oi.getgearclose() == true)
//    	{
//    		upbuttonpress = true;
//    		gearmechanism.closethegear();  		
//    	}
//    	if(upbuttonpress == true && limitSwitchleft.islimitSwitchclose() == false)
//    	{
//    		gearmechanism.gearStop();
//    		upbuttonpress = false;
//    	}
//    	else if (upbuttonpress == true)
//    	//else 
//    	{
//    		gearmechanism.closethegear();
//    		//gearmechanism.gearStop();
//    	}
//    }
    
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
    	SmartDashboard.putNumber("UR", ultrasonicShoot.getRange());
    	SmartDashboard.putNumber("Ultrasinictest", ultrasonictest.getRange());
    	SmartDashboard.putNumber("ENCODERS", encoder.getDistance());
//    	openGear();
//    	closeGear();
    	
    	//intakeBalls();
    	//shootBalls();
    	//climbTheRope();
    	
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
       LiveWindow.run();			
	}
    
    
}
