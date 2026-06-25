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
      -Xms2g \
      -Xmx2g \
      -XX:MaxMetaspaceSize=512m \
      -XX:ReservedCodeCacheSize=256m \
      -XX:+UseG1GC \
      -XX:MaxGCPauseMillis=200 \
      -XX:+HeapDumpOnOutOfMemoryError \
      -XX:HeapDumpPath=/data/oom-dump \
      -XX:+ExitOnOutOfMemoryError \
      -jar /app/app.jar
    ;;
  *)
    exec sh -c "$*"
    ;;
esac
