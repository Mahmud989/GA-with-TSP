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
public class Cities {

    City[] cities;
    private int size = 0;
    private int step = 10;

    public Cities() {
    }

    public Cities(int length) {
        cities = new City[length];
    }

    public Cities(City[] cities) {
        this.cities = cities;
        this.size = cities.length;
    }

    public void append(City city) {
        if (size - 1 >= this.cities.length) {
            City[] citys = new City[this.cities.length + step];
            System.arraycopy(cities, 0, citys, 0, cities.length);
            cities = citys;
        }
        cities[size] = city;
        size++;
    }

    public void setCity(int pos, City city) {
        cities[pos] = city;
    }

    public City getCity(int pos) throws ArrayIndexOutOfBoundsException {
        if (pos >= size) {
            throw new ArrayIndexOutOfBoundsException();
        }
        return cities[pos];
    }

    public double compute(int[] directions) {
        Double value = 0d;
        for (int i = 1; i < directions.length; i++) {
            value += cities[directions[i]].getConnection()[directions[i - 1]];
        }
        value += cities[directions[directions.length - 1]].getConnection()[directions[0]];

        return value.isInfinite()?value.MAX_VALUE:value.doubleValue();
    }

}


