package http;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;

/**
 * Handler class which will respond to a DELETE HTTP request to delete a KV pair from the DB
 * */
public class DeleteKVPair extends Thread implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {

    }
}
