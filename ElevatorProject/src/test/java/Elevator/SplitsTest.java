package Elevator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

/**
 * Testing functionality of Splits class
 * @author Florian, Rajesh, Rolando, Mark
 *
 */
public class SplitsTest
{
	/**
	 * Ensuring commands are being split correctly to receive the first and second number.
	 */
	@Test
	public void testSplitSource1() {
		Split split = new Split();
		int src = split.getSource("1:10");
		assertEquals(1, src);
	}
	@Test
	public void testSplitSource2() {
		Split split = new Split();
		int src = split.getSource("100:125");
		assertEquals(100, src);
	}
	@Test
	public void testSplitDestination1() {
		Split split = new Split();
		int dest = split.getDestination("1:10");
		assertEquals(10, dest);
	}

	public void testSplitDestination2() {
		Split split = new Split();
		int dest = split.getDestination("100:125");
		assertEquals(125, dest);
	}
	@Test
	public void testSplitBooleanSource1() {
		SplitCheck splitBoolean = new SplitCheck();
		int src = splitBoolean.getSource("1:10");
		assertEquals(1, src);
	}
	@Test
	public void testSplitBooleanSource2() {
		SplitCheck splitBoolean = new SplitCheck();
		int src = splitBoolean.getSource("100:10");
		assertEquals(0, src);
	}
	@Test
	public void testSplitBooleanDestination1() {
		SplitCheck splitBoolean = new SplitCheck();
		int dest = splitBoolean.getDestination("1:10");
		assertEquals(1, dest);
	}
	@Test
	public void testSplitBooleanDestination2() {
		SplitCheck splitBoolean = new SplitCheck();
		int dest = splitBoolean.getDestination("5:100");
		assertEquals(0, dest);
	}
	@Test
	public void testSplitInput1() {
		SplitInput split = new SplitInput();
		String [] str = {"1:10","2:10"};
		String [] string = split.InputSplit("1:10,2:10");
		assertTrue(Arrays.equals(str,string));
	}
	@Test
	public void testSplitInput2() {
		SplitInput split = new SplitInput();
		String [] str = {"1:10","2:10","3:10","4:10"};
		String [] string = split.InputSplit("1:10,2:10,3:10,4:10");
		assertTrue(Arrays.equals(str,string));
	}

}
