package clientServer;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface BookingInterface extends Remote {
	
	public void bookASeat(int route) throws FullyBookedException, RemoteException;
	
	public String getRouteDestination(int route) throws RemoteException;
	
	public List<Route> getRoutes() throws RemoteException;
	
	public int getSpacesAvailable(int route) throws RemoteException;
	
}
