import java.io.*;
import java.net.*;
import java.util.*;
public class client  
	{
	public static void main(String argsv[]) throws Exception
		{
	 	// creating a scanner
    		Scanner inputScanner = new Scanner( System.in );

   		 //  taking input from the user
    		System.out.print("Enter URl:");
    		String userInput = inputScanner.nextLine();

     		// creating the object
		URL url=new URL(userInput);
		int port_no=url.getPort();
		String ipadd=url.getHost();
		String fname=url.getFile();
		String file=fname.substring(1);

   		// creating a socket connection
		Socket clientSocket=new Socket(ipadd,port_no);
		DataOutputStream streamOut=new DataOutputStream(clientSocket.getOutputStream());
		DataInputStream streamIn=new DataInputStream(clientSocket.getInputStream());
		
		// sending request
		String httpRequest="GET "+file+" HTTP/1.1";
		FileWriter filewriter=new FileWriter("Log.txt",true);

    		// creating object to print into the file
		PrintWriter printwriter=new PrintWriter(filewriter);
		printwriter.write("\r\nClient Request:"+httpRequest);

		// sending the http request
    		streamOut.writeUTF(httpRequest);

		// retrieveing the responce from the server  
                       	String httpResponse=streamIn.readUTF();
   		System.out.println(httpResponse);

		// writting a http response       
    		printwriter.write("\r\nServer Response:"+httpResponse);

    		// printing the content in the file     
  		 if(httpResponse.equals("HTTP/1.1 200 ok"))
  			 {
			 byte[] a=new byte[1024];
			 streamIn.read(a, 0, a.length);
			 String output=new String(a);
			 System.out.println("File Content:"+output);
   			 }

    		// closing all connections
    		streamIn.close();
    		streamOut.close();
    		printwriter.close();
    		filewriter.close();
    		clientSocket.close();
   	 }
}



//http://www.careerbless.com/samplecodes/java/beginners/socket/SocketBasic1.php-referred.
