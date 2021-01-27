# 스프링 코어 학습 내용 정리

## 목차

### 1. 자바로 POJO 구성하기

- **@Configuration**을 이용하여 구성 클래스를 작성한다.  
- 구성 클래스에서 빈 선언시 **@Bean**을 이용하여 빈 선언을 한다.  
- **ApplicationContext**를 이용하여 설정한 빈을 가져올 수 있다.  
- 구성 클래스가 아닌 클래스 선언부에서 빈 선언시 **@Component(@Repository, @Service, @Controller)**를 이용한다.  
- **@ComponentScan**을 이용해서 IoC 컨테이너 구성에 필요한 빈들을 검색한다 - **basePackages 설정 필수**  
- 스프링 빈 스코프(**@Scope)** - @Scope("prototype") 형식으로 설정


|스코프|설명|
|--------------|---|
|singleton|IOC 컨테이너당 빈 인스턴스 하나 생성|
|prototype|요청할 때마다 빈 인스턴스 생성|
|request|HTTP 요청당 하나의 빈 인스턴스 생성. 웹애플리케이션 컨텍스트에만 해당|
|session|HTTP 세션당 빈 인스턴스 하나를 생성. 웹 애플리케이션 컨텍스트에만 해당|
|globalSession|전역 HTTP 세션당 빈 인스턴스 하나를 생성. 포털 애플리케이션 컨텍스트에만 해당|

### 2. Properties 파일을 이용한 설정과 Resource를 이용한 파일 읽기

- **@PropertySource**와 **PropertySourcesPlaceholderConfigurer**를 **@Bean**으로 설정하여 Properties 파일의 정보를 읽어 들일 수 있다.
- Resource 인터페이스를 이용해서 classpath 상의 파일을 읽어들일 수 있다.
  - 구현체로는 FileSystemResource, UrlResource, ClassPathResource등이 있다.
- @PostConstruct를 사용하여 IoC 컨테이너에서 빈이 생성되고 난 뒤에 메소드를 호출 할 수 있다. - (pojo.BannerLoader)
  - **@PostConstruct를 사용하기 위해서는 javax.annotation-api가 필요한데 java9 부터 포함되지 않으므로 gradle에 의존성을 추가해야 한다.**

### 3. 다국어 메시지 설정

- **ReloadableMessageSource**를 이용하여 리소스 번들 메시지를 처리할 수 있다. - (config.MessageConfiguration)
- 리소스 번들 메시지 파일은 **파일prefix_ISO언어코드_ISO지역코드.properties**로 구성한다. (ex - messages_en_US.properties)
  - 지역 코드 없이 ISO 언어코드로만 구성된 리소스 번들 메시지 파일도 구성할 수 있다. (ex - messages_en.properties)

### 4. POJO 초기화, 초기화 순서 설정, 초기화 및 폐기 커스터 마이징

- @Bean(initMethod = , destoryMethod = )를 이용해 POJO 초기화 후 처리나 POJO 해제전 필요한 데이터 등을 처리 할 수 있다. - (pojo.PojoConfiguration)
- 빈 클래스에 직접 설정시 **@PostConstruct**와 **@PreDestroy**를 이용해 처리 할 수 있다. - (pojo.Disc) 
- **@PostConstruct나 @PreDestroy를 사용하기 위해서는 javax.annotation-api가 필요한데 java9 부터 포함되지 않으므로 gradle에 의존성을 추가해야 한다.**
- @Lazy를 이용해서 POJO가 실제로 참조되기 전까지 빈 생성 타임을 늦출 수 있다. - (pojo.ShoppingCart)
- @DependsOn으로 설정된 빈의 초기화를 먼저 실행시켜 초기화 순서를 설정 할 수 있다. - (config.SequenceConfiguration)


