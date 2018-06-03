package ua.vgoryashko.findshortestpath;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

import java.util.ArrayList;
import java.util.List;

/**
 * Class that defines City model.
 *
 * @author Vlad Goryashko
 * @version 0.1
 * @since 01.06.18
 */
public class City {

    private String cityName;

    private int cityNumber;

    private int numberOfConnections;

    private boolean visited;

    private int distance = Integer.MAX_VALUE;

    private City previousCity;

    public City() {
    }

    int[][] connectedCities;

    public City(String cityName, int cityNumber, int numberOfConnections, int distance, City previousCity, int[][] connectedCities) {
        this.cityName = cityName;
        this.cityNumber = cityNumber;
        this.numberOfConnections = numberOfConnections;
        this.distance = distance;
        this.previousCity = previousCity;
        this.connectedCities = connectedCities;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public int getCityNumber() {
        return cityNumber;
    }

    public void setCityNumber(int cityNumber) {
        this.cityNumber = cityNumber;
    }

    public int getNumberOfConnections() {
        return numberOfConnections;
    }

    public void setNumberOfConnections(int numberOfConnections) {
        this.numberOfConnections = numberOfConnections;
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public City getPreviousCity() {
        return previousCity;
    }

    public void setPreviousCity(City previousCity) {
        this.previousCity = previousCity;
    }

    public int[][] getConnectedCities() {
        return connectedCities;
    }

    public void setConnectedCities(int[][] connectedCities) {
        this.connectedCities = connectedCities;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(this.cityName, this.cityNumber);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof City)) {
            return false;
        }
        City city = (City) obj;
        return Objects.equal(this.cityName, city.cityName) &&
                Objects.equal(this.cityNumber, city.cityNumber);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this).
                add("City:", this.cityName).
                add("number:", this.cityNumber).
                add("distance:", this.distance).
                add("visited:", this.visited).
                add("previous:", this.previousCity).omitNullValues().
                add("connectedCities:", this.connectedCities).
                toString();
    }
}