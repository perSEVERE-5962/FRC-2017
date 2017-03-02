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
	int targetSpeed = 1500; 
	CANTalon scalingtalon = RobotMap.scalingtalon;
	int currentDistance = 0;
	int finaldistance = 20;
	public void scaling()
	{
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
	}

	public void stop (){
		/* get gamepad axis */
    	//double leftYstick = joystickbutton.getAxis(AxisType.kY);
    	double motorOutput = RobotMap.talon.getOutputVoltage() / RobotMap.talon.getBusVoltage();
    	/* prepare line to print */
		string.append("\tout:");
		string.append(motorOutput);
        string.append("\tspd:");
        string.append(RobotMap.talon.getSpeed() );
        
        {
      
        
        	/* Speed mode */
        	RobotMap.talon.changeControlMode(TalonControlMode.Position);
        	
       currentDistance =  	RobotMap.talon.getEncPosition(); /* 1500 RPM in either direction */

        	/* append more signals to print when in speed mode. */
            string.append("\terr:");
            string.append(RobotMap.talon.getClosedLoopError());
            string.append("\ttrg:");
            string.append(targetSpeed);
            
          
            
	}
        if (currentDistance  == finaldistance)
        {
        	RobotMap.talon.set(0); /* 1500 RPM in either direction */
        	
        }
        else
        {
        	currentDistance = finaldistance;
        	RobotMap.talon.set(targetSpeed); /* 1500 RPM in either direction */
        }
	}
        /*
	public void scaling(){
		scalingtalon.set(-1);
	}

	public void stop(){
		scalingtalon.set(0);
	}
	*/
	
	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub

	}

}
