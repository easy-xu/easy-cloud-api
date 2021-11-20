FROM nacos/nacos-server:latest
MAINTAINER easyxu
ENV MODE=standalone
ADD nacos/conf/application.properties conf/application.properties
EXPOSE 8848
ENTRYPOINT ["sh","bin/docker-startup.sh"]