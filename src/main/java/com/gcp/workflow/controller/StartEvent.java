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

    @GetMapping("/startevent")
    public ResponseEntity<String> startEvent()throws IOException, InterruptedException, ExecutionException {
        String resp=triggerWorkflow.triggerWorkflow();
        return new ResponseEntity<>("Workflow Event Started: "+resp, HttpStatus.OK);
    }
}
