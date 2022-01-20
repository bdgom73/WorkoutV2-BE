# [Backend] Workout Application V2
### 수정 전 Backend -> [V1 이동](https://github.com/bdgom73/workoutApp)
### ⚫ 목차
1. [회원관리](#1-회원관리)
2. [신체데이터관리](#2-신체데이터관리)
3. [운동관리](#3-운동관리)
4. [루틴관리](#4-루틴관리)
5. [볼륨관리](#5-볼륨관리)
6. [예외](#6-예외)

## 1. 회원관리
### 1-1 로그인
* URL
```TEXT
POST /api/login
```
* PARAMETER
  
|파라미터|타입|필수여부|설명|
|-----|---|---|---|
|email|String|true|로그인 이메일 입니다|
|password|String|true|로그인 암호 입니다|

```JSON
{
    "email" : "유저 이메일",
    "password" : "유저 비밀번호"
}
```

* RETURN
```JSON
{
    "data" : "토큰"
}
```

### 1-2 회원가입
* URL
```TEXT
POST /api/join
```
* PARAMETER

|파라미터|타입|필수여부|설명|
|-----|---|---|---|
|email|String|true|회원가입 이메일 입니다|
|password|String|true|회원가입 암호 입니다|
|password2|String|true|회원가입 확인 암호 입니다|
|name|String|true|회원가입 이름 입니다|
|nickname|String|true|회원가입 별명 입니다|

```JSON
{
    "email" : "이메일",
    "password" : "비밀번호",
    "password2" : "비밀번호 확인",
    "name" : "이름",
    "nickname" : "별명"
}
```

* RETURN
```JSON
{
    "data" : "유저 고유 아이디"
}
```

### 1-3 로그인 유저 정보
* URL
```TEXT
GET /api/member
```
* Request Headers

```TEXT
Authorization : TOKEN
```

* RETURN
```JSON
{
    "data" : {
        "memberId" : "유저 고유 아이디",
        "email": "이메일",
        "name" : "이름",
        "nickname" : "별명",
        "bodyDataId" : "신체 데이터 고유 아이디",
        "age" : "나이",
        "height" : "키",
        "weight" : "몸무게"
    }
}
```

### 1-4 유저 리스트
* URL
```TEXT
GET /api/members
```

* PARAMETER

|파라미터|타입|필수여부|기본값|설명|
|-----|---|---|---|-----|
|page|int|false|0|현재 페이지 입니다.|
|size|int|false|10|한 페이지당 가져올 데이터 개수입니다.|
|sort|String|false|id|정렬 기준 값입니다.|
|direction|String|false|desc|오름차순, 내림차순|
```TEXT
?page=0&size=10&sort=id&direction=desc
```

* RETURN
```JSON
{
    "data" : [
        {
            "memberId" : "유저 고유 아이디",
            "email": "이메일",
            "name" : "이름",
            "nickname" : "별명",
            "bodyDataId" : "신체 데이터 고유 아이디",
            "age" : "나이",
            "height" : "키",
            "weight" : "몸무게"
        },
        "more..."
    ]
}
```

### 1-5 유저 상세보기
* URL
```TEXT
GET /api/members/{memberId}
```

* PARAMETER

|파라미터|타입|필수여부|설명|
|-----|---|---|---|
|memberId|Long|true|특정 유저 아이디|

* RETURN
```JSON
{
    "data" : {
        "memberId" : "유저 고유 아이디",
        "email": "이메일",
        "name" : "이름",
        "nickname" : "별명",
        "bodyDataId" : "신체 데이터 고유 아이디",
        "age" : "나이",
        "height" : "키",
        "weight" : "몸무게"
    }
}
```

### 1-6 유저 수정
* URL
```TEXT
PUT /api/members/{memberId}
```
* Request Headers

```TEXT
Authorization : TOKEN
```
* PARAMETER

|파라미터|타입|필수여부|설명|
|-----|---|---|---|
|memberId|Long|true|특정 유저 아이디|
|---|---|---|---|
|name|String|true|변경할 이름|
|nickname|String|true|변경할 닉네임|
```JSON
{
    "name" : "change name",
    "nickname" : "change nickname"
}
```
* RETURN
```JSON
{
    "data" : {
      "memberId" : "유저 고유 아이디",
      "email": "이메일",
      "name" : "이름",
      "nickname" : "별명",
      "bodyDataId" : "신체 데이터 고유 아이디",
      "age" : "나이",
      "height" : "키",
      "weight" : "몸무게"
    }
}
```

### 1-7 유저 아바타 추가 및 수정
* URL
```TEXT
POST /api/members/avatar
```
* Request Headers

```TEXT
Authorization : TOKEN
```
* PARAMETER

|파라미터|타입|필수여부|설명|
|-----|---|---|---|
|avatar|MultipartFile|true|아바타 파일 정보|

```TEXT
mulipart/form-data {
    avatar : IMAGE_FILE
}
```
* RETURN
```JSON
{
    "data" : {
      "memberId" : "유저 고유 아이디",
      "email": "이메일",
      "name" : "이름",
      "nickname" : "별명",
      "bodyDataId" : "신체 데이터 고유 아이디",
      "age" : "나이",
      "height" : "키",
      "weight" : "몸무게"
    }
}
```
## 2. 신체데이터관리
### 2-1 신체 데이터 리스트
* URL
```TEXT
GET /api/body-data
```
<!-- * Request Headers

```TEXT
Authorization : TOKEN
``` -->
* PARAMETER

|파라미터|타입|필수여부|설명|
|-----|---|---|---|
|page|int|false|현재 페이지 입니다.|
|size|int|false|한 페이지당 가져올 데이터 개수입니다.|
```TEXT
?page=0&size=10
```

* RETURN
```JSON
{
    "data" : [
        {
            "bodyDataId" : "신체 데이터 고유 아이디",
            "age" : "나이",
            "height" : "키",
            "weight" : "몸무게"
        },
        "more..."
    ]
}
```
### 2-2 신체 데이터 상세보기
* URL
```TEXT
GET /api/body-data/{bodyDataId}
```
<!-- * Request Headers

```TEXT
Authorization : TOKEN
``` -->
* PARAMETER

|파라미터|타입|필수여부|설명|
|-----|---|---|---|
|bodyDataId|Long|true|바디데이터 고유 아이디값|

* RETURN
```JSON
{
    "data" : {
      "bodyDataId" : "신체 데이터 고유 아이디",
      "age" : "나이",
      "height" : "키",
      "weight" : "몸무게"
    }
}
```
### 2-3 최근 신체 데이터 보기
* URL
```TEXT
GET /api/body-data/latest
```
* Request Headers

```TEXT
Authorization : TOKEN
```

* RETURN
```JSON
{
    "data" : {
      "bodyDataId" : "신체 데이터 고유 아이디",
      "age" : "나이",
      "height" : "키",
      "weight" : "몸무게"
    }
}
```
### 2-4 신체 데이터 추가
* URL
```TEXT
POST /api/body-data
```
* Request Headers

```TEXT
Authorization : TOKEN
```
* PARAMETER

|파라미터|타입|필수여부|설명|
|-----|---|---|---|
|age|int|true|유저의 나이|
|height|int|true|유저의 키|
|weight|int|true|유저의 몸무게|
```JSON
{
    "age" : "나이",
    "height" : "키",
    "weight" : "몸무게"
}
```

* RETURN
```JSON
{
    "data" : {
      "bodyDataId" : "신체 데이터 고유 아이디",
      "age" : "나이",
      "height" : "키",
      "weight" : "몸무게"
    }
}
```
### 2-5 신체 데이터 수정
* URL
```TEXT
PUT /api/body-data/{bodyDataId}
```
<!-- * Request Headers

```TEXT
Authorization : TOKEN
``` -->
* PARAMETER

|파라미터|타입|필수여부|설명|
|-----|---|---|---|
|bodyDataId|Long|true|수정할 신체 데이터 고유아이디|
|age|int|true|수정할 유저의 나이|
|height|int|true|수정할 유저의 키|
|weight|int|true|수정할 유저의 몸무게|
```JSON
{
  "age" : "나이",
  "height" : "키",
  "weight" : "몸무게"
}
```

* RETURN
```JSON
{
    "data" : {
      "bodyDataId" : "신체 데이터 고유 아이디",
      "age" : "나이",
      "height" : "키",
      "weight" : "몸무게"
    }
}
```
### 2-6 신체 데이터 삭제
* URL
```TEXT
DELETE /api/body-data/{bodyDataId}
```
* Request Headers

```TEXT
Authorization : TOKEN
```
* PARAMETER

|파라미터|타입|필수여부|설명|
|-----|---|---|---|
|bodyDataId|Long|true|수정할 신체 데이터 고유아이디|

## 3. 운동관리
### 3-1 운동 리스트
* URL
```TEXT
GET /api/workouts
```
* PARAMETER

|파라미터|타입|필수여부|기본값|설명|
|-----|---|---|---|-----|
|page|int|false|0|현재 페이지 입니다.|
|size|int|false|10|한 페이지당 가져올 데이터 개수입니다.|
|sort|String|false|id|정렬 기준 값입니다.|
|direction|String|false|aes|오름차순, 내림차순 (aes/desc)|
```TEXT
?page=0&size=10&sort=id&direction=aes
```

* RETURN
```JSON
{
    "data" : [
        {
            "workoutId" : "운동 고유 아이디",
            "name" : "운동명",
            "imageUrl" : "이미지 url",
            "part" : "운동부위",
            "type" : "무산소 / 유산소",
            "explanation" : "설명"
        },
        "more..."
    ]
}
```
### 3-2 운동 상세정보
* URL
```TEXT
GET /api/workouts/{workoutId}
```
* PARAMETER

|파라미터|타입|필수여부|설명|
|-----|---|---|---|
|workoutId|Long|true|운동 고유 아이디입니다|

* RETURN
```JSON
{
    "data" : {
        "workoutId" : "운동 고유 아이디",
        "name" : "운동명",
        "imageUrl" : "이미지 url",
        "part" : "운동부위",
        "type" : "무산소 or 유산소",
        "explanation" : "설명"
    }
}
```
### 3-3 운동 추가
* URL
```TEXT
POST /api/workouts
```
* PARAMETER

|파라미터|타입|필수여부|설명|
|-----|---|---|---|
|name|String|true|운동 이름|
|part|String|true|운동 부위|
|type|String|true|유/무산소 구분|
```JSON
{
    "name" : "운동이름",
    "part" : "운동부위",
    "type" : "유산소 or 무산소"
}
```
* RETURN
```JSON
{
    "data" : "운동 고유 아이디"
}
```
### 3-4 운동 수정
* URL
```TEXT
PUT /api/workouts/{workoutId}
```
* PARAMETER

|파라미터|타입|필수여부|설명|
|-----|---|---|---|
|workoutId|Long|true|운동 고유 아이디|
|name|String|true|운동 이름|
|part|String|true|운동 부위|
|type|String|true|유/무산소 구분|
|explanation|String|true|운동 설명|

```JSON
{
    "name" : "운동이름",
    "part" : "운동부위",
    "type" : "유산소 or 무산소",
    "explanation" : "운동 설명"
}
```
* RETURN
```JSON
{
    "data" : {
        "workoutId" : "운동 고유 아이디",
        "name" : "운동명",
        "imageUrl" : "이미지 url",
        "part" : "운동부위",
        "type" : "무산소 or 유산소",
        "explanation" : "설명"
    }
}
```
### 3-5 운동 삭제
* URL
```TEXT
DELETE /api/workouts/{workoutId}
```
* PARAMETER

|파라미터|타입|필수여부|설명|
|-----|---|---|---|
|workoutId|Long|true|운동 고유 아이디|

## 4. 루틴관리
### 4-1 루틴 리스트
* URL
```TEXT
GET /api/routines
```
* PARAMETER

|파라미터|타입|필수여부|기본값|설명|
|-----|---|---|---|-----|
|page|int|false|0|현재 페이지 입니다.|
|size|int|false|10|한 페이지당 가져올 데이터 개수입니다.|
|sort|String|false|createDate|정렬 기준 값입니다.|
|direction|String|false|aes|오름차순, 내림차순 (aes/desc)|
```TEXT
?page=0&size=10&sort=createDate&direction=aes
```
* RETURN
```JSON
{
    "data" : [
        {
            "routineId" : "루틴 고유 아이디",
            "title" : "루틴제목",
            "part" : "운동부위",
            "share" : "공유여부",
            "originalAuthor" : "루틴 제작자",
            "memberId" : "루틴 사용 유저 고유 아이디",
            "memberNickname" : "유저 별명"
        },
        "more..."
    ]
}
```
### 4-2 루틴 상세보기
* URL
```TEXT
GET /api/routines/{routineId}
```
* PARAMETER

|파라미터|타입|필수여부|설명|
|-----|---|---|---
|routineId|Long|true|루틴 고유 아이디|
* RETURN
```JSON
{
    "data" : {
        "routineId" : "루틴 고유 아이디",
        "title" : "루틴제목",
        "part" : "운동부위",
        "share" : "공유여부",
        "originalAuthor" : "루틴 제작자",
        "memberId" : "루틴 사용 유저 고유 아이디",
        "memberNickname" : "유저 별명"
    }
        
    
}
```
### 4-3 루틴 추가
* URL
```TEXT
POST /api/routines
```
* Request Headers

```TEXT
Authorization : TOKEN
```
* PARAMETER

|파라미터|타입|필수여부|설명|
|-----|---|---|---|
|title|String|true|루틴의 제목|
|part|String|true|루틴의 주요 운동부위|
|share|boolean|true|루틴 공유 여부|
|**[volumes](#Volumes)**|List|true|볼륨값 리스트|

#### Volumes

|파라미터|타입|필수여부|설명|
|-----|---|---|---|
|num|int|true|운동의 횟수|
|sets|int|true|운동의 세트수|
|workoutId|Long|true|운동의 고유아이디|

* RETURN
```JSON
{
    "data" : "루틴의 고유 아이디"
}
```
### 4-4 루틴 수정
* URL
```TEXT
PUT /api/routines/{routineId}
```
* Request Headers

```TEXT
Authorization : TOKEN
```
* PARAMETER

|파라미터|타입|필수여부|설명|
|-----|---|---|---|
|routineId|String|true|루틴의 고유 아이디|
|title|String|true|루틴의 제목|
|part|boolean|true|루틴 주요 운동부위|

* RETURN
```JSON
{
    "data" : "루틴의 고유 아이디"
}
```
### 4-5 루틴 삭제
* URL
```TEXT
DELETE /api/routines/{routineId}
```
* Request Headers

```TEXT
Authorization : TOKEN
```
* PARAMETER

|파라미터|타입|필수여부|설명|
|-----|---|---|---|
|routineId|String|true|루틴의 고유 아이디|

### 4-6 공유 루틴 리스트
* URL
```TEXT
GET /api/routines/share
```
* PARAMETER

|파라미터|타입|필수여부|기본값|설명|
|-----|---|---|---|-----|
|page|int|false|0|현재 페이지 입니다.|
|size|int|false|10|한 페이지당 가져올 데이터 개수입니다.|
|sort|String|false|createDate|정렬 기준 값입니다.|
|direction|String|false|AES|오름차순, 내림차순 (AES/DESC)|
```TEXT
?page=0&size=10&sort=createDate&direction=aes
```

* RETURN
```JSON
{
    "data" : [
        {
          "routineId" : "루틴 고유 아이디",
          "title" : "루틴제목",
          "part" : "운동부위",
          "share" : true,
          "originalAuthor" : "루틴 제작자",
          "memberId" : "루틴 사용 유저 고유 아이디",
          "memberNickname" : "유저 별명"
        },
        "more..."
    ]
}
```
### 4-7 루틴 복사
* URL
```TEXT
POST /api/routines/{routineId}/copy
```
* Request Headers

```TEXT
Authorization : TOKEN
```
* PARAMETER

|파라미터|타입|필수여부|설명|
|-----|---|---|---|
|routineId|String|true|루틴의 고유 아이디|

* RETURN
```JSON
{
    "data" : "루틴의 고유 아이디"
}
```
### 4-8 루틴 추천
* URL
```TEXT
POST /api/routines/{routineId}/recommend
```
* Request Headers

```TEXT
Authorization : TOKEN
```
* PARAMETER

|파라미터|타입|필수여부|설명|
|-----|---|---|---|
|routineId|String|true|루틴의 고유 아이디|

### 4-9 루틴 추천 취소
* URL
```TEXT
DELETE /api/routines/{routineId}/recommend
```
* Request Headers

```TEXT
Authorization : TOKEN
```
* PARAMETER

|파라미터|타입|필수여부|설명|
|-----|---|---|---|
|routineId|String|true|루틴의 고유 아이디|

## 5. 볼륨관리
### 5-1 해당 루틴의 볼륨 가져오기
* URL
```TEXT
GET /api/volumes
```
* PARAMETER

|파라미터|타입|필수여부|설명|
|-----|---|---|---|
|routineId|Long|true|루틴의 고유 아이디|
```
?routineId=1
```
* RETURN
```JSON
{
  "data": [
      {
        "volumeId" : "볼륨 고유 아이디",
        "num" : "운동 횟수",
        "sets" : "운동 세트수",
        "workoutId" : "운동 고유 아이디",
        "workoutName" : "운동 이름",
        "workoutImageUrl" : "운동 이미지",
        "workoutPart" : "운동 부위",
        "workoutType" : "유산소 or 무산소",
        "routineId" : "루틴 고유 아이디"
      },
      "more..."
    ]
}
```
### 5-2 볼륨 상세보기
* URL
```TEXT
GET /api/volumes/{volumeId}
```
* PARAMETER

|파라미터|타입|필수여부|설명|
|-----|---|---|---|
|volumeId|Long|true|볼륨의 고유 아이디|

* RETURN
```JSON
{
  "data": {
    "volumeId" : "볼륨 고유 아이디",
    "num" : "운동 횟수",
    "sets" : "운동 세트수",
    "workoutId" : "운동 고유 아이디",
    "workoutName" : "운동 이름",
    "workoutImageUrl" : "운동 이미지",
    "workoutPart" : "운동 부위",
    "workoutType" : "유산소 or 무산소",
    "routineId" : "루틴 고유 아이디"
  }
      
    
}
```
### 5-3 볼륨 추가
* URL
```TEXT
POST /api/volumes
```
* PARAMETER

|파라미터|타입|필수여부|설명|
|-----|---|---|---|
|routineId|Long|true|루틴의 고유 아이디|
|workoutId|Long|true|운동의 고유 아이디|
|num|int|true|운동의 횟수|
|sets|int|true|운동의 세트수|
### 5-4 볼륨 수정
* URL
```TEXT
PUT /api/volumes/{volumeId}
```
* PARAMETER

|파라미터|타입|필수여부|설명|
|-----|---|---|---|
|volumeId|Long|true|볼륨의 고유 아이디|
|workoutId|Long|true|운동의 고유 아이디|
|num|int|true|운동의 횟수|
|sets|int|true|운동의 세트수|

* RETURN
```JSON
{
  "data": "볼륨의 고유 아이디"
}
```
### 5-5 볼륨 삭제
* URL
```TEXT
DELETE /api/volumes/{volumeId}
```
* PARAMETER

|파라미터|타입|필수여부|설명|
|-----|---|---|---|
|volumeId|Long|true|볼륨의 고유 아이디|

## 6. 예외
### 예외 스펙

|파라미터|타입|설명|
|-----|---|---|
|timestamp|LocalDateTime|예외 발생 시간|
|status|int|HTTP 상태 코드|
|error|String|HTTP 상태 메시지|
|path|String|예외 발생 URI|
|message|String|예외 메시지|
|exception|String|예외 종류|

* ex) 유저를 찾을 수 없는 경우
```JSON
{
  "timestamp": "2022-01-20T15:14:16.6031399",
  "status": 400,
  "error": "BAD_REQUEST",
  "path": "/api/members/5",
  "message": "유저를 찾을 수 없습니다",
  "exception": "java.lang.IllegalStateException"
}
```