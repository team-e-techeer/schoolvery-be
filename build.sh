docker run --rm -w /app -v $(pwd):/app gradle:jdk11 /bin/bash -c "./gradlew clean build"
exit