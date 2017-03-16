package org.usfirst.frc.team5962.robot.subsystems;

import org.usfirst.frc.team5962.robot.RobotMap;

import com.ctre.CANTalon;
import com.ctre.CANTalon.FeedbackDevice;
import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;

public class ScalingMechanism extends Subsystem {
	//Victor scalingvictor = RobotMap.scalingVictor;
	StringBuilder string = new StringBuilder();
	int targetSpeed = 3000; 
	//CANTalon scalingtalon = RobotMap.scalingtalon;
	int currentDistance = 0;
	int finaldistance = 20;
	public void scaling()
	{
		RobotMap.scalingtalon.setInverted(true);
        /* first choose the sensor */
		RobotMap.scalingtalon.setFeedbackDevice(FeedbackDevice.QuadEncoder);
		RobotMap.scalingtalon.reverseSensor(false);
		RobotMap.scalingtalon.configEncoderCodesPerRev(20); 
        // if using FeedbackDevice.QuadEncoder
        //_talon.configPotentiometerTurns(XXX), // if using FeedbackDevice.AnalogEncoder or AnalogPot

        /* set the peak and nominal outputs, 12V means full */
		RobotMap.scalingtalon.configNominalOutputVoltage(+0.0f, -0.0f);
		RobotMap.scalingtalon.configPeakOutputVoltage(+12.0f, -12.0f);
        /* set closed loop gains in slot0 */
		RobotMap.scalingtalon.setProfile(0);
		RobotMap.scalingtalon.setF(1);
		RobotMap.scalingtalon.setP(0);
		RobotMap.scalingtalon.setI(0); 
		RobotMap.scalingtalon.setD(0);
	}

	public void stop (){
		/* get gamepad axis */
    	//double leftYstick = joystickbutton.getAxis(AxisType.kY);
    	double motorOutput = RobotMap.scalingtalon.getOutputVoltage() / RobotMap.scalingtalon.getBusVoltage();
    	/* prepare line to print */
		string.append("\tout:");
		string.append(motorOutput);
        string.append("\tspd:");
        string.append(RobotMap.scalingtalon.getSpeed() );
        
        {
      
        
        	/* Speed mode */
        	RobotMap.scalingtalon.changeControlMode(TalonControlMode.Position);
        	
       currentDistance =  	RobotMap.scalingtalon.getEncPosition(); /* 1500 RPM in either direction */

        	/* append more signals to print when in speed mode. */
            string.append("\terr:");
            string.append(RobotMap.scalingtalon.getClosedLoopError());
            string.append("\ttrg:");
            string.append(targetSpeed);
            
          
            
	}
        if (currentDistance  == finaldistance)
        {
        	RobotMap.scalingtalon.set(0); /* 1500 RPM in either direction */
        	
        }
        else
        {
        	currentDistance = finaldistance;
        	RobotMap.scalingtalon.set(targetSpeed); /* 1500 RPM in either direction */
        }
	}
//	public void scaling(){
//		RobotMap.scalingtalon.set(-1);
//	}
//
//	public void stop(){
//		RobotMap.scalingtalon.set(0);
//	}

	
	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub

	}

}
