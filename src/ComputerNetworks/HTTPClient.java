package ComputerNetworks;
import java.net.*;
import java.io.BufferedReader;
import java.io.Console;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.io.IOException;

public class HTTPClient {
    public static void main (String args[]) throws IOException {
        
        Socket clientSocket = new Socket("localhost",6789);
        try {
        	
//			HTTPClient client = new HTTPClient(clientSocket, args[0], new URI(args[1] + ":" + args[2]), args[3]);
        	HTTPClient client = new HTTPClient(clientSocket, "GET", new URI("http://www.google.be/index.html:80"), "HTTP/1.0");
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
        clientSocket.close();
	}

    public HTTPClient (Socket clientSocket, String command, URI uri, String version) {
    	if (version.equals("HTTP/1.0")) {
    		try {
				DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
				BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
				outToServer.writeBytes(command + " " + uri.getPath() + "\n");
				
				String line = inFromServer.readLine();
				String response = line;
				
				while (line != null) {
						line = inFromServer.readLine();
						response = response + line;
				}
				System.out.println(response);
				clientSocket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
    		
    	}
    	else if (version.equals("HTTP/1.1"))
    	{
    		System.out.println("I AM THE ONE WHO KNOCKS");
    	}
    	else System.out.println("JESSE, YOU ARE THE BLOWFISH");
    }
    
}
