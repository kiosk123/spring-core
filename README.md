# 스프링 코어 학습 내용 정리

## 목차
### 1. 자바로 POJO 구성하기
@Configuration을 이용하여 구성 클래스를 작성한다.  
구성 클래스 @Bean을 이용하여 빈 설정을 한다.  
ApplicationContext를 이용하여 설정한 빈을 가져올 수 있다.  
구성 클래스가 아닌 클래스 선언부에서 빈 선언을 하려면 @Component(@Repository, @Service, @Controller)를 이용한다.  
@ComponentScan을 이용해서 IoC 컨테이너 구성에 필요한 빈들을 검색한다 - **basePackages 설정 필수**  


