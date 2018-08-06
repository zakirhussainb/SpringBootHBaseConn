package com.example.jpa;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.MasterNotRunningException;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.HBaseAdmin;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TestHBaseConn {

    public static void main(String[] args) throws Exception {
//        args[0] = "18.188.77.36";
//        args[1] = "2181";
        SpringApplication.run(TestHBaseConn.class, args);
    }
}
