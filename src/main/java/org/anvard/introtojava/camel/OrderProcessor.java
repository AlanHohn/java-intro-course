package org.anvard.introtojava.camel;

import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;

import java.util.Scanner;

public class OrderProcessor {

    private CamelContext context;

    public OrderProcessor(RouteBuilder builder) {
        try {
            context = new DefaultCamelContext();
            context.addRoutes(builder);
        } catch (Exception e) {
            System.out.println("Failed");
        }
    }

    public void start() throws Exception {
        context.start();
    }

    public static void main(String[] args) throws Exception {
        System.out.println("Choose a route option: ");
        System.out.println("1. Enrich with POST");
        System.out.println("2. Enrich with GET");
        System.out.println("3. Enrich with GET partial with aggregator");
        Scanner in = new Scanner(System.in);
        int num = in.nextInt();
        in.close();
        switch (num) {
            case 1:
                new OrderProcessor(new EnrichRoutePost()).start();
                break;
            case 2:
                new OrderProcessor(new EnrichRouteGet()).start();
                break;
            case 3:
                new OrderProcessor(new EnrichRouteGetAggregate()).start();
                break;
        }
        new EmbeddedServer().start();
        while (true) {
            Thread.sleep(1000);
        }

    }

}
