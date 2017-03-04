package org.usfirst.frc.team5962.robot.commands;

import org.usfirst.frc.team5962.robot.Robot;
import org.usfirst.frc.team5962.robot.RobotMap;
import org.usfirst.frc.team5962.robot.subsystems.Autonomous;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class RunAutonomous extends Command {

	private Robot.AutonomousTarget target;
	public static Robot.AutonomousVision autoVision;

	private Autonomous autonomousSubsystem = new Autonomous();
	private boolean reachedTarget = false;
	private boolean isFinished = false;	

	public RunAutonomous (Robot.AutonomousVision autoVision , Robot.AutonomousPosition position , Robot.AutonomousTarget target){
		SmartDashboard.putString("Autonomous Position Selected", position.toString());
		SmartDashboard.putString("Autonomous Target Selected", target.toString());
		SmartDashboard.putString("Autonomous Vision", autoVision.toString());

		this.target = target;
		this.autoVision = autoVision;
		
		if(this.autoVision == Robot.AutonomousVision.NotUsingVision){
			autonomousSubsystem.vision = false;
			autonomousSubsystem.setPosition(position);
		}
		else if(this.autoVision == Robot.AutonomousVision.UsingVision){
			autonomousSubsystem.vision = true;
			autonomousSubsystem.setPosition(position);
		}
	}


	protected void initialize(){
		Robot.encoder.reset();
		Robot.gyro.resetGyro();
		RobotMap.myRobot.setMaxOutput(1);	
	}

	private void reachTarget(){
		switch(target){

		case CrossTheLine:
			reachedTarget = autonomousSubsystem.driveForward();
			break;
		case PutTheGear:
			autonomousSubsystem.putTheGear();
			SmartDashboard.putString("putTheGear", "true");
			break;
		default:
			isFinished = true;
			break;
		}
	}


	int counter=0;
	int elsecount=0;
	protected void execute() {
		if (isFinished || target == Robot.AutonomousTarget.None) {
			//SmartDashboard.putString("execute", "FINISHED");
			RobotMap.myRobot.drive(0, 0);
		}

		else if(!reachedTarget)
		{
			//SmartDashboard.putString("elseif", "!reachedTarget");
			//SmartDashboard.putString("reachedTarget", counter++ + "");
			reachTarget();
		}

		else {
			RobotMap.myRobot.drive(0, 0);
			//SmartDashboard.putString("else", "broken");
			//SmartDashboard.putString("execute, else", elsecount++ + "");
		}
	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}




}
