# 스프링 코어 학습 내용 정리

## 프로젝트 구성

스프링 5  
JAVA 11  
gradle 6.6

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

### 5. 빈 후처리기로 POJO 검증 및 수정

빈 후처리기를 이용하여 초기화 콜백 메서드(@Bean(initMethod), @PostConstruct) 전 후에 원하는 로직을 적용할 수 있다.  
BeanPostProcessor 인터페이스를 구현한 클래스를 빈으로 등록한다.

- 모든 빈 인스턴스를 처리하는 후처리기 - (config.AuditCheckBeanPostProcessor)
- 주어진 빈 인스턴스만 처리하는 후 처리기 - (config.ProductCheckBeanPostProcessor)

@Required를 이용하여 특정 빈 프로퍼티가 설정되었는지 확인 할 수 있다. - (pojo.Disc)
@Required가 설정된 프로퍼티에 값이 설정 되어 있지 않다면 BeanInitializationException 예외를 던진다.


### 6. 팩토리로 POJO 생성

#### 6-1. 정적 팩터리 메서드로 POJO 생성

- 팩토리 클래스 - (pojo.ProductCreator)
- 팩토리 빈 생성 - (config.PojoConfiguration)

#### 6-2. 인스턴스 팩터리 메서드로 POJO 생성

Map을 구성해서 빈 정보를 담아서 POJO를 생성한다.

- 팩토리 클래스 - (pojo.ProductCreator)
- 팩토리 빈 생성 - (config.PojoConfiguration)

#### 6-3. 스프링 팩토리 빈(AbstractFactoryBean)으로 POJO 생성

AbstractFactoryBean을 상속받아 팩토리 클래스를 구현한다.  


- 팩토리 클래스 - (pojo.DiscountFactoryBean)
- 팩토리 빈 생성 - (config.PojoConfiguration)

### 7. POJO에서 IOC 컨테이너 리소스로 접근

POJO에서 IOC 컨테이너 리소스로 접근하기 위해서는 *Aware 인터페이스를 POJO에서 구현해서 해야한다.

|Aware 인터페이스|대상 리소스 타입|
|--------------|---|
|BeanNameAware|IOC 컨테이너에 구성한 인스턴스의 빈 이름|
|BeanFactoryAware|현재 빈 팩토리, 컨테이너 서비스를 호출하는 데 쓰임|
|ApplicationContextAware|현재 애플리케이션 컨텍스트, 컨테이너 서비스를 호출하는 데 쓰임|
|MessageSourceAware|메시지 소스, 텍스트 메시지를 해석하는 데 쓰임|
|ApplicationEventPublisherAware|애플리케이션 이벤트 발행기(퍼블리셔), 애플리케이션 이벤트를 발행하는데 쓰임|
|ResourceLoaderAware|리소스 로더, 외부 리소스를 로드하는 데 쓰임|
|EnvironmentAware|ApplicationContext 인터페이스에 묶인 org.springframework.core.env.Environment 인스턴스|

Aware 인터페이스의 세터 메서드는 스프링이 빈 프로퍼티를 설정한 이후, 초기화 콜백 메서드(@PostConstructor)를 호출하기 이전에 호출한다.  
순서는 다음과 같다

1. 생성자나 팩토리 메서드를 호출해 빈 인스턴스 생성  

2. 빈 프로퍼티에 값, 빈 레퍼런스를 설정  

3. **Aware 인터페이스에 정의한 세터 메서드를 호출**  

4. 빈 인스턴스를 각 빈 후처리기에 있는 postProcessBeforeInitialization() 메서드로 넘겨초기화 콜백 메서드를 호출  

5. 빈 인스턴스를 각 빈 후처리기 postProcessAfterInitialization() 메서드로 넘김

6. 컨테이너가 종료되면 폐기 콜백 메서드(@PreDestroy)를 호출  

- BeanNameAware 구현 - (pojo.Cashier)
