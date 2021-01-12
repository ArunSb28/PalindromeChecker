FROM openjdk:11
RUN addgroup --gid 4000 appgroup && adduser --home /app --shell /bin/sh --uid 3000 --disabled-password --gecos TestUser --gid 4000 linxuser
USER linxuser
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} /app/app.jar
EXPOSE 8088
ENTRYPOINT ["java","-jar","/app/app.jar"]