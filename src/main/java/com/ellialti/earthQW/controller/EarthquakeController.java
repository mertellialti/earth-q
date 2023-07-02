package com.ellialti.earthQW.controller;

import com.ellialti.earthQW.domain.Response;
import com.ellialti.earthQW.domain.earthquake.Earthquake;
import com.ellialti.earthQW.domain.earthquake.request.RetrieveEarthquakeListByMagnitude;
import com.ellialti.earthQW.domain.earthquake.request.RetrieveEarthquakeListFilteredByDate;
import com.ellialti.earthQW.service.EarthquakeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("earthquake")
public class EarthquakeController {

    @Autowired
    EarthquakeService earthquakeService;

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/get-earthquakes")
    public Response ListEarthquakes() {
        try {
            Date startTime = new Date();
            System.out.println("Get Earthquakes: Start time: " + startTime);
            List<Earthquake> earthquakeList = earthquakeService.list();
            Date endTime = new Date();
            System.out.println("Action end time " + endTime);
            Response response = new Response(true, earthquakeList);
            return response;
        } catch (Exception e) {
            Response response = new Response(true,
                    null);
            return response;
        }
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/get-earthquakes-by-date")
    public Response ListEarthquakesByDate(@RequestBody RetrieveEarthquakeListFilteredByDate retrieveEarthquakeListFilteredByDate) {
        try {
            Response response = new Response(true, earthquakeService.listByDate(retrieveEarthquakeListFilteredByDate.getDate()));
            return response;
        } catch (Exception e) {
            Response response = new Response(true,
                    null);
            return response;
        }
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/get-earthquakes-by-magnitude")
    public Response ListEarthquakesByMagnitude(@RequestBody RetrieveEarthquakeListByMagnitude retrieveEarthquakeListByMagnitude) {
        try {
            Response response = new Response(true,
                    earthquakeService.listByMagnitudes(retrieveEarthquakeListByMagnitude.getMinVal(),
                            retrieveEarthquakeListByMagnitude.getMaxVal()));
            return response;
        } catch (Exception e) {
            Response response = new Response(true,
                    null);
            return response;
        }
    }
}
