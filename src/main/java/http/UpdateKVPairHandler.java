package http;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;

/**
 * Handler class which will respond to a POST HTTP request to Modify a KV pair in the DB
 * */
public class UpdateKVPairHandler extends Thread implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {

    }
}
