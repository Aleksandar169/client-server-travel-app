/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package main;

import forms.MainForm;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import logic.Controller;

/**
 *
 * @author Lenovo
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        MainForm mf=new MainForm();
        try {
            // TODO code application logic here
            Controller.getInstance();
               mf=new MainForm();
             mf.setVisible(true);
        } catch (IOException ex) {
            
            //Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
           JOptionPane.showMessageDialog(mf, "Server don't work!", "Error", JOptionPane.ERROR_MESSAGE);
            
           return;
        }
       
    }
    
}
