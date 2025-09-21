# FROM 指定使用哪个镜像作为基准
FROM openjdk:8-jdk-alpine
# 数据卷
VOLUME /tmp
# 复制文件到镜像中
COPY ./weblog-server/target/weblog-server-0.0.1-SNAPSHOT.jar app.jar
# 指定容器内的时区
RUN echo "Asia/Shanghai" > /etc/timezone;
# ENV为设置环境变量
ENV JAVA_OPTS=""
# ENTRYPOINT 为启动时运行的命令
# FROM 指定使用哪个镜像作为基准
FROM openjdk:8-jdk-alpine

# 设置容器内的时区
RUN apk add --no-cache tzdata && \
    ln -sf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime && \
    echo "Asia/Shanghai" > /etc/timezone

# 数据卷
VOLUME /tmp

# 复制文件到镜像中
COPY ./weblog-server/target/weblog-server-0.0.1-SNAPSHOT.jar app.jar

# ENV为设置环境变量
ENV JAVA_OPTS=""
ENV SPRING_PROFILES_ACTIVE="dev"

# ENTRYPOINT 为启动时运行的命令
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -Xms200m -Xmx200m -Dspring.profiles.active=$SPRING_PROFILES_ACTIVE -jar app.jar"]