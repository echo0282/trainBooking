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
    	try{
    		Registry registry = LocateRegistry.getRegistry("localhost");
    		BookingInterface stub = (BookingInterface) registry.lookup("BookingInterface");
    		routes = stub.getRoutes();		
    	} catch (Exception e){
    		System.err.println("Client exception: "+e.toString());
    	}
    }
    
    public void printMenu(){
    	System.out.println("*********Welcome to the Train Booking System***********");
    	System.out.println("Please select a route by entering the corresponding route number: ");
    	for (int i = 0; i < routes.size(); i++){
    		System.out.print(routes.get(i).getDestination()+"("+i+")\t");
    	}
    }
}
