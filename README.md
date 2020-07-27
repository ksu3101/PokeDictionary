# PokeDictionary  

## 1. used libraries

사용한 외부 라이브러리는 아래와 같습니다. 

- [kotlin](https://mvnrepository.com/artifact/org.jetbrains.kotlin/kotlin-stdlib-jdk7)
- androidx
  - [appcompat](https://developer.android.com/jetpack/androidx/releases/appcompat?hl=ko)
  - [recyclerView](https://developer.android.com/jetpack/androidx/releases/recyclerview?hl=ko)
  - [architecture lifecycle](https://developer.android.com/jetpack/androidx/releases/lifecycle?hl=ko)
  - [navigation](https://developer.android.com/jetpack/androidx/releases/navigation?hl=ko)
  - [ktx core](https://developer.android.com/jetpack/androidx/releases/core?hl=ko)
  - [ktx-fragment](https://developer.android.com/jetpack/androidx/releases/fragment?hl=ko)
- [google_map](https://developers.google.com/maps/documentation/android-sdk/start)
- [constraintlayout](https://developer.android.com/jetpack/androidx/releases/constraintlayout?hl=ko)
- [multidex](https://developer.android.com/studio/build/multidex?hl=ko)
- [rxandroid](https://github.com/ReactiveX/RxAndroid)
- [Koin](https://github.com/InsertKoinIO/koin)
- [retrofit](https://square.github.io/retrofit/)
- [okhttp](https://square.github.io/okhttp/)
- [moshi](https://github.com/square/moshi)
- [glide](https://github.com/bumptech/glide)

## 2. Modules 

아래와 같은 목적으로 `app`모듈을 분리 하였습니다. 

1. **비즈니스 로직 수행 코드**와 **뷰 로직 수행 코드**의 분리
2. 각 모듈 단위의 테스트 수행

각 모듈별 관계는 아래와 같습니다. 

> app > model > common

- `app`모듈은 `common`, `model`모듈들을 알고 있습니다. 
- `model`모듈은 `common`모듈만 알고 있습니다. 
- `common`모듈은 다른 모듈과 관계를 갖고 있지 않습니다. 

### 2.1 `app` 모듈 

`app`모듈의 목적은 기존 `app`과 동일 하지만 비즈니스 로직을 제거 하고, 안드로이드 컴포넌트에 의존을 갖는 코드들이 존재 합니다.  

#### 2.1.1 `base` 패키지

- 공통적으로 사용 되는 클래스, 함수의 구현 및 확장 함수
- databinding에 사용 될 공통 binding adapter 함수
- `BaseFragment` 클래스
- `Koin` 컴포넌트들 정의
- `model`모듈 에서 `app`모듈에 의존을 갖는 리소스, 모듈 등에 접근 하기 위한 `Helper`인터페이스들의 구현 클래스. 

#### 2.1.2 `repositories` 패키지

- `Retrofit`에서 사용 될 `api`인터페이스 정의
- `model`모듈에서 네트워크 모듈을 사용하기 위한 `Repository`인터페이스의 구현 클래스. (보통 인터페이스 이름 뒤에 `impl`을 붙임)

#### 2.1.3 `view` 패키지 

- 각 도메인 별 `Activity, Fragment` 구현  
- 안드로이드 컴포넌트, 뷰 에 의존을 갖는 클래스들
  - ex) `ListAdapter, ViewHolder, DiffUtil.Callback`등 의 구현. data binding adapter 함수 

#### 2.1.4 `resource`

- `Navigation` graph xml 파일
- layout, drawable 등 xml리소스 파일  

### 2.2 `model` 모듈 

`model`모듈의 목적은 각 도메인별 비즈니스 로직의 수행과 `ViewModel`의 구현 및 관리 입니다.   
비즈니스 로직의 수행을 위해 관련된 기본, 공통모듈들이 구현 되어 있습니다. 

#### 2.2.1 `base` 패키지

- 각 서브 도메인에서 `app`모듈에대한 의존이 필요한 경우(리소스 id, AOS의존 생기는 작업 등) 해당 작업을 정의한 인터페이스 인 `Helper`의 정의.
- `BaseViewModel` 
  - `reactivex`옵저버 관련하여 disposabler객체를 안드로이드 컴포넌트 생명주기에 동기화 하여 필요시 `dispose()`시켜주는 `RxDisposer`인터페이스. 
- 그 외 유틸리티 함수 및 확장 함수들 

#### 2.2.2 `domain` 패키지 

- 서브 도베인별로 나뉘어진 하위 패키지
  - 도메인에 종속된 `Helper`인터페이스 들
  - `ViewModel`클래스 들
    - `LiveData`로 이루어진 데이터 매핑 인스턴스 들
  - `RecyclerView`에서 사용 될 클래스 들
    - 아이템 데이터 클래스
- 공통 도메인 하위 패키지

### 2.3 `common` 모듈 

- `Constants`글로벌 상수 들
- `string` 문자열 리소스 xml
- 그 외 유틸리티 및 확장 함수들

