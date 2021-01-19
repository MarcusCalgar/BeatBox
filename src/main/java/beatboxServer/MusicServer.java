package beatboxServer;

import java.io.*;
import java.net.*;
import java.util.*;

public class MusicServer {

    public static void main(String[] args) {
        ArrayList<ObjectOutputStream> clientOutputStreams = new ArrayList<>();
        try {
            ServerSocket serverSock = new ServerSocket(4242);
            while (true) {
                Socket clientSocket = serverSock.accept();
                ObjectOutputStream out = new ObjectOutputStream(clientSocket.getOutputStream());
                clientOutputStreams.add(out);

                Thread t = new Thread(new ClientHandler(clientSocket, clientOutputStreams));
                t.start();
                System.out.println("got a connection");
            }
        } catch (IOException ex) {
        }
    }
}
