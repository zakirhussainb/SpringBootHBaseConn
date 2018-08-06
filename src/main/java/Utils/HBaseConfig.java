package Utils;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;

public class HBaseConfig {
    private String host;
    private String port;

    public HBaseConfig(String host, String port){
        this.host = host;
        this.port = port;
    }
    public Configuration connect(){
        Configuration config = HBaseConfiguration.create();
        config.set("hbase.zookeeper.quorum",host);
        config.set("hbase.zookeeper.property.clientPort",port);
        return config;
    }
}
