package lesson6.service;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    private Server server;
    private Socket socket;
    private DataInputStream in;
    private DataOutputStream out;
    private Thread tR;
    private Thread tS;
    private final String IP_ADDRESS = "localhost";
    private final int PORT = 8189;


    public Client() {

        try {
            socket = new Socket(IP_ADDRESS, PORT);
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());

            tR = runReceivingMessages();
            tS = runSendingMessages();

            tR.start();
            tS.start();

        } catch (IOException e) {
            try {
                socket.close();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }

    }

    private Thread runReceivingMessages() {
        return new Thread(() -> {
            try {

                System.out.println("Клиент принимает сообщения");

                while (!Thread.currentThread().isInterrupted()) {
                    String str = in.readUTF();

                    if (str.replace("\n", "").replace("\r", "").equals("/end")) {
                        stopServer();
                        break;
                    }

                    System.out.println("Сервер написал: " + str);
                }

            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private Thread runSendingMessages() {
        return new Thread(() -> {
            try {

                Scanner scanner = new Scanner(System.in);

                while (!Thread.currentThread().isInterrupted()) {
                    String str = scanner.nextLine();

                    if (str.replace("\n", "").replace("\r", "").equals("/end")) {
                        if(!socket.isClosed()) {
                            out.writeUTF("/end");
                        }
                        stopServer();
                        break;
                    }

                    if (!str.isEmpty()) {
                        out.writeUTF(str);
                    }
                }

            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void stopServer(){
        tS.interrupt();
        tR.interrupt();
    }
}
