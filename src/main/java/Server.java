import com.sun.net.httpserver.HttpServer;
import config.Configuration;
import config.ConfigurationManager;
import http.EchoGetHandler;
import http.EchoHeaderHandler;
import http.EchoPostHandler;
import http.RootHandler;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

public class Server {


    public static void main(String[] args) throws IOException {

        ConfigurationManager.getInstance().loadConfigurationFile("src/main/resources/http.json");
        Configuration conf = ConfigurationManager.getInstance().getCurrentConfiguration();
        HttpServer server = HttpServer.create(new InetSocketAddress(conf.getPort()), 0);
        System.out.println("server started at " + conf.getPort());
        server.createContext("/", new RootHandler());
        server.createContext("/echoHeader", new EchoHeaderHandler());
        server.createContext("/echoGet", new EchoGetHandler());
        server.createContext("/echoPost", new EchoPostHandler());
        server.setExecutor(Executors.newCachedThreadPool());
        server.start();

    }

}
