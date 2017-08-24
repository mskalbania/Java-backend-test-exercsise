package com.javabackendtest.service;

import com.javabackendtest.pojo.RequestData;
import com.javabackendtest.pojo.ResponseData;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;

@Service
public class HoverService {

    private final int X_COORDINATE_INDEX = 0;
    private final int Y_COORDINATE_INDEX = 1;

    private RequestData request;
    private List<Integer> currentCoordinates;
    private int patchesCleaned;

    public ResponseData computeRespondData(RequestData request) {
        initializeVariables(request);
        for (char c : request.getInstructions().toCharArray()) {
            switch (c) {
                case 'N':
                    if (!isOnNorthWall()) {
                        moveTowardsNorth();
                    }
                    break;
                case 'S':
                    if (!isOnSouthWall()) {
                        moveTowardsSouth();
                    }
                    break;
                case 'E':
                    if (!isOnEastWall()) {
                        moveTowardsEast();
                    }
                    break;
                case 'W':
                    if (!isOnWestWall()) {
                        moveTowardsWest();
                    }
                    break;
            }
            cleanPatchIfOn();
        }
        return new ResponseData(currentCoordinates, patchesCleaned);
    }

    private void initializeVariables(RequestData request) {
        this.request = request;
        this.currentCoordinates = request.getStartingCoordinates();
        this.patchesCleaned = 0;
    }

    private void moveTowardsNorth() {
        int currentY = currentCoordinates.get(Y_COORDINATE_INDEX);
        currentCoordinates.remove(Y_COORDINATE_INDEX);
        currentCoordinates.add(Y_COORDINATE_INDEX, currentY + 1);
    }

    private void moveTowardsSouth() {
        int currentY = currentCoordinates.get(Y_COORDINATE_INDEX);
        currentCoordinates.remove(Y_COORDINATE_INDEX);
        currentCoordinates.add(Y_COORDINATE_INDEX, currentY - 1);
    }

    private void moveTowardsEast() {
        int currentX = currentCoordinates.get(X_COORDINATE_INDEX);
        currentCoordinates.remove(X_COORDINATE_INDEX);
        currentCoordinates.add(X_COORDINATE_INDEX, currentX + 1);
    }

    private void moveTowardsWest() {
        int currentX = currentCoordinates.get(X_COORDINATE_INDEX);
        currentCoordinates.remove(X_COORDINATE_INDEX);
        currentCoordinates.add(X_COORDINATE_INDEX, currentX - 1);
    }

    private void cleanPatchIfOn() {
        Iterator<List<Integer>> iterator = request.getPatches().iterator();
        while (iterator.hasNext()) {
            List<Integer> currentPatchCords = iterator.next();
            if (isOnPatch(currentPatchCords)) {
                patchesCleaned++;
                iterator.remove();
                break;
            }
        }
    }

    private boolean isOnNorthWall() {
        return currentCoordinates.get(Y_COORDINATE_INDEX)
                .equals(request.getRoomSize().get(Y_COORDINATE_INDEX) - 1);
    }

    private boolean isOnSouthWall() {
        return currentCoordinates.get(Y_COORDINATE_INDEX).equals(0);
    }


    private boolean isOnWestWall() {
        return currentCoordinates.get(X_COORDINATE_INDEX).equals(0);
    }

    private boolean isOnEastWall() {
        return currentCoordinates.get(X_COORDINATE_INDEX)
                .equals(request.getRoomSize().get(X_COORDINATE_INDEX) - 1);
    }

    private boolean isOnPatch(List<Integer> patchCords) {
        return currentCoordinates.get(X_COORDINATE_INDEX).equals(patchCords.get(X_COORDINATE_INDEX)) &&
                currentCoordinates.get(Y_COORDINATE_INDEX).equals(patchCords.get(Y_COORDINATE_INDEX));
    }
}
