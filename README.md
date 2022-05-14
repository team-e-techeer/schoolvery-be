schoolvery-be

## Docker 실행

1. .env.example 파일의 복사하고 복사본의 이름을 .env로 수정합니다.
2. 해당 파일에 환경변수를 입력합니다.
3. ./gradlew build를 통해 jar 파일을 생성합니다.  
   만약 ./gradlew build가 안된다면 gradle wrapper 또는 ./initialize.sh을 실행해줍니다.  
   build 이후 해당 파일은 build/libs 안에 schoolvery-server-0.0.1-SNAPSHOT.jar(추후 변경 가능) 라는 이름으로 존재해야 합니다.
4. `docker-compose up --build`
