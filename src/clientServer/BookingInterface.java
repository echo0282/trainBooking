package clientServer;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface BookingInterface extends Remote{
	
	public void bookASeat(int route) throws RemoteException;
	
	public String getRouteDestination(int route) throws RemoteException;
	
}
