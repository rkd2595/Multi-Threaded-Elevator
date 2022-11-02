package Elevator;

/**
 * 
 * @author Florian, Rajesh, Rolando, Mark
 * Returns an array for multiple commands
 */
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SplitInput
{
	private static final Logger LOGGER = LogManager.getLogger(SplitInput.class);

	public String[] InputSplit(String input)
	{
		String[] splitInputs = null;

		input = input.replace(" ", "");
		splitInputs = input.split(",");
		LOGGER.trace("String array after split in InputSplit(): " + splitInputs);

		return splitInputs;
	}
}
