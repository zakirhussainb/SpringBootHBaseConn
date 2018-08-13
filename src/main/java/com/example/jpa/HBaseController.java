package com.example.jpa;

import Utils.CrudRepository;
import Utils.HBaseConfiguration;
import org.apache.hadoop.hbase.client.Admin;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.Result;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@ResponseBody
@RequestMapping("/xedflix")
public class HBaseController extends CrudRepository {

    private HBaseConfiguration hBaseConfiguration;

    private Connection connection;

    private Admin admin;

    public HBaseController() throws IOException {

    }

    @GetMapping("/hello")
    public String Hello() {

        return "Hello Guys....****";
    }

    @GetMapping(value="/getResult")
    public Result getResult(@RequestBody String tableName, @RequestBody String rowName, @RequestBody String familyName, @RequestBody String qualifier) throws Exception{
        return get(tableName,rowName,familyName,qualifier);
    }

    /*@PostMapping(value = "/create")
    private void createTable(@RequestBody String tableName, @RequestBody ColumnFamilyCreator columnFamilyName) throws IOException {
        connection = hBaseConfiguration.getHBaseConnection();
        admin = connection.getAdmin();

        if (admin.tableExists(TableName.valueOf(tableName))) {
            admin.disableTable(TableName.valueOf(tableName));
//            admin.deleteTable(TableName.valueOf(tableName));
        }

        HTableDescriptor tableDes = new HTableDescriptor(TableName.valueOf(tableName));
        HColumnDescriptor cf1 = new HColumnDescriptor(columnFamilyName);
        tableDes.addFamily(cf1);
        admin.createTable(tableDes);
    }*/

    /*@PostMapping(value = "/get")
    private void get(String tableName, final String rowName, final String familyName, final String qualifier, final RowMapper<T> mapper){


        return hbaseTemplate.get(tableName,rowName,familyName,qualifier,);
    }*/




}
