import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import config.SequenceGeneratorConfiguration;
import dao.SequenceDao;
import pojo.SequenceGenerator;

public class App {
    public static void main(String[] args) {
        // 빈 설정된 IoC 컨테이너 가져오기
        ApplicationContext context = new AnnotationConfigApplicationContext(SequenceGeneratorConfiguration.class);

        // 빈 이름으로 설정된 빈 가져오기
        SequenceGenerator sequenceGenerator = (SequenceGenerator)context.getBean("sequenceGenerator");
        
        System.out.println(sequenceGenerator.getSequence());
        System.out.println(sequenceGenerator.getSequence());

        // 빈 타입으로 설정된 빈 가져온기
        SequenceDao sequenceDao = context.getBean(SequenceDao.class);

        System.out.println(sequenceDao.getNextValue("IT"));
        System.out.println(sequenceDao.getNextValue("IT"));
    }
}
