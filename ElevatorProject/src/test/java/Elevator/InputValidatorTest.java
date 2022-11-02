package Elevator;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

/**
 * Testing functionality of InputValidator class
 * @author Florian, Rajesh, Rolando, Mark
 *
 */
public class InputValidatorTest
{
	/**
	 * checking for valid and invalid inputs
	 */
	@Test
	public void testinputValidator1() {
		InputValidator Validator = new InputValidator();
		
		boolean check = Validator.inputValidator("1:10");
		
		assertTrue(check);
	}
	
	@Test
	public void testinputValidator2() {
		InputValidator Validator = new InputValidator();
		
		boolean check = Validator.inputValidator("1:10,2:10");
		
		assertTrue(check);
	}
	
	@Test
	public void testinputValidator3() {
		InputValidator Validator = new InputValidator();
		
		boolean check = Validator.inputValidator("2:20");
		
		assertFalse(check);
	}
	
	@Test
	public void testinputValidator4() {
		InputValidator Validator = new InputValidator();
		
		boolean check = Validator.inputValidator("ghadhjj");
		
		assertFalse(check);
	}
	
	@Test
	public void testinputValidator5() {
		InputValidator Validator = new InputValidator();
		
		boolean check = Validator.inputValidator("exit");
		
		assertTrue(check);
	}
	
	@Test
	public void testinputValidator6() {
		InputValidator Validator = new InputValidator();
		
		boolean check = Validator.inputValidator("Auto");
		
		assertTrue(check);
	}
	
	@Test
	public void testinputValidator7() {
		InputValidator Validator = new InputValidator();
		
		boolean check = Validator.inputValidator("quit");
		
		assertTrue(check);
	}
	
	@Test
	public void testinputValidator8() {
		InputValidator Validator = new InputValidator();
		
		boolean check = Validator.inputValidator("1:10,2:20,3:10");
		
		assertFalse(check);
	}
	
	@Test
	public void testinputValidator9() {
		InputValidator Validator = new InputValidator();
		
		boolean check = Validator.inputValidator("r");
		
		assertTrue(check);
	}
	
	@Test
	public void testinputValidator10() {
		InputValidator Validator = new InputValidator();
		
		boolean check = Validator.inputValidator("R");
		
		assertTrue(check);
	}
	
	@Test
	public void testinputValidator11() {
		InputValidator Validator = new InputValidator();
		
		boolean check = Validator.inputValidator("auto");
		
		assertTrue(check);
	}
	
	@Test
	public void testinputValidator12() {
		InputValidator Validator = new InputValidator();
		
		boolean check = Validator.inputValidator("Exit");
		
		assertTrue(check);
	}
	
	@Test
	public void testinputValidator13() {
		InputValidator Validator = new InputValidator();
		
		boolean check = Validator.inputValidator("Quit");
		
		assertTrue(check);
	}
}
