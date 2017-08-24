package com.javabackendtest.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.javabackendtest.pojo.RequestData;
import com.javabackendtest.pojo.ResponseData;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.*;


public class HoverServiceTest {

    private final String SCENARIOS_PATH = "src/test/resources/hover/json/scenarios/";
    private ObjectMapper objectMapper;
    private HoverService hoverService;

    public HoverServiceTest() {
        objectMapper = new ObjectMapper();
        hoverService = new HoverService();
    }

    @Test
    public void shouldPassAllPatchesScenario() throws IOException {

        RequestData requestData =
                objectMapper.readValue(new File(SCENARIOS_PATH + "allPatchesRequest.json"), RequestData.class);
        ResponseData responseData =
                objectMapper.readValue(new File(SCENARIOS_PATH + "allPatchesResponse.json"), ResponseData.class);

        assertEquals(responseData, hoverService.computeRespondData(requestData));
    }

    @Test
    public void shouldPassCorridorScenario() throws IOException {

        RequestData requestData =
                objectMapper.readValue(new File(SCENARIOS_PATH + "corridorPatchesRequest.json"), RequestData.class);
        ResponseData responseData =
                objectMapper.readValue(new File(SCENARIOS_PATH + "corridorPatchesResponse.json"), ResponseData.class);

        assertEquals(responseData, hoverService.computeRespondData(requestData));
    }

    @Test
    public void shouldPassLargeRoomsScenario() throws IOException {

        RequestData requestData =
                objectMapper.readValue(new File(SCENARIOS_PATH + "largeRoomRequest.json"), RequestData.class);
        ResponseData responseData =
                objectMapper.readValue(new File(SCENARIOS_PATH + "largeRoomResponse.json"), ResponseData.class);

        assertEquals(responseData, hoverService.computeRespondData(requestData));
    }

    @Test
    public void shouldPassLoopScenario() throws IOException {

        RequestData requestData =
                objectMapper.readValue(new File(SCENARIOS_PATH + "loopRequest.json"), RequestData.class);
        ResponseData responseData =
                objectMapper.readValue(new File(SCENARIOS_PATH + "loopResponse.json"), ResponseData.class);

        assertEquals(responseData, hoverService.computeRespondData(requestData));
    }

    @Test
    public void shouldPassNoMoveScenario() throws IOException {

        RequestData requestData =
                objectMapper.readValue(new File(SCENARIOS_PATH + "noMoveRequest.json"), RequestData.class);
        ResponseData responseData =
                objectMapper.readValue(new File(SCENARIOS_PATH + "noMoveResponse.json"), ResponseData.class);

        assertEquals(responseData, hoverService.computeRespondData(requestData));
    }


}
