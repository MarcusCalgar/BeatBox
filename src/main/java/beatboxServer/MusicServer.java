package beatboxServer;

import java.io.*;
import java.net.*;
import java.util.*;

public class MusicServer {

    public static void main(String[] args) {
        ArrayList<ObjectOutputStream> clientOutputStreams = new ArrayList<>();  //Holds the outputstreams of the clients.
        try {
            ServerSocket serverSock = new ServerSocket(4242);   //Listens for clients on port 4242
            while (true) {
                Socket clientSocket = serverSock.accept();
                ObjectOutputStream out = new ObjectOutputStream(clientSocket.getOutputStream());
                clientOutputStreams.add(out);

                Thread t = new Thread(new ClientHandler(clientSocket, clientOutputStreams)); //Starts a new thread that deals with each specific client.
                t.start();
            }
        } catch (IOException ex) {
        }
    }
}
