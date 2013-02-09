package clientServer;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface BookingInterface extends Remote {
	
	public void bookASeat(int route) throws IllegalArgumentException, RemoteException;
	
	public String getRouteDestination(int route) throws RemoteException;
	
	public List<Route> getRoutes() throws RemoteException;
	
}
