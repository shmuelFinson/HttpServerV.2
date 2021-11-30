import com.sun.net.httpserver.HttpServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import config.Configuration;
import config.ConfigurationManager;
import temp.EchoGetHandler;
import temp.EchoHeaderHandler;
import temp.EchoPostHandler;
import http.RootHandler;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

public class Server {

    private final static Logger LOGGER = LoggerFactory.getLogger(Server.class);

    public static void main(String[] args) throws IOException {

        ConfigurationManager.getInstance().loadConfigurationFile("src/main/resources/http.json");
        Configuration conf = ConfigurationManager.getInstance().getCurrentConfiguration();

        LOGGER.info("Server starting...");

        HttpServer server = HttpServer.create(new InetSocketAddress(conf.getPort()), 0);

        LOGGER.info("Server started at port: " + conf.getPort());
        LOGGER.info("Webroot: " + conf.getWebroot());

        server.createContext(conf.getWebroot(), new RootHandler());
        server.createContext("/echoHeader", new EchoHeaderHandler());
        server.createContext("/echoGet", new EchoGetHandler());
        server.createContext("/echoPost", new EchoPostHandler());

        //TODO See if this really is multithreaded - could be useful
        server.setExecutor(Executors.newCachedThreadPool());

        server.start();

        //TODO See how to deal with bad requests - how to handle wrong version number etc..


    }

}
