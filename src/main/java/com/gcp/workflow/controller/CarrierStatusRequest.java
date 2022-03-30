package com.gcp.workflow.controller;

import com.gcp.workflow.trigger.TriggerWorkflow;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CarrierStatusRequest {

    private Log log = LogFactory.getLog(CarrierStatusRequest.class);

    @PostMapping("/carrierstatusupdate")
    public ResponseEntity<String> carrierStatusReq(@RequestBody String requestPayload){
        log.info("carrierstatusupdate request: "+requestPayload);
        return new ResponseEntity<>("Success", HttpStatus.OK);
    }
}
