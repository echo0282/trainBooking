package clientServer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.List;
public class Client {
	
	BookingInterface stub;
	BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
	
	
	private Client(){

	}
	
    public static void main(String[] args) {
    	Client client = new Client();
    	client.openConnection();
    }
    
    public void openConnection(){
    	try{
    		Registry registry = LocateRegistry.getRegistry("localhost", 5005);
    		stub = (BookingInterface) registry.lookup("BookingInterface");
        	System.out.println("\t\t*********Welcome to the Train Booking System***********");
    		printMenu();
    	} catch (Exception e){
    		System.err.println("Client exception: "+e.toString());
    		return;
    	}
    }
    
    private void printMenu() throws IOException{
    	List<Route> routes = stub.getRoutes();
    	System.out.println("Please select a route by entering the corresponding route number: ");
    	for (int i = 0; i < routes.size(); i++){
    		System.out.println(routes.get(i).getDestination()+" ("+(i+1)+")");
    	}
    	parseUserInput();
    }
    
    private void parseUserInput() throws IOException {
    	String selectedRoute = in.readLine();
    	int route = 0;
    	try{
    		route = Integer.parseInt(selectedRoute);
    	} catch(NumberFormatException e){
    		System.out.println("Invalid route entered! Please try again.");
    		printMenu();
    	}
    	if (route == 1 || route == 2 || route ==3)
    		bookASeat(route - 1);
    	else{
    		System.out.println("Invalid route entered! Please try again.");
    		printMenu();
    	}
    	
    }
    
    private void bookASeat(int route) throws IOException{
    	try{
    		stub.bookASeat(route);
    		System.out.println("Ticket to "+stub.getRouteDestination(route)+" successfully booked!");
    	}catch (FullyBookedException e){
    		System.out.println(e.getMessage());
    	}
    	if (anotherTicket()){
    		printMenu();
    	}
    	else{
    		System.out.println("Goodbye");
    	}
    }
    
    private boolean anotherTicket() throws IOException{
    	System.out.println("Do you want to book another ticket? (Y/N)");
    	String choice = in.readLine();
    	if (choice.equalsIgnoreCase("Y"))
    		return true;
    	return false;
    }
    
}
