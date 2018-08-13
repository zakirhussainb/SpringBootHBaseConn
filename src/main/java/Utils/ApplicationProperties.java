package Utils;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Properties specific to Xedflixanalytics.
 * <p>
 * Properties are configured in the application.yml file.
 * See {@link //io.github.jhipster.config.JHipsterProperties} for a good example.
 */
@ConfigurationProperties(prefix = "application", ignoreUnknownFields = false)
public class ApplicationProperties {

    private final Hbase hbase = new Hbase();

    private final Kafka kafka = new Kafka();

    public Hbase getHbase() {
        return hbase;
    }

    public Kafka getKafka() {
        return kafka;
    }

    public static class Kafka {

        private final Common common = new Common();
        private final Analytics analytics = new Analytics();

        public Analytics getAnalytics() {
            return analytics;
        }

        public Common getCommon() {
            return common;
        }

        public static class Analytics {
            private String groupId;
            private String topic;

            public String getGroupId() {
                return groupId;
            }

            public void setGroupId(String groupId) {
                this.groupId = groupId;
            }

            public String getTopic() {
                return topic;
            }

            public void setTopic(String topic) {
                this.topic = topic;
            }
        }

        public static class Common {
            private String serializerString = "org.apache.kafka.common.serialization.StringSerializer";
            private String deserializerString = "org.apache.kafka.common.serialization.StringDeserializer";

            private String acks = "all";

            private final Bootstrap bootstrap = new Bootstrap();

            public Bootstrap getBootstrap() {
                return bootstrap;
            }

            public String getSerializerString() {
                return serializerString;
            }

            public String getDeserializerString() {
                return deserializerString;
            }

            public String getAcks() {
                return acks;
            }

            public void setSerializerString(String serializerString) {
                this.serializerString = serializerString;
            }

            public void setDeserializerString(String deserializerString) {
                this.deserializerString = deserializerString;
            }

            public void setAcks(String acks) {
                this.acks = acks;
            }

            public static class Bootstrap {
                private String servers = "localhost:9092";

                public String getServers() {
                    return servers;
                }

                public void setServers(String servers) {
                    this.servers = servers;
                }
            }
        }
    }

    public static class Hbase {

        private String master = "18.188.77.36";

        private final Zookeeper zookeeper = new Zookeeper();

        public String getMaster() {
            return master;
        }

        public void setMaster(String master) {
            this.master = master;
        }

        public Zookeeper getZookeeper() {
            return zookeeper;
        }

        public static class Zookeeper {

            private final Property property = new Property();
            private String quorum = "18.188.77.36";

            public String getQuorum() {
                return quorum;
            }

            public void setQuorum(String quorum) {
                this.quorum = quorum;
            }

            public Property getProperty() {
                return property;
            }

            public static class Property {
                private String clientPort = "2181";

                public String getClientPort() {
                    return clientPort;
                }

                public void setClientPort(String clientPort) {
                    this.clientPort = clientPort;
                }
            }
        }
    }
}
