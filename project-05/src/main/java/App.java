

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import pojo.Cashier;
import pojo.Product;
import pojo.ShoppingCart;

public class App {

    public static void main(String[] args) throws Exception {

        ApplicationContext context =
                new GenericXmlApplicationContext("appContext.xml");

        // ApplicationContext context = 
        //     new AnnotationConfigApplicationContext(PojoConfiguration.class);

        Product aaa = (Product) context.getBean("aaa");
        Product cdrw = (Product) context.getBean("cdrw");
        Product dvdrw = (Product) context.getBean("dvdrw");

        ShoppingCart cart1 = (ShoppingCart) context.getBean("shoppingCart");
        cart1.addItem(aaa);
        cart1.addItem(cdrw);
        System.out.println("Shopping cart 1 contains " + cart1.getItems());

        ShoppingCart cart2 = (ShoppingCart) context.getBean("shoppingCart");
        cart2.addItem(dvdrw);
        System.out.println("Shopping cart 2 contains " + cart2.getItems());

        Cashier cashier = (Cashier) context.getBean("cashier");
        cashier.checkout(cart1);
        cashier.checkout(cart2);
    }
}
