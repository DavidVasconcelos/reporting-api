FROM eclipse-temurin:25-jre

LABEL org.opencontainers.image.source="https://github.com/DavidVasconcelos/reporting-api"

WORKDIR /app

EXPOSE 8080

RUN addgroup --system spring && adduser --system --ingroup spring spring
USER spring

COPY init.sh /app/
COPY build/libs/reporting-api.jar /app/app.jar

ENTRYPOINT ["bash", "init.sh"]
