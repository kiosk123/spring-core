import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.SyncTaskExecutor;
import org.springframework.core.task.support.TaskExecutorAdapter;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import taskexecutor.DemonstrationRunnable;
import taskexecutor.ExecutorsConfiguration;

@Component
public class App {

    @Autowired
    private SimpleAsyncTaskExecutor asyncTaskExecutor;
    
    @Autowired
    private SyncTaskExecutor syncTaskExecutor;
    
    @Autowired
    private TaskExecutorAdapter taskExecutorAdapter;
    
    @Autowired
    private ThreadPoolTaskExecutor threadPoolTaskExecutor;
    
    @Autowired
    private DemonstrationRunnable task;
    
    @PostConstruct
    public void submitJobs() {
        // syncTaskExecutor.execute(task);
        // taskExecutorAdapter.submit(task);
        asyncTaskExecutor.submit(task);
        
        for (int i = 0; i < 500; i++) {
        }
    }
    public static void main(String[] args) {
        new AnnotationConfigApplicationContext(ExecutorsConfiguration.class)
            .registerShutdownHook();
        ExecutorService executorService = Executors.newCachedThreadPool();
    }
}
