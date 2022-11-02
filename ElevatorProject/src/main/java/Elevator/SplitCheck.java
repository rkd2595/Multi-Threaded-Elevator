package Elevator;

/**
 * 
 * @author Florian, Rajesh, Rolando, Mark
 * Returns 1 = true, 0 = false.
 */
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SplitCheck implements Splitter
{
	private static final Logger LOGGER = LogManager.getLogger(SplitCheck.class);
	ConfigFile configFile = new ConfigFile();

	@Override
	public int getDestination(String message)
	{
		ConfigFile.readFromFile();
		int numberOfFloors = configFile.getNumberOfFloors();
		
		message.indexOf(":");
		String[] Dest = message.split(":");
		LOGGER.trace("String array after split for checkSource(): " + Dest);
		LOGGER.debug("message in checkDestination(): " + message);

		int destination = Integer.parseInt(Dest[1]);
		LOGGER.debug("Destination value in getDestination(): " + destination);

		if (destination > numberOfFloors)
		{
			LOGGER.warn("Invalid input. There are only " + numberOfFloors + " floors.");
			System.out.println("Invalid input. There are only " + numberOfFloors + " floors.");

			return 0;
		}

		return 1;
	}

	@Override
	public int getSource(String message)
	{
		ConfigFile.readFromFile();
		int numberOfFloors = configFile.getNumberOfFloors();

		message.indexOf(":");
		String[] source = message.split(":");
		LOGGER.trace("String array after split for checkSource(): " + source);

		int src = Integer.parseInt(source[0]);
		LOGGER.debug("Source value in getSourcen(): " + src);
		
		if (src > numberOfFloors)
		{
			LOGGER.warn("Invalid input. There are only " + numberOfFloors + " floors.");
			System.out.println("Invalid input. There are only " + numberOfFloors + " floors.");
			return 0;
		}

		return 1;
	}
}
