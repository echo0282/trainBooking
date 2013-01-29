package clientServer;

import java.util.ArrayList;
import java.util.List;

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
			output = server.displayMenu();
			state = MENU_DISPLAYED;
		}
	else if (state == MENU_DISPLAYED){
		if (input.equals("1")||input.equals("2")||input.equals("3")){
			int selectedRoute = Integer.parseInt(input);
			try{
				server.bookASeat(selectedRoute);
				output = "Seat successfully booked! Would you like to book another route? (Y/N)";
				state = ROUTE_CHOSEN;
			} catch (IllegalArgumentException e){
				output = e.getMessage();
			}
		}
		else{
			state = CLIENT_CONNECT;
			output = "Invalid route number entered. Please try again!";
			}
		}
	else if (state == ROUTE_CHOSEN){
		if (input.equalsIgnoreCase("y")){
			output = "Please select a route, Cork(1), Galway(2) or Belfast(3)";
			state = MENU_DISPLAYED;
		}
		else
			output = "Bye";
	}
		return output;
	}
	

}
