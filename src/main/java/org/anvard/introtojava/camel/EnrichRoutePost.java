package org.anvard.introtojava.camel;

import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;

public class EnrichRoutePost extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        from("timer://ordergen").bean(new OrderGenerator(), "generate")
                .marshal().json(JsonLibrary.Jackson)
                .setHeader(Exchange.CONTENT_TYPE, constant("application/json"))
                .enrich("http://localhost:8680/rest/order/lookup").log("${body}");
    }

}
