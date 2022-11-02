package Elevator;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * Testing functionality of scheduler class
 * @author Florian, Rajesh, Rolando, Mark
 *
 */
public class SchedulerTest
{
	/**
	 * Testing if the correct messages have been sent to ElevatorThreads.
	 */
	@Test
	public void testScheduler1()
	{
		Messages messages = new Messages();
		messages.postMessage(QueueType.INPUT, 10, 'Z', "1:12");

		Thread schedulerThread = new Thread(new Scheduler(messages));
		schedulerThread.start();

		String message1;
		try
		{
			Thread.sleep(1000);
		} catch (InterruptedException e)
		{
			e.printStackTrace();
		}
		message1 = messages.getMessage(QueueType.INPUT, 1, 'C', true);
		assertEquals("1:12", message1);
	}
	
	@Test
	public void testScheduler2()
	{
		Messages messages = new Messages();
		messages.postMessage(QueueType.INPUT, 10, 'Z', "1:12");
		messages.postMessage(QueueType.INPUT, 10, 'Z', "2:14");

		Thread schedulerThread = new Thread(new Scheduler(messages));
		schedulerThread.start();

		String message1;
		String message2;
		try
		{
			Thread.sleep(1000);
		} catch (InterruptedException e)
		{
			e.printStackTrace();
		}
		message1 = messages.getMessage(QueueType.INPUT, 1, 'C', true);
		assertEquals("1:12", message1);
		
		try
		{
			Thread.sleep(5000);
		} catch (InterruptedException e)
		{
			e.printStackTrace();
		}
		
		message2 = messages.getMessage(QueueType.INPUT,2 , 'C', true);
		assertEquals("2:14", message2);
	}
	
	@Test
	public void testScheduler3()
	{
		Messages messages = new Messages();
		messages.postMessage(QueueType.INPUT, 10, 'Z', "1:12");
		messages.postMessage(QueueType.INPUT, 10, 'Z', "2:12");
		messages.postMessage(QueueType.INPUT, 10, 'Z', "3:12");
		messages.postMessage(QueueType.INPUT, 10, 'Z', "4:12");

		Thread schedulerThread = new Thread(new Scheduler(messages));
		schedulerThread.start();

		String message1;
		String message2;
		String message3;
		String message4; 
		
		try
		{
			Thread.sleep(1000);
		} catch (InterruptedException e)
		{
			e.printStackTrace();
		}
		message1 = messages.getMessage(QueueType.INPUT, 1, 'C', true);
		assertEquals("1:12", message1);
		
		try
		{
			Thread.sleep(5000);
		} catch (InterruptedException e)
		{
			e.printStackTrace();
		}
		
		message2 = messages.getMessage(QueueType.INPUT,2 , 'C', true);
		assertEquals("2:12", message2);
		
		try
		{
			Thread.sleep(5000);
		} catch (InterruptedException e)
		{
			e.printStackTrace();
		}
		
		message3 = messages.getMessage(QueueType.INPUT,3 , 'C', true);
		assertEquals("3:12", message3);
		
		try
		{
			Thread.sleep(5000);
		} catch (InterruptedException e)
		{
			e.printStackTrace();
		}
		
		message4 = messages.getMessage(QueueType.INPUT,4 , 'C', true);
		assertEquals("4:12", message4);
		
	}
	
	@Test
	public void testScheduler4()
	{
		Messages messages = new Messages();
		messages.postMessage(QueueType.INPUT, 100, 'R', "r");

		Thread schedulerThread = new Thread(new Scheduler(messages));
		schedulerThread.start();

		String message1;
		String message2;
		String message3;
		String message4;
		try
		{
			Thread.sleep(5000);
		} catch (InterruptedException e)
		{
			e.printStackTrace();
		}
		message1 = messages.getMessage(QueueType.INPUT, 1, 'C', true);
		assertEquals("0:0", message1);
		message2 = messages.getMessage(QueueType.INPUT, 2, 'C', true);
		assertEquals("0:0", message2);
		message3 = messages.getMessage(QueueType.INPUT, 3, 'C', true);
		assertEquals("0:0", message3);
		message4 = messages.getMessage(QueueType.INPUT, 4, 'C', true);
		assertEquals("0:0", message4);
	}

}
