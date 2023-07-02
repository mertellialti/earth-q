package com.ellialti.earthQW.controller;
import com.ellialti.earthQW.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("city")
public class CityController {

    @Autowired
    CityService cityService;

//    @GetMapping("/get-city-list-in-radius")
//    public List<String> getCityListInRadius() {
//        try {
//            List<String> list = cityService.findCitiesWithinRadius();
//            return list;
//        } catch (Exception e) {
//            return null;
//        }
//    }
}