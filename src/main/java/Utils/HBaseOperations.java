package Utils;

import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Result;

import java.io.IOException;
import java.util.List;

public interface HBaseOperations {

    Result get(String tableName, String rowName) throws Exception;

    Result get(String tableName, String rowName, String familyName) throws Exception;

    Result get(String tableName, final String rowName, final String familyName, final String qualifier) throws Exception;

    Result[] batchGet(String tableName, List<Get> getList) throws IOException;

    void put(String tableName, final String rowName, final String familyName, final String qualifier, final byte[] data) throws IOException;

    void batchPut(String tableName, List<Put> putList) throws Exception;

    void increment(String tableName, String rowName, String familyName, String qualifier, int amount) throws IOException;
}
