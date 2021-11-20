FROM java:8
MAINTAINER easyxu
COPY nacos nacos
EXPOSE 8848
ENTRYPOINT ["sh","nacos/bin/startup.sh", "-m", "standalone"]