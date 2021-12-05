package db;

public class Main {
    public static void main(String[] args){
        DynamoDBCRUD db = new DynamoDBCRUD();
        System.out.println(db.createKVPair("999","Something"));
    }
}
