package org.usfirst.frc.team5962.robot.subsystems;

import org.usfirst.frc.team5962.robot.Robot;
import org.usfirst.frc.team5962.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Subsystem;

public class BoilerLEDVision extends Subsystem{

	private boolean got = false;
	public boolean sense = false;
	
	private double[] areas = null;
	private double[] centerX = null;
	
	private int length = 0;
	private int place = 0;
	private int biggestPlace = 0;
	private int imgWidth = 640;
	
	private double sysTimeInit = 0.0;
	private double biggestValue = 0.0;
	
	
	@Override
	protected void initDefaultCommand() {	
	}
	
	public void runBoilerVision(){
		if(got == false && sense == true){
			getTableValues();
		}
		else if(got == true && sense == true){
			comparePlaceToLength();
		}
	}
	
	@SuppressWarnings("deprecation")
	private void getTableValues(){
		areas = Robot.LEDBoiler.getNumberArray("area");
		centerX = Robot.LEDBoiler.getNumberArray("centerX");
		
		length = areas.length;
		place = 0;
		
		got = true;
	}
	
	private void comparePlaceToLength(){
		if(place < length){
			//increasePlace();
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
			setSysInitTime();
			move(-0.4, 1);
			printException(e);
		}
	}
	
	private void centerContours(){
		try{
			//avgCenterX = (centerX[biggestPlace] + centerX[biggestPlaceTwo]) / 2;
			
			if(centerX[biggestPlace] < ((.5*(imgWidth)) - 270)){
				setSysInitTime();
				move(-0.35, -1);
			}
			else if(centerX[biggestPlace] > ((.5*(imgWidth)) + 270)){
				setSysInitTime();
				move(-0.35, 1);
			}
			
			
			else if(centerX[biggestPlace] >= ((.5*(imgWidth)) - 270) && centerX[biggestPlace] < ((.5*(imgWidth)) - 90)){
				setSysInitTime();
				move(-0.20, -1);
			}
			else if(centerX[biggestPlace] <= ((.5*(imgWidth)) + 270) && centerX[biggestPlace] > ((.5*(imgWidth)) + 90)){
				setSysInitTime();
				move(-0.20, 1);
			}
			
			
			else if(centerX[biggestPlace] >= ((.5*(imgWidth)) - 90) && centerX[biggestPlace] < ((.5*(imgWidth)) - 50)){
				setSysInitTime();
				move(-0.125, -1);
			}
			else if(centerX[biggestPlace] <= ((.5*(imgWidth)) + 90) && centerX[biggestPlace] > ((.5*(imgWidth)) + 50)){
				setSysInitTime();
				move(-0.125, 1);
			}
			
			
			else if(centerX[biggestPlace] >= ((.5*(imgWidth)) - 50) && centerX[biggestPlace] <= ((.5*(imgWidth)) + 50)){
				checkRobotMode();
			}
			else{
				setSysInitTime();
				move(-0.4, 1);
			}
		}catch(Exception e){
			setSysInitTime();
			move(-0.4, 1);
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
	
	private void compareSysTime(){
		if((System.currentTimeMillis() - sysTimeInit) >= 200){
			
		}
	}
	
	private void checkRobotMode(){
		if(Robot.mode == true){
			compareSysTime();
			move(0, 0);
		}
		else if(Robot.mode == false){
			move(0, 0);
		}
	}
	
	private void setSysInitTime(){
		sysTimeInit = System.currentTimeMillis();
	}
	
	
	
	
	

}
