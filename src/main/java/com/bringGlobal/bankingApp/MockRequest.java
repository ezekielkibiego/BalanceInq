package com.bringGlobal.bankingApp;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MockRequest extends RouteBuilder {

    @Autowired
    private MockProcessor mockProcessor;

    @Override
    public void configure() throws Exception {

        rest("/balance")
                .post("/services")
                .description(" A post service")
                .consumes("application/json")
                .produces("application/json")
                .to("direct:dispatchPost");

        from("direct:dispatchPost")
                .routeId("request.dispatchPost")
                .removeHeader("camel*")
                .process(mockProcessor);
    }
}
