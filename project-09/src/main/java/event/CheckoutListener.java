package event;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class CheckoutListener implements ApplicationListener<CheckoutEvent> {

    //@EventListener
    @Override
    public void onApplicationEvent(CheckoutEvent event) {
        System.out.println("Checkout event [" + event.getTime() + "]");
    }
    
}
