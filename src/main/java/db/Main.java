package db;

public class Main {
    public static void main(String[] args){
        DynamoDBCRUD db = new DynamoDBCRUD();
        db.createKVPair("3","{name:Ron, Age:49}");
    }
}
