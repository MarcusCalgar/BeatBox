package beatboxServer;

import java.io.*;
import java.net.Socket;
import java.util.*;

public class ClientHandler implements Runnable {

    private ObjectInputStream in;
    private Socket clientSocket;
    private ArrayList<ObjectOutputStream> clientOutputStreams;
    
    public ClientHandler(){}

    public ClientHandler(Socket socket, ArrayList<ObjectOutputStream> clientOutputStreams) {
        try {
            this.clientOutputStreams = clientOutputStreams;
            clientSocket = socket;
            in = new ObjectInputStream(clientSocket.getInputStream());
        } catch (IOException ex) {
        }
    } // close constructor

    @Override
    public void run() {
        Object o2;
        Object o1;
        try {
            while ((o1 = in.readObject()) != null) {
                o2 = in.readObject();
                System.out.println("read two objects");
                tellEveryone(o1, o2);
            } // close while
        } catch (ClassNotFoundException | IOException ex) {
        }
    } // close run

    public void tellEveryone(Object one, Object two) {
        Iterator it = clientOutputStreams.iterator();
        while (it.hasNext()) {
            try {
                ObjectOutputStream out = (ObjectOutputStream) it.next();
                out.writeObject(one);
                out.writeObject(two);
            } catch (IOException ex) {
            }
        } // end while
    } // close tellEveryone
} // close inner class
