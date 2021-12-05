package http;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import db.DynamoDBCRUD;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

import static http.ParseQuery.parseQuery;
import static http.ParseQuery.properFormatForK;

/**
 * Handler class which will respond to a DELETE HTTP request to delete a KV pair from the DB
 * */
public class DeleteKVPairHandler extends Thread implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        DynamoDBCRUD dbcrud = new DynamoDBCRUD();

        // parse request
        Map<String, Object> parameters = new HashMap<String, Object>();
        URI requestedUri = exchange.getRequestURI();
        String query = requestedUri.getRawQuery();
        parseQuery(query, parameters);
        String response = "";
        if(properFormatForK(parameters)){
            String key = "";
            if(parameters.containsKey("Key"))
                key = (String) parameters.get("Key");
            else{
                key = (String) parameters.get("key");
            }
            response  = dbcrud.deleteKVPair(key);
        }
        // send response
        exchange.sendResponseHeaders(200, response.length());
        OutputStream os = exchange.getResponseBody();
        os.write(response.toString().getBytes());
        System.out.println(currentThread().getId());
        os.close();
    }
}
