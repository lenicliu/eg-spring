mvn clean package
java -Xms64M -Xmx64M -jar eg-spring-cloud-loadbalance-discovery/target/eg-spring-cloud-loadbalance-discovery-0.0.1-SNAPSHOT.jar --server.port=8080 --eureka.instance.hostname=site.localhost.com&
java -Xms64M -Xmx64M -jar eg-spring-cloud-loadbalance-bookservice/target/eg-spring-cloud-loadbalance-bookservice-0.0.1-SNAPSHOT.jar --server.port=8081 --eureka.instance.hostname=site1.localhost.com&
java -Xms64M -Xmx64M -jar eg-spring-cloud-loadbalance-bookservice/target/eg-spring-cloud-loadbalance-bookservice-0.0.1-SNAPSHOT.jar --server.port=8082 --eureka.instance.hostname=site2.localhost.com&
java -Xms64M -Xmx64M -jar eg-spring-cloud-loadbalance-client/target/eg-spring-cloud-loadbalance-client-0.0.1-SNAPSHOT.jar --server.port=8083 --eureka.instance.hostname=site3.localhost.com
