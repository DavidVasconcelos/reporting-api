FROM gradle:8.6-jdk21 AS builder

USER root

ENV APP_DIR /app
WORKDIR $APP_DIR

COPY build.gradle $APP_DIR/
COPY settings.gradle $APP_DIR/
COPY dependencies.gradle $APP_DIR/
COPY gradle.properties $APP_DIR/
COPY ./gradle/git-hooks/git-hooks.gradle $APP_DIR/gradle/git-hooks/

RUN gradle dependencies

COPY . $APP_DIR

RUN gradle assemble --no-daemon

USER guest

# -----------------------------------------------------------------------------

FROM openjdk:21-slim-buster

WORKDIR /app

COPY --from=builder /app/init.sh /app
COPY --from=builder /app/build/libs/reporting-api-*.jar /app/

ENTRYPOINT ["bash", "init.sh"]
