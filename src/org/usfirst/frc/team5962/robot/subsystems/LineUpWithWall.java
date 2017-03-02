package org.usfirst.frc.team5962.robot.subsystems;

import org.usfirst.frc.team5962.robot.sensors.RobotUltrasonicBase;
import edu.wpi.first.wpilibj.command.Subsystem;

public class LineUpWithWall extends Subsystem {

	private RobotUltrasonicBase ultrasonicLeft;
	private RobotUltrasonicBase ultrasonicRight;

	private LineUpWithWallHelper helper = new LineUpWithWallHelper();

	public LineUpWithWall(RobotUltrasonicBase ultrasonicLeft, RobotUltrasonicBase ultrasonicRight) {
		this.ultrasonicLeft = ultrasonicLeft;
		this.ultrasonicRight = ultrasonicRight;
	}

	public void lineUp() {
		helper.lineUp(ultrasonicLeft, ultrasonicRight);
	}

	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
	}

}
