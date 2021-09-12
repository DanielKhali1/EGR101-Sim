import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {
	public static void main(String[] args) {
		try {
			
			ServerSocket ss = new ServerSocket(666);
			
			Socket sock = ss.accept();
			
			DataInputStream dis = new DataInputStream(sock.getInputStream());
			DataOutputStream dos = new DataOutputStream(sock.getOutputStream());
			
			String str = "";
			
			Thread t = new Thread(() -> {
				while(true) {
					try {
						Scanner s = new Scanner(System.in);
						String string = s.nextLine();
						dos.writeUTF(string);
						dos.flush();
					} catch(Exception e) {
						e.printStackTrace();
					}
				}
			});
			t.start();
			
			while(true) {
				System.out.println("Client = " + (String)(dis.readUTF()));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
