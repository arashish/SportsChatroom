package com.sportschatroom.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

import com.sportschatroom.handler.BaseballChatWebSocketHandler;
import com.sportschatroom.handler.BasketballChatWebSocketHandler;
import com.sportschatroom.handler.BoxingChatWebSocketHandler;
import com.sportschatroom.handler.ChatWebSocketHandler;
import com.sportschatroom.handler.FootballChatWebSocketHandler;
import com.sportschatroom.handler.HockeyChatWebSocketHandler;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

    private final static String CHAT_ENDPOINT = "/chat";
    private final static String FOOTBALL_CHAT_ENDPOINT = "/footballchat";
    private final static String BOXING_CHAT_ENDPOINT = "/boxingchat";
    private final static String BASEBALL_CHAT_ENDPOINT = "/baseballchat";
    private final static String BASKETBALL_CHAT_ENDPOINT = "/basketballchat";
    private final static String HOCKEY_CHAT_ENDPOINT = "/hockeychat"; 

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry webSocketHandlerRegistry) {
        webSocketHandlerRegistry.addHandler(getChatWebSocketHandler(), CHAT_ENDPOINT)
                .setAllowedOrigins("*");
        webSocketHandlerRegistry.addHandler(getFootballChatWebSocketHandler(), FOOTBALL_CHAT_ENDPOINT)
        .setAllowedOrigins("*");
        webSocketHandlerRegistry.addHandler(getBoxingChatWebSocketHandler(), BOXING_CHAT_ENDPOINT)
        .setAllowedOrigins("*");
        webSocketHandlerRegistry.addHandler(getBaseballChatWebSocketHandler(), BASEBALL_CHAT_ENDPOINT)
        .setAllowedOrigins("*");
        webSocketHandlerRegistry.addHandler(getBasketballChatWebSocketHandler(), BASKETBALL_CHAT_ENDPOINT)
        .setAllowedOrigins("*");
        webSocketHandlerRegistry.addHandler(getHockeyChatWebSocketHandler(), HOCKEY_CHAT_ENDPOINT)
        .setAllowedOrigins("*");
    }

    @Bean
    public WebSocketHandler getChatWebSocketHandler(){
        return new ChatWebSocketHandler();
    }
    
    @Bean
    public WebSocketHandler getFootballChatWebSocketHandler(){
        return new FootballChatWebSocketHandler();
    }
    
    @Bean
    public WebSocketHandler getBoxingChatWebSocketHandler(){
        return new BoxingChatWebSocketHandler();
    }
    
    @Bean
    public WebSocketHandler getBaseballChatWebSocketHandler(){
        return new BaseballChatWebSocketHandler();
    }
    
    @Bean
    public WebSocketHandler getBasketballChatWebSocketHandler(){
        return new BasketballChatWebSocketHandler();
    }
    
    @Bean
    public WebSocketHandler getHockeyChatWebSocketHandler(){
        return new HockeyChatWebSocketHandler();
    }
}
