package org.anvard.introtojava.camel;

import java.util.Date;
import java.util.Random;

public class OrderGenerator {
    private final Random r = new Random();

    public OrderInfo generate() {
        OrderInfo info = new OrderInfo();
        info.setOrderNumber(r.nextInt(1000));
        info.setTimestamp(new Date());
        return info;
    }
}