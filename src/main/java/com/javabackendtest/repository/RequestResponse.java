package com.javabackendtest.repository;

import com.javabackendtest.pojo.RequestData;
import com.javabackendtest.pojo.ResponseData;
import org.springframework.data.annotation.Id;

public class RequestResponse {

    @Id
    private String id;
    private RequestData requestData;
    private ResponseData responseData;

    public RequestResponse() {
    }

    public RequestResponse(RequestData requestData, ResponseData responseData) {
        this.requestData = requestData;
        this.responseData = responseData;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public RequestData getRequestData() {
        return requestData;
    }

    public void setRequestData(RequestData requestData) {
        this.requestData = requestData;
    }

    public ResponseData getResponseData() {
        return responseData;
    }

    public void setResponseData(ResponseData responseData) {
        this.responseData = responseData;
    }
}
