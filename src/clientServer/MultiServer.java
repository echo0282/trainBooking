package clientServer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class MultiServer extends Thread{

	private Socket socket = null;
	private Server server;

	public MultiServer(Socket socket, Server server){
		super("MultiServer");
		this.socket = socket;
		this.server = server;
	}

	@Override
	public void run() {

		boolean justConnected = true;

		try {
			System.out.println("Client Connected!");
			PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
			BufferedReader in = new BufferedReader(
					new InputStreamReader(
							socket.getInputStream()));

			String inputLine, outputLine;
			Communication coms = new Communication(server);
			outputLine = coms.processInput(null);
			out.println(outputLine);

			while ((inputLine = in.readLine()) != null) {
				outputLine = coms.processInput(inputLine);
				out.println(outputLine);
				if (outputLine.equals("Bye"))
					break;
			}
			out.close();
			in.close();
			socket.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}		
}


