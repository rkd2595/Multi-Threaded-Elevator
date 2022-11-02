package Elevator;

import java.util.ArrayList;
import java.util.Arrays;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * 
 * @author Florian, Rajesh, Rolando, Mark
 * This class finds the most suitable elevator for a job.
 * 
 */
public class Scheduler implements Runnable, Processing
{
	private int source;
	private int destination;
	ArrayList<Integer> elevatorFloors = new ArrayList<>();
	private static final Logger LOGGER = LogManager.getLogger(Scheduler.class);
	ConfigFile configFile = new ConfigFile();
	Messages messages = new Messages();
	private boolean run = true;

	public Scheduler(Messages messages)
	{
		this.messages = messages;
	}

	/**
	 * Runs when the Scheduler thread gets started from the ElevatorStart class.
	 * Continues running while run == true.
	 */
	@Override
	public void run()
	{
		ConfigFile.readFromFile();

		for (int i = 0; i < configFile.getNumberOfElevators(); i++)
		{
			elevatorFloors.add(0);
		}

		LOGGER.info("Starting floor of each elevators: " + elevatorFloors);
		while (run == true)
		{
			try
			{
				processMessage();
				Thread.sleep(2000); // let other things happen

			} catch (InterruptedException e)
			{
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * Inherited method from the processing interface.
	 * Takes in a message with type 'Z' or 'R' and processes it.
	 * 'Z' sends message to ElevatorThreads. 'R' for sending elevators to the default floor which is 0.
	 */
	@Override
	public void processMessage()
	{
		char head;
		String message;

		if ((message = messages.getMessage(QueueType.INPUT, 100, 'R', true)) != null)
		{
			for (int i = 0; i < configFile.getNumberOfElevators(); i++)
			{
				try
				{
					Thread.sleep(1000);
				} catch (InterruptedException e)
				{
					e.printStackTrace();
				}

				String string = Integer.toString(elevatorFloors.get(i)) + ":" + Integer.toString(0);
				messages.postMessage(QueueType.INPUT, i + 1, 'C', string);
				elevatorFloors.set(i, 0);
			}
		}

		if ((message = messages.getMessage(QueueType.INPUT, 10, 'Z', true)) != null)
		{

			if ((head = message.charAt(0)) == 'E' || (head = message.charAt(0)) == 'e')
			{ 
				run = false;

			} else
			{
				Split split = new Split();
				source = split.getSource(message);
				LOGGER.trace("Source: " + source);
				destination = split.getDestination(message);
				LOGGER.trace("Destination: " + destination);
				int ElevatorPriority = 1;
				int minValue = Math.abs(elevatorFloors.get(0) - source);

				for (int i = 0; i < elevatorFloors.size(); i++)

				{
					if ((Math.abs(elevatorFloors.get(i) - source) < minValue))
					{
						minValue = Math.abs(elevatorFloors.get(i) - source);
						ElevatorPriority = i + 1;
						LOGGER.debug("elevator priority number: " + ElevatorPriority);
					}
				}
				LOGGER.trace("Minimum floors between selected elevator and source: " + minValue);
				
				elevatorFloors.set(ElevatorPriority - 1, destination);
				LOGGER.debug("elevator priority: " + ElevatorPriority);

				messages.postMessage(QueueType.INPUT, ElevatorPriority, 'C', message);
				LOGGER.debug("in the scheduler: " + message + " " + ElevatorPriority);
				LOGGER.debug("new stored value: " + minValue);
			}
			
		}	
		
	}
	
}
