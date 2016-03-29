package com.lenicliu.spring.boot;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.BiConsumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;

@Component
public class ChatHandler implements WebSocketHandler {

	private Logger							logger		= LoggerFactory.getLogger(ChatHandler.class);
	private Map<String, WebSocketSession>	exchange	= new ConcurrentHashMap<String, WebSocketSession>();

	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		if (!exchange.containsKey(session.getId())) {
			exchange.put(session.getId(), session);
			logger.info(session.getId() + " connected");
		}
	}

	@Override
	public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
		final String host = String.format("User(%s)", session.getId());
		final String time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
		final String text = message.getPayload().toString();
		logger.info(session.getId() + " : " + text);
		exchange.forEach(new BiConsumer<String, WebSocketSession>() {
			@Override
			public void accept(String id, WebSocketSession session) {
				try {
					session.sendMessage(new TextMessage(host + "(" + time + ") : <br/>" + text + "<br/>"));
				} catch (IOException e) {
					logger.error(e.getMessage(), e);
				}
			}
		});
	}

	@Override
	public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
		logger.error("websocket error", exception);
	}

	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
		if (exchange.containsKey(session.getId())) {
			exchange.remove(session.getId());
			logger.info(session.getId() + " closed");
		}
	}

	@Override
	public boolean supportsPartialMessages() {
		return false;
	}
}