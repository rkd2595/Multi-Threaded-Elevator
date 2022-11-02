package Elevator;

import java.util.ArrayList;

/**
 * Generic messaging to send and receive Event classes
 * Contains two Event queues, one for input events and one for output Events
 * Events can be added to the queues by calling the postMessage methods
 * Events can be retrieved from an queue by calling the getMessage 
 * and the getAllMessages methods
 *  
 * @author Donald Witcombe
 * @Version 1.01
 *
 */
public class Messages
{
	private ArrayList<Event> inputs;			// list of input events 
	private ArrayList<Event> outputs;			// list of output events
	
	/**
	 * Constructor for messages. It needs no parameters
	 * It creates an instance of the input and output ArayLists. 
	 */
	public Messages() {
		inputs = new ArrayList<Event>();
		outputs = new ArrayList<Event>();
	}
	
	/**
	 * Adds an Event to the specified list with an integer as the message type.
	 * @param queueType TypeType the event array type, INPUT or OUTPUT
	 * @param id identifier for the receiver like thread number
	 * @param eventType The event type like C for command, E for exit  
	 * @param message message The message you want to pass
	 */
	public synchronized void postMessage(QueueType queueType, int id, char eventType, int message) {
		postMessage(queueType, id, eventType, "" + message);
	}
	
	/**
	 * Adds an Event to the specified list with a String as the message type.
	 * @param queueType TypeType the event array type, INPUT or OUTPUT
	 * @param id identifier for the receiver like thread number
	 * @param eventType The event type like C for command, E for exit  
	 * @param message The message you want to pass
	 */
	public synchronized void postMessage(QueueType queueType, int id, char eventType, String message) {
		
		switch (queueType) {
		case INPUT:
			inputs.add(new Event(id, eventType, message));
			break;
		case OUTPUT :
			outputs.add(new Event(id, eventType, message));
			break;
		}
	}
	
	
	/**
	 * Adds an Event to the specified list with a reference type as the Object type.
	 * @param queueType TypeType the event array type, INPUT or OUTPUT
	 * @param id identifier for the receiver like thread number
	 * @param eventType The event type like C for command, E for exit  
	 * @param object a reference type to pass
	 */
	public synchronized void postMessage(QueueType queueType, int id, char eventType, Object object) {
		
		switch (queueType) {
		case INPUT:
			inputs.add(new Event(id, eventType, object));
			break;
		case OUTPUT :
			outputs.add(new Event(id, eventType, object));
			break;
		}
	}
		
	/**
	 * Gets the first Event in the specified Input or Output array that matches the id and head parameters.
	 * @param queueType Type the event array type, INPUT or OUTPUT
	 * @param id identifier for the receiver like thread number
	 * @param eventType The event type like C for command, E for exit  
	 * @param remove
	 * @return the message in the event
	 */
	public synchronized String getMessage(QueueType queueType, int id, char eventType, boolean remove) {
		String message = null;
		
		switch (queueType) {
		case INPUT:
			message = getMessage(inputs, id, eventType, remove);
			break;
		case OUTPUT :
			message = getMessage(outputs, id, eventType, remove);
			break;
		}	
		
		return message;
		
	}
	
	
	/**
	 * Gets the first Event in the specified Input or Output array that matches the id and head parameters.
	 * @param queueType Type the event array type, INPUT or OUTPUT
	 * @param id identifier for the receiver like thread number
	 * @param eventType The event type like C for command, E for exit  
	 * @param remove
	 * @return the message in the event
	 */
	public synchronized Object getMessageObject(QueueType queueType, int id, char eventType, boolean remove) {
		Object object = null;
		
		switch (queueType) {
		case INPUT:

			object = getMessageObj(inputs, id, eventType, remove);
			break;
		case OUTPUT :
			object = getMessageObj(outputs, id, eventType, remove);
			break;
		}	
		
		return object;
		
	}	
	
