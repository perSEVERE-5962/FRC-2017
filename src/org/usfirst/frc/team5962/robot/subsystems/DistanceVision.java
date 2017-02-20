package org.usfirst.frc.team5962.robot.subsystems;

import org.usfirst.frc.team5962.robot.Robot;
import org.usfirst.frc.team5962.robot.RobotMap;
import org.usfirst.frc.team5962.robot.subsystems.Autonomous.State;

import edu.wpi.first.wpilibj.command.Subsystem;

public class DistanceVision extends Subsystem{

	private boolean setGyro = false;
	private boolean got = false;
	public boolean sense = true;
	
	private double[] areas = null;
	private double[] centerY = null;
	
	private int length = 0;
	private int place = 0;
	private int biggestPlace = 0;
	private int imgHeight = 480;
	
	private double initAngle = 0.0;
	private double sysTimeInit = 0.0;
	private double biggestValue = 0.0;
	
	public static boolean switchStateDistance = false;
	
	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub	
	}
	
	public void runDistanceVision(){
		if(got == false && sense == true){
			getTableValues();
		}
		else if(got == true && sense == true){
			comparePlaceToLength();
		}
	}
	
	private void getTableValues(){
		areas = Robot.LEDBoiler.getNumberArray("area");
		centerY = Robot.LEDBoiler.getNumberArray("centerY");
		
		length = areas.length;
		place = 0;
		
		//setInitAngle();
		got = true;
	}
	
	private void comparePlaceToLength(){
		if(place < length){
		//	increasePlace();
			updateBiggest();
			increasePlace();
		}
		else if(place >= length){
			centerContours();
		}
	}
	
	private void increasePlace(){
		place = place + 1;
	}
	
	private void updateBiggest(){
		try{
			if(areas[place] > biggestValue){
				//biggestValueTwo = biggestValue;
				//biggestPlaceTwo = biggestPlace;
			
				biggestValue = areas[place];
				biggestPlace = place;
			}
		}catch(Exception e){
			setInitSysTime();
			double angle = getGyroAngle();
			move(0.20, 0/*-angle * 0.03*/);
			printException(e);
		}
	}
	
	private void centerContours(){
		try{
			double angle = getGyroAngle();
			//avgCenterX = (centerX[biggestPlace] + centerX[biggestPlaceTwo]) / 2;
			
			if(centerY[biggestPlace] < ((.5 * imgHeight) - 120)){
				setInitSysTime();
    			move(0.20, 0/*-angle * 0.03*/);
    		}
    		else if(centerY[biggestPlace] > ((.5 * imgHeight) + 120)){
    			setInitSysTime();
    			move(-0.20, 0/*-angle * 0.03*/);
    		}
    		
    		
    		else if(centerY[biggestPlace] >= ((.5 * imgHeight) - 120) && centerY[biggestPlace] < ((.5 * imgHeight) - 55)){
    			setInitSysTime();
    			move(0.10, 0/*-angle * 0.03*/);
    		}
    		else if(centerY[biggestPlace] <= ((.5 * imgHeight) + 120) && centerY[biggestPlace] > ((.5 * imgHeight) + 55)){
    			setInitSysTime();
    			move(-0.10, 0/*-angle * 0.03*/);
    		}
    		
    		
    		else if(centerY[biggestPlace] >= ((.5 * imgHeight) - 55) && centerY[biggestPlace] <= ((.5 * imgHeight) + 55)){
    			System.out.println("HERE DISTANCE");
    			checkRobotMode();
    		}
    		else{
    			setInitSysTime();
    			move(0.20, 0/*-angle * 0.03*/);
    		}
		}catch(Exception e){
			setInitSysTime();
			double angle = getGyroAngle();
			move(0.20, 0/*-angle * 0.03*/);
			printException(e);
			resetValues();
		}
		resetValues();
	}
	
	private void printException(Exception e){
		System.out.println(e);
	}
	
	private void resetValues(){
		biggestPlace = 0;
		//biggestPlaceTwo = 0;
		biggestValue = 0;
		//biggestValueTwo = 0;
		got = false;
	}
	
	private void move(double spd, double turn){
		RobotMap.myRobot.drive(spd, turn);
	}
	
	private double getGyroAngle() {
		double angle = (Robot.gyro.getGyroAngle() - initAngle);
		return angle;
	}
	
	private void setInitSysTime(){
		sysTimeInit = System.currentTimeMillis();
	}
	
	private void compareSysTime(){
		if((System.currentTimeMillis() - sysTimeInit) >= 100){
			switchStateDistance = true;
		}
	}
	/*
	private void setInitAngle(){
		if(setGyro = false){
			initAngle = 0; //Robot.gyro.getGyroAngle();
			setGyro = true;
		}
	
	}*/
	
	private void checkRobotMode(){
		if(Robot.mode == true){
			compareSysTime();
			move(0, 0);
		}
		else if(Robot.mode == false){
			move(0, 0);
		}
	}
	
	
	

}
