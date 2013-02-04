package clientServer;



public class Communication {
	
	private static final int  CLIENT_CONNECT = 0;
	private static final int MENU_DISPLAYED = 1;
	private static final int ROUTE_CHOSEN = 2;

	private Server server;
	
	
	private int state = CLIENT_CONNECT;
	
	public Communication(Server server){
		this.server = server;
	}
	
	public String processInput(String input){
		String output = null;
	if (state == CLIENT_CONNECT){
			output = "Please enter the route that you wish to book a ticket for using the following " +
					"route numbers: Cork(1), Galway(2) or Belfast(3).";
			state = MENU_DISPLAYED;
		}
	else if (state == MENU_DISPLAYED){
		if (input.equals("1")||input.equals("2")||input.equals("3")){
			int selectedRoute = Integer.parseInt(input) - 1;
			try{
				server.bookASeat(selectedRoute);
				output = "Ticket to "+server.getRouteDestination(selectedRoute)+
						" successfully booked! Would you like to book another route? (Y/N)";
				state = ROUTE_CHOSEN;
			} catch (IllegalArgumentException e){
				output = e.getMessage()+". Would you like to book another route? (Y/N)";
				state = ROUTE_CHOSEN;
			}
		}
		else{
			output = "Invalid route number entered. Press to try again! Cork(1), Galway(2) or Belfast(3)";
			}
		}
	else if (state == ROUTE_CHOSEN){
		if (input.equalsIgnoreCase("y")){
			output = "Please select a route, Cork(1), Galway(2) or Belfast(3)";
			state = MENU_DISPLAYED;
		}
		else
			output = "Bye.";
	}
		return output;
	}
	

}
