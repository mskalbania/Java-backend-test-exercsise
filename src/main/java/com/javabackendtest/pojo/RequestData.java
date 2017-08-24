package com.javabackendtest.pojo;


import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

public class RequestData {

    private long id;
    private List<Integer> roomSize;
    @JsonProperty("coords")
    private List<Integer> startingCoordinates;
    private Set<List<Integer>> patches;
    private String instructions;

    public RequestData() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<Integer> getRoomSize() {
        return roomSize;
    }

    public void setRoomSize(List<Integer> roomSize) {
        this.roomSize = roomSize;
    }

    public List<Integer> getStartingCoordinates() {
        return startingCoordinates;
    }

    public void setStartingCoordinates(List<Integer> startingCoordinates) {
        this.startingCoordinates = startingCoordinates;
    }

    public Set<List<Integer>> getPatches() {
        return patches;
    }

    public void setPatches(Set<List<Integer>> patches) {
        this.patches = patches;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    //Auto generated just for debug
    @Override
    public String toString() {
        return "RequestData{" +
                "roomSize=" + roomSize +
                ", startingCoordinates=" + startingCoordinates +
                ", patches=" + patches +
                ", instructions='" + instructions + '\'' +
                '}';
    }
}
