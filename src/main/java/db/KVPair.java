package db;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

@DynamoDBTable(tableName="KVPairs")
public class KVPair {


    private String _Key;

    private String Value;

    @DynamoDBHashKey(attributeName="_Key")
    public String get_Key() {
        return _Key;
    }

    public void set_Key(String _Key) {
        this._Key = _Key;
    }

    @DynamoDBAttribute(attributeName="Value")
    public String getValue() {
        return Value;
    }

    public void setValue(String value) {
        Value = value;
    }


    @Override
    public String toString() {
        return "{" +
                "Key='" + _Key + '\'' +
                ", Value='" + Value + '\'' +
                '}';
    }
}
