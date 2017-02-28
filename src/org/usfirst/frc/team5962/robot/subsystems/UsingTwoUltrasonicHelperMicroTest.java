package org.usfirst.frc.team5962.robot.subsystems;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.usfirst.frc.team5962.robot.RobotMap;
import org.usfirst.frc.team5962.robot.sensors.RobotUltrasonicBase;

import junit.framework.TestCase;

public class UsingTwoUltrasonicHelperMicroTest  extends TestCase{
	
	/*
	 * NOTE: Install EclEmma for code coverage
	 * URL = http://update.eclemma.org/
	 */
	
	private MockRobotUltrasonicAnalog ultrasonicLeft = new MockRobotUltrasonicAnalog();
	private MockRobotUltrasonicAnalog ultrasonicRight = new MockRobotUltrasonicAnalog();
	
	private UsingTwoUltrasonicHelper usingTwoUltrasonicHelper = new MockUsingTwoUltrasonicHelper();
	

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testBothRangeGreaterThan7() {
		// test when both at same range
		ultrasonicLeft.rangeInInches = 10;
		ultrasonicRight.rangeInInches = 10;
		usingTwoUltrasonicHelper.lineUp(ultrasonicLeft, ultrasonicRight);
		assertEquals(-0.6, ((MockUsingTwoUltrasonicHelper)usingTwoUltrasonicHelper).outputMagnitude);
		assertEquals(0.0, ((MockUsingTwoUltrasonicHelper)usingTwoUltrasonicHelper).curve);
	}
	
	@Test
	public void testLeftRangeEqualTo7AndRightInRange() {
		ultrasonicLeft.rangeInInches = 7;
		ultrasonicRight.rangeInInches = 6;
		usingTwoUltrasonicHelper.lineUp(ultrasonicLeft, ultrasonicRight);
		assertEquals(-0.6, ((MockUsingTwoUltrasonicHelper)usingTwoUltrasonicHelper).outputMagnitude);
		assertEquals(-1.0, ((MockUsingTwoUltrasonicHelper)usingTwoUltrasonicHelper).curve);
	}
	
	@Test
	public void testRightRangeEqualTo7AndLeftInRange() {
		ultrasonicLeft.rangeInInches = 6;
		ultrasonicRight.rangeInInches = 7;
		usingTwoUltrasonicHelper.lineUp(ultrasonicLeft, ultrasonicRight);
		assertEquals(-0.6, ((MockUsingTwoUltrasonicHelper)usingTwoUltrasonicHelper).outputMagnitude);
		assertEquals(1.0, ((MockUsingTwoUltrasonicHelper)usingTwoUltrasonicHelper).curve);
	}
	
	class MockRobotUltrasonicAnalog extends RobotUltrasonicBase {

		protected double rangeInInches = 0;
		
		public MockRobotUltrasonicAnalog() {
			super();
			// TODO Auto-generated constructor stub
		}

	    public double getRange() {
	    	return rangeInInches;
	    }

		@Override
		public boolean isEnabled() {
			// TODO Auto-generated method stub
			return false;
		}

	}
	
	class MockUsingTwoUltrasonicHelper extends UsingTwoUltrasonicHelper {
		
		protected double outputMagnitude = 0;
		protected double curve = 0;
		
		protected void driveRobot(double outputMagnitude, double curve) {
			this.outputMagnitude = outputMagnitude;
			this.curve = curve;
		}
	}

}
