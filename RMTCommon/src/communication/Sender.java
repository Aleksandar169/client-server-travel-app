/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package communication;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.SocketException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Lenovo
 */
public class Sender {
    private final Socket socket;
 
    public Sender(Socket socket) {
        this.socket = socket;
    }

   public void send(Object object) throws Exception{
        try {
           // if(socket.isClosed() || socket==null || !socket.isConnected() || socket.isBound()){
                //throw  new Exception("Socket is null or socket is closed");
            //}
            ObjectOutputStream out =new ObjectOutputStream(socket.getOutputStream());
            out.writeObject(object);
            out.flush();
        } catch (SocketException ex) {
           // ex.printStackTrace();
            throw  new SocketException("Greska kod slanja objekta "+ex.getMessage());
        }
 
   }
    
}
