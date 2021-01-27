package pojo;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

;

public class Disc extends Product {

    private int capacity;

    public Disc() {
        super();
    }

    public Disc(String name, double price) {
        super(name, price);
    }

    
    @PostConstruct
    public void init() {
        System.out.println("Created Disc : " + this.getName() + ":" + this.getPrice());
    }

    @PreDestroy
    public void destory() {
        System.out.println("Destroy Disc : " + this.getName() + ":" + this.getPrice());
    }

    // 게터 및 세터
    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
}
