package Server;


import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


public class Server implements Runnable {
    private ServerSocket server = null;
    private Thread thread = null;
    private ServerThread client = null;


    public Server(int port) {
        try {
            System.out.println("Starting the socket server at port:" + port);
            server = new ServerSocket(port);
            System.out.println("Server started: " + server);
            start();
        } catch (IOException e) {
            e.printStackTrace();


        }
    }
    @Override
    public void run() {
        while (thread != null) {

            try {
                System.out.println("Waiting for a client ...");
                addThread(server.accept());
            } catch (IOException e) {
                System.out.println("Acceptance Error: " + e);
            }
        }
    }

    private void addThread(Socket socket) {
        System.out.println("Client accepted: " + socket);
        client = new ServerThread(socket);
        client.start();
    }

    public void start() {
        if (thread == null) {
            thread = new Thread(this);
            thread.start();
        }
    }
    public void stop() {
        if (thread != null) {
            thread.interrupt();
            thread = null;
        }
    }
}