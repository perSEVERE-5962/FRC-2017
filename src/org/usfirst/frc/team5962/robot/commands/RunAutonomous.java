package org.usfirst.frc.team5962.robot.commands;

import org.usfirst.frc.team5962.robot.Robot;
import org.usfirst.frc.team5962.robot.RobotMap;
import org.usfirst.frc.team5962.robot.subsystems.Autonomous;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class RunAutonomous extends Command {

	
	private Robot.AutonomousPosition position;
	private Robot.AutonomousTarget target;
	private Robot.AutonomousChoosing autochoosing;
	
	private Autonomous autonomousSubsystem = new Autonomous();
	private boolean reachedTarget = false;
	private boolean isFinished = false;	
	public RunAutonomous (Robot.AutonomousChoosing autochoosing , Robot.AutonomousPosition position , Robot.AutonomousTarget target){
		this.position = position;
		this.target = target;
		this.autochoosing = autochoosing;
		
		SmartDashboard.putString("Autonomous Position Selected", position.toString());
		SmartDashboard.putString("Autonomous Target Selected", target.toString());
		SmartDashboard.putString("Autonomous Choosing", autochoosing.toString());
	}
	
	protected void initialize(){
		Robot.encoder.reset();
		 Robot.gyro.resetGyro();
		 RobotMap.myRobot.setMaxOutput(1);	
	}

	private void reachToTheLine(){
//switch(autochoosing){
	//case withOutGreenLight:
		if(autochoosing == Robot.AutonomousChoosing.withOutGreenLight ) 
		{
		switch(target){
		
		
		case CrossTheLine:
			reachedTarget = autonomousSubsystem.driveForward();
			break;
		case PutTheGear:
			//reachedTarget = autonomousSubsystem.driveForward();
			autonomousSubsystem.putTheGear();
			SmartDashboard.putString("putTheGear", "true");
			break;
			default:
				isFinished = true;
				break;
		}
		}
		else
		{
			System.out.print("LOL");
		}
	
		
		
	}
		
	int counter=0;
	int elsecount=0;
		protected void execute() {
			if (isFinished || target == Robot.AutonomousTarget.None) {
				SmartDashboard.putString("execute", "FINISHED");

				RobotMap.myRobot.drive(0, 0);
			}
			
			else if(!reachedTarget)
			{
				SmartDashboard.putString("elseif", "!reachedTarget");
				SmartDashboard.putString("reachedTarget", counter++ + "");
				reachToTheLine();
			}
			
			else {
				SmartDashboard.putString("else", "broken");
				SmartDashboard.putString("execute, else", elsecount++ + "");
				
			}
			
				
	}
	
	
	
	
	
	
	
	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}

	
	
	
}
