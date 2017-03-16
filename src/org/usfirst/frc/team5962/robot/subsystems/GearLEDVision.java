package org.usfirst.frc.team5962.robot.subsystems;

import org.usfirst.frc.team5962.robot.Robot;
import org.usfirst.frc.team5962.robot.RobotMap;
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
	
	public static int direction = -1;
	
	private double sysTimeInit = 0.0;
	private double biggestValueOne = 0.0;
	//private double biggestValueTwo = 0.0;
	private double avgCenterX = 0.0;
	
	public static boolean switchStateGear = false;
	
	
	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		
	}
	
	public void runGearVision(double setSpd, double setTurn){
		if(got == false && sense == true){
			getTableValues();
		}
		else if(got == true && sense == true){
			comparePlaceToLength(setSpd, setTurn);
		}
	}
	
	@SuppressWarnings("deprecation")
	private void getTableValues(){
		try{
			areas = Robot.LEDGear.getNumberArray("area");
			centerX = Robot.LEDGear.getNumberArray("centerX");
		}catch(Exception e){
			System.out.println("Error: " + e);
		}
		length = areas.length;
		place = 0;
		
		got = true;
	}
	
	private void comparePlaceToLength(double setSpd, double setTurn){
		if(place < length){
			//increasePlace();
			updateBiggest();
			increasePlace();
		}
		if(place >= length){
			centerContours(setSpd, setTurn);
		}
	}
	
	private void increasePlace(){
		place = place + 1;
	}
	
	private void updateBiggest(){
		try{
			if(areas[place] > biggestValueOne){
				//biggestValueTwo = biggestValueOne;
				biggestPlaceTwo = biggestPlaceOne;
			
				biggestValueOne = areas[place];
				biggestPlaceOne = place;
			}
		}catch(Exception e){
			setInitSysTime();
			move(-0.2, direction); //-0.4
			printException(e);
			resetValues();
		}
	}
	
	private void centerContours(double setSpd, double setTurn){
		try{
			avgCenterX = (centerX[biggestPlaceOne] + centerX[biggestPlaceTwo]) / 2;
			
			if(avgCenterX < ((.5*(imgWidth)) - (270))){
				setInitSysTime();
				move(-0.30, -1); //-0.40
			}
			else if(avgCenterX > ((.5*(imgWidth)) + (270))){
				setInitSysTime();
				move(-0.30, 1); //-0.40
			}
			
			
			else if(avgCenterX >= ((.5*(imgWidth)) - (270)) && avgCenterX < ((.5*(imgWidth)) - (90))){
				setInitSysTime();
				move(-0.20, -1); //-0.30
			}
			else if(avgCenterX <= ((.5*(imgWidth)) + (270)) && avgCenterX > ((.5*(imgWidth)) + (90))){
				setInitSysTime();
				move(-0.20, 1); //-0.30
			}
			
			
			else if(avgCenterX >= ((.5*(imgWidth)) - (90)) && avgCenterX < ((.5*(imgWidth)) - (50))){
				setInitSysTime();
				move(-0.125, -1);
			}
			else if(avgCenterX <= ((.5*(imgWidth)) + (90)) && avgCenterX > ((.5*(imgWidth)) + (50))){
				setInitSysTime();
				move(-0.125, 1);
			}
			
			
			else if(avgCenterX >= ((.5*(imgWidth)) - (50)) && avgCenterX <= ((.5*(imgWidth)) + (50))){
				System.out.println(switchStateGear + "GEAR GEAR");
				System.out.println("HERE GEAR");
				checkRobotMode(setSpd, setTurn);
			}
			else{
				setInitSysTime();
				move(-0.2, direction); //-0.4
			}
		}catch(Exception e){
			setInitSysTime();
			move(-0.2, direction); //-0.4
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
		//biggestValueTwo = 0;
		got = false;
	}
	
	private void move(double spd, double turn){
		RobotMap.myRobot.drive(spd, turn);
	}
	
	private void setInitSysTime(){
		sysTimeInit = System.currentTimeMillis();
	}
	
	private void compareSysTime(){
		if((System.currentTimeMillis() - sysTimeInit) >= 200){
			//Robot.distanceVision.runDistanceVision();
			switchStateGear = true;
		}
	}
	
	private void checkRobotMode(double setSpd, double setTurn){
		if(Robot.mode == true){
			compareSysTime();
			move(setSpd, setTurn);
		}
		else if(Robot.mode == false){
			move(setSpd, setTurn);
		}
	}
	
	public void setDirection(Robot.AutonomousPosition position){
		if(position == Robot.AutonomousPosition.LeftStartingPosition || position == Robot.AutonomousPosition.MiddleStartingPosition){
			direction = -1;
		}
		else if(position == Robot.AutonomousPosition.RightStartingPosition){
			direction = 1;
		}
	}
	
	public boolean getSwitchStateGear(){
		return switchStateGear;
	}
	
	
	
	
	

}
