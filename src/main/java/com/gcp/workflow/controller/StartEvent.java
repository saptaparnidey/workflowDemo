package com.gcp.workflow.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

@RestController
public class StartEvent {

    @PostMapping("/startevent")
    public ResponseEntity<String> startEvent(@RequestBody String payload)throws IOException, InterruptedException, ExecutionException {
//        String resp=triggerWorkflow.triggerWorkflow();
        return new ResponseEntity<>("Workflow Event Started: "+resp, HttpStatus.OK);
    }
}