	/**
	 * Return all events matching the select queue with matching id and eventType  
	 * @param queueType Type the event array type, INPUT or OUTPUT
	 * @param id identifier for the receiver like thread number
	 * @param eventType The event type like C for command, E for exit  
	 * @param remove remove the event read
	 * @return An ArrayList of the matching events in the queue
	 */
	public synchronized ArrayList<String> getAllMessages(QueueType queueType, int id, char eventType, boolean remove) {
		ArrayList<String> message = null;
		
		switch (queueType) {
			case INPUT:
				message = getAllMessages(inputs, id, eventType, remove);
				break;
			case OUTPUT :
				message = getAllMessages(outputs, id, eventType, remove);
				break;
		}	
		
		return message;
		
	}	

	/**
	 * Return an ArrayList with the contents of the selected queue
	 * @param queueType the event array type, INPUT or OUTPUT
	 * @return An ArrayList of the events in the queue
	 */
	public ArrayList<Event> getEventQueue(QueueType queueType){
		ArrayList<Event> outEvent = null;
		
		switch (queueType) {
			case INPUT:
				outEvent = inputs;
				break;
			case OUTPUT :
				outEvent = outputs;
				break;
		}	

		return (outEvent);
	}
	
	/**
	 * Return the number of events in the selected queue
	 * @param queueType the event array type, INPUT or OUTPUT
	 * @return the size of the queue
	 */
	public int getEventSize(QueueType queueType){
		int outsize = 0;
		
		switch (queueType) {
		case INPUT:
			outsize = inputs.size();
			break;
		case OUTPUT :
			outsize = outputs.size();
			break;
		}	

		return outsize;
	}
	

	/**
	 * Get the message from the selected with the matching id and eventType queue. 
	 * If one exists return the message if not return null. 
	 * @param events The selected event queue
	 * @param id identifier for the receiver like thread number
	 * @param eventType The event type like C for command, E for exit  
	 * @param delete delete the event after reading it
	 * @return the message in the matching event or null if not found
	 */
	private String getMessage(ArrayList<Event> events, int id, char eventType, boolean delete) {
		String message = null; 

		for (Event event : events) {
			if (event.getId() == id && event.getType() == eventType) {		// first match
				
				if ((message = event.getMessage())!= null ) {
					if (delete == true) {
						events.remove(event);
					}
					break;
				}
			}
		}
		return message;
	}
	
	
	/**
	 * Get the Object from the selected with the matching id and eventType queue. 
	 * If one exists return the message if not return null. 
	 * @param events The selected event queue
	 * @param id identifier for the receiver like thread number
	 * @param eventType The event type like C for command, E for exit  
	 * @param delete delete the event after reading it
	 * @return the message in the matching event or null if not found
	 */
	private Object getMessageObj(ArrayList<Event> events, int id, char eventType, boolean delete) {
		Object object = null; 

		for (Event event : events) {
			if (event.getId() == id && event.getType() == eventType) {		// first match
				
				if ((object = event.getObject())!= null ) {
					if (delete == true) {
						events.remove(event);
					}
					break;
				}
			}
		}
		return object;
	}	
	
	/**
	 * Return an ArrayList of all messages in a queue
	 * @param events The selected event queue
	 * @param id identifier for the receiver like thread number
	 * @param eventType The event type like C for command, E for exit  
	 * @param delete delete the event after reading it
	 * @return ArrayList of events
	 */
	private ArrayList<String> getAllMessages(ArrayList<Event> events, int id, char eventType, boolean delete) {
		ArrayList<String> messages = null; 
		String message;

		for (Event event : events) {
			if (event.getId() == id && event.getType() == eventType) {		// first match
				
				if ((message = event.getMessage())!= null ) {
					messages.add(message);
					
					if (delete == true) {
						events.remove(event);
					}
					break;
				}
			}
		}
		return messages;
	}	
	
	/**
	 * Deletes all events in the select queue
	 * @param Queue type, INPUT or OUTPUT 
	 */
	public void clear(QueueType type) {
		switch (type) {
		case INPUT:
			inputs.clear();
			break;
		case OUTPUT :
			outputs.clear();
			break;
		}	
		
	}
	
	/**
	 * Delete all events in all queues
	 */
	public void clearAll() {
		clear(QueueType.INPUT);
		clear(QueueType.OUTPUT);
	}	
	

}
