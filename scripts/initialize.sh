docker run --rm -it -w /app -v $(pwd):/app gradle:jdk11 /bin/bash -c "gradle wrapper"
exit