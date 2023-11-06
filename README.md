# sparta-anonymous-board

## 설계
[노션 링크](https://lycoris62.notion.site/Spring-494a7848f9e947599ddf97a91ff9749c?pvs=4)

## 디렉토리 구조 
```
├── java
│   └── sparta
│       └── spartaboard
│           ├── SpartaboardApplication.java
│           ├── domain                                                    // 도메인 별로 디렉토리를 구분 
│           │   ├── model                                                 // 모든 도메인에 쓰이는 엔티티 집합 
│           │   │   └── BaseEntity.java
│           │   └── post                                                  // 게시글 도메인 디렉토리 
│           │       ├── controller
│           │       │   └── PostController.java
│           │       ├── dto
│           │       │   ├── request
│           │       │   │   ├── PostCreateRequestDto.java                 // 게시글 생성 요청 DTO
│           │       │   │   └── PostEditRequestDto.java                   // 게시글 수정 요청 DTO
│           │       │   └── response
│           │       │       ├── PostCreateResponseDto.java                // 게시글 생성 성공 응답 DTO
│           │       │       ├── PostDetailResponseDto.java                // 게시글 세부 정보 응답 DTO (게시글 단일 조회용)
│           │       │       └── PostPreviewResponseDto.java               // 게시글 단순 정보 응답 DTO (게시글 목록 조회용) 
│           │       ├── entity
│           │       │   └── Post.java
│           │       ├── repository
│           │       │   └── PostRepository.java
│           │       └── service
│           │           └── PostService.java
│           └── global                                                    // 전반적으로 쓰이는 디렉토리 집합 
│               ├── error                                                 
│               │   ├── ErrorCode.java                                    // 에러 코드를 ENUM 으로 응집 
│               │   ├── ErrorResponse.java                                // 에러 발생시 반환되는 DTO 
│               │   ├── GlobalExceptionHandler.java                       // 에러 핸들링 컨트롤러  
│               │   └── exception                                         
│               │       ├── InvalidPasswordException.java                 // 잘못된 비밀번호 입력 시 발생 예외 
│               │       ├── PostNotFoundException.java                    // 잘못된 게시글 접근 시 발생 예외 
│               │       └── ServerErrorException.java                     // 내부 서버 오류 시 발생 예외 
│               └── security                                               
│                   └── PasswordEncoder.java                              // 비밀번호 암호화 담당 객체 
└── resources
    ├── application.yml
    ├── static
    └── templates
```

## 유스케이스 다이어그램 
![스파르타_익명_게시판](https://github.com/lycoris62/sparta-anonymous-board/assets/55584664/897dbb47-8012-4014-8adc-edbea3f030d8)

## API 명세서
[POSTMAN 명세서](https://documenter.getpostman.com/view/16720681/2s9YXfa2v8)    

<br />

<img width="454" alt="스크린샷 2023-11-05 오후 5 43 25" src="https://github.com/lycoris62/sparta-anonymous-board/assets/55584664/7f0eb9a5-118d-4b59-8d02-3188881780dd">

## ERD
[ERD CLOUD 링크](https://www.erdcloud.com/d/SXMnq2BfAkKfycpKq)    

<br />

![SpartaBoard](https://github.com/lycoris62/sparta-anonymous-board/assets/55584664/3570f26e-68fd-413e-8068-58b38b38a282)
