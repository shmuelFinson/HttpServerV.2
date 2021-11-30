package http;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;

/**
 * Handler class which will respond to a POST HTTP request to Add a KV pair to the DB
 * */
public class CreateKVPairHandler extends Thread implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {

    }
}
