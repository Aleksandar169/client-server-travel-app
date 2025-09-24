/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package server;

import database.DatabaseBroker;
import handler.ClientHandler;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Lenovo
 */
public class Server {

    private DatabaseBroker db;
    private static List<ClientHandler> users = new ArrayList<>();

    public Server() {
        db = new DatabaseBroker();
    }

    public void startServer() {
        try {
            ServerSocket serverSocket = new ServerSocket(8000);
            System.out.println("Server pokrenut, cekam klijente...");

            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("KLijent se povezao na server...");
                ClientHandler ch = new ClientHandler(socket);
                users.add(ch);
                ch.start();
            }
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
