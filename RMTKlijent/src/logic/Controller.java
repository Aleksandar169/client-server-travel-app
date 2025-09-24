/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logic;

import communication.Operation;
import communication.Receiver;
import communication.Request;
import communication.Response;
import communication.Sender;
import domain.Trip;
import domain.User;
import forms.UserForm;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.Socket;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Lenovo
 */
public class Controller {
    private static Controller instance; 
    private   Socket socket;
    private   Sender sender;
    private   Receiver receiver;

    private Controller() throws IOException {
        socket=new Socket("localhost", 8000);
        sender=new Sender(socket);
        receiver=new Receiver(socket);
    }
    
    public static Controller getInstance() throws IOException{
        if(instance==null) instance=new Controller();
        return instance;
    }

    public User logIn(User user) throws Exception {
        Request request = new Request(Operation.LOGIN, user);
        try{
        sender.send(request);
        }catch(Exception e){
            throw e;
        }
        Response response =  (Response) receiver.receive();
        if (response.getException() == null){
            return (User) response.getResult();
        }else{
            throw response.getException();
        }
    }

    public void addTrip(Trip trip) throws Exception {
        Request request = new Request(Operation.ADD_TRIP, trip);
        try{
        sender.send(request);
        }catch (Exception e){
            throw e;
        }
        Response response =  (Response) receiver.receive();
        if (response.getException() != null){
               throw response.getException();
        }
        
        
        generateFile(trip);
    }

    public void addUser(User user) throws Exception {
         Request request = new Request(Operation.ADD_USER, user);
         try{
        sender.send(request);
        }catch (Exception e){
            throw e;
        }
 
        Response response =  (Response) receiver.receive();
        if (response.getException() != null){
               throw response.getException();
        }
    }

    public List<Trip> getTrips(User user) throws Exception {
        
        Request request = new Request(Operation.SHOW_TRIPS, user);
        try{
        sender.send(request);
        }catch (Exception e){
            throw e;
        }
        Response response =  (Response) receiver.receive();
        
        if (response.getException() == null){
            return   (List<Trip>) response.getResult();
        }else{
            throw response.getException();
        }
    }

    public List<Trip> getTripsNo(String s) throws Exception {
         Request request = new Request(Operation.SHOW_TRIPS_NO, s);
        try{
        sender.send(request);
        }catch (Exception e){
            throw e;
        }
        Response response =  (Response) receiver.receive();
        
        if (response.getException() == null){
            return   (List<Trip>) response.getResult();
        }else{
            throw response.getException();
        }
    }

    private void generateFile(Trip trip) {
        
        String fileName="Trip of " +trip.getName()+ " "+trip.getLastname()+".txt";
        File file=new File(fileName);
        boolean payment=needToPay(trip.getJmbg());
        try{
            BufferedWriter writer=new BufferedWriter(new FileWriter(file));
            writer.write("Travel information\n");
            writer.write("----------------------------------\n");
            writer.write("Name: "+trip.getName()+"\n");
            writer.write("JMBG: " + trip.getJmbg() + "\n");
            writer.write("Passport: " + trip.getPassportno() + "\n");
            writer.write("Destination: " + trip.getDestination() + "\n");
            writer.write("Entry Date: " + trip.getEntryDate() + "\n");
            writer.write("Exit Date: " + trip.getExitDate() + "\n");
            writer.write("Days of Stay: " + ChronoUnit.DAYS.between(trip.getEntryDate(), trip.getExitDate()) + "\n");
            writer.write("Transport: " + trip.getTransport() + "\n");
            writer.write("Free: "+(payment? "Yes" : "No")+"\n");
            writer.write("*********************************");
            writer.close();
            
        }catch(Exception ex){
            Logger.getLogger(UserForm.class.getName()).log(Level.SEVERE, null, ex);
            
        }
        
        
    }

    private boolean needToPay(String jmbg) {
       // try{
        int birth=Integer.parseInt(jmbg.substring(4, 7));
        int currentYear=LocalDate.now().getYear();
        
        int age=currentYear-((birth<=999? 2000: 1900)+birth);
        
        return age>=18 && age<=70;

    
        
//        }catch(Exception e){
//            return false;
//        }
        
        
    }

    public void changeTrip(Trip trip) throws Exception {
        Request request = new Request(Operation.CHANGE_TRIP, trip);
        try{
        sender.send(request);
        }catch (Exception e){
            throw e;
        }
 
        Response response =  (Response) receiver.receive();
        if (response.getException() != null){
               throw response.getException();
        }
        
    }
    
    
    
    
    
}
