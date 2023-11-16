FROM maven:3.6.3-jdk-8
WORKDIR /app
COPY . .
EXPOSE 8080
RUN mvn clean install
CMD ["java","-cp", "target/tugas-besar-2-SOAP-jar-with-dependencies.jar","com.LMS.Main"]
