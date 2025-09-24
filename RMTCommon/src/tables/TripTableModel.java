/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tables;

import domain.Trip;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Lenovo
 */
public class TripTableModel extends AbstractTableModel {

    List<Trip> trips = new ArrayList<>();
    String[] columns = {"Destination", "EntryDate", "ExitDate", "Status"};

    public TripTableModel(List<Trip> trips) {
        this.trips = trips;
    }

    public List<Trip> getTrips() {
        return trips;
    }

    public void setTrips(List<Trip> trips) {
        this.trips = trips;
    }

    @Override
    public int getRowCount() {
        if (trips == null) {
            return 0;
        }
        return trips.size();
    }

    @Override
    public int getColumnCount() {
        return columns.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Trip trip = trips.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return trip.getDestination();
            case 1: 
                return trip.getEntryDate();
            case 2:
                return trip.getExitDate();
            case 3:
                return trip.getStatus();
            default:
                    throw new AssertionError();
        }
    }

    @Override
    public String getColumnName(int column) {
        return columns[column];
    }
    
    public Trip getTrip(int row){
        return trips.get(row);
    }
    

}
