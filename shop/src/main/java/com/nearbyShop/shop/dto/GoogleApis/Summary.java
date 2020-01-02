package com.nearbyShop.shop.dto.GoogleApis;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Summary {

    @JsonProperty(value = "numResults", required = true)
    private long numResults;

    public Summary() {

    }

    public long getNumResults() {
        return numResults;
    }

    public void setNumResults(long numResults) {
        this.numResults = numResults;
    }

}