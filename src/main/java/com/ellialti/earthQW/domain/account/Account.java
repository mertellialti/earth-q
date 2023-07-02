package com.ellialti.earthQW.domain.account;

import com.ellialti.earthQW.domain.base.Base;
import com.ellialti.earthQW.domain.base.Coordinate;
import lombok.Getter;
import lombok.Setter;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Account extends Base {

    private String name, surname;
    private String password;
    private Coordinate coordinate;
    private List<String> relativesIds;
    private List<Account> relatives;
    private List<String> needIds;

    public Account() {
        this.relativesIds = new ArrayList<>();
        this.coordinate = new Coordinate();
    }

    public Account(String name, String surname) {
        super();
        this.name = name;
        this.surname = surname;
        this.relativesIds = new ArrayList<>();
        this.coordinate = new Coordinate();
    }

    public void SetLongitudeAndLatitude(double longitude, double latitude){
        this.coordinate = new Coordinate();
        this.coordinate.setLongitude(longitude);
        this.coordinate.setLatitude(latitude);
    }
}
