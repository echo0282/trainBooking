package clientServer;

	import java.net.*;
import java.util.ArrayList;
import java.util.List;
import java.io.*;
	 
	public class Server {
		
		private List<Route> routes = new ArrayList<Route>() {{
			add(new Route("Cork"));
			add(new Route("Galway"));
			add(new Route("Belfast"));
		}};
		
		
	    public static void main(String[] args) throws IOException {
	 
	        ServerSocket serverSocket = null;
	        try {
	            serverSocket = new ServerSocket(4444);
	        } catch (IOException e) {
	            System.err.println("Could not listen on port: 4444.");
	            System.exit(1);
	        }
	 
	        Socket clientSocket = null;
	        try {	            
	        	System.out.println("Waiting on connection...");
	            clientSocket = serverSocket.accept();
	            System.out.println("Client connected!");
	        } catch (IOException e) {
	            System.err.println("Accept failed.");
	            System.exit(1);
	        }
	 
	        PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
	        BufferedReader in = new BufferedReader(
	                new InputStreamReader(
	                clientSocket.getInputStream()));
	        String inputLine, outputLine;
	        KnockKnockProtocol kkp = new KnockKnockProtocol();
	 
	        outputLine = kkp.processInput(null);
	        out.println(outputLine);
	 
	        while ((inputLine = in.readLine()) != null) {
	             outputLine = kkp.processInput(inputLine);
	             out.println(outputLine);
	             if (outputLine.equals("Bye."))
	                break;
	        }
	        out.close();
	        in.close();
	        clientSocket.close();
	        serverSocket.close();
	    }
}

