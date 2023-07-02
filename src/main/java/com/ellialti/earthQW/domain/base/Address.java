package com.ellialti.earthQW.domain.base;
import com.ellialti.earthQW.domain.base.Base;
import com.ellialti.earthQW.domain.base.Coordinate;

public class Address extends Base {
    private Coordinate coordinate;
    private String address;
    private String streetName;
    private String unitNumber;
    private String houseNumber;
    private String districtName;
    private String provinceName;
    private String cityNameOrTownName;

    public Address(Coordinate coordinate, String address, String streetName, String unitNumber, String houseNumber, String districtName, String provinceName, String cityNameOrTownName) {
        this.coordinate = coordinate;
        this.address = address;
        this.streetName = streetName;
        this.unitNumber = unitNumber;
        this.houseNumber = houseNumber;
        this.districtName = districtName;
        this.provinceName = provinceName;
        this.cityNameOrTownName = cityNameOrTownName;
    }

    public Address() {
    }
}
