package config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Controller;

import pojo.SequenceGenerator;


@Configuration
@ComponentScan(
    basePackages = {"dao", "pojo"},
    includeFilters = {
        @ComponentScan.Filter(
            type = FilterType.REGEX,
            pattern = {"dao.*Dao"}
        )
    },
    excludeFilters = {
        @ComponentScan.Filter(
            type = FilterType.ANNOTATION,
            classes = {Controller.class}
        )
    }
)
public class SequenceGeneratorConfiguration {
    
    /**
     * sequenceGenerator
     * SequenceGenerator 빈을 생성
     * @return SequenceGenerator
     */
    @Bean
    public SequenceGenerator sequenceGenerator() {
        SequenceGenerator seqgen = new SequenceGenerator();
        seqgen.setPrefix("30");
        seqgen.setSuffix("A");
        seqgen.setInitial(100000);
        return seqgen;
    }
}
