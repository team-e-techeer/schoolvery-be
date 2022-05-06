schoolvery-be

## Docker 실행
### 사전 세팅
먼저 ./gradlew build를 통해 jar 파일을 생성합니다.
해당 파일은 build/libs 안에 schoolvery-server-0.0.1-SNAPSHOT.jar(추후 변경 가능) 라는 이름으로 존재해야 합니다.
1. .env.example 파일의 이름을 .env로 수정합니다.
2. 해당 파일에 환경변수를 입력합니다.
3. `docker-compose up --build`
