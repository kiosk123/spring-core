package pojo;

import org.springframework.beans.factory.annotation.Required;

public class Disc extends Product {

    private int capacity;

    public Disc() {
        super();
    }

    public Disc(String name, double price) {
        super(name, price);
    }

    // 게터 및 세터
    public int getCapacity() {
        return capacity;
    }

    @Required
    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
}
