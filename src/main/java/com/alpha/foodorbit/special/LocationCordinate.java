package com.alpha.foodorbit.special;

public class LocationCordinate {

    private double latitude;
    private double longitute;

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitute() {
        return longitute;
    }

    public void setLongitute(double longitute) {
        this.longitute = longitute;
    }

    public LocationCordinate(double latitude, double longitute) {
        this.latitude = latitude;
        this.longitute = longitute;
    }

    public LocationCordinate() {
    }
}
