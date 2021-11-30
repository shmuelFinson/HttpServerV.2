package http;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;

/**
 * Handler class which will respond to a GET HTTP request to read a KV pair from the DB
 * */
public class ReadKVPairHandler extends Thread implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {

    }
}
