#!/bin/sh
echo ">>> Starting Spring Boot on port: $PORT"
java -Dserver.port=${PORT:-8080} -jar target/codeboard-backend-0.0.1-SNAPSHOT.jar