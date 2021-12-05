package http;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import db.DynamoDBCRUD;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

import static http.ParseQuery.*;

/**
 * Handler class which will respond to a POST HTTP request to Add a KV pair to the DB
 * */
public class CreateKVPairHandler extends Thread implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        DynamoDBCRUD dbcrud = new DynamoDBCRUD();

        // parse request
        Map<String, Object> parameters = new HashMap<String, Object>();
        URI requestedUri = exchange.getRequestURI();
        String query = requestedUri.getRawQuery();
        parseQuery(query, parameters);
        String response = "";
        if(properFormatForKV(parameters)){
            String key = "";
            String value = "";
           if(parameters.containsKey("Key"))
               key = (String) parameters.get("Key");
           else{
               key = (String) parameters.get("key");
           }
           if(parameters.containsKey("Value"))
               value = (String)parameters.get("Value");
           else{
               value = (String)parameters.get("value");
           }
           response  = dbcrud.createKVPair(key,value);
        }
        // send response
        exchange.sendResponseHeaders(200, response.length());
        OutputStream os = exchange.getResponseBody();
        os.write(response.toString().getBytes());
        System.out.println(currentThread().getId());
        os.close();
    }
}
