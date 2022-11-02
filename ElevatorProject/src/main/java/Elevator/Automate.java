package Elevator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * 
 * @author Florian, Rajesh, Rolando, Mark
 * Automate class includes methods to automate commands to the scheduler.
 * This class should allow the elevators to run without any manual input.
 */

public class Automate implements Runnable
{
	Messages messages = new Messages();
	ConfigFile configFile = new ConfigFile();
	private boolean run = true;
	private static final Logger LOGGER = LogManager.getLogger(Automate.class);

	public Automate(Messages messages)
	{
		this.messages = messages;
	}
	
	
	/**
	 * Runs when the Automate thread gets started from the ElevatorStart class.
	 * Continues running while run == true.
	 */
	@Override
	public void run()
	{
		ConfigFile.readFromFile();
		while (run == true)
		{
			automatic();
			try
			{
				Thread.sleep(1000);
			} catch (InterruptedException e)
			{
				e.printStackTrace();
			}
		}

	}

	/**
	 * Receives a message from the ConsoleInput to start automating commands.
	 * Automates commands by selecting a random number from 0 - number of floors.
	 */
	public void automatic()
	{
		String message;
		char head;
		boolean randomizer = true;

		if ((message = messages.getMessage(QueueType.INPUT, 20, 'A', true)) != null)					// Checks for 'A' type messages
		{
			int source;
			int destination;
			int numberOfFloors = configFile.getNumberOfFloors(); // 16
			while (randomizer == true)
			{
				if ((message = messages.getMessage(QueueType.INPUT, 20, 'Q', true)) != null)			// Checks for 'Q'. Stops automation
				{
					randomizer = false;
					System.out.println("Stopped. Finishing current jobs.");
				}

				source = (int) (Math.random() * numberOfFloors);										// Random number creation
				destination = (int) (Math.random() * numberOfFloors);
				String command = Integer.toString(source) + ":" + Integer.toString(destination);

				messages.postMessage(QueueType.INPUT, 10, 'Z', command); 								// sends message with type 'Z'
				LOGGER.trace("Message delivered to INPUT queue with single: " + command);

				try
				{
					Thread.sleep(1000);
				} catch (InterruptedException e)
				{
					e.printStackTrace();
				}
			}
		}

	}

}
