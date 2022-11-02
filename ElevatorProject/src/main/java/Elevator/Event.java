package Elevator;

/**
 * A simple event class that contains the information for an event to be identified
 * and a message
 * @author Donald Witcombe
 * @version 1.01
 *
 */
public class Event
{
	private int id;						// An identifier for the receiver like thread number

	private char type;					// Type of event identifier
	private String message;				// The events message as a string
	private Object object;				// A reference type
	
	
	/**
	 * event constructor to set all the required values
	 * @param id the event identifier.	
	 * @param type the event type.
	 * @param message event message as a string. 	
	 */
	public Event(int id, char type, String message) {
		this.id = id;
		this.type = type;
		this.message = message;
	}
	
	/**
	 * event constructor to set all the required values
	 * @param id the event identifier.	
	 * @param type the event type.
	 * @param message event message as a string. 	
	 */
	public Event(int id, char type, Object object) {
		this.id = id;
		this.type = type;
		this.object = object;
	}	

	/**
	 * Getter for the event identifier
	 * @return
	 */
	public int getId()
	{
		return id;
	}
	
	/**
	 * Getter for the event type
	 * @return
	 */
	public char getType()
	{
		return type;
	}

	/**
	 * Getter for the event message
	 * @return
	 */
	public String getMessage()
	{
		return message;
	}
	
	public Object getObject() {
		return object;
	}

}
