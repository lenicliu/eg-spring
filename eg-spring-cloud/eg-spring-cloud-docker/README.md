*	打包 `mvn package docker:build`
*	启动容器 `docker run -p 8080:8080 -t app/examples-spring-cloud-docker`
*	-p 8080:8080 端口映射
*	-t 控制台
*	访问 `http://machine-ip:8080/users`

*	查询虚拟机 `docker-machine ls`
*	ssh到虚拟机 `docker-machine ssh`
*	开机|关机｜重启 `docker-machine start|stop|restart`
*	查询容器 `docker ps`
*	启动容器 `docker run [container]`
*	停止容器 `docker stop [container]`