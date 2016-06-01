curl -X POST http://localhost:8080/shutdown
curl -X POST http://localhost:8081/shutdown
java -cp h2.jar org.h2.tools.Server -tcpShutdown "tcp://localhost"