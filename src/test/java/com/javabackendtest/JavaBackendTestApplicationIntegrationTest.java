package com.javabackendtest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.javabackendtest.pojo.RequestData;
import com.javabackendtest.service.HoverService;
import org.apache.commons.io.IOUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.io.FileInputStream;
import java.io.IOException;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class JavaBackendTestApplicationIntegrationTest {

    private final String TEST_RESOURCES_PATH = "src/test/resources/hover/json/";

    private ObjectMapper objectMapper;

    @Autowired
    private HoverService hoverService;

    @Autowired
    private WebApplicationContext context;

    private MockMvc mockMvc;

    @Before
    public void setUp() {
        objectMapper = new ObjectMapper();
        mockMvc = webAppContextSetup(context).build();
    }

    @Test
    public void shouldReturnErrorMessageWhenRequestHasInvalidParameters() throws Exception {

        String invalidRequest = parseJsonFile(TEST_RESOURCES_PATH + "invalidRoomSizeRequest.json");
        RequestData invalidRequestData = objectMapper.readValue(invalidRequest, RequestData.class);
        String errorMessage = parseJsonFile(TEST_RESOURCES_PATH + "errorMessage.json");

        mockMvc.perform(MockMvcRequestBuilders.post("/test")
                .content(objectMapper.writeValueAsBytes(invalidRequestData))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(content().json(errorMessage));
    }

    private String parseJsonFile(String path) throws IOException {
        return IOUtils.toString(new FileInputStream(path), "UTF-8");
    }

    @Test
    public void shouldReturnDefaultResponseWhenSendDefaultRequest() throws Exception {

        String defaultRequest = parseJsonFile(TEST_RESOURCES_PATH + "/defaultRequest.json");
        RequestData defaultRequestData = objectMapper.readValue(defaultRequest, RequestData.class);
        String defaultRespond = parseJsonFile(TEST_RESOURCES_PATH + "/defaultResponse.json");

        mockMvc.perform(MockMvcRequestBuilders.post("/test")
                .content(objectMapper.writeValueAsBytes(defaultRequestData))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(defaultRespond));


    }
}