package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

	public static void main (String[] args) {
		try {
			ServerSocket serverSocket = new ServerSocket(5555);
			Socket client;
			GameroomManager gameroomManager = new GameroomManager();
			System.out.println("HI JEREMY!");
			while ((client = serverSocket.accept()) != null)
			{
				CommManager user = new CommManager(client, gameroomManager);
				user.start();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
