package com.egr101sim.core;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class CommunicationManager {
	ServerSocket ss;
	Socket sock;
	DataInputStream dis;
	DataOutputStream dos;
	
	public CommunicationManager() {
		new Thread(() -> initializeServerCharacteristics()).start();
	}
	
	public void initializeServerCharacteristics() {
		
		try {
			ss = new ServerSocket(666);
			sock = ss.accept();
			
			dis = new DataInputStream(sock.getInputStream());
			dos = new DataOutputStream(sock.getOutputStream());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void sendMessage(String s){
		try {
	        byte[] toSendBytes = s.getBytes();
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

}
