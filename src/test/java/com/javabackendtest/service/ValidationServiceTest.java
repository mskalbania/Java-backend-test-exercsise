package com.javabackendtest.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.javabackendtest.pojo.RequestData;
import org.apache.commons.io.IOUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import static org.junit.Assert.*;

public class ValidationServiceTest {

    private final String TEST_RESOURCES_PATH = "src/test/resources/hover/json/";
    private final ValidationService validationService = new ValidationService();
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void shouldReturnTrueIfRoomSizeValid() throws IOException {
        String validRequest = parseJsonFile(TEST_RESOURCES_PATH + "validRoomSizeRequest.json");
        RequestData requestData = objectMapper.readValue(validRequest, RequestData.class);
        assertEquals(true, validationService.validate(requestData));
    }

    @Test
    public void shouldReturnFalseIfRoomSizeInvalid() throws IOException {
        String invalidRequest = parseJsonFile(TEST_RESOURCES_PATH + "invalidRoomSizeRequest.json");
        RequestData requestData = objectMapper.readValue(invalidRequest, RequestData.class);
        assertEquals(false, validationService.validate(requestData));
    }

    @Test
    public void shouldReturnTrueIfStartingCoordinatesValid() throws IOException {
        String validRequest = parseJsonFile(TEST_RESOURCES_PATH + "validStartingCoordinatesRequest.json");
        RequestData requestData = objectMapper.readValue(validRequest, RequestData.class);
        assertEquals(true, validationService.validate(requestData));
    }

    @Test
    public void shouldReturnFalseIfStartingCoordinatesInvalid() throws IOException {
        String invalidRequest1 = parseJsonFile(TEST_RESOURCES_PATH + "invalidStartingCoordinatesRequest1.json");
        String invalidRequest2 = parseJsonFile(TEST_RESOURCES_PATH + "invalidStartingCoordinatesRequest2.json");
        RequestData requestData1 = objectMapper.readValue(invalidRequest1, RequestData.class);
        RequestData requestData2 = objectMapper.readValue(invalidRequest2, RequestData.class);
        assertEquals(false, validationService.validate(requestData1));
        assertEquals(false, validationService.validate(requestData2));
    }

    private String parseJsonFile(String path) throws IOException {
        return IOUtils.toString(new FileInputStream(path), "UTF-8");
    }

}