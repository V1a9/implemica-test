package ua.vgoryashko.findshortestpath;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

/**
 * Class that defines City model.
 *
 * @author Vlad Goryashko
 * @version 0.2
 * @since 03.06.18
 */
public class City {

    private String cityName;

    private int cityNumber;

    private int numberOfConnections;

    private boolean visited;

    private int shortestDistanceFromSource = Integer.MAX_VALUE;

    private City previousCity;

    private int[][] connectedCities;

    public City() {
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

    public int getShortestDistanceFromSource() {
        return shortestDistanceFromSource;
    }

    public void setShortestDistanceFromSource(int shortestDistanceFromSource) {
        this.shortestDistanceFromSource = shortestDistanceFromSource;
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
                add("shortestDistanceFromSource:", this.shortestDistanceFromSource).
                add("visited:", this.visited).
                add("previous:", this.previousCity).omitNullValues().
                add("connectedCities:", this.connectedCities).
                toString();
    }
}