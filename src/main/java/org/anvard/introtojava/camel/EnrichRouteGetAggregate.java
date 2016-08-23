package org.anvard.introtojava.camel;

import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;

public class EnrichRouteGetAggregate extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        from("timer://ordergen").bean(new OrderGenerator(), "generate")
                .enrich("direct:enricher", new OrderAggregationStrategy()).marshal().json(JsonLibrary.Jackson)
                .log("${body}");
        from("direct:enricher")
                .setHeader(Exchange.HTTP_URI, simple("http://localhost:8680/rest/order/lookup/${body.orderNumber}"))
                .transform().simple("${null}").to("http://ignored").unmarshal().json(JsonLibrary.Jackson, OrderInfo.class);
    }

}
