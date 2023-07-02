package com.ellialti.earthQW.domain.earthquake;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class Earthquake {

    private double magnitude;
    private double approximateEffectingRadius;
    private double longitude, latitude, depth;
    private Date date;
    private String region;

    public Earthquake(double longitude, double latitude, double magnitude, Date date, String region) {
        this.longitude = longitude;
        this.latitude = latitude;
        this.magnitude = magnitude;
        this.date = date;
        this.region = region;
    }

    public Earthquake() {
    }

    public void calculateEffectingRadius(double magnitude) {
        //   double radius = Math.pow(10, 0.12 * (magnitude - 4)) * Math.pow(depth + 70, 0.25);
        //        setApproximateEffectingRadius(radius);
        if (magnitude <= 6.5) {
            setApproximateEffectingRadius (25000);
        } else if (magnitude <= 7) {
            setApproximateEffectingRadius (50000);
        } else if (magnitude <= 8) {
            setApproximateEffectingRadius (100000);
        } else {
            setApproximateEffectingRadius (150000);
        }
    }
}

