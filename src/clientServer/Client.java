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
    		printMenu();
    	} catch (Exception e){
    		System.err.println("Client exception: "+e.toString());
    		return;
    	}
    }
    
    private void printMenu() throws IOException{
    	List<Route> routes = stub.getRoutes();
    	System.out.println("\t\t*********Welcome to the Train Booking System***********");
    	System.out.println("Please select a route by entering the corresponding route number: ");
    	for (int i = 0; i < routes.size(); i++){
    		System.out.println(routes.get(i).getDestination()+" ("+(i+1)+")");
    	}
    	parseUserInput();
    }
    
    private void parseUserInput() throws IOException{
    	String selectedRoute = in.readLine();
    	int route = Integer.parseInt(selectedRoute);
    	if (route == 1 || route ==2 || route ==3)
    		bookASeat(route);
    	else{
    		System.out.println("Invalid route entered! Please try again.");
    		printMenu();
    	}
    	
    }
    
    private void bookASeat(int route) throws RemoteException{
    	try{
    		stub.bookASeat(route);
    	}catch (IllegalArgumentException e){
    		System.out.println(e.getMessage());
    	}
    }
    
}
