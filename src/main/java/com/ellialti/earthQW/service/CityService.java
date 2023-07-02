package com.ellialti.earthQW.service;

import com.ellialti.earthQW.domain.account.Account;
import com.ellialti.earthQW.domain.city.City;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;
import org.locationtech.jts.geom.Polygon;
import org.locationtech.jts.operation.distance.DistanceOp;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CityService {

    private final List<City> cities = new ArrayList<>();

    public void findAccountsWithinRadius(double latitude, double longitude, double radius) {
//        List<Account> sampleAccountData = CreateSampleAccountList(40);
//        Point center = new GeometryFactory().createPoint(new Coordinate(longitude, latitude));
//        Polygon searchArea = (Polygon) center.buffer(radius);
//        List<Account> result = new ArrayList<>();
//        for (Account user : sampleAccountData) {
//            if (searchArea.contains(user.getAccountCurrentLocation())) {
//                result.add(user);
//            } else if (DistanceOp.distance(user.getAccountCurrentLocation(), center) <= radius) {
//                result.add(user);
//            }
//        }
//        return result;
    }

    private void CreateSampleAccountList(int length) {
//        List<Account> userList = new ArrayList<>();
//        for (int i = 0; i < 75; i++) {
//            Account user = new Account();
//            userList.add(user);
//        }
//        return userList;
    }
}
