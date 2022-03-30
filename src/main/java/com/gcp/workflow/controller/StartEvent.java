package com.gcp.workflow.controller;

import com.gcp.workflow.trigger.TriggerWorkflow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

@RestController
public class StartEvent {

    @Autowired
    private TriggerWorkflow triggerWorkflow;

    @PostMapping("/startevent")
    public ResponseEntity<String> startEvent(@RequestBody String payload)throws IOException, InterruptedException, ExecutionException {
        triggerWorkflow.triggerWorkflow(payload);
        return new ResponseEntity<>("Workflow Event Started ", HttpStatus.OK);
    }
}