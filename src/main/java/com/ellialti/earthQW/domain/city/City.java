package com.ellialti.earthQW.domain.city;
import lombok.Getter;
import lombok.Setter;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;

import java.util.List;

@Getter
@Setter
public class City {

    private String name;
    private List<City> cityListInRadius;
    private double latitude;
    private double longitude;
    private Point cityPositionPoint;

    public City(String name, double latitude, double longitude) {
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        cityPositionPoint = getLocation();
    }

    public City() {
    }

    public Point getLocation() {
        return new GeometryFactory().createPoint(new Coordinate(longitude, latitude));
    }
}
