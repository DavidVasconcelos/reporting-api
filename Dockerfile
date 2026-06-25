FROM eclipse-temurin:25-jdk AS builder

WORKDIR /app

ENV GRADLE_OPTS="-Dorg.gradle.daemon=false -Dorg.gradle.parallel=false -Dorg.gradle.workers.max=2 -Xmx1g"

COPY gradlew .
COPY gradle/ gradle/
RUN chmod +x ./gradlew

COPY build.gradle settings.gradle dependencies.gradle gradle.properties ./
COPY ./gradle/git-hooks/git-hooks.gradle ./gradle/git-hooks/

RUN ./gradlew dependencies

COPY . .
RUN ./gradlew assemble

# -----------------------------------------------------------------------------

FROM eclipse-temurin:25-jre

WORKDIR /app

RUN addgroup --system spring && adduser --system --ingroup spring spring
USER spring

COPY --from=builder /app/init.sh /app/
COPY --from=builder /app/build/libs/reporting-api.jar /app/app.jar

ENTRYPOINT ["bash", "init.sh"]
