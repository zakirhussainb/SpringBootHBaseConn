package Utils;

import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.IOException;

@Configuration
@EnableConfigurationProperties(ApplicationProperties.class)
public class HBaseConfiguration {

    private final Logger log = LoggerFactory.getLogger(HBaseConfiguration.class);

    @Autowired
    private ApplicationProperties applicationProperties;

    private static final String HBASE_CONFIGURATION_ZOOKEEPER_QUORUM = "hbase.zookeeper.quorum";
    private static final String HBASE_CONFIGURATION_ZOOKEEPER_CLIENT_PORT = "hbase.zookeeper.property.clientPort";
    private static final String HBASE_CONFIGURATION_MASTER = "hbase.master";

    private static Connection connection;

    @PostConstruct
    public void setupConnection() throws IOException {
        log.info("Setting up HBase connection");
        org.apache.hadoop.conf.Configuration configuration = org.apache.hadoop.hbase.HBaseConfiguration.create();
//        configuration.set(HBASE_CONFIGURATION_MASTER, applicationProperties.getHbase().getMaster());
        configuration.set(HBASE_CONFIGURATION_ZOOKEEPER_QUORUM, applicationProperties.getHbase().getZookeeper().getQuorum());
        configuration.set(HBASE_CONFIGURATION_ZOOKEEPER_CLIENT_PORT, applicationProperties.getHbase().getZookeeper().getProperty().getClientPort());
        connection = ConnectionFactory.createConnection(configuration);
        log.info("HBase connection setup done");
    }

    @Bean
    public Connection getHBaseConnection() throws IOException {
        if(connection == null) {
            log.info("Inside connection method");

            org.apache.hadoop.conf.Configuration configuration = org.apache.hadoop.hbase.HBaseConfiguration.create();
            configuration.set(HBASE_CONFIGURATION_MASTER, applicationProperties.getHbase().getMaster());
            log.info("quorum...." + applicationProperties.getHbase().getZookeeper().getQuorum());
            log.info("port....." + applicationProperties.getHbase().getZookeeper().getProperty().getClientPort());
            System.exit(0);
            configuration.set(HBASE_CONFIGURATION_ZOOKEEPER_QUORUM, "18.188.77.36");
            configuration.set(HBASE_CONFIGURATION_ZOOKEEPER_CLIENT_PORT, "2181");
            connection = ConnectionFactory.createConnection(configuration);
        }
        return connection;
    }

    @PreDestroy
    public void onDestroy() throws IOException {
        connection.close();
        log.info("Closing HBase connection");
    }
}
