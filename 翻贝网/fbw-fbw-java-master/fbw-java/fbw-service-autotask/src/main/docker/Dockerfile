FROM java:8
VOLUME /tmp
ADD fbw-service-autotask-3.0.0.jar app.jar
RUN bash -c 'touch /app.jar'
EXPOSE 8030
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]