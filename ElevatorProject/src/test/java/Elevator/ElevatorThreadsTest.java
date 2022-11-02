package Elevator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.intThat;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

/**
 * Testing functionality of ElevatorThreads class
 * @author Florian, Rajesh, Rolando, Mark
 *
 */
public class ElevatorThreadsTest
{
	/**
	 * Testing if the correct messages have been sent to the FrameView
	 */
	@Test
	public void testElevatorThreads1()
	{
		Messages messages = new Messages();

		
			Thread thread1 =new Thread(new ElevatorThreads(messages, 1, 0));

			messages.postMessage(QueueType.INPUT, 1, 'C', "0:5");

			thread1.start();

			try
			{
				Thread.sleep(1000);
			} catch (InterruptedException e)
			{
				e.printStackTrace();
			}

       String message1;
		
		int i = 0;
		String message = messages.getMessage(QueueType.OUTPUT,1, 'F', true);
		while((message1 = messages.getMessage(QueueType.OUTPUT,1, 'F', true)) !=null) {	

			int floor1 = Integer.parseInt(message1);

			assertEquals(i++, floor1);
		
		}
		
		
	}
	
	@Test
	public void testElevatorThreads2()
	{
		Messages messages = new Messages();

		
			Thread thread1 =new Thread(new ElevatorThreads(messages, 1, 0));

			messages.postMessage(QueueType.INPUT, 1, 'C', "0:12");

			thread1.start();

			try
			{
				Thread.sleep(1000);
			} catch (InterruptedException e)
			{
				e.printStackTrace();
			}

        String message;
		String message1;
		int i = 0;
		message = messages.getMessage(QueueType.OUTPUT,1, 'F', true);
		while((message1 = messages.getMessage(QueueType.OUTPUT,1, 'F', true)) !=null) {	

			int floor1 = Integer.parseInt(message1);

			assertEquals(i++, floor1);
		
		}
		
		
	}
	
	@Test
	public void testElevatorThreads3()
	{
		Messages messages = new Messages();

		
			Thread thread1 = new Thread(new ElevatorThreads(messages, 1, 0));

			messages.postMessage(QueueType.INPUT, 1, 'C', "2:5");

			thread1.start();

			try
			{
				Thread.sleep(1000);
			} catch (InterruptedException e)
			{
				e.printStackTrace();
			}

        String message;
		String message1;
		String message2;
		int i = 2;
		message = messages.getMessage(QueueType.OUTPUT,1, 'F', true);
		try
		{
			Thread.sleep(3000);
		} catch (InterruptedException e)
		{
			e.printStackTrace();
		}
		for(int j=0;j<=2;j++) {
		message1 = messages.getMessage(QueueType.OUTPUT,1, 'F', true);	

			int floor1 = Integer.parseInt(message1);

			assertEquals(j, floor1);
		
		}
		try
		{
			Thread.sleep(3000);
		} catch (InterruptedException e)
		{
			e.printStackTrace();
		}
		
		while((message2 = messages.getMessage(QueueType.OUTPUT,1, 'F', true)) !=null) {	

			int floor2 = Integer.parseInt(message2);

			assertEquals(i++, floor2);
		
		}
		
		
	}
	
}
