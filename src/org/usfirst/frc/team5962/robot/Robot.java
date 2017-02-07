
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

import org.usfirst.frc.team5962.robot.commands.ExampleCommand;
import org.usfirst.frc.team5962.robot.commands.RunArcadeGame;
import org.usfirst.frc.team5962.robot.sensors.RobotUltrasonicAnalog;
import org.usfirst.frc.team5962.robot.subsystems.BoilerLEDVision;
import org.usfirst.frc.team5962.robot.subsystems.Camera;
import org.usfirst.frc.team5962.robot.subsystems.CameraTwo;
import org.usfirst.frc.team5962.robot.subsystems.DistanceVision;
import org.usfirst.frc.team5962.robot.subsystems.Drive;
import org.usfirst.frc.team5962.robot.subsystems.ExampleSubsystem;
import org.usfirst.frc.team5962.robot.subsystems.GearLEDVision;
import org.usfirst.frc.team5962.robot.subsystems.GripPipeline;

import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

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
	
	public static final ExampleSubsystem exampleSubsystem = new ExampleSubsystem();
	public static OI oi;
	//private static final int IMG_WIDTH = 320;
	//private static final int IMG_HEIGHT = 240;
	
	public static Camera camera;
	public static CameraTwo cameraTwo;
	
	private VisionThread visionThread;
	public static GripPipeline gripPipeline;
	//private double centerX = 0.0;
	public static Drive drive;
	
	//private RobotDrive visionDrive;
	
    public static RobotUltrasonicAnalog ultrasonicShoot;
	
	//private final Object imgLock = new Object();
//    Command autonomousCommand;
//    SendableChooser chooser;
//	Solenoid exampleSolenoid = new Solenoid(0);

    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
	boolean ran = false;
	
    public void robotInit() {
		RobotMap.init();
		camera = new Camera();
		cameraTwo = new CameraTwo();
		drive = new Drive();
	    ultrasonicShoot = new RobotUltrasonicAnalog(0);
		oi = new OI();
		
		//visionDrive = new RobotDrive(RobotMap.robotLeftVictor1, RobotMap.robotVictor8);
		
		s1.set(true);
		//gripPipeline = new GripPipeline();
		
//		double[] LBdefaultValue = new double[0];
//		double[] LPdefaultValue = new double[0];
//		double[] BBdefaultValue = new double[0];
//		double[] RBdefaultValue = new double[0];
//		 new Thread(() -> {
//		while (true) {
//			double[] LBareas = LEDBoiler.getNumberArray("LBArea", LBdefaultValue);
//			double[] LPareas = LEDPeg.getNumberArray("LPArea", LPdefaultValue);
//			double[] BBareas = BlueBoiler.getNumberArray("BBArea", BBdefaultValue);
//			double[] RBareas = RedBoiler.getNumberArray("RBArea", RBdefaultValue);
//			for(double LBarea : LBareas) {
//				System.out.println(LBarea + " ");
//			}
//			for(double LParea : LPareas) {
//				System.out.println(LParea + " ");
//			}
//			for(double BBarea : BBareas) {
//				System.out.println(BBarea + " ");
//			}
//			for(double RBarea : RBareas) {
//				System.out.println(RBarea + " ");
//			}
//			
//			System.out.println();
//			Timer.delay(1);
//		}
//		 }).start();
//	    UsbCamera camera = CameraServer.getInstance().startAutomaticCapture();
//	    camera.setResolution(IMG_WIDTH, IMG_HEIGHT);
//	    
//	    visionThread = new VisionThread(camera, new GripPipeline(), pipeline -> {
//	        if (!pipeline.filterContoursOutput().isEmpty()) {
//	            Rect r = Imgproc.boundingRect(pipeline.filterContoursOutput().get(0));
//	            synchronized (imgLock) {
//	                centerX = r.x + (r.width / 2);
//	            }
//	        }
//	    });
//	    visionThread.start();		


//        chooser = new SendableChooser();
//        chooser.addDefault("Default Auto", new ExampleCommand());
////        chooser.addObject("My Auto", new MyAutoCommand());
//        SmartDashboard.putData("Auto mode", chooser);

	        
	}
	
	/**
     * This function is called once each time the robot enters Disabled mode.
     * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
     */
    public void disabledInit(){
    	// start with the LED ring off
		RobotMap.ledVictor.set(0);		
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
//        autonomousCommand = (Command) chooser.getSelected();
        
		/* String autoSelected = SmartDashboard.getString("Auto Selector", "Default");
		switch(autoSelected) {
		case "My Auto":
			autonomousCommand = new MyAutoCommand();
			break;
		case "Default Auto":
		default:
			autonomousCommand = new ExampleCommand();
			break;
		} */
    	
    	// schedule the autonomous command (example)
//        if (autonomousCommand != null) autonomousCommand.start();
    	
    	// turn the LED ring on
    	RobotMap.ledVictor.set(1);
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
    	
    	
    }

    public void teleopInit() {
		// This makes sure that the autonomous stops running when
        // teleop starts running. If you want the autonomous to 
        // continue until interrupted by another command, remove
        // this line or comment it out.
//        if (autonomousCommand != null) autonomousCommand.cancel();
    	
    	// start with the LED ring off
		//RobotMap.ledVictor.set(0);		
		//Command command = new RunArcadeGame();
		//command.start();
    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
        Scheduler.getInstance().run();
    	SmartDashboard.putNumber("UR", ultrasonicShoot.getRange());
    	
    		
    }
    
    /**
     * This function is called periodically during test mode
     */
    int count = 0;
    public void testPeriodic() {
       LiveWindow.run();
//		if (count <=300 || count > 600) {
//			exampleSolenoid.set(true);
//		}
//
//    else if (count > 300 && count <=600) {
//    		exampleSolenoid.set(false);
//		}
//    	count++;
    	
   	
//		if (oi.getCoPilotRightTrigger() >= 0.5)
//		{	
			RobotMap.ledVictor.set(1);		
//		} else {
//			RobotMap.ledVictor.set(0);		
//		}
	}
    
    
}
