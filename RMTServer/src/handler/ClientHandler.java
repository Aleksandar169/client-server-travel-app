/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package handler;

import communication.Operation;
import communication.Receiver;
import communication.Request;
import communication.Response;
import communication.Sender;
import database.Controller;
import domain.Trip;
import domain.User;
import java.io.IOException;
import java.net.Socket;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Lenovo
 */
public class ClientHandler extends Thread {

    private Receiver receiver;
    private Sender sender;
    private Socket socket;

    public ClientHandler() {
    }

    public ClientHandler(Socket socket) {
        this.socket = socket;
        sender = new Sender(socket);
        receiver = new Receiver(socket);
    }

    @Override
    public void run() {

        while (true) {
            try {
                Request request = (Request) receiver.receive();
                Response response = new Response();

                switch (request.getOperation()) {
                    case LOGIN:
                        User user = (User) request.getArgument();
                        try {
                            user = Controller.getInstance().logIn(user);
                            response.setResult(user);
                        } catch (SQLException sqle) {
                            response.setException(sqle);
                        }
                        sender.send(response);
                        break;

                    case ADD_USER:
                        User us = (User) request.getArgument();

                        try {

                            Controller.getInstance().addUser(us);
                            response.setResult(true);

                        } catch (Exception sqle) {
                            response.setException(sqle);
                        }
                        sender.send(response);
                        break;

                    case ADD_TRIP:
                        Trip trip = (Trip) request.getArgument();
                        try {
                            Controller.getInstance().addTrip(trip);
                            response.setResult(true);
                        } catch (Exception sqle) {
                            response.setException(sqle);
                        }
                        sender.send(response);
                        break;

                    case CHANGE_TRIP:
                        Trip tri = (Trip) request.getArgument();
                        try {
                            Controller.getInstance().changeTrip(tri);
                            response.setResult(true);
                        } catch (SQLException sqle) {
                            response.setException(sqle);
                        }
                        sender.send(response);
                        break;
    

                    case SHOW_TRIPS:
                        User use = (User) request.getArgument();
                        try {
                            List<Trip> trips = Controller.getInstance().showTrips(use);
                            response.setResult(trips);

                        } catch (SQLException sqle) {
                            response.setException(sqle);
                        }

                        sender.send(response);
                        break;
                    case SHOW_TRIPS_NO:
                        String s=(String) request.getArgument();
                        try{
                            List<Trip> trips=Controller.getInstance().showTripsNo(s);
                            response.setResult(trips);
                        }catch(SQLException sqle){
                            response.setException(sqle);
                        }
                        sender.send(response);
                        break;
                    default:
                        throw new AssertionError();
                }

            } catch (Exception ex) {
                try {
                   // Logger.getLogger(ClientHandler.class.getName()).log(Level.SEVERE, null, ex);
                    socket.close();
                    break;
                } catch (IOException ex1) {
                    Logger.getLogger(ClientHandler.class.getName()).log(Level.SEVERE, null, ex1);
                }
            }
        }
    }
}
