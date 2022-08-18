schoolvery-be

## Docker 실행

1. .env.example 파일의 복사하고 복사본의 이름을 .env로 수정합니다.
2. 해당 파일에 환경변수를 입력합니다.
3. application.properties에 jwt 관련한 설정을 주석 해제 해줍니다.  
4. ./gradlew build 또는 ./build.sh를 통해 jar 파일을 생성합니다.  
   만약 ./gradlew build가 안된다면 gradle wrapper 또는 ./initialize.sh을 실행해줍니다.  
   만약 contextLoads() FAILED 에러가 나면 무시해줘도 됩니다.  
   build/libs 안에 schoolvery-server-0.0.1-SNAPSHOT.jar(추후 변경 가능) 라는 파일만 존재한다면 실행하는데 문제가 없습니다.

### 일반(개발용) 실행
`docker compose up --build`  
중지: `docker compose down`

### 테스트용 실행
`./scripts/production-run.sh`  
중지: `./scripts/production-down.sh`