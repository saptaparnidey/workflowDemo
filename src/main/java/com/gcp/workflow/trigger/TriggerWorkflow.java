package com.gcp.workflow.trigger;

import com.google.cloud.workflows.executions.v1.CreateExecutionRequest;
import com.google.cloud.workflows.executions.v1.Execution;
import com.google.cloud.workflows.executions.v1.ExecutionsClient;
import com.google.cloud.workflows.executions.v1.WorkflowName;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

@Component
public class TriggerWorkflow {

    private static final String PROJECT = "workflow-demo-345011";
    private static final String LOCATION = "us-central1";
    private static final String WORKFLOW = "workflow-args";
    private static volatile boolean finished;


    private Log log = LogFactory.getLog(TriggerWorkflow.class);

    public void triggerWorkflow()throws IOException, InterruptedException, ExecutionException {

        String result="";


        try (ExecutionsClient executionsClient = ExecutionsClient.create()) {

            WorkflowName parent = WorkflowName.of(PROJECT, LOCATION, WORKFLOW);
            String payload = "{\"firstName\":\"Dragon\",\"lastName\":\"Ally\"}\"}";

            CreateExecutionRequest request =
                    CreateExecutionRequest.newBuilder()
                            .setParent(parent.toString())
                            .setExecution(Execution.newBuilder().setArgument(payload).build())
                            .build();

            Execution response = executionsClient.createExecution(request);
            String executionName = response.getName();
            result=response.getResult();

            log.info("Created execution: "+ executionName);
            log.info("Result.. : "+result);

            long backoffTime = 0;
            long backoffDelay = 1_000; // Start wait with delay of 1,000 ms
            final long backoffTimeout = 10 * 60 * 1_000; // Time out at 10 minutes

            log.info("Poll for results...");

            while (!finished && backoffTime < backoffTimeout) {
                Execution execution = executionsClient.getExecution(executionName);
                finished = execution.getState() != Execution.State.ACTIVE;

                if (!finished) {
                    log.info("- Waiting for results");
                    Thread.sleep(backoffDelay);
                    backoffTime += backoffDelay;
                    backoffDelay *= 2; // Double the delay to provide exponential backoff.
                } else {
                    log.info("Execution finished with state: " + execution.getState().name());
                    log.info("Execution results: " + execution.getResult());
                }
            }

        }

    }

}
