package clientServer;

import java.io.*;
import java.net.*;
 
public class Client {
    public static void main(String[] args) throws IOException {
 
        Socket socket = null;
        PrintWriter out = null;
        BufferedReader in = null;
 
        try {
            socket = new Socket("127.0.0.1", 5005);
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (UnknownHostException e) {
            System.err.println("Don't know about host: 127.0.0.1");
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to 127.0.0.1");
            System.exit(1);
        }
 
        BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
        String fromServer;
        String fromUser;
        System.out.println("*********Welcome to the Train Ticket Booking System*******");
        while ((fromServer = in.readLine()) != null) {
            System.out.println("Booking System: "+fromServer);
            if (fromServer.equals("Bye."))
                break;
             
            fromUser = stdIn.readLine();
        if (fromUser != null) {
                out.println(fromUser);
        }
        }
 
        out.close();
        in.close();
        stdIn.close();
        socket.close();
    }
}
