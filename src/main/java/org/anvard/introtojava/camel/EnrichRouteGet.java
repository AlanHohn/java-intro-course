package org.anvard.introtojava.camel;

import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;

public class EnrichRouteGet extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        from("timer://ordergen").bean(new OrderGenerator(), "generate")
                .setHeader(Exchange.HTTP_URI,
                        simple("http://localhost:8680/rest/order/lookup/${body.orderNumber}/${body.timestamp.time}"))
                .transform().simple("${null}").to("http://ignored").log("${body}");
    }

}
