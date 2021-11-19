FROM java:8
MAINTAINER easyxu
ADD ${jar} ${jar}
EXPOSE 8080
CMD ["java","-jar","${jar}", "--spring.profiles.active=${profile}"]
