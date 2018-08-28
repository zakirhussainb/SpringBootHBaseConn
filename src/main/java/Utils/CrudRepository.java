package Utils;

import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;
import java.util.List;

public class CrudRepository implements HBaseOperations {

//    HBaseConfiguration hBaseConfiguration;
    /*String host = "18.188.77.36";
    String port = "2181";
    HBaseConfig hBaseConfig = new HBaseConfig(host,port);
    Configuration config =  hBaseConfig.connect();
    Connection connection = ConnectionFactory.createConnection(config);*/

    HBaseConfiguration hBaseConfiguration = new HBaseConfiguration();
//    hBaseConfiguration.setupConnection();
    Connection connection = hBaseConfiguration.getHBaseConnection();


    public CrudRepository() throws IOException {
    }

   /* public CrudRepository() throws IOException {
        connection = hBaseConfiguration.getHBaseConnection();
    }*/

    @Override
    public Result get(String tableName, String rowName) throws Exception {
        return get(tableName, rowName, null, null);
    }

    @Override
    public Result get(String tableName, String rowName, String familyName) throws Exception {
        return get(tableName, rowName, familyName, null);
    }

    @Override
    public Result get(String tableName, String rowName, String familyName, String qualifier) throws Exception {
        Table table = connection.getTable(TableName.valueOf(tableName));
        Get get = new Get(rowName.getBytes());
        if (familyName != null) {
            byte[] family = familyName.getBytes();

            if (qualifier != null) {
                get.addColumn(family, qualifier.getBytes());
            } else {
                get.addFamily(family);
            }
        }

        Result result = table.get(get);
        table.close();
        return result;
    }

    @Override
    public Result[] batchGet(String tableName, List<Get> getList) throws IOException {
        Table table = connection.getTable(TableName.valueOf(tableName));
        Result[] result = table.get(getList);
        table.close();
        return result;
    }

    @Override
    public void put(String tableName, String rowName, String familyName, String qualifier, String data) throws IOException {
        Table table = connection.getTable(TableName.valueOf(tableName));
        Put put = new Put(rowName.getBytes());
        put.addColumn(familyName.getBytes(), qualifier.getBytes(), data.getBytes());
        table.put(put);
        table.close();
    }

    @Override
    public void batchPut(String tableName, List<Put> putList) throws Exception {
        Table table = connection.getTable(TableName.valueOf(tableName));
        table.put(putList);
        table.close();
    }

    @Override
    public void increment(String tableName, String rowName, String familyName, String qualifier, int amount) throws IOException {
        Table table = connection.getTable(TableName.valueOf(tableName));
        Increment increment = new Increment(Bytes.toBytes(rowName));
        increment.addColumn(Bytes.toBytes(familyName), Bytes.toBytes(qualifier), amount);
        table.increment(increment);
        table.close();
    }
}
