FROM openjdk:8
ADD target/workflowdemo.jar workflowdemo.jar
ADD ../cert/workflow-demo-345011-a10b328faa0e.json workflow-demo-345011-a10b328faa0e.json
#EXPOSE 9095
#ENV PORT 9095
ENV GOOGLE_APPLICATION_CREDENTIALS ../cert/workflow-demo-345011-a10b328faa0e.json
ENV GOOGLE_CLOUD_PROJECT workflow-demo-345011
ENTRYPOINT ["java", "-jar", "workflowdemo.jar"]