package Elevator;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 
 * @author Florian, Rajesh, Rolando, Mark
 * This class runs a scanner to allow for user input
 * Sends messages to the respective classes depending on the user input.
 * checks for input validation via the InputValidator class.
 */
public class ConsoleInput implements Runnable
{

	private static final Logger LOGGER = LogManager.getLogger(ConsoleInput.class);
	private boolean run = true;
	private String input;
	private int name;
	private int numberOfFloors = 0;
	Messages messages;
	ElevatorThreads elevatorThreads;
	private static final ObjectMapper MAPPER = new ObjectMapper();

	public ConsoleInput(Messages messages, int name)
	{
		this.name = name;
		this.messages = messages;
	}

	/**
	 * Runs when the ConsoleInput thread gets started from the ElevatorStart class.
	 * Continues running while run == true.
	 */
	@Override
	public void run()
	{
		ConfigFile configFile = new ConfigFile();
		ConfigFile.readFromFile();
		numberOfFloors = configFile.getNumberOfFloors();

		while (run == true)
		{
			char head;

			input = getInput();
			LOGGER.info("Input provided: " + input);

			if (input == null)
			{
				LOGGER.warn("Invalid input when input is null");

			} else
			{

				multipleCommands(input);
				LOGGER.info("Input after multpleCommands(): " + input);

				if ((head = input.charAt(0)) == 'E' || (head = input.charAt(0)) == 'e')								// Stops the application from running.
				{
					LOGGER.info("Exit Input: " + input);
					System.out.println("Exited. Finishing current jobs.");
					run = false;

				}
			}

		}
	}

	/**
	 * Posts a message with a particular 'Char' and message depending on the input conditions.
	 * @param input which would be a valid user input.
	 */
	public void multipleCommands(String input)
	{
		char head;
		SplitInput splitInput = new SplitInput();
		ArrayList<ElevatorState> eleState = new ArrayList<>();
		ConfigFile file = new ConfigFile();
		ConfigFile.readFromFile();

		if ((head = input.charAt(0)) == 'A' || (head = input.charAt(0)) == 'a')										// posts 'A' type messages
		{
			messages.postMessage(QueueType.INPUT, 20, 'A', input);
		} else if ((head = input.charAt(0)) == 'Q' || (head = input.charAt(0)) == 'q')								// posts for 'Q' type messages
		{
			messages.postMessage(QueueType.INPUT, 20, 'Q', input);

		} else if ((head = input.charAt(0)) == 'R' || (head = input.charAt(0)) == 'r')								// posts for 'R' type messages
		{
			messages.postMessage(QueueType.INPUT, 100, 'R', input);
		} else
		{

			if (input.contains(","))
			{
				String[] splitInputString = splitInput.InputSplit(input);
				LOGGER.trace("String array after split in multipleCommands(): " + splitInputString);

				for (int i = 0; i < splitInputString.length; i++)
				{
					messages.postMessage(QueueType.INPUT, 10, 'Z', splitInputString[i]);							// posts for 'Z' type messages
					LOGGER.trace("Message delivered to INPUT queue with multiple commands: " + splitInputString[i]);
				}
			// 'J' is for JSON files
			} else if (input.charAt(0) == 'J' || input.charAt(0) == 'j')											// posts for 'J' type messages
			{
				for (int i = 1; i <= file.getNumberOfElevators(); i++)
				{
					messages.postMessage(QueueType.INPUT, i, 'J', 1);
					try
					{
						Thread.sleep(2000);
					} catch (InterruptedException e)
					{
						e.printStackTrace();
					}

					try
					{
						eleState.add(wjason(i));
						if (eleState.size() > 0)
						{
							FileWriter jfile = new FileWriter("JSON/ElevatorFloor.json", false);
							MAPPER.writeValue(jfile, eleState);							
						}

					} catch (IOException e)
					{
						e.printStackTrace();
					}

				}
				LOGGER.debug("eleState size: " + eleState.size());
				
				for (ElevatorState ele : eleState)
				{
					LOGGER.debug("ele " + ele.toString());
				}

			} else
			{
				messages.postMessage(QueueType.INPUT, 10, 'Z', input);
				LOGGER.trace("Message delivered to INPUT queue with single: " + input);
			}
		}

	}

	/**
	 * Receives user input via a scanner.
	 * Sends the input to InputValidator to check for a valid input
	 * 
	 */
	public String getInput()
	{
		boolean scannerRunning = true;
		InputValidator inputValid = new InputValidator();

		while (scannerRunning == true)
		{
			Scanner input = new Scanner(System.in);
			LOGGER.trace("Please insert a command: ");
			System.out.println("Please insert a command: ");

			String inputCommand = input.nextLine();
			LOGGER.debug("Currently moving: " + inputCommand);

			if (inputValid.inputValidator(inputCommand) == true)
			{
				scannerRunning = false;
				return inputCommand;
			} else
			{
				LOGGER.warn("Current input: " + inputCommand + " is invalid");
				System.out.println("Please provide valid input.");
				return null;
			}			
		}
		return null;

	}

	/**
	 * Writing to a JSON file. No method for reading.
	 * @param threadName
	 * @return
	 * @throws IOException
	 */
	public ElevatorState wjason(int threadName) throws IOException
	{
		String message;
		ElevatorState elevateState = new ElevatorState();
		while ((message = messages.getMessage(QueueType.OUTPUT, threadName, 'X', true)) != null)
		{
			int currFloor = Integer.parseInt(message);
			LOGGER.info("currentFloor " + currFloor);
			LOGGER.info("name " + name);

			elevateState.setName(threadName);
			elevateState.setCurrentFloor(currFloor);
		}
		return elevateState;
	}

}
