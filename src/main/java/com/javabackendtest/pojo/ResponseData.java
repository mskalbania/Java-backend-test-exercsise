package com.javabackendtest.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.List;

public class ResponseData {

    private long id;
    @JsonProperty("coords")
    private List<Integer> finalCoordinates;
    @JsonProperty("patches")
    private int patchesCleaned;

    public ResponseData() {
    }

    public List<Integer> getFinalCoordinates() {
        return finalCoordinates;
    }

    public ResponseData(List<Integer> finalCoordinates, int patchesCleaned) {
        this.finalCoordinates = finalCoordinates;
        this.patchesCleaned = patchesCleaned;
    }

    public void setFinalCoordinates(List<Integer> finalCoordinates) {
        this.finalCoordinates = finalCoordinates;
    }

    public int getPatchesCleaned() {
        return patchesCleaned;
    }

    public void setPatchesCleaned(int patchesCleaned) {
        this.patchesCleaned = patchesCleaned;
    }

    @Override
    public boolean equals(Object o){
        ResponseData requestData = ((ResponseData) o);
        return patchesCleaned == requestData.getPatchesCleaned() &&
                finalCoordinates.equals(requestData.finalCoordinates);
    }
}
