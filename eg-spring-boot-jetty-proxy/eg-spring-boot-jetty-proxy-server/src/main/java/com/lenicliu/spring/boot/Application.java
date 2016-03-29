package com.lenicliu.spring.boot;

import java.util.LinkedHashMap;
import java.util.Map;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

public class Application {
	public static void main(String[] args) throws Exception {
		Server server = new Server(9000);
		ServletContextHandler handler = new ServletContextHandler();
		// proxy reserve
		// handler.addServlet(proxy("jetty-proxy-userservice", "http://localhost:9001/users", "/users"), "/users");
		handler.addServlet(proxy("jetty-proxy-userservice", "http://localhost:9001", "/"), "/users");
		handler.addServlet(proxy("jetty-proxy-bookservice", "http://localhost:9002", "/"), "/books");
		server.setHandler(handler);
		server.start();
		server.join();
	}

	private static Map<String, String> mapping(String proxyTo, String prefix) {
		Map<String, String> mapping = new LinkedHashMap<>();
		mapping.put("proxyTo", proxyTo);
		mapping.put("prefix", prefix);
		return mapping;
	}

	private static ServletHolder proxy(String name, String proxyTo, String prefix) {
		ServletHolder proxy = new ServletHolder();
		proxy.setName(name);
		proxy.setClassName("org.eclipse.jetty.proxy.ProxyServlet$Transparent");
		proxy.setInitParameters(mapping(proxyTo, prefix));
		return proxy;
	}
}
