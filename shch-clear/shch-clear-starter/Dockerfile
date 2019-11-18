FROM openjdk:8-jre-alpine
VOLUME /tmp
ADD target/shch-liquidation-starter-2.2.2-SNAPSHOT.jar /app.jar
RUN echo "Asia/Shanghai" > /etc/timezone
ENV JAVA_OPTS="-Xmx200m -Xms200m -Xmn64m -Xss256k"
ENV BOOT_OPTS="--spring.profiles.active=dev"
EXPOSE 8080
ENTRYPOINT [ "sh", "-c", "java ${JAVA_OPTS} -Djava.security.egd=file:/dev/./urandom -jar /app.jar ${BOOT_OPTS}'" ]