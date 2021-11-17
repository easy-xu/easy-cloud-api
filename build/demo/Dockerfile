FROM java:8
MAINTAINER easyxu
ADD application.jar application.jar
EXPOSE 8080
ENV profile="dev"
ENTRYPOINT ["java","-jar","application.jar", "--spring.profiles.active=${profile}"]
