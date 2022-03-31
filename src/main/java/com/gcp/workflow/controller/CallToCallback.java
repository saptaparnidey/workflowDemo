package com.gcp.workflow.controller;

import com.gcp.workflow.trigger.TriggerCallbackWorkflow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

@RestController
public class CallToCallback {

    @Autowired
    TriggerCallbackWorkflow triggerCallbackWorkflow;

    @PostMapping("/callbackurl")
    public ResponseEntity<String> callbackRequest(@RequestBody String request) throws InterruptedException, ExecutionException, IOException {
        String resp =triggerCallbackWorkflow.triggerCallbackWorkflow(request);
        return new ResponseEntity<>(resp, HttpStatus.OK);
    }
}
