package com.hyfocus.web.util;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.google.gson.Gson;
import com.hyfocus.web.service.ExtraService;
import com.hyfocus.web.service.RentService;

public class WebSocketHandler extends TextWebSocketHandler {
	
	@Autowired
	private ExtraService extraService;
	
	@Autowired
	private RentService rentService;
	
	private static final Map<Session, Integer> sessions = new ConcurrentHashMap<>();

    @OnOpen
    public void onOpen(Session session) {
        sessions.put(session, 1);
    }

    // 아ㅏㅏㅏㅏㅏㅏㅏ아아ㅏ아앙 구조를 잘 못짜서 삼각대까지 수정하려면 다 갈어엎어야 돼~!~~!~!~!~!~ 처음 구조가 중요합니다.
    @OnMessage
    public synchronized void onMessage(int rentNo, String extraName, Session session) {
    	int extraCount = extraService.chkCntByName(extraName);
    	if (extraCount < 1){
    		
    	} else {
    		int updatedExtraCount = rentService.updateUserExtra(rentNo, extraName, extraCount);

            // 모든 클라이언트에 남은 갯수 전송
            sessions.forEach((s, v) -> {
                try {
                    s.getBasicRemote().sendText(new Gson().toJson(updatedExtraCount));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
    	}
    }

    @OnClose
    public void onClose(Session session) {
        sessions.remove(session);
    }
}