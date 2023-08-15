FROM openjdk:17-slim-bullseye
COPY build/libs/hc3-main-heating-*-all.jar hc3-main-heating.jar
EXPOSE 8080
CMD  java -jar hc3-main-heating.jar
