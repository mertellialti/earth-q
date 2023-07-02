package com.ellialti.earthQW.domain.earthquake.request;

import com.ellialti.earthQW.domain.Request;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RetrieveEarthquakeListByMagnitude {
    double minVal;
    double maxVal;

    public RetrieveEarthquakeListByMagnitude(double minVal, double maxVal) {
        this.minVal = minVal;
        this.maxVal = maxVal;
    }

    public RetrieveEarthquakeListByMagnitude() {
    }
}
