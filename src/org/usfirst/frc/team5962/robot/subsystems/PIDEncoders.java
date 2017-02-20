/**
 * Example demonstrating the velocity closed-loop servo.
 * Tested with Logitech F350 USB Gamepad inserted into Driver Station]
 * 
 * Be sure to select the correct feedback sensor using SetFeedbackDevice() below.
 *
 * After deploying/debugging this to your RIO, first use the left Y-stick 
 * to throttle the Talon manually.  This will confirm your hardware setup.
 * Be sure to confirm that when the Talon is driving forward (green) the 
 * position sensor is moving in a positive direction.  If this is not the cause
 * flip the boolena input to the SetSensorDirection() call below.
 *
 * Once you've ensured your feedback device is in-phase with the motor,
 * use the button shortcuts to servo to target velocity.  
 *
 * Tweak the PID gains accordingly.
 */
package org.usfirst.frc.team5962.robot.subsystems;

import org.usfirst.frc.team5962.robot.OI;
import org.usfirst.frc.team5962.robot.Robot;
import org.usfirst.frc.team5962.robot.RobotMap;

import com.ctre.CANTalon;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Joystick.AxisType;
import edu.wpi.first.wpilibj.command.Subsystem;

import com.ctre.CANTalon.FeedbackDevice;
import com.ctre.CANTalon.TalonControlMode;

public class PIDEncoders extends Subsystem {
  
	//CANTalon talon = new CANTalon(0);	
	//oi = new OI();
		
	//Joystick joystickbutton = new Joystick(0);	
	StringBuilder string = new StringBuilder();
	int loops = 0;
	
	int targetSpeed = 3600;
	public void robotInit() {
		//RobotMap.talon.enableControl();
		RobotMap.talon.setInverted(true);
        /* first choose the sensor */
		RobotMap.talon.setFeedbackDevice(FeedbackDevice.QuadEncoder);
		RobotMap.talon.reverseSensor(false);
		RobotMap.talon.configEncoderCodesPerRev(20); 
        // if using FeedbackDevice.QuadEncoder
        //_talon.configPotentiometerTurns(XXX), // if using FeedbackDevice.AnalogEncoder or AnalogPot

        /* set the peak and nominal outputs, 12V means full */
		RobotMap.talon.configNominalOutputVoltage(+0.0f, -0.0f);
		RobotMap.talon.configPeakOutputVoltage(+12.0f, -12.0f);
        /* set closed loop gains in slot0 */
		RobotMap.talon.setProfile(0);
		RobotMap.talon.setF(1);
		RobotMap.talon.setP(0);
		RobotMap.talon.setI(0); 
		RobotMap.talon.setD(0);
		
    	//double targetSpeed = leftYstick * 1500.0; /* 1500 RPM in either direction */
        	
		
	}
    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
    	/* get gamepad axis */
    	//double leftYstick = joystickbutton.getAxis(AxisType.kY);
    	double motorOutput = RobotMap.talon.getOutputVoltage() / RobotMap.talon.getBusVoltage();
    	/* prepare line to print */
		string.append("\tout:");
		string.append(motorOutput);
        string.append("\tspd:");
        string.append(RobotMap.talon.getSpeed() );
        
        //if(Robot.oi.runPI){
      //if(Robot.pidencoder.isEnabled() == true)
        {
        	/* Speed mode */
        	RobotMap.talon.changeControlMode(TalonControlMode.Speed);
        	RobotMap.talon.set(targetSpeed); /* 1500 RPM in either direction */
        	//RobotMap.talon.set(targetSpeed); /* 1500 RPM in either direction */

        	/* append more signals to print when in speed mode. */
            string.append("\terr:");
            string.append(RobotMap.talon.getClosedLoopError());
            string.append("\ttrg:");
            string.append(targetSpeed);
            

        //} /*else {
        	// Percent voltage mode 
        	//RobotMap.talon.changeControlMode(TalonControlMode.PercentVbus);
        	//talon.set(leftYstick);
        } 

       if(++loops >= 10) {
        	loops = 0;
        	System.out.println(string.toString());
        }
        string.setLength(0);
    }
    
    public void stopTalon(){
    	
    	//RobotMap.talon.disableControl();
    	RobotMap.talon.set(0);
    	
    }
    
	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		
	}
   
    
    
}