
package org.usfirst.frc.team5962.robot;

import edu.wpi.first.wpilibj.IterativeRobot;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.networktables.NetworkTable;

import org.usfirst.frc.team5962.robot.commands.ExampleCommand;
import org.usfirst.frc.team5962.robot.sensors.RobotEncoder;
import org.usfirst.frc.team5962.robot.sensors.RobotGyro;
import org.usfirst.frc.team5962.robot.sensors.RobotUltrasonicAnalog;
import org.usfirst.frc.team5962.robot.subsystems.Camera;
import org.usfirst.frc.team5962.robot.subsystems.CameraTwo;
import org.usfirst.frc.team5962.robot.subsystems.Drive;
import org.usfirst.frc.team5962.robot.subsystems.ExampleSubsystem;
import org.usfirst.frc.team5962.robot.subsystems.GearMechanism;
import org.usfirst.frc.team5962.robot.subsystems.GripPipeline;
import org.usfirst.frc.team5962.robot.subsystems.LimitSwitchclose;
import org.usfirst.frc.team5962.robot.subsystems.LimitSwitchopen;
import org.usfirst.frc.team5962.robot.subsystems.Pneumatics;
import org.usfirst.frc.team5962.robot.subsystems.ShootingMechnasim;
import org.usfirst.frc.team5962.robot.subsystems.scalingMechnasim;
import org.usfirst.frc.team5962.robot.subsystems.BallIntake;

import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.opencv.core.Rect;
import org.opencv.imgproc.Imgproc;
import com.ctre.CANTalon;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.vision.VisionRunner;
import edu.wpi.first.wpilibj.vision.VisionThread;

 /* The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {

	NetworkTable table;
	
	public Robot(){
		table = NetworkTable.getTable("GRIP/myContentReport");
	}
	
	public static final ExampleSubsystem exampleSubsystem = new ExampleSubsystem();
	public static OI oi;
	private static final int IMG_WIDTH = 320;
	private static final int IMG_HEIGHT = 240;
	
	public static Camera camera;
	public static Pneumatics pneumatics;
	public static CameraTwo camerTwo;

	public static BallIntake intake;
	public static ShootingMechnasim ballshooting;
	public static scalingMechnasim scaling;
	public static GearMechanism gearmechanism;
	public static LimitSwitchopen limitSwitchright;
	public static LimitSwitchclose limitSwitchleft;
	
	private VisionThread visionThread;
	private double centerX = 0.0;
	public static Drive drive;
	
    public static RobotUltrasonicAnalog ultrasonicShoot;
    public static RobotGyro gyro= new RobotGyro();
    public static RobotEncoder encoder = new RobotEncoder();
	
	//private final Object imgLock = new Object();
    Command autonomousCommand;
    SendableChooser chooser;
	//Solenoid exampleSolenoid = new Solenoid(0);

    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
		RobotMap.init();
	
	/*	
		camera = new Camera();
	    UsbCamera camera = CameraServer.getInstance().startAutomaticCapture();
	    camera.setResolution(IMG_WIDTH, IMG_HEIGHT);
	    
	    visionThread = new VisionThread(camera, new GripPipeline(), pipeline -> {
	        if (!pipeline.filterContoursOutput().isEmpty()) {
	            Rect r = Imgproc.boundingRect(pipeline.filterContoursOutput().get(0));
	            synchronized (imgLock) {
	                centerX = r.x + (r.width / 2);
	            }
	        }
	    });
	    visionThread.start();	
	    */
		drive = new Drive();
	    ultrasonicShoot = new RobotUltrasonicAnalog(0);
		oi = new OI();
		pneumatics = new Pneumatics();
		gyro.resetGyro();
		intake = new BallIntake();
		ballshooting = new ShootingMechnasim();
		scaling = new scalingMechnasim();
		gearmechanism = new GearMechanism();
		limitSwitchright = new LimitSwitchopen();
		limitSwitchleft = new LimitSwitchclose(); 
      //  chooser = new SendableChooser();
    //    chooser.addDefault("Default Auto", new ExampleCommand());
  //     chooser.addObject("My Auto", new MyAutoCommand());
