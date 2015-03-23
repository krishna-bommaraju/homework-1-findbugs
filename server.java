import java.io.*;  
import java.net.*;
public class server
{
	public static void main(String argv[]) throws Exception
		{	
			Socket serverSock;	
	
			//taking the port number
			System.out.println("Enter the port number:");
			BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
			
			// reading the port number as a string
			String portString=br.readLine();
			int port_no=Integer.parseInt(portString);
			
			// creating the server socket
			ServerSocket serverSocket = new ServerSocket(port_no);
			System.out.println("Waiting for the Client!");

			while(true) 
        		{

				//server accepts the client socket
	        			serverSock = serverSocket.accept();
				System.out.println("Connected to port no:"+port_no);
				HttpRequest req=new HttpRequest(serverSock);
	
				// creating new thread for multi-threading using request object
				Thread thread=new Thread(req);
				thread.start();
			}
		}
}


//http://www.careerbless.com/samplecodes/java/beginners/socket/SocketBasic1.php- referred.
//http://code.bretonstyle.net/?page_id=198-referred.
