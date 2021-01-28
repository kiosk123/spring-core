package event;

import java.util.Date;

import org.springframework.context.ApplicationEvent;

public class CheckoutEvent extends ApplicationEvent {

    private static final long serialVersionUID = -7548670362722493740L;
    private final ShoppingCart cart;
    private final Date time;
    
    public CheckoutEvent(ShoppingCart cart, Date time) {
        super(cart);
        this.cart = cart;
        this.time = time;
    }
    
    
    public ShoppingCart getCart() {
        return cart;
    }
    
    public Date getTime() {
        return time;
    }

    @Override
    public String toString() {
        return "CheckoutEvent [toString()=" + super.toString() + "]";
    }
}
