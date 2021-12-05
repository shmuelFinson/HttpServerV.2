package http;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import config.Configuration;
import config.ConfigurationManager;
import db.DynamoDBCRUD;

import java.io.IOException;
import java.io.OutputStream;

public class RootHandler extends Thread implements HttpHandler {

    @Override
    public void handle(HttpExchange he) throws IOException {

        ConfigurationManager.getInstance().loadConfigurationFile("src/main/resources/http.json");
        Configuration conf = ConfigurationManager.getInstance().getCurrentConfiguration();
        String response = "<h1>Server start successful</h1>" + "<h1>Options: </h1>"
                + "<p>" +
                "<li>" + conf.getCreate_record() + "</li>" +
                "<li>" +  conf.getRead_record() + "</li>" +
                "<li>" + conf.getUpdate_record() + "</li>" +
                "<li>" + conf.getDelete_record() + "</li>" +
                "<li>" + "?Key=key&Value=value" + "</li>" +
                "</p>";
        he.sendResponseHeaders(200, response.length());
        OutputStream os = he.getResponseBody();
        os.write(response.getBytes());
        System.out.println(currentThread().getId());
        os.close();
    }
}
