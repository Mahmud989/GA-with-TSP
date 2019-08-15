/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

/**
 *
 * @author Mahmud
 */
public class City {

    private String name;
    private double connection[];
    private int id;

    public City(String name, double[] connection, int id) {
        this.name = name;
        this.connection = connection;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public double[] getConnection() {
        return connection;
    }

    public int getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setConnection(double[] connection) {
        this.setConnection(connection);
    }

    public void setId(int id) {
        this.id = id;
    }
    
}


