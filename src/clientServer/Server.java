package clientServer;

import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.io.*;

public class Server implements BookingInterface{

	private List<Route> routes = new ArrayList<Route>();

	public Server(){
		routes.add(new Route("Cork"));
		routes.add(new Route("Galway"));
		routes.add(new Route("Belfast"));
	}


	public static void main(String[] args) throws IOException {
		try{
			Server server = new Server();
			BookingInterface stub = (BookingInterface) UnicastRemoteObject.exportObject(server, 0);
			Registry registry = LocateRegistry.createRegistry(5005);
			//Registry registry = LocateRegistry.getRegistry();
			registry.bind("BookingInterface", stub);
			System.out.println("Server running, waiting on client connection....");
		} catch (Exception e){
			System.err.println("Server error:"+e.toString());
		}
	}

	@Override
	public void bookASeat(int route) throws IllegalArgumentException, RemoteException{
		routes.get(route).bookASeat();
	}
	
	@Override
	public String getRouteDestination(int route) throws RemoteException{
		return routes.get(route).getDestination();
	}


	@Override
	public List<Route> getRoutes() throws RemoteException {
		return routes;
	}
}

