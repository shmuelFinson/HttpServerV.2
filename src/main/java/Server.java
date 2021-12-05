import com.sun.net.httpserver.HttpServer;
import http.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import config.Configuration;
import config.ConfigurationManager;


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

        //Load configuration from file:
        LOGGER.info("Server started at port: " + conf.getPort());
        LOGGER.info("Webroot: " + conf.getWebroot());
        LOGGER.info("create record: " + conf.getCreate_record());
        LOGGER.info("read record: " + conf.getRead_record());
        LOGGER.info("update record: " + conf.getUpdate_record());
        LOGGER.info("delete record: " + conf.getDelete_record());

        server.createContext(conf.getWebroot(), new RootHandler());
        //CRUD Handlers:
        server.createContext(conf.getCreate_record(), new CreateKVPairHandler());
        server.createContext(conf.getRead_record(), new ReadKVPairHandler());
        server.createContext(conf.getUpdate_record(), new UpdateKVPairHandler());
        server.createContext(conf.getDelete_record(), new DeleteKVPairHandler());

        server.setExecutor(Executors.newCachedThreadPool());

        server.start();



    }

}
