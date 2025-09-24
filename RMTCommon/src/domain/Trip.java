/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

/**
 *
 * @author Lenovo
 */
public class Trip implements Serializable{
  
    private String name;
    private String lastname;
    private String jmbg;
    private String passportno;
    
    private String destination;
    private LocalDate entryDate;
    private LocalDate exitDate;
    private Transport transport;
    
    private int tripId;
    private String status;

    public int getTripId() {
        return tripId;
    }

    public void setTripId(int tripId) {
        this.tripId = tripId;
    }

    public Trip(String name, String lastname, String jmbg, String passportno, String destination, LocalDate entryDate, LocalDate exitDate, Transport transport, int tripId) {
        this.name = name;
        this.lastname = lastname;
        this.jmbg = jmbg;
        this.passportno = passportno;
        this.destination = destination;
        this.entryDate = entryDate;
        this.exitDate = exitDate;
        this.transport = transport;
        this.tripId = tripId;
        setStatus();
    }
    
    

    public Trip() {
    }

    public Trip(String name, String lastname, String jmbg, String passportno, String destination, LocalDate entryDate, LocalDate exitDate, Transport transport) {
        this.name = name;
        this.lastname = lastname;
        this.jmbg = jmbg;
        this.passportno = passportno;
        this.destination = destination;
        this.entryDate = entryDate;
        this.exitDate = exitDate;
        this.transport = transport;
        setStatus();
    }
    public Trip(String destination, LocalDate entryDate, LocalDate exitDate){
        this.destination = destination;
        this.entryDate = entryDate;
        this.exitDate = exitDate;
        setStatus();
        
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getJmbg() {
        return jmbg;
    }

    public void setJmbg(String jmbg) {
        this.jmbg = jmbg;
    }

    public String getPassportno() {
        return passportno;
    }

    public void setPassportno(String passportno) {
        this.passportno = passportno;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public LocalDate getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(LocalDate entryDate) {
        this.entryDate = entryDate;
    }

    public LocalDate getExitDate() {
        return exitDate;
    }

    public void setExitDate(LocalDate exitDate) {
        this.exitDate = exitDate;
    }

    public Transport getTransport() {
        return transport;
    }

    public void setTransport(Transport transport) {
        this.transport = transport;
    }

    @Override
    public String toString() {
        return "Trip{" + "name=" + name + ", lastname=" + lastname + ", jmbg=" + jmbg + ", passportno=" + passportno + ", destination=" + destination + ", entryDate=" + entryDate + ", exitDate=" + exitDate + ", transport=" + transport + ", tripId=" + tripId + '}';
    }

     
    
    
    public boolean isEditable(){
        return (ChronoUnit.DAYS.between(LocalDate.now(), entryDate)>2);
       
    }

    public void setStatus() {
        if(entryDate.isBefore(LocalDate.now())){
            status="Finished";
        }else if((ChronoUnit.DAYS.between(LocalDate.now(), entryDate))>=2){
            status="Processing";
        }else{
            status="Locked";
        }
        
    }

    public String getStatus() {
        return status;
    }
 
    
    
    
}
