package Elevator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import org.junit.jupiter.api.Test;

/**
 * Testing functionality of ConsoleInput class.
 * @author Florian, Rajesh, Rolando, Mark
 *
 */
public class ConsoleInputTest
{
	/**
	 * Testing of the value in input stream is equal to desired string.
	 */
	@Test
	public void testConsole1() {
		Messages messages = new Messages();
		ConsoleInput consoleInput = new ConsoleInput(messages, 0);
		
		String inputString = "1:10";
		InputStream in = new ByteArrayInputStream(inputString.getBytes());
		System.setIn(in);
		assertEquals("1:10",consoleInput.getInput());
	}
	
	/**
	 * Check if message is sent to the input queue for the scheduler is accurate
	 */
	@Test
	public void testConsole2() {
		Messages messages = new Messages();
		ConsoleInput consoleInput = new ConsoleInput(messages, 0);
		
		String inputString = "1:10";
		consoleInput.multipleCommands(inputString);
		String message1;
		message1 = messages.getMessage(QueueType.INPUT, 10, 'Z', true);
		assertEquals("1:10", message1);
	}
	
	/**
	 *  Check if message is sent to the input queue for the scheduler is accurate with two different commands.
	 */
	@Test
	public void testConsole3() {
		Messages messages = new Messages();
		ConsoleInput consoleInput = new ConsoleInput(messages, 0);
		
		String inputString = "1:10,2:10";
		consoleInput.multipleCommands(inputString);
		String message1;
		String message2;
		message1 = messages.getMessage(QueueType.INPUT, 10, 'Z', true);
		assertEquals("1:10", message1);
		message2 = messages.getMessage(QueueType.INPUT, 10, 'Z', true);
		assertEquals("2:10", message2);
	}
	
	/**
	 *  check if message sent to the input queue for the scheduler is accurate with four different commands.
	 */
	@Test
	public void testConsole4() {
		Messages messages = new Messages();
		ConsoleInput consoleInput = new ConsoleInput(messages, 0);
		
		String inputString = "1:10,2:10,3:10,4:10";
		consoleInput.multipleCommands(inputString);
		String message1;
		String message2;
		String message3;
		String message4;
		message1 = messages.getMessage(QueueType.INPUT, 10, 'Z', true);
		assertEquals("1:10", message1);
		message2 = messages.getMessage(QueueType.INPUT, 10, 'Z', true);
		assertEquals("2:10", message2);
		message3 = messages.getMessage(QueueType.INPUT, 10, 'Z', true);
		assertEquals("3:10", message3);
		message4 = messages.getMessage(QueueType.INPUT, 10, 'Z', true);
		assertEquals("4:10", message4);
	}

}
