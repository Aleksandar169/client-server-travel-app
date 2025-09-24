/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package database;

import domain.Transport;
import domain.Trip;
import domain.User;
import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Lenovo
 */
public class DatabaseBroker {

    private Connection connection;

    public DatabaseBroker() {
        getConnection();
    }

    private void getConnection() {
        if (connection == null) {
            String url = "jdbc:mysql://localhost:3306/rmt?useSSL=false&serverTimezone=Europe/Belgrade";
            String user = "root";
            String pass = "root";

            try {
                connection = DriverManager.getConnection(url, user, pass);
                System.out.println("nadjena konekcija sa bazom");

            } catch (SQLException ex) {
                Logger.getLogger(DatabaseBroker.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }

    //login
    public User getUser(User user) throws SQLException {
        getConnection();
        try {
            String query = "SELECT  jmbg, name, lastname, email, username, password, passportno FROM user WHERE username=? AND password=?";

            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, user.getUsername());
            statement.setString(2, user.getPassword());
            //izvsi upit
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                user.setJmbg(rs.getString("jmbg"));
                user.setName(rs.getString("name"));
                user.setLastname(rs.getString("lastname"));
                user.setEmail(rs.getString("email"));
                user.setPassportNo(rs.getString("passportno"));
            } else {
                throw new SQLException("User don't exist!!!");
            }

            rs.close();
            statement.close();
            System.out.println("Uspesno ucitavanje objekta User iz baze!");
            return user;
        } catch (SQLException ex) {
            System.out.println("Objekat User nije uspesno ucitan iz baze!");
            //ex.printStackTrace();
            throw ex;
        }
    }

    //registracija korisnika tj dodavanje u bazu 
    public void addUser(User user) throws SQLException {
        getConnection();

        String query = "INSERT INTO user (jmbg, name, lastname, email, username, password,  passportno) VALUES (?,?,?,?,?,?,?)";

        PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        statement.setString(1, user.getJmbg());
        statement.setString(2, user.getName());
        statement.setString(3, user.getLastname());
        statement.setString(4, user.getEmail());
        statement.setString(5, user.getUsername());
        statement.setString(6, user.getPassword());
        statement.setString(7, user.getPassportNo());

        statement.execute();
//                    
//            ResultSet rsID = statement.getGeneratedKeys();
//            //NALAZI SE U PRVOJ KOLONI KOJA JE TIPA LONG
//            if (rsID.next()) {
//                //user.setUserId(rsID.getInt(1));
//            }
//            rsID.close();
        statement.close();

        // System.out.println("Vrednost generisanog primarnog kljuca je: " + user.getUserId());
    }

    //dodavanje putovanja
    public void addTrip(Trip trip) throws SQLException {
        //getConnection();

        String query = "INSERT INTO trip (name, lastname, jmbg, passport, destination, entrydate, exitdate, transport) VALUES (?,?,?,?,?,?,?,?)";
        PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

        statement.setString(1, trip.getName());
        statement.setString(2, trip.getLastname());
        statement.setString(3, trip.getJmbg());
        statement.setString(4, trip.getPassportno());
        statement.setString(5, trip.getDestination());
        statement.setDate(6, Date.valueOf(trip.getEntryDate()));
        statement.setDate(7, Date.valueOf(trip.getExitDate()));
        statement.setString(8, trip.getTransport().toString());

        statement.execute();

//            ResultSet rsID = statement.getGeneratedKeys();
//            //NALAZI SE U PRVOJ KOLONI KOJA JE TIPA LONG
//            if (rsID.next()) {
//                //trip.setTripId(rsID.getInt(1));
//            }
//            rsID.close();
        statement.close();

        // System.out.println("Vrednost generisanog primarnog kljuca je: " + trip.getTripId());
    }

    boolean validateUser(User user) throws SQLException {

        String querry = "SELECT * FROM user where jmbg= '" + user.getJmbg() + "' OR username= '" + user.getUsername() + "' OR passportno= '" + user.getPassportNo()+"'";

        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery(querry);

         return(!rs.next());

    }

    boolean validateResident(String user, String lastname, String jmbg, String passportNo) throws SQLException {
        String querry = "SELECT * FROM population where jmbg= '" + jmbg +"' AND passport= '" + passportNo+"' AND name= '"+user+"' AND lastname=  '"+lastname+"'";

        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery(querry);

        return(rs.next());
        
    }

    List<Trip> showTrips(User use) throws SQLException {
        List<Trip> trips=new ArrayList<>();
        String querry = "SELECT * FROM trip WHERE jmbg= '"+ use.getJmbg()+ " ' OR passport= '"+use.getPassportNo() +"'";
        Statement statement=connection.createStatement();
        ResultSet rs=statement.executeQuery(querry);
        
        while(rs.next()){
            Trip tr=new Trip();
            tr.setName(rs.getString("name"));
            tr.setLastname(rs.getString("lastname"));
            tr.setJmbg(rs.getString("jmbg"));
            tr.setPassportno(rs.getString("passport"));
            tr.setDestination(rs.getString("destination"));
            tr.setEntryDate(rs.getDate("entrydate").toLocalDate());
            tr.setExitDate(rs.getDate("exitdate").toLocalDate());
            tr.setTripId(rs.getInt("idtrip"));
             tr.setTransport(Transport.valueOf(rs.getString("transport")));
             tr.setStatus();
            trips.add(tr);
            
            
        }
        return trips;
        
    }

    List<Trip> showTripsNo(String s) throws SQLException {
        List<Trip> trips=new ArrayList<>();
        String querry = "SELECT * FROM trip WHERE jmbg= '"+ s + "' OR passport= '"+ s +"'";
        Statement statement=connection.createStatement();
        ResultSet rs=statement.executeQuery(querry);
        
        while(rs.next()){
            Trip tr=new Trip();
            tr.setName(rs.getString("name"));
            tr.setLastname(rs.getString("lastname"));
            tr.setJmbg(rs.getString("jmbg"));
            tr.setPassportno(rs.getString("passport"));
            tr.setDestination(rs.getString("destination"));
            tr.setEntryDate(rs.getDate("entrydate").toLocalDate());
            tr.setExitDate(rs.getDate("exitdate").toLocalDate());
            tr.setTripId(rs.getInt("idtrip"));
            tr.setTransport(Transport.valueOf(rs.getString("transport")));
             tr.setStatus();
            trips.add(tr);
            
            
        }
        return trips;
    }

    void changeTrip(Trip tri) throws SQLException {
        
        String query="UPDATE trip SET  destination=?, entrydate=?, exitdate=?, transport=? WHERE idtrip=?";
        
        PreparedStatement statement=connection.prepareStatement(query);
        
        statement.setString(1, tri.getDestination());
        statement.setDate(2, Date.valueOf(tri.getEntryDate()));
        statement.setDate(3, Date.valueOf(tri.getExitDate()));
        statement.setString(4, tri.getTransport().toString());
        statement.setInt(5, tri.getTripId());
        System.out.println();
        statement.execute();
 
    }

    boolean validateTrip(Trip trip) throws SQLException {
        String query="SELECT * FROM trip WHERE jmbg= ? AND destination=? AND entrydate=? AND exitdate=?";
        
        PreparedStatement statement=connection.prepareStatement(query);
        statement.setString(1, trip.getJmbg());
        statement.setString(2, trip.getDestination());
        statement.setDate(3, Date.valueOf(trip.getEntryDate()));
        statement.setDate(4, Date.valueOf(trip.getExitDate()));
               
        
        ResultSet rs= statement.executeQuery();
        return(rs.next());
        
    }

}
