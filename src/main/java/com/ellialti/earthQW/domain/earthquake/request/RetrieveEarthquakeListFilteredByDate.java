package com.ellialti.earthQW.domain.earthquake.request;

import com.ellialti.earthQW.domain.Request;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class RetrieveEarthquakeListFilteredByDate {

    Date date;

    public RetrieveEarthquakeListFilteredByDate(Date date) {
        this.date = date;
    }

    public RetrieveEarthquakeListFilteredByDate() {
    }
}
