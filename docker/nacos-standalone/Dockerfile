FROM nacos/nacos-server:latest
MAINTAINER easyxu
ADD nacos/conf/application.properties conf/application.properties
EXPOSE 8848
ENTRYPOINT ["sh","nacos/bin/startup.sh", "-m", "standalone"]