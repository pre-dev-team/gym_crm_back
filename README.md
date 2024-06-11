# 🌲<span style="font-size:40px">Pre_Dev</span> < GYM_Template >
[🔗 GYM_Template 바로가기(배포 후 수정 예정)](https://supreme-price-d63.notion.site/Lesson-Team-Project-21b09ddc888b4155b53141ec061d2e20?pvs=4)<br>
[🔗 API 명세서 바로가기](https://www.notion.so/139f8d29bd1942ccadbc93cff8497c01?v=7957a7d0608b4d3aacbf694828e4d432&pvs=4)

## 📍목차
1.[ 프로젝트 소개](#-프로젝트-소개)<br>
2.[ 주요기능](#-주요기능)<br>
3.[ 기획 의도](#-왜-이-프로젝트를-기획했나요)<br>
4.[ 프로젝트를 통해 배운점](#-이-프로젝트를-통해-무엇을-배우셨나요)<br>
5.[ 개발 기간](#-프로젝트-개발-기간)<br>
6.[ 팀 소개](#-팀-소개)<br>
7.[ 역할 분담](#️-역할-분담)<br>
8.[ API명세서](#-api-명세서-바로가기)<br>
9.[ 개발 환경](#️-개발-환경)<br>
10.[ pom.xml](#️-pomxml)<br>
11.[ node modules](#️-node-modules)<br>
12.[ 협업방식](#-협업-방식)<br>
13.[ 프로젝트 진행상황 관리](#-프로젝트-진행-상황-관리)<br>
14.[ branch 전략](#-브랜치-전략)<br>
15.[ github flow 전략](#-github-flow-전략)<br>
16.[ github action](#-github-action---브랜치-생성-자동화)<br>
17.[ 컨벤션](#-컨벤션)<br>
18.[ 커밋 컨벤션](#-커밋-컨벤션)<br>
19.[ 페이지 미리보기](#페이지-미리보기)<br>

##  🍀프로젝트 소개

- GYM_Template은 회원 명부, PT회원 명부, 예약 날짜 및 시간 관리 시트를 통합하여 관리할 수 있는 다목적 템플릿입니다.
- 웹 서비스 특성 상 개인정보가 많이 들어가있어 보안을 신경썼으며 이메일 인증 절차를 통해 신뢰를 높였습니다.
- 회원들은 자신이 원하는 트레이너를 선택하여 PT를 예약할 수 있습니다.
- 트레이너는 회원의 Inbody 정보 등을 확인하여 적합한 운동 루틴을 설정할 수 있습니다.
- 관리자는 회원과 트레이너 전체를 확인할 수 있으며, 예약 횟수를 월별 조회 가능하고, 트레이너의 등록 및 연차 승인을 할 수 있습니다.

## 🌱 주요기능
- 회원들은 트레이너를 확인, PT를 예약할 수 있으며, PT를 받은 트레이너를 대상으로 Review를 작성할 수 있습니다.
- 트레이너들은 자신에게 예약을 신청한 회원들의 정보를 확인할 수 있으며, 운동 루틴을 작성하여 PT회원에게 보낼 수 있습니다.
- 관리자는 회원 명부, 트레이너 명부를 확인하고, 차트를 통해 월별 PT 현황을 볼 수 있으며 트레이너들의 연차를 승인 및 반려할 수 있습니다.
- 해당 과정에서 예약은 트레이너와 회원들에게 알람이 가도록 하여 예약 시간을 잊지 않도록 하였습니다.

<br>

## 🧐 왜 이 프로젝트를 기획했나요?
- 많은 전문지식 또는 전문기술을 가진 사람들과 그러한 것을 배우고 싶어하는 사람들이 있습니다. 이러한 수요자와 공급자들을 위한 웹 플랫폼을 초기 주제로 정했습니다.<br>그중 사교육이 활발한 우리나라 특성을 생각해 과외라는 특징을 살려 프로젝트를 기획하게되었습니다.
- 초기 기획안이었던 매칭이라는 아이디어를 적극 활용했습니다.
- 실제 구현 및 사용 가능한 기능 및 서비스 제작했습니다.

<br>

## 🎓 이 프로젝트를 통해 무엇을 배우셨나요?
### 박화목
1. 데이터베이스 설계의 중요성
   프로젝트 중 여러 번의 리팩토링을 겪으며 데이터베이스 설계의 중요성을 인식했습니다. 특히, SOLID 원칙 중 열린-닫힌 원칙을 데이터베이스 테이블 설계에 적용함으로써 서비스 개발 도중에 추가 요구사항이 생겨도 유연하게 대응할 수 있음을 배웠습니다.

2. 데이터 처리 위치의 고민
   반복 및 연산 처리 위치에 대한 고민을 하면서, 복잡한 연산(반복문 및 카운트)은 데이터베이스에서 처리하고, 스프링 서버에서는 데이터 검증을 빠르게 수행하는 것이 효율적임을 깨달았습니다.
   이는 특히 많은 트래픽을 처리해야 하는 상황을 고려하면 더욱 중요하겠다라고 생각했습니다.

3. 객체지향 프로그래밍의 중요성
   백엔드 개발 과정에서 객체 간의 협력과 책임 분배에 대해 더 깊이 고민했습니다.
   특히 서비스 레이어의 책임 과중을 방지하기 위해 어떤 형태의 코드를 작성해야 하는지에 대한 고민이 필요했습니다.
   검증하는 코드를 aop를 통해 캡슐화하고, 데이터 변환을 dto나 entity의 메서드로 구현하는 것이 더 바람직한 작성방법이었습니다.

4. 네트워크 동기화와 프론트엔드 최적화
   싱글 페이지 애플리케이션(SPA)에서 효율적인 렌더링을 위해서는
   컴포넌트의 적절한 분리 및 추상화를 통해 최소한의 ajax요청과 그 결과를 어느정도의 상위에서 구현할지를 많이 고민했습니다.

5. GIT을 활용한 협업과 코드 관리
   GIT을 사용한 협업에서 코드 병합 시 충돌을 최소화하기 위해 SOLID 원칙을 적극적으로 적용해야한다는 것을 깨닳았습니다.
   또한, 코드 및 깃 컨벤션의 일관성이 코드 관리 및 협업 효율성을 높이는 데 중요함을 인식했습니다.

### 김도훈
1. 이번 프로젝트를 통해 먼저 배운 것은 현업에서의 실무 경험이 이론만큼 중요하다는 것입니다. 과정에서 배운 지식이 실제 현장에서 어떻게 활용되는지를 경험적으로 알게 되었습니다. 이를 통해 이론과 실무 간의 간극을 좁히고, 실무에 필요한 능력을 보강해야 한다는 인식을 가졌습니다.

2. 또한, 팀 프로젝트를 통해 코딩 능력은 중요하지만, 팀원 간의 협력과 소통 역시 필수적이라는 것을 깨달았습니다. 프로젝트를 성공적으로 완수하기 위해서는 개개인의 능력 뿐만 아니라 팀원들과의 조화로운 협업이 필수적입니다. 이를 통해 협업과 소통 능력의 중요성을 다시 한번 깨달았습니다.

3. 마지막으로, 팀 내에서의 분위기와 자기 개발 의지가 프로젝트 성패에 큰 영향을 미친다는 것을 깨달았습니다. 우리 팀은 행운이 좋게도 시작부터 긍정적인 분위기를 유지하며 함께 문제를 해결했습니다. 이러한 분위기가 팀원들의 열정과 책임감을 불러일으켜 프로젝트의 성과를 향상시켰습니다. 그래서 앞으로는 팀 내 분위기 조성과 개인의 자기 개발에 더욱 신경을 써야 한다는 생각을 하게 되었습니다.
### 김세진
1. 프로젝트 중 발생한 여러 가지 문제들을 해결하는 과정에서 문제 해결 능력이 향상된 것을 느꼈습니다. 어려운 상황에서도 주저하지 않고 적극적으로 해결책을 모색하고 실행하는 노력이 중요하다는 것을 배웠습니다.
2. 프로젝트를 진행하면서 새로운 기술들을 배우고 적용해보는 경험이 많았습니다. 이를 통해 기술적으로 성장할 수 있는 기회가 되었습니다.
3. 이번 프로젝트를 통해 팀원들과의 원활한 협업이 얼마나 중요한지 깨달았습니다. 각자의 역할과 책임을 분명히 하고 소통하는 것이 프로젝트의 성패를 좌우한다는 것을 배웠습니다.

### 안근수

<br>

## ⏳ 프로젝트 개발 기간

<!-- ```mermaid
gantt
    title 프로젝트 개발 기간
    dateFormat  YYYY-MM-DD
    section lessonTree
    프로젝트 개발, 배포       :a1, 2024-04-04, 30d 

``` -->
<br>

## 🌈 팀 소개
|<span style="font-size:16px">박화목(팀장)</span>&emsp;&emsp;&emsp;&emsp;&emsp;|<span style="font-size:16px">김도훈</span>&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;|<span style="font-size:16px">김세진</span>&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;|<span style="font-size:16px">안근수</span>&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;|
|--|--|--|--|

<a href="https://github.com/elikese" >
    <img src=https://github.com/pre-dev-team/gym_crm_front/assets/155143646/7ff918f9-0ff3-4960-90a0-86ba27d4b38a width="160px" height="200px">
<a>&emsp;&emsp;
<a href="hhttps://github.com/rlaehgns69" >
    <img src=https://github.com/pre-dev-team/gym_crm_front/assets/155143646/e62d5199-7129-484d-b1cf-fafc58bc22e5 width="160px" height="200px">
<a>&emsp;&emsp;
<a href="https://github.com/leeseongchi" >
    <img src=https://github.com/pre-dev-team/gym_crm_front/assets/155143646/35e58a72-8439-493f-b3d5-2b652fa8f3bd width="160px" height="200px">
</a>
<a>&emsp;&emsp;
<a href="https://github.com/maybeags" >
    <img src=https://github.com/pre-dev-team/gym_crm_front/assets/155143646/ec44a190-7ad9-4a41-98e7-4170a4d5b363 width="160px" height="200px">


<p align="right"><a href="#top">TOP 🔼</a></p>

<br>


## 🙋‍♀️ 역할 분담

<center>

|<span style="font-size:30px">박화목</span>|<span style="font-size:30px">김도훈</span>|<span style="font-size:30px">김세진</span>|<span style="font-size:30px">안근수</span>|
|---|---|---|---|
|TOP3 트레이너 소개페이지|트레이너 연차 조회|트레이너 로그인 구현|예약 현황 차트|
|유저 -> 트레이너 예약시 FCM알림|트레이너 퇴사|트레이너 스케줄 2일씩 조회|트레이너 연차 승인/반려|계정 JWT구현|어드민 비밀번호 변경|회원 리뷰 메인페이지상 비회원 확인 가능|---|
|기능 시연 영상추출|내비게이션바/헤더/로그아웃|인바디 관련 데이터 작성 및 읽기|메일 전송 3분 타이머 구현|
|ERD설계|헤더/로그아웃|전체 예약 조회|DB 참조 Entity들 구현|
|DB 참조 Entity들 구현|유저삭제 -> 예약/인바디 삭제|예약 현황 차트|트레이너 로그인 구현|
|트레이너 로그인 구현|예약삭제 -> 루틴삭제|트레이너 연차 조회|트레이너 스케줄 2일씩 조회|
|트레이너 스케줄 2일씩 조회|트레이너삭제 -> 예약/연차 삭제|트레이너 연차 승인/반려|트레이너 당일 날짜 예약 확인|
|트레이너 당일 날짜 예약 확인|스크롤 이벤트 생성|트레이너 퇴사|회원 리뷰 작성 메인 페이지 반영|
|계정 JWT구현|TOP3 트레이너 소개 페이지|내비게이션바/헤더/로그아웃|회원 리뷰 메인페이지상 비회원 확인 기능|
|트레이너삭제 -> 예약/연차 삭제|유저 -> 트레이너 예약시 FCM 알림|유저삭제 -> 예약/인바디 삭제|인바디 관련 데이터 작성 및 읽기|
|인바디 관련 데이터 작성 및 읽기|트레이너 예약취소시 FCM 알림|예약삭제 -> 루틴삭제|어드민 비밀번호 변경|
|트레이너 연차 조회|계정 JWT구현|트레이너삭제 -> 예약/연차 삭제|헤더/로그아웃|
|트레이너 연차 승인/반려|게시판 페이지네이션 및 검색 기능|스크롤 이벤트 생성|유저 -> 트레이너 예약시 FCM알림|트레이너 퇴사|ERD설계|게시판 유저 이미지 조회|---|
|어드민 비밀번호 변경|학생 프로필 조회|게시물 신고|TOP3 트레이너 소개 페이지|
|내비게이션바/헤더/로그아웃|학생 프로필 수정|게시물 신고페이지|노션 회의록 작성|
|헤더/로그아웃|로그아웃, 회원탈퇴, 메일보내기 등 모달 기능|게시물 조회수|API 명세서 작성|
|유저삭제 ->예약/인바디 삭제|페이지 풋 디자인|프로필 이미지 등록|Presentation 자료 작성|
|예약삭제 -> 루틴삭제|최종 메인페이지 및 로고 디자인|트레이너 연차 등록|인바디 페이지 작성|
|유저 -> 트레이너 예약시 FCM 알림|gif 자료 추출|||
|유저 -> 트레이너 예약취소시 FCM 알림||||
|메일 전송 3분 타이머 구현||||

</center>

### <span style="font-size:30px"> 🌎공통 작업</span>


<br>
<br>

## ⚙️ 개발 환경

<div>
  <img src="https://img.shields.io/badge/Java-007396?style=flat-false&logo=OpenJDK&logoColor=white" style="width:80px"/>
  <img src="https://img.shields.io/badge/Spring Boot-6DB33F?style=flat-false&logo=Spring Boot&logoColor=yellow" style="width:135px"/>
</div>
<div>
  <img src="https://img.shields.io/badge/React-white?style=flat&logo=React&logoColor=61DAFB" style="width:85px"/>
  <img src="https://img.shields.io/badge/JavaScript-gray?style=flat&logo=JavaScript&logoColor=F7DF1E" style="width:130px"/>
  <img src="https://img.shields.io/badge/css-1572B6?style=flat-false&logo=css3&logoColor=white" style="width:70px"/>
  <img src="https://img.shields.io/badge/html5-E34F26?style=flat-false&logo=html5&logoColor=white" style="width:90px"/> 
  <img src="https://img.shields.io/badge/Node.js-green?style=flat&logo=Node.js&logoColor=0052CC" style="width:104px"/>
</div>
<div>
  <img src="https://img.shields.io/badge/MySQL-4479A1?style=flat&logo=mysql&logoColor=white" style="width:85px"/>
</div>
<div>
  <img src="https://img.shields.io/badge/git-F05032?style=flat-false&logo=git&logoColor=white" style="width:60px"/>
  <img src="https://img.shields.io/badge/firebase-FFCA28?style=flat-false&logo=firebase&logoColor=white" style="width:100px"/>
  <img src="https://img.shields.io/badge/aws-232F3E?style=flat-false&logo=amazonaws&logoColor=white" style="width:75px"/>
  <img src="https://img.shields.io/badge/Docker-2496ED?style=flat&logo=Docker&logoColor=white" style="width:100px"/>
</div>

<br>
<br>

<!-- ### ⛓️ pom.xml
|모듈명|용도|
|-|-|
|lombok| 어노테이션으로 코드 자동 생성을 위해 사용 |
|jjwt-api / jjwt-impl / jjwt-jackson| JWT 활용을 위해 사용 |
|spring-boot-starter-mail | 이메일 인증 및 과외 신청, 프로필 홍보 기능 위해 사용 |
|spring-boot-starter-oauth2-client | oauth2 인증을 위해 사용 |
|spring-boot-starter-web | spring web 프로젝트에 필수적인 모듈 |
|mysql-connector-java | mysql 연결을 위해 사용 |
|spring-boot-starter-security | 스프링 시큐리티를 적용하기 위해 사용 |
|spring-boot-starter-aop | 스프링 aop를 적용하기 위해 사용 |
|mybatis-spring-boot-starter | mybatis를 적용하기 위해 사용 |
|spring-boot-starter-thymeleaf | 메일에 html 형식 파일을 보내기 위해 사용 |
|spring-boot-devtools | 빠른 재시작을 위해 사용 | -->

<br>

<!-- ### ⛓️ node modules
|모듈명|용도|
|-|-|
|react-router-dom / react-dom / router|페이지 라우팅을 위해 사용|
|axios|서버와 통신을 위해 사용|
|styled-reset| 스타일 구현의 편의를 위해 사용 |
|@emotion/react / @emotion/styled| 쉬운 css 적용을 위해 사용 |
|@types/react-modal / react-modal| 모달을 구현하기 위해 사용 |
|firebase| 이미지 업로드를 위해 사용 |
|react / react-scripts| 리액트를 적용하기 위해 사용 |
|react-icons| 리액트 아이콘을 적용하기 위해 사용 |
|react-query| 데이터 Fetching, Caching, 동기화, 서버 데이터 업데이트 등을 쉽게 하기 위해 사용 |
|react-quill| 텍스트 에디터를 적용하기 위해 사용 |
|react-select| select 요소를 쉽게 쓰기 위해 사용 |

<p align="right"><a href="#top">TOP 🔼</a></p> -->
<br>

## <a>🤝 협업 방식</a>

1. 엑셀 파일 혹은 프로젝트 노션 페이지를 통해 남은 작업 목록에서 작업을 분담합니다.
   <a href="https://www.notion.so/Team-Pre-Dev-84fe557b3d0b4d59ac7b8acd6f0c714b"> [Notion]</a>
2. 해당하는 업무에 대해 **GitHub Issue**를 생성합니다. (이슈 템플릿 사용)
3. GitHub Actions에 의해 자동으로 생성된 브랜치로 전환하여 해당하는 업무를 진행합니다.
4. 작업을 완료하면 작업한 브랜치에서(main브랜치 X) 코드를 push합니다.
5. **PR(Pull Request)** 을 오픈합니다.
- 코드 리뷰 & 승인은 생산성을 위해 팀장으로 지정하고, 각자가 맡은 부분에 대한 책임감을 가지고 프로젝트를 진행했습니다.

6. PR이 merge되어 close 되면 해당 이슈는 자동으로 Done상태로 변경됩니다.

<br>

## 📊 프로젝트 진행 상황 관리

- <a href="https://github.com/pre-dev-team" target="_blank">🔘 GitHub Issues</a>
   - 간편한 이슈 생성을 위해 기능에 따른 이슈를 만들어 진행하였습니다.
<!-- - <a href="" target="_blank">🗂️ GitHub Projects</a>
    - 칸반 보드로 프로젝트 진행 상황을 한 눈에 확인할 수 있어 일정을 관리하기 수월했습니다. -->

<p align="right"><a href="#top">TOP 🔼</a></p>
<br>

## 🔀 브랜치 전략

### 👍 GitHub Flow 전략

- 프로젝트 기간 동안 팀원들이 같은 시간에 작업하기 때문에 잦은 충돌이 발생할 것을 우려하여 충돌의 크기를 줄이고자 기능별로 담당자를 정해 개발을 진행하였습니다.
- 기본적으로 master branch에 대한 규칙만 정확하게 정립되어 있다면 나머지 가지들에 대해서는 특별한 관여를 하지 않으며 pull request기능을 사용하도록 권장하였습니다.

<br>

### 🚀 GitHub Action - 브랜치 생성 자동화

- 이슈를 생성하면 GitHub Action으로 해당 이슈에 해당하는 브랜치가 자동으로 생성되도록 설정하여 브랜치명을 고민하고 브랜치를 생성하는 시간을 줄였습니다.
- 예) 자동 생성된 브랜치를 pull 하고 ```git fetch ```한뒤 ```git checkout feed좋아요중복방지-#242```하여 해당 브랜치로 이동합니다.
- [브랜치 history] <a href="">백앤드 브랜치 히스토리</a>

<p align="right"><a href="#top">TOP 🔼</a></p>
<br>

## 📐 컨벤션

팀원 간의 원활한 소통과 협업을 위해 커밋 컨벤션과, 코드 컨벤션을 만들어 이를 따랐습니다.
리드미에는 간략히 작성하고, 자세한 컨벤션은 각각의 타이틀에 링크된 깃허브 위키에 적어두었습니다.


### [🔗 커밋 컨벤션](https://www.notion.so/Git-Commit-Message-Convention-967e57f36716424280f2625137691d8c?pvs=4)

- 깃 커밋 컨벤션을 참고하여 회의를 통해 프로젝트에서 사용할 컨벤션을 지정했습니다.

    ```
    1. 커밋 유형 지정
        - 커밋 유형은 영어 대문자로 작성하기
        - 커밋 유형
        - Feat : 새로운 기능 추가
        - Fix : 버그 수정
        - Docs : 문서 수정
        - Style : 코드 formatting, 세미콜론 누락, 코드 자체의 변경이 없는 경우
        - Refactor : 코드 리팩토링
        - Test : 테스트 코드, 리팩토링 테스트 코드 추가
        - Chore : 패키지 매니저 수정, 그 외 기타 수정 ex) .gitignore
        - Design : CSS 등 사용자 UI 디자인 변경
        - Comment : 필요한 주석 추가 및 변경
        - Rename : 파일 또는 폴더 명을 수정하거나 옮기는 작업만인 경우
        - Remove : 파일을 삭제하는 작업만 수행한 경우
        - !BREAKING CHANGE : 커다란 API 변경의 경우
        - !HOTFIX : 급하게 치명적인 버그를 고쳐야 하는 경우

    🧾 2. 제목과 본문을 빈행으로 분리
            - 커밋 유형 이후 제목과 본문은 한글로 작성하여 내용이 잘 전달될 수 있도록 할 것
            - 본문에는 변경한 내용과 이유 설명 (어떻게보다는 무엇 & 왜를 설명)

    ↩️ 3. 제목은 영문 기준 50자 이내로 할 것

    ⏺️ 4. 자신의 코드가 직관적으로 바로 파악할 수 있다고 생각하지 말자

    ```

<br>

## 페이지 미리보기



<div align="center">
    <div>
        <b>약관 동의</b>
    </div>
    <p align="center">
        <img src="https://github.com/pre-dev-team/gym_crm_back/assets/100101089/1924db1d-3492-4ec3-b191-5020e3d8f2cd" width="70%" />
    </p>
    <br>
</div>

<div align="center">
    <div>
        <b>약관동의 이후 회원가입</b>
    </div>
    <p align="center">
        <img src="https://github.com/pre-dev-team/gym_crm_back/assets/100101089/8f5e80ed-e851-4c47-818d-e8e83d121afa" width="70%" />
    </p>
    <br>
</div>

<div align="center">
    <div>
        <b>아이디 찾기</b>
    </div>
    <p align="center">
        <img src="https://github.com/pre-dev-team/gym_crm_back/assets/100101089/2336e127-c7fc-437d-ab56-eee9e417869a" width="70%"/>
    </p>
    <br>
</div>

<div align="center">
    <div>
        <b>회원 비밀번호 변경</b>
    </div>
    <p align="center">
        <img src="https://github.com/pre-dev-team/gym_crm_back/assets/100101089/45b83ad7-50ab-4e01-9436-0c780be15e85" width="70%" />
    </p>
    <br>
</div>

<div align="center">
    <div>
        <b>회원 루틴 확인하기</b>
    </div>
    <p align="center">
        <img src="https://github.com/pre-dev-team/gym_crm_back/assets/100101089/cef44046-2a3b-40c2-88d1-af179b4b7daa" width="70%" />
    </p>
    <br>
</div>

<div align="center">
    <div>
        <b>회원 본인 인바디 조회</b>
    </div>
    <p align="center">
        <img src="https://github.com/pre-dev-team/gym_crm_back/assets/100101089/41cf21b0-0543-44d3-903d-87de7fdea4d8" width="70%" />
    </p>
    <br>
</div>

<div align="center">
    <div>
        <b>회원 트레이너 바로 예약</b>
    </div>
    <p align="center">
        <img src="https://github.com/pre-dev-team/gym_crm_back/assets/100101089/ed57d193-dd78-4a8f-a927-1e53766edae4" width="70%" />
    </p>
    <br>
</div>

<div align="center">
    <div>
        <b>회원 트레이너 바로 예약</b>
    </div>
    <p align="center">
        <img src="https://github.com/pre-dev-team/gym_crm_back/assets/100101089/d02f0e9d-dbc4-46e5-bec3-fdd39b0b81df" width="70%" />
    </p>
    <br>
</div>


<div align="center">
    <div>
        <b>트레이너 페이지 렌더링</b>
    </div>
    <p align="center">
        <img src="https://github.com/pre-dev-team/gym_crm_back/assets/100101089/d84774bb-6037-40fa-864a-28ffae86cb62" width="70%" />
    </p>
    <br>
</div>




<div align="center">
    <div>
        <b>트레이너 연차조회 승인</b>
    </div>
    <p align="center">
        <img src="https://github.com/pre-dev-team/gym_crm_back/assets/100101089/e6ab3117-58c7-432a-9cd0-89ab66e0e938" width="70%" />
    </p>
    <br>
</div>

<div align="center">
    <div>
        <b>트레이너 연차조회 반려</b>
    </div>
    <p align="center">
        <img src="https://github.com/pre-dev-team/gym_crm_back/assets/100101089/2651ec31-44a4-4afc-a73a-2e539f301f9a" width="70%" />
    </p>
    <br>
</div>

<div align="center">
    <div>
        <div><b>트레이너 연차 취소</b></div>
    </div>
    <p align="center">
        <img src="https://github.com/pre-dev-team/gym_crm_back/assets/100101089/7f951423-51d2-41db-8f7e-8518a3cc793a" width="70%" />
    </p>
    <br>
</div>

<div align="center">
    <div>
        <b>트레이너 이미 등록된 연차</b>
    </div>
    <p align="center">
        <img src="https://github.com/pre-dev-team/gym_crm_back/assets/100101089/eddfcfac-5d7d-41a4-9e1d-ae61ba9b58c7" width="70%" />
    </p>
    <br>
</div>

<div align="center">
    <div>
        <b>트레이너 나의 예약 검색</b>
    </div>
    <p align="center">
        <img src="https://github.com/pre-dev-team/gym_crm_back/assets/100101089/301a5d53-15a1-4019-9579-92a9914c1298" width="70%" />
    </p>
    <br>
</div>

<div align="center">
    <div>
        <b>어드민 페이지 렌더링</b>
    </div>
    <p align="center">
        <img src="https://github.com/pre-dev-team/gym_crm_back/assets/100101089/38883228-4f7e-42b5-839b-f2bceb1ce003" width="70%" />
    </p>
    <br>
</div>

<div align="center">
    <div>
        <b>트레이너 연차 승인</b>
    </div>
    <p align="center">
        <img src="https://github.com/pre-dev-team/gym_crm_back/assets/100101089/b402b994-4f05-4361-b14b-942f3d267dc0" width="70%" />
    </p>
    <br>
</div>

<div align="center">
    <div>
        <b>어드민 페이지 렌더링</b>
    </div>
    <p align="center">
        <img src="https://github.com/pre-dev-team/gym_crm_back/assets/100101089/38883228-4f7e-42b5-839b-f2bceb1ce003" width="70%" />
    </p>
    <br>
</div>

<div align="center">
    <div>
        <b>어드민-트레이너 퇴사</b>
    </div>
    <p align="center">
        <img src="https://github.com/pre-dev-team/gym_crm_back/assets/100101089/2bdb18e2-a091-45f8-bb56-0d10346161d7" width="70%" />
    </p>
    <br>
</div>

<div align="center">
    <div>
        <b>어드민-트레이너 연차 승인 반려</b>
    </div>
    <p align="center">
        <img src="https://github.com/pre-dev-team/gym_crm_back/assets/100101089/efa04a09-7c9c-43b8-bed0-865b1817016a" width="70%" />
    </p>
    <br>
</div>

<div align="center">
    <div>
        <b>어드민-회원 작성 리뷰 조회</b>
    </div>
    <p align="center">
        <img src="https://github.com/pre-dev-team/gym_crm_back/assets/100101089/920bed4e-a60c-4e07-9475-7cae3ad357f4" width="70%" />
    </p>
    <br>
</div>

<div align="center">
    <div>
        <b>어드민-트레이너 연차 조회</b>
    </div>
    <p align="center">
        <img src="https://github.com/pre-dev-team/gym_crm_back/assets/100101089/1665e9ec-a5ae-41dc-a7c9-b412b169bb4c" width="70%" />
    </p>
    <br>
</div>


<div align="center">
    <div>
        <b>어드민 전체 예약 조회 트레이너 이름으로 검색</b>
    </div>
    <p align="center">
        <img src="https://github.com/pre-dev-team/gym_crm_back/assets/100101089/bae4f5c2-2969-4b44-8d54-edaea7ef0c38" width="70%" />
    </p>
    <br>
</div>


<div align="center">
    <div>
        <b>어드민 전체 예약 조회 회원 이름으로 검색</b>
    </div>
    <p align="center">
        <img src="https://github.com/pre-dev-team/gym_crm_back/assets/100101089/accaab65-0af4-43d2-9dcd-176ae84548cd" width="70%" />
    </p>
    <br>
</div>


<div align="center">
    <div>
        <div><b>어드민 - 전체 회원 명단조회</b></div>
    </div>
    <p align="center">
        <img src="https://github.com/pre-dev-team/gym_crm_back/assets/100101089/e62867f2-2313-44a6-a960-3174d0a53162" width="70%" />
    </p>
    <br>
</div>

## 로컬 환경에서 프로젝트 구동 - 백엔드

- 레포지토리 클론
```
git clone 
```
- 프로젝트 소스 코드 폴더로 이동
```
cd my-app
```
- maven 빌드 진행
```
./mvnw clean package -Dtestskip
```
- 백그라운드에서 실행
```
nohup java -jar ~.jar &
```

## 로컬 환경에서 프로젝트 구동 - 프론트

- 레포지토리 클론
```
git clone 
```
- 프로젝트 소스 코드 폴더로 이동
```
cd my-app
```
- 필요한 모듈 설치
```
npm install
```
- 로컬 환경에서 리액트 앱을 실행
```
npm start
```

<p align="right"><a href="#top">TOP 🔼</a></p>