//        SmartDashboard.putData("Auto mode", chooser);

	        
	}
	
	/**
     * This function is called once each time the robot enters Disabled mode.
     * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
     */
    public void disabledInit(){
    	// start with the LED ring off
//		RobotMap.ledVictor.set(0);		
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
//    	RobotMap.ledVictor.set(1);
    }

    /**
     * This function is called periodically during autonomous
     */
  /*  public void autonomousPeriodic() {
        Scheduler.getInstance().run();
    	double centerX;
    	synchronized (imgLock) {
    		centerX = this.centerX;
    	}
    	double turn = centerX - (IMG_WIDTH / 2);
    	RobotMap.myRobot.arcadeDrive(-0.6, turn * 0.005);
    }
*/
    public void teleopInit() {
		// This makes sure that the autonomous stops running when
        // teleop starts running. If you want the autonomous to 
        // continue until interrupted by another command, remove
        // this line or comment it out.
//        if (autonomousCommand != null) autonomousCommand.cancel();
    	
    	// start with the LED ring off
//		RobotMap.ledVictor.set(0);		
    }

    /**
     * This function is called periodically during operator control
     */
    Solenoid green = new Solenoid(0);
    Solenoid red = new Solenoid(1);
    Solenoid blue = new Solenoid(2);
    Solenoid yellow = new Solenoid(3);
    boolean downbuttonpress = false;
    boolean upbuttonpress = false;
    public void teleopPeriodic() {
        Scheduler.getInstance().run();
    	SmartDashboard.putNumber("UR", ultrasonicShoot.getRange());
    	
     
    	if (oi.getgearopen() == true)
    	{
    		downbuttonpress = true;
    		gearmechanism.openthegear();
    		
    	}
    	if(downbuttonpress == true && limitSwitchright.islimitSwitchopen() == false )
    	{
    		
    		gearmechanism.gearStop();
    		downbuttonpress = false;
    	}
    	else if(downbuttonpress == true)
    	{
    		gearmechanism.openthegear();
    	}
    	
    	
    	
    	if (oi.getgearclose() == true)
    	{
    		upbuttonpress = true;
    		gearmechanism.closethegear();
    		
    	}
    	if(upbuttonpress == true && limitSwitchleft.islimitSwitchclose() == false)
    	{
    		gearmechanism.gearStop();
    		upbuttonpress = false;
    	}
    	else if (upbuttonpress == true)
    	//else 
    	{
    		gearmechanism.closethegear();
    		//gearmechanism.gearStop();
    	}
    	if (oi.getIntakeButton() == true)
    	{
    		intake.inTakeBall();
    		
    	}
    	else 
    	{
    		intake.stop();
    	}
    	if (oi.getShootingBall() == true)
    	{
    		ballshooting.shootingBall();
    		
    	}
    	else 
    	{
    		ballshooting.stop();
    	}

    	if (oi.getScaling() == true)
    	{
    		scaling.scaling();
    		
    	}
    	else 
    	{
    		scaling.stop();
    	}
    	if(oi.getGreenAButton() == true)
    	{
    		green.set(true);
    		
    	}
    	else
    	{
    		green.set(false);
    	}
    	
    	if(oi.getRedBButton() == true)
    	{
    		red.set(true);
    		
    	}
    	else
    	{
    		red.set(false);
    	}
    	
    	if(oi.getBlueXButton() == true)
    	{
    		blue.set(true);
    		
    	}
    	else
    	{
    		blue.set(false);
    	}
    	if(oi.getYellowYButton() == true)
    	{
    		yellow.set(true);
    		
    	}
    	else
    	{
    		yellow.set(false);
    	}
    	
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
//			RobotMap.ledVictor.set(1);		
//		} else {
//			RobotMap.ledVictor.set(0);		
//		}
			
	}
}
