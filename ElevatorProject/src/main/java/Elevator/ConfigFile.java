package Elevator;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * 
 * @author Florian, Rajesh, Rolando, Mark
 * This class reads the configuration from Config/ConfigFile.
 * Sets the pre-determined number of floors and number of elevators.
 */

public class ConfigFile
{
	private static int numberOfFloors = 0;
	private static int numberOfElevators = 0;
	private static final Logger LOGGER = LogManager.getLogger(ConfigFile.class);

	
	/**
	 * Creates a config file if it doesn't exist.
	 * Reads the information in the config file
	 * @return the boolean result in the method.
	 */
	public static boolean readFromFile()
	{
		boolean readFile = false;
		String readLine;

		try
		{
			File file = new File("Config/ConfigFile");												// Looks for file
			if (file.exists())
			{
				FileReader reader = new FileReader(file);
				BufferedReader buffReader = new BufferedReader(reader);

				try
				{
					readLine = buffReader.readLine();												// read lines in the file
					reader.close();
					LOGGER.info("Config File information: " + readLine);

					String[] configOutput = readLine.split(",");
					numberOfFloors = Integer.parseInt(configOutput[0]);
					numberOfElevators = Integer.parseInt(configOutput[1]);

				} catch (IOException e)
				{
					e.printStackTrace();
					LOGGER.error("Error: " + e);
				}
			} else
			{
				LOGGER.warn("Config File does not exist");
				System.out.println("Config File does not exist");
			}

		} catch (FileNotFoundException e)
		{
			e.printStackTrace();
			LOGGER.error("Error: " + e);
		}

		return readFile;

	}

	// getters and setters
	public int getNumberOfFloors()
	{
		return numberOfFloors;
	}

	public void setNumberOfFloors(int numberOfFloors)
	{
		this.numberOfFloors = numberOfFloors;
	}

	public int getNumberOfElevators()
	{
		return numberOfElevators;
	}

	public void setNumberOfElevators(int numberOfElevators)
	{
		this.numberOfElevators = numberOfElevators;
	}

}
