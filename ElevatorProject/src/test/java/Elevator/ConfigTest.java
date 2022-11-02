package Elevator;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * Testing ConfigFile class
 * @author Florian, Rajesh, Rolando, Mark
 *
 */
public class ConfigTest
{

/**
 * Testing to check if correct value of numberOfFloros is returned.
 */
@Test
public void testConfigFile() {
	ConfigFile configFile = new ConfigFile();
	ConfigFile.readFromFile();
	int numberOfFloors = configFile.getNumberOfFloors();
	assertEquals(16, numberOfFloors);
}

/**
 * Testing to check if correct value of numberOfElevators is returned
 */
@Test
public void testConfigFile2() {
	ConfigFile configFile = new ConfigFile();
	ConfigFile.readFromFile();
	int numberOfElevators = configFile.getNumberOfElevators();
	assertEquals(4, numberOfElevators);
}
}
