FROM amazoncorretto:17

RUN mkdir -p /usr/src/app/

COPY ./*.* /usr/src/app/
COPY weather-resources/*.* /usr/src/app/weather-resources/
COPY weather-entities/*.* /usr/src/app/weather-entities/
COPY weather-clients/*.* /usr/src/app/weather-clients/
COPY weather-commons/*.* /usr/src/app/weather-commons/
COPY weather-models/*.* /usr/src/app/weather-models/

WORKDIR /usr/src/app

RUN yum install -y tar which gzip # TODO remove

# common for all images
ENV MAVEN_HOME /usr/share/maven

COPY --from=maven:3.9.4-eclipse-temurin-11 ${MAVEN_HOME} ${MAVEN_HOME}
COPY --from=maven:3.9.4-eclipse-temurin-11 /usr/local/bin/mvn-entrypoint.sh /usr/local/bin/mvn-entrypoint.sh
COPY --from=maven:3.9.4-eclipse-temurin-11 /usr/share/maven/ref/settings-docker.xml /usr/share/maven/ref/settings-docker.xml

RUN ln -s ${MAVEN_HOME}/bin/mvn /usr/bin/mvn

ARG MAVEN_VERSION=3.9.4
ARG USER_HOME_DIR="/root"
ENV MAVEN_CONFIG "$USER_HOME_DIR/.m2"



RUN mvn spring-boot:run
ENTRYPOINT ["java", "org.springframework.boot.loader.JarLauncher"]


#ARG JAR_FILE=weather-resources/target/weather-resources-0.0.1-SNAPSHOT.jar
#COPY ${JAR_FILE} app.jar
##ENTRYPOINT ["java","-cp","com.weather.resources.WeatherApp"]
#ENTRYPOINT ["java","-jar","/app.jar"]
##ENTRYPOINT ["java"," -jar ","/app.jar com.docker build -t spring-boot-demo .weather.resources.WeatherApp"]

#FROM maven:3-openjdk:17-jdk-alpine as builder
#
#
#FROM nginx
#RUN rm /etc/nginx/conf.d/default.conf
#COPY content /usr/share/nginx/html
#COPY conf /etc/nginx

#WORKDIR /build
#
## Copy the dependency specifications
#COPY pom.xml pom.xml
#COPY weather-clients/pom.xml weather-clients/pom.xml
#COPY weather-commons/pom.xml weather-commons/pom.xml
#COPY weather-models/pom.xml weather-models/pom.xml
#COPY weather-entities/pom.xml weather-entities/pom.xml
#COPY weather-resources/pom.xml weather-resources/pom.xml
#
#
#
## Resolve dependencies for `common` module, e.g., shared libraries
## Also build all the required projects needed by the common module.
## In this case, it will also resolve dependencies for the `root` module.
#RUN mvn -q -ntp -B -pl common -am dependency:go-offline
## Copy full sources for `common` module
#COPY common common
## Install the common module in the local Maven repo (`.m2`)
## This will also install the `root` module.
## See: `la -lat ~/.m2/repository/org/example/*/*`
#RUN mvn -q -B -pl common install
#
## Resolve dependencies for the main application
#RUN mvn -q -ntp -B -pl user-app -am dependency:go-offline
## Copy sources for main application
#COPY user-app user-app
## Package the common and application modules together
#RUN mvn -q -ntp -B -pl common,user-app package
#
#RUN mkdir -p /jar-layers
#WORKDIR /jar-layers
## Extract JAR layers
#RUN java -Djarmode=layertools -jar /build/user-app/target/*.jar extract
#
#FROM adoptopenjdk/openjdk11:centos-jre
#
#RUN mkdir -p /app
#WORKDIR /app
#
## Copy JAR layers, layers that change more often should go at the end
#COPY --from=builder /jar-layers/dependencies/ ./
#COPY --from=builder /jar-layers/spring-boot-loader/ ./
#COPY --from=builder /jar-layers/snapshot-dependencies/ ./
#COPY --from=builder /jar-layers/application/ ./
#
#ENTRYPOINT ["java", "org.springframework.boot.loader.JarLauncher"]