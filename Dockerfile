FROM openjdk:17-jdk-slim
ADD ./target/springboot3-java17-0.0.1-SNAPSHOT.jar springboot3-java17-0.0.1-SNAPSHOT.jar
CMD ["java","-jar","springboot3-java17-0.0.1-SNAPSHOT.jar"]