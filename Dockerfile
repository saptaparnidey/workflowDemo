FROM openjdk:8
ADD target/workflowdemo.jar workflowdemo.jar
ENV GOOGLE_CLOUD_PROJECT workflow-demo-345011
ENTRYPOINT ["java", "-jar", "workflowdemo.jar"]