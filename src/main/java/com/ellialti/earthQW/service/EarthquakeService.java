package com.ellialti.earthQW.service;

import com.ellialti.earthQW.domain.Response;
import com.ellialti.earthQW.domain.base.Coordinate;
import com.ellialti.earthQW.domain.city.City;
import com.ellialti.earthQW.domain.earthquake.Earthquake;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Service;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class EarthquakeService {

    @Autowired
    CityService cityService;
    @Autowired
    AccountService accountService;

    private Date todayDate = new Date();
    private Date monthAgoDate;
    String textConversionOfTodayDate, textConversionOfMonthAgoDate;
    private SimpleDateFormat sdf;
    private String apiUrl; // "https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&starttime=2023-02-05&endtime=2023-02-09";

    //    private static final String URL = "http://www.koeri.boun.edu.tr/sismo/2/son-depremler/otomatik-cozumler/";
    private List<Earthquake> earthquakeList = new ArrayList<>();
    private List<City> cityList = new ArrayList<>();
    private Coordinate boundPointNE = new Coordinate(45, 50); //
    private Coordinate boundPointSW = new Coordinate(35, 20); //

    public List<Earthquake> list() throws JSONException, IOException {
        try {
            Calendar cal = Calendar.getInstance();
            cal.setTime(todayDate);
            cal.add(Calendar.MONTH, -1);
            monthAgoDate = cal.getTime();
            sdf = new SimpleDateFormat("yyyy-MM-dd");

            textConversionOfTodayDate = sdf.format(todayDate);
            textConversionOfMonthAgoDate = sdf.format(monthAgoDate);
            // normal link
              apiUrl = "https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&starttime=" + textConversionOfMonthAgoDate + "&endtime=" + textConversionOfTodayDate;
            // static link for analyze of Kahramanmaraş earthquake
            //apiUrl = "https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&starttime=" + "2023-01-25" + "&endtime=" + "2023-02-07";
            // static link for analyze of possible Istanbul earthquake
            // apiUrl = "https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&starttime=" + "2023-03-20" + "&endtime=" + "2023-04-09";
            //"https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&starttime=2023-02-05&endtime=2023-02-09";
            earthquakeList = new ArrayList<>();
            URL url = new URL(apiUrl);
            URLConnection connection = url.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder response = new StringBuilder();
            String inputLine;

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            JSONObject json = new JSONObject(response.toString());
            JSONArray earthquakes = json.getJSONArray("features");
            Earthquake earthquakeObject;

            for (int i = 0; i < earthquakes.length(); i++) {

                JSONObject earthquake = earthquakes.getJSONObject(i);
                JSONObject earthquakeProperties = earthquake.getJSONObject("properties");
                JSONObject earthquakeGeometry = earthquake.getJSONObject("geometry");

                String earthquakePlace = earthquakeProperties.getString("place");

//                if (!earthquakePlace.isEmpty() && ((earthquakePlace.contains("Turkey") || earthquakePlace.contains("Syria") ||
//                        earthquakePlace.contains("Greece") || earthquakePlace.contains("Armenia"))) &&
//                        earthquakeProperties.getDouble("mag") >= 1)
                float cLat = (float) earthquakeGeometry.getJSONArray("coordinates").getDouble(1);
                float cLong = (float) earthquakeGeometry.getJSONArray("coordinates").getDouble(0);

                if (
//                        earthquakeProperties.getDouble("mag") >= 1 &&
                        (
                                (boundPointSW.getLatitude() <= cLat && boundPointNE.getLatitude() >= cLat) &&
                                        (boundPointSW.getLongitude() <= cLong && boundPointNE.getLongitude() >= cLong)
                        )) {

                    earthquakeObject = new Earthquake();

                    if(!earthquakePlace.isEmpty() || !earthquakePlace.isBlank()){
                        earthquakeObject.setRegion(earthquakePlace);
                    } else {
                        earthquakeObject.setRegion("unknown");
                    }

                    if (earthquakeProperties.has("mag") && !earthquakeProperties.isNull("mag")) {
                        earthquakeObject.setMagnitude(earthquakeProperties.getDouble("mag"));
                        earthquakeObject.calculateEffectingRadius(earthquakeObject.getMagnitude());
                    }
                     else {
                        System.out.println("earthquake has no mag!");
                        earthquakeObject.setMagnitude(-1);
                    }
                    if (earthquakeGeometry.has("coordinates")) {
                        double latitude = earthquakeGeometry.getJSONArray("coordinates").getDouble(1);
                        double longitude = earthquakeGeometry.getJSONArray("coordinates").getDouble(0);
                        double depth = earthquakeGeometry.getJSONArray("coordinates").getDouble(2);
                        earthquakeObject.setLatitude(latitude);
                        earthquakeObject.setLongitude(longitude);
                        earthquakeObject.setDepth(depth);
                    }
                    if (earthquakeProperties.has("time")) {
                        long val = earthquakeProperties.getLong("time");
                        Date date = new Date(val);
                        earthquakeObject.setDate(date);
                    }
                    earthquakeList.add(earthquakeObject);
                }
                ObjectMapper mapper = new ObjectMapper();
                for (Earthquake earthQ : earthquakeList) {
                    String jsonString = mapper.writeValueAsString(earthQ);
//                    System.out.println(earthQ.toString());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        PrintEarthquakeList();
        return earthquakeList;
    }

    public List<Earthquake> listByDate(Date date) {
        try {
            Date currentDate = new Date();
            List<Earthquake> filteredEarthquakes = filterByDateRange(earthquakeList, date, currentDate);
            System.out.println("filtering done");
            return (filteredEarthquakes);
        } catch (Exception e) {
            return null;
        }
    }

    public List<Earthquake> listByMagnitudes(double minVal, double maxVal) {
        try {
            List<Earthquake> filteredEarthquakes = filterByMagnitude(earthquakeList, minVal, maxVal);
            return (filteredEarthquakes);
        } catch (Exception e) {
            return null;
        }
    }

    private static List<Earthquake> filterByDateRange(List<Earthquake> earthquakes, Date startDate, Date endDate) {
        List<Earthquake> filteredObjects = new ArrayList<>();
        for (Earthquake earthquake : earthquakes) {
            Date occurredDate = earthquake.getDate();
            if (earthquake.getDate().compareTo(startDate) != -1 && earthquake.getDate().compareTo(endDate) != 1) {
                filteredObjects.add(earthquake);
            }
        }
        return filteredObjects;
    }

    private static List<Earthquake> filterByMagnitude(List<Earthquake> earthquakes, double minVal, double maxVal) {
        List<Earthquake> filteredObjects = new ArrayList<>();
        for (Earthquake earthquake : earthquakes) {
            if (earthquake.getMagnitude() >= minVal && earthquake.getMagnitude() <= maxVal) {
                filteredObjects.add(earthquake);
            }
        }
        return filteredObjects;
    }

    private void PrintEarthquakeList(){
        for (Earthquake e:earthquakeList) {
            Gson gson = new Gson();
            String json = gson.toJson(e);
            System.out.println(json);
        }
    }
}

