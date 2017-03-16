//package org.usfirst.frc.team5962.robot.subsystems;
//
//import org.junit.After;
//import org.junit.Before;
//import org.junit.Test;
//import org.usfirst.frc.team5962.robot.sensors.RobotUltrasonicBase;
//
//import junit.framework.TestCase;
//
//public class LineUpWithWallHelperMicroTest extends TestCase {
//
//	/*
//	 * NOTE: Install EclEmma for code coverage URL = http://update.eclemma.org/
//	 */
//
//	private MockRobotUltrasonicAnalog ultrasonicLeft = new MockRobotUltrasonicAnalog();
//	private MockRobotUltrasonicAnalog ultrasonicRight = new MockRobotUltrasonicAnalog();
//
//	private LineUpWithWallHelper lineUpWithWallHelper = new MockLineUpWithWallHelper();
//
//	@Before
//	public void setUp() throws Exception {
//	}
//
//	@After
//	public void tearDown() throws Exception {
//	}
//
//	@Test
//	// robot is outside of range, so drive forward
//	public void testForDrive_GreaterThan8() {
//		// left is greater than right
//		ultrasonicLeft.rangeInInches = 9.0;
//		ultrasonicRight.rangeInInches = 8.5;
//		lineUpWithWallHelper.lineUp(ultrasonicLeft, ultrasonicRight);
//		assertEquals(-0.25, ((MockLineUpWithWallHelper) lineUpWithWallHelper).outputMagnitude);
//		assertEquals(0.0, ((MockLineUpWithWallHelper) lineUpWithWallHelper).curve);
//
//		ultrasonicLeft.rangeInInches = 9.0;
//		ultrasonicRight.rangeInInches = 7.5;
//		lineUpWithWallHelper.lineUp(ultrasonicLeft, ultrasonicRight);
//		assertEquals(-0.25, ((MockLineUpWithWallHelper) lineUpWithWallHelper).outputMagnitude);
//		assertEquals(0.0, ((MockLineUpWithWallHelper) lineUpWithWallHelper).curve);
//
//		// right is greater than left
//		ultrasonicLeft.rangeInInches = 8.5;
//		ultrasonicRight.rangeInInches = 9;
//		lineUpWithWallHelper.lineUp(ultrasonicLeft, ultrasonicRight);
//		assertEquals(-0.25, ((MockLineUpWithWallHelper) lineUpWithWallHelper).outputMagnitude);
//		assertEquals(0.0, ((MockLineUpWithWallHelper) lineUpWithWallHelper).curve);
//
//		ultrasonicLeft.rangeInInches = 7.5;
//		ultrasonicRight.rangeInInches = 9.5;
//		lineUpWithWallHelper.lineUp(ultrasonicLeft, ultrasonicRight);
//		assertEquals(-0.25, ((MockLineUpWithWallHelper) lineUpWithWallHelper).outputMagnitude);
//		assertEquals(0.0, ((MockLineUpWithWallHelper) lineUpWithWallHelper).curve);
//
//	}
//
//	@Test
//	// robot is in range and straight, so drive forward
//	public void testForDrive_InRange() {
//		// left is greater than right
//		ultrasonicLeft.rangeInInches = 7.0;
//		ultrasonicRight.rangeInInches = 6.5;
//		lineUpWithWallHelper.lineUp(ultrasonicLeft, ultrasonicRight);
//		assertEquals(-0.25, ((MockLineUpWithWallHelper) lineUpWithWallHelper).outputMagnitude);
//		assertEquals(0.0, ((MockLineUpWithWallHelper) lineUpWithWallHelper).curve);
//
//		ultrasonicLeft.rangeInInches = 5.3;
//		ultrasonicRight.rangeInInches = 4.5;
//		lineUpWithWallHelper.lineUp(ultrasonicLeft, ultrasonicRight);
//		assertEquals(-0.25, ((MockLineUpWithWallHelper) lineUpWithWallHelper).outputMagnitude);
//		assertEquals(0.0, ((MockLineUpWithWallHelper) lineUpWithWallHelper).curve);
//		// right is greater than left
//		ultrasonicLeft.rangeInInches = 6.5;
//		ultrasonicRight.rangeInInches = 7;
//		lineUpWithWallHelper.lineUp(ultrasonicLeft, ultrasonicRight);
//		assertEquals(-0.25, ((MockLineUpWithWallHelper) lineUpWithWallHelper).outputMagnitude);
//		assertEquals(0.0, ((MockLineUpWithWallHelper) lineUpWithWallHelper).curve);
//
//		ultrasonicLeft.rangeInInches = 4.5;
//		ultrasonicRight.rangeInInches = 5.3;
//		lineUpWithWallHelper.lineUp(ultrasonicLeft, ultrasonicRight);
//		assertEquals(-0.25, ((MockLineUpWithWallHelper) lineUpWithWallHelper).outputMagnitude);
//		assertEquals(0.0, ((MockLineUpWithWallHelper) lineUpWithWallHelper).curve);
//	}
//
//	@Test
//	// robot in range (closer than 8 inches), but right is greater than left by
//	// more than one inch
//	public void testRightIsGreaterThanLeftByMoreThan1() {
//		ultrasonicLeft.rangeInInches = 5.5;
//		ultrasonicRight.rangeInInches = 7.5;
//		lineUpWithWallHelper.lineUp(ultrasonicLeft, ultrasonicRight);
//		assertEquals(-0.25, ((MockLineUpWithWallHelper) lineUpWithWallHelper).outputMagnitude);
//		assertEquals(-1.0, ((MockLineUpWithWallHelper) lineUpWithWallHelper).curve);
//	}
//
//	@Test
//	// robot in range (closer than 8 inches), but left is greater than right by
//	// more than one inch
//	public void testLeftIsGreaterThanRightByMoreThan1() {
//		ultrasonicLeft.rangeInInches = 7.5;
//		ultrasonicRight.rangeInInches = 5.5;
//		lineUpWithWallHelper.lineUp(ultrasonicLeft, ultrasonicRight);
//		assertEquals(-0.25, ((MockLineUpWithWallHelper) lineUpWithWallHelper).outputMagnitude);
//		assertEquals(1.0, ((MockLineUpWithWallHelper) lineUpWithWallHelper).curve);
//	}
//
//	@Test
//	// stop the robot
//	public void testBothWithin5() {
//		// test right ahead of left
//		ultrasonicLeft.rangeInInches = 4.9;
//		ultrasonicRight.rangeInInches = 4.5;
//		lineUpWithWallHelper.lineUp(ultrasonicLeft, ultrasonicRight);
//		assertEquals(0.0, ((MockLineUpWithWallHelper) lineUpWithWallHelper).outputMagnitude);
//		assertEquals(0.0, ((MockLineUpWithWallHelper) lineUpWithWallHelper).curve);
//		// test left ahead of right
//		ultrasonicLeft.rangeInInches = 4.5;
//		ultrasonicRight.rangeInInches = 4.9;
//		lineUpWithWallHelper.lineUp(ultrasonicLeft, ultrasonicRight);
//		assertEquals(0.0, ((MockLineUpWithWallHelper) lineUpWithWallHelper).outputMagnitude);
//		assertEquals(0.0, ((MockLineUpWithWallHelper) lineUpWithWallHelper).curve);
//	}
//
//	@Test
//	// backup the robot
//	public void testRobotTooClose() {
//		// test right ahead of left
//		ultrasonicLeft.rangeInInches = 3.1;
//		ultrasonicRight.rangeInInches = 2.5;
//		lineUpWithWallHelper.lineUp(ultrasonicLeft, ultrasonicRight);
//		assertEquals(0.25, ((MockLineUpWithWallHelper) lineUpWithWallHelper).outputMagnitude);
//		assertEquals(0.0, ((MockLineUpWithWallHelper) lineUpWithWallHelper).curve);
//		// test left ahead of right
//		ultrasonicLeft.rangeInInches = 2.5;
//		ultrasonicRight.rangeInInches = 3.1;
//		lineUpWithWallHelper.lineUp(ultrasonicLeft, ultrasonicRight);
//		assertEquals(0.25, ((MockLineUpWithWallHelper) lineUpWithWallHelper).outputMagnitude);
//		assertEquals(0.0, ((MockLineUpWithWallHelper) lineUpWithWallHelper).curve);
//	}
//
//	class MockRobotUltrasonicAnalog extends RobotUltrasonicBase {
//
//		protected double rangeInInches = 0;
//
//		public MockRobotUltrasonicAnalog() {
//			super();
//			// TODO Auto-generated constructor stub
//		}
//
//		public double getRange() {
//			return rangeInInches;
//		}
//
//		@Override
//		public boolean isEnabled() {
//			// TODO Auto-generated method stub
//			return false;
//		}
//
//	}
//
//	class MockLineUpWithWallHelper extends LineUpWithWallHelper {
//
//		protected double outputMagnitude = 0;
//		protected double curve = 0;
//
//		protected void driveRobot(double outputMagnitude, double curve) {
//			this.outputMagnitude = outputMagnitude;
//			this.curve = curve;
//		}
//	}
//
//}
