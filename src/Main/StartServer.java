package Main;


import Server.Server;

/**
 * Created by Dennis on 2016-03-15.
 */
class StartServer {
    public static void main(String[] args) {
        Server socketServer = new Server(9990);
    }
    }
