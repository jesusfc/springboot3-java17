FROM openjdk:17-alpine
ADD ./target/springboot3-java17-0.0.1-SNAPSHOT.jar springboot3-java17-0.0.1-SNAPSHOT.jar
CMD ["java","-Dspring.profiles.active=development","-jar","springboot3-java17-0.0.1-SNAPSHOT.jar"]