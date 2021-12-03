package db;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

@DynamoDBTable(tableName="KVPairs")
public class KVPair {


    private String ID;

    private String Value;

    @DynamoDBHashKey(attributeName="ID")
    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    @DynamoDBAttribute(attributeName="Value")
    public String getValue() {
        return Value;
    }

    public void setValue(String value) {
        Value = value;
    }





}
