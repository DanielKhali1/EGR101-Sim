import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Client {
	
	
	public static void main(String[] args) {
		try {
			Socket sock = new Socket("localhost", 666);
			
			DataInputStream dis = new DataInputStream(sock.getInputStream());
			DataOutputStream dos = new DataOutputStream(sock.getOutputStream());
			
			
			String str = "";
			
			Thread t = new Thread(() -> {
				while(true) {
					Scanner s = new Scanner(System.in);
					try {
						String string = s.nextLine();
						dos.writeUTF(string);
						dos.flush();
					} catch(Exception e) {
						e.printStackTrace();
					}
				}
			});
			t.start();
			
			while(true) 
				System.out.println("Server = " + (String)(dis.readUTF()));
		
		} catch( Exception e) {
			e.printStackTrace();
		}
	}
	

}
