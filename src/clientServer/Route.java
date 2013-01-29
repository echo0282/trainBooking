package clientServer;

public class Route {
	
	private String destination;
	private int spacesAvailable = 3;
	
	public Route(String destination){
		this.destination = destination;
	}
	
	public void bookASeat() throws IllegalArgumentException{
		if (spacesAvailable > 0)
			spacesAvailable--;
		else
			throw new IllegalArgumentException("Sorry, this route is fully booked");
	}
	
	public String getDestination(){
		return destination;
	}

}
