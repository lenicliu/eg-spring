#	Run

*	mvn clean install
*	java -jar examples-spring-cloud-gateway-bookservice.jar
*	access http://localhost:8081/books
*	java -jar examples-spring-cloud-gateway-userservice.jar
*	access http://localhost:8082/users
*	java -jar examples-spring-cloud-gateway-proxy.jar
*	access http://localhost:8080/books
*	access http://localhost:8080/users

use `@EnableZuulProxy` running a simple zuul server
