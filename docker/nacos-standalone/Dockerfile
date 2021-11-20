FROM nacos/nacos-server:latest
MAINTAINER easyxu
ADD nacos/conf/application.properties conf/application.properties
EXPOSE 8848
ENTRYPOINT ["sh","bin/docker-startup.sh", "-m", "standalone"]