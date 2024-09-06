#!/usr/bin/env bash

set -e

JVM_OPS="${JVM_OPS:-""}"
APPLICATION_PORT="${PORT:-"8080"}"

COMMAND=${1:-"web"}
echo $COMMAND

echo "Application port:  $APPLICATION_PORT"

case "$COMMAND" in
  migrate|web)
    export SPRING_DATASOURCE_URL="${JDBC_DATABASE_URL}"
    export SPRING_PROFILES_ACTIVE=$( [[ "$COMMAND" == "web" ]] && echo "default" || echo "dbmigration" )

    exec java ${JVM_OPS} -Djava.security.egd=file:/dev/./urandom \
      -Duser.Timezone=America/Sao_Paulo \
      -Dserver.port=$APPLICATION_PORT \
      -Xmx3500m \
      -XX:MaxMetaspaceSize=256m \
      -XX:+UseG1GC \
      -XX:+HeapDumpOnOutOfMemoryError \
      -XX:HeapDumpPath=/data/oom-dump \
      -jar /app/reporting-api-*.jar \
      $COMMAND
    ;;
  *)
    exec sh -c "$*"
    ;;
esac
