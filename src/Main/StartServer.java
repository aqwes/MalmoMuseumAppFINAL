package Main;


import Server.Server;


class StartServer {
    public static void main(String[] args) {
        Server socketServer = new Server(getHerokuAssignedPort());
    }
    static int getHerokuAssignedPort() {
        ProcessBuilder processBuilder = new ProcessBuilder();
        if (processBuilder.environment().get("PORT") != null) {
            return Integer.parseInt(processBuilder.environment().get("PORT"));
        }
        return 4567; // return default port if heroku-port isn't set (i.e. on
        // localhost)
    }
}
