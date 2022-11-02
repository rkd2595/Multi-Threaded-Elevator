package Elevator;


import java.util.ArrayList;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * 
 * @author Florian, Rajesh, Rolando, Mark
 * The 'Main' method.
 * Starts the threads for this application.
 * Includes starting the thread to set up the visuals via FrameView.
 */

public class ElevatorStart
{
	
	private static final Logger LOGGER = LogManager.getLogger(ElevatorStart.class);

	
	public static void main(String[] args)
	{
		ConfigFile configFile = new ConfigFile();
		ConfigFile.readFromFile();
		int numberOfFloors = configFile.getNumberOfFloors();
		int numberOfElevators = configFile.getNumberOfElevators();  
		Messages messages = new Messages();
		ArrayList<Integer> currentElevatorFloors  = new ArrayList<>();	
				
		LOGGER.info("Number of floors: " + numberOfFloors );
		LOGGER.info("Number of Elevators: " + numberOfElevators );
		
		for (int i = 0; i < numberOfElevators; i++) {
			currentElevatorFloors.add(0);
		}			
		LOGGER.info("Current Elevator Floors: " + currentElevatorFloors );
			
		ArrayList<Thread> threads = new ArrayList<>();
		for (int i = 0; i < numberOfElevators; i++) {
			threads.add(new Thread( new ElevatorThreads(messages, i+1, currentElevatorFloors.get(i))));				
		}
		LOGGER.info("Elevator thread arraylist: " + threads );
			
		FrameView view = new FrameView("title goes here", 0, numberOfFloors, numberOfElevators, messages);				// Creating the view
		Thread graphics = new Thread(view);
		graphics.start();
					
		Thread consoleInputThread = new Thread(new ConsoleInput(messages, 2));											// Creating the consoleInput Thread
		consoleInputThread.start();
		
		Thread automateThread = new Thread(new Automate(messages));														// Creating the automate Thread
		automateThread.start();
		
		Thread schedulerThread = new Thread(new Scheduler(messages));													// Creating the scheduler Thread
		schedulerThread.start();
				
		try
		{
			Thread.sleep(500);
			
		} catch (InterruptedException e1)
		{
			e1.printStackTrace();
			LOGGER.error("Error: " + e1 );
		}


		for (int i = 0; i < numberOfElevators; i++) {
			threads.get(i).start();																						// starts the elevator threads
			
		}

		
		try
		{
			Thread.sleep(500);
			
		} catch (InterruptedException e1)
		{
			e1.printStackTrace();
			LOGGER.error("Error: " + e1 );
		}
		


	}

}
