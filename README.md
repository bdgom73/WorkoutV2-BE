# [Backend] Workout Application

### ⚫ 목차
1. [회원관리](#1.-회원관리)
2. [신체데이터관리](#2.-신체데이터관리)
3. [운동관리](#3.-운동관리)
4. [루틴관리](#4.-루틴관리)
5. [볼륨관리](#5.-볼륨관리)


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
    "email" : "user email",
    "password" : "user password"
}
```

* RETURN
```JSON
{
    "data" : "TOKEN VALUE"
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
    "email" : "email",
    "password" : "valid password",
    "password2" : "valid password check",
    "name" : "name",
    "nickname" : "nickname"
}
```

* RETURN
```JSON
{
    "data" : "USER ID"
}
```

### 1-3 로그인 유저 정보
* URL
```TEXT
GET /api/member
```
* Request Headers

```http
Authorization : TOKEN
```

* RETURN
```JSON
{
    "data" : {
        "memberId" : "memberId",
        "email": "email",
        "name" : "name",
        "nickname" : "nickname",
        "bodyDataId" : "bodyDataId",
        "age" : 20,
        "height" : 172.3,
        "weight : 62.3
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
            "memberId" : "memberId",
            "email": "email",
            "name" : "name",
            "nickname" : "nickname",
            "bodyDataId" : "bodyDataId",
            "age" : 20,
            "height" : 172.3,
            "weight" : 62.3
        },
        .
        .
        .
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
        "memberId" : "memberId",
        "email": "email",
        "name" : "name",
        "nickname" : "nickname",
        "bodyDataId" : "bodyDataId",
        "age" : 20,
        "height" : 172.3,
        "weight" : 62.3
    }
}
```

### 1-6 유저 수정
* URL
```TEXT
PUT /api/members/{memberId}
```
* Request Headers

```http
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
        "memberId" : "memberId",
        "email": "email",
        "name" : "name",
        "nickname" : "nickname",
        "bodyDataId" : "bodyDataId",
        "age" : 20,
        "height" : 172.3,
        "weight" : 62.3
    }
}
```

### 1-7 유저 아바타 추가 및 수정
* URL
```TEXT
POST /api/members/avatar
```
* Request Headers

```http
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
        "memberId" : "memberId",
        "email": "email",
        "name" : "name",
        "nickname" : "nickname",
        "bodyDataId" : "bodyDataId",
        "age" : 20,
        "height" : 172.3,
        "weight" : 62.3
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

```http
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
            "bodyDataId" : "bodyDataId",
            "age" : 20,
            "height" : 172.3,
            "weight" : 62.3
        },
        .
        .
        .
    ]
}
```
### 2-2 신체 데이터 상세보기
* URL
```TEXT
GET /api/body-data/{bodyDataId}
```
<!-- * Request Headers

```http
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
        "bodyDataId" : "bodyDataId",
        "age" : 20,
        "height" : 172.3,
        "weight" : 62.3
    }
}
```
### 2-3 최근 신체 데이터 보기
* URL
```TEXT
GET /api/body-data/latest
```
* Request Headers

```http
Authorization : TOKEN
```

* RETURN
```JSON
{
    "data" : {
        "bodyDataId" : "bodyDataId",
        "age" : 20,
        "height" : 172.3,
        "weight" : 62.3
    }
}
```
### 2-4 신체 데이터 추가
* URL
```TEXT
POST /api/body-data
```
* Request Headers

```http
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
    "age" : "user age",
    "height" : "user height",
    "weight" : "user weight"
}
```

* RETURN
```JSON
{
    "data" : {
        "bodyDataId" : "bodyDataId",
        "age" : 20,
        "height" : 172.3,
        "weight" : 62.3
    }
}
```
### 2-5 신체 데이터 수정
* URL
```TEXT
PUT /api/body-data/{bodyDataId}
```
<!-- * Request Headers

```http
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
    "age" : "user age",
    "height" : "user height",
    "weight" : "user weight"
}
```

* RETURN
```JSON
{
    "data" : {
        "bodyDataId" : "bodyDataId",
        "age" : 20,
        "height" : 172.3,
        "weight" : 62.3
    }
}
```
### 2-6 신체 데이터 삭제
* URL
```TEXT
DELETE /api/body-data/{bodyDataId}
```
* Request Headers

```http
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
            "explanation" : "설명",
        },
        .
        .
        .
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
        "explanation" : "설명",
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
        "explanation" : "설명",
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

## 5. 볼륨관리