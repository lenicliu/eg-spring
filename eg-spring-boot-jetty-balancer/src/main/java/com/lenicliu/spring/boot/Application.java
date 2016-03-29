package com.lenicliu.spring.boot;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.session.HashSessionIdManager;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

public class Application {

	public static void main(String[] args) throws Exception {
		note("A", 9001).start();
		note("B", 9002).start();
		
		Server balancer = new Server(9000);
		new ServletContextHandler(balancer, "/app", ServletContextHandler.SESSIONS) {
			{
				addServlet(new ServletHolder() {
					{
						setName("load-balancer");
						setClassName("org.eclipse.jetty.proxy.BalancerServlet");
						setInitParameter("stickySessions", "true");
						setInitParameter("proxyPassReverse", "true");
						setInitParameter("balancerMember.A.proxyTo", "http://localhost:9001");
						setInitParameter("balancerMember.B.proxyTo", "http://localhost:9002");

					}
				}, "/simple");
			}
		};
		
		balancer.start();
	}

	static Server note(final String name, final int port) {
		Server server = new Server(port);
		server.setSessionIdManager(new HashSessionIdManager() {
			{
				setWorkerName(name);
			}
		});
		new ServletContextHandler(server, "/app", ServletContextHandler.SESSIONS) {
			{
				addServlet(new ServletHolder(new SimpleServlet(name)), "/simple");
			}
		};
		return server;
	}

	public static class SimpleServlet extends HttpServlet {

		private static final long	serialVersionUID	= 4400159405154824324L;
		private String				name;

		public SimpleServlet(String name) {
			super();
			this.name = name;
		}

		@Override
		protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
			res.setContentType("text/plain");
			res.setStatus(200);
			res.getWriter().write(name + ":" + req.getSession().getId());
		}
	}
}