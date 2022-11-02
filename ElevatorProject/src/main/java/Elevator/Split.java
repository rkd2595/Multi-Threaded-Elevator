package Elevator;

/**
 * 
 * @author Florian, Rajesh, Rolando, Mark
 * Splits a command by ":" and finds the left and right numbers.
 */
public class Split implements Splitter
{
	@Override
	public int getSource(String input)
	{
		input.indexOf(":");
		String[] source = input.split(":");
		int src = Integer.parseInt(source[0]);
		
		return src;
	}
	
	@Override
	public int getDestination(String input)
	{
		input.indexOf(":");
		String[] Dest = input.split(":");
		int destination = Integer.parseInt(Dest[1]);
	
		return destination;
	}
}
