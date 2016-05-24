java -cp h2.jar org.h2.tools.Server -tcp -baseDir . &
java -Xms64M -Xmx64M -jar target/eg-spring-boot-session-jdbc-0.0.1-SNAPSHOT.jar --server.port=8080 &
java -Xms64M -Xmx64M -jar target/eg-spring-boot-session-jdbc-0.0.1-SNAPSHOT.jar --server.port=8081 &
