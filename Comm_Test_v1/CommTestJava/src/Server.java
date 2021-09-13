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
						String toSend = "Hello Danny: ";
				        byte[] toSendBytes = toSend.getBytes();
				        int toSendLen = toSendBytes.length;
				        byte[] toSendLenBytes = new byte[4];
				        toSendLenBytes[0] = (byte)(toSendLen & 0xff);
				        toSendLenBytes[1] = (byte)((toSendLen >> 8) & 0xff);
				        toSendLenBytes[2] = (byte)((toSendLen >> 16) & 0xff);
				        toSendLenBytes[3] = (byte)((toSendLen >> 24) & 0xff);
				        dos.write(toSendLenBytes);
				        dos.write(toSendBytes);
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
