package Elevator;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.timeout;
import static org.mockito.Mockito.verify;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.junit.jupiter.MockitoExtension;
/**
 * 
 * @author Florian, Rajesh, Rolando, Mark
 * Testing Automate class.
 */

@ExtendWith(MockitoExtension.class)
public class AutomateTest
{

	@Test
	public void testAutomate() {
		
		Automate automateMock = mock(Automate.class);
		assertNotNull(automateMock);
	
	doNothing().when(automateMock).automatic();
	automateMock.automatic();
	verify(automateMock,timeout(1)).automatic();
		
	}
}
