FROM gradle:7.4.0 as builder

USER root

ENV APP_DIR /app
WORKDIR $APP_DIR

COPY build.gradle $APP_DIR/
COPY settings.gradle $APP_DIR/
COPY dependencies.gradle $APP_DIR/
COPY gradle.properties $APP_DIR/

RUN gradle dependencies

COPY . $APP_DIR

RUN gradle build -x test

USER guest

# -----------------------------------------------------------------------------

FROM openjdk:13-slim-buster

WORKDIR /app

COPY --from=builder /app/init.sh /app
COPY --from=builder /app/build/libs/reporting-api-*.jar /app/

ENTRYPOINT ["sh", "init.sh"]
