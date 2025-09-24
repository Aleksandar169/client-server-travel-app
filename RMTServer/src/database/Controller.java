/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package database;

import com.mysql.cj.protocol.x.XProtocolDecoder;
import domain.Trip;
import domain.User;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Lenovo
 */
public class Controller {

    private DatabaseBroker dbbr;
    private static Controller instance;

    private Controller() {
        dbbr = new DatabaseBroker();
    }

    public static Controller getInstance() {
        if (instance == null) {
            instance = new Controller();
        }
        return instance;
    }

    public User logIn(User user) throws SQLException {
        return dbbr.getUser(user);
    }

    public void addTrip(Trip trip) throws SQLException, Exception {

        if (dbbr.validateResident(trip.getName(), trip.getLastname(), trip.getJmbg(), trip.getPassportno())) {
            
            if(!dbbr.validateTrip(trip)){
                dbbr.addTrip(trip);
            }else{
                throw new Exception("This trip already exist!");
            }
            
        } else {
            throw new Exception("User don't exist in population!");
        }
    }

    public void addUser(User user) throws SQLException, Exception {

        if (dbbr.validateUser(user)) {
            if (dbbr.validateResident(user.getName(), user.getLastname(), user.getJmbg(), user.getPassportNo())) {
                dbbr.addUser(user);
            } else {
                throw new Exception("User don't exist in population!");
            }
        } else {
            throw new Exception("User already exist!");
        }

    }

    public List<Trip> showTrips(User use) throws SQLException {
        return dbbr.showTrips(use);
    }

    public List<Trip> showTripsNo(String s) throws SQLException {
        return dbbr.showTripsNo(s);
    }

    public void changeTrip(Trip tri) throws SQLException {
        dbbr.changeTrip(tri);
    }

}
