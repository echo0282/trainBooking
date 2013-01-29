package clientServer;

import java.net.*;
import java.util.ArrayList;
import java.util.List;
import java.io.*;

public class Server {

	private List<Route> routes = new ArrayList<Route>();
	private ServerSocket serverSocket;

	public Server(){
		routes.add(new Route("Cork"));
		routes.add(new Route("Galway"));
		routes.add(new Route("Belfast"));
	}

	public void openSocket() throws IOException{
		serverSocket = null;
		boolean listening = true;
		try {
			serverSocket = new ServerSocket(5005);
		} catch (IOException e) {
			System.err.println("Could not listen on port: 5005.");
			System.exit(-1);
		}
		
		while (listening)
			new MultiServer(serverSocket.accept(), this).start();
		
		serverSocket.close();

//		Socket clientSocket = null;
//		try {	            
//			System.out.println("Waiting for a client connection...");
//			clientSocket = serverSocket.accept();
//			System.out.println("Client connected!");
//		} catch (IOException e) {
//			System.err.println("Accept failed.");
//			System.exit(1);
//		}
//
//		PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
//		BufferedReader in = new BufferedReader(
//				new InputStreamReader(
//						clientSocket.getInputStream()));
//		String inputLine, outputLine;
//		Communication kkp = new Communication(this);
//
//		outputLine = kkp.processInput(null);
//		out.println(outputLine);
//
//		while ((inputLine = in.readLine()) != null) {
//			outputLine = kkp.processInput(inputLine);
//			out.println(outputLine);
//			if (outputLine.equals("Bye."))
//				break;
//		}
//		out.close();
//		in.close();
//		clientSocket.close();
//		serverSocket.close();
	}

	public static void main(String[] args) throws IOException {
		Server server = new Server();
		server.openSocket();
	}
	
	public String displayMenu(){
		return "Please select a route: Cork(1), Galway(2) or Belfast(3)";
	}
	public List<Route> getRoutes(){
		return routes;
	}
	
	public synchronized void bookASeat(int route) throws IllegalArgumentException{
		routes.get(route).bookASeat();
	}
	
	public String getRouteDestination(int route){
		return routes.get(route).getDestination();
	}
}

