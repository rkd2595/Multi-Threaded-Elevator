package Elevator;

/**
 * 
 * @author Florian, Rajesh, Rolando, Mark
 * Used for the JSON file.
 */
public class ElevatorState
{

	private int name;
	private int currentFloor;
	
	
	public int getName()
	{
		return name;
	}
	public void setName(int name)
	{
		this.name = name;
	}
	public int getCurrentFloor()
	{
		return currentFloor;
	}
	public void setCurrentFloor(int currentFloor)
	{
		this.currentFloor = currentFloor;
	}
	@Override
	public String toString()
	{
		return "ElevatorState [name=" + name + ", currentFloor=" + currentFloor + "]";
	}
	
	
	
}
