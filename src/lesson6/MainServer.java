package lesson6;

import lesson6.service.Client;
import lesson6.service.Server;

public class MainServer {
    public static void main(String[] args) {
        new Server();
    }
}

class MainClient {
    public static void main(String[] args) {
        new Client();
    }
}
