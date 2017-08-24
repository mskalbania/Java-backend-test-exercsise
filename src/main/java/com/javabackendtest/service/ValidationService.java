package com.javabackendtest.service;

import com.javabackendtest.pojo.RequestData;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ValidationService {

    private final int X_COORDINATE_INDEX = 0;
    private final int Y_COORDINATE_INDEX = 1;

    public boolean validate(RequestData requestData) {
        return validateRoomSize(requestData) && validateStartingCoordinates(requestData);
    }

    boolean validateRoomSize(RequestData requestData) {
        final List<Integer> roomSizeCoordinates = requestData.getRoomSize();
        return roomSizeCoordinates.get(X_COORDINATE_INDEX) > 0 &&
                roomSizeCoordinates.get(Y_COORDINATE_INDEX) > 0;
    }

    boolean validateStartingCoordinates(RequestData requestData) {
        final List<Integer> roomSizeCoordinates = requestData.getRoomSize();
        final List<Integer> startingCoordinates = requestData.getStartingCoordinates();
        return startingCoordinates.get(X_COORDINATE_INDEX) >= 0
                && startingCoordinates.get(X_COORDINATE_INDEX) < roomSizeCoordinates.get(X_COORDINATE_INDEX)
                && startingCoordinates.get(Y_COORDINATE_INDEX) >= 0
                && startingCoordinates.get(Y_COORDINATE_INDEX) < roomSizeCoordinates.get(Y_COORDINATE_INDEX);
    }

    //Validation of patches not required: wrong patch coordinates does not have impact on logic
}
