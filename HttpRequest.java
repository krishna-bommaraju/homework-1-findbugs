import java.io.*;
import java.net.*;
import java.util.StringTokenizer;

public class HttpRequest implements Runnable 
{

	//creating socket
	Socket socket;	

	public HttpRequest( Socket socket)throws Exception

	{

		// initializing socket
		this.socket=socket;
	}

	public void run()
	{

	try
	{
	
	DataInputStream in = new DataInputStream(socket.getInputStream());
	DataOutputStream out =new DataOutputStream(socket.getOutputStream());

	// reads the request
	String clientInput = in.readUTF();

	// retrieves the file
	System.out.println("ClientRequest:"+clientInput);

	// extracts the file name
	StringTokenizer st=new StringTokenizer(clientInput);
	String token=st.nextToken();
	String krishna=st.nextToken();
	
	if(token.equals("GET"))
	{
	if(krishna.startsWith("/")==true)
	krishna=krishna.substring(1);
	File f=new File(krishna);

	//checks the existance of the file      
	if(f.exists())
	{
		
		out.writeUTF("HTTP/1.1 200 ok");
		System.out.println("Http Response: HTTP/1.1 200 ok");
	        byte[] a=new byte[1024*15];
	        FileInputStream stream = new FileInputStream(krishna);
	        BufferedInputStream inputStream = new BufferedInputStream(stream);
	        DataInputStream ds = new DataInputStream(inputStream);
	        while (ds.available() != 0) 
	        {

	        // reading from the file and printing it on the console
	        ds.read(a);
	        out.write(a);
	        }

	        // colseing all the connectionss
	        stream.close();
	        inputStream.close();
	        ds.close();
	 }

	 else 
	 {
	
		out.writeUTF("HTTP/1.0 404 Not Found") ;
		System.out.println("Http Response: HTTP/1.0 404 Not Found");
         }
	 }

       	 }	 
 catch(Exception e)
	{
		System.out.println("exception while getting HTTP Response");
	}
        }
   
}



//http://code.bretonstyle.net/?page_id=198-referred.
