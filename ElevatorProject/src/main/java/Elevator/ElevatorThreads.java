package Elevator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * 
 * @author Florian, Rajesh, Rolando, Mark
 * This class has the elevator threads.
 * Moves the elevator and talks with the FrameView to update the visuals.
 */
public class ElevatorThreads implements Runnable, Processing // implement frameGUI?
{
	private Messages messages;
	private int name;
	private boolean run;
	private int queue;
	private int source;
	private int destination;
	private String input;
	private int currentState = 0;
	private static final Logger LOGGER = LogManager.getLogger(ElevatorThreads.class);

	public ElevatorThreads(Messages messages, int name, int currentFloor)
	{
		this.name = name;
		this.messages = messages;
		run = true;
		queue = 0;
		this.currentState = currentFloor;
		messages.postMessage(QueueType.OUTPUT, name, 'F', currentState);

	}

	/**
	 * Runs when the ConsoleInput thread gets started from the ElevatorStart class.
	 * Continues running while run == true.
	 */
	public void run()
	{
		LOGGER.info("Thread name: " + name);

		while (run == true)
		{
			try
			{
				processMessage();
				Thread.sleep(2000); // let other things happen

			} catch (InterruptedException e)
			{
				e.printStackTrace();
				LOGGER.error("Error: " + e);
			}
		}
		LOGGER.info("Exit for thread " + name);
	}


	/**
	 * Inherited method from the processing interface.
	 * Takes in a message with type 'C' and processes it.
	 * Sends message to FrameView via type 'F'
	 */
	@Override
	public void processMessage()
	{
		char head;
		String message;
		ConfigFile configFile = new ConfigFile();

		if ((message = messages.getMessage(QueueType.INPUT, name, 'C', true)) != null)
		{
			Split split = new Split();
			source = split.getSource(message);
			;
			destination = split.getDestination(message);

			LOGGER.info("Source: " + source + " \nDestination: " + destination);

			messages.postMessage(QueueType.OUTPUT, name, 'C', "Running");
			LOGGER.info("In the processMessage: " + message);

			if ((head = message.charAt(0)) == 'E' || (head = message.charAt(0)) == 'e')										// Exit
			{ 
				run = false;
			}

			if (currentState != source)
			{
				if (currentState < source)
				{
					for (int i = currentState; i <= source; i++)
					{
						if ((message = messages.getMessage(QueueType.INPUT, name, 'J', true)) != null)						// JSON
						{
							messages.postMessage(QueueType.OUTPUT, name, 'X', currentState);
							break;
						} else
						{
							messages.postMessage(QueueType.OUTPUT, name, 'S', 2);
							messages.postMessage(QueueType.OUTPUT, name, 'F', i);
							currentState = i;
							messages.postMessage(QueueType.OUTPUT, name, 'X', currentState);
							try
							{
								Thread.sleep(1000);
							} catch (InterruptedException e)
							{
								e.printStackTrace();
								LOGGER.error("Error: " + e);
							}
						}
					}
				} else if (currentState > source)
				{
					for (int i = currentState; i >= source; i--)
					{
						if ((message = messages.getMessage(QueueType.INPUT, name, 'J', true)) != null)
						{
							messages.postMessage(QueueType.OUTPUT, name, 'X', currentState);
							break;
						} else
						{
							messages.postMessage(QueueType.OUTPUT, name, 'S', 2);
							messages.postMessage(QueueType.OUTPUT, name, 'F', i);
							currentState = i;
							messages.postMessage(QueueType.OUTPUT, name, 'X', currentState);
							try
							{
								Thread.sleep(1000);
							} catch (InterruptedException e)
							{
								e.printStackTrace();
								LOGGER.error("Error: " + e);
							}
						}
					}
				}

			}

			messages.postMessage(QueueType.OUTPUT, name, 'S', 1);
			messages.postMessage(QueueType.OUTPUT, name, 'C', "Stopped");
			try
			{
				Thread.sleep(1000);
			} catch (InterruptedException e)
			{
				e.printStackTrace();
				LOGGER.error("Error: " + e);
			}

			messages.postMessage(QueueType.OUTPUT, name, 'S', 2);
			messages.postMessage(QueueType.OUTPUT, name, 'C', "Running");

			if (destination < source)
			{
				for (int i = source; i >= destination; i--)
				{
					if ((message = messages.getMessage(QueueType.INPUT, name, 'J', true)) != null)
					{
						messages.postMessage(QueueType.OUTPUT, name, 'X', currentState);
						break;
					} else
					{
						messages.postMessage(QueueType.OUTPUT, name, 'S', 2);
						messages.postMessage(QueueType.OUTPUT, name, 'F', i);
						currentState = i;
						messages.postMessage(QueueType.OUTPUT, name, 'X', currentState);
						try
						{
							Thread.sleep(1000);
						} catch (InterruptedException e)
						{
							e.printStackTrace();
							LOGGER.error("Error: " + e);
						}
					}
				}

			} else if (source < destination)
			{
				for (int i = source; i <= destination; i++)
				{
					if ((message = messages.getMessage(QueueType.INPUT, name, 'J', true)) != null)
					{
						messages.postMessage(QueueType.OUTPUT, name, 'X', currentState);
						break;
					} else
					{
						messages.postMessage(QueueType.OUTPUT, name, 'S', 2);
						messages.postMessage(QueueType.OUTPUT, name, 'F', i);
						currentState = i;
						messages.postMessage(QueueType.OUTPUT, name, 'X', currentState);
						try
						{
							Thread.sleep(1000);
						} catch (InterruptedException e)
						{
							e.printStackTrace();
							LOGGER.error("Error: " + e);
						}
					}
				}
			}
			LOGGER.info("current state: " + currentState);

			messages.postMessage(QueueType.OUTPUT, name, 'S', 0);
			messages.postMessage(QueueType.OUTPUT, name, 'S', 1);
			messages.postMessage(QueueType.OUTPUT, name, 'C', "Stopped");
			try
			{
				Thread.sleep(1000);
			} catch (InterruptedException e)
			{
				e.printStackTrace();
				LOGGER.error("Error: " + e);
			}
			messages.postMessage(QueueType.OUTPUT, name, 'S', 0);
			messages.postMessage(QueueType.OUTPUT, name, 'C', "Idle");
		}

	}

}
