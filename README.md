# 🚇 지하철 노선도 앱 (Subway Map App)

## 📌 프로젝트 개요

해당 프로젝트는 기후동행카드의 사용이 가능, 불가능을 구분하기 위한 아이디어로 시작되었으며, 해당 기능 말고도 해당 플랫폼의 지하철 도착정보, 막차시간 등 여러가지 정보를 확인할 수 있습니다.
Java의 FrameWork인 Spring을 이용하여 구현이 되어있습니다.

## 🌟 주요 기능

- **지하철 동행 기후 카드 사용 가능 여부**: 선택한 역 기후 동행카드 사용 가능 여부를 확인할 수 있습니다.
- **버스 동행기후 카드 사용 가능**: 지하철과 마찬가지로 버스 또한 사용 가능 여부 확인이 가능합니다.
- **노선도 조회**: 출발과 목적지를 선택하면 중간에 기후 동행카드 사용 가능 여부를 확인할 수 있습니다.
- **지하철 정보**: 해당 플랫폼의 노선도의 지하철에 관한 정보를 확인할 수 있습니다.


## 🛠️ 기술 스택

- **언어**: Java
- **Framework**: Spring Boot
- **Spring 버전**: 3.2.5
- **JDK**: 17
- **DB**: MySQL 또는 PostgreSQL
- **Tool**: IntelliJ [Version.2023.1] - Spring

## 📥 설치 및 실행 방법
### 설치 절차

1. **레포지토리 클론**
    ```bash
    git clone https://github.com/your-username/subway-map-app.git
    cd subway-map-app
    ```

2. **백엔드 설정**

    1. `application.properties` 파일 설정
        ```properties
        spring.datasource.url=jdbc:mysql://localhost:3306/subway_db
        spring.datasource.username=your_db_username
        spring.datasource.password=your_db_password
        spring.jpa.hibernate.ddl-auto=update
        ```

    2. **의존성 설치 및 빌드**
        ```bash
        cd server
        mvn clean install
        ```

    3. **서버 실행**
        ```bash
        mvn spring-boot:run
        ```

3. **프론트엔드 설정**

    1. **의존성 설치**
        ```bash
        cd client
        npm install
        ```

    2. **앱 실행**
        ```bash
        npm start
        ```

## 💡 사용 방법
1. 앱을 실행한 후, 메인 화면에서 지하철 노선도를 확인할 수 있습니다.
2. 상단 검색 바를 통해 출발지와 목적지를 입력하면 최적의 경로를 안내받을 수 있습니다.
3. 역 정보를 클릭하면 해당 역의 실시간 도착 정보를 확인할 수 있습니다.
4. 즐겨찾기 기능을 통해 자주 이용하는 역을 추가하고, 빠르게 접근할 수 있습니다.

