package clientServer;

import java.io.Serializable;

public class Route implements Serializable{
	
	private String destination;
	private int spacesAvailable = 3;
	
	public Route(String destination){
		this.destination = destination;
	}
	
	public synchronized void bookASeat() throws IllegalArgumentException{
		if (spacesAvailable > 0)
			spacesAvailable--;
		else
			throw new IllegalArgumentException("Sorry, this route is fully booked");
	}
	
	public String getDestination(){
		return destination;
	}

}
