FROM java:8
MAINTAINER easyxu
#发布时设置环境变量，运行时设定
ENV JPASSWORD=abc
#使用gradle替换变量，构建前设定
ADD ${jar} ${jar}
EXPOSE 8080
CMD ["java","-jar","${jar}", "--spring.profiles.active=${profile}", "--jasypt.encryptor.password=${JPASSWORD}"]
