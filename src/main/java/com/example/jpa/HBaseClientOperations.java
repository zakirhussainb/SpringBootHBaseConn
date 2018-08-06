package com.example.jpa;

import Utils.HBaseConfig;
import com.google.gson.JsonArray;
import com.google.protobuf.ServiceException;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.MasterNotRunningException;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@ResponseBody
@RequestMapping("/xedflix")
public class HBaseClientOperations {
    String host = "18.188.77.36";
    String port = "2181";
    HBaseConfig hBaseConfig = new HBaseConfig(host,port);
    Configuration config =  hBaseConfig.connect();
    Connection connection = ConnectionFactory.createConnection(config);
    Admin admin = connection.getAdmin();
    private final TableName table1 = TableName.valueOf("test_new");
    public static final Logger logger = LoggerFactory.getLogger(HBaseClientOperations.class);
    public HBaseClientOperations() throws IOException {
    }

    @GetMapping("/hello")
    public String Hello(){
        return "Hello Guys....****";
    }
    @GetMapping("/list")
    private List<String> listallTables() throws IOException {
        HTableDescriptor hTableDescriptor[] = admin.listTables();
        List<String> lst = new ArrayList<>();
        for (int i = 0; i < hTableDescriptor.length; i++) {
//            System.out.println(hTableDescriptor[i].getNameAsString());
            lst.add(hTableDescriptor[i].getNameAsString());
        }
        return lst;
    }
//    @GetMapping(value = "/scan", produces = {MediaType.APPLICATION_JSON_VALUE})
    @GetMapping("/scan")
    private List<String> scan() throws IOException {
        System.out.println("\n*** SCAN example ~fetching data in Family1:Qualifier1 ~ ***");
        Scan scan = new Scan();
//        scan.addColumn(family1.getBytes(), qualifier1);
        List<String> lst = new ArrayList<>();
        JsonArray jsonArray = new JsonArray();
        Table table = connection.getTable(table1);
        try (ResultScanner scanner = table.getScanner(scan)) {
            for (Result result : scanner) {
                System.out.println("Found row: " + result);
                lst.add(result.toString());
            }
        }
        return lst;
    }
    /*@GetMapping("/list")
    public List<String> getList() throws IOException, ServiceException {
        Configuration config = HBaseConfiguration.create();
        String path = this.getClass().getClassLoader().getResource("hbase-site.xml").getPath();
        config.addResource(new Path(path));
        try {
            HBaseAdmin.checkHBaseAvailable(config);
        } catch (MasterNotRunningException e) {
            System.out.println("HBase is not running." + e.getMessage());
        }
        List<String> lst = new ArrayList<>();
        try (Connection connection = ConnectionFactory.createConnection(config)) {
            Admin admin = connection.getAdmin();
            HTableDescriptor hTableDescriptor[] = admin.listTables();
            for (int i = 0; i < hTableDescriptor.length; i++) {
                System.out.println(hTableDescriptor[i].getNameAsString());
                lst.add(hTableDescriptor[i].getNameAsString());
            }
        }
        return lst;
    }*/
}
