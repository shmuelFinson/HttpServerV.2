package db;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.GetItemRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class DynamoDBCRUD {

    private final String TABLE_NAME = "KVPairs";
    AWSCredentialsProvider creds;
    final AmazonDynamoDB ddb;

    public DynamoDBCRUD() {
        this.creds  = new AWSStaticCredentialsProvider(
                new BasicAWSCredentials(
                        SecurityCreds.ACCESS_KEY,
                        SecurityCreds.SECRET_KEY
                )
        );
        this.ddb = AmazonDynamoDBClientBuilder.standard()
                .withCredentials(creds)
                .withRegion("eu-west-1")
                .build();
    }


    public void readKVPair(String name) {
            HashMap<String, AttributeValue> key_to_get =
                    new HashMap<String, AttributeValue>();

            key_to_get.put("ID", new AttributeValue(String.valueOf(1)));
            //key_to_get.put("ID",new AttributeValue(String.valueOf(1)));

            GetItemRequest request = null;

        request = new GetItemRequest()
                .withKey(key_to_get)
                .withTableName(TABLE_NAME);


        AWSCredentialsProvider creds = new AWSStaticCredentialsProvider(
                new BasicAWSCredentials(
                        "AKIAVGLMMMVG42VFT5RE",
                        "wH6AxAAJCnYy/WXKeJiHWFFUf+XlP5TorkJGIj8+"
                )
        );
        final AmazonDynamoDB ddb = AmazonDynamoDBClientBuilder.standard()
                .withCredentials(creds)
                .withRegion("eu-west-1")
                .build();

        try {
            Map<String, AttributeValue> returned_item =
                    ddb.getItem(request).getItem();
            if (returned_item != null) {
                Set<String> keys = returned_item.keySet();
                for (String key : keys) {
                    System.out.format("%s: %s\n",
                            key, returned_item.get(key).toString());
                }
            } else {
                System.out.format("No item found with the key %s!\n", name);
            }
        } catch (AmazonServiceException e) {
            System.err.println(e.getErrorMessage());
        }
    }

    public void updateKVPair(){

    }

    public void deleteKVPair(){}

    public void createKVPair(String key, String Value){


        KVPair kvPair = new KVPair();

        try{
            kvPair.setID(key);
            kvPair.setValue(Value);
            DynamoDBMapper mapper = new DynamoDBMapper(ddb);
            try{
                KVPair load = mapper.load(KVPair.class,key);
                if(load != null){
                    System.out.println(load.getValue());
                }
            }
            catch (Exception e){}

           // mapper.save(kvPair);
        }
        catch (Exception e){

        }
    }

}
