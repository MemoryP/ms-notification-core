FROM nexus.ocbc.com:8443/repository/ocbc-docker-release/openjre-21:1.0-0
MAINTAINER digitalcoreteam@ocbc.com
RUN echo "securerandom.source=file:/dev/urandom" >> /usr/lib/jvm/conf/security/java.security
COPY config /app/config
ENV TZ Asia/Singapore
ADD target/ms-notification-core-api-*-SNAPSHOT.jar /app/ms-notification-core.jar
EXPOSE 8080
ENTRYPOINT ["summon", "-f", "/etc/summon/secrets.yml", "java", "-jar","/app/ms-notification-core.jar"]
