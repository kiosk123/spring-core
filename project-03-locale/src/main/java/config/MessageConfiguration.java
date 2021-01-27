package config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

@Configuration
public class MessageConfiguration {

    @Bean
    public ReloadableResourceBundleMessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();

        // 클래스 패스 상에 위치한 locale 디렉터리에서 파일 이름 prefix가 messages인 것 모두 리소스 번들 메시지로 인식한다
        messageSource.setBasenames("classpath:locale/messages");
        messageSource.setCacheSeconds(1);
        return messageSource;
    }
}
