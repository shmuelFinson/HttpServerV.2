package db;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DynamoDBCRUD {

    //region Members
    private final String TABLE_NAME = "KVPairs";
    AWSCredentialsProvider creds;
    final AmazonDynamoDB ddb;
    DynamoDBMapper mapper;
    private final static Logger LOGGER = LoggerFactory.getLogger(DynamoDBCRUD.class);
    //endregion

    //region C-tor
    public DynamoDBCRUD() {
        this.creds = new AWSStaticCredentialsProvider(
                new BasicAWSCredentials(
                        SecurityCreds.ACCESS_KEY,
                        SecurityCreds.SECRET_KEY
                )
        );
        this.ddb = AmazonDynamoDBClientBuilder.standard()
                .withCredentials(creds)
                .withRegion("eu-west-1")
                .build();
        LOGGER.info("Connected to DB...");

        try {
            this.mapper = new DynamoDBMapper(ddb);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
    }
    //endregion

    //region CRUD functions
    public String readKVPair(String key) {
        String res = null;
        try {

                KVPair kvPair = mapper.load(KVPair.class, key);
                if(kvPair != null){
                    LOGGER.info("Record found: " + kvPair.toString());
                    res = "<h1>Record Found: </h1>" + "<h1>" + kvPair.toString() + "</h1>";
                }


                else{
                    LOGGER.info("Invalid key - No Record exists for key: " + key);
                    res = "<h1>Invalid key - No Record exists for key: </h1>" + "<h1>" + key + "</h1>";

                }


        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            res = "<p>" + e.getMessage() + "</p>";
        }

        return res;
    }


    //both cases work
    public String updateKVPair(String key, String value) {

        String res = null;
        KVPair kvPair = new KVPair();
        try {
                kvPair = mapper.load(KVPair.class, key);
                if(kvPair != null){
                    kvPair.setValue(value);
                    mapper.save(kvPair);
                    //TODO Tell user that value has been updated
                    LOGGER.info("Record updated to: " + kvPair.toString());
                    res = "<h1>Record updated to: </h1>" + "<h1>" + kvPair.toString() + "</h1>";
                }

           else{
                    KVPair newPair = new KVPair();
                    newPair.set_Key(key);
                    newPair.setValue(value);
                    mapper.save(newPair);
                    //TODO Tell client that record does not exist
                    LOGGER.info("No Such record, creating new Record: " + newPair.toString());
                    res = "<h1>No Such record, creating new Record: </h1>" + "<h1>" + newPair.toString() + "</h1>";
                }

        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            res = "<p>" + e.getMessage() + "</p>";
        }
        return res;

    }

    //works for both cases
    public String deleteKVPair(String key) {
        String res = null;
        KVPair kvPair = new KVPair();

        try {
                 kvPair = mapper.load(KVPair.class, key);
            if (kvPair != null) {
                mapper.delete(kvPair);
                LOGGER.info("Record Deleted: " + kvPair.toString());
                res = "<h1>Record Deleted: </h1>" + "<h1>" + kvPair.toString() + "</h1>";
            }
            else{
                LOGGER.info("Record Does not exist for key: " + key);
                res = "<h1>Record Does not exist for key: </h1>" + "<h1>" + key + "</h1>";
            }


        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            res = "<p>" + e.getMessage() + "</p>";

        }
        return res;

    }

    //works for both cases
    public String createKVPair(String key, String value) {

        KVPair kvPair = new KVPair();
        String res = null;

        try {

            KVPair search = mapper.load(KVPair.class, key);
            if (search != null) {
                kvPair.set_Key(key);
                kvPair.setValue(value);
                mapper.save(kvPair);
                //TODO Tell Client that record existed and was overwritten
                LOGGER.info("Record already exists, was overwritten to: " + kvPair.toString());
                res = "<h1>Record already exists, was overwritten to: </h1>" + "<h1>" + kvPair.toString() + "</h1>";

            } else {

                kvPair.set_Key(key);
                kvPair.setValue(value);
                mapper.save(kvPair);

                //TODO tell client record created
                LOGGER.info("New Record created: " + kvPair.toString());
                res = "<h1>New Record created: </h1>" + "<h1>" + kvPair.toString() + "</h1>";

            }
        }
         catch (Exception e) {
            LOGGER.error(e.getMessage());
            res = "<p>" + e.getMessage() + "</p>";
        }
        return res;
    }

}
