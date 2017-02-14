package org.usfirst.frc.team5962.robot.subsystems;

import org.usfirst.frc.team5962.robot.Robot;
import org.usfirst.frc.team5962.robot.RobotMap;
import org.usfirst.frc.team5962.robot.subsystems.Autonomous.State;

import edu.wpi.first.wpilibj.command.Subsystem;

public class GearLEDVision extends Subsystem{

	private boolean got = false;
	public boolean sense = true;
	
	private double[] areas = null;
	private double[] centerX = null;
	
	private int length = 0;
	private int place = 0;
	private int biggestPlaceOne = 0;
	private int biggestPlaceTwo = 0;
	private int imgWidth = 640;
	
	private double biggestValueOne = 0.0;
	private double biggestValueTwo = 0.0;
	private double avgCenterX = 0.0;
	
	public static boolean switchStateGear = false;
	
	
	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		
	}
	
	public void runGearVision(){
		if(got == false && sense == true){
			getTableValues();
		}
		else if(got == true && sense == true){
			comparePlaceToLength();
		}
	}
	
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
		if(place >= length){
			centerContours();
		}
	}
	
	private void increasePlace(){
		place = place + 1;
	}
	
	private void updateBiggest(){
		try{
			if(areas[place] > biggestValueOne){
				biggestValueTwo = biggestValueOne;
				biggestPlaceTwo = biggestPlaceOne;
			
				biggestValueOne = areas[place];
				biggestPlaceOne = place;
			}
		}catch(Exception e){
			move(-0.32, -1);
			printException(e);
			resetValues();
		}
	}
	
	private void centerContours(){
		try{
			avgCenterX = (centerX[biggestPlaceOne] + centerX[biggestPlaceTwo]) / 2;
			
			if(avgCenterX < ((.5*(imgWidth)) - 270)){
				move(-0.30, -1);
			}
			else if(avgCenterX > ((.5*(imgWidth)) + 270)){
				move(-0.30, 1);
			}
			
			
			else if(avgCenterX >= ((.5*(imgWidth)) - 270) && avgCenterX < ((.5*(imgWidth)) - 90)){
				move(-0.20, -1);
			}
			else if(avgCenterX <= ((.5*(imgWidth)) + 270) && avgCenterX > ((.5*(imgWidth)) + 90)){
				move(-0.20, 1);
			}
			
			
			else if(avgCenterX >= ((.5*(imgWidth)) - 90) && avgCenterX < ((.5*(imgWidth)) - 50)){
				move(-0.125, -1);
			}
			else if(avgCenterX <= ((.5*(imgWidth)) + 90) && avgCenterX > ((.5*(imgWidth)) + 50)){
				move(-0.125, 1);
			}
			
			
			else if(avgCenterX >= ((.5*(imgWidth)) - 50) && avgCenterX <= ((.5*(imgWidth)) + 50)){
				switchStateGear = true;
				System.out.println(switchStateGear + "GEAR GEAR");
				System.out.println("HERE GEAR");
				move(0, 0);
			}
			else{
				move(-0.32, -1);
			}
		}catch(Exception e){
			move(-0.32, -1);
			printException(e);
			resetValues();
		}
		resetValues();
	}
	
	private void printException(Exception e){
		System.out.println(e);
	}
	
	private void resetValues(){
		biggestPlaceOne = 0;
		biggestPlaceTwo = 0;
		biggestValueOne = 0;
		biggestValueTwo = 0;
		got = false;
	}
	
	private void move(double spd, double turn){
		RobotMap.myRobot.drive(spd, turn);
	}
	
	
	
	
	

}
