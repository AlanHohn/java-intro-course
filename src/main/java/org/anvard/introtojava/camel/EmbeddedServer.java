package org.anvard.introtojava.camel;

import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.ContextHandler;
import org.eclipse.jetty.server.handler.DefaultHandler;
import org.eclipse.jetty.server.handler.HandlerCollection;
import org.glassfish.jersey.jetty.JettyHttpContainerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import javax.ws.rs.core.UriBuilder;
import java.net.URI;

public class EmbeddedServer {

    private static final int SERVER_PORT = 8680;

    private Server server;

    public EmbeddedServer() {
        URI baseUri = UriBuilder.fromUri("http://localhost").port(SERVER_PORT).build();
        ResourceConfig config = new ResourceConfig(OrderLookup.class);
        server = JettyHttpContainerFactory.createServer(baseUri, config, false);

        ContextHandler contextHandler = new ContextHandler("/rest");
        contextHandler.setHandler(server.getHandler());
        HandlerCollection handlerCollection = new HandlerCollection();
        handlerCollection.setHandlers(new Handler[]{contextHandler, new DefaultHandler()});
        server.setHandler(handlerCollection);
    }

    public void start() {
        try {
            server.start();
        } catch (Exception e) {
            System.out.println("Failed");
        }
    }

    public static void main(String[] args) throws Exception {
        EmbeddedServer es = new EmbeddedServer();
        es.start();
        es.server.join();
    }
}
