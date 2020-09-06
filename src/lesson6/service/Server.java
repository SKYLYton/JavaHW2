package lesson6.service;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.Scanner;
import java.util.Vector;

public class Server {

    private int PORT = 8189;
    ServerSocket server = null;
    Socket socket = null;
    private DataInputStream in;
    private DataOutputStream out;
    private Thread tR;
    private Thread tS;

    public Server() {

        clientConnection();

    }

    private void clientConnection() {
        try {
            server = new ServerSocket(PORT);
            System.out.println("Сервер запущен");

            socket = server.accept();
            System.out.println("Клиент подключился");

            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());

            //client = new Client();

            tR = runReceivingMessages();
            tS = runSendingMessages();

            tR.start();
            tS.start();


        } catch (IOException e) {
            e.printStackTrace();
            try {
                server.close();
            } catch (IOException ee) {
                e.printStackTrace();
            }
        }
    }

    private Thread runReceivingMessages() {
        return new Thread(() ->{
            try {

                System.out.println("Сервер принимает сообщения");

                while (!Thread.currentThread().isInterrupted()) {
                    String str = in.readUTF();

                    if (str.replace("\n", "").replace("\r", "").equals("/end")) {
                        stopServer();
                        break;
                    }

                    System.out.println("Клиент написал: " + str);
                }


            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    server.close();
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
                        }                        stopServer();
                        break;
                    }

                    if(!str.isEmpty()){
                        out.writeUTF(str);
                    }
                }

            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    server.close();
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
