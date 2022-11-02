package Elevator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * 
 * @author Florian, Rajesh, Rolando, Mark
 * This class reads user input and checks it for errors.
 * 
 */
public class InputValidator
{
	private static final Logger LOGGER = LogManager.getLogger(InputValidator.class);
	
	/**
	 * Validates input
	 * @param input from the user
	 * @return boolean. True for valid.
	 */
	public boolean inputValidator(String input)
	{
		boolean checker = false;
		SplitCheck splitBoolean = new SplitCheck();
		SplitInput splitInput = new SplitInput();
		if (input.contains(","))
		{
			LOGGER.debug("check if it goes in split");
			String[] splitInputString = splitInput.InputSplit(input);
			LOGGER.trace("String array after split in inputValidator(): " + splitInputString);
			for (int i = 0; i < splitInputString.length; i++)
				
			{
				checker = false;
				LOGGER.debug("checking for loop: " + splitInputString[i]);
				if (splitInputString[i].matches("[0-9]+:[0-9]+"))
				{
					if ((splitBoolean.getSource(splitInputString[i]) == 0) || (splitBoolean.getDestination(splitInputString[i]) == 0))
					{
						LOGGER.debug("returning false?" + splitInputString[i]);
						checker = false;
						break;
					} else
					{
						LOGGER.debug("returning true?" + splitInputString[i]);
						checker = true;
					}

				}

			}
		} else if (input.matches("[0-9]+:[0-9]+")) {	
				if ((splitBoolean.getSource(input) == 1) && (splitBoolean.getDestination(input) == 1))
				{
					LOGGER.debug("returning true?");
					checker = true;
				} else
				{
					LOGGER.debug("returning false?");			
				}
			
		}
		else if (input.charAt(0) == 'J' || input.charAt(0) == 'j')															// JSON
		{
			LOGGER.info("stopping Json");
			checker = true;
		} else if (input.charAt(0) == 'E' || input.charAt(0) == 'e') {			
			LOGGER.info("exiting");
			checker = true;
		} else if (input.charAt(0) == 'A' || input.charAt(0) == 'a') {
			LOGGER.info("automating");			
			checker = true;
		} else if (input.charAt(0) == 'Q' || input.charAt(0) == 'q') {
			LOGGER.info("stopping automation");	
			checker = true;
		}else if (input.charAt(0) == 'R' || input.charAt(0) == 'r') {
			LOGGER.info("rebooting");			
			checker = true;
		}
		
		else {
			LOGGER.warn("invalid input");
			System.out.println("invalid input");
		}
		LOGGER.debug("last checker " + checker);
		
		return checker;

	}
}
