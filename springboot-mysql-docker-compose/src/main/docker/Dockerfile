FROM adoptopenjdk/openjdk14:alpine-slim

RUN apk --update --no-cache add nmap nmap-scripts curl wget vim git jq bash bash-completion tcpdump bind-tools nmap-ncat iputils libcap libcap-ng-utils tzdata \
    && mkdir -p /opt/microservices \
    && adduser -D -u 1000 usrjava \
    && chown -R usrjava /opt/ \
    && setcap cap_net_raw+ep /bin/busybox \
    && rm -rf /var/cache/apk/* /tmp/*
WORKDIR /opt/microservices
EXPOSE 8080
USER usrjava
ADD maven/springboot-mysql-docker-compose.jar /opt/microservices/app.jar
CMD ["-Djava.security.egd=file:/dev/urandom", "-Xmx1024m", "-jar", "app.jar"]
ENTRYPOINT ["java"]