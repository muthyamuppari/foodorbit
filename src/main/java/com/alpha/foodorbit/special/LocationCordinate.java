package com.alpha.foodorbit.special;

public class LocationCordinate {

    private Double latitude;
    private Double longitute;

    public LocationCordinate(Double latitude, Double longitute) {
        this.latitude = latitude;
        this.longitute = longitute;
    }

    public LocationCordinate() {
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitute() {
        return longitute;
    }

    public void setLongitute(Double longitute) {
        this.longitute = longitute;
    }
}
