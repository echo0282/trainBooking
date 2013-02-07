package clientServer;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.List;
public class Client {
	
	private List<Route> routes;
	
	private Client(){

	}
	
    public static void main(String[] args) {
    	Client client = new Client();
    	client.openConnection();
    }
    
    public void openConnection(){
    	BookingInterface stub;
    	try{
    		Registry registry = LocateRegistry.getRegistry("localhost", 5005);
    		stub = (BookingInterface) registry.lookup("BookingInterface");
    		routes = stub.getRoutes();	
    		printMenu();
    	} catch (Exception e){
    		System.err.println("Client exception: "+e.toString());
    		return;
    	}
    }
    
    public void printMenu(){
    	System.out.println("\t\t*********Welcome to the Train Booking System***********");
    	System.out.println("Please select a route by entering the corresponding route number: ");
    	for (int i = 0; i < routes.size(); i++){
    		System.out.println(routes.get(i).getDestination()+"("+(i+1)+")");
    	}
    }
}
