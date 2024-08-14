#!/bin/bash


# Function to build and restart the application
build_and_restart() {
  echo "Changes detected. Building and restarting the application..."
  ./gradlew build -x test
 PID=$(ps aux | grep 'java -jar build/libs/XENO_backend-0.0.1-SNAPSHOT.jar' | grep -v grep | awk '{print $2}')
if [ -n "$PID" ]; then
    kill -9 $PID
fi
  java -jar build/libs/XENO_backend-0.0.1-SNAPSHOT.jar &
}

# Start the initial build and run
build_and_restart

# Watch for file changes and rebuild/restart as needed
while inotifywait -r -e modify,create,delete /app/src; do
  build_and_restart
done