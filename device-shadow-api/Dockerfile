FROM eclipseecsp/ecsp-base-java17-tomcat10-apr:1.0.0

ARG PROJECT_JAR_VERSION

ADD target/device-shadow-api.war /tmp/device-shadow.war
ADD src/main/resources/logback.xml /device-shadow-api/conf/logback.xml
COPY src/scripts/* /opt/device-shadow-api/bin/
RUN mkdir /logs/
RUN chmod 777 /logs/
COPY src/main/resources/server.xml /usr/local/tomcat/conf

RUN chmod 755 /opt/device-shadow-api/bin/start.sh
RUN rm -rf /usr/local/tomcat/webapps/* && \
mv /tmp/device-shadow.war /usr/local/tomcat/webapps
RUN addgroup --system appgroup 
RUN adduser --system appuser --ingroup appgroup
RUN chown -R appuser:appgroup /usr/local/tomcat/
USER appuser
RUN ls -l /usr/local/tomcat/bin/
ENTRYPOINT /bin/sh /opt/device-shadow-api/bin/start.sh
