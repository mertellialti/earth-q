package com.ellialti.earthQW.domain.base;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Coordinate {
    double latitude;
    double longitude;

    public Coordinate(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Coordinate() {
    }
}
