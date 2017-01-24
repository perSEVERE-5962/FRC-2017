
package org.usfirst.frc.team5962.robot;

import edu.wpi.first.wpilibj.IterativeRobot;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.networktables.NetworkTable;

import org.usfirst.frc.team5962.robot.sensors.RobotEncoder;
import org.usfirst.frc.team5962.robot.sensors.RobotGyro;
import org.usfirst.frc.team5962.robot.sensors.RobotUltrasonicAnalog;
import org.usfirst.frc.team5962.robot.subsystems.Camera;
import org.usfirst.frc.team5962.robot.subsystems.CameraTwo;
import org.usfirst.frc.team5962.robot.subsystems.Drive;
import org.usfirst.frc.team5962.robot.subsystems.GearMechanism;
import org.usfirst.frc.team5962.robot.subsystems.LimitSwitchclose;
import org.usfirst.frc.team5962.robot.subsystems.LimitSwitchopen;
import org.usfirst.frc.team5962.robot.subsystems.Pneumatics;
import org.usfirst.frc.team5962.robot.subsystems.ShootingMechansim;
import org.usfirst.frc.team5962.robot.subsystems.ScalingMechanism;
import org.usfirst.frc.team5962.robot.subsystems.BallIntake;

import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
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
	
	public static OI oi;
	
	public static enum AutonomousPosition {
		LeftStartingPosition,
		MiddleStartingPosition,
		RightStartingPosition,
	}
	
	public static enum AutonomousTarget {
		
		CrossTheLine,
		PutTheGear,
		ShootTheBall,
		
	}
	
	
	
	
	
	private static final int IMG_WIDTH = 320;
	private static final int IMG_HEIGHT = 240;
	
	public static Camera camera;
//	public static Pneumatics pneumatics;
	public static CameraTwo camerTwo;

	public static BallIntake intake;
	public static ShootingMechansim ballshooting;
	public static ScalingMechanism scaling;
	
	// Gear Manipulator
//	public static GearMechanism gearmechanism;
//	public static LimitSwitchopen limitSwitchright;
//	public static LimitSwitchclose limitSwitchleft;
	
//	private VisionThread visionThread;
//	private double centerX = 0.0;
	
	public static Drive drive;
	
    public static RobotUltrasonicAnalog ultrasonicShoot;
    public static RobotGyro gyro= new RobotGyro();
    public static RobotEncoder encoder = new RobotEncoder();
	
	//private final Object imgLock = new Object();
    Command autonomousCommand;

    SendableChooser autoPositionChooser;
    SendableChooser autoFirstTargetChooser;
    
	


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
//		pneumatics = new Pneumatics();
		gyro.resetGyro();
		intake = new BallIntake();
		ballshooting = new ShootingMechansim();
		scaling = new ScalingMechanism();
		
		//gearmechanism = new GearMechanism();
		//limitSwitchright = new LimitSwitchopen();
		//limitSwitchleft = new LimitSwitchclose(); 
       
		initAutonomousPositionChooser();
		initAutonomousTargetChooser();

	        
	}
    private void initAutonomousPositionChooser() {
    	autoPositionChooser = new SendableChooser();
    	autoPositionChooser.addDefault("LeftStartingPosition", AutonomousPosition.LeftStartingPosition);
    	autoPositionChooser.addObject("MiddleStartingPosition", AutonomousPosition.MiddleStartingPosition);
    	autoPositionChooser.addObject("RightStartingPosition", AutonomousPosition.RightStartingPosition);
    	SmartDashboard.putData("Select Autonomous Start Position", autoPositionChooser);
    }
    
    private void initAutonomousTargetChooser(){
    	autoFirstTargetChooser = new SendableChooser();
    	autoFirstTargetChooser.addDefault("CrossTheLine", AutonomousTarget.CrossTheLine);
    	autoFirstTargetChooser.addObject("PutTheGear", AutonomousTarget.PutTheGear);
    	autoFirstTargetChooser.addObject("ShootTheBall", AutonomousTarget.ShootTheBall);
    	SmartDashboard.putData("Select Autonomous First Target", autoFirstTargetChooser);
    	
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
    	
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
    	/*
        Scheduler.getInstance().run();
    	double centerX;
    	synchronized (imgLock) {
    		centerX = this.centerX;
    	}
    	double turn = centerX - (IMG_WIDTH / 2);
    	RobotMap.myRobot.arcadeDrive(-0.6, turn * 0.005);
    	*/
    }

    public void teleopInit() {
		// This makes sure that the autonomous stops running when
        // teleop starts running. If you want the autonomous to 
        // continue until interrupted by another command, remove
        // this line or comment it out.
//        if (autonomousCommand != null) autonomousCommand.cancel();
    	
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
    	
//    	openGear();
//    	closeGear();
    	
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
