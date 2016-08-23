package org.anvard.introtojava.camel;

import org.apache.camel.Exchange;
import org.apache.camel.processor.aggregate.AggregationStrategy;

public class OrderAggregationStrategy implements AggregationStrategy {

    @Override
    public Exchange aggregate(Exchange original, Exchange resource) {
        OrderInfo info = (OrderInfo) original.getIn().getBody();
        OrderInfo recd = (OrderInfo) resource.getIn().getBody();
        info.setCustomerName(recd.getCustomerName());
        info.setOrderTotal(recd.getOrderTotal());
        if (original.getPattern().isOutCapable()) {
            original.getOut().setBody(info);
        }
        return original;
    }

}
