package com.javabackendtest.controller;

import com.javabackendtest.pojo.ErrorMessage;
import com.javabackendtest.pojo.RequestData;
import com.javabackendtest.pojo.ResponseData;
import com.javabackendtest.repository.HoverRepository;
import com.javabackendtest.repository.RequestResponse;
import com.javabackendtest.service.HoverService;
import com.javabackendtest.service.ValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class HoverController {

    private HoverService hoverService;
    private ValidationService validationService;
    private HoverRepository hoverRepository;

    @Autowired
    HoverController(HoverService hoverService, ValidationService validationService,
                    HoverRepository hoverRepository) {

        this.hoverService = hoverService;
        this.validationService = validationService;
        this.hoverRepository = hoverRepository;
    }

    @RequestMapping(value = "/test", method = RequestMethod.POST)
    public ResponseEntity getRespond(@RequestBody RequestData requestData) throws IOException {
        if (validationService.validate(requestData)) {
            ResponseData responseData = hoverService.computeRespondData(requestData);
            hoverRepository.save(new RequestResponse(requestData, responseData));
            return new ResponseEntity<>(responseData, HttpStatus.OK);
        } else {
            ErrorMessage errorMessage = new ErrorMessage("Input data wrong (check room size or starting coordinates)");
            return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
        }
    }
}